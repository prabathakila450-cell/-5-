package com.example

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import java.net.URLEncoder

// Three Official Online Question Paper Portals for Grade 11
const val GOVDOC_GRADE_11_URL = "https://govdoc.lk/category/term-test-papers/grade-11"
const val ETHAKSALAWA_GRADE_11_URL = "https://e-thaksalawa.moe.gov.lk/lcms/course/index.php?categoryid=22"
const val PASTPAPERS_WIKI_GRADE_11_URL = "https://pastpapers.wiki/category/term-test-papers/grade-11/"

enum class Grade11WebSource(
  val title: String,
  val subtitle: String,
  val url: String,
  val badge: String,
  val badgeColor: Color
) {
  GOVDOC(
    title = "GovDoc.lk",
    subtitle = "11 වසර වාර විභාග ප්‍රශ්න පත්‍ර",
    url = GOVDOC_GRADE_11_URL,
    badge = "GOVDOC (11)",
    badgeColor = Color(0xFF047857)
  ),
  ETHAKSALAWA(
    title = "e-Thaksalawa",
    subtitle = "අධ්‍යාපන අමාත්‍යාංශ නිල ද්වාරය",
    url = ETHAKSALAWA_GRADE_11_URL,
    badge = "OFFICIAL MOE",
    badgeColor = Color(0xFF1D4ED8)
  ),
  PASTPAPERS_WIKI(
    title = "PastPapers.wiki",
    subtitle = "11 ශ්‍රේණිය පළාත් වාර විභාග ප්‍රශ්න පත්‍ර",
    url = PASTPAPERS_WIKI_GRADE_11_URL,
    badge = "PAST PAPERS",
    badgeColor = Color(0xFFD97706)
  )
}

data class Grade11SubjectFilter(
  val nameSinhala: String,
  val keyword: String,
  val icon: String
)

val grade11SubjectFilterList = listOf(
  Grade11SubjectFilter("සියලුම විෂයන්", "", "📚"),
  Grade11SubjectFilter("විද්‍යාව", "science", "🧬"),
  Grade11SubjectFilter("ගණිතය", "mathematics", "📐"),
  Grade11SubjectFilter("සිංහල", "sinhala", "📖"),
  Grade11SubjectFilter("ඉංග්‍රීසි", "english", "🔤"),
  Grade11SubjectFilter("ඉතිහාසය", "history", "🏛️"),
  Grade11SubjectFilter("බුද්ධ ධර්මය", "buddhism", "☸️"),
  Grade11SubjectFilter("භූගෝල විද්‍යාව", "geography", "🌍"),
  Grade11SubjectFilter("පුරවැසි අධ්‍යාපනය", "civics", "⚖️"),
  Grade11SubjectFilter("තොරතුරු තාක්ෂණය (ICT)", "ict", "💻"),
  Grade11SubjectFilter("වාණිජ්‍ය", "commerce", "📊"),
  Grade11SubjectFilter("කෘෂිකර්මය", "agriculture", "🌱"),
  Grade11SubjectFilter("සෞඛ්‍යය", "health", "🏃"),
  Grade11SubjectFilter("චිත්‍ර කලාව", "art", "🎨"),
  Grade11SubjectFilter("සංගීතය", "music", "🎵"),
  Grade11SubjectFilter("නර්තනය", "dancing", "💃")
)

fun buildGrade11PortalUrl(source: Grade11WebSource, subjectKeyword: String?, term: Int?): String {
  val cleanKey = subjectKeyword?.trim().orEmpty()
  val termStr = when (term) {
    1 -> "1st term"
    2 -> "2nd term"
    3 -> "3rd term"
    else -> ""
  }

  val queryParts = listOf(cleanKey, termStr).filter { it.isNotBlank() }
  val query = queryParts.joinToString(" ")

  return when (source) {
    Grade11WebSource.GOVDOC -> {
      if (query.isNotBlank()) {
        try {
          "https://govdoc.lk/category/term-test-papers/grade-11?s=${URLEncoder.encode(query, "UTF-8")}"
        } catch (_: Exception) {
          GOVDOC_GRADE_11_URL
        }
      } else {
        GOVDOC_GRADE_11_URL
      }
    }
    Grade11WebSource.ETHAKSALAWA -> {
      ETHAKSALAWA_GRADE_11_URL
    }
    Grade11WebSource.PASTPAPERS_WIKI -> {
      if (query.isNotBlank()) {
        try {
          "https://pastpapers.wiki/?s=${URLEncoder.encode("grade 11 $query", "UTF-8")}"
        } catch (_: Exception) {
          PASTPAPERS_WIKI_GRADE_11_URL
        }
      } else {
        PASTPAPERS_WIKI_GRADE_11_URL
      }
    }
  }
}

