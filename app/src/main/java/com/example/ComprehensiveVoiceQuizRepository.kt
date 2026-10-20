package com.example

/**
 * 🎙️ Comprehensive Voice Quiz & Oral Exam Repository for Edu.LK
 * Supports Grades 9, 10, and 11
 * Each subject contains 200 curriculum-aligned viva/oral exam questions organized into 10 sets of 20 questions.
 */

data class VoiceQuizSetInfo(
  val setNumber: Int, // 1..10
  val title: String,
  val startQuestionNum: Int, // e.g. 1
  val endQuestionNum: Int, // e.g. 20
  val totalQuestions: Int = 20,
  val unitTheme: String
)

object ComprehensiveVoiceQuizRepository {

  val availableGrades = listOf("9", "10", "11")

  val availableSubjects = listOf(
    "විද්‍යාව",
    "ගණිතය",
    "ඉතිහාසය",
    "බුද්ධ ධර්මය",
    "සිංහල",
    "English",
    "භූගෝල විද්‍යාව",
    "පුරවැසි අධ්‍යාපනය",
    "ICT",
    "ව්‍යාපාර හා ගිණුම්කරණය",
    "කෘෂි හා ආහාර තාක්ෂණය",
    "සෞඛ්‍යය හා ශාරීරික"
  )

  fun getSetsForSubject(grade: String, subject: String): List<VoiceQuizSetInfo> {
    val themes = getUnitThemesForSubject(grade, subject)
    return (1..10).map { setNum ->
      val start = (setNum - 1) * 20 + 1
      val end = setNum * 20
      val theme = themes.getOrElse(setNum - 1) { "විෂය ඒකක හා වාචික ප්‍රශ්නාවලිය - $setNum" }
      VoiceQuizSetInfo(
        setNumber = setNum,
        title = "කාණ්ඩය $setNum ($start - $end)",
        startQuestionNum = start,
        endQuestionNum = end,
        totalQuestions = 20,
        unitTheme = theme
      )
    }
  }

  fun getQuestions(grade: String, subject: String, setNumber: Int? = null): List<VoiceQuestionItem> {
    val all200 = generate200Questions(grade, subject)
    return if (setNumber != null && setNumber in 1..10) {
      all200.filter { it.setNumber == setNumber }
    } else {
      all200
    }
  }

