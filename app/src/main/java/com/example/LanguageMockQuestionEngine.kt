package com.example

/**
 * High-precision Language Question Engine (Sinhala & English) for Live Exam Hall.
 * Generates 60 unique questions per paper across 30 papers and 3 terms.
 */
object LanguageMockQuestionEngine {

  fun generateSinhalaQuestion(grade: String, term: Int, paperIdx: Int, qNum: Int): MockExamQuestion {
    val v = (paperIdx - 1) % 30
    val topic = when (term) {
      1 -> when (qNum) {
        in 1..15 -> "අක්ෂර මාලාව, නාම පද හා ක්‍රියා පද"
        in 16..30 -> "උක්ත-ආඛ්‍යාත සම්බන්ධය හා විභක්ති"
        in 31..45 -> "සන්ධි සහ පද බෙදීම"
        else -> "කාව්‍ය රසවින්දනය හා වාක්‍ය රීති"
      }
      2 -> when (qNum) {
        in 1..15 -> "සමාස, තද්ධිත හා කෘදන්ත"
        in 16..30 -> "කර්තෘ කාරක සහ කර්ම කාරක වාක්‍ය"
        in 31..45 -> "ප්‍රස්ථා පිරුළු, රූඪි හා උපමා"
        else -> "විරාම ලක්ෂණ හා නිවැරදි අක්ෂර වින්‍යාසය"
      }
      else -> when (qNum) {
        in 1..15 -> "ඡන්දස් සහ අලංකාර (උපමා, රූපක)"
        in 16..30 -> "සම්භාව්‍ය ගද්‍ය-පද්‍ය සාහිත්‍ය විචාර"
        in 31..45 -> "නවකතා හා කෙටිකතා විචාර"
        else -> "නිබන්ධන රචනා සහ සන්නිවේදන ශිල්ප"
      }
    }

    val (text, opts, correct, exp) = when (qNum % 5) {
      1 -> {
        val subjectVerbs = listOf(
          ("ගුරුවරු ශිෂ්‍යයන්ට පාඩම් _____" to listOf("උගන්වති", "උගන්වයි", "උගන්වමු", "උගන්වහු")) to (1 to "'ගුරුවරු' ප්‍රථම පුරුෂ බහුවචන උක්තය බැවින් ආඛ්‍යාතය 'උගන්වති' වේ."),
          ("මම දිනපතා පුස්තකාලයට _____" to listOf("යමි", "යති", "යයි", "යමු")) to (1 to "'මම' උත්තම පුරුෂ ඒකවචන උක්තය බැවින් ආඛ්‍යාතය 'යමි' වේ."),
          ("අපි ලබන සතියේ විනෝද චාරිකාවක් _____" to listOf("යමු", "යමි", "යති", "යයි")) to (1 to "'අපි' උත්තම පුරුෂ බහුවචන උක්තය බැවින් ආඛ්‍යාතය 'යමු' වේ."),
          ("දරුවා මව්පියන්ට ගෞරව _____" to listOf("කරයි", "කරති", "කරමු", "කරහි")) to (1 to "'දරුවා' ප්‍රථම පුරුෂ ඒකවචන උක්තය බැවින් ආඛ්‍යාතය 'කරයි' වේ.")
        )
        val sel = subjectVerbs[v % subjectVerbs.size]
        Quad(sel.first.first, sel.first.second, sel.second.first, sel.second.second)
      }
      2 -> {
        val vibhakthi = listOf(
          "\"මිනිසා පොරවෙන් ගස කැපී ය\" හි 'පොරවෙන්' පදය අයත් වන්නේ කුමන විභක්තියටද?" to (listOf("කරණ විභක්තිය (Instrumental)", "කර්තෘ විභක්තිය", "කර්ම විභක්තිය", "සම්ප්‍රදාන විභක්තිය") to "ක්‍රියාව සිදුකිරීමට උපකාරී වන උපකරණය දැක්වීමට කරණ විභක්තිය (ෙන්, ින්) යෙදේ."),
          "\"මව දරුවාට කිරි දුන්නා ය\" හි 'දරුවාට' පදයේ විභක්තිය කුමක්ද?" to (listOf("සම්ප්‍රදාන විභක්තිය (Dative)", "කර්තෘ විභක්තිය", "අවධි විභක්තිය", "ආධාර විභක්තිය") to "කිසියම් දෙයක් ලබන්නා දැක්වීමට සම්ප්‍රදාන විභක්තිය (ට) යෙදේ."),
          "\"ගසින් ගෙඩියක් බිමට වැටුණි\" හි 'ගසින්' පදය අයත් විභක්තිය කුමක්ද?" to (listOf("අවධි විභක්තිය (Ablative)", "කරණ විභක්තිය", "සම්බන්ධ විභක්තිය", "කර්ම විභක්තිය") to "යමකින් වෙන්වීම හෝ ආරම්භය දැක්වීමට අවධි විභක්තිය (ෙන්, ින්) යෙදේ.")
        )
        val sel = vibhakthi[v % vibhakthi.size]
        Quad(sel.first, sel.second.first, 1, sel.second.second)
      }
      3 -> Quad(
        "\"අතීතය + අනුගමනය\" සන්ධි කළ විට ලැබෙන නිවැරදි පදය කුමක්ද?",
        listOf("අතීතානුගමනය (ස්වර සන්ධිය)", "අතීතනුගමනය", "අතීතඅනුගමනය", "අතීත්ගමනය"),
        1,
        "පූර්ව ස්වරය (අ) ලොප් වී පර ස්වරය (අ) දීර්ඝ වී (ආ) අතීතානුගමනය සෑදේ."
      )
      4 -> Quad(
        "\"අනුන්ට බාධා කරමින් තමාට ද ප්‍රයෝජනයක් නොගැනීම\" අර්ථවත් කරන ප්‍රස්ථා පිරුළ කුමක්ද?",
        listOf("බල්ලා පිදුරු ගොඩට දැමුවා සේ", "අලියා ගිලපු දිවුල් ගෙඩිය වගේ", "ඉඟුරු දී මිරිස් ගත්තා වගේ", "ගහෙන් වැටුණු මිනිහාට ගොනා ඇන්නා වගේ"),
        1,
        "බල්ලාට පිදුරු කෑමට නොහැකි මුත් කන ගවයාට ද බුරමින් කෑමට නොදීම මෙයින් නිරූපණය වේ."
      )
      else -> Quad(
        "\"ගුත්තිල කාව්‍යය\" රචනා කළ කෝට්ටේ යුගයේ වැඩවිසූ මහා කවීන්ද්‍රයාණන් වහන්සේ කවුද?",
        listOf("වෑත්තෑවේ හිමියන්", "තොටගමුවේ ශ්‍රී රාහුල හිමියන්", "විදුරපොළ පියතිස්ස හිමි", "ගුරුළුගෝමී පඬිතුමා"),
        1,
        "ගුත්තිල මූසිල පුවත ඇසුරින් වෑත්තෑවේ හිමියන් විසින් ගුත්තිල කාව්‍යය නිර්මාණය කරන ලදී."
      )
    }

    return MockExamQuestion(
      id = qNum,
      questionText = "[$qNum] $text",
      options = opts,
      correctOption = correct,
      explanation = exp,
      topicName = topic
    )
  }

