package com.example

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.speech.tts.TextToSpeech

// ==============================================================================
// 🌟 INTERACTIVE Q&A MASTER HUB (ප්‍රශ්න හා පිළිතුරු පුහුණු මධ්‍යස්ථානය)
// 1. Structured / Short-Answer Self-Evaluation (ව්‍යුහගත කෙටි පිළිතුරු)
// 2. Interactive Flashcard Q&A (Flip-Card මතක කාඩ්පත්)
// 3. Daily Mixed 10 Challenge (දිනපතා මිශ්‍ර ප්‍රශ්න 10)
// 4. Match the Following & Timeline Ordering (ගැලපීම් & කාලරේඛා)
// 5. True/False with Deep Justification (සත්‍ය/අසත්‍ය & විවරණ)
// 6. Custom Self-Quiz Maker (ශිෂ්‍යයාගේම ප්‍රශ්න එකතුව)
// ==============================================================================

enum class QaHubTab(val title: String, val icon: String, val badge: String, val color: Color) {
  SHORT_ANSWER("කෙටි පිළිතුරු", "✍️", "EVALUATE", Color(0xFF38BDF8)),
  FLASHCARDS("Flashcards", "⚡", "FLIP", Color(0xFFF59E0B)),
  DAILY_CHALLENGE("දිනපතා 10", "🎯", "DAILY", Color(0xFF10B981)),
  MATCHING("ගැලපීම් & පෙළගැස්ම", "🧩", "MATCH", Color(0xFF8B5CF6)),
  TRUE_FALSE("සත්‍ය / අසත්‍ය", "⚖️", "REASON", Color(0xFFEC4899)),
  CUSTOM_QUIZ("මගේ ප්‍රශ්න", "📝", "MAKER", Color(0xFF14B8A6))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InteractiveQaMasterHubScreen(
  onBack: () -> Unit,
  initialTab: QaHubTab = QaHubTab.SHORT_ANSWER
) {
  var activeTab by remember { mutableStateOf(initialTab) }

  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Column {
            Text(
              text = "🎯 Interactive Q&A Master Hub",
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
            Text(
              text = "O/L විභාග ප්‍රශ්න හා පිළිතුරු පුහුණු විශේෂාංග 6",
              fontSize = 10.sp,
              color = Color(0xFFBAE6FD)
            )
          }
        },
        navigationIcon = {
          IconButton(
            onClick = onBack,
            modifier = Modifier.testTag("qa_hub_back_btn")
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "Back",
              tint = Color.White
            )
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F172A))
      )
    }
  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF0B132B))
        .padding(padding)
    ) {
      // Horizontal Tab Row
      ScrollableTabRow(
        selectedTabIndex = activeTab.ordinal,
        containerColor = Color(0xFF111C44),
        contentColor = Color.White,
        edgePadding = 12.dp,
        divider = {}
      ) {
        QaHubTab.values().forEach { tab ->
          Tab(
            selected = activeTab == tab,
            onClick = { activeTab = tab },
            text = {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
              ) {
                Text(tab.icon, fontSize = 14.sp)
                Text(
                  text = tab.title,
                  fontSize = 11.5.sp,
                  fontWeight = if (activeTab == tab) FontWeight.Bold else FontWeight.Normal,
                  color = if (activeTab == tab) tab.color else Color(0xFF94A3B8)
                )
              }
            },
            modifier = Modifier.testTag("tab_${tab.name}")
          )
        }
      }

      // Tab Content Body
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(horizontal = 12.dp, vertical = 8.dp)
      ) {
        when (activeTab) {
          QaHubTab.SHORT_ANSWER -> ShortAnswerEvaluationSection()
          QaHubTab.FLASHCARDS -> FlashcardQaSection()
          QaHubTab.DAILY_CHALLENGE -> DailyMixedChallengeSection()
          QaHubTab.MATCHING -> MatchingAndOrderingSection()
          QaHubTab.TRUE_FALSE -> TrueFalseWithJustificationSection()
          QaHubTab.CUSTOM_QUIZ -> CustomQuizMakerSection()
        }
      }
    }
  }
}

