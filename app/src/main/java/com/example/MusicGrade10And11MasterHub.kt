package com.example

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy

/**
 * 🌟 100% ACCURATE GRADE 10 & 11 ORIENTAL MUSIC (පෙරදිග සංගීතය) MASTER HUB
 * Strictly exclusive to Grade 10 & 11 Music Subject
 */
@Composable
fun MusicGrade10And11MasterHubDialog(
  initialGradeTab: MusicGradeTab = MusicGradeTab.ALL,
  initialSectionCategory: MusicSectionCategory? = null,
  onDismiss: () -> Unit
) {
  var selectedGradeTab by remember { mutableStateOf(initialGradeTab) }
  var selectedCategory by remember { mutableStateOf(initialSectionCategory ?: MusicSectionCategory.RAGAS_AND_SWARAS) }
  var searchQuery by remember { mutableStateOf("") }

  // State for MCQ interactive test
  val userSelectedOptions = remember { mutableStateMapOf<Int, Int>() }
  var mcqScore by remember { mutableIntStateOf(0) }

  // Expanded items state
  var expandedRagaId by remember { mutableStateOf<String?>(null) }
  var expandedThalaId by remember { mutableStateOf<String?>(null) }
  var expandedFolkId by remember { mutableStateOf<String?>(null) }
  var expandedInstrumentId by remember { mutableStateOf<String?>(null) }
  var expandedMusicianId by remember { mutableStateOf<String?>(null) }
  var expandedPracticalId by remember { mutableStateOf<String?>(null) }
  var expandedEssayId by remember { mutableStateOf<String?>(null) }
  var expandedLessonId by remember { mutableStateOf<String?>(null) }

  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(
      usePlatformDefaultWidth = false,
      securePolicy = SecureFlagPolicy.Inherit
    )
  ) {
    BackHandler { onDismiss() }

    Surface(
      modifier = Modifier
        .fillMaxSize()
        .testTag("music_master_hub_dialog"),
      color = Color(0xFF140727)
    ) {
      Column(modifier = Modifier.fillMaxSize()) {
        // TOP APP BAR
        // TOP APP BAR: Ultra-compact Purple Theme (Minimizes screen height)
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .background(
              Brush.verticalGradient(
                colors = listOf(Color(0xFF7E22CE), Color(0xFF581C87))
              )
            )
            .statusBarsPadding()
            .padding(horizontal = 10.dp, vertical = 3.dp)
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .height(34.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            IconButton(
              onClick = onDismiss,
              modifier = Modifier
                .size(30.dp)
                .background(Color(0x33000000), CircleShape)
                .testTag("music_hub_back_btn")
            ) {
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
              )
            }

            Spacer(modifier = Modifier.width(6.dp))

            Column(modifier = Modifier.weight(1f)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                  text = "🎵 10 & 11 සංගීතය Master Hub",
                  color = Color.White,
                  fontSize = 13.5.sp,
                  fontWeight = FontWeight.Bold,
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(4.dp))
                Surface(
                  color = Color(0xFFC026D3),
                  shape = RoundedCornerShape(3.dp)
                ) {
                  Text(
                    text = "100% නිවැරදි",
                    color = Color.White,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                  )
                }
              }
              Text(
                text = "රාග 8 • තාල • ජන ගී • වාද්‍ය භාණ්ඩ • ප්‍රවීණයන් • MCQs",
                color = Color(0xFFE9D5FF),
                fontSize = 9.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
              )
            }

            // Close Button
            IconButton(
              onClick = onDismiss,
              modifier = Modifier.size(28.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
              )
            }
          }
        }

        // GRADE TABS (Ultra-compact)
        Surface(
          color = Color(0xFF220E3D),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 10.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            MusicGradeTab.entries.forEach { gradeTab ->
              val isSelected = selectedGradeTab == gradeTab
              Surface(
                shape = RoundedCornerShape(6.dp),
                color = if (isSelected) Color(0xFF9333EA) else Color(0xFF331657),
                border = BorderStroke(
                  1.dp,
                  if (isSelected) Color(0xFFD8B4FE) else Color(0xFF581C87)
                ),
                modifier = Modifier
                  .weight(1f)
                  .height(26.dp)
                  .clickable { selectedGradeTab = gradeTab }
                  .testTag("music_grade_tab_${gradeTab.name.lowercase()}")
              ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                  Text(
                    text = gradeTab.labelSinhala,
                    color = Color.White,
                    fontSize = 10.5.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                  )
                }
              }
            }
          }
        }

        // HORIZONTAL SCROLLING CATEGORY TABS (Ultra-compact)
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1B0B32))
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 8.dp, vertical = 3.dp),
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          MusicSectionCategory.entries.forEach { category ->
            val isSelected = selectedCategory == category
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = if (isSelected) category.themeColor else Color(0xFF2B124C),
              border = BorderStroke(
                1.dp,
                if (isSelected) Color.White.copy(alpha = 0.8f) else Color(0xFF4C1D95)
              ),
              modifier = Modifier
                .clickable { selectedCategory = category }
                .testTag("music_cat_${category.id}")
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(category.emoji, fontSize = 11.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = category.titleSinhala,
                  color = Color.White,
                  fontSize = 10.5.sp,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
              }
            }
          }
        }

        // SEARCH BAR (Ultra-compact)
        Surface(
          color = Color(0xFF180A2D),
          modifier = Modifier.fillMaxWidth()
        ) {
          OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = {
              Text(
                "සංගීතය සංකල්ප, රාග, තාල, ගීත, වාද්‍ය භාණ්ඩ සොයන්න...",
                fontSize = 11.sp,
                color = Color(0xFFA855F7).copy(alpha = 0.7f)
              )
            },
            leadingIcon = {
              Icon(
                Icons.Default.Search,
                contentDescription = null,
                tint = Color(0xFFC084FC),
                modifier = Modifier.size(15.dp)
              )
            },
            trailingIcon = {
              if (searchQuery.isNotBlank()) {
                IconButton(onClick = { searchQuery = "" }, modifier = Modifier.size(20.dp)) {
                  Icon(Icons.Default.Clear, contentDescription = "Clear", tint = Color.LightGray, modifier = Modifier.size(13.dp))
                }
              }
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
              focusedContainerColor = Color(0xFF261042),
              unfocusedContainerColor = Color(0xFF200C38),
              focusedBorderColor = Color(0xFFC084FC),
              unfocusedBorderColor = Color(0xFF581C87),
              focusedTextColor = Color.White,
              unfocusedTextColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
              .fillMaxWidth()
              .height(36.dp)
              .padding(horizontal = 10.dp, vertical = 2.dp)
          )
        }

        // CONTENT SECTION
        Box(
          modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .background(Color(0xFF120623))
        ) {
          when (selectedCategory) {
            MusicSectionCategory.RAGAS_AND_SWARAS -> {
              val filtered = remember(selectedGradeTab, searchQuery) {
                MusicMasterDataProvider.ragas
                  .filter { raga ->
                    when (selectedGradeTab) {
                      MusicGradeTab.ALL -> true
                      MusicGradeTab.GRADE_10 -> raga.grade == "10"
                      MusicGradeTab.GRADE_11 -> raga.grade == "11"
                    }
                  }
                  .filter { raga ->
                    searchQuery.isBlank() ||
                      raga.nameSinhala.contains(searchQuery, ignoreCase = true) ||
                      raga.thaatSinhala.contains(searchQuery, ignoreCase = true) ||
                      raga.arohana.contains(searchQuery, ignoreCase = true) ||
                      raga.avarohana.contains(searchQuery, ignoreCase = true) ||
                      raga.vadiSwara.contains(searchQuery, ignoreCase = true)
                  }
              }
              MusicRagasList(
                ragas = filtered,
                expandedId = expandedRagaId,
                onToggleExpand = { id -> expandedRagaId = if (expandedRagaId == id) null else id }
              )
            }

            MusicSectionCategory.THALAS_AND_LAYA -> {
              val filtered = remember(searchQuery) {
                MusicMasterDataProvider.thalas.filter { thala ->
                  searchQuery.isBlank() ||
                    thala.nameSinhala.contains(searchQuery, ignoreCase = true) ||
                    thala.thekaBols.contains(searchQuery, ignoreCase = true) ||
                    thala.usageAndInstruments.contains(searchQuery, ignoreCase = true)
                }
              }
              MusicThalasList(
                thalas = filtered,
                expandedId = expandedThalaId,
                onToggleExpand = { id -> expandedThalaId = if (expandedThalaId == id) null else id }
              )
            }

            MusicSectionCategory.FOLK_AND_THEATRE -> {
              val filtered = remember(searchQuery) {
                MusicMasterDataProvider.folkAndTheatre.filter { item ->
                  searchQuery.isBlank() ||
                    item.titleSinhala.contains(searchQuery, ignoreCase = true) ||
                    item.category.contains(searchQuery, ignoreCase = true) ||
                    item.sampleLyrics.contains(searchQuery, ignoreCase = true) ||
                    item.socialContext.contains(searchQuery, ignoreCase = true)
                }
              }
              MusicFolkTheatreList(
                items = filtered,
                expandedId = expandedFolkId,
                onToggleExpand = { id -> expandedFolkId = if (expandedFolkId == id) null else id }
              )
            }

            MusicSectionCategory.INSTRUMENTS_AND_VOICE -> {
              val filtered = remember(searchQuery) {
                MusicMasterDataProvider.instruments.filter { inst ->
                  searchQuery.isBlank() ||
                    inst.titleSinhala.contains(searchQuery, ignoreCase = true) ||
                    inst.category.contains(searchQuery, ignoreCase = true) ||
                    inst.constructionMaterials.contains(searchQuery, ignoreCase = true) ||
                    inst.playingTechnique.contains(searchQuery, ignoreCase = true)
                }
              }
              MusicInstrumentsList(
                instruments = filtered,
                expandedId = expandedInstrumentId,
                onToggleExpand = { id -> expandedInstrumentId = if (expandedInstrumentId == id) null else id }
              )
            }

            MusicSectionCategory.EMINENT_MUSICIANS -> {
              val filtered = remember(searchQuery) {
                MusicMasterDataProvider.musicians.filter { mus ->
                  searchQuery.isBlank() ||
                    mus.nameSinhala.contains(searchQuery, ignoreCase = true) ||
                    mus.titleOrRole.contains(searchQuery, ignoreCase = true) ||
                    mus.famousWorksOrBooks.contains(searchQuery, ignoreCase = true)
                }
              }
              MusicMusiciansList(
                musicians = filtered,
                expandedId = expandedMusicianId,
                onToggleExpand = { id -> expandedMusicianId = if (expandedMusicianId == id) null else id }
              )
            }

            MusicSectionCategory.PRACTICAL_AND_VIVA -> {
              val filtered = remember(searchQuery) {
                MusicMasterDataProvider.practicalVivaGuides.filter { viva ->
                  searchQuery.isBlank() ||
                    viva.titleSinhala.contains(searchQuery, ignoreCase = true) ||
                    viva.practicalCategory.contains(searchQuery, ignoreCase = true) ||
                    viva.examinerExpectations.contains(searchQuery, ignoreCase = true)
                }
              }
              MusicPracticalVivaList(
                items = filtered,
                expandedId = expandedPracticalId,
                onToggleExpand = { id -> expandedPracticalId = if (expandedPracticalId == id) null else id }
              )
            }

            MusicSectionCategory.EXAM_MCQ_AND_ESSAYS -> {
              val filteredMcqs = remember(selectedGradeTab, searchQuery) {
                MusicMasterDataProvider.mcqQuestions
                  .filter { q ->
                    when (selectedGradeTab) {
                      MusicGradeTab.ALL -> true
                      MusicGradeTab.GRADE_10 -> q.grade == "10"
                      MusicGradeTab.GRADE_11 -> q.grade == "11"
                    }
                  }
                  .filter { q ->
                    searchQuery.isBlank() ||
                      q.questionText.contains(searchQuery, ignoreCase = true) ||
                      q.categoryTag.contains(searchQuery, ignoreCase = true)
                  }
              }

              val filteredEssays = remember(selectedGradeTab, searchQuery) {
                MusicMasterDataProvider.modelEssays
                  .filter { e ->
                    when (selectedGradeTab) {
                      MusicGradeTab.ALL -> true
                      MusicGradeTab.GRADE_10 -> e.grade == "10"
                      MusicGradeTab.GRADE_11 -> e.grade == "11"
                    }
                  }
                  .filter { e ->
                    searchQuery.isBlank() ||
                      e.questionTitle.contains(searchQuery, ignoreCase = true) ||
                      e.scenarioOrStem.contains(searchQuery, ignoreCase = true)
                  }
              }

              MusicExamsList(
                mcqs = filteredMcqs,
                essays = filteredEssays,
                userSelectedOptions = userSelectedOptions,
                mcqScore = mcqScore,
                onOptionSelect = { qId, optIdx, isCorrect ->
                  userSelectedOptions[qId] = optIdx
                  if (isCorrect) mcqScore += 1
                },
                expandedEssayId = expandedEssayId,
                onToggleEssayExpand = { id -> expandedEssayId = if (expandedEssayId == id) null else id }
              )
            }

            MusicSectionCategory.LESSONS_AND_NOTES -> {
              val filtered = remember(selectedGradeTab, searchQuery) {
                MusicMasterDataProvider.lessonNotes
                  .filter { note ->
                    when (selectedGradeTab) {
                      MusicGradeTab.ALL -> true
                      MusicGradeTab.GRADE_10 -> note.grade == "10"
                      MusicGradeTab.GRADE_11 -> note.grade == "11"
                    }
                  }
                  .filter { note ->
                    searchQuery.isBlank() ||
                      note.unitTitle.contains(searchQuery, ignoreCase = true) ||
                      note.summaryText.contains(searchQuery, ignoreCase = true)
                  }
              }
              MusicLessonsList(
                lessons = filtered,
                expandedId = expandedLessonId,
                onToggleExpand = { id -> expandedLessonId = if (expandedLessonId == id) null else id }
              )
            }
            MusicSectionCategory.MUSIC_HISTORY_FEATURES -> {
              MusicMusicHistoryFeaturesList(activeGrade = selectedGradeTab.name.replace("GRADE_", ""))
            }
          }
        }
      }
    }
  }
}