/**
 * 11 ශ්‍රේණිය වාර විභාග ප්‍රශ්න පත්‍ර වෙබ් අඩවි ත්‍රිත්ව ද්වාරය (Grade 11 Three Portals Viewer)
 * 1. GovDoc.lk (11 වසර වාර විභාග)
 * 2. e-Thaksalawa (අධ්‍යාපන අමාත්‍යාංශ නිල ද්වාරය)
 * 3. PastPapers.wiki (11 ශ්‍රේණිය පළාත් ප්‍රශ්න පත්‍ර)
 * 
 * - මුළු තිරයම ඉහළ පහළ කළ හැකි (Full Vertical Scrollable)
 * - PDF සහ Google Drive ප්‍රශ්න පත්‍ර ඇප් එක තුළින්ම නැරඹිය හැකි (In-App PDF View)
 * - DRM & Screenshot Security Active (ඇඩ්මින් හැර අනෙක් අයට තිර ඡායාරූප අවහිර කර ඇත)
 */
@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Grade11SecureTermTestPortalScreen(
  initialSource: Grade11WebSource = Grade11WebSource.GOVDOC,
  initialSubject: String? = null,
  initialTerm: Int? = null,
  studentName: String = "ශිෂ්‍ය ගිණුම",
  studentPhone: String = "",
  onOpenPdfModal: ((url: String, title: String) -> Unit)? = null,
  onBack: () -> Unit
) {
  val context = LocalContext.current
  val activity = context as? Activity

  var hardwareSecureEnabled by remember { mutableStateOf(true) }

  DisposableEffect(activity, hardwareSecureEnabled) {
    activity?.let { act ->
      AppSecurityManager.applyScreenProtection(act, hardwareSecureEnabled)
    }
    onDispose {
      activity?.let { act ->
        AppSecurityManager.enforceSecurityForUser(act, AppSecurityManager.isAdminSession)
      }
    }
  }

  var currentSource by remember { mutableStateOf(initialSource) }
  var selectedSubjectName by remember {
    mutableStateOf(
      if (!initialSubject.isNullOrBlank() && grade11SubjectFilterList.any { it.nameSinhala == initialSubject }) {
        initialSubject
      } else {
        "සියලුම විෂයන්"
      }
    )
  }
  var selectedTerm by remember { mutableStateOf(initialTerm ?: 0) } // 0: All, 1: 1st, 2: 2nd, 3: 3rd

  val selectedSubjectKeyword = remember(selectedSubjectName) {
    grade11SubjectFilterList.firstOrNull { it.nameSinhala == selectedSubjectName }?.keyword.orEmpty()
  }

  var activeUrl by remember {
    mutableStateOf(buildGrade11PortalUrl(initialSource, selectedSubjectKeyword, selectedTerm))
  }

  var webViewInstance by remember { mutableStateOf<WebView?>(null) }
  var isLoading by remember { mutableStateOf(true) }
  var pageProgress by remember { mutableIntStateOf(0) }
  var canGoBack by remember { mutableStateOf(false) }
  var canGoForward by remember { mutableStateOf(false) }
  var showSecurityInfoDialog by remember { mutableStateOf(false) }
  var showWatermark by remember { mutableStateOf(true) }

  fun navigateTo(source: Grade11WebSource, subjectKey: String, term: Int) {
    currentSource = source
    val targetUrl = buildGrade11PortalUrl(source, subjectKey, if (term > 0) term else null)
    activeUrl = targetUrl
    webViewInstance?.loadUrl(targetUrl)
  }

  // Hardware back button navigates web history first
  BackHandler {
    if (webViewInstance?.canGoBack() == true) {
      webViewInstance?.goBack()
    } else {
      onBack()
    }
  }

  Scaffold(
    topBar = {
      Surface(
        color = Color(0xFF0F172A),
        shadowElevation = 4.dp
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 8.dp, vertical = 6.dp)
        ) {
          // Top Header Row
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.weight(1f)
            ) {
              IconButton(
                onClick = onBack,
                modifier = Modifier
                  .size(36.dp)
                  .testTag("grade11_portal_back_btn")
              ) {
                Icon(
                  imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                  contentDescription = "Back",
                  tint = Color.White
                )
              }

              Spacer(modifier = Modifier.width(4.dp))

              Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Text(
                    text = "11 වසර වාර විභාග ප්‍රශ්න පත්‍ර",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                  )
                  Spacer(modifier = Modifier.width(6.dp))
                  Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = currentSource.badgeColor
                  ) {
                    Text(
                      text = currentSource.badge,
                      fontSize = 8.sp,
                      fontWeight = FontWeight.ExtraBold,
                      color = Color.White,
                      modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                    )
                  }
                }

                Text(
                  text = "${currentSource.title} • $selectedSubjectName • ${if (selectedTerm > 0) "$selectedTerm වන වාරය" else "සියලුම වාර"}",
                  fontSize = 10.sp,
                  color = Color(0xFF94A3B8),
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis
                )
              }
            }

            IconButton(
              onClick = { showSecurityInfoDialog = true },
              modifier = Modifier.size(36.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Security,
                contentDescription = "Security Info",
                tint = Color(0xFF34D399)
              )
            }
          }

          Spacer(modifier = Modifier.height(4.dp))

          // 1. THREE MAIN WEBSITE TABS (GovDoc.lk | e-Thaksalawa | PastPapers.wiki)
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 4.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Grade11WebSource.values().forEach { src ->
              val isSelected = currentSource == src
              Surface(
                onClick = {
                  if (currentSource != src) {
                    navigateTo(src, selectedSubjectKeyword, selectedTerm)
                  }
                },
                shape = RoundedCornerShape(8.dp),
                color = if (isSelected) src.badgeColor else Color(0xFF1E293B),
                border = BorderStroke(1.dp, if (isSelected) src.badgeColor else Color(0xFF334155)),
                modifier = Modifier
                  .weight(1f)
                  .testTag("portal_tab_${src.name.lowercase()}")
              ) {
                Column(
                  modifier = Modifier.padding(vertical = 7.dp, horizontal = 4.dp),
                  horizontalAlignment = Alignment.CenterHorizontally
                ) {
                  Text(
                    text = when (src) {
                      Grade11WebSource.GOVDOC -> "📄"
                      Grade11WebSource.ETHAKSALAWA -> "🏛️"
                      Grade11WebSource.PASTPAPERS_WIKI -> "📑"
                    },
                    fontSize = 12.sp
                  )
                  Text(
                    text = src.title,
                    fontSize = 10.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) Color.White else Color(0xFFCBD5E1),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                  )
                  Text(
                    text = when (src) {
                      Grade11WebSource.GOVDOC -> "11 වාර Papers"
                      Grade11WebSource.ETHAKSALAWA -> "නිල MOE"
                      Grade11WebSource.PASTPAPERS_WIKI -> "පළාත් Papers"
                    },
                    fontSize = 8.sp,
                    color = if (isSelected) Color(0xFFE2E8F0) else Color(0xFF94A3B8),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                  )
                }
              }
            }
          }

          // 2. Terms Bar (සියලුම වාර | 1 වන වාරය | 2 වන වාරය | 3 වන වාරය)
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 4.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            val termsList = listOf(
              0 to "සියලුම වාර",
              1 to "1 වන වාරය",
              2 to "2 වන වාරය",
              3 to "3 වන වාරය"
            )

            termsList.forEach { (termNum, label) ->
              val isSelected = selectedTerm == termNum
              Surface(
                onClick = {
                  selectedTerm = termNum
                  navigateTo(currentSource, selectedSubjectKeyword, termNum)
                },
                shape = RoundedCornerShape(6.dp),
                color = if (isSelected) Color(0xFF047857) else Color(0xFF1E293B),
                border = BorderStroke(1.dp, if (isSelected) Color(0xFF10B981) else Color(0xFF334155)),
                modifier = Modifier.weight(1f)
              ) {
                Box(
                  modifier = Modifier.padding(vertical = 5.dp),
                  contentAlignment = Alignment.Center
                ) {
                  Text(
                    text = label,
                    fontSize = 9.5.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) Color.White else Color(0xFFCBD5E1)
                  )
                }
              }
            }
          }

          // 3. Subject Filter Chips (Horizontal Scrollable)
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .horizontalScroll(rememberScrollState())
              .padding(horizontal = 4.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            grade11SubjectFilterList.forEach { filterItem ->
              val isSelected = selectedSubjectName == filterItem.nameSinhala
              Surface(
                onClick = {
                  selectedSubjectName = filterItem.nameSinhala
                  navigateTo(currentSource, filterItem.keyword, selectedTerm)
                },
                shape = RoundedCornerShape(16.dp),
                color = if (isSelected) Color(0xFF059669) else Color(0xFF1E293B),
                border = BorderStroke(1.dp, if (isSelected) Color(0xFF34D399) else Color(0xFF334155))
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text(filterItem.icon, fontSize = 11.sp)
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = filterItem.nameSinhala,
                    fontSize = 10.5.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) Color.White else Color(0xFFCBD5E1)
                  )
                }
              }
            }
          }

          // Loading Progress Bar
          if (isLoading) {
            LinearProgressIndicator(
              progress = { pageProgress / 100f },
              modifier = Modifier
                .fillMaxWidth()
                .height(2.5.dp),
              color = Color(0xFF10B981),
              trackColor = Color(0xFF1E293B)
            )
          }
        }
      }
    },
    bottomBar = {
      // Bottom Utility Controls (Web Navigation & Zoom)
      Surface(
        color = Color(0xFF0F172A),
        border = BorderStroke(1.dp, Color(0xFF1E293B)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 8.dp, vertical = 4.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
              onClick = {
                if (webViewInstance?.canGoBack() == true) webViewInstance?.goBack()
              },
              enabled = canGoBack,
              modifier = Modifier.size(36.dp)
            ) {
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Web Back",
                tint = if (canGoBack) Color.White else Color(0xFF475569),
                modifier = Modifier.size(18.dp)
              )
            }

            IconButton(
              onClick = {
                if (webViewInstance?.canGoForward() == true) webViewInstance?.goForward()
              },
              enabled = canGoForward,
              modifier = Modifier.size(36.dp)
            ) {
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Web Forward",
                tint = if (canGoForward) Color.White else Color(0xFF475569),
                modifier = Modifier.size(18.dp)
              )
            }

            IconButton(
              onClick = { webViewInstance?.reload() },
              modifier = Modifier.size(36.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Reload",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
              )
            }

            IconButton(
              onClick = {
                navigateTo(currentSource, "", 0)
              },
              modifier = Modifier.size(36.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Reset Home",
                tint = Color(0xFF38BDF8),
                modifier = Modifier.size(18.dp)
              )
            }
          }

          // Zoom Controls
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Surface(
              onClick = { webViewInstance?.zoomOut() },
              shape = RoundedCornerShape(4.dp),
              color = Color(0xFF1E293B),
              border = BorderStroke(1.dp, Color(0xFF334155))
            ) {
              Text(
                text = "A-",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
              )
            }

            Surface(
              onClick = { webViewInstance?.zoomIn() },
              shape = RoundedCornerShape(4.dp),
              color = Color(0xFF1E293B),
              border = BorderStroke(1.dp, Color(0xFF334155))
            ) {
              Text(
                text = "A+",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
              )
            }
          }
        }
      }
    }
  ) { paddingValues ->
    // Full Screen Scrollable Web Container (100% Up and Down Scrollable)
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        .background(Color.White)
    ) {
      AndroidView(
        factory = { ctx ->
          WebView(ctx).apply {
            webViewInstance = this
            isVerticalScrollBarEnabled = true
            isHorizontalScrollBarEnabled = true

            // Anti-Copy & Secure configuration
            isLongClickable = false
            isHapticFeedbackEnabled = false
            setOnLongClickListener { true }

            settings.apply {
              javaScriptEnabled = true
              domStorageEnabled = true
              databaseEnabled = true
              setSupportZoom(true)
              builtInZoomControls = true
              displayZoomControls = false
              useWideViewPort = true
              loadWithOverviewMode = true
              mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
              userAgentString = "Mozilla/5.0 (Linux; Android 14; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Mobile Safari/537.36"
            }

            webChromeClient = object : WebChromeClient() {
              override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                pageProgress = newProgress
              }
            }

            webViewClient = object : WebViewClient() {
              override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                isLoading = true
              }

              override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                isLoading = false
                canGoBack = view?.canGoBack() ?: false
                canGoForward = view?.canGoForward() ?: false

                // Anti-Copy and Text Selection Protection
                val drmScript = """
                  (function() {
                    var css = '* { -webkit-user-select: none !important; user-select: none !important; }';
                    var head = document.head || document.getElementsByTagName('head')[0];
                    if (head) {
                      var style = document.createElement('style');
                      style.type = 'text/css';
                      style.appendChild(document.createTextNode(css));
                      head.appendChild(style);
                    }
                    ['contextmenu', 'copy', 'cut', 'selectstart'].forEach(function(evt) {
                      document.addEventListener(evt, function(e) { e.preventDefault(); return false; }, true);
                    });
                  })();
                """.trimIndent()
                view?.evaluateJavascript(drmScript, null)
              }

              override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val target = request?.url?.toString() ?: return false
                // If it's a PDF file link, open via clean Google Docs Viewer
                if (target.endsWith(".pdf", ignoreCase = true) || target.contains(".pdf?", ignoreCase = true)) {
                  view?.loadUrl("https://docs.google.com/viewer?embedded=true&url=${URLEncoder.encode(target, "UTF-8")}")
                  return true
                }
                // If it's a Google Drive link, load in view
                if (target.contains("drive.google.com") && !target.contains("/preview")) {
                  val previewUrl = if (target.contains("/view")) target.replace("/view", "/preview") else target
                  view?.loadUrl(previewUrl)
                  return true
                }
                return false
              }
            }

            // Intercept downloads and render directly inside viewer
            setDownloadListener { downloadUrl, _, _, _, _ ->
              if (downloadUrl.endsWith(".pdf", ignoreCase = true) || downloadUrl.contains(".pdf")) {
                loadUrl("https://docs.google.com/viewer?embedded=true&url=${URLEncoder.encode(downloadUrl, "UTF-8")}")
              } else {
                Toast.makeText(ctx, "ප්‍රශ්න පත්‍රය යෙදුම තුළම කියවීමට සූදානම් කෙරේ...", Toast.LENGTH_SHORT).show()
                loadUrl(downloadUrl)
              }
            }

            loadUrl(activeUrl)
          }
        },
        update = { webView ->
          webViewInstance = webView
        },
        onRelease = { webView ->
          webView.stopLoading()
          webView.destroy()
        },
        modifier = Modifier.fillMaxSize()
      )

      // Anti-Theft Watermark
      if (showWatermark) {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .alpha(0.06f)
            .padding(16.dp)
        ) {
          Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            repeat(4) { idx ->
              Text(
                text = "GRADE 11 OFFICIAL • ${studentName.take(15)}",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.rotate(if (idx % 2 == 0) -15f else 15f)
              )
            }
          }
        }
      }
    }
  }

  // Security Information Dialog
  if (showSecurityInfoDialog) {
    AlertDialog(
      onDismissRequest = { showSecurityInfoDialog = false },
      title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.Security,
            contentDescription = null,
            tint = Color(0xFF047857),
            modifier = Modifier.size(24.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "11 ශ්‍රේණිය වෙබ් ද්වාර ආරක්ෂණ පද්ධතිය",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0F172A)
          )
        }
      },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(
            text = "මෙම 11 ශ්‍රේණිය වාර විභාග ද්වාරයෙහි නිල වෙබ් අඩවි 3ක (GovDoc.lk, e-Thaksalawa, PastPapers.wiki) ප්‍රශ්න පත්‍ර සෘජුව නැරඹිය හැක:",
            fontSize = 12.sp,
            color = Color(0xFF334155)
          )

          listOf(
            "📄 GovDoc.lk" to "11 වසර සියලුම පළාත් හා පාසල් වාර විභාග ප්‍රශ්න පත්‍ර සහ පිළිතුරු.",
            "🏛️ e-Thaksalawa" to "ශ්‍රී ලංකා අධ්‍යාපන අමාත්‍යාංශයේ නිල ඉගෙනුම් ද්වාරය.",
            "📑 PastPapers.wiki" to "පළාත් සභා හා ප්‍රමුඛ පෙළේ පාසල් ප්‍රශ්න පත්‍ර එකතුව.",
            "🚫 ආරක්ෂාව" to "තිර ඡායාරූප (Screenshots) සහ පිටපත් කිරීම (Anti-Copy) අවහිර කර ඇත."
          ).forEach { (itemTitle, itemDesc) ->
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = Color(0xFFF1F5F9),
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(modifier = Modifier.padding(8.dp)) {
                Text(
                  text = itemTitle,
                  fontSize = 11.5.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF0F172A)
                )
                Text(
                  text = itemDesc,
                  fontSize = 10.sp,
                  color = Color(0xFF475569)
                )
              }
            }
          }
        }
      },
      confirmButton = {
        Button(
          onClick = { showSecurityInfoDialog = false },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF047857))
        ) {
          Text("තහවුරුයි", color = Color.White, fontSize = 12.sp)
        }
      }
    )
  }
}