// ==============================================================================
// 1. ✍️ STRUCTURED / SHORT ANSWER EVALUATION (20 Distinct Sets Per Subject)
// ==============================================================================
@Composable
fun ShortAnswerEvaluationSection() {
  val grades = listOf("10", "11")
  var selectedGrade by remember { mutableStateOf("11") }
  val subjectOptions = ShortAnswerMasterRepository.subjects
  var selectedSubject by remember { mutableStateOf("විද්‍යාව") }

  val setsForGrade = remember(selectedGrade, selectedSubject) {
    ShortAnswerMasterRepository.getSetsForSubject(selectedGrade, selectedSubject)
  }

  var selectedSetNumber by remember(selectedGrade, selectedSubject) {
    mutableIntStateOf(1)
  }

  val currentSetInfo = setsForGrade.find { it.setNumber == selectedSetNumber } ?: setsForGrade.first()

  val questions = remember(selectedGrade, selectedSubject, selectedSetNumber) {
    ShortAnswerMasterRepository.getQuestions(selectedGrade, selectedSubject, selectedSetNumber)
  }

  var selectedIndex by remember(selectedGrade, selectedSubject, selectedSetNumber) { mutableIntStateOf(0) }
  val currentQ = if (questions.isNotEmpty() && selectedIndex < questions.size) questions[selectedIndex] else questions.firstOrNull()

  var studentAnswer by remember(selectedGrade, selectedSubject, selectedSetNumber, selectedIndex) { mutableStateOf("") }
  var isEvaluated by remember(selectedGrade, selectedSubject, selectedSetNumber, selectedIndex) { mutableStateOf(false) }
  var evalResult by remember(selectedGrade, selectedSubject, selectedSetNumber, selectedIndex) { mutableStateOf<EvaluationResult?>(null) }

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(12.dp),
    contentPadding = PaddingValues(top = 4.dp, bottom = 140.dp)
  ) {
    // Top Control: Grade Selection and Overview
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        border = BorderStroke(1.dp, Color(0xFF38BDF8).copy(alpha = 0.5f))
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text("✍️", fontSize = 18.sp)
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = "ව්‍යුහගත කෙටි පිළිතුරු ස්වයං ඇගයීම",
                fontSize = 13.5.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
            }
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = Color(0xFF0284C7)
            ) {
              Text(
                text = "විෂයකට කාණ්ඩ 20 | ප්‍රශ්න 10 බැගින්",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = "ශ්‍රේණිය සහ විෂය තෝරන්න. සෑම විෂයකටම එකිනෙකට වෙනස් විෂය නිර්දේශානුකූල කාණ්ඩ 20 ක් (කාණ්ඩයකට ප්‍රශ්න 10 බැගින්) අඩංගුය. 100% ක් නිවැරදි Marking Scheme මඟින් ක්ෂණික ලකුණු හිමිවේ.",
            fontSize = 11.5.sp,
            color = Color(0xFFCBD5E1),
            lineHeight = 16.sp
          )

          Spacer(modifier = Modifier.height(12.dp))

          // 1. Grade Selector (10 / 11)
          Text(
            text = "ශ්‍රේණිය තෝරන්න (Select Grade):",
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF94A3B8)
          )

          Spacer(modifier = Modifier.height(6.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            grades.forEach { gr ->
              val isSelected = gr == selectedGrade
              Button(
                onClick = {
                  selectedGrade = gr
                  selectedSetNumber = 1
                  selectedIndex = 0
                  studentAnswer = ""
                  isEvaluated = false
                  evalResult = null
                },
                modifier = Modifier
                  .weight(1f)
                  .testTag("grade_btn_" + gr),
                colors = ButtonDefaults.buttonColors(
                  containerColor = if (isSelected) Color(0xFF0284C7) else Color(0xFF0F172A)
                ),
                border = BorderStroke(
                  1.dp,
                  if (isSelected) Color(0xFF38BDF8) else Color(0xFF334155)
                ),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
              ) {
                Text(
                  text = "$gr ශ්‍රේණිය",
                  fontSize = 12.sp,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                  color = if (isSelected) Color.White else Color(0xFFCBD5E1)
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(12.dp))

          // 2. Subject Selector
          Text(
            text = "විෂය තෝරන්න (Select Subject):",
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF94A3B8)
          )

          Spacer(modifier = Modifier.height(6.dp))

          LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(vertical = 2.dp)
          ) {
            items(subjectOptions) { subj ->
              val isSubjSelected = subj == selectedSubject
              val icon = ShortAnswerMasterRepository.getSubjectIcon(subj)
              Surface(
                onClick = {
                  selectedSubject = subj
                  selectedSetNumber = 1
                  selectedIndex = 0
                  studentAnswer = ""
                  isEvaluated = false
                  evalResult = null
                },
                shape = RoundedCornerShape(8.dp),
                color = if (isSubjSelected) Color(0xFF0284C7) else Color(0xFF0F172A),
                border = BorderStroke(1.dp, if (isSubjSelected) Color(0xFF38BDF8) else Color(0xFF334155)),
                modifier = Modifier.testTag("subj_chip_" + subj)
              ) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                  Text(icon, fontSize = 12.sp)
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = subj,
                    fontSize = 11.sp,
                    fontWeight = if (isSubjSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSubjSelected) Color.White else Color(0xFFCBD5E1)
                  )
                }
              }
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // 3. Question Set Selector (20 Sets)
          Text(
            text = "$selectedGrade ශ්‍රේණියේ $selectedSubject ප්‍රශ්න කාණ්ඩය තෝරන්න (කාණ්ඩ 20):",
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF94A3B8)
          )

          Spacer(modifier = Modifier.height(6.dp))

          LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 2.dp)
          ) {
            items(setsForGrade) { setInfo ->
              val isSetSelected = setInfo.setNumber == selectedSetNumber
              Surface(
                onClick = {
                  selectedSetNumber = setInfo.setNumber
                  selectedIndex = 0
                  studentAnswer = ""
                  isEvaluated = false
                  evalResult = null
                },
                shape = RoundedCornerShape(10.dp),
                color = if (isSetSelected) Color(0xFF0369A1) else Color(0xFF0F172A),
                border = BorderStroke(
                  1.dp,
                  if (isSetSelected) Color(0xFF38BDF8) else Color(0xFF334155)
                ),
                modifier = Modifier.testTag("set_chip_${setInfo.setNumber}")
              ) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                  Text(setInfo.icon, fontSize = 13.sp)
                  Spacer(modifier = Modifier.width(6.dp))
                  Column {
                    Text(
                      text = "කාණ්ඩය ${String.format("%02d", setInfo.setNumber)}",
                      fontSize = 11.sp,
                      fontWeight = FontWeight.Bold,
                      color = if (isSetSelected) Color.White else Color(0xFFE2E8F0)
                    )
                    Text(
                      text = setInfo.titleSinhala.substringAfter(": ", setInfo.titleSinhala),
                      fontSize = 9.sp,
                      color = if (isSetSelected) Color(0xFFBAE6FD) else Color(0xFF94A3B8),
                      maxLines = 1,
                      overflow = TextOverflow.Ellipsis
                    )
                  }
                }
              }
            }
          }
        }
      }
    }

    // Set Title Banner
    item {
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
        border = BorderStroke(1.dp, Color(0xFF1E293B))
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
          ) {
            Text(currentSetInfo.icon, fontSize = 16.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = currentSetInfo.titleSinhala,
              fontSize = 11.5.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFFE2E8F0),
              maxLines = 1,
              overflow = TextOverflow.Ellipsis
            )
          }

          Surface(
            shape = RoundedCornerShape(6.dp),
            color = Color(0xFF0284C7).copy(alpha = 0.2f)
          ) {
            Text(
              text = "ප්‍රශ්න ${selectedIndex + 1}/${questions.size}",
              fontSize = 10.5.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF38BDF8),
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
            )
          }
        }
      }
    }

    // 4. Question Index Stepper (10 Questions in the set)
    item {
      LazyRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        itemsIndexed(questions) { idx, q ->
          val isQSelected = selectedIndex == idx
          Surface(
            onClick = {
              selectedIndex = idx
              studentAnswer = ""
              isEvaluated = false
              evalResult = null
            },
            shape = RoundedCornerShape(8.dp),
            color = if (isQSelected) Color(0xFF0284C7) else Color(0xFF1E293B),
            border = BorderStroke(
              1.dp,
              if (isQSelected) Color(0xFF38BDF8) else Color(0xFF334155)
            ),
            modifier = Modifier.testTag("q_step_$idx")
          ) {
            Text(
              text = "Q${idx + 1}",
              fontSize = 11.sp,
              fontWeight = if (isQSelected) FontWeight.Bold else FontWeight.Normal,
              color = if (isQSelected) Color.White else Color(0xFFCBD5E1),
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
            )
          }
        }
      }
    }

    // 5. Current Question Display & Answer Box
    if (currentQ != null) {
      item {
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
          border = BorderStroke(1.dp, Color(0xFF334155))
        ) {
          Column(modifier = Modifier.padding(14.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Surface(
                shape = RoundedCornerShape(6.dp),
                color = Color(0xFF38BDF8).copy(alpha = 0.15f)
              ) {
                Text(
                  text = "${currentQ.subject} • ${currentQ.grade} ශ්‍රේණිය • කාණ්ඩය ${String.format("%02d", selectedSetNumber)}",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF38BDF8),
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
              }

              Text(
                text = "උපරිම ලකුණු: ${currentQ.maxMarks}",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFBBF24)
              )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
              text = "පාඩම/මාතෘකාව: ${currentQ.topic}",
              fontSize = 11.sp,
              fontWeight = FontWeight.SemiBold,
              color = Color(0xFF94A3B8)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
              text = currentQ.question,
              fontSize = 13.5.sp,
              fontWeight = FontWeight.SemiBold,
              color = Color.White,
              lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            OutlinedTextField(
              value = studentAnswer,
              onValueChange = {
                studentAnswer = it
                if (isEvaluated) isEvaluated = false
              },
              placeholder = {
                Text(
                  "ඔබගේ කෙටි පිළිතුර මෙහි සටහන් කරන්න...",
                  color = Color(0xFF64748B),
                  fontSize = 12.sp
                )
              },
              modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp)
                .testTag("short_answer_input"),
              shape = RoundedCornerShape(12.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF1E293B),
                unfocusedContainerColor = Color(0xFF1E293B),
                focusedBorderColor = Color(0xFF38BDF8),
                unfocusedBorderColor = Color(0xFF475569),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
              )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Button(
                onClick = {
                  if (studentAnswer.isNotBlank()) {
                    val result = ShortAnswerEvaluator.evaluate(currentQ, studentAnswer)
                    evalResult = result
                    isEvaluated = true
                  }
                },
                enabled = studentAnswer.isNotBlank(),
                modifier = Modifier
                  .weight(1f)
                  .testTag("evaluate_answer_btn"),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0284C7)),
                shape = RoundedCornerShape(10.dp)
              ) {
                Text(
                  "🔍 පිළිතුර ඇගයීම (Evaluate)",
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color.White
                )
              }

              OutlinedButton(
                onClick = {
                  studentAnswer = ""
                  isEvaluated = false
                  evalResult = null
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFCBD5E1)),
                border = BorderStroke(1.dp, Color(0xFF475569))
              ) {
                Text("මකන්න", fontSize = 11.sp)
              }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Quick Question Navigation in Card
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              OutlinedButton(
                onClick = {
                  if (selectedIndex > 0) {
                    selectedIndex--
                  } else {
                    selectedIndex = questions.size - 1
                  }
                  studentAnswer = ""
                  isEvaluated = false
                  evalResult = null
                },
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color(0xFF475569))
              ) {
                Text(
                  text = "⬅️ පෙර ප්‍රශ්නය",
                  fontSize = 11.sp,
                  color = Color(0xFFCBD5E1)
                )
              }

              Text(
                text = "Q ${selectedIndex + 1} / ${questions.size}",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF38BDF8)
              )

              Button(
                onClick = {
                  if (selectedIndex < questions.size - 1) {
                    selectedIndex++
                  } else {
                    selectedIndex = 0
                  }
                  studentAnswer = ""
                  isEvaluated = false
                  evalResult = null
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0369A1)),
                shape = RoundedCornerShape(8.dp)
              ) {
                Text(
                  text = "මීළඟ ප්‍රශ්නය ➔",
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

    // 6. Evaluation & Marking Scheme Result Card
    if (isEvaluated && evalResult != null && currentQ != null) {
      val result = evalResult!!
      item {
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
          border = BorderStroke(
            1.5.dp,
            if (result.scoreEarned == currentQ.maxMarks) Color(0xFF22C55E)
            else if (result.scoreEarned > 0) Color(0xFFF59E0B)
            else Color(0xFFEF4444)
          )
        ) {
          Column(modifier = Modifier.padding(14.dp)) {
            // Header: Status and Score
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                  if (result.scoreEarned == currentQ.maxMarks) "🎉" else if (result.scoreEarned > 0) "💡" else "⚠️",
                  fontSize = 20.sp
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = "ඇගයීම් ප්‍රතිඵලය:",
                  fontSize = 13.5.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color.White
                )
              }

              Surface(
                shape = RoundedCornerShape(8.dp),
                color = if (result.scoreEarned == currentQ.maxMarks) Color(0xFF16A34A)
                else if (result.scoreEarned > 0) Color(0xFFD97706)
                else Color(0xFFDC2626)
              ) {
                Text(
                  text = "ලකුණු: ${result.scoreEarned} / ${result.maxMarks} (${(result.matchRatio * 100).toInt()}%)",
                  fontSize = 11.5.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color.White,
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                )
              }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Feedback Message
            Text(
              text = result.feedbackSinhala,
              fontSize = 12.sp,
              fontWeight = FontWeight.SemiBold,
              color = if (result.scoreEarned == currentQ.maxMarks) Color(0xFF86EFAC)
              else if (result.scoreEarned > 0) Color(0xFFFDE68A)
              else Color(0xFFFCA5A5)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Key Points Analysis
            Text(
              text = "හඳුනාගත් මූලපද (Matched Keywords):",
              fontSize = 11.sp,
              fontWeight = FontWeight.SemiBold,
              color = Color(0xFF94A3B8)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
              horizontalArrangement = Arrangement.spacedBy(6.dp),
              modifier = Modifier.fillMaxWidth()
            ) {
              currentQ.keyPoints.forEach { kp ->
                val matched = result.matchedKeywords.contains(kp)
                Surface(
                  shape = RoundedCornerShape(6.dp),
                  color = if (matched) Color(0xFF16A34A).copy(alpha = 0.25f) else Color(0xFFDC2626).copy(alpha = 0.2f),
                  border = BorderStroke(
                    1.dp,
                    if (matched) Color(0xFF22C55E) else Color(0xFFEF4444).copy(alpha = 0.4f)
                  )
                ) {
                  Text(
                    text = "${if (matched) "✓" else "✕"} $kp",
                    fontSize = 10.5.sp,
                    color = if (matched) Color(0xFF86EFAC) else Color(0xFFFCA5A5),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                  )
                }
              }
            }

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = Color(0xFF334155))
            Spacer(modifier = Modifier.height(10.dp))

            // Official Marking Scheme
            Text(
              text = "📋 නිල ලකුණු දීමේ පටිපාටිය (Marking Scheme):",
              fontSize = 11.5.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFFFBBF24)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = currentQ.officialMarkingScheme,
              fontSize = 11.5.sp,
              color = Color(0xFFE2E8F0),
              lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Model Answer
            Text(
              text = "🌟 ආදර්ශ නිවැරදි පිළිතුර (Model Answer):",
              fontSize = 11.5.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF38BDF8)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = currentQ.sampleIdealAnswer,
              fontSize = 11.5.sp,
              color = Color(0xFFBAE6FD),
              lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Navigation Row: Prev & Next
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              OutlinedButton(
                onClick = {
                  if (selectedIndex > 0) {
                    selectedIndex--
                  } else {
                    selectedIndex = questions.size - 1
                  }
                  studentAnswer = ""
                  isEvaluated = false
                  evalResult = null
                },
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color(0xFF475569))
              ) {
                Text("⬅️ පෙර ප්‍රශ්නය", fontSize = 11.sp, color = Color(0xFFCBD5E1))
              }

              Button(
                onClick = {
                  if (selectedIndex < questions.size - 1) {
                    selectedIndex++
                  } else {
                    selectedIndex = 0
                  }
                  studentAnswer = ""
                  isEvaluated = false
                  evalResult = null
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0284C7)),
                shape = RoundedCornerShape(8.dp)
              ) {
                Text("මීළඟ ප්‍රශ්නය ➔", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
              }
            }
          }
        }
      }
    }
  }
}
// ==============================================================================
// 2. ⚡ INTERACTIVE FLASHCARD Q&A (Flip-Card Spaced Repetition)
// ==============================================================================