/**
 * 🌟 EMBEDDED SHORT-NOTES CARD FOR 10 & 11 MUSIC (පෙරදිග සංගීතය)
 * Appears directly inside Tab 0 of Music Subject
 */
@Composable
fun MusicGrade10And11MasterHub(
  currentGrade: String = "11",
  onOpenFullHub: (MusicSectionCategory?) -> Unit
) {
  Card(
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E0A30)),
    border = BorderStroke(1.2.dp, Color(0xFFC026D3)),
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(14.dp))
      .clickable { onOpenFullHub(null) }
      .testTag("music_master_hub_embedded_card")
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text("🎵", fontSize = 18.sp)
          Spacer(modifier = Modifier.width(6.dp))
          Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "10 & 11 පෙරදිග සංගීතය Master Hub",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
              )
              Spacer(modifier = Modifier.width(6.dp))
              Surface(
                color = Color(0xFFC026D3),
                shape = RoundedCornerShape(4.dp)
              ) {
                Text(
                  text = "100% SYLLABUS",
                  color = Color.White,
                  fontSize = 8.sp,
                  fontWeight = FontWeight.ExtraBold,
                  modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.5.dp)
                )
              }
            }
            Text(
              text = "රාග 8 • තාල & ලයකාරි • ජන ගී • වාද්‍ය භාණ්ඩ • ප්‍රවීණයන් • Viva • O/L MCQs",
              color = Color(0xFFF3E8FF),
              fontSize = 10.sp
            )
          }
        }
        Surface(
          color = Color(0xFF581C87),
          shape = CircleShape,
          border = BorderStroke(1.dp, Color(0xFFC084FC))
        ) {
          Icon(
            imageVector = Icons.Default.OpenInFull,
            contentDescription = "Open Fullscreen",
            tint = Color(0xFFFDE047),
            modifier = Modifier.padding(6.dp).size(15.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Shortcut Buttons Row
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Button(
          onClick = { onOpenFullHub(MusicSectionCategory.RAGAS_AND_SWARAS) },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC026D3)),
          shape = RoundedCornerShape(8.dp),
          contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
          modifier = Modifier.height(28.dp)
        ) {
          Text("🎵 රාග 8", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Button(
          onClick = { onOpenFullHub(MusicSectionCategory.THALAS_AND_LAYA) },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0284C7)),
          shape = RoundedCornerShape(8.dp),
          contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
          modifier = Modifier.height(28.dp)
        ) {
          Text("🥁 තාල & ලයකාරි", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Button(
          onClick = { onOpenFullHub(MusicSectionCategory.FOLK_AND_THEATRE) },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF059669)),
          shape = RoundedCornerShape(8.dp),
          contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
          modifier = Modifier.height(28.dp)
        ) {
          Text("🌾 ජන ගී & නාට්‍ය", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Button(
          onClick = { onOpenFullHub(MusicSectionCategory.INSTRUMENTS_AND_VOICE) },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD97706)),
          shape = RoundedCornerShape(8.dp),
          contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
          modifier = Modifier.height(28.dp)
        ) {
          Text("🪕 වාද්‍ය භාණ්ඩ", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Button(
          onClick = { onOpenFullHub(MusicSectionCategory.EMINENT_MUSICIANS) },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7C3AED)),
          shape = RoundedCornerShape(8.dp),
          contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
          modifier = Modifier.height(28.dp)
        ) {
          Text("🎻 සංගීතඥයන්", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Button(
          onClick = { onOpenFullHub(MusicSectionCategory.PRACTICAL_AND_VIVA) },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE11D48)),
          shape = RoundedCornerShape(8.dp),
          contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
          modifier = Modifier.height(28.dp)
        ) {
          Text("🎙️ Viva & ප්‍රායෝගික", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Button(
          onClick = { onOpenFullHub(MusicSectionCategory.EXAM_MCQ_AND_ESSAYS) },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC2626)),
          shape = RoundedCornerShape(8.dp),
          contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
          modifier = Modifier.height(28.dp)
        ) {
          Text("🎯 O/L MCQs & රචනා", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Button(
          onClick = { onOpenFullHub(MusicSectionCategory.LESSONS_AND_NOTES) },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F46E5)),
          shape = RoundedCornerShape(8.dp),
          contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
          modifier = Modifier.height(28.dp)
        ) {
          Text("📚 ඒකක සටහන්", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
      }
    }
  }
}

