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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy

/**
 * 🌟 100% ACCURATE GRADE 10 & 11 DANCING (නර්තනය) MASTER HUB
 * Exclusive to Grade 10 & 11 Dancing Subject
 */
@Composable
fun DancingGrade10And11MasterHubDialog(
  initialGradeTab: DancingGradeTab = DancingGradeTab.ALL,
  initialSectionCategory: DancingSectionCategory? = null,
  onDismiss: () -> Unit
) {
  var selectedGradeTab by remember { mutableStateOf(initialGradeTab) }
  var selectedCategory by remember { mutableStateOf(initialSectionCategory ?: DancingSectionCategory.TRADITIONS_AND_DRUMS) }
  var searchQuery by remember { mutableStateOf("") }

  // State for MCQ interactive test
  val userSelectedOptions = remember { mutableStateMapOf<Int, Int>() }
  var mcqScore by remember { mutableIntStateOf(0) }

  // Expanded items state
  var expandedTraditionId by remember { mutableStateOf<String?>(null) }
  var expandedVannamNumber by remember { mutableStateOf<Int?>(null) }
  var expandedAbhinayaId by remember { mutableStateOf<String?>(null) }
  var expandedRasaNumber by remember { mutableStateOf<Int?>(null) }
  var expandedMudraId by remember { mutableStateOf<String?>(null) }
  var expandedInstrumentId by remember { mutableStateOf<String?>(null) }
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
        .testTag("dancing_master_hub_dialog"),
      color = Color(0xFF1E0A12)
    ) {
      Column(modifier = Modifier.fillMaxSize()) {
        // TOP APP BAR
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .background(
              Brush.verticalGradient(
                colors = listOf(Color(0xFF9F1239), Color(0xFF881337))
              )
            )
            .statusBarsPadding()
            .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
          ) {
            IconButton(
              onClick = onDismiss,
              modifier = Modifier
                .size(36.dp)
                .background(Color.White.copy(alpha = 0.2f), CircleShape)
                .testTag("dancing_hub_back_btn")
            ) {
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
              )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text("💃", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                  text = "10 & 11 නර්තනය Master Hub",
                  color = Color.White,
                  fontSize = 15.sp,
                  fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(6.dp))
                Surface(
                  color = Color(0xFFFDA4AF),
                  shape = RoundedCornerShape(4.dp)
                ) {
                  Text(
                    text = "100% O/L SYLLABUS",
                    color = Color(0xFF881337),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                  )
                }
              }
              Text(
                text = "ත්‍රිවිධ සම්ප්‍රදාය • වන්නම් 18 • අභිනය • මුද්‍රා • බෙර වාදන • Viva • MCQs",
                color = Color(0xFFFFE4E6),
                fontSize = 10.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
              )
            }
          }
        }

        // GRADE TABS SELECTOR (10 & 11)
        Surface(
          color = Color(0xFF2A0815),
          border = BorderStroke(1.dp, Color(0xFF4C0519)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 8.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            DancingGradeTab.values().forEach { tab ->
              val isSelected = selectedGradeTab == tab
              Surface(
                onClick = { selectedGradeTab = tab },
                shape = RoundedCornerShape(8.dp),
                color = if (isSelected) Color(0xFFE11D48) else Color(0xFF3F0B1E),
                border = BorderStroke(
                  1.dp,
                  if (isSelected) Color(0xFFFB7185) else Color(0xFF500724)
                ),
                modifier = Modifier.weight(1f)
              ) {
                Column(
                  modifier = Modifier.padding(vertical = 6.dp, horizontal = 4.dp),
                  horizontalAlignment = Alignment.CenterHorizontally
                ) {
                  Text(
                    text = tab.labelSinhala,
                    color = if (isSelected) Color.White else Color(0xFFFECDD3),
                    fontSize = 11.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                  )
                  Text(
                    text = tab.badge,
                    color = if (isSelected) Color(0xFFFFE4E6) else Color(0xFFFDA4AF).copy(alpha = 0.7f),
                    fontSize = 8.5.sp
                  )
                }
              }
            }
          }
        }

        // SECTION CATEGORIES HORIZONTAL SCROLL
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1E0A12))
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 8.dp, vertical = 6.dp),
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          DancingSectionCategory.values().forEach { cat ->
            val isSelected = selectedCategory == cat
            Surface(
              onClick = { selectedCategory = cat },
              shape = RoundedCornerShape(10.dp),
              color = if (isSelected) cat.themeColor else Color(0xFF2D0E1C),
              border = BorderStroke(
                1.2.dp,
                if (isSelected) Color.White.copy(alpha = 0.6f) else cat.themeColor.copy(alpha = 0.4f)
              ),
              modifier = Modifier.height(34.dp)
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(cat.emoji, fontSize = 13.sp)
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                  text = cat.titleSinhala,
                  color = if (isSelected) Color.White else Color(0xFFFECDD3),
                  fontSize = 10.5.sp,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                )
              }
            }
          }
        }

        // SEARCH BAR
        Surface(
          color = Color(0xFF250A17),
          modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 4.dp),
          shape = RoundedCornerShape(8.dp),
          border = BorderStroke(1.dp, Color(0xFF4C0519))
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(Icons.Default.Search, contentDescription = "Search", tint = Color(0xFFFDA4AF), modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            TextField(
              value = searchQuery,
              onValueChange = { searchQuery = it },
              placeholder = { Text("නර්තන තේමා, වන්නම්, බෙර, මුද්‍රා සොයන්න...", fontSize = 11.sp, color = Color(0xFF9F1239)) },
              colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
              ),
              singleLine = true,
              modifier = Modifier.weight(1f).height(40.dp)
            )
            if (searchQuery.isNotBlank()) {
              IconButton(onClick = { searchQuery = "" }, modifier = Modifier.size(24.dp)) {
                Icon(Icons.Default.Close, contentDescription = "Clear", tint = Color.White, modifier = Modifier.size(14.dp))
              }
            }
          }
        }

        // CONTENT SECTION LIST
        Box(
          modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .background(Color(0xFF14050C))
        ) {
          when (selectedCategory) {
            DancingSectionCategory.TRADITIONS_AND_DRUMS -> {
              DancingTraditionsList(
                traditions = DancingMasterDataProvider.traditions.filter {
                  searchQuery.isBlank() ||
                  it.nameSinhala.contains(searchQuery, ignoreCase = true) ||
                  it.primaryDrum.contains(searchQuery, ignoreCase = true) ||
                  it.sacredShanthikarma.contains(searchQuery, ignoreCase = true)
                },
                expandedId = expandedTraditionId,
                onToggleExpand = { expandedTraditionId = if (expandedTraditionId == it) null else it }
              )
            }

            DancingSectionCategory.VANNAM_18 -> {
              DancingVannamsList(
                vannams = DancingMasterDataProvider.vannams.filter {
                  searchQuery.isBlank() ||
                  it.nameSinhala.contains(searchQuery, ignoreCase = true) ||
                  it.animalOrTheme.contains(searchQuery, ignoreCase = true) ||
                  it.poemSinhala.contains(searchQuery, ignoreCase = true)
                },
                expandedNumber = expandedVannamNumber,
                onToggleExpand = { expandedVannamNumber = if (expandedVannamNumber == it) null else it }
              )
            }

            DancingSectionCategory.ABHINAYA_AND_MUDRAS -> {
              DancingAbhinayaAndMudrasList(
                abhinayas = DancingMasterDataProvider.abhinayas,
                rasas = DancingMasterDataProvider.rasas,
                mudras = DancingMasterDataProvider.mudras.filter {
                  searchQuery.isBlank() ||
                  it.nameSinhala.contains(searchQuery, ignoreCase = true) ||
                  it.type.contains(searchQuery, ignoreCase = true)
                },
                expandedAbhinayaId = expandedAbhinayaId,
                onToggleAbhinaya = { expandedAbhinayaId = if (expandedAbhinayaId == it) null else it },
                expandedRasaNumber = expandedRasaNumber,
                onToggleRasa = { expandedRasaNumber = if (expandedRasaNumber == it) null else it },
                expandedMudraId = expandedMudraId,
                onToggleMudra = { expandedMudraId = if (expandedMudraId == it) null else it }
              )
            }

            DancingSectionCategory.INSTRUMENTS_AND_COSTUMES -> {
              DancingInstrumentsList(
                items = DancingMasterDataProvider.instrumentsAndCostumes.filter {
                  searchQuery.isBlank() ||
                  it.titleSinhala.contains(searchQuery, ignoreCase = true) ||
                  it.category.contains(searchQuery, ignoreCase = true) ||
                  it.classification.contains(searchQuery, ignoreCase = true)
                },
                expandedId = expandedInstrumentId,
                onToggleExpand = { expandedInstrumentId = if (expandedInstrumentId == it) null else it }
              )
            }

            DancingSectionCategory.PRACTICAL_AND_VIVA -> {
              DancingPracticalVivaList(
                items = DancingMasterDataProvider.practicalVivaGuides.filter {
                  searchQuery.isBlank() ||
                  it.titleSinhala.contains(searchQuery, ignoreCase = true) ||
                  it.category.contains(searchQuery, ignoreCase = true)
                },
                expandedId = expandedPracticalId,
                onToggleExpand = { expandedPracticalId = if (expandedPracticalId == it) null else it }
              )
            }

            DancingSectionCategory.EXAM_MCQ_AND_ESSAYS -> {
              DancingExamsList(
                mcqList = DancingMasterDataProvider.mcqQuestions.filter {
                  val matchesGrade = when (selectedGradeTab) {
                    DancingGradeTab.ALL -> true
                    DancingGradeTab.GRADE_10 -> it.grade == "10"
                    DancingGradeTab.GRADE_11 -> it.grade == "11"
                  }
                  matchesGrade && (searchQuery.isBlank() ||
                  it.questionText.contains(searchQuery, ignoreCase = true) ||
                  it.categoryTag.contains(searchQuery, ignoreCase = true))
                },
                essays = DancingMasterDataProvider.modelEssays.filter {
                  searchQuery.isBlank() ||
                  it.questionTitle.contains(searchQuery, ignoreCase = true) ||
                  it.unitName.contains(searchQuery, ignoreCase = true)
                },
                userSelectedOptions = userSelectedOptions,
                onSelectOption = { qId, optIndex, isCorrect ->
                  userSelectedOptions[qId] = optIndex
                  if (isCorrect) mcqScore++
                },
                mcqScore = mcqScore,
                expandedEssayId = expandedEssayId,
                onToggleEssay = { expandedEssayId = if (expandedEssayId == it) null else it }
              )
            }

            DancingSectionCategory.LESSONS_AND_NOTES -> {
              DancingLessonsList(
                lessons = DancingMasterDataProvider.lessonNotes.filter {
                  val matchesGrade = when (selectedGradeTab) {
                    DancingGradeTab.ALL -> true
                    DancingGradeTab.GRADE_10 -> it.grade == "10"
                    DancingGradeTab.GRADE_11 -> it.grade == "11"
                  }
                  matchesGrade && (searchQuery.isBlank() ||
                  it.unitTitle.contains(searchQuery, ignoreCase = true) ||
                  it.summaryText.contains(searchQuery, ignoreCase = true))
                },
                expandedId = expandedLessonId,
                onToggleExpand = { expandedLessonId = if (expandedLessonId == it) null else it }
              )
            }
          }
        }
      }
    }
  }
}