data class QaFlashcardItem(
  val id: Int,
  val subject: String,
  val category: String,
  val frontQuestion: String,
  val backAnswer: String,
  val examTip: String,
  val formulaOrFact: String = ""
)

@Composable
fun FlashcardQaSection() {
  val grades = listOf("9", "10", "11")
  var selectedGrade by remember { mutableStateOf("11") }

  val subjects = remember { ComprehensiveFlashcardRepository.availableSubjects }
  var selectedSubject by remember { mutableStateOf(subjects.first()) }
  var selectedSetNumber by remember { mutableStateOf<Int?>(1) }

  val setsList = remember(selectedGrade, selectedSubject) {
    ComprehensiveFlashcardRepository.getSetsForSubject(selectedGrade, selectedSubject)
  }

  val cards = remember(selectedGrade, selectedSubject, selectedSetNumber) {
    ComprehensiveFlashcardRepository.getFlashcards(selectedGrade, selectedSubject, selectedSetNumber)
  }

  var currentIdx by remember { mutableIntStateOf(0) }
  var isFlipped by remember { mutableStateOf(false) }
  var masteredCount by remember { mutableIntStateOf(0) }
  var reviewCount by remember { mutableIntStateOf(0) }

  LaunchedEffect(selectedGrade, selectedSubject, selectedSetNumber) {
    currentIdx = 0
    isFlipped = false
  }

  val activeCard = if (cards.isNotEmpty()) cards[currentIdx.coerceIn(0, cards.size - 1)] else null

  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(bottom = 40.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    Surface(
      shape = RoundedCornerShape(10.dp),
      color = Color(0xFF1E293B),
      border = BorderStroke(1.dp, Color(0xFF334155)),
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(3.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
      ) {
        grades.forEach { gr ->
          val isSel = selectedGrade == gr
          Surface(
            onClick = { selectedGrade = gr },
            shape = RoundedCornerShape(8.dp),
            color = if (isSel) Color(0xFFF59E0B) else Color.Transparent,
            modifier = Modifier.weight(1f)
          ) {
            Box(
              contentAlignment = Alignment.Center,
              modifier = Modifier.padding(vertical = 6.dp)
            ) {
              Text(
                text = "$gr ශ්‍රේණිය",
                fontSize = 11.5.sp,
                fontWeight = if (isSel) FontWeight.Bold else FontWeight.Medium,
                color = if (isSel) Color.Black else Color(0xFF94A3B8)
              )
            }
          }
        }
      }
    }

    // 2. Subject Filter Row
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(6.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      items(subjects) { subj ->
        FilterChip(
          selected = selectedSubject == subj,
          onClick = {
            selectedSubject = subj
            currentIdx = 0
            isFlipped = false
          },
          label = { Text(subj, fontSize = 11.sp, fontWeight = FontWeight.Bold) },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Color(0xFF0284C7),
            selectedLabelColor = Color.White
          )
        )
      }
    }

    // 3. Set Filter (කාණ්ඩ 20ක් - කාණ්ඩයකට කාඩ්පත් 20 බැගින්)
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(6.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      item {
        FilterChip(
          selected = selectedSetNumber == null,
          onClick = { selectedSetNumber = null },
          label = { Text("සියලු 400", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Color(0xFF8B5CF6),
            selectedLabelColor = Color.White
          )
        )
      }
      items((1..20).toList()) { setIdx ->
        val isSel = selectedSetNumber == setIdx
        val startNum = (setIdx - 1) * 20 + 1
        val endNum = setIdx * 20
        FilterChip(
          selected = isSel,
          onClick = { selectedSetNumber = setIdx },
          label = { Text("කාණ්ඩය $setIdx ($startNum-$endNum)", fontSize = 10.sp) },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Color(0xFFF59E0B),
            selectedLabelColor = Color.Black
          )
        )
      }
    }

    // Progress and stats bar
    Card(
      shape = RoundedCornerShape(12.dp),
      colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
      border = BorderStroke(1.dp, Color(0xFF334155))
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 12.dp, vertical = 7.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "කාඩ්පත: ${if (cards.isEmpty()) 0 else currentIdx + 1} / ${cards.size}",
            fontSize = 11.5.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFDE68A)
          )
          if (activeCard != null) {
            Text(
              text = "සමස්ත අංකය: ${activeCard.globalIndex} / 400",
              fontSize = 9.sp,
              color = Color(0xFF94A3B8)
            )
          }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = Color(0xFF16A34A).copy(alpha = 0.25f)
          ) {
            Text(
              text = "✅ මතකයි: $masteredCount",
              fontSize = 10.sp,
              color = Color(0xFF86EFAC),
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
            )
          }

          Surface(
            shape = RoundedCornerShape(6.dp),
            color = Color(0xFFDC2626).copy(alpha = 0.25f)
          ) {
            Text(
              text = "🔄 නැවත: $reviewCount",
              fontSize = 10.sp,
              color = Color(0xFFFCA5A5),
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
            )
          }
        }
      }
    }

    if (cards.isEmpty() || activeCard == null) {
      Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
        Text("මෙම කාණ්ඩය සඳහා කාඩ්පත් හමු නොවීය.", color = Color(0xFF94A3B8))
      }
    } else {
      // FLIP CARD (Interactive Surface)
      Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (!isFlipped) Color(0xFF1E293B) else Color(0xFF0F172A)
        ),
        border = BorderStroke(
          1.8.dp,
          if (!isFlipped) Color(0xFFF59E0B).copy(alpha = 0.8f) else Color(0xFF10B981).copy(alpha = 0.8f)
        ),
        modifier = Modifier
          .fillMaxWidth()
          .heightIn(min = 280.dp)
          .clickable { isFlipped = !isFlipped }
          .testTag("flashcard_surface")
      ) {
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
          verticalArrangement = Arrangement.SpaceBetween
        ) {
          // Top Card Meta
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Surface(
              shape = RoundedCornerShape(6.dp),
              color = Color(0xFFF59E0B).copy(alpha = 0.2f)
            ) {
              Text(
                text = "${activeCard.subject} • ${activeCard.grade} ශ්‍රේණිය • කාණ්ඩය ${activeCard.setNumber}",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFCD34D),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
              )
            }

            Surface(
              shape = RoundedCornerShape(6.dp),
              color = if (!isFlipped) Color(0xFF3B82F6).copy(alpha = 0.2f) else Color(0xFF10B981).copy(alpha = 0.2f)
            ) {
              Text(
                text = if (!isFlipped) "❓ ප්‍රශ්නය #${activeCard.cardIndexInSet}/20" else "💡 පිළිතුර & විවරණය",
                fontSize = 9.5.sp,
                fontWeight = FontWeight.Bold,
                color = if (!isFlipped) Color(0xFF93C5FD) else Color(0xFF6EE7B7),
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
              )
            }
          }

          // Center Content (Front or Back)
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 14.dp),
            contentAlignment = Alignment.Center
          ) {
            if (!isFlipped) {
              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (activeCard.unitCategory.isNotEmpty()) {
                  Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFF334155),
                    modifier = Modifier.padding(bottom = 10.dp)
                  ) {
                    Text(
                      text = "📖 ${activeCard.unitCategory}",
                      fontSize = 10.5.sp,
                      color = Color(0xFFCBD5E1),
                      modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                  }
                }
                Text(
                  text = activeCard.questionFront.replace(Regex("""^\[.*?\]\s*"""), ""),
                  fontSize = 15.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color.White,
                  textAlign = TextAlign.Center,
                  lineHeight = 23.sp
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                  text = "👆 පිළිතුර බැලීමට කාඩ්පත මත ඔබන්න (Tap to Flip)",
                  fontSize = 10.5.sp,
                  color = Color(0xFF94A3B8)
                )
              }
            } else {
              Column {
                Text(
                  text = "💡 නිල පිළිතුර:",
                  fontSize = 11.5.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF34D399)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                  text = activeCard.answerBack,
                  fontSize = 13.5.sp,
                  color = Color.White,
                  lineHeight = 20.sp
                )

                if (activeCard.formulaOrFact.isNotBlank()) {
                  Spacer(modifier = Modifier.height(8.dp))
                  Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFF1E293B),
                    border = BorderStroke(1.dp, Color(0xFF475569))
                  ) {
                    Text(
                      text = "📐 ${activeCard.formulaOrFact}",
                      fontSize = 11.sp,
                      fontWeight = FontWeight.SemiBold,
                      color = Color(0xFFFDE047),
                      modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                  }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                  text = "🎯 විභාග ඉඟිය: ${activeCard.explanationTip}",
                  fontSize = 10.5.sp,
                  color = Color(0xFFCBD5E1),
                  lineHeight = 15.sp
                )
              }
            }
          }

          // Bottom Tap Indicator
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
          ) {
            Text(
              text = if (!isFlipped) "🔄 Flip to Reveal Answer" else "🔄 Tap to Flip Back",
              fontSize = 10.sp,
              color = Color(0xFF64748B)
            )
          }
        }
      }

      // Action Buttons: Easy vs Hard & Next
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Button(
          onClick = {
            reviewCount++
            if (currentIdx < cards.size - 1) currentIdx++ else currentIdx = 0
            isFlipped = false
          },
          modifier = Modifier.weight(1f),
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC2626)),
          shape = RoundedCornerShape(10.dp)
        ) {
          Text("🔄 නැවත අවශ්‍යයි", fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }

        Button(
          onClick = {
            masteredCount++
            if (currentIdx < cards.size - 1) currentIdx++ else currentIdx = 0
            isFlipped = false
          },
          modifier = Modifier.weight(1f),
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF16A34A)),
          shape = RoundedCornerShape(10.dp)
        ) {
          Text("✅ දනිමි / මතකයි", fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }
      }
    }
  }
}