// ============================================================================
// DEDICATED SECTION LIST COMPOSABLES
// ============================================================================

@Composable
fun MusicRagasList(
  ragas: List<RagaItem>,
  expandedId: String?,
  onToggleExpand: (String) -> Unit
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 50.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(ragas) { raga ->
      val isExpanded = expandedId == raga.id
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E0A30)),
        border = BorderStroke(1.dp, Color(0xFFC026D3)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                  text = raga.nameSinhala,
                  color = Color.White,
                  fontSize = 13.sp,
                  fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(6.dp))
                Surface(
                  color = Color(0xFF7E22CE),
                  shape = RoundedCornerShape(4.dp)
                ) {
                  Text(
                    text = "${raga.grade} ශ්‍රේණිය",
                    color = Color.White,
                    fontSize = 8.5.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                  )
                }
              }
              Spacer(modifier = Modifier.height(2.dp))
              Text(
                text = "${raga.thaatSinhala} • ජාතිය: ${raga.jatiSinhala}",
                fontSize = 10.sp,
                color = Color(0xFFE9D5FF)
              )
            }
            IconButton(
              onClick = { onToggleExpand(raga.id) },
              modifier = Modifier.size(28.dp)
            ) {
              Icon(
                imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = null,
                tint = Color(0xFFD8B4FE)
              )
            }
          }

          // Summary Swara line
          Spacer(modifier = Modifier.height(4.dp))
          Surface(
            color = Color(0xFF2E1065),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(6.dp)) {
              Text(
                text = "ආරෝහණ: ${raga.arohana}",
                fontSize = 10.sp,
                color = Color(0xFFFDE047),
                fontWeight = FontWeight.SemiBold
              )
              Text(
                text = "අවරෝහණ: ${raga.avarohana}",
                fontSize = 10.sp,
                color = Color(0xFF67E8F9),
                fontWeight = FontWeight.SemiBold
              )
            }
          }

          AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
          ) {
            Column(modifier = Modifier.padding(top = 8.dp)) {
              HorizontalDivider(color = Color(0xFF581C87))
              Spacer(modifier = Modifier.height(6.dp))

              Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                  Text("👑 වාදී ස්වරය: ${raga.vadiSwara}", fontSize = 10.sp, color = Color(0xFFFBBF24), fontWeight = FontWeight.Bold)
                  Text("🤝 සංවාදී ස්වරය: ${raga.samvadiSwara}", fontSize = 10.sp, color = Color(0xFFA7F3D0), fontWeight = FontWeight.Bold)
                }
                Column(modifier = Modifier.weight(1f)) {
                  Text("⏰ ගායන සමය: ${raga.gayanSamaya}", fontSize = 9.5.sp, color = Color(0xFFE9D5FF))
                  Text("🚫 වර්ජිත ස්වර: ${raga.varjithaSwaras}", fontSize = 9.5.sp, color = Color(0xFFFCA5A5))
                }
              }

              Spacer(modifier = Modifier.height(6.dp))
              Text("🎯 පකඩ (ස්වරූපය):", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE879F9))
              Text(raga.pakad, fontSize = 9.5.sp, color = Color.White, modifier = Modifier.padding(start = 4.dp))

              Spacer(modifier = Modifier.height(4.dp))
              Text("📖 විස්තරය & රසය:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
              Text(raga.descriptionAndRasa, fontSize = 9.5.sp, color = Color(0xFFE0E7FF), modifier = Modifier.padding(start = 4.dp))

              Spacer(modifier = Modifier.height(4.dp))
              Text("🎼 ආදර්ශ බණ්ඩිස් / ලක්ෂණ ගීතය:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF34D399))
              Text(raga.sampleBandisOrLakshan, fontSize = 9.5.sp, color = Color(0xFFA7F3D0), modifier = Modifier.padding(start = 4.dp))
            }
          }
        }
      }
    }
  }
}