/**
 * 🌟 INLINE EMBEDDED DANCING MASTER HUB (INSIDE SUBJECT SCREEN)
 */
@Composable
fun DancingGrade10And11MasterHub(
  currentGrade: String = "11",
  onOpenFullHub: (DancingSectionCategory?) -> Unit
) {
  Card(
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF200612)),
    border = BorderStroke(1.2.dp, Color(0xFFE11D48)),
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(14.dp))
      .clickable { onOpenFullHub(null) }
      .testTag("dancing_master_hub_embedded_card")
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text("💃", fontSize = 18.sp)
          Spacer(modifier = Modifier.width(6.dp))
          Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "10 & 11 නර්තනය Master Hub (පූර්ණ තිරයෙන්)",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
              )
              Spacer(modifier = Modifier.width(6.dp))
              Surface(
                color = Color(0xFFE11D48),
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
              text = "ත්‍රිවිධ සම්ප්‍රදාය • වන්නම් 18 • අභිනය • නවරස • මුද්‍රා • බෙර වාදන • Viva • O/L MCQs",
              color = Color(0xFFFECDD3),
              fontSize = 10.sp
            )
          }
        }
        Surface(
          color = Color(0xFF881337),
          shape = CircleShape,
          border = BorderStroke(1.dp, Color(0xFFFB7185))
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
          onClick = { onOpenFullHub(DancingSectionCategory.TRADITIONS_AND_DRUMS) },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE11D48)),
          shape = RoundedCornerShape(8.dp),
          contentPadding = PaddingValues(horizontal = 8.dp, vertical = 3.dp),
          modifier = Modifier.height(28.dp)
        ) {
          Text("💃 ත්‍රිවිධ සම්ප්‍රදාය", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Button(
          onClick = { onOpenFullHub(DancingSectionCategory.VANNAM_18) },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0284C7)),
          shape = RoundedCornerShape(8.dp),
          contentPadding = PaddingValues(horizontal = 8.dp, vertical = 3.dp),
          modifier = Modifier.height(28.dp)
        ) {
          Text("🦚 වන්නම් 18", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Button(
          onClick = { onOpenFullHub(DancingSectionCategory.ABHINAYA_AND_MUDRAS) },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7C3AED)),
          shape = RoundedCornerShape(8.dp),
          contentPadding = PaddingValues(horizontal = 8.dp, vertical = 3.dp),
          modifier = Modifier.height(28.dp)
        ) {
          Text("🖐️ අභිනය & මුද්‍රා", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Button(
          onClick = { onOpenFullHub(DancingSectionCategory.INSTRUMENTS_AND_COSTUMES) },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD97706)),
          shape = RoundedCornerShape(8.dp),
          contentPadding = PaddingValues(horizontal = 8.dp, vertical = 3.dp),
          modifier = Modifier.height(28.dp)
        ) {
          Text("🥁 පංචතූර්ය & ඇඳුම්", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Button(
          onClick = { onOpenFullHub(DancingSectionCategory.PRACTICAL_AND_VIVA) },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF059669)),
          shape = RoundedCornerShape(8.dp),
          contentPadding = PaddingValues(horizontal = 8.dp, vertical = 3.dp),
          modifier = Modifier.height(28.dp)
        ) {
          Text("🎙️ ප්‍රායෝගික & Viva", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Button(
          onClick = { onOpenFullHub(DancingSectionCategory.EXAM_MCQ_AND_ESSAYS) },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC2626)),
          shape = RoundedCornerShape(8.dp),
          contentPadding = PaddingValues(horizontal = 8.dp, vertical = 3.dp),
          modifier = Modifier.height(28.dp)
        ) {
          Text("🎯 O/L MCQs & රචනා", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
      }
    }
  }
}