// ==============================================================================
// 3. 🎯 O/L DAILY MIXED 10 CHALLENGE
// ==============================================================================

data class DailyMixedQuestion(
  val id: Int,
  val subject: String,
  val question: String,
  val options: List<String>,
  val correctIndex: Int,
  val explanation: String
)

@Composable
fun DailyMixedChallengeSection() {
  val context = LocalContext.current
  val prefs = remember { context.getSharedPreferences("ol_daily_quiz_prefs", Context.MODE_PRIVATE) }

  var selectedDaySet by remember { mutableIntStateOf(1) } // 1..5
  val questions = remember(selectedDaySet) {
    val startIndex = ((selectedDaySet - 1) * 10).coerceIn(0, 40)
    DailyMixed50QuestionsBank.pool50.subList(startIndex, startIndex + 10)
  }

  var currentQIndex by remember(selectedDaySet) { mutableIntStateOf(0) }
  var selectedOption by remember(selectedDaySet, currentQIndex) { mutableStateOf<Int?>(null) }
  var userAnswers by remember(selectedDaySet) { mutableStateOf<Map<Int, Int>>(emptyMap()) }
  var isSubmitted by remember(selectedDaySet) { mutableStateOf(false) }
  var streak by remember { mutableIntStateOf(prefs.getInt("streak_days", 3)) }
  var highScore by remember { mutableIntStateOf(prefs.getInt("high_score", 8)) }

  if (!isSubmitted) {
    val q = questions[currentQIndex]

    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(bottom = 40.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        border = BorderStroke(1.dp, Color(0xFF10B981).copy(alpha = 0.5f))
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text("🔥", fontSize = 18.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Column {
              Text(
                text = "Daily Streak: $streak දින",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF34D399)
              )
              Text(
                text = "ඉහළම ලකුණු: $highScore/10",
                fontSize = 10.sp,
                color = Color(0xFF94A3B8)
              )
            }
          }

          Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color(0xFF10B981).copy(alpha = 0.2f)
          ) {
            Text(
              text = "ප්‍රශ්න ${currentQIndex + 1} / ${questions.size}",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF6EE7B7),
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
          }
        }
      }

      // Linear Progress Indicator
      LinearProgressIndicator(
        progress = { (currentQIndex + 1).toFloat() / questions.size.toFloat() },
        modifier = Modifier
          .fillMaxWidth()
          .height(6.dp)
          .clip(RoundedCornerShape(3.dp)),
        color = Color(0xFF10B981),
        trackColor = Color(0xFF334155)
      )

      // Question Card
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
        border = BorderStroke(1.dp, Color(0xFF334155))
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = Color(0xFF0D9488).copy(alpha = 0.25f)
          ) {
            Text(
              text = q.subject,
              fontSize = 10.5.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF2DD4BF),
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
            )
          }

          Spacer(modifier = Modifier.height(10.dp))
          Text(
            text = q.question,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            lineHeight = 20.sp
          )

          Spacer(modifier = Modifier.height(16.dp))

          // Options
          q.options.forEachIndexed { optIndex, optText ->
            val isSelected = selectedOption == optIndex
            Surface(
              shape = RoundedCornerShape(10.dp),
              color = if (isSelected) Color(0xFF065F46) else Color(0xFF1E293B),
              border = BorderStroke(
                1.dp,
                if (isSelected) Color(0xFF34D399) else Color(0xFF475569)
              ),
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable { selectedOption = optIndex }
            ) {
              Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Surface(
                  shape = CircleShape,
                  color = if (isSelected) Color(0xFF34D399) else Color(0xFF334155),
                  modifier = Modifier.size(24.dp)
                ) {
                  Box(contentAlignment = Alignment.Center) {
                    Text(
                      text = "${optIndex + 1}",
                      fontSize = 11.sp,
                      fontWeight = FontWeight.Bold,
                      color = if (isSelected) Color.Black else Color.White
                    )
                  }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                  text = optText,
                  fontSize = 12.5.sp,
                  color = Color.White
                )
              }
            }
          }
        }
      }

      // Next / Submit Button
      Button(
        onClick = {
          if (selectedOption != null) {
            val updated = userAnswers.toMutableMap()
            updated[currentQIndex] = selectedOption!!
            userAnswers = updated
            selectedOption = null

            if (currentQIndex < questions.size - 1) {
              currentQIndex++
            } else {
              isSubmitted = true
              // Save Streak and High score
              val correct = questions.count { userAnswers[it.id - 1] == it.correctIndex }
              if (correct > highScore) {
                highScore = correct
                prefs.edit().putInt("high_score", correct).apply()
              }
              val newStreak = streak + 1
              prefs.edit().putInt("streak_days", newStreak).apply()
              streak = newStreak
            }
          }
        },
        enabled = selectedOption != null,
        modifier = Modifier
          .fillMaxWidth()
          .testTag("daily_challenge_next_btn"),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
        shape = RoundedCornerShape(10.dp)
      ) {
        Text(
          text = if (currentQIndex < questions.size - 1) "මීළඟ ප්‍රශ්නය ➔" else "ප්‍රතිඵල බලන්න (Submit) 🏁",
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = Color.Black
        )
      }
    }
  } else {
    // Score Summary & Review
    val correctCount = questions.count { userAnswers[it.id - 1] == it.correctIndex }
    val percentage = (correctCount * 100) / questions.size

    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      item {
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF064E3B)),
          border = BorderStroke(1.5.dp, Color(0xFF10B981))
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Text("🎉 දිනපතා අභියෝගය සම්පූර්ණයි!", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = "$correctCount / ${questions.size}",
              fontSize = 32.sp,
              fontWeight = FontWeight.ExtraBold,
              color = Color(0xFF34D399)
            )
            Text(
              text = "නිරවද්‍යතාවය: $percentage% • Streak: $streak Days 🔥",
              fontSize = 12.sp,
              color = Color(0xFFA7F3D0)
            )

            Spacer(modifier = Modifier.height(12.dp))
            Button(
              onClick = {
                isSubmitted = false
                currentQIndex = 0
                userAnswers = emptyMap()
                selectedOption = null
              },
              colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
              shape = RoundedCornerShape(8.dp)
            ) {
              Text("නැවත කරන්න (Retake)", color = Color.Black, fontWeight = FontWeight.Bold)
            }
          }
        }
      }

      item {
        Text(
          text = "විවරණ සහ නිවැරදි පිළිතුරු (Detailed Solutions):",
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
      }

      itemsIndexed(questions) { idx, q ->
        val userAns = userAnswers[idx]
        val isCorrect = userAns == q.correctIndex

        Card(
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
          border = BorderStroke(1.dp, if (isCorrect) Color(0xFF16A34A) else Color(0xFFDC2626))
        ) {
          Column(modifier = Modifier.padding(12.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(
                text = "Q${idx + 1}. ${q.subject}",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF94A3B8)
              )
              Text(
                text = if (isCorrect) "✅ නිවැරදියි (+1)" else "❌ වැරදියි (0)",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = if (isCorrect) Color(0xFF86EFAC) else Color(0xFFFCA5A5)
              )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = q.question, fontSize = 12.5.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = "නිවැරදි පිළිතුර: ${q.options[q.correctIndex]}",
              fontSize = 11.5.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF34D399)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = "විවරණය: ${q.explanation}",
              fontSize = 11.sp,
              color = Color(0xFFCBD5E1)
            )
          }
        }
      }
    }
  }
}

// ==============================================================================
// 4. 🧩 MATCH THE FOLLOWING & TIMELINE ORDERING
// ==============================================================================

data class MatchItem(
  val leftId: Int,
  val leftText: String,
  val rightCorrectText: String
)

data class TimelineItem(
  val id: Int,
  val eventText: String,
  val eraYear: String,
  val correctOrder: Int
)