@Composable
fun MusicThalasList(
  thalas: List<ThalaItem>,
  expandedId: String?,
  onToggleExpand: (String) -> Unit
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 50.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(thalas) { thala ->
      val isExpanded = expandedId == thala.id
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0C243B)),
        border = BorderStroke(1.dp, Color(0xFF0284C7)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = thala.nameSinhala,
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "මාත්‍රා ${thala.totalMatras} • විභාගය: ${thala.vibhagDivision}",
                fontSize = 10.sp,
                color = Color(0xFFBAE6FD)
              )
            }
            IconButton(onClick = { onToggleExpand(thala.id) }, modifier = Modifier.size(28.dp)) {
              Icon(
                imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = null,
                tint = Color(0xFF7DD3FC)
              )
            }
          }

          Spacer(modifier = Modifier.height(6.dp))
          Surface(
            color = Color(0xFF082F49),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(6.dp)) {
              Text("තාලි (අත්පුඩි): ${thala.thaliPositions} • ඛාලි (හිස්): ${thala.khaliPositions}", fontSize = 9.5.sp, color = Color(0xFFFDE047))
              Spacer(modifier = Modifier.height(2.dp))
              Text("තේකාව (Theka): ${thala.thekaBols}", fontSize = 10.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
          }

          AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.padding(top = 8.dp)) {
              HorizontalDivider(color = Color(0xFF0369A1))
              Spacer(modifier = Modifier.height(6.dp))

              Text("⚡ දුගුන් ලය (Dugun):", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
              Text(thala.dugunBols, fontSize = 9.sp, color = Color(0xFFE0F2FE), modifier = Modifier.padding(start = 4.dp))

              Spacer(modifier = Modifier.height(4.dp))
              Text("🚀 චෞගුන් ලය (Chaugun):", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFA78BFA))
              Text(thala.chaugunBols, fontSize = 9.sp, color = Color(0xFFF3E8FF), modifier = Modifier.padding(start = 4.dp))

              Spacer(modifier = Modifier.height(4.dp))
              Text("🥁 භාවිතය & වාද්‍ය භාණ්ඩ:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF34D399))
              Text(thala.usageAndInstruments, fontSize = 9.5.sp, color = Color(0xFFA7F3D0), modifier = Modifier.padding(start = 4.dp))
            }
          }
        }
      }
    }
  }
}