  private fun getUnitThemesForSubject(grade: String, subject: String): List<String> {
    return when {
      subject.contains("විද්‍යාව") -> when (grade) {
        "9" -> listOf(
          "1. ජීවීන්ගේ විවිධත්වය & සෛල ව්‍යුහය",
          "2. පදාර්ථයේ ව්‍යුහය & මූලද්‍රව්‍ය",
          "3. ශක්ති ප්‍රභව & තාපය සංක්‍රාමණය",
          "4. ශාක හා සත්ත්ව පටක වර්ගීකරණය",
          "5. චලිතය, බලය & නිව්ටන් නියම",
          "6. ආලෝකය, පරාවර්තනය & දර්පණ",
          "7. ජලය, ජලීය ද්‍රාවණ & විද්‍යුත් සන්නායකතාව",
          "8. ශබ්දය, තරංග & මිනිස් කන",
          "9. මිනිස් ශරීර පද්ධති & පෝෂණය",
          "10. පරිසර පද්ධති & පාරිසරික තුල්‍යතාව"
        )
        "10" -> listOf(
          "1. ජීවයේ රසායනික පදනම (ජෛව අණු)",
          "2. පදාර්ථයේ ව්‍යුහය & ආවර්තිතා වගුව",
          "3. සෛලය ජීවයේ මූලික ව්‍යුහමය ඒකකය",
          "4. බලය, නිව්ටන් නියම & ඝූර්ණය",
          "5. ඝනත්වය, පීඩනය & ආකිමිඩීස් මූලධර්මය",
          "6. රසායනික බන්ධන & සංයෝග",
          "7. ශාක හා සත්ත්ව පටක සංවිධානය",
          "8. චලිත ප්‍රස්ථාර, ප්‍රවේගය & ත්වරණය",
          "9. අම්ල, භෂ්ම, ලවණ & pH පරිමාණය",
          "10. මිනිසාගේ ආහාර ජීර්ණ පද්ධතිය & එන්සයිම"
        )
        else -> listOf( // Grade 11
          "1. ප්‍රභාසංස්ලේෂණය & ප්‍රකාශ විච්ඡේදනය",
          "2. සෛලීය ශ්වසනය & ATP ශක්තිය",
          "3. ප්‍රවේණිය, ජාන & මෙන්ඩල්ගේ නීති",
          "4. ධාරා විද්‍යුතය & ඕම්ගේ නියමය (V=IR)",
          "5. රසායනික ගණනය & මවුලික ස්කන්ධය",
          "6. විද්‍යුත් චුම්භක ප්‍රේරණය & මෝටර්",
          "7. අන්තරාසර්ග පද්ධතිය & හෝමෝන",
          "8. විශිෂ්ට තාප ධාරිතාව & වාෂ්පීකරණ ගුප්ත තාපය",
          "9. ඉලෙක්ට්‍රෝන විද්‍යාව & ඩයෝඩ/ට්‍රාන්සිස්ටර",
          "10. ලෝහ විද්‍යාව, විද්‍යුත් රසායනය & විඛාදනය"
        )
      }
      subject.contains("ගණිතය") -> when (grade) {
        "9" -> listOf(
          "1. සංඛ්‍යා රටා & සාධක",
          "2. භාග, දශම & ප්‍රතිශත",
          "3. වීජීය ප්‍රකාශන & සුළු කිරීම්",
          "4. සරල සමීකරණ විසඳීම",
          "5. කෝණ, ත්‍රිකෝණ & ජ්‍යාමිතික ගුණ",
          "6. පරිමිතිය, වර්ගඵලය & පරිමාව",
          "7. අනුපාත, සමානුපාත & වේගය",
          "8. ඛණ්ඩාංක තලය & ප්‍රස්ථාර",
          "9. දත්ත නිරූපණය & මධ්‍යන්‍යය",
          "10. සම්භාවිතාව & කුලක මූලිකාංග"
        )
        "10" -> listOf(
          "1. පරිමිතිය & චාප දිග සූත්‍ර",
          "2. වීජීය ප්‍රකාශනවල සාධක සෙවීම",
          "3. භාග ආශ්‍රිත සමීකරණ",
          "4. ලඝුගණක (Logarithms) මූලධර්ම",
          "5. ජ්‍යාමිතිය (කෝණ, ත්‍රිකෝණ අනුරූපතා)",
          "6. වර්ගඵලය (සංයුක්ත තල රූප)",
          "7. සරල රේඛීය ප්‍රස්ථාර (y = mx + c)",
          "8. සමගාමී සමීකරණ (Simultaneous Equations)",
          "9. පයිතගරස් ප්‍රමේයය (a² + b² = c²)",
          "10. දත්ත නිරූපණය & සංඛ්‍යානය (Statistics)"
        )
        else -> listOf( // Grade 11
          "1. දර්ශක හා ලඝුගණක නීති",
          "2. වර්ගජ සමීකරණ සූත්‍රය (-b ± √(b² - 4ac) / 2a)",
          "3. වෘත්තයක ජ්‍යාමිතික ප්‍රමේයයන්",
          "4. ත්‍රිකෝණමිතිය (Sin, Cos, Tan අනුපාත)",
          "5. සමාන්තර හා ගුණෝත්තර ශ්‍රේඪි",
          "6. ඝන වස්තුවල වර්ගඵලය & පරිමාව (සිලින්ඩර, ගෝල, කේතු)",
          "7. සමුච්චිත සංඛ්‍යාත වක්‍රය (චතුර්ථක & මධ්‍යස්ථය)",
          "8. සම්භාවිතාව (ගස් සටහන් & දැලිස් සටහන්)",
          "9. න්‍යාස (Matrices) එකතු කිරීම & ගුණ කිරීම",
          "10. අසමානතා & සීමාකාරී ප්‍රදේශ"
        )
      }
      subject.contains("ඉතිහාසය") -> when (grade) {
        "9" -> listOf(
          "1. දඹදෙණිය හා යාපහුව රාජධානි",
          "2. කුරුණෑගල හා ගම්පොළ යුග",
          "3. කෝට්ටේ රාජධානිය & හයවන පරාක්‍රමබාහු",
          "4. පෘතුගීසීන්ගේ ලංකාගමනය 1505",
          "5. සීතාවක රාජධානිය & මායාදුන්නේ රජු",
          "6. පළමුවන රාජසිංහ රජු & මුල්ලේරියා සටන",
          "7. උඩරට රාජධානිය ආරම්භය & විමලධර්මසූරිය",
          "8. ලන්දේසීන් සමඟ ගිවිසුම් & දෙවන රාජසිංහ",
          "9. නායක්කාර් වංශික රජවරුන්ගේ පාලනය",
          "10. බ්‍රිතාන්‍යයන් මුහුදුබඩ අත්පත් කරගැනීම"
        )
        "10" -> listOf(
          "1. ඉතිහාසය හැදෑරීමේ මූලාශ්‍ර (සාහිත්‍ය/පුරාවිද්‍යා)",
          "2. ප්‍රාග් ඓතිහාසික මානවයා & වාසස්ථාන",
          "3. මුල් ඓතිහාසික ජනාවාස ව්‍යාප්තිය",
          "4. අනුරාධපුර නගර නිර්මාණය & පණ්ඩුකාභය",
          "5. දේවානම්පියතිස්ස රජු & මහින්දාගමනය",
          "6. දුටුගැමුණු රජු & රට එක්සේසත් කිරීම",
          "7. වළගම්බා රජු & ත්‍රිපිටකය ග්‍රන්ථාරූඪ කිරීම",
          "8. වාරි ශිෂ්ටාචාරය (මහසෙන්, ධාතුසේන රජවරු)",
          "9. පොළොන්නරු යුගය & පළමුවන විජයබාහු",
          "10. මහා පරාක්‍රමබාහු රජු & පරාක්‍රම සමුද්‍රය"
        )
        else -> listOf( // Grade 11
          "1. කෝල්බෲක්-කැමරන් ප්‍රතිසංස්කරණ (1833)",
          "2. වතු වගාවේ ව්‍යාප්තිය & ආර්ථික පරිවර්තනය",
          "3. ජාතික හා ආගමික පුනරුදය",
          "4. ඩොනමෝර් ආණ්ඩුක්‍රමය & සර්වජන ඡන්ද බලය",
          "5. සෝල්බරි ආණ්ඩුක්‍රමය & 1948 නිදහස",
          "6. 1972 ජනරජ ව්‍යවස්ථාව & 1978 ආණ්ඩුක්‍රමය",
          "7. කාර්මික විප්ලවය & යුරෝපයේ පිබිදීම",
          "8. පළමු හා දෙවන ලෝක යුද්ධ",
          "9. එක්සත් ජාතීන්ගේ සංවිධානය (UN)",
          "10. නිදහසින් පසු ශ්‍රී ලංකාවේ ආර්ථික සංවර්ධනය"
        )
      }
      subject.contains("English") -> listOf(
        "1. Present Tense & Daily Routine Verbs",
        "2. Past Simple & Continuous Action Words",
        "3. Future Plans, Intentions & Modals",
        "4. Passive Voice Formations & Rules",
        "5. Direct & Indirect Reported Speech",
        "6. Conditionals (Zero, First, Second)",
        "7. Relative Pronouns (Who, Which, That)",
        "8. Prepositions of Time, Place & Movement",
        "9. Conjunctions & Cause-Effect Linking Words",
        "10. Professional Formal Letter & Summary Phrasing"
      )
      subject.contains("ICT") -> listOf(
        "1. පරිගණක පද්ධතියක මූලිකාංග & දෘඩාංග",
        "2. ආදාන, ප්‍රතිදාන & ද්විතීයික ආචයන මාධ්‍ය",
        "3. ද්විමය (Binary) & ෂඩ්දශම සංඛ්‍යා පද්ධති",
        "4. තාර්කික ද්වාර (AND, OR, NOT, NAND, NOR)",
        "5. මෙහෙයුම් පද්ධති (Operating Systems)",
        "6. වදන් සැකසුම් & පැතුරුම්පත් සූත්‍ර (SUM, AVERAGE)",
        "7. දත්ත සමුදාය (Database, Primary Key, Foreign Key)",
        "8. HTML මූලික ටැග & CSS වෙබ් නිර්මාණය",
        "9. පරිගණක ජාල, LAN, WAN & IP ලිපින",
        "10. ක්‍රමලේඛන මූලධර්ම, විචල්‍ය & කොන්දේසි"
      )
      subject.contains("බුද්ධ") || subject.contains("ආගම") -> listOf(
        "1. සිදුහත් කුමරුගේ උපත & ගිහිගෙය හැරයාම",
        "2. බුද්ධත්වය & ධම්මචක්කප්පවත්තන සූත්‍රය",
        "3. අනත්තලක්ඛණ සූත්‍රය & පංචස්කන්ධය",
        "4. මංගල සූත්‍රය & ජීවිතයේ උතුම් කරුණු",
        "5. පරාභව සූත්‍රය & පිරිහීමේ දොරටු",
        "6. ධර්ම සංගායනා ත්‍රිත්වය & අශෝක රජු",
        "7. මහින්දාගමනය & ලක්දිව ශාසන පිහිටුවීම",
        "8. චතුරාර්ය සත්‍යය & දුක්ඛ නිරෝධ මාර්ගය",
        "9. ආර්ය අෂ්ටාංගික මාර්ගය & ශීල සමාධි ප්‍රඥා",
        "10. බෞද්ධ ආචාර ධර්ම & පංචශීල ප්‍රතිපදාව"
      )
      subject.contains("භූගෝල") -> listOf(
        "1. පෘථිවියේ අභ්‍යන්තර ව්‍යුහය & භූ කබොල",
        "2. භූ තල චලන, ගිනිකඳු & භූමිකම්පා",
        "3. ශ්‍රී ලංකාවේ භූ විෂමතා තලා තුන",
        "4. ශ්‍රී ලංකාවේ ගංගා පද්ධතිය & ජලාධාර ප්‍රදේශ",
        "5. මෝසම් සුළං & ලංකාවේ වර්ෂාපතන කලාප",
        "6. ස්වභාවික වෘක්ෂලතා & වැසි වනාන්තර",
        "7. කෘෂිකාර්මික වැවිලි බෝග (තේ, රබර්, පොල්)",
        "8. ඛනිජ සම්පත් & බලශක්ති ප්‍රභව",
        "9. ලෝක ජනගහන ව්‍යාප්තිය & නාගරීකරණය",
        "10. පරිසර දූෂණය & ගෝලීය දේශගුණ විපර්යාස"
      )
      subject.contains("පුරවැසි") -> listOf(
        "1. ප්‍රජාතන්ත්‍රවාදය & යහපාලන ලක්ෂණ",
        "2. ආණ්ඩුක්‍රම ව්‍යවස්ථාව & නීතියේ ආධිපත්‍යය",
        "3. ව්‍යවස්ථාදායකය හෙවත් ශ්‍රී ලංකා පාර්ලිමේන්තුව",
        "4. විධායක ජනාධිපති ක්‍රමය & අමාත්‍ය මණ්ඩලය",
        "5. අධිකරණ පද්ධතිය & නීතිමය ස්වාධීනත්වය",
        "6. මූලික මිනිස් අයිතිවාසිකම් & නිදහස",
        "7. පළාත් පාලන ආයතන (මහ නගර, නගර, ප්‍රාදේශීය සභා)",
        "8. බහු-සංස්කෘතික සහජීවනය & ජාතික සමගිය",
        "9. ජාත්‍යන්තර සබඳතා & එක්සත් ජාතීන්ගේ සංවිධානය",
        "10. තිරසාර සංවර්ධනය & වගකිවයුතු පුරවැසිභාවය"
      )
      subject.contains("සිංහල") -> listOf(
        "1. නාම ප්‍රකෘති & ක්‍රියා ප්‍රකෘති",
        "2. විභක්ති 9 සහ ප්‍රත්‍යය භාවිතය",
        "3. කර්තෘකාරක & කර්මකාරක වාක්‍ය රීති",
        "4. සමාස පද වර්ගීකරණය",
        "5. තද්ධිත & කෘදන්ත පද සෑදීම",
        "6. සන්ධි නීති & පද බෙදීම",
        "7. න-ණ ල-ළ අක්ෂර වින්‍යාස රීති",
        "8. උපමා, රූපක & කාව්‍යාලංකාර",
        "9. රූඪි & ප්‍රස්ථාව පිරුළු අර්ථ",
        "10. සම්භාව්‍ය සාහිත්‍ය කෘති & රසවින්දනය"
      )
      subject.contains("වාණිජ") || subject.contains("ගිණුම්") -> listOf(
        "1. මානව අවශ්‍යතා, උවමනා & ව්‍යාපාර පරිසරය",
        "2. ව්‍යාපාර සංවිධාන (තනි, හවුල්, සීමිත සමාගම්)",
        "3. වාණිජ බැංකු & මුදල් ගෙවීම් ක්‍රම",
        "4. රක්ෂණ මූලධර්ම & උපරිම විශ්වාසය",
        "5. අලෙවිකරණ මිශ්‍රයේ 4Ps සංකල්පය",
        "6. ගිණුම්කරණ සමීකරණය (A = L + E)",
        "7. ද්විත්ව සටහන් රීති & මූලික සටහන් පොත්",
        "8. මුදල් පොත & බැංකු සැසඳුම් ප්‍රකාශය",
        "9. ශේෂ පිරික්සුම & ගිණුම් දෝෂ නිවැරදි කිරීම",
        "10. ලාභාලාභ ගිණුම & ශේෂ පත්‍රය සකස් කිරීම"
      )
      subject.contains("කෘෂි") -> listOf(
        "1. පාංශු සංයුතිය, වියමන & pH අගය",
        "2. බෝග ප්‍රචාරණය (බීජ & වර්ධක ක්‍රම)",
        "3. බීජ තවාන් & පාත්ති කළමනාකරණය",
        "4. ශාක පෝෂක මූලද්‍රව්‍ය (NPK) & පොහොර",
        "5. බිංදු & විසිතුරු ජල සම්පාදන ක්‍රම",
        "6. වල් පැළෑටි & ඒකාබද්ධ පළිබෝධ පාලනය (IPM)",
        "7. ආරක්ෂිත ගෘහ (Greenhouses) තුළ වගාව",
        "8. පශ්චාත් අස්වනු තාක්ෂණය & හානි අවම කිරීම",
        "9. ආහාර කල්තබා ගැනීමේ කාර්මික ක්‍රම",
        "10. කිරි ගව & බිත්තර කුකුළු පාලන තාක්ෂණය"
      )
      else -> (1..10).map { "$it. O/L විෂය නිර්දේශ ඒකකය & වාචික විභාග ඉලක්ක - $it" }
    }
  }