@Composable
fun MatchingAndOrderingSection() {
  var subTab by remember { mutableIntStateOf(0) } // 0 = Matching, 1 = Timeline

  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    // Sub-mode toggle
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Button(
        onClick = { subTab = 0 },
        modifier = Modifier.weight(1f),
        colors = ButtonDefaults.buttonColors(
          containerColor = if (subTab == 0) Color(0xFF8B5CF6) else Color(0xFF1E293B)
        ),
        shape = RoundedCornerShape(10.dp)
      ) {
        Text("🔗 ගැලපීම් (Match Columns)", fontSize = 11.sp, fontWeight = FontWeight.Bold)
      }

      Button(
        onClick = { subTab = 1 },
        modifier = Modifier.weight(1f),
        colors = ButtonDefaults.buttonColors(
          containerColor = if (subTab == 1) Color(0xFF8B5CF6) else Color(0xFF1E293B)
        ),
        shape = RoundedCornerShape(10.dp)
      ) {
        Text("⏳ කාලරේඛා පෙළගැස්ම (Timeline)", fontSize = 11.sp, fontWeight = FontWeight.Bold)
      }
    }

    if (subTab == 0) {
      MatchColumnsSubView()
    } else {
      TimelineOrderingSubView()
    }
  }
}

@Composable
fun MatchColumnsSubView() {
  val matchSets = InteractiveMatchingAndTimelineBank.matchSets
  var selectedSetIndex by remember { mutableIntStateOf(0) }
  val currentMatchSet = matchSets[selectedSetIndex]
  val matchData = currentMatchSet.items

  val shuffledRight = remember(selectedSetIndex) { matchData.map { it.rightCorrectText }.shuffled() }
  var selectedLeft by remember(selectedSetIndex) { mutableStateOf<String?>(null) }
  var studentPairs by remember(selectedSetIndex) { mutableStateOf<Map<String, String>>(emptyMap()) }
  var isChecked by remember(selectedSetIndex) { mutableStateOf(false) }

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(bottom = 40.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    item {
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        border = BorderStroke(1.dp, Color(0xFF8B5CF6).copy(alpha = 0.5f))
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text(
            text = "🔗 ${currentMatchSet.icon} ${currentMatchSet.subject} - ${currentMatchSet.title}",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "පළමුව වම් පසින් කරුණක් තෝරා, පසුව දකුණු පසින් අදාළ නිවැරදි ගැලපීම මත ඔබන්න.",
            fontSize = 11.sp,
            color = Color(0xFFCBD5E1)
          )
          Spacer(modifier = Modifier.height(8.dp))

          // Subject Chips
          LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            itemsIndexed(matchSets) { idx, s ->
              val isSel = idx == selectedSetIndex
              Surface(
                onClick = {
                  selectedSetIndex = idx
                  selectedLeft = null
                  studentPairs = emptyMap()
                  isChecked = false
                },
                shape = RoundedCornerShape(8.dp),
                color = if (isSel) Color(0xFF8B5CF6) else Color(0xFF0F172A),
                border = BorderStroke(1.dp, if (isSel) Color(0xFFC4B5FD) else Color(0xFF334155))
              ) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                ) {
                  Text(s.icon, fontSize = 11.sp)
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = s.subject,
                    fontSize = 10.5.sp,
                    fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSel) Color.White else Color(0xFFCBD5E1)
                  )
                }
              }
            }
          }
        }
      }
    }

    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        // Left Column
        Column(
          modifier = Modifier.weight(1f),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Text("A තීරුව (උපකරණය):", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFC4B5FD))
          matchData.forEach { item ->
            val isSelected = selectedLeft == item.leftText
            val pairedWith = studentPairs[item.leftText]
            val isCorrect = pairedWith == item.rightCorrectText

            Surface(
              shape = RoundedCornerShape(8.dp),
              color = when {
                isChecked && isCorrect -> Color(0xFF065F46)
                isChecked && pairedWith != null && !isCorrect -> Color(0xFF7F1D1D)
                isSelected -> Color(0xFF6D28D9)
                pairedWith != null -> Color(0xFF4C1D95)
                else -> Color(0xFF1E293B)
              },
              border = BorderStroke(
                1.dp,
                if (isSelected) Color(0xFFA78BFA) else Color(0xFF475569)
              ),
              modifier = Modifier
                .fillMaxWidth()
                .clickable { selectedLeft = item.leftText }
            ) {
              Column(modifier = Modifier.padding(8.dp)) {
                Text(text = item.leftText, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                if (pairedWith != null) {
                  Text(
                    text = "➔ $pairedWith",
                    fontSize = 9.5.sp,
                    color = if (isChecked) {
                      if (isCorrect) Color(0xFF86EFAC) else Color(0xFFFCA5A5)
                    } else Color(0xFFE9D5FF)
                  )
                }
              }
            }
          }
        }

        // Right Column
        Column(
          modifier = Modifier.weight(1f),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Text("B තීරුව (කාර්යය):", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFC4B5FD))
          shuffledRight.forEach { rightText ->
            val isAlreadyPaired = studentPairs.values.contains(rightText)

            Surface(
              shape = RoundedCornerShape(8.dp),
              color = if (isAlreadyPaired) Color(0xFF334155) else Color(0xFF1E293B),
              border = BorderStroke(1.dp, Color(0xFF475569)),
              modifier = Modifier
                .fillMaxWidth()
                .clickable {
                  if (selectedLeft != null) {
                    val updated = studentPairs.toMutableMap()
                    updated[selectedLeft!!] = rightText
                    studentPairs = updated
                    selectedLeft = null
                    isChecked = false
                  }
                }
            ) {
              Text(
                text = rightText,
                fontSize = 11.sp,
                color = if (isAlreadyPaired) Color(0xFF94A3B8) else Color.White,
                modifier = Modifier.padding(8.dp)
              )
            }
          }
        }
      }
    }

    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Button(
          onClick = { isChecked = true },
          enabled = studentPairs.size == matchData.size,
          modifier = Modifier.weight(1f),
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B5CF6)),
          shape = RoundedCornerShape(8.dp)
        ) {
          Text("පරීක්ෂා කරන්න (Check)", fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
        }

        OutlinedButton(
          onClick = {
            studentPairs = emptyMap()
            selectedLeft = null
            isChecked = false
          },
          shape = RoundedCornerShape(8.dp),
          colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFCBD5E1))
        ) {
          Text("Reset", fontSize = 11.sp)
        }
      }
    }
  }
}

@Composable
fun TimelineOrderingSubView() {
  val timelineSets = InteractiveMatchingAndTimelineBank.timelineSets
  var selectedTimelineIndex by remember { mutableIntStateOf(0) }
  val currentTimelineSet = timelineSets[selectedTimelineIndex]

  var currentList by remember(selectedTimelineIndex) { mutableStateOf(currentTimelineSet.items.shuffled()) }
  var isChecked by remember(selectedTimelineIndex) { mutableStateOf(false) }

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(bottom = 40.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    item {
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        border = BorderStroke(1.dp, Color(0xFF8B5CF6).copy(alpha = 0.5f))
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text(
            text = "⏳ ${currentTimelineSet.icon} ${currentTimelineSet.subject} - ${currentTimelineSet.title}",
            fontSize = 12.5.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "ඉහළ/පහළ ඊතල (▲ / ▼) භාවිතයෙන් සිදුවීම් මුල සිට අගට නිවැරදි අනුපිළිවෙලට සකසන්න.",
            fontSize = 11.sp,
            color = Color(0xFFCBD5E1)
          )
          Spacer(modifier = Modifier.height(8.dp))

          // Subject Chips
          LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            itemsIndexed(timelineSets) { idx, t ->
              val isSel = idx == selectedTimelineIndex
              Surface(
                onClick = {
                  selectedTimelineIndex = idx
                  isChecked = false
                },
                shape = RoundedCornerShape(8.dp),
                color = if (isSel) Color(0xFF8B5CF6) else Color(0xFF0F172A),
                border = BorderStroke(1.dp, if (isSel) Color(0xFFC4B5FD) else Color(0xFF334155))
              ) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                ) {
                  Text(t.icon, fontSize = 11.sp)
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = t.subject,
                    fontSize = 10.5.sp,
                    fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSel) Color.White else Color(0xFFCBD5E1)
                  )
                }
              }
            }
          }
        }
      }
    }

    itemsIndexed(currentList) { idx, item ->
      val isCorrectOrder = item.correctOrder == (idx + 1)

      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (isChecked) {
            if (isCorrectOrder) Color(0xFF064E3B) else Color(0xFF7F1D1D)
          } else Color(0xFF0F172A)
        ),
        border = BorderStroke(
          1.dp,
          if (isChecked) {
            if (isCorrectOrder) Color(0xFF10B981) else Color(0xFFEF4444)
          } else Color(0xFF334155)
        )
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Surface(
              shape = CircleShape,
              color = Color(0xFF8B5CF6),
              modifier = Modifier.size(24.dp)
            ) {
              Box(contentAlignment = Alignment.Center) {
                Text("${idx + 1}", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
              }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(item.eventText, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
              if (isChecked) {
                Text("වකවානුව: ${item.eraYear}", fontSize = 10.sp, color = Color(0xFFE9D5FF))
              }
            }
          }

          // Move Up / Down
          Row {
            IconButton(
              onClick = {
                if (idx > 0) {
                  val list = currentList.toMutableList()
                  val temp = list[idx]
                  list[idx] = list[idx - 1]
                  list[idx - 1] = temp
                  currentList = list
                  isChecked = false
                }
              },
              enabled = idx > 0
            ) {
              Text("▲", fontSize = 14.sp, color = if (idx > 0) Color.White else Color(0xFF475569))
            }

            IconButton(
              onClick = {
                if (idx < currentList.size - 1) {
                  val list = currentList.toMutableList()
                  val temp = list[idx]
                  list[idx] = list[idx + 1]
                  list[idx + 1] = temp
                  currentList = list
                  isChecked = false
                }
              },
              enabled = idx < currentList.size - 1
            ) {
              Text("▼", fontSize = 14.sp, color = if (idx < currentList.size - 1) Color.White else Color(0xFF475569))
            }
          }
        }
      }
    }

    item {
      Button(
        onClick = { isChecked = true },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B5CF6)),
        shape = RoundedCornerShape(8.dp)
      ) {
        Text("අනුපිළිවෙල පරීක්ෂා කරන්න (Check Timeline)", fontSize = 12.sp, fontWeight = FontWeight.Bold)
      }
    }
  }
}