// ============================================================================
// SUB-COMPONENTS FOR EACH CATEGORY
// ============================================================================

@Composable
fun DancingTraditionsList(
  traditions: List<DanceTradition>,
  expandedId: String?,
  onToggleExpand: (String) -> Unit
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 50.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    items(traditions) { tradition ->
      val isExpanded = expandedId == tradition.id
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF240A18)),
        border = BorderStroke(1.dp, Color(0xFF9F1239)),
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
                text = tradition.nameSinhala,
                color = Color.White,
                fontSize = 13.5.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "කලාපය: ${tradition.region}",
                color = Color(0xFFFDA4AF),
                fontSize = 10.sp
              )
            }
            IconButton(
              onClick = { onToggleExpand(tradition.id) },
              modifier = Modifier.size(28.dp)
            ) {
              Icon(
                imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = "Expand",
                tint = Color(0xFFFB7185)
              )
            }
          }

          Spacer(modifier = Modifier.height(6.dp))

          // Key Highlights Row
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Surface(
              color = Color(0xFF3F0B1E),
              shape = RoundedCornerShape(6.dp),
              modifier = Modifier.weight(1f)
            ) {
              Column(modifier = Modifier.padding(6.dp)) {
                Text("ප්‍රධාන බෙරය", fontSize = 8.5.sp, color = Color(0xFFFECDD3))
                Text(tradition.primaryDrum, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE047))
              }
            }

            Surface(
              color = Color(0xFF3F0B1E),
              shape = RoundedCornerShape(6.dp),
              modifier = Modifier.weight(1f)
            ) {
              Column(modifier = Modifier.padding(6.dp)) {
                Text("ප්‍රධාන ශාන්තිකර්මය", fontSize = 8.5.sp, color = Color(0xFFFECDD3))
                Text(tradition.sacredShanthikarma, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.White, maxLines = 1, overflow = TextOverflow.Ellipsis)
              }
            }
          }

          AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
          ) {
            Column(modifier = Modifier.padding(top = 10.dp)) {
              HorizontalDivider(color = Color(0xFF4C0519))
              Spacer(modifier = Modifier.height(8.dp))

              Text("🥁 බෙරයේ ලක්ෂණ සහ නාද රටා:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE047))
              Text(tradition.drumCharacteristics, fontSize = 10.5.sp, color = Color(0xFFFFE4E6), lineHeight = 15.sp)
              Spacer(modifier = Modifier.height(4.dp))
              Surface(
                color = Color(0xFF14050C),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.dp, Color(0xFFE11D48).copy(alpha = 0.5f)),
                modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)
              ) {
                Text(
                  text = "වාදන අක්ෂර: ${tradition.drumSyllables}",
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFFFB7185),
                  modifier = Modifier.padding(6.dp)
                )
              }

              Spacer(modifier = Modifier.height(8.dp))
              Text("👗 ප්‍රධාන ඇඳුම් ආයිත්තම්:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE047))
              Text(tradition.mainCostume, fontSize = 10.5.sp, color = Color.White)
              tradition.costumeJewelry.forEach { jw ->
                Text("• $jw", fontSize = 10.sp, color = Color(0xFFFDA4AF), modifier = Modifier.padding(start = 6.dp, top = 1.dp))
              }

              Spacer(modifier = Modifier.height(8.dp))
              Text("🏃 මූලික අභ්‍යාස & සරඹ:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE047))
              tradition.keyExercises.forEach { ex ->
                Text("• $ex", fontSize = 10.sp, color = Color(0xFFE2E8F0), modifier = Modifier.padding(start = 6.dp, top = 1.dp))
              }

              Spacer(modifier = Modifier.height(8.dp))
              Text("🏛️ ඓතිහාසික පසුබිම & විශේෂතා:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE047))
              Text(tradition.historicalOrigin, fontSize = 10.5.sp, color = Color(0xFFFFE4E6), lineHeight = 14.sp)
              Spacer(modifier = Modifier.height(3.dp))
              Text(tradition.specialFeatures, fontSize = 10.sp, color = Color(0xFFFECDD3), fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
            }
          }
        }
      }
    }
  }
}