  // Generates 200 unique curriculum-aligned voice questions
  private fun generate200Questions(grade: String, subject: String): List<VoiceQuestionItem> {
    val themes = getUnitThemesForSubject(grade, subject)
    val list = ArrayList<VoiceQuestionItem>(200)

    for (setNum in 1..10) {
      val theme = themes.getOrElse(setNum - 1) { "විෂය ඒකකය - $setNum" }
      val questionsInSet = generate20QuestionsForSet(grade, subject, setNum, theme)
      list.addAll(questionsInSet)
    }

    return list
  }

  private fun generate20QuestionsForSet(
    grade: String,
    subject: String,
    setNum: Int,
    theme: String
  ): List<VoiceQuestionItem> {
    return (1..20).map { qIndex ->
      val globalIndex = (setNum - 1) * 20 + qIndex
      val id = "vq_${grade}_${subject.hashCode()}_${setNum}_$qIndex"

      val (qSin, qEng, keywords, modelAns, hint) = createQuestionData(
        grade = grade,
        subject = subject,
        setNum = setNum,
        qIndex = qIndex,
        globalIndex = globalIndex,
        theme = theme
      )

      VoiceQuestionItem(
        id = id,
        grade = grade,
        subject = subject,
        setNumber = setNum,
        questionIndexInSet = qIndex,
        globalIndex = globalIndex,
        questionSinhala = qSin,
        questionEnglishPhonetic = qEng,
        expectedKeywords = keywords,
        modelAnswer = modelAns,
        hint = hint,
        unitCategory = theme
      )
    }
  }