// ==============================================================================
// 5. ❌/✔️ TRUE/FALSE WITH DEEP JUSTIFICATION
// ==============================================================================

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TrueFalseWithJustificationSection() {
  val context = LocalContext.current

  // State: Grade selection (9, 10, 11)
  var selectedGrade by remember { mutableIntStateOf(11) }

  // State: Subject selection
  var selectedSubject by remember { mutableStateOf(TrueFalseSubject.SCIENCE) }

  // State: Batch selection (0 to 16, i.e., 17 batches of 30 questions for total 500 questions)
  var selectedBatchIndex by remember { mutableIntStateOf(0) }

  // State: Question index within current batch (0 until batch size)
  var questionIndexInBatch by remember { mutableIntStateOf(0) }

  // Filter: mistakes only
  var filterOnlyMistakes by remember { mutableStateOf(false) }

  // User answers map: Key = question.id ("tf_g11_sci_q42"), Value = chosen Boolean
  var userAnswers by remember { mutableStateOf<Map<String, Boolean>>(emptyMap()) }

  // TTS for reading statements
  var tts by remember { mutableStateOf<TextToSpeech?>(null) }
  DisposableEffect(Unit) {
    var ttsEngine: TextToSpeech? = null
    ttsEngine = TextToSpeech(context) { status ->
      if (status == TextToSpeech.SUCCESS) {
        ttsEngine?.language = Locale("si", "LK")
      }
    }
    tts = ttsEngine
    onDispose {
      ttsEngine?.stop()
      ttsEngine?.shutdown()
    }
  }

  // Load questions for selected batch
  val batchQuestions = remember(selectedGrade, selectedSubject, selectedBatchIndex) {
    TrueFalse500Repository.getQuestionsForBatch(selectedGrade, selectedSubject, selectedBatchIndex)
  }

  // Filtered list if mistakes only is on
  val displayedQuestions = remember(batchQuestions, filterOnlyMistakes, userAnswers) {
    if (!filterOnlyMistakes) {
      batchQuestions
    } else {
      batchQuestions.filter { q ->
        val answered = userAnswers[q.id]
        answered != null && answered != q.isTrue
      }
    }
  }

  // Ensure current question index is clamped
  val effectiveIndex = if (displayedQuestions.isEmpty()) 0 else questionIndexInBatch.coerceIn(0, displayedQuestions.size - 1)
  val activeQ = displayedQuestions.getOrNull(effectiveIndex)

  // Current batch stats
  val answeredInBatch = batchQuestions.count { userAnswers.containsKey(it.id) }
  val correctInBatch = batchQuestions.count { userAnswers[it.id] == it.isTrue }
  val batchRange = TrueFalse500Repository.getBatchRange(selectedBatchIndex)

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .testTag("true_false_master_section"),
    contentPadding = PaddingValues(bottom = 40.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    // 1. Grade Selector Banner (9 / 10 / 11)
    item {
      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        border = BorderStroke(1.dp, Color(0xFFEC4899).copy(alpha = 0.6f))
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text("⚖️", fontSize = 18.sp)
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "සත්‍ය / අසත්‍ය පුහුණුව • විෂයකට ප්‍රශ්න 500",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
            }
            Surface(
              shape = RoundedCornerShape(6.dp),
              color = Color(0xFFEC4899).copy(alpha = 0.25f)
            ) {
              Text(
                text = "Grade $selectedGrade",
                fontSize = 11.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFF472B6),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(10.dp))

          // Grade Selection Buttons (9, 10, 11)
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            listOf(9, 10, 11).forEach { grade ->
              val isSelected = selectedGrade == grade
              OutlinedButton(
                onClick = {
                  selectedGrade = grade
                  selectedBatchIndex = 0
                  questionIndexInBatch = 0
                },
                modifier = Modifier
                  .weight(1f)
                  .testTag("grade_btn_$grade"),
                colors = ButtonDefaults.outlinedButtonColors(
                  containerColor = if (isSelected) Color(0xFFEC4899) else Color(0xFF0F172A),
                  contentColor = if (isSelected) Color.White else Color(0xFFE2E8F0)
                ),
                border = BorderStroke(
                  1.5.dp,
                  if (isSelected) Color(0xFFF472B6) else Color(0xFF334155)
                ),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(vertical = 6.dp)
              ) {
                Text(
                  text = "$grade ශ්‍රේණිය",
                  fontSize = 12.sp,
                  fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium
                )
              }
            }
          }
        }
      }
    }

    // 2. Subject Selection Chips
    item {
      Column {
        Text(
          text = "විෂය තෝරන්න (Select Subject):",
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF94A3B8),
          modifier = Modifier.padding(bottom = 6.dp)
        )
        LazyRow(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          contentPadding = PaddingValues(horizontal = 2.dp)
        ) {
          items(TrueFalseSubject.values()) { subject ->
            val isSelected = selectedSubject == subject
            Surface(
              shape = RoundedCornerShape(10.dp),
              color = if (isSelected) subject.primaryColor else Color(0xFF1E293B),
              border = BorderStroke(
                1.dp,
                if (isSelected) Color.White else Color(0xFF334155)
              ),
              modifier = Modifier
                .clickable {
                  selectedSubject = subject
                  selectedBatchIndex = 0
                  questionIndexInBatch = 0
                }
                .testTag("subject_chip_${subject.id}")
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(subject.iconEmoji, fontSize = 14.sp)
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                  text = subject.displayName,
                  fontSize = 11.sp,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                  color = if (isSelected) Color.White else Color(0xFFCBD5E1)
                )
              }
            }
          }
        }
      }
    }

    // 3. Batch Selection Chips (17 Batches of 30 questions)
    item {
      Column {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "තිහේ කාණ්ඩය තෝරන්න (30 Questions Batch):",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF94A3B8)
          )
          Text(
            text = "කාණ්ඩ 17 න් #${selectedBatchIndex + 1}",
            fontSize = 10.5.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFF472B6)
          )
        }
        Spacer(modifier = Modifier.height(6.dp))
        LazyRow(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          contentPadding = PaddingValues(horizontal = 2.dp)
        ) {
          items(TrueFalse500Repository.TOTAL_BATCHES) { batchIdx ->
            val isSelected = selectedBatchIndex == batchIdx
            val (start, end) = TrueFalse500Repository.getBatchRange(batchIdx)
            val batchCount = (start..end).count { qNum ->
              userAnswers.containsKey("tf_g${selectedGrade}_${selectedSubject.id}_q$qNum")
            }
            val isCompleted = batchCount == (end - start + 1)

            Surface(
              shape = RoundedCornerShape(10.dp),
              color = if (isSelected) Color(0xFF831843) else Color(0xFF1E293B),
              border = BorderStroke(
                1.5.dp,
                if (isSelected) Color(0xFFF472B6) else if (isCompleted) Color(0xFF10B981) else Color(0xFF334155)
              ),
              modifier = Modifier
                .clickable {
                  selectedBatchIndex = batchIdx
                  questionIndexInBatch = 0
                }
                .testTag("batch_chip_$batchIdx")
            ) {
              Column(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                Text(
                  text = "කාණ්ඩය ${batchIdx + 1}",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = if (isSelected) Color.White else Color(0xFFE2E8F0)
                )
                Text(
                  text = "ප්‍රශ්න $start - $end",
                  fontSize = 9.sp,
                  color = if (isSelected) Color(0xFFFCE7F3) else Color(0xFF94A3B8)
                )
                if (batchCount > 0) {
                  Text(
                    text = if (isCompleted) "සම්පූර්ණයි ✅" else "$batchCount/${end - start + 1}",
                    fontSize = 8.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isCompleted) Color(0xFF4ADE80) else Color(0xFFFBBF24)
                  )
                }
              }
            }
          }
        }
      }
    }

    // 4. Batch Progress & Controls Bar
    item {
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
        border = BorderStroke(1.dp, Color(0xFF334155))
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "කාණ්ඩයේ ප්‍රගතිය: $answeredInBatch / ${batchQuestions.size} පිළිතුරු",
              fontSize = 10.5.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
            Text(
              text = "ලකුණු: $correctInBatch • නිරවද්‍යතාව: ${if (answeredInBatch > 0) (correctInBatch * 100) / answeredInBatch else 0}%",
              fontSize = 9.5.sp,
              color = Color(0xFF38BDF8)
            )
          }

          Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            // Filter mistakes
            FilterChip(
              selected = filterOnlyMistakes,
              onClick = { filterOnlyMistakes = !filterOnlyMistakes },
              label = { Text("වැරදුණු ඒවා", fontSize = 9.5.sp) },
              colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color(0xFFEF4444),
                selectedLabelColor = Color.White
              ),
              modifier = Modifier.testTag("filter_mistakes_chip")
            )

            // Reset batch
            IconButton(
              onClick = {
                val newAnswers = userAnswers.toMutableMap()
                batchQuestions.forEach { newAnswers.remove(it.id) }
                userAnswers = newAnswers
                questionIndexInBatch = 0
              },
              modifier = Modifier.size(32.dp).testTag("reset_batch_btn")
            ) {
              Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Reset Batch",
                tint = Color(0xFF94A3B8),
                modifier = Modifier.size(18.dp)
              )
            }
          }
        }
      }
    }

    // 5. Active Question Card
    if (activeQ != null) {
      val userAnswer = userAnswers[activeQ.id]
      val hasAnswered = userAnswer != null
      val isCorrect = hasAnswered && userAnswer == activeQ.isTrue

      item {
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
          border = BorderStroke(
            1.5.dp,
            if (!hasAnswered) Color(0xFF475569) else if (isCorrect) Color(0xFF22C55E) else Color(0xFFEF4444)
          )
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            // Top badges
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Surface(
                shape = RoundedCornerShape(6.dp),
                color = selectedSubject.primaryColor.copy(alpha = 0.2f)
              ) {
                Text(
                  text = "${selectedSubject.displayName} • ${activeQ.unitName}",
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold,
                  color = selectedSubject.primaryColor,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
              }

              Surface(
                shape = RoundedCornerShape(6.dp),
                color = Color(0xFF334155)
              ) {
                Text(
                  text = "ප්‍රශ්න අංක ${effectiveIndex + 1} / ${displayedQuestions.size} (මුළු #${activeQ.number})",
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFFCBD5E1),
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Statement with TTS button
            Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.Top
            ) {
              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = "ප්‍රකාශය (Statement):",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = Color(0xFF94A3B8)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                  text = "\"${activeQ.statement}\"",
                  fontSize = 14.5.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color.White,
                  lineHeight = 22.sp
                )
              }

              IconButton(
                onClick = {
                  try {
                    tts?.speak(activeQ.statement, TextToSpeech.QUEUE_FLUSH, null, "tf_tts")
                  } catch (_: Exception) {}
                },
                modifier = Modifier
                  .padding(start = 4.dp)
                  .testTag("tts_play_btn")
              ) {
                Icon(
                  imageVector = Icons.Default.VolumeUp,
                  contentDescription = "Read Statement Aloud",
                  tint = Color(0xFF38BDF8)
                )
              }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // True / False Buttons
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              // TRUE BUTTON
              Button(
                onClick = {
                  userAnswers = userAnswers + (activeQ.id to true)
                },
                modifier = Modifier
                  .weight(1f)
                  .testTag("true_btn_${activeQ.id}"),
                colors = ButtonDefaults.buttonColors(
                  containerColor = if (userAnswer == true) {
                    if (activeQ.isTrue) Color(0xFF16A34A) else Color(0xFFDC2626)
                  } else Color(0xFF0F172A)
                ),
                border = BorderStroke(
                  1.5.dp,
                  if (userAnswer == true) Color(0xFF4ADE80) else Color(0xFF475569)
                ),
                shape = RoundedCornerShape(10.dp)
              ) {
                Text(
                  text = "✔️ සත්‍ය වේ (True)",
                  fontSize = 12.5.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color.White
                )
              }

              // FALSE BUTTON
              Button(
                onClick = {
                  userAnswers = userAnswers + (activeQ.id to false)
                },
                modifier = Modifier
                  .weight(1f)
                  .testTag("false_btn_${activeQ.id}"),
                colors = ButtonDefaults.buttonColors(
                  containerColor = if (userAnswer == false) {
                    if (!activeQ.isTrue) Color(0xFF16A34A) else Color(0xFFDC2626)
                  } else Color(0xFF0F172A)
                ),
                border = BorderStroke(
                  1.5.dp,
                  if (userAnswer == false) Color(0xFFF87171) else Color(0xFF475569)
                ),
                shape = RoundedCornerShape(10.dp)
              ) {
                Text(
                  text = "❌ අසත්‍ය වේ (False)",
                  fontSize = 12.5.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color.White
                )
              }
            }
          }
        }
      }

      // 6. Justification Explanation Box (Appears after answer)
      if (hasAnswered) {
        item {
          Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
              containerColor = if (isCorrect) Color(0xFF064E3B) else Color(0xFF450A0A)
            ),
            border = BorderStroke(
              1.5.dp,
              if (isCorrect) Color(0xFF22C55E) else Color(0xFFEF4444)
            )
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = if (isCorrect) "🎉 ඔබේ තීරණය 100% නිවැරදියි!" else "💡 ඔබේ තීරණය වැරදියි!",
                  fontSize = 13.sp,
                  fontWeight = FontWeight.ExtraBold,
                  color = if (isCorrect) Color(0xFF86EFAC) else Color(0xFFFCA5A5)
                )
                Surface(
                  shape = RoundedCornerShape(6.dp),
                  color = if (activeQ.isTrue) Color(0xFF16A34A) else Color(0xFFDC2626)
                ) {
                  Text(
                    text = if (activeQ.isTrue) "නිවැරදි පිළිතුර: සත්‍ය වේ" else "නිවැරදි පිළිතුර: අසත්‍ය වේ",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                  )
                }
              }

              Spacer(modifier = Modifier.height(10.dp))
              Text(
                text = "🔍 විද්‍යාත්මක / න්‍යායාත්මක හේතුව (Justification):",
                fontSize = 11.5.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFBBF24)
              )
              Spacer(modifier = Modifier.height(3.dp))
              Text(
                text = activeQ.justification,
                fontSize = 12.sp,
                color = Color(0xFFF1F5F9),
                lineHeight = 18.sp
              )

              Spacer(modifier = Modifier.height(10.dp))
              Text(
                text = "⚠️ විභාග අනතුරු ඇඟවීම / Exam Trap: ${activeQ.examTrapOrFact}",
                fontSize = 11.sp,
                color = Color(0xFFFDA4AF),
                lineHeight = 16.sp
              )
            }
          }
        }
      }

      // 7. Navigation Controls (Previous / Next)
      item {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          OutlinedButton(
            onClick = {
              if (effectiveIndex > 0) {
                questionIndexInBatch = effectiveIndex - 1
              }
            },
            enabled = effectiveIndex > 0,
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFF475569)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.testTag("prev_q_btn")
          ) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous", modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("පෙර ප්‍රශ්නය", fontSize = 11.sp)
          }

          Button(
            onClick = {
              if (effectiveIndex < displayedQuestions.size - 1) {
                questionIndexInBatch = effectiveIndex + 1
              } else if (selectedBatchIndex < TrueFalse500Repository.TOTAL_BATCHES - 1) {
                selectedBatchIndex++
                questionIndexInBatch = 0
              }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEC4899)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.testTag("next_q_btn")
          ) {
            Text(
              text = if (effectiveIndex < displayedQuestions.size - 1) "මීළඟ ප්‍රශ්නය" else "ඊළඟ කාණ්ඩයට ➔",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next", modifier = Modifier.size(16.dp))
          }
        }
      }

      // 8. Question Jump Grid (1 to 30 circle chips)
      item {
        Card(
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
          border = BorderStroke(1.dp, Color(0xFF334155))
        ) {
          Column(modifier = Modifier.padding(12.dp)) {
            Text(
              text = "ප්‍රශ්න ලැයිස්තුව (කාණ්ඩය #${selectedBatchIndex + 1}):",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF94A3B8)
            )
            Spacer(modifier = Modifier.height(8.dp))

            FlowRow(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(6.dp),
              verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              displayedQuestions.forEachIndexed { idx, q ->
                val ans = userAnswers[q.id]
                val isCurrent = idx == effectiveIndex
                val circleBg = when {
                  ans == null -> Color(0xFF0F172A)
                  ans == q.isTrue -> Color(0xFF16A34A)
                  else -> Color(0xFFDC2626)
                }

                Box(
                  modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(circleBg)
                    .border(
                      width = if (isCurrent) 2.dp else 1.dp,
                      color = if (isCurrent) Color(0xFFEC4899) else Color(0xFF475569),
                      shape = CircleShape
                    )
                    .clickable {
                      questionIndexInBatch = idx
                    },
                  contentAlignment = Alignment.Center
                ) {
                  Text(
                    text = "${idx + 1}",
                    fontSize = 10.sp,
                    fontWeight = if (isCurrent) FontWeight.ExtraBold else FontWeight.Normal,
                    color = Color.White
                  )
                }
              }
            }
          }
        }
      }
    } else {
      // Empty state if filtering mistakes
      item {
        Card(
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
          modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)
        ) {
          Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Text("🎉", fontSize = 36.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = "මෙම කාණ්ඩයේ වැරදුණු ප්‍රශ්න කිසිවක් නැත!",
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = "සියලු ප්‍රශ්න බැලීමට 'වැරදුණු ඒවා' ෆිල්ටරය අක්‍රිය කරන්න.",
              fontSize = 11.sp,
              color = Color(0xFF94A3B8)
            )
          }
        }
      }
    }
  }
}