@Composable
fun DancingVannamsList(
  vannams: List<VannamItem>,
  expandedNumber: Int?,
  onToggleExpand: (Int) -> Unit
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 50.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(vannams) { vannam ->
      val isExpanded = expandedNumber == vannam.number
      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1528)),
        border = BorderStroke(1.dp, Color(0xFF0284C7)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(10.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
              Surface(
                color = Color(0xFF0284C7),
                shape = CircleShape,
                modifier = Modifier.size(24.dp)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Text("${vannam.number}", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
              }
              Spacer(modifier = Modifier.width(8.dp))
              Column {
                Text(vannam.nameSinhala, color = Color.White, fontSize = 12.5.sp, fontWeight = FontWeight.Bold)
                Text("තේමාව: ${vannam.animalOrTheme} • ${vannam.matraCount}", color = Color(0xFFBAE6FD), fontSize = 9.5.sp)
              }
            }
            IconButton(
              onClick = { onToggleExpand(vannam.number) },
              modifier = Modifier.size(26.dp)
            ) {
              Icon(
                imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = "Expand",
                tint = Color(0xFF38BDF8)
              )
            }
          }

          AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
          ) {
            Column(modifier = Modifier.padding(top = 8.dp)) {
              HorizontalDivider(color = Color(0xFF0369A1))
              Spacer(modifier = Modifier.height(6.dp))

              Text("📜 ආරම්භක කවිය:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE047))
              Surface(
                color = Color(0xFF0F172A),
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)
              ) {
                Text(
                  text = vannam.poemSinhala,
                  fontSize = 10.sp,
                  color = Color(0xFFE2E8F0),
                  lineHeight = 15.sp,
                  modifier = Modifier.padding(6.dp)
                )
              }

              Spacer(modifier = Modifier.height(4.dp))
              Text("🎶 තානම (Thanama):", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
              Text(vannam.thanamaSinhala, fontSize = 9.5.sp, color = Color(0xFFBAE6FD))

              Spacer(modifier = Modifier.height(4.dp))
              Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                  Text("⚡ කස්තිරම:", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFFF472B6))
                  Text(vannam.kastiramaSinhala, fontSize = 9.sp, color = Color.White)
                }
                Column(modifier = Modifier.weight(1f)) {
                  Text("🔄 සීරුමාරුව:", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4ADE80))
                  Text(vannam.seerumaruwaSinhala, fontSize = 9.sp, color = Color.White)
                }
              }

              Spacer(modifier = Modifier.height(4.dp))
              Text("🏁 අඩව්ව (Adawwa):", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFACC15))
              Text(vannam.adawwaSinhala, fontSize = 9.5.sp, color = Color.White)

              Spacer(modifier = Modifier.height(4.dp))
              Text("💡 අර්ථය හා රස භාවය:", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFFA78BFA))
              Text(vannam.meaningAndBhava, fontSize = 9.5.sp, color = Color(0xFFE0E7FF), lineHeight = 13.5.sp)
            }
          }
        }
      }
    }
  }
}