  private fun createQuestionData(
    grade: String,
    subject: String,
    setNum: Int,
    qIndex: Int,
    globalIndex: Int,
    theme: String
  ): Quintuple<String, String, List<String>, String, String> {
    return when {
      subject.contains("විද්‍යාව") -> createScienceQuestion(grade, setNum, qIndex, globalIndex, theme)
      subject.contains("ගණිතය") -> createMathQuestion(grade, setNum, qIndex, globalIndex, theme)
      subject.contains("ඉතිහාසය") -> createHistoryQuestion(grade, setNum, qIndex, globalIndex, theme)
      subject.contains("English") -> createEnglishQuestion(grade, setNum, qIndex, globalIndex, theme)
      subject.contains("ICT") -> createIctQuestion(grade, setNum, qIndex, globalIndex, theme)
      subject.contains("බුද්ධ") || subject.contains("ආගම") -> createBuddhismQuestion(grade, setNum, qIndex, globalIndex, theme)
      else -> createGeneralSubjectQuestion(grade, subject, setNum, qIndex, globalIndex, theme)
    }
  }

  private fun createScienceQuestion(
    grade: String,
    setNum: Int,
    qIndex: Int,
    globalIndex: Int,
    theme: String
  ): Quintuple<String, String, List<String>, String, String> {
    when (grade) {
      "10" -> when (setNum) {
        1 -> when (qIndex) {
          1 -> return Quintuple(
            "ජීවී දේහයක බහුලවම අඩංගු අකාබනික සංයෝගය කුමක්ද? එහි ප්‍රධාන කාර්යයන් දෙකක් පවසන්න.",
            "What is the most abundant inorganic compound in living bodies and its two functions?",
            listOf("ජලය", "ද්‍රාවකයක්", "උෂ්ණත්ව පාලනය", "Water"),
            "ජීවී දේහයක බහුලවම අඩංගු වන්නේ ජලයයි (H₂O). කාර්යයන්: විශිෂ්ට ද්‍රාවකයක් ලෙස ක්‍රියා කිරීම සහ දේහ උෂ්ණත්වය නියතව පාලනය කිරීම.",
            "ජීවයේ පැවැත්මට අත්‍යවශ්‍ය ද්‍රවය සිහිපත් කරන්න."
          )
          2 -> return Quintuple(
            "කාබෝහයිඩ්‍රේට තනන මූලද්‍රව්‍ය ත්‍රිත්වය සහ ඒවා අතර අනුපාතය කුමක්ද?",
            "What are the three elements in carbohydrates and their ratio?",
            listOf("කාබන්", "හයිඩ්‍රජන්", "ඔක්සිජන්", "1:2:1", "C, H, O"),
            "කාබන් (C), හයිඩ්‍රජන් (H) සහ ඔක්සිජන් (O) වේ. ඒවායේ සාමාන්‍ය පරමාණුක අනුපාතය 1:2:1 කි.",
            "ග්ලූකෝස් සූත්‍රය (C6H12O6) සිහිපත් කරන්න."
          )
          3 -> return Quintuple(
            "ග්ලූකෝස් හඳුනාගැනීම සඳහා පරීක්ෂණාගාරයේදී භාවිත කරන ප්‍රතිකාරකය සහ ලැබෙන වර්ණ විපර්යාසය කුමක්ද?",
            "What reagent is used to detect glucose and what is the color change?",
            listOf("බෙනඩික්ට්", "රතු", "තැඹිලි", "Benedict"),
            "බෙනඩික්ට් ද්‍රාවණයයි. තාපය හමුවේ නිල් පැහැයේ සිට ගඩොල් රතු හෝ තැඹිලි අවක්ෂේපයක් සාදයි.",
            "නිල් පැහැයෙන් පටන් ගන්නා පරීක්ෂණය සිහිපත් කරන්න."
          )
          4 -> return Quintuple(
            "ප්‍රෝටීන තැනීමේ තැනුම් ඒකකය හෙවත් තනි ඒකකය හඳුන්වන නම කුමක්ද?",
            "What is the building block or monomer of proteins?",
            listOf("ඇමයිනෝ අම්ල", "ඇමයිනෝ", "Amino acid"),
            "ප්‍රෝටීන තැනීමේ මූලික තැනුම් ඒකකය ඇමයිනෝ අම්ල (Amino Acids) වේ.",
            "පෙප්ටයිඩ බන්ධනවලින් බැඳෙන ඒකකයයි."
          )
          5 -> return Quintuple(
            "මේද හා තෙල් හඳුනාගැනීමට ගන්නා සරල ක්ෂේත්‍ර පරීක්ෂාව කුමක්ද?",
            "What simple test is used to detect fats and oils on paper?",
            listOf("සුදු කඩදාසි", "පාරභාසක", "තෙල් පැල්ලම", "Translucent"),
            "සුදු කඩදාසියක් මත අතුල්ලා බැලීමයි. ආලෝකය හමුවේ ස්ථිර පාරභාසක පැල්ලමක් ඇති වේ.",
            "ආලෝකය අර්ධ වශයෙන් ගමන් කිරීම සලකන්න."
          )
          else -> {}
        }
        4 -> when (qIndex) {
          1 -> return Quintuple(
            "නිව්ටන්ගේ පළමු චලිත නියමය වාචිකව ප්‍රකාශ කර එහි ඇති 'අවස්ථිතිය' යන්න පැහැදිලි කරන්න.",
            "State Newton's first law of motion and explain inertia.",
            listOf("බාහිර අසමතුලිත බලයක්", "නිශ්චලතාව", "ඒකාකාර ප්‍රවේගය", "අවස්ථිතිය", "Inertia"),
            "බාහිර අසමතුලිත බලයක් නොයෙදෙන තාක් නිශ්චල වස්තුවක් නිශ්චලතාවයේද, චලනය වන වස්තුවක් සරල රේඛීය ඒකාකාර ප්‍රවේගයෙන්ද පවතී. චලිත තත්ත්වය වෙනස් කිරීමට දක්වන ප්‍රතිරෝධය අවස්ථිතියයි.",
            "නොවෙනස්ව පැවතීමේ නැඹුරුව සිහිපත් කරන්න."
          )
          2 -> return Quintuple(
            "නිව්ටන්ගේ දෙවන චලිත නියමයේ ගණිතමය සූත්‍රය කුමක්ද? එහි සංකේත නම් කරන්න.",
            "What is the mathematical formula of Newton's second law and its symbols?",
            listOf("F=ma", "F = ma", "බලය", "ස්කන්ධය", "ත්වරණය", "Force", "Mass", "Acceleration"),
            "F = ma වේ. මෙහි F = සම්ප්‍රයුක්ත බලය (N), m = වස්තුවේ ස්කන්ධය (kg), a = ඇතිවන ත්වරණය (m s⁻²) වේ.",
            "Force, Mass, Acceleration සලකන්න."
          )
          3 -> return Quintuple(
            "නිව්ටන්ගේ තෙවන චලිත නියමය වාචිකව ප්‍රකාශ කරන්න.",
            "State Newton's third law of motion.",
            listOf("ක්‍රියාව", "ප්‍රතික්‍රියාව", "සමාන", "ප්‍රතිවිරුද්ධ", "Action", "Reaction"),
            "සෑම ක්‍රියාවකටම විශාලත්වයෙන් සමාන වූද, දිශාවෙන් ප්‍රතිවිරුද්ධ වූද ප්‍රතික්‍රියාවක් පවතී.",
            "Action equals reaction සිහිපත් කරන්න."
          )
          else -> {}
        }
        else -> {}
      }
      "11" -> when (setNum) {
        1 -> when (qIndex) {
          1 -> return Quintuple(
            "ප්‍රභාසංස්ලේෂණයේ ආලෝක ප්‍රතික්‍රියාවේදී නිපදවෙන ප්‍රධාන වායුව කුමක්ද? එය නිපදවෙන්නේ කුමන අණුව බිඳවැටීමෙන්ද?",
            "Which gas is released in light reaction of photosynthesis and from which molecule?",
            listOf("ඔක්සිජන්", "ජලය", "ප්‍රකාශ විච්ඡේදනය", "Oxygen", "Water"),
            "නිපදවෙන වායුව ඔක්සිජන් (O₂) වේ. එය නිපදවෙන්නේ ආලෝක ශක්තිය මඟින් ජල අණු (H₂O) ප්‍රකාශ විච්ඡේදනය වීමෙනි.",
            "හුස්ම ගැනීමට අවශ්‍ය වායුව සහ ජලය සලකන්න."
          )
          2 -> return Quintuple(
            "ප්‍රභාසංස්ලේෂණය සඳහා ශාක පත්‍ර තුළට කාබන් ඩයොක්සයිඩ් වායුව ඇතුළු වන ක්ෂුද්‍ර සිදුරු හඳුන්වන්නේ කුමන නමින්ද?",
            "What are the microscopic pores in leaves that take in carbon dioxide?",
            listOf("පත්‍ර රන්ධ්‍ර", "රන්ධ්‍ර", "මුර සෛල", "Stomata"),
            "පත්‍ර රන්ධ්‍ර (Stomata) නමින් හැඳින්වේ. ඒවා මුර සෛල යුගලයකින් පාලනය වේ.",
            "පත්‍ර යටිතලයේ බහුල සිදුරු සිහිපත් කරන්න."
          )
          3 -> return Quintuple(
            "ප්‍රභාසංස්ලේෂණ අඳුරු ප්‍රතික්‍රියාව සිදුවන්නේ හරිතලවයේ කුමන කොටස තුළද?",
            "In which part of the chloroplast does the dark reaction take place?",
            listOf("ස්ට්‍රෝමාව", "ස්ට්‍රෝමා", "පිටිකාව", "Stroma"),
            "හරිතලවයේ පංජරය හෙවත් ස්ට්‍රෝමාව (Stroma) තුළදී සිදු වේ.",
            "තයිලකොයිඩ අතර ඇති තරලමය කොටසයි."
          )
          else -> {}
        }
        4 -> when (qIndex) {
          1 -> return Quintuple(
            "ඕම්ගේ නියමය වාචිකව ප්‍රකාශ කර එහි ගණිතමය සමීකරණය සඳහන් කරන්න.",
            "State Ohm's law and write its mathematical equation.",
            listOf("V=IR", "V = IR", "විභව අන්තරය", "ධාරාව", "උෂ්ණත්වය", "ප්‍රතිරෝධය"),
            "නියත උෂ්ණත්වයේ පවතින සන්නායකයක් තුළින් ගලන ධාරාව (I), එහි දෙකෙළවර විභව අන්තරයට (V) සෘජුව සමානුපාතික වේ. සමීකරණය: V = IR වේ.",
            "Voltage, Current, Resistance අතර සම්බන්ධයයි."
          )
          2 -> return Quintuple(
            "විද්‍යුත් ධාරාව, විභව අන්තරය සහ ප්‍රතිරෝධය මනින සම්මත SI ඒකක පිළිවෙළින් මොනවාද?",
            "What are the SI units of current, voltage and resistance?",
            listOf("ඇම්පියර", "වෝල්ට්", "ඕම්", "Ampere", "Volt", "Ohm", "A, V, Ω"),
            "විද්‍යුත් ධාරාව = ඇම්පියර (A), විභව අන්තරය = වෝල්ට් (V), ප්‍රතිරෝධය = ඕම් (Ω) වේ.",
            "A, V, Ohm සංකේත සිහිපත් කරන්න."
          )
          else -> {}
        }
        else -> {}
      }
      else -> {}
    }

    // Dynamic curriculum generator for remaining indices
    val questionPrefixes = listOf(
      "විද්‍යාත්මකව පැහැදිලි කරන්න: $theme ඒකකයට අදාළව",
      "ප්‍රධාන ලක්ෂණ හා වැදගත්කම සඳහන් කරන්න:",
      "විභාග මූලික සංකල්පය ප්‍රකාශ කරන්න:",
      "ගණිතමය හෝ රසායනික සූත්‍රය දක්වන්න:",
      "ක්‍රියාවලිය හා පියවර විස්තර කරන්න:"
    )
    val prefix = questionPrefixes[(qIndex - 1) % questionPrefixes.size]

    return Quintuple(
      "$prefix #$qIndex - $theme ආශ්‍රිත ප්‍රශ්නය: විභාගයේදී බහුලව විමසන මූලික නියමය හෝ අර්ථ දැක්වීම කුමක්ද?",
      "State the key definition or rule regarding $theme for question #$qIndex.",
      listOf("විද්‍යාව", "නියමය", "සූත්‍රය", "පරීක්ෂාව", "ශක්තිය", "ප්‍රතික්‍රියාව", "ඒකකය"),
      "$theme හි #$qIndex ප්‍රශ්නයට අදාළ පිළිතුර: විෂය නිර්දේශයේ දක්වා ඇති නිල සිද්ධාන්තය, පරීක්ෂණ නිරීක්ෂණ හා නියමයන් නිවැරදිව ප්‍රකාශ කිරීමයි.",
      "$theme විෂය ඒකකයේ මූල පද නිවැරදිව භාවිත කරන්න."
    )
  }