// ==============================================================================
// 6. 📝 CUSTOM SELF-QUIZ MAKER (Student's Personal Q&A Bank)
// ==============================================================================

data class CustomStudentQuestion(
  val id: Long,
  val question: String,
  val answer: String,
  val subject: String,
  val dateAdded: String
)

@Composable
fun CustomQuizMakerSection() {
  val context = LocalContext.current
  val prefs = remember { context.getSharedPreferences("ol_custom_quizzes", Context.MODE_PRIVATE) }

  // Load saved custom questions
  fun loadQuestions(): List<CustomStudentQuestion> {
    val raw = prefs.getString("custom_q_list", null) ?: return listOf(
      CustomStudentQuestion(
        id = 1L,
        question = "රසායනික බන්ධන ප්‍රධාන වර්ග 2 මොනවාද?",
        answer = "සහසංයුජ බන්ධන සහ අයනික බන්ධන.",
        subject = "විද්‍යාව",
        dateAdded = "අද"
      ),
      CustomStudentQuestion(
        id = 2L,
        question = "ලක්දිව පළමු විදේශීය ආක්‍රමණය කළ සේන සහ ගුත්තික පැමිණියේ කොහෙන්ද?",
        answer = "දකුණු ඉන්දියාවෙන් (අශ්ව වෙළඳුන් ලෙස).",
        subject = "ඉතිහාසය",
        dateAdded = "ඊයේ"
      )
    )
    return try {
      val arr = JSONArray(raw)
      val list = mutableListOf<CustomStudentQuestion>()
      for (i in 0 until arr.length()) {
        val obj = arr.getJSONObject(i)
        list.add(
          CustomStudentQuestion(
            id = obj.getLong("id"),
            question = obj.getString("q"),
            answer = obj.getString("a"),
            subject = obj.getString("s"),
            dateAdded = obj.getString("d")
          )
        )
      }
      list
    } catch (e: Exception) {
      emptyList()
    }
  }

  fun saveQuestions(list: List<CustomStudentQuestion>) {
    val arr = JSONArray()
    list.forEach { item ->
      val obj = JSONObject()
      obj.put("id", item.id)
      obj.put("q", item.question)
      obj.put("a", item.answer)
      obj.put("s", item.subject)
      obj.put("d", item.dateAdded)
      arr.put(obj)
    }
    prefs.edit().putString("custom_q_list", arr.toString()).apply()
  }

  var questionsList by remember { mutableStateOf(loadQuestions()) }
  var isAddingNew by remember { mutableStateOf(false) }
  var newQText by remember { mutableStateOf("") }
  var newAText by remember { mutableStateOf("") }
  var newSubject by remember { mutableStateOf("විද්‍යාව") }
  var testModeIndex by remember { mutableStateOf<Int?>(null) }
  var revealAnswer by remember { mutableStateOf(false) }

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(bottom = 40.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    item {
      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        border = BorderStroke(1.dp, Color(0xFF14B8A6).copy(alpha = 0.5f))
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text("📝", fontSize = 18.sp)
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "මගේම ප්‍රශ්න බැංකුව (Self-Quiz Maker)",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
            }

            Surface(
              shape = RoundedCornerShape(6.dp),
              color = Color(0xFF14B8A6).copy(alpha = 0.25f)
            ) {
              Text(
                text = "එකතුව: ${questionsList.size}",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2DD4BF),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "පන්තියෙන්, ටියුෂන් වලින් හෝ පාඩම් පොතෙන් ලැබෙන විශේෂ ප්‍රශ්න මෙහි ඇතුළත් කර ස්වයං පරීක්ෂණ පවත්වන්න.",
            fontSize = 11.sp,
            color = Color(0xFF94A3B8)
          )

          Spacer(modifier = Modifier.height(10.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Button(
              onClick = {
                isAddingNew = !isAddingNew
                testModeIndex = null
              },
              modifier = Modifier.weight(1f),
              colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D9488)),
              shape = RoundedCornerShape(8.dp)
            ) {
              Text(if (!isAddingNew) "+ අලුත් ප්‍රශ්නයක්" else "වසන්න ✕", fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
            }

            if (questionsList.isNotEmpty()) {
              Button(
                onClick = {
                  testModeIndex = if (testModeIndex == null) 0 else null
                  isAddingNew = false
                  revealAnswer = false
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0284C7)),
                shape = RoundedCornerShape(8.dp)
              ) {
                Text(
                  text = if (testModeIndex == null) "🎯 Self-Test කරන්න" else "Test අවසන්",
                  fontSize = 11.5.sp,
                  fontWeight = FontWeight.Bold
                )
              }
            }
          }
        }
      }
    }

    // Add New Question Form
    if (isAddingNew) {
      item {
        Card(
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
          border = BorderStroke(1.dp, Color(0xFF14B8A6))
        ) {
          Column(modifier = Modifier.padding(14.dp)) {
            Text("නව ප්‍රශ්නයක් ඇතුළත් කිරීම:", fontSize = 12.5.sp, fontWeight = FontWeight.Bold, color = Color.White)

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
              value = newQText,
              onValueChange = { newQText = it },
              placeholder = { Text("ප්‍රශ්නය ඇතුළත් කරන්න...", fontSize = 11.sp) },
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
              value = newAText,
              onValueChange = { newAText = it },
              placeholder = { Text("නිවැරදි පිළිතුර / සටහන...", fontSize = 11.sp) },
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text("විෂය:", fontSize = 11.sp, color = Color(0xFF94A3B8))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
              val subjs = listOf("විද්‍යාව", "ඉතිහාසය", "ගණිතය", "සිංහල", "බුද්ධ ධර්මය", "ICT", "වෙනත්")
              items(subjs) { s ->
                FilterChip(
                  selected = newSubject == s,
                  onClick = { newSubject = s },
                  label = { Text(s, fontSize = 10.sp) }
                )
              }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Button(
              onClick = {
                if (newQText.isNotBlank() && newAText.isNotBlank()) {
                  val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                  val newItem = CustomStudentQuestion(
                    id = System.currentTimeMillis(),
                    question = newQText.trim(),
                    answer = newAText.trim(),
                    subject = newSubject,
                    dateAdded = sdf.format(Date())
                  )
                  val updated = questionsList + newItem
                  questionsList = updated
                  saveQuestions(updated)
                  newQText = ""
                  newAText = ""
                  isAddingNew = false
                }
              },
              enabled = newQText.isNotBlank() && newAText.isNotBlank(),
              modifier = Modifier.fillMaxWidth(),
              colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF14B8A6)),
              shape = RoundedCornerShape(8.dp)
            ) {
              Text("සුරකින්න (Save Question)", fontWeight = FontWeight.Bold, fontSize = 11.5.sp)
            }
          }
        }
      }
    }

    // Active Self-Test Flash Mode
    if (testModeIndex != null && questionsList.isNotEmpty()) {
      val testQ = questionsList[testModeIndex!!]

      item {
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
          border = BorderStroke(2.dp, Color(0xFF0284C7))
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Text(
              text = "🎯 Self-Test පරීක්ෂණය (${testModeIndex!! + 1} / ${questionsList.size})",
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF38BDF8)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Surface(
              shape = RoundedCornerShape(6.dp),
              color = Color(0xFF0284C7).copy(alpha = 0.2f)
            ) {
              Text(
                text = testQ.subject,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7DD3FC),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
              )
            }

            Spacer(modifier = Modifier.height(14.dp))
            Text(
              text = testQ.question,
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White,
              textAlign = TextAlign.Center,
              lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            if (!revealAnswer) {
              Button(
                onClick = { revealAnswer = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0284C7)),
                shape = RoundedCornerShape(8.dp)
              ) {
                Text("👁️ පිළිතුර පෙන්වන්න", fontSize = 11.sp, fontWeight = FontWeight.Bold)
              }
            } else {
              Surface(
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFF1E293B),
                border = BorderStroke(1.dp, Color(0xFF34D399)),
                modifier = Modifier.fillMaxWidth()
              ) {
                Column(modifier = Modifier.padding(12.dp)) {
                  Text("✅ ඔබ ලියා ඇති පිළිතුර:", fontSize = 10.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFF86EFAC))
                  Spacer(modifier = Modifier.height(4.dp))
                  Text(testQ.answer, fontSize = 13.sp, color = Color.White, lineHeight = 18.sp)
                }
              }

              Spacer(modifier = Modifier.height(12.dp))
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Button(
                  onClick = {
                    if (testModeIndex!! > 0) testModeIndex = testModeIndex!! - 1
                    revealAnswer = false
                  },
                  enabled = testModeIndex!! > 0,
                  shape = RoundedCornerShape(8.dp)
                ) {
                  Text("◀ කලින්", fontSize = 10.sp)
                }

                Button(
                  onClick = {
                    if (testModeIndex!! < questionsList.size - 1) {
                      testModeIndex = testModeIndex!! + 1
                    } else {
                      testModeIndex = 0
                    }
                    revealAnswer = false
                  },
                  shape = RoundedCornerShape(8.dp),
                  colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981))
                ) {
                  Text("මීළඟ ➔", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
              }
            }
          }
        }
      }
    }

    // List of Saved Questions
    if (questionsList.isNotEmpty() && testModeIndex == null) {
      item {
        Text(
          text = "සුරැකි ප්‍රශ්න ලැයිස්තුව (${questionsList.size}):",
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
      }

      items(questionsList) { q ->
        Card(
          shape = RoundedCornerShape(10.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
          border = BorderStroke(1.dp, Color(0xFF334155))
        ) {
          Column(modifier = Modifier.padding(12.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Surface(
                shape = RoundedCornerShape(4.dp),
                color = Color(0xFF14B8A6).copy(alpha = 0.2f)
              ) {
                Text(
                  text = q.subject,
                  fontSize = 9.5.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF2DD4BF),
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }

              IconButton(
                onClick = {
                  val updated = questionsList.filter { it.id != q.id }
                  questionsList = updated
                  saveQuestions(updated)
                },
                modifier = Modifier.size(22.dp)
              ) {
                Text("🗑️", fontSize = 12.sp)
              }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = q.question,
              fontSize = 12.sp,
              fontWeight = FontWeight.SemiBold,
              color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = "A: ${q.answer}",
              fontSize = 11.sp,
              color = Color(0xFFCBD5E1)
            )
          }
        }
      }
    }
  }
}
