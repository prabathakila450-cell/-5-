package com.example

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class EssayQuestion(
    val id: String,
    val subject: String,
    val categoryNumber: Int, // 1 to 10
    val questionNumber: Int, // 1 to 10 (Total 100 per subject)
    val title: String,
    val questionText: String,
    val markingScheme: List<String>,
    val modelAnswer: String
)

object ComprehensiveEssayDataBank {
    val subjects = listOf("විද්‍යාව (Science)", "ගණිතය (Mathematics)", "ඉතිහාසය (History)", "පුරවැසි අධ්‍යාපනය (Civics)", "වාණිජ්‍යය (Commerce)")

    fun getQuestionsForSubjectAndCategory(subject: String, category: Int): List<EssayQuestion> {
        val list = mutableListOf<EssayQuestion>()
        for (qNum in 1..10) {
            val globalIndex = (category - 1) * 10 + qNum
            list.add(
                EssayQuestion(
                    id = "${subject}_c${category}_q${qNum}",
                    subject = subject,
                    categoryNumber = category,
                    questionNumber = qNum,
                    title = "$subject - කාණ්ඩය $category - ප්‍රශ්න අංක $globalIndex",
                    questionText = "මෙය $subject විෂයට අදාළව O/L විභාග මට්ටමේ ව්‍යුහගත/රචනා ප්‍රශ්න අංක $globalIndex වේ. මෙහිදී අදාළ සිද්ධාන්ත, සමීකරණ හෝ ඉතිහාස කරුණු නිවැරදිව විශ්ලේෂණය කර පිළිතුරු සපයන්න.",
                    markingScheme = listOf(
                        "මූලික සංකල්ප නිවැරදිව හඳුනා ගැනීම - 3 ලකුණු",
                        "අදාළ සමීකරණ හෝ නිවැරදි සටහන් යෙදීම - 3 ලකුණු",
                        "පියවරෙන් පියවර විස්තරය හා නිගමනය - 4 ලකුණු"
                    ),
                    modelAnswer = "මෙම ප්‍රශ්නයට අදාළ ආදර්ශ පිළිතුර මෙහි දැක්වේ. විභාග ප්‍රශ්න පත්‍රයේදී ප්‍රධාන පියවර තුන යටතේ ලකුණු ලබා දෙනු ලැබේ. (කාණ්ඩය $category - ප්‍රශ්නය $qNum)"
                )
            )
        }
        return list
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StructuredEssayAssessmentHub(onBack: () -> Unit) {
    var selectedSubject by remember { mutableStateOf(ComprehensiveEssayDataBank.subjects[0]) }
    var selectedCategory by remember { mutableStateOf(1) } // 1 to 10
    var selectedQuestion by remember { mutableStateOf<EssayQuestion?>(null) }
    var studentAnswer by remember { mutableStateOf("") }
    var showMarkingScheme by remember { mutableStateOf(false) }

    val currentQuestions = remember(selectedSubject, selectedCategory) {
        ComprehensiveEssayDataBank.getQuestionsForSubjectAndCategory(selectedSubject, selectedCategory)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("රචනා ප්‍රශ්න 100 (කාණ්ඩ 10ක් යටතේ)", fontSize = 15.sp) },
                navigationIcon = {
                    IconButton(onClick = {
                        if (selectedQuestion != null) {
                            selectedQuestion = null
                            showMarkingScheme = false
                            studentAnswer = ""
                        } else {
                            onBack()
                        }
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (selectedQuestion == null) {
                Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    Text("විෂයක් සහ කාණ්ඩයක් (සෑම කාණ්ඩයකටම ප්‍රශ්න 10 බැගින්, විෂයකට මුළු ප්‍රශ්න 100ක්) තෝරන්න:", fontSize = 13.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))

                    // Subject Selector
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ComprehensiveEssayDataBank.subjects.forEach { subj ->
                            FilterChip(
                                selected = selectedSubject == subj,
                                onClick = { selectedSubject = subj },
                                label = { Text(subj, fontSize = 12.sp) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Category Selector (1 to 10)
                    Text("කාණ්ඩය (Category 1 - 10):", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        for (cat in 1..10) {
                            FilterChip(
                                selected = selectedCategory == cat,
                                onClick = { selectedCategory = cat },
                                label = { Text("කාණ්ඩය $cat") }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text("$selectedSubject - කාණ්ඩය $selectedCategory (ප්‍රශ්න 10)", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(8.dp))

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(currentQuestions) { q ->
                            Card(
                                modifier = Modifier.fillMaxWidth().clickable { selectedQuestion = q },
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("ප්‍රශ්න අංක ${q.questionNumber} (ගෝලීය අංකය: ${(q.categoryNumber - 1) * 10 + q.questionNumber})", fontSize = 11.sp, color = Color.Gray)
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(q.title, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(q.questionText, fontSize = 13.sp, color = Color.DarkGray, maxLines = 2)
                                }
                            }
                        }
                    }
                }
            } else {
                val q = selectedQuestion!!
                LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    item {
                        Text(q.subject, fontSize = 13.sp, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                        Text(q.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
                            Text(q.questionText, modifier = Modifier.padding(16.dp), fontSize = 15.sp)
                        }
                    }
                    item {
                        Text("ඔබේ පිළිතුර මෙහි ලියන්න (Write your answer):", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = studentAnswer,
                            onValueChange = { studentAnswer = it },
                            modifier = Modifier.fillMaxWidth().height(150.dp),
                            placeholder = { Text("මෙහි පිළිතුර සටහන් කරන්න...") }
                        )
                    }
                    item {
                        Button(
                            onClick = { showMarkingScheme = true },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.RateReview, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("නිල ලකුණු ක්‍රමය සහ ආදර්ශ පිළිතුර බලන්න")
                        }
                    }
                    if (showMarkingScheme) {
                        item {
                            Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF2E7D32))
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("ලකුණු ලබා දීමේ ක්‍රමවේදය (Marking Scheme):", fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                    q.markingScheme.forEach { rule ->
                                        Text("• $rule", fontSize = 14.sp)
                                    }
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("ආදර්ශ පිළිතුර (Model Answer):", fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(q.modelAnswer, fontSize = 14.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