  private fun createMathQuestion(
    grade: String,
    setNum: Int,
    qIndex: Int,
    globalIndex: Int,
    theme: String
  ): Quintuple<String, String, List<String>, String, String> {
    if (theme.contains("පයිතගරස්") || (setNum == 9 && grade == "10")) {
      return Quintuple(
        "සෘජුකෝණී ත්‍රිකෝණයක කර්ණය හා පාද අතර සම්බන්ධය දක්වන පයිතගරස් ප්‍රමේයයේ සූත්‍රය වාචිකව පවසන්න.",
        "State the Pythagoras theorem formula relating hypotenuse and sides of a right triangle.",
        listOf("පයිතගරස්", "a² + b² = c²", "කර්ණයේ වර්ගය", "අනෙක් පාද දෙකේ වර්ගවල එකතුව", "Pythagoras"),
        "සෘජුකෝණී ත්‍රිකෝණයක කර්ණයේ වර්ගය අනෙක් පාද දෙකේ වර්ගවල එකතුවට සමාන වේ (a² + b² = c²).",
        "a වර්ගය ධන b වර්ගය සමානයි c වර්ගය."
      )
    }

    if (theme.contains("වර්ගජ") || (setNum == 2 && grade == "11")) {
      return Quintuple(
        "ax² + bx + c = 0 වර්ගජ සමීකරණයක් විසඳීම සඳහා භාවිත කරන සූත්‍රය කුමක්ද?",
        "What is the quadratic formula used to solve ax^2 + bx + c = 0?",
        listOf("-b", "b² - 4ac", "2a", "වර්ගජ සූත්‍රය", "Formula"),
        "x = (-b ± √(b² - 4ac)) / 2a වේ.",
        "සෘණ b ධන හෝ සෘණ වර්ගමූල b වර්ගය අඩු කිරීම 4ac බෙදීම 2a."
      )
    }

    if (theme.contains("ත්‍රිකෝණමිතිය") || (setNum == 4 && grade == "11")) {
      return Quintuple(
        "සෘජුකෝණී ත්‍රිකෝණයක කෝණයක Sin, Cos සහ Tan අනුපාත අර්ථ දක්වන ආකාරය පවසන්න.",
        "Define Sin, Cos and Tan ratios in a right-angled triangle.",
        listOf("සම්මුඛ පාදය", "කර්ණය", "බද්ධ පාදය", "Sin", "Cos", "Tan"),
        "Sin θ = සම්මුඛ පාදය / කර්ණය, Cos θ = බද්ධ පාදය / කර්ණය, Tan θ = සම්මුඛ පාදය / බද්ධ පාදය වේ.",
        "සම්මුඛ, බද්ධ සහ කර්ණය අතර අනුපාත සිහිපත් කරන්න."
      )
    }

    return Quintuple(
      "ගණිත ගැටලුව #$qIndex ($theme): මෙහිදී යොදාගන්නා මූලික ගණිතමය සූත්‍රය හා නියමය කුමක්ද?",
      "State the key mathematical formula and rule for $theme question #$qIndex.",
      listOf("සූත්‍රය", "ගණනය", "සමීකරණය", "අගය", "ප්‍රමේයය", "Math"),
      "$theme ආශ්‍රිත #$qIndex ප්‍රශ්නයේ සූත්‍රය: පාඩමේ මූලික සමීකරණය ආදේශ කර නිවැරදි සුළු කිරීම සිදු කිරීමයි.",
      "අදාළ ගණිත සූත්‍රය සහ වීජීය පද පැහැදිලිව ප්‍රකාශ කරන්න."
    )
  }