@Composable
fun DancingAbhinayaAndMudrasList(
  abhinayas: List<AbhinayaItem>,
  rasas: List<RasaItem>,
  mudras: List<MudraItem>,
  expandedAbhinayaId: String?,
  onToggleAbhinaya: (String) -> Unit,
  expandedRasaNumber: Int?,
  onToggleRasa: (Int) -> Unit,
  expandedMudraId: String?,
  onToggleMudra: (String) -> Unit
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 50.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    // 1. චතුර්විධ අභිනය
    item {
      Text(
        text = "🎭 චතුර්විධ අභිනය (The 4 Abhinayas)",
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFFDE047)
      )
    }

    items(abhinayas) { abhinaya ->
      val isExpanded = expandedAbhinayaId == abhinaya.id
      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF261238)),
        border = BorderStroke(1.dp, Color(0xFF7C3AED)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(10.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(abhinaya.nameSinhala, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
              Text(abhinaya.nameSanskrit, color = Color(0xFFC4B5FD), fontSize = 9.5.sp)
            }
            IconButton(
              onClick = { onToggleAbhinaya(abhinaya.id) },
              modifier = Modifier.size(24.dp)
            ) {
              Icon(
                imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = null,
                tint = Color(0xFFA78BFA)
              )
            }
          }
          Text(abhinaya.definition, fontSize = 10.sp, color = Color(0xFFEDE9FE), lineHeight = 14.sp)

          AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.padding(top = 6.dp)) {
              HorizontalDivider(color = Color(0xFF4C1D95))
              Spacer(modifier = Modifier.height(4.dp))
              Text("සංරචක:", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE047))
              abhinaya.components.forEach { comp ->
                Text("• $comp", fontSize = 9.5.sp, color = Color(0xFFDDD6FE), modifier = Modifier.padding(start = 4.dp, top = 1.dp))
              }
              Spacer(modifier = Modifier.height(4.dp))
              Text("ප්‍රායෝගික භාවිතය: ${abhinaya.practicalApplication}", fontSize = 9.sp, color = Color(0xFFA7F3D0))
            }
          }
        }
      }
    }

    // 2. නවරස
    item {
      Spacer(modifier = Modifier.height(6.dp))
      Text(
        text = "🎨 නවරස සහ ස්ථායී භාව (The 9 Rasas)",
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFFDE047)
      )
    }

    items(rasas) { rasa ->
      val isExpanded = expandedRasaNumber == rasa.number
      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E2E)),
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
              Text("${rasa.number}. ${rasa.rasaSinhala}", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
              Text("ස්ථායී භාවය: ${rasa.sthayiBhavaSinhala}", color = Color(0xFF818CF8), fontSize = 9.5.sp)
            }
            IconButton(onClick = { onToggleRasa(rasa.number) }, modifier = Modifier.size(24.dp)) {
              Icon(if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null, tint = Color(0xFFA5B4FC))
            }
          }

          AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.padding(top = 6.dp)) {
              HorizontalDivider(color = Color(0xFF312E81))
              Spacer(modifier = Modifier.height(4.dp))
              Text(rasa.description, fontSize = 10.sp, color = Color(0xFFE0E7FF), lineHeight = 14.sp)
              Spacer(modifier = Modifier.height(4.dp))
              Text("වර්ණය: ${rasa.colorAssociation} • අධිපති දෙවියා: ${rasa.presidingDeity}", fontSize = 9.sp, color = Color(0xFFFDE047))
              Text("💡 නර්තන ඉරියව් ඉඟිය: ${rasa.danceExpressionTip}", fontSize = 9.sp, color = Color(0xFF6EE7B7))
            }
          }
        }
      }
    }

    // 3. හස්ත මුද්‍රා
    item {
      Spacer(modifier = Modifier.height(6.dp))
      Text(
        text = "🖐️ හස්ත මුද්‍රා (අසංයුත 28 & සංයුත 24)",
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFFDE047)
      )
    }

    items(mudras) { mudra ->
      val isExpanded = expandedMudraId == mudra.id
      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF14241E)),
        border = BorderStroke(1.dp, Color(0xFF059669)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(10.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(mudra.nameSinhala, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
              Text("වර්ගය: ${mudra.type}", color = Color(0xFF6EE7B7), fontSize = 9.sp)
            }
            IconButton(onClick = { onToggleMudra(mudra.id) }, modifier = Modifier.size(24.dp)) {
              Icon(if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null, tint = Color(0xFF34D399))
            }
          }
          Text("හැඩය: ${mudra.handShapeDescription}", fontSize = 9.5.sp, color = Color(0xFFD1FAE5))

          AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.padding(top = 6.dp)) {
              HorizontalDivider(color = Color(0xFF065F46))
              Spacer(modifier = Modifier.height(4.dp))
              Text("දක්වන අර්ථයන්:", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE047))
              mudra.representations.forEach { rep ->
                Text("• $rep", fontSize = 9.sp, color = Color.White, modifier = Modifier.padding(start = 4.dp))
              }
              Spacer(modifier = Modifier.height(3.dp))
              Text("විස්තරය: ${mudra.shlokaReference}", fontSize = 8.5.sp, color = Color(0xFFA7F3D0))
            }
          }
        }
      }
    }
  }
}