@Composable
fun MusicFolkTheatreList(
  items: List<FolkTheatreItem>,
  expandedId: String?,
  onToggleExpand: (String) -> Unit
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 50.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(items) { item ->
      val isExpanded = expandedId == item.id
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF06281D)),
        border = BorderStroke(1.dp, Color(0xFF059669)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(item.titleSinhala, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(6.dp))
                Surface(color = Color(0xFF047857), shape = RoundedCornerShape(4.dp)) {
                  Text(item.category, color = Color.White, fontSize = 8.5.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp))
                }
              }
              Spacer(modifier = Modifier.height(2.dp))
              Text(item.socialContext, fontSize = 10.sp, color = Color(0xFFA7F3D0))
            }
            IconButton(onClick = { onToggleExpand(item.id) }, modifier = Modifier.size(28.dp)) {
              Icon(if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null, tint = Color(0xFF6EE7B7))
            }
          }

          Spacer(modifier = Modifier.height(4.dp))
          Surface(color = Color(0xFF064E3B), shape = RoundedCornerShape(6.dp), modifier = Modifier.fillMaxWidth()) {
            Text("🎶 පද රචනය: \"${item.sampleLyrics}\"", fontSize = 9.5.sp, color = Color(0xFFFDE047), modifier = Modifier.padding(6.dp), fontWeight = FontWeight.SemiBold)
          }

          AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.padding(top = 8.dp)) {
              HorizontalDivider(color = Color(0xFF047857))
              Spacer(modifier = Modifier.height(6.dp))

              Text("🎼 සංගීතමය ලක්ෂණ:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF34D399))
              item.musicalCharacteristics.forEach { charact ->
                Text("• $charact", fontSize = 9.5.sp, color = Color(0xFFD1FAE5), modifier = Modifier.padding(start = 4.dp, top = 1.dp))
              }

              Spacer(modifier = Modifier.height(6.dp))
              Text("📜 ඓතිහාසික වැදගත්කම:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFBBF24))
              Text(item.historicalSignificance, fontSize = 9.5.sp, color = Color(0xFFFEF3C7), modifier = Modifier.padding(start = 4.dp))
            }
          }
        }
      }
    }
  }
}