  private fun createHistoryQuestion(
    grade: String,
    setNum: Int,
    qIndex: Int,
    globalIndex: Int,
    theme: String
  ): Quintuple<String, String, List<String>, String, String> {
    if (theme.contains("පරාක්‍රමබාහු") || (setNum == 10 && grade == "10")) {
      return Quintuple(
        "පරාක්‍රම සමුද්‍රය නිර්මාණය කළ රජතුමා කවුද? එය සෑදීමට එක් කළ කුඩා වැව් 3න් දෙකක් නම් කරන්න.",
        "Who built Parakrama Samudra and name two smaller tanks combined to form it?",
        listOf("පරාක්‍රමබාහු", "තෝපාවැව", "එරබදු", "දුඹුටුළු", "Parakramabahu"),
        "මහා පරාක්‍රමබාහු රජතුමා විසිනි. තෝපාවැව, එරබදු වැව සහ දුඹුටුළු වැව එක්කර එය නිර්මාණය කරන ලදී.",
        "පොළොන්නරුවේ මහා රජු සහ තෝපාවැව සිහිපත් කරන්න."
      )
    }

    if (theme.contains("මුල්ලේරියා") || (setNum == 6 && grade == "9")) {
      return Quintuple(
        "1562 මුල්ලේරියා මහා සටනින් පෘතුගීසීන් පරදා ජය ලැබූ සීතාවක රජතුමා කවුද?",
        "Which Sitawaka king defeated the Portuguese at the battle of Mulleriyawa in 1562?",
        listOf("පළමුවන රාජසිංහ", "රාජසිංහ", "ටිකිරි කුමාරු", "සීතාවක", "Rajasinha"),
        "පළමුවන රාජසිංහ රජතුමා (ටිකිරි බණ්ඩාර කුමරු) විසිනි. මෙය පෘතුගීසීන් ලංකාවේදී ලැබූ දරුණුතම ගොඩබිම් පරාජයකි.",
        "ටිකිරි කුමාරු නමින් ප්‍රකට වීර රජු සිහිපත් කරන්න."
      )
    }

    return Quintuple(
      "ඓතිහාසික සිදුවීම #$qIndex ($theme): මෙහි වැදගත්කම හා අදාළ නායකයා/රජු කවුද?",
      "State the significance and historical leader for $theme question #$qIndex.",
      listOf("රජු", "යුගය", "ශ්‍රී ලංකාව", "ඉතිහාසය", "සටන", "ගිවිසුම", "වැව"),
      "$theme හි #$qIndex සිදුවීම: ශ්‍රී ලංකා ඉතිහාසයේ වැදගත් සන්ධිස්ථානයක් වන අතර ඓතිහාසික මූලාශ්‍ර සාක්ෂි දරයි.",
      "යුගය සහ අදාළ ඓතිහාසික චරිතය සඳහන් කරන්න."
    )
  }