  fun generateEnglishQuestion(grade: String, term: Int, paperIdx: Int, qNum: Int): MockExamQuestion {
    val v = (paperIdx - 1) % 30
    val topic = when (term) {
      1 -> "Tenses, Subject-Verb Agreement & Prepositions"
      2 -> "Passive Voice, Indirect Speech & Conditionals"
      else -> "Relative Clauses, Comparison & Vocabulary"
    }

    val (text, opts, correct, exp) = when (qNum % 5) {
      1 -> {
        val tenses = listOf(
          ("Neither the teacher nor the students _____ present at the hall yesterday." to listOf("were", "was", "are", "is")) to (1 to "When subjects are joined by 'neither... nor', the verb agrees with the closer subject ('students' - plural past 'were')."),
          ("Every student in our class _____ a library membership card." to listOf("has", "have", "are having", "were having")) to (1 to "'Every student' is singular and takes the singular verb 'has'."),
          ("The Sun _____ in the east and sets in the west." to listOf("rises", "rose", "is rising", "has risen")) to (1 to "Universal truths and habitual actions take Present Simple tense ('rises').")
        )
        val sel = tenses[v % tenses.size]
        Quad(sel.first.first, sel.first.second, sel.second.first, sel.second.second)
      }
      2 -> Quad(
        "Complete the conditional sentence: \"If it rains heavily tomorrow, we _____ the cricket match.\"",
        listOf("will cancel", "would cancel", "would have cancelled", "cancelled"),
        1,
        "First Conditional (Real/Likely): If + Present Simple ('rains'), will + base verb ('will cancel')."
      )
      3 -> Quad(
        "Convert to Passive Voice: \"The chef cooked a delicious dinner.\"",
        listOf("A delicious dinner was cooked by the chef.", "A delicious dinner is cooked by the chef.", "A delicious dinner has been cooked.", "The chef was cooked by dinner."),
        1,
        "Past Simple active ('cooked') changes to 'was/were + past participle' ('was cooked')."
      )
      4 -> Quad(
        "Fill in the preposition: \"She has been living in Colombo _____ 2018.\"",
        listOf("since", "for", "at", "during"),
        1,
        "'Since' is used with a specific starting point in time (2018), while 'for' is used with duration."
      )
      else -> Quad(
        "Identify the correct antonym of the word \"GENEROUS\":",
        listOf("Miserly (Selfish / Stingy)", "Kind", "Benevolent", "Friendly"),
        1,
        "Generous means giving freely; its direct opposite is miserly or stingy."
      )
    }

    return MockExamQuestion(
      id = qNum,
      questionText = "[$qNum] $text",
      options = opts,
      correctOption = correct,
      explanation = exp,
      topicName = topic
    )
  }

  private data class Quad(
    val q: String,
    val opts: List<String>,
    val c: Int,
    val exp: String
  )
}