@Composable
fun MusicInstrumentsList(
  instruments: List<MusicInstrumentItem>,
  expandedId: String?,
  onToggleExpand: (String) -> Unit
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 50.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(instruments) { inst ->
      val isExpanded = expandedId == inst.id
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2E1905)),
        border = BorderStroke(1.dp, Color(0xFFD97706)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(inst.titleSinhala, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(6.dp))
                Surface(color = Color(0xFFB45309), shape = RoundedCornerShape(4.dp)) {
                  Text(inst.category, color = Color.White, fontSize = 8.5.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp))
                }
              }
              Spacer(modifier = Modifier.height(2.dp))
              Text(inst.constructionMaterials, fontSize = 10.sp, color = Color(0xFFFDE68A))
            }
            IconButton(onClick = { onToggleExpand(inst.id) }, modifier = Modifier.size(28.dp)) {
              Icon(if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null, tint = Color(0xFFFCD34D))
            }
          }

          AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.padding(top = 8.dp)) {
              HorizontalDivider(color = Color(0xFF92400E))
              Spacer(modifier = Modifier.height(6.dp))

              Text("🔧 කොටස් & සුසර කිරීම (Tuning):", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE047))
              inst.partsAndTuning.forEach { pt ->
                Text("• $pt", fontSize = 9.5.sp, color = Color(0xFFFEF3C7), modifier = Modifier.padding(start = 4.dp, top = 1.dp))
              }

              Spacer(modifier = Modifier.height(6.dp))
              Text("🖐️ වාදන ශිල්ප ක්‍රමය:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
              Text(inst.playingTechnique, fontSize = 9.5.sp, color = Color(0xFFE0F2FE), modifier = Modifier.padding(start = 4.dp))

              Spacer(modifier = Modifier.height(4.dp))
              Text("🎭 කාර්යභාරය (ප්‍රසංගයෙහි):", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF34D399))
              Text(inst.roleInPerformance, fontSize = 9.5.sp, color = Color(0xFFA7F3D0), modifier = Modifier.padding(start = 4.dp))
            }
          }
        }
      }
    }
  }
}

@Composable
fun MusicMusiciansList(
  musicians: List<MusicMusicianItem>,
  expandedId: String?,
  onToggleExpand: (String) -> Unit
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 50.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(musicians) { mus ->
      val isExpanded = expandedId == mus.id
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1F0C3B)),
        border = BorderStroke(1.dp, Color(0xFF7C3AED)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(mus.nameSinhala, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(6.dp))
                Surface(color = Color(0xFF6D28D9), shape = RoundedCornerShape(4.dp)) {
                  Text(mus.eraOrTradition, color = Color.White, fontSize = 8.5.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp))
                }
              }
              Spacer(modifier = Modifier.height(2.dp))
              Text(mus.titleOrRole, fontSize = 10.sp, color = Color(0xFFDDD6FE))
            }
            IconButton(onClick = { onToggleExpand(mus.id) }, modifier = Modifier.size(28.dp)) {
              Icon(if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null, tint = Color(0xFFC4B5FD))
            }
          }

          AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.padding(top = 8.dp)) {
              HorizontalDivider(color = Color(0xFF5B21B6))
              Spacer(modifier = Modifier.height(6.dp))

              Text("🌟 ප්‍රධාන දායකත්වයන්:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE047))
              mus.majorContributions.forEach { c ->
                Text("• $c", fontSize = 9.5.sp, color = Color(0xFFEDE9FE), modifier = Modifier.padding(start = 4.dp, top = 1.dp))
              }

              Spacer(modifier = Modifier.height(6.dp))
              Text("📚 ප්‍රකට කෘති / ග්‍රන්ථ:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
              Text(mus.famousWorksOrBooks, fontSize = 9.5.sp, color = Color(0xFFE0F2FE), modifier = Modifier.padding(start = 4.dp))

              Spacer(modifier = Modifier.height(4.dp))
              Text("🏛️ ඓතිහාසික බලපෑම:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF34D399))
              Text(mus.historicalImpact, fontSize = 9.5.sp, color = Color(0xFFA7F3D0), modifier = Modifier.padding(start = 4.dp))
            }
          }
        }
      }
    }
  }
}