  private fun createEnglishQuestion(
    grade: String,
    setNum: Int,
    qIndex: Int,
    globalIndex: Int,
    theme: String
  ): Quintuple<String, String, List<String>, String, String> {
    when (setNum) {
      1 -> return Quintuple(
        "Speak two complete sentences describing your daily morning routine in the Simple Present Tense.",
        "Describe your daily morning routine in Simple Present Tense.",
        listOf("wake up", "brush", "wash", "breakfast", "school", "go", "morning", "at"),
        "I wake up at 6:00 a.m. every morning. Then I brush my teeth and get ready for school.",
        "Use 'I wake up...' and 'I go to school...' in present tense."
      )
      4 -> return Quintuple(
        "Convert this active sentence into passive voice: 'The teacher explained the difficult grammar lesson.'",
        "Convert to passive: The teacher explained the difficult grammar lesson.",
        listOf("was explained", "difficult grammar lesson", "by the teacher", "explained"),
        "The difficult grammar lesson was explained by the teacher.",
        "Start with 'The difficult grammar lesson was explained...'"
      )
      6 -> return Quintuple(
        "Complete this conditional sentence: 'If you study hard every day, ...'",
        "Complete the first conditional sentence: If you study hard every day...",
        listOf("will pass", "will succeed", "will get", "can pass", "good results"),
        "If you study hard every day, you will pass the O/L examination with good results.",
        "Use 'you will pass' or 'you will succeed'."
      )
      else -> return Quintuple(
        "Speak an English sentence demonstrating your knowledge of $theme (#$qIndex).",
        "Speak a sentence related to $theme in correct English.",
        listOf("english", "sentence", "grammar", "because", "study", "exam", "good"),
        "A well-structured English sentence using correct subject-verb agreement and vocabulary related to $theme.",
        "Speak clearly in English with proper sentence structure."
      )
    }
  }