@Composable
fun DancingInstrumentsList(
  items: List<InstrumentCostumeItem>,
  expandedId: String?,
  onToggleExpand: (String) -> Unit
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 50.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(items) { item ->
      val isExpanded = expandedId == item.id
      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF26180B)),
        border = BorderStroke(1.dp, Color(0xFFD97706)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(10.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(item.titleSinhala, color = Color.White, fontSize = 12.5.sp, fontWeight = FontWeight.Bold)
              Text("${item.category} • ${item.classification}", color = Color(0xFFFDE68A), fontSize = 9.5.sp)
            }
            IconButton(onClick = { onToggleExpand(item.id) }, modifier = Modifier.size(24.dp)) {
              Icon(if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null, tint = Color(0xFFFBBF24))
            }
          }

          Text(item.detailsSinhala, fontSize = 10.sp, color = Color(0xFFFEF3C7), lineHeight = 14.sp)

          AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.padding(top = 6.dp)) {
              HorizontalDivider(color = Color(0xFF78350F))
              Spacer(modifier = Modifier.height(4.dp))
              Text("තැනීමට ගන්නා ද්‍රව්‍ය: ${item.rawMaterials}", fontSize = 9.5.sp, color = Color(0xFFFCD34D))
              Spacer(modifier = Modifier.height(2.dp))
              Text("ශාන්තිකර්ම හා පූජා වැදගත්කම: ${item.ritualSignificance}", fontSize = 9.5.sp, color = Color(0xFFD1FAE5))
            }
          }
        }
      }
    }
  }
}