@Composable
fun MusicPracticalVivaList(
  items: List<MusicPracticalVivaItem>,
  expandedId: String?,
  onToggleExpand: (String) -> Unit
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 50.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(items) { viva ->
      val isExpanded = expandedId == viva.id
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF330919)),
        border = BorderStroke(1.dp, Color(0xFFE11D48)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(viva.titleSinhala, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(6.dp))
                Surface(color = Color(0xFFBE123C), shape = RoundedCornerShape(4.dp)) {
                  Text(viva.practicalCategory, color = Color.White, fontSize = 8.5.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp))
                }
              }
              Spacer(modifier = Modifier.height(2.dp))
              Text("ලකුණු ප්‍රමාණය: ${viva.marksWeightage}", fontSize = 10.sp, color = Color(0xFFFDA4AF), fontWeight = FontWeight.SemiBold)
            }
            IconButton(onClick = { onToggleExpand(viva.id) }, modifier = Modifier.size(28.dp)) {
              Icon(if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null, tint = Color(0xFFFB7185))
            }
          }

          AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.padding(top = 8.dp)) {
              HorizontalDivider(color = Color(0xFF9F1239))
              Spacer(modifier = Modifier.height(6.dp))

              Text("🔑 ප්‍රධාන ශිල්පීය ක්‍රම & උපදෙස්:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE047))
              viva.keyTechniques.forEach { t ->
                Text("• $t", fontSize = 9.5.sp, color = Color(0xFFFFE4E6), modifier = Modifier.padding(start = 4.dp, top = 1.dp))
              }

              Spacer(modifier = Modifier.height(6.dp))
              Text("👨‍🏫 පරීක්ෂක අපේක්ෂාවන් (Examiner Expectations):", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
              Text(viva.examinerExpectations, fontSize = 9.5.sp, color = Color(0xFFE0F2FE), modifier = Modifier.padding(start = 4.dp))

              Spacer(modifier = Modifier.height(4.dp))
              Text("⚠️ සිසුන් නිතර කරන වැරදි (වළක්වා ගන්න):", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFCA5A5))
              Text(viva.commonMistakesToAvoid, fontSize = 9.5.sp, color = Color(0xFFFECDD3), modifier = Modifier.padding(start = 4.dp))

              if (viva.sampleQuestionAndAnswer != null) {
                Spacer(modifier = Modifier.height(6.dp))
                Surface(color = Color(0xFF4C0519), shape = RoundedCornerShape(6.dp), modifier = Modifier.fillMaxWidth()) {
                  Column(modifier = Modifier.padding(8.dp)) {
                    Text("💡 ආදර්ශ Viva ප්‍රශ්නය & පිළිතුර:", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFF34D399))
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(viva.sampleQuestionAndAnswer, fontSize = 9.sp, color = Color(0xFFA7F3D0))
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

@Composable
fun MusicExamsList(
  mcqs: List<MusicMcqQuestion>,
  essays: List<MusicModelEssay>,
  userSelectedOptions: Map<Int, Int>,
  mcqScore: Int,
  onOptionSelect: (Int, Int, Boolean) -> Unit,
  expandedEssayId: String?,
  onToggleEssayExpand: (String) -> Unit
) {
  var activeExamSubTab by remember { mutableIntStateOf(0) } // 0: MCQs, 1: Essays

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 4.dp, bottom = 50.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    // SUB-TABS (Scrolls together as single view)
    item(key = "music_exam_sub_tabs") {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .background(Color(0xFF260D1E), RoundedCornerShape(8.dp))
          .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Button(
          onClick = { activeExamSubTab = 0 },
          colors = ButtonDefaults.buttonColors(
            containerColor = if (activeExamSubTab == 0) Color(0xFFDC2626) else Color(0xFF450A0A)
          ),
          shape = RoundedCornerShape(8.dp),
          modifier = Modifier.weight(1f).height(30.dp),
          contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp)
        ) {
          Text("🎯 O/L MCQs (${mcqs.size})", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Button(
          onClick = { activeExamSubTab = 1 },
          colors = ButtonDefaults.buttonColors(
            containerColor = if (activeExamSubTab == 1) Color(0xFFDC2626) else Color(0xFF450A0A)
          ),
          shape = RoundedCornerShape(8.dp),
          modifier = Modifier.weight(1f).height(30.dp),
          contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp)
        ) {
          Text("📝 ආදර්ශ රචනා (${essays.size})", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
      }
    }

    if (activeExamSubTab == 0) {
      // Score header (Scrolls together)
      item(key = "music_exam_score_header") {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1E0813), RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text("පිළිතුරු සැපයූ ප්‍රමාණය: ${userSelectedOptions.size} / ${mcqs.size}", fontSize = 10.5.sp, color = Color(0xFFFECDD3))
          Surface(color = Color(0xFFDC2626), shape = RoundedCornerShape(4.dp)) {
            Text("ලකුණු: $mcqScore", color = Color.White, fontSize = 10.5.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
          }
        }
      }
        items(mcqs) { q ->
          val selectedOption = userSelectedOptions[q.id]
          val isAnswered = selectedOption != null

          Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF220914)),
            border = BorderStroke(1.dp, Color(0xFFE11D48)),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(10.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Surface(color = Color(0xFF9F1239), shape = RoundedCornerShape(4.dp)) {
                  Text("ප්‍රශ්නය ${q.id} • ${q.grade} ශ්‍රේණිය", fontSize = 8.5.sp, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.5.dp))
                }
                Text(q.categoryTag, fontSize = 9.sp, color = Color(0xFFFDA4AF))
              }

              Spacer(modifier = Modifier.height(4.dp))
              Text(q.questionText, color = Color.White, fontSize = 11.5.sp, fontWeight = FontWeight.SemiBold, lineHeight = 16.sp)

              Spacer(modifier = Modifier.height(6.dp))

              // Options
              q.options.forEachIndexed { optIndex, optionText ->
                val isThisSelected = selectedOption == optIndex
                val isCorrectAnswer = optIndex == q.correctOptionIndex

                val optBgColor = when {
                  !isAnswered -> Color(0xFF330E1F)
                  isThisSelected && isCorrectAnswer -> Color(0xFF065F46)
                  isThisSelected && !isCorrectAnswer -> Color(0xFF991B1B)
                  isCorrectAnswer -> Color(0xFF065F46).copy(alpha = 0.6f)
                  else -> Color(0xFF330E1F).copy(alpha = 0.5f)
                }

                Surface(
                  shape = RoundedCornerShape(6.dp),
                  color = optBgColor,
                  border = BorderStroke(
                    1.dp,
                    if (isThisSelected) Color.White else Color(0xFF881337)
                  ),
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.5.dp)
                    .clickable(enabled = !isAnswered) {
                      onOptionSelect(q.id, optIndex, optIndex == q.correctOptionIndex)
                    }
                ) {
                  Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                  ) {
                    Text(
                      text = "(${optIndex + 1})",
                      fontSize = 10.sp,
                      fontWeight = FontWeight.Bold,
                      color = if (isThisSelected) Color.White else Color(0xFFFDA4AF)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                      text = optionText,
                      fontSize = 10.5.sp,
                      color = Color.White,
                      modifier = Modifier.weight(1f)
                    )
                    if (isAnswered) {
                      if (isCorrectAnswer) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF34D399), modifier = Modifier.size(16.dp))
                      } else if (isThisSelected) {
                        Icon(Icons.Default.Cancel, contentDescription = null, tint = Color(0xFFF87171), modifier = Modifier.size(16.dp))
                      }
                    }
                  }
                }
              }

              if (isAnswered) {
                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                  color = Color(0xFF3B071B),
                  shape = RoundedCornerShape(6.dp),
                  modifier = Modifier.fillMaxWidth()
                ) {
                  Column(modifier = Modifier.padding(6.dp)) {
                    Text("💡 විවරණය:", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE047))
                    Text(q.explanation, fontSize = 9.sp, color = Color(0xFFFECDD3))
                  }
                }
              }
            }
          }
        }
    } else {
      // Model Essays List (Scrolls together as single view)
      items(essays) { essay ->
        val isExpanded = expandedEssayId == essay.id

          Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF240A1A)),
            border = BorderStroke(1.dp, Color(0xFFEF4444)),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(10.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Column(modifier = Modifier.weight(1f)) {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(color = Color(0xFFB91C1C), shape = RoundedCornerShape(4.dp)) {
                      Text("${essay.grade} ශ්‍රේණිය", fontSize = 8.5.sp, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp))
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(essay.unitName, fontSize = 9.5.sp, color = Color(0xFFFECDD3))
                  }
                  Spacer(modifier = Modifier.height(2.dp))
                  Text(essay.questionTitle, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
                IconButton(onClick = { onToggleEssayExpand(essay.id) }, modifier = Modifier.size(26.dp)) {
                  Icon(if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null, tint = Color(0xFFFCA5A5))
                }
              }

              Spacer(modifier = Modifier.height(4.dp))
              Text(essay.scenarioOrStem, fontSize = 10.sp, color = Color(0xFFE2E8F0), lineHeight = 14.sp)

              AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(top = 8.dp)) {
                  HorizontalDivider(color = Color(0xFF7F1D1D))
                  Spacer(modifier = Modifier.height(6.dp))

                  essay.subQuestions.forEach { sq ->
                    Surface(
                      color = Color(0xFF380718),
                      shape = RoundedCornerShape(6.dp),
                      modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)
                    ) {
                      Column(modifier = Modifier.padding(6.dp)) {
                        Row(
                          modifier = Modifier.fillMaxWidth(),
                          horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                          Text("${sq.numberText} ${sq.questionText}", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE047), modifier = Modifier.weight(1f))
                          Surface(color = Color(0xFFDC2626), shape = RoundedCornerShape(3.dp)) {
                            Text("${sq.marks} marks", fontSize = 8.5.sp, color = Color.White, modifier = Modifier.padding(horizontal = 3.dp, vertical = 1.dp))
                          }
                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        Text("📋 නිල ලකුණු පටිපාටිය (Marking Scheme):", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF34D399))
                        sq.markingSchemePoints.forEach { pt ->
                          Text("• $pt", fontSize = 9.sp, color = Color(0xFFA7F3D0), modifier = Modifier.padding(start = 4.dp, top = 1.dp))
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
}

@Composable
fun MusicLessonsList(
  lessons: List<MusicLessonNote>,
  expandedId: String?,
  onToggleExpand: (String) -> Unit
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 50.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(lessons) { lesson ->
      val isExpanded = expandedId == lesson.id
      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1038)),
        border = BorderStroke(1.dp, Color(0xFF4F46E5)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(10.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(
                "${lesson.grade} ශ්‍රේණිය - ඒකකය ${lesson.unitNumber}: ${lesson.unitTitle}",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
              )
            }
            IconButton(onClick = { onToggleExpand(lesson.id) }, modifier = Modifier.size(24.dp)) {
              Icon(if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null, tint = Color(0xFF818CF8))
            }
          }

          Text(lesson.summaryText, fontSize = 10.sp, color = Color(0xFFE0E7FF), lineHeight = 14.sp)

          AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.padding(top = 6.dp)) {
              HorizontalDivider(color = Color(0xFF312E81))
              Spacer(modifier = Modifier.height(4.dp))

              Text("📌 ප්‍රධාන සංකල්ප:", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE047))
              lesson.coreConcepts.forEach { conc ->
                Text("• $conc", fontSize = 9.5.sp, color = Color(0xFFC7D2FE), modifier = Modifier.padding(start = 4.dp, top = 1.dp))
              }

              Spacer(modifier = Modifier.height(4.dp))
              Text("🎯 විභාග ඉලක්කගත කරුණු:", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFF34D399))
              lesson.examFocusPoints.forEach { pt ->
                Text("• $pt", fontSize = 9.sp, color = Color(0xFFA7F3D0), modifier = Modifier.padding(start = 4.dp, top = 1.dp))
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun MusicMusicHistoryFeaturesList(activeGrade: String) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    item {
      MusicHistoryFeatureCard(
        title = "ඓතිහාසික ශ්‍රවණ අභ්‍යාස",
        description = "ඉතිහාසයේ පැරණි වාද්‍ය භාණ්ඩ සහ සංගීත ශෛලීන් පිළිබඳ ශ්‍රවණ අභ්‍යාස මෙහි ඇතුළත් වේ.",
        emoji = "🎧"
      )
    }
    item {
      MusicHistoryFeatureCard(
        title = "ස්වර ලිපි පුහුණුව",
        description = "ඓතිහාසික සංගීත ස්වර ලිපි කියවීම සහ ලිවීම පිළිබඳ පුහුණුව.",
        emoji = "🎼"
      )
    }
    item {
      MusicHistoryFeatureCard(
        title = "ප්‍රසංග ඇගයුම්",
        description = "ප්‍රසංගයකදී අවධානය යොමු කළ යුතු කරුණු පිරික්සුම.",
        emoji = "✅"
      )
    }
    item {
      MusicHistoryFeatureCard(
        title = "සංගීත ඉතිහාස පරිණාමය",
        description = "සංගීතයේ ආදිකාලීන මූලයන් සහ විකාශනය පිළිබඳ පාඩම්.",
        emoji = "📜"
      )
    }
  }
}

@Composable
fun MusicHistoryFeatureCard(title: String, description: String, emoji: String) {
  Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E0A30)),
    border = BorderStroke(1.dp, Color(0xFF6D28D9)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(text = "$emoji $title", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
      Spacer(modifier = Modifier.height(8.dp))
      Text(text = description, fontSize = 14.sp, color = Color.Gray)
    }
  }
}