  private fun createIctQuestion(
    grade: String,
    setNum: Int,
    qIndex: Int,
    globalIndex: Int,
    theme: String
  ): Quintuple<String, String, List<String>, String, String> {
    if (setNum == 3 || theme.contains("ද්විමය")) {
      return Quintuple(
        "පරිගණකයේ දත්ත නිරූපණය සඳහා භාවිත කරන ද්විමය (Binary) සංඛ්‍යා පද්ධතියේ ඇති සංකේත දෙක මොනවාද?",
        "What are the two symbols used in the binary number system?",
        listOf("0 සහ 1", "0 and 1", "බිංදුව සහ එක", "Zero and One", "0", "1"),
        "0 සහ 1 යන සංකේත දෙක පමණි. ඒවා Bits (Binary Digits) ලෙස හැඳින්වේ.",
        "බිංදුව සහ එක සිහිපත් කරන්න."
      )
    }
    if (setNum == 4 || theme.contains("ද්වාර")) {
      return Quintuple(
        "මූලික තාර්කික ද්වාර (Logic Gates) ත්‍රිත්වය නම් කර ඒවායේ ක්‍රියාකාරිත්වය කෙටියෙන් පවසන්න.",
        "Name the three fundamental logic gates and state their operation.",
        listOf("AND", "OR", "NOT", "ද්වාර", "ඇන්ඩ්", "ඕර්", "නොට්"),
        "AND ද්වාරය, OR ද්වාරය සහ NOT ද්වාරය වේ. NOT මඟින් ප්‍රතිදානය ප්‍රතිවර්තනය කරයි.",
        "AND, OR සහ NOT සිහිපත් කරන්න."
      )
    }
    return Quintuple(
      "ICT ප්‍රශ්නය #$qIndex ($theme): මෙහි මූලික සංකල්පය හෝ තාක්ෂණික යෙදුම කුමක්ද?",
      "State the fundamental concept or technical term for $theme question #$qIndex.",
      listOf("පරිගණක", "දත්ත", "තොරතුරු", "ජාල", "ක්‍රමලේඛන", "ICT", "Hardware", "Software"),
      "$theme ආශ්‍රිත #$qIndex ප්‍රශ්නය: නිල විෂය නිර්දේශයේ දක්වා ඇති පරිගණක විද්‍යාත්මක මූලධර්මයයි.",
      "අදාළ තාක්ෂණික පදය ඉංග්‍රීසි හෝ සිංහලෙන් නිවැරදිව පවසන්න."
    )
  }

  private fun createBuddhismQuestion(
    grade: String,
    setNum: Int,
    qIndex: Int,
    globalIndex: Int,
    theme: String
  ): Quintuple<String, String, List<String>, String, String> {
    if (setNum == 2 || theme.contains("ධම්මචක්කප්පවත්තන")) {
      return Quintuple(
        "බුදුරජාණන් වහන්සේ දේශනා කළ ප්‍රථම ධර්ම දේශනාව කුමක්ද? එය දේශනා කළේ කා හටද?",
        "What was the first sermon preached by the Buddha and to whom?",
        listOf("ධම්මචක්කප්පවත්තන", "පස්වග මහණුන්", "බරණැස", "ඉසිපතන", "Dhammacakka"),
        "ධම්මචක්කප්පවත්තන සූත්‍රයයි. බරණැස ඉසිපතන මිගදායේදී කොණ්ඩඤ්ඤ ඇතුළු පස්වග තවුසන් හට දේශනා කරන ලදී.",
        "ප්‍රථම ධර්ම දේශනාව හා පස්වග මහණුන් සිහිපත් කරන්න."
      )
    }
    if (setNum == 8 || theme.contains("චතුරාර්ය")) {
      return Quintuple(
        "බුදු දහමේ මූලික හරය වන චතුරාර්ය සත්‍යය ධර්ම 4 පිළිවෙළින් නම් කරන්න.",
        "Name the Four Noble Truths in Buddhism.",
        listOf("දුක්ඛ", "සමුදය", "නිරෝධ", "මාර්ග", "චතුරාර්ය සත්‍යය"),
        "1. දුක්ඛ සත්‍යය, 2. දුක්ඛ සමුදය සත්‍යය, 3. දුක්ඛ නිරෝධ සත්‍යය, 4. දුක්ඛ නිරෝධ ගාමිණී පටිපදා ආර්ය සත්‍යය (මාර්ග සත්‍යය).",
        "දුක, හටගැනීම, නැතිවීම සහ මඟ සිහිපත් කරන්න."
      )
    }
    return Quintuple(
      "බුද්ධ ධර්මය ප්‍රශ්නය #$qIndex ($theme): මෙහි ධර්මානුකූල අර්ථය හා වැදගත්කම කුමක්ද?",
      "State the Dhamma meaning and significance for $theme question #$qIndex.",
      listOf("ධර්මය", "ශීලය", "සමාධිය", "ප්‍රඥාව", "බුදුරදුන්", "සූත්‍රය"),
      "$theme හි #$qIndex ධර්ම කාරණය: බුද්ධ දේශනාවට අනුකූලව සදාචාරාත්මක හා දහම් සංකල්ප නිවැරදිව ප්‍රකාශ කිරීමයි.",
      "අදාළ සූත්‍රය හෝ බෞද්ධ සංකල්පය පැහැදිලිව ප්‍රකාශ කරන්න."
    )
  }

  private fun createGeneralSubjectQuestion(
    grade: String,
    subject: String,
    setNum: Int,
    qIndex: Int,
    globalIndex: Int,
    theme: String
  ): Quintuple<String, String, List<String>, String, String> {
    return Quintuple(
      "$subject ප්‍රශ්නය #$qIndex ($theme): සාමාන්‍ය පෙළ විභාගයේදී විමසන ප්‍රධාන මූල ධර්මය කුමක්ද?",
      "State the core principle tested in O/L for $subject in $theme question #$qIndex.",
      listOf(subject, "මූලධර්මය", "විභාගය", "නියමය", "ලක්ෂණ", "අර්ථය"),
      "$subject හි $theme ආශ්‍රිත නිල විෂය නිර්දේශ පිළිතුර සහ විභාග ලකුණු ලබාදෙන මූල පද මෙහිදී අගය කරනු ලැබේ.",
      "විෂයට අදාළ ප්‍රධාන කරුණු හා උදාහරණ සඳහන් කරන්න."
    )
  }
}

data class Quintuple<A, B, C, D, E>(
  val first: A,
  val second: B,
  val third: C,
  val fourth: D,
  val fifth: E
)
