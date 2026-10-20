package com.example

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OlSubjectToolsScreen(
  initialSubject: String = "විද්‍යාව",
  lockSubject: Boolean = false,
  showInnerTopBar: Boolean = false,
  isFullScreenMode: Boolean = false,
  onToggleFullScreen: (() -> Unit)? = null,
  onBack: () -> Unit
) {
  val context = LocalContext.current
  val clipboardManager = LocalClipboardManager.current
  var selectedSubject by remember { mutableStateOf(initialSubject) }
  var selectedGroupNumber by remember { mutableStateOf(0) } // 0: All groups
  var searchQuery by remember { mutableStateOf("") }
  var expandedToolId by remember { mutableStateOf<String?>(null) }

  // Internal fullscreen state fallback
  var internalFullScreen by remember { mutableStateOf(false) }
  val effectiveFullScreen = isFullScreenMode || internalFullScreen
  val toggleFullScreenAction: () -> Unit = {
    if (onToggleFullScreen != null) {
      onToggleFullScreen()
    } else {
      internalFullScreen = !internalFullScreen
    }
  }

  // Full Screen Tool Modal & Category Modal
  var fullScreenTool by remember { mutableStateOf<OlSubjectToolItem?>(null) }
  var fullScreenCategoryGroup by remember { mutableStateOf<Int?>(null) }

  val subjects = remember { OlSubjectToolsRepository.subjectsList }
  val currentSubjectCategory = remember(selectedSubject) {
    subjects.find { it.subjectName == selectedSubject } ?: subjects.first()
  }

  val allToolsForSubject = remember(selectedSubject) {
    OlSubjectToolsRepository.getToolsForSubject(selectedSubject)
  }

  val filteredTools = remember(allToolsForSubject, selectedGroupNumber, searchQuery) {
    allToolsForSubject.filter { item ->
      val matchesGroup = selectedGroupNumber == 0 || item.groupNumber == selectedGroupNumber
      val matchesSearch = searchQuery.isBlank() ||
          item.titleSinhala.contains(searchQuery, ignoreCase = true) ||
          item.titleEnglish.contains(searchQuery, ignoreCase = true) ||
          item.category.contains(searchQuery, ignoreCase = true) ||
          item.formulaOrRule.contains(searchQuery, ignoreCase = true) ||
          item.quickSummary.contains(searchQuery, ignoreCase = true)
      matchesGroup && matchesSearch
    }
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xFF0B132B))
  ) {
    // Single, fully scrollable LazyColumn containing ALL components from top to bottom
    // so the user can freely scroll the headers, search, and category chips up and down!
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .testTag("ol_subject_tools_lazy_column"),
      contentPadding = PaddingValues(
        top = if (effectiveFullScreen) 8.dp else 4.dp,
        bottom = 88.dp,
        start = 10.dp,
        end = 10.dp
      ),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      // 1. Optional Inner Top App Bar (Only when standalone and NOT in full-screen)
      if (showInnerTopBar && !effectiveFullScreen) {
        item(key = "inner_top_bar") {
          Surface(
            color = Color(0xFF0F172A),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
              .fillMaxWidth()
              .padding(bottom = 2.dp)
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              IconButton(onClick = onBack) {
                Icon(
                  imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                  contentDescription = "Back",
                  tint = Color.White
                )
              }
              Spacer(modifier = Modifier.width(4.dp))
              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = "🛠️ O/L විෂය මෙවලම් (Tools 100)",
                  fontSize = 15.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color.White
                )
                Text(
                  text = "${currentSubjectCategory.subjectName} • මෙවලම් 100 (10x10 කාණ්ඩ)",
                  fontSize = 11.sp,
                  color = Color(0xFF38BDF8)
                )
              }
              IconButton(onClick = toggleFullScreenAction) {
                Icon(
                  imageVector = Icons.Default.Fullscreen,
                  contentDescription = "Full Screen",
                  tint = Color(0xFF38BDF8)
                )
              }
            }
          }
        }
      }

      // 2. Subject Selector Tabs (Scrolls up and down with the screen - hidden if lockSubject is true)
      if (!lockSubject) {
        item(key = "subject_selector_tabs") {
          Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFF0F172A),
            modifier = Modifier.fillMaxWidth()
          ) {
            ScrollableTabRow(
              selectedTabIndex = subjects.indexOfFirst { it.subjectName == selectedSubject }.coerceAtLeast(0),
              containerColor = Color.Transparent,
              contentColor = Color.White,
              edgePadding = 8.dp,
              divider = {}
            ) {
              subjects.forEach { category ->
                val isSelected = selectedSubject == category.subjectName
                Tab(
                  selected = isSelected,
                  onClick = {
                    selectedSubject = category.subjectName
                    selectedGroupNumber = 0
                    expandedToolId = null
                  },
                  text = {
                    Row(
                      verticalAlignment = Alignment.CenterVertically,
                      modifier = Modifier.padding(vertical = 6.dp)
                    ) {
                      Text(category.icon, fontSize = 14.sp)
                      Spacer(modifier = Modifier.width(6.dp))
                      Text(
                        text = "${category.subjectName} (100)",
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) category.color else Color(0xFF94A3B8)
                      )
                    }
                  }
                )
              }
            }
          }
        }
      }

      // 3. Search & Category 10x10 Selector (Scrolls up and down with the screen)
      item(key = "search_and_category_header") {
        Surface(
          color = Color(0xFF1E293B),
          shape = RoundedCornerShape(14.dp),
          modifier = Modifier.fillMaxWidth(),
          border = BorderStroke(1.dp, Color(0xFF334155))
        ) {
          Column(modifier = Modifier.padding(12.dp)) {
            // Search Input Row with Fullscreen Toggle
            Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.weight(1f),
                placeholder = {
                  Text("මෙවලම, සූත්‍රය හෝ නම සොයන්න...", fontSize = 12.sp, color = Color(0xFF64748B))
                },
                leadingIcon = {
                  Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color(0xFF38BDF8),
                    modifier = Modifier.size(18.dp)
                  )
                },
                trailingIcon = {
                  if (searchQuery.isNotBlank()) {
                    IconButton(onClick = { searchQuery = "" }) {
                      Icon(
                        Icons.Default.Close,
                        contentDescription = "Clear",
                        tint = Color(0xFF94A3B8),
                        modifier = Modifier.size(16.dp)
                      )
                    }
                  }
                },
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                  focusedContainerColor = Color(0xFF0F172A),
                  unfocusedContainerColor = Color(0xFF0F172A),
                  focusedBorderColor = Color(0xFF38BDF8),
                  unfocusedBorderColor = Color(0xFF334155),
                  focusedTextColor = Color.White,
                  unfocusedTextColor = Color.White
                ),
                singleLine = true
              )

              // Quick Fullscreen Toggle Button
              Surface(
                onClick = toggleFullScreenAction,
                shape = RoundedCornerShape(10.dp),
                color = if (effectiveFullScreen) Color(0xFF10B981) else Color(0xFF0F172A),
                border = BorderStroke(1.dp, if (effectiveFullScreen) Color(0xFF10B981) else Color(0xFF38BDF8)),
                modifier = Modifier.height(52.dp)
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 10.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.Center
                ) {
                  Icon(
                    imageVector = if (effectiveFullScreen) Icons.Default.FullscreenExit else Icons.Default.Fullscreen,
                    contentDescription = "Full Screen",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                  )
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = if (effectiveFullScreen) "සාමාන්‍ය" else "ෆුල් ස්ක්‍රීන්",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                  )
                }
              }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Category Bar Title & Category Fullscreen Button
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "📂 කාණ්ඩ 10 තෝරන්න (10x10 කාණ්ඩ ක්‍රමය):",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF38BDF8)
              )

              if (selectedGroupNumber in 1..10) {
                Surface(
                  onClick = { fullScreenCategoryGroup = selectedGroupNumber },
                  shape = RoundedCornerShape(8.dp),
                  color = Color(0xFF2563EB).copy(alpha = 0.25f),
                  border = BorderStroke(1.dp, Color(0xFF38BDF8).copy(alpha = 0.5f))
                ) {
                  Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                  ) {
                    Text("⛶", fontSize = 11.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                      text = "කාණ්ඩය $selectedGroupNumber ෆුල් ස්ක්‍රීන්",
                      fontSize = 10.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color(0xFF38BDF8)
                    )
                  }
                }
              }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // 10 Groups Filter Chips (Scrolls horizontally inside this row)
            LazyRow(
              horizontalArrangement = Arrangement.spacedBy(6.dp),
              modifier = Modifier.fillMaxWidth()
            ) {
              item {
                FilterChip(
                  selected = selectedGroupNumber == 0,
                  onClick = { selectedGroupNumber = 0 },
                  label = { Text("සියල්ල (100)", fontSize = 11.sp) },
                  colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Color(0xFF38BDF8),
                    selectedLabelColor = Color(0xFF0F172A),
                    containerColor = Color(0xFF0F172A),
                    labelColor = Color.White
                  )
                )
              }

              items((1..10).toList()) { grp ->
                val isSelected = selectedGroupNumber == grp
                FilterChip(
                  selected = isSelected,
                  onClick = { selectedGroupNumber = grp },
                  label = {
                    Text(
                      text = "කාණ්ඩය $grp (10)",
                      fontSize = 11.sp
                    )
                  },
                  colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Color(0xFF38BDF8),
                    selectedLabelColor = Color(0xFF0F172A),
                    containerColor = Color(0xFF0F172A),
                    labelColor = Color.White
                  )
                )
              }
            }
          }
        }
      }

      // 4. Group Header Info Banner (Scrolls up and down with the screen)
      if (selectedGroupNumber in 1..10) {
        item(key = "active_group_info_banner") {
          Surface(
            color = Color(0xFF0F172A),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color(0xFF38BDF8).copy(alpha = 0.35f)),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
              ) {
                Text("📌", fontSize = 13.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                  Text(
                    text = "කාණ්ඩය $selectedGroupNumber: ${OlSubjectToolsRepository.getGroupName(selectedSubject, selectedGroupNumber)}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF38BDF8),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                  )
                  Text(
                    text = "මෙම කාණ්ඩයට අයත් මෙවලම් 10",
                    fontSize = 10.sp,
                    color = Color(0xFF94A3B8)
                  )
                }
              }

              // Direct Fullscreen Reader for this category
              Surface(
                onClick = { fullScreenCategoryGroup = selectedGroupNumber },
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF10B981),
                modifier = Modifier.padding(start = 6.dp)
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text("⛶", fontSize = 11.sp, color = Color.White)
                  Spacer(modifier = Modifier.width(3.dp))
                  Text(
                    text = "ෆුල් ස්ක්‍රීන්",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                  )
                }
              }
            }
          }
        }
      }

      // 5. Tools Count & Quality Indicator
      item(key = "tools_count_summary") {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "පෙන්වන්නේ මෙවලම් ${filteredTools.size} / 100",
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF94A3B8)
          )
          Text(
            text = "100% O/L විෂය නිර්දේශානුකූලයි",
            fontSize = 10.sp,
            color = Color(0xFF10B981),
            fontWeight = FontWeight.Bold
          )
        }
      }

      // 6. Tools Cards (100 items)
      items(filteredTools, key = { it.id }) { tool ->
        val isExpanded = expandedToolId == tool.id

        Card(
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
          border = BorderStroke(
            1.dp,
            if (isExpanded) Color(0xFF38BDF8) else Color(0xFF334155)
          ),
          modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .clickable {
              expandedToolId = if (isExpanded) null else tool.id
            }
        ) {
          Column(modifier = Modifier.padding(12.dp)) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.fillMaxWidth()
            ) {
              // Tool Number badge
              Box(
                modifier = Modifier
                  .size(34.dp)
                  .clip(CircleShape)
                  .background(Color(0xFF0F172A)),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = "#${tool.toolNumber}",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.ExtraBold,
                  color = Color(0xFF38BDF8)
                )
              }

              Spacer(modifier = Modifier.width(10.dp))

              Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Text(tool.icon, fontSize = 14.sp)
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = tool.titleSinhala,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                  )
                }
                Text(
                  text = "${tool.titleEnglish} • [${tool.category}]",
                  fontSize = 10.sp,
                  color = Color(0xFF94A3B8)
                )
              }

              // Group badge
              Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF334155)
              ) {
                Text(
                  text = "G${tool.groupNumber}",
                  fontSize = 9.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF38BDF8),
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }

              Spacer(modifier = Modifier.width(4.dp))

              // Card-level Fullscreen trigger button
              IconButton(
                onClick = { fullScreenTool = tool },
                modifier = Modifier.size(32.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Fullscreen,
                  contentDescription = "Full Screen Tool View",
                  tint = Color(0xFF38BDF8),
                  modifier = Modifier.size(18.dp)
                )
              }

              Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Collapse" else "Expand",
                tint = Color(0xFF94A3B8),
                modifier = Modifier.size(20.dp)
              )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Quick summary
            Text(
              text = tool.quickSummary,
              fontSize = 11.sp,
              color = Color(0xFFE2E8F0),
              lineHeight = 16.sp
            )

            // Formula or Rule Highlight Box
            Spacer(modifier = Modifier.height(6.dp))
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = Color(0xFF0F172A),
              border = BorderStroke(1.dp, Color(0xFF38BDF8).copy(alpha = 0.3f)),
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Column(modifier = Modifier.weight(1f)) {
                  Text(
                    text = "⚡ සූත්‍රය / නීතිය / මූලධර්මය:",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF38BDF8)
                  )
                  Text(
                    text = tool.formulaOrRule,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFF1F5F9)
                  )
                }
                IconButton(
                  onClick = {
                    if (AppSecurityManager.canCopyContent(context)) {
                      clipboardManager.setText(AnnotatedString(tool.formulaOrRule))
                      Toast.makeText(context, "සූත්‍රය පිටපත් විය (Copied)!", Toast.LENGTH_SHORT).show()
                    } else {
                      Toast.makeText(context, "ආරක්ෂක නීති අනුව සටහන් පිටපත් කිරීම (Copy) අවහිර කර ඇත (Admin Only).", Toast.LENGTH_SHORT).show()
                    }
                  },
                  modifier = Modifier.size(28.dp)
                ) {
                  Icon(
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = "Copy Formula",
                    tint = Color(0xFF94A3B8),
                    modifier = Modifier.size(15.dp)
                  )
                }
              }
            }

            // Expanded Details (Practical App & Exam Tip)
            AnimatedVisibility(visible = isExpanded) {
              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(top = 8.dp)
              ) {
                HorizontalDivider(color = Color(0xFF334155), modifier = Modifier.padding(vertical = 4.dp))

                // Practical Application
                Row(modifier = Modifier.padding(vertical = 2.dp)) {
                  Text("🛠️ ", fontSize = 11.sp)
                  Column {
                    Text(
                      text = "ප්‍රායෝගික භාවිතය:",
                      fontSize = 10.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color(0xFF10B981)
                    )
                    Text(
                      text = tool.practicalApplication,
                      fontSize = 11.sp,
                      color = Color(0xFFCBD5E1)
                    )
                  }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Exam Tip
                Row(modifier = Modifier.padding(vertical = 2.dp)) {
                  Text("🎯 ", fontSize = 11.sp)
                  Column {
                    Text(
                      text = "O/L විභාග රහස & ලකුණු ලබාගැනීමේ Tip:",
                      fontSize = 10.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color(0xFFF59E0B)
                    )
                    Text(
                      text = tool.examTip,
                      fontSize = 11.sp,
                      color = Color(0xFFFEF3C7)
                    )
                  }
                }

                // Fullscreen button inside expanded card
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                  onClick = { fullScreenTool = tool },
                  shape = RoundedCornerShape(8.dp),
                  colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB)),
                  modifier = Modifier.fillMaxWidth().height(36.dp)
                ) {
                  Icon(
                    imageVector = Icons.Default.Fullscreen,
                    contentDescription = "Full Screen",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                  )
                  Spacer(modifier = Modifier.width(6.dp))
                  Text(
                    text = "මෙවලම #${tool.toolNumber} ෆුල් ස්ක්‍රීන් බලන්න (Full Screen)",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                  )
                }
              }
            }
          }
        }
      }
    }

    // 7. Floating Exit-Fullscreen Button when in Fullscreen Mode
    if (effectiveFullScreen) {
      Surface(
        onClick = toggleFullScreenAction,
        shape = RoundedCornerShape(24.dp),
        color = Color(0xFF10B981),
        shadowElevation = 8.dp,
        modifier = Modifier
          .align(Alignment.BottomCenter)
          .padding(bottom = 20.dp)
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Default.FullscreenExit,
            contentDescription = "Exit Fullscreen",
            tint = Color.White,
            modifier = Modifier.size(18.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "⛶ සාමාන්‍ය තිරයට (Exit Fullscreen)",
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }
    }

    // 8. Dedicated Full-Screen Tool Reader Dialog
    if (fullScreenTool != null) {
      val tool = fullScreenTool!!
      val currentIndex = allToolsForSubject.indexOfFirst { it.id == tool.id }

      Dialog(
        onDismissRequest = { fullScreenTool = null },
        properties = DialogProperties(usePlatformDefaultWidth = false)
      ) {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = Color(0xFF0B132B)
        ) {
          Column(modifier = Modifier.fillMaxSize()) {
            // Full Screen Dialog Top Bar
            Surface(
              color = Color(0xFF0F172A),
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                IconButton(onClick = { fullScreenTool = null }) {
                  Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White
                  )
                }

                Column(
                  horizontalAlignment = Alignment.CenterHorizontally,
                  modifier = Modifier.weight(1f)
                ) {
                  Text(
                    text = "${currentSubjectCategory.subjectName} • මෙවලම #${tool.toolNumber}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF38BDF8)
                  )
                  Text(
                    text = "කාණ්ඩය ${tool.groupNumber}: ${OlSubjectToolsRepository.getGroupName(selectedSubject, tool.groupNumber)}",
                    fontSize = 11.sp,
                    color = Color(0xFF94A3B8),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                  )
                }

                IconButton(onClick = {
                  if (AppSecurityManager.canCopyContent(context)) {
                    clipboardManager.setText(AnnotatedString("${tool.titleSinhala}\n${tool.formulaOrRule}\n${tool.quickSummary}"))
                    Toast.makeText(context, "සම්පූර්ණ මෙවලම පිටපත් විය!", Toast.LENGTH_SHORT).show()
                  } else {
                    Toast.makeText(context, "ආරක්ෂක නීති අනුව සටහන් පිටපත් කිරීම (Copy) අවහිර කර ඇත (Admin Only).", Toast.LENGTH_SHORT).show()
                  }
                }) {
                  Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share / Copy",
                    tint = Color(0xFF38BDF8)
                  )
                }
              }
            }

            // Fullscreen Tool Content (Scrollable)
            LazyColumn(
              modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(horizontal = 16.dp),
              contentPadding = PaddingValues(vertical = 16.dp),
              verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
              item {
                // Title and badges
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Box(
                    modifier = Modifier
                      .size(44.dp)
                      .clip(CircleShape)
                      .background(Color(0xFF1E293B)),
                    contentAlignment = Alignment.Center
                  ) {
                    Text(
                      text = "#${tool.toolNumber}",
                      fontSize = 16.sp,
                      fontWeight = FontWeight.ExtraBold,
                      color = Color(0xFF38BDF8)
                    )
                  }
                  Spacer(modifier = Modifier.width(12.dp))
                  Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                      Text(tool.icon, fontSize = 18.sp)
                      Spacer(modifier = Modifier.width(6.dp))
                      Text(
                        text = tool.titleSinhala,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                      )
                    }
                    Text(
                      text = "${tool.titleEnglish} • [${tool.category}]",
                      fontSize = 12.sp,
                      color = Color(0xFF94A3B8)
                    )
                  }
                }
              }

              item {
                // Quick Summary Card
                Surface(
                  shape = RoundedCornerShape(12.dp),
                  color = Color(0xFF1E293B),
                  modifier = Modifier.fillMaxWidth()
                ) {
                  Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                      text = "📖 මූලික හැඳින්වීම & සාරාංශය:",
                      fontSize = 12.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color(0xFF38BDF8)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                      text = tool.quickSummary,
                      fontSize = 13.sp,
                      color = Color(0xFFE2E8F0),
                      lineHeight = 19.sp
                    )
                  }
                }
              }

              item {
                // Formula / Rule / Principle Big Highlight Box
                Surface(
                  shape = RoundedCornerShape(12.dp),
                  color = Color(0xFF0F172A),
                  border = BorderStroke(1.5.dp, Color(0xFF38BDF8)),
                  modifier = Modifier.fillMaxWidth()
                ) {
                  Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                      modifier = Modifier.fillMaxWidth(),
                      horizontalArrangement = Arrangement.SpaceBetween,
                      verticalAlignment = Alignment.CenterVertically
                    ) {
                      Text(
                        text = "⚡ සූත්‍රය / නීතිය / මූලධර්මය:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF38BDF8)
                      )
                      IconButton(
                        onClick = {
                          if (AppSecurityManager.canCopyContent(context)) {
                            clipboardManager.setText(AnnotatedString(tool.formulaOrRule))
                            Toast.makeText(context, "සූත්‍රය පිටපත් විය!", Toast.LENGTH_SHORT).show()
                          } else {
                            Toast.makeText(context, "ආරක්ෂක නීති අනුව සටහන් පිටපත් කිරීම (Copy) අවහිර කර ඇත (Admin Only).", Toast.LENGTH_SHORT).show()
                          }
                        },
                        modifier = Modifier.size(28.dp)
                      ) {
                        Icon(
                          imageVector = Icons.Default.ContentCopy,
                          contentDescription = "Copy",
                          tint = Color(0xFF38BDF8),
                          modifier = Modifier.size(16.dp)
                        )
                      }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                      text = tool.formulaOrRule,
                      fontSize = 15.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color.White,
                      lineHeight = 22.sp
                    )
                  }
                }
              }

              item {
                // Practical Application
                Surface(
                  shape = RoundedCornerShape(12.dp),
                  color = Color(0xFF064E3B).copy(alpha = 0.4f),
                  border = BorderStroke(1.dp, Color(0xFF10B981).copy(alpha = 0.5f)),
                  modifier = Modifier.fillMaxWidth()
                ) {
                  Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                      text = "🛠️ ප්‍රායෝගික භාවිතය & ගැටලු විසඳීම:",
                      fontSize = 12.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color(0xFF34D399)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                      text = tool.practicalApplication,
                      fontSize = 13.sp,
                      color = Color(0xFFD1FAE5),
                      lineHeight = 19.sp
                    )
                  }
                }
              }

              item {
                // Exam Tip & Secrets
                Surface(
                  shape = RoundedCornerShape(12.dp),
                  color = Color(0xFF78350F).copy(alpha = 0.4f),
                  border = BorderStroke(1.dp, Color(0xFFF59E0B).copy(alpha = 0.5f)),
                  modifier = Modifier.fillMaxWidth()
                ) {
                  Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                      text = "🎯 O/L විභාග රහස & ලකුණු ලබාගැනීමේ Tip:",
                      fontSize = 12.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color(0xFFFBBF24)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                      text = tool.examTip,
                      fontSize = 13.sp,
                      color = Color(0xFFFEF3C7),
                      lineHeight = 19.sp
                    )
                  }
                }
              }

              item {
                // Navigation Buttons (Previous / Next)
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween
                ) {
                  Button(
                    onClick = {
                      if (currentIndex > 0) {
                        fullScreenTool = allToolsForSubject[currentIndex - 1]
                      }
                    },
                    enabled = currentIndex > 0,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E293B)),
                    modifier = Modifier.weight(1f)
                  ) {
                    Icon(
                      imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                      contentDescription = "Previous",
                      tint = if (currentIndex > 0) Color.White else Color(0xFF64748B),
                      modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("පෙර මෙවලම", fontSize = 12.sp)
                  }

                  Spacer(modifier = Modifier.width(12.dp))

                  Button(
                    onClick = {
                      if (currentIndex < allToolsForSubject.size - 1) {
                        fullScreenTool = allToolsForSubject[currentIndex + 1]
                      }
                    },
                    enabled = currentIndex < allToolsForSubject.size - 1,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB)),
                    modifier = Modifier.weight(1f)
                  ) {
                    Text("ඊළඟ මෙවලම", fontSize = 12.sp, color = Color.White)
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                      imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                      contentDescription = "Next",
                      tint = Color.White,
                      modifier = Modifier.size(16.dp)
                    )
                  }
                }
              }
            }
          }
        }
      }
    }

    // 9. Dedicated Full-Screen Category Modal (Reading all 10 tools of a category)
    if (fullScreenCategoryGroup != null) {
      val groupNum = fullScreenCategoryGroup!!
      val groupTools = remember(selectedSubject, groupNum) {
        OlSubjectToolsRepository.getToolsForSubjectAndGroup(selectedSubject, groupNum)
      }
      val groupName = remember(selectedSubject, groupNum) {
        OlSubjectToolsRepository.getGroupName(selectedSubject, groupNum)
      }

      Dialog(
        onDismissRequest = { fullScreenCategoryGroup = null },
        properties = DialogProperties(usePlatformDefaultWidth = false)
      ) {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = Color(0xFF0B132B)
        ) {
          Column(modifier = Modifier.fillMaxSize()) {
            // Category Top Bar
            Surface(
              color = Color(0xFF0F172A),
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                IconButton(onClick = { fullScreenCategoryGroup = null }) {
                  Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White
                  )
                }

                Spacer(modifier = Modifier.width(6.dp))

                Column(modifier = Modifier.weight(1f)) {
                  Text(
                    text = "කාණ්ඩය $groupNum ෆුල් ස්ක්‍රීන් (මෙවලම් ${groupTools.size})",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                  )
                  Text(
                    text = "${currentSubjectCategory.subjectName} • $groupName",
                    fontSize = 11.sp,
                    color = Color(0xFF38BDF8),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                  )
                }

                Surface(
                  shape = RoundedCornerShape(8.dp),
                  color = Color(0xFF10B981)
                ) {
                  Text(
                    text = "G$groupNum",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                  )
                }
              }
            }

            // Scrollable List of the 10 tools in this category in full screen
            LazyColumn(
              modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
              contentPadding = PaddingValues(vertical = 12.dp),
              verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              items(groupTools, key = { it.id }) { tool ->
                Card(
                  shape = RoundedCornerShape(12.dp),
                  colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
                  border = BorderStroke(1.dp, Color(0xFF334155)),
                  modifier = Modifier.fillMaxWidth()
                ) {
                  Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                      verticalAlignment = Alignment.CenterVertically,
                      modifier = Modifier.fillMaxWidth()
                    ) {
                      Box(
                        modifier = Modifier
                          .size(32.dp)
                          .clip(CircleShape)
                          .background(Color(0xFF0F172A)),
                        contentAlignment = Alignment.Center
                      ) {
                        Text(
                          text = "#${tool.toolNumber}",
                          fontSize = 11.sp,
                          fontWeight = FontWeight.ExtraBold,
                          color = Color(0xFF38BDF8)
                        )
                      }
                      Spacer(modifier = Modifier.width(8.dp))
                      Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                          Text(tool.icon, fontSize = 14.sp)
                          Spacer(modifier = Modifier.width(4.dp))
                          Text(
                            text = tool.titleSinhala,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                          )
                        }
                        Text(
                          text = tool.titleEnglish,
                          fontSize = 10.sp,
                          color = Color(0xFF94A3B8)
                        )
                      }
                      IconButton(
                        onClick = {
                          fullScreenTool = tool
                        },
                        modifier = Modifier.size(30.dp)
                      ) {
                        Icon(
                          imageVector = Icons.Default.Fullscreen,
                          contentDescription = "Expand Tool",
                          tint = Color(0xFF38BDF8),
                          modifier = Modifier.size(18.dp)
                        )
                      }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                      text = tool.quickSummary,
                      fontSize = 11.sp,
                      color = Color(0xFFE2E8F0),
                      lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // Formula Box with Copy
                    Surface(
                      shape = RoundedCornerShape(8.dp),
                      color = Color(0xFF0F172A),
                      border = BorderStroke(1.dp, Color(0xFF38BDF8).copy(alpha = 0.3f)),
                      modifier = Modifier.fillMaxWidth()
                    ) {
                      Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                      ) {
                        Column(modifier = Modifier.weight(1f)) {
                          Text(
                            text = "⚡ සූත්‍රය / නීතිය:",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF38BDF8)
                          )
                          Text(
                            text = tool.formulaOrRule,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                          )
                        }
                        IconButton(
                          onClick = {
                            if (AppSecurityManager.canCopyContent(context)) {
                              clipboardManager.setText(AnnotatedString(tool.formulaOrRule))
                              Toast.makeText(context, "පිටපත් විය!", Toast.LENGTH_SHORT).show()
                            } else {
                              Toast.makeText(context, "ආරක්ෂක නීති අනුව සටහන් පිටපත් කිරීම (Copy) අවහිර කර ඇත (Admin Only).", Toast.LENGTH_SHORT).show()
                            }
                          },
                          modifier = Modifier.size(26.dp)
                        ) {
                          Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Copy",
                            tint = Color(0xFF94A3B8),
                            modifier = Modifier.size(14.dp)
                          )
                        }
                      }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Exam Tip
                    Row(modifier = Modifier.padding(vertical = 2.dp)) {
                      Text("🎯 ", fontSize = 10.sp)
                      Text(
                        text = "Tip: ${tool.examTip}",
                        fontSize = 10.sp,
                        color = Color(0xFFFEF3C7),
                        lineHeight = 14.sp
                      )
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}