@Composable
fun DancingPracticalVivaList(
  items: List<PracticalVivaItem>,
  expandedId: String?,
  onToggleExpand: (String) -> Unit
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 50.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(items) { item ->
      val isExpanded = expandedId == item.id
      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0A2218)),
        border = BorderStroke(1.dp, Color(0xFF059669)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(10.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(item.titleSinhala, color = Color.White, fontSize = 12.5.sp, fontWeight = FontWeight.Bold)
              Text("${item.category} • ලකුණු: ${item.marksWeightage}", color = Color(0xFFA7F3D0), fontSize = 9.5.sp)
            }
            IconButton(onClick = { onToggleExpand(item.id) }, modifier = Modifier.size(24.dp)) {
              Icon(if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null, tint = Color(0xFF34D399))
            }
          }

          AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.padding(top = 6.dp)) {
              HorizontalDivider(color = Color(0xFF065F46))
              Spacer(modifier = Modifier.height(4.dp))

              Text("🎯 මූලික ශිල්ප ක්‍රම & පුහුණුව:", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE047))
              item.keyTechniques.forEach { tech ->
                Text("• $tech", fontSize = 9.5.sp, color = Color(0xFFD1FAE5), modifier = Modifier.padding(start = 4.dp))
              }

              Spacer(modifier = Modifier.height(4.dp))
              Text("👨‍🏫 පරීක්ෂක අපේක්ෂාව: ${item.examinerExpectations}", fontSize = 9.5.sp, color = Color.White)

              Spacer(modifier = Modifier.height(4.dp))
              Text("⚠️ වැළකිය යුතු පොදු වැරදි: ${item.commonMistakesToAvoid}", fontSize = 9.5.sp, color = Color(0xFFFECDD3))

              if (item.sampleQuestionAndAnswer != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                  color = Color(0xFF041E15),
                  shape = RoundedCornerShape(6.dp),
                  modifier = Modifier.fillMaxWidth().padding(top = 2.dp)
                ) {
                  Text(
                    text = item.sampleQuestionAndAnswer,
                    fontSize = 9.5.sp,
                    color = Color(0xFFFDE047),
                    modifier = Modifier.padding(6.dp),
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

@Composable
fun DancingExamsList(
  mcqList: List<DanceMcqQuestion>,
  essays: List<DanceModelEssay>,
  userSelectedOptions: Map<Int, Int>,
  onSelectOption: (Int, Int, Boolean) -> Unit,
  mcqScore: Int,
  expandedEssayId: String?,
  onToggleEssay: (String) -> Unit
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 50.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    // MCQ Score Card
    item {
      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF3F0B1E)),
        border = BorderStroke(1.dp, Color(0xFFE11D48)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier.padding(10.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Column {
            Text("🎯 O/L නර්තනය MCQ පෙරහුරුව", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text("පිළිතුරු ලකුණු කර විවරණ බලන්න", color = Color(0xFFFDA4AF), fontSize = 9.5.sp)
          }
          Surface(
            color = Color(0xFFE11D48),
            shape = RoundedCornerShape(8.dp)
          ) {
            Text(
              text = "ලකුණු: $mcqScore / ${mcqList.size}",
              color = Color.White,
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
          }
        }
      }
    }

    // MCQ Questions
    itemsIndexed(mcqList) { index, mcq ->
      val selectedOption = userSelectedOptions[mcq.id]
      val hasAnswered = selectedOption != null

      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E0A16)),
        border = BorderStroke(1.dp, Color(0xFF881337)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(10.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(
              text = "${index + 1}. [${mcq.grade} ශ්‍රේණිය • ${mcq.categoryTag}]",
              color = Color(0xFFFDA4AF),
              fontSize = 9.5.sp,
              fontWeight = FontWeight.Bold
            )
          }
          Spacer(modifier = Modifier.height(4.dp))
          Text(mcq.questionText, color = Color.White, fontSize = 11.5.sp, fontWeight = FontWeight.Medium, lineHeight = 15.sp)

          Spacer(modifier = Modifier.height(8.dp))

          mcq.options.forEachIndexed { optIndex, optText ->
            val isSelected = selectedOption == optIndex
            val isCorrect = optIndex == mcq.correctOptionIndex

            val optColor = when {
              !hasAnswered -> Color(0xFF2E0D1E)
              isSelected && isCorrect -> Color(0xFF065F46)
              isSelected && !isCorrect -> Color(0xFF7F1D1D)
              isCorrect -> Color(0xFF065F46).copy(alpha = 0.6f)
              else -> Color(0xFF2E0D1E)
            }

            Surface(
              onClick = {
                if (!hasAnswered) {
                  onSelectOption(mcq.id, optIndex, optIndex == mcq.correctOptionIndex)
                }
              },
              shape = RoundedCornerShape(6.dp),
              color = optColor,
              border = BorderStroke(1.dp, if (isSelected) Color.White else Color(0xFF500724)),
              modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = "(${optIndex + 1})",
                  color = Color(0xFFFDA4AF),
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(optText, color = Color.White, fontSize = 10.sp, modifier = Modifier.weight(1f))
                if (hasAnswered && isCorrect) {
                  Icon(Icons.Default.CheckCircle, contentDescription = "Correct", tint = Color(0xFF34D399), modifier = Modifier.size(16.dp))
                }
              }
            }
          }

          if (hasAnswered) {
            Spacer(modifier = Modifier.height(6.dp))
            Surface(
              color = Color(0xFF14050C),
              shape = RoundedCornerShape(6.dp),
              border = BorderStroke(1.dp, Color(0xFF4C0519)),
              modifier = Modifier.fillMaxWidth()
            ) {
              Text(
                text = "💡 විවරණය: ${mcq.explanation}",
                fontSize = 9.5.sp,
                color = Color(0xFFFDE047),
                modifier = Modifier.padding(6.dp),
                lineHeight = 13.5.sp
              )
            }
          }
        }
      }
    }

    // Model Essays
    item {
      Spacer(modifier = Modifier.height(8.dp))
      Text("📝 O/L II පත්‍රයේ ආදර්ශ රචනා සහ ලකුණු පටිපාටි", color = Color(0xFFFDE047), fontSize = 13.sp, fontWeight = FontWeight.Bold)
    }

    items(essays) { essay ->
      val isExpanded = expandedEssayId == essay.id
      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF18182E)),
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
              Text(essay.questionTitle, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
              Text("ඒකකය: ${essay.unitName} • ${essay.grade} ශ්‍රේණිය", color = Color(0xFFA5B4FC), fontSize = 9.sp)
            }
            IconButton(onClick = { onToggleEssay(essay.id) }, modifier = Modifier.size(24.dp)) {
              Icon(if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null, tint = Color(0xFF818CF8))
            }
          }

          Text(essay.scenarioOrStem, fontSize = 9.5.sp, color = Color(0xFFE0E7FF), lineHeight = 13.5.sp)

          AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.padding(top = 8.dp)) {
              HorizontalDivider(color = Color(0xFF312E81))
              Spacer(modifier = Modifier.height(6.dp))

              essay.subQuestions.forEach { sq ->
                Text("${sq.numberText} ${sq.questionText} (ලකුණු ${sq.marks})", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE047))
                Spacer(modifier = Modifier.height(2.dp))
                sq.markingSchemePoints.forEach { pt ->
                  Text("• $pt", fontSize = 9.5.sp, color = Color(0xFFC7D2FE), modifier = Modifier.padding(start = 6.dp, top = 1.dp))
                }
                Spacer(modifier = Modifier.height(6.dp))
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun DancingLessonsList(
  lessons: List<DanceLessonNote>,
  expandedId: String?,
  onToggleExpand: (String) -> Unit
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 50.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(lessons) { lesson ->
      val isExpanded = expandedId == lesson.id
      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF14142B)),
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
              Text("${lesson.grade} ශ්‍රේණිය - ඒකකය ${lesson.unitNumber}: ${lesson.unitTitle}", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
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
