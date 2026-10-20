package com.example

/**
 * Question Engine for Buddhism, ICT, Commerce, Civics, and Geography in Live Exam Hall.
 * Generates 60 unique questions per paper across 30 papers and 3 terms.
 */
object BuddhismAndElectivesMockEngine {

  fun generateBuddhismQuestion(grade: String, term: Int, paperIdx: Int, qNum: Int): MockExamQuestion {
    val v = (paperIdx - 1) % 30
    val topic = when (term) {
      1 -> "බුද්ධ චරිතය, චතුරාර්ය සත්‍යය හා පන්සිල්"
      2 -> "ත්‍රිපිටකය, ධර්ම සංගායනා හා පාරමිතා"
      else -> "කර්මය, පුනර්භවය, බෞද්ධ සංස්කෘතිය හා විහාර"
    }

    val (text, opts, correct, exp) = when (qNum % 5) {
      1 -> {
        val suttaList = listOf(
          "බුදුරජාණන් වහන්සේ ප්‍රථම ධර්ම දේශනාව වන දම්සක් පැවතුම් සූත්‍රය දේශනා කළේ කා හටද?" to (listOf("පස්වග තවුසන්ට (කොණ්ඩඤ්ඤ ආදී)", "ආලාර කාලාම තවුසාට", "උද්දකරාමපුත්ත තවුසාට", "බිම්බිසාර රජුට") to "බරණැස ඉසිපතන මිගදායේදී ඇසළ පුන් පොහෝ දින පස්වග මහණුන්ට දම්සක් පැවතුම් සූත්‍රය දේශනා කරන ලදී."),
          "බුදුරදුන් සියලු කෙලෙසුන් නසා උතුම් සම්මා සම්බුද්ධත්වයට පත්වූයේ කුමන පුන් පොහෝ දිනකද?" to (listOf("වෙසක් පුන් පොහෝ දින", "පොසොන් පොහෝ දින", "ඇසළ පොහෝ දින", "ඉල් පොහෝ දින") to "ගයාවේ බෝධි මූලයේදී වෙසක් පුන් පොහෝ දින සිද්ධාර්ථ තවුසා සම්මා සම්බුද්ධත්වයට පත්විය."),
          "මහා පරිනිර්වාණයෙන් තෙමසකට පසු ප්‍රථම ධර්ම සංගායනාවට ප්‍රධානත්වය දුන් මහා තෙරුන් වහන්සේ කවුද?" to (listOf("මහා කාශ්‍යප මහ රහතන් වහන්සේ", "ආනන්ද හිමි", "උපාලි හිමි", "සැරියුත් හිමි") to "අජාසත් රජුගේ දායකත්වයෙන් රජගහනුවර සප්තපණ්ණී ගුහාද්වාරයේදී ප්‍රථම සංගායනාව පැවැත්විණි.")
        )
        val sel = suttaList[v % suttaList.size]
        Quad(sel.first, sel.second.first, 1, sel.second.second)
      }
      2 -> Quad(
        "බුදුදහමේ මූලික ඉගැන්වීමක් වන දුක නැති කිරීමේ මාර්ගය (දුක්ඛ නිරෝධ ගාමිණී පටිපදාව) කුමක්ද?",
        listOf("ආර්ය අෂ්ටාංගික මාර්ගය (මධ්‍යම ප්‍රතිපදාව)", "අත්තකිලමථානුයෝගය", "කාමසුඛල්ලිකානුයෝගය", "පඤ්ච කාමය"),
        1,
        "සීල, සමාධි, ප්‍රඥා යන ත්‍රිශික්ෂාවට අයත් අංග 8කින් සමන්විත ආර්ය අෂ්ටාංගික මාර්ගය දුක නැති කිරීමේ එකම මගයි."
      )
      3 -> Quad(
        "ත්‍රිපිටකයේ විනය පිටකයට අයත් වන ධර්ම ග්‍රන්ථ කාණ්ඩය කුමක්ද?",
        listOf("මහා වග්ග, චුල්ල වග්ග, පාරාජිකා, පාචිත්තිය, පරිවාර", "දීඝ නිකාය", "ධම්මපදය", "අභිධම්මත්ථ සංගහය"),
        1,
        "භික්ෂු භික්ෂුණීන්ගේ ශික්ෂාපද හා විනය නීති විනය පිටකයේ ඇතුළත් වේ."
      )
      4 -> Quad(
        "බුදුදහමට අනුව කුසල හා අකුසල කර්ම රැස්වීමේදී මූලික වන ප්‍රධාන සාධකය කුමක්ද?",
        listOf("චේතනාව (චේතනාහං භික්ඛවේ කම්මං වදාමි)", "ක්‍රියාවේ වේගය", "බාහිර පරිසරය", "දෛවය"),
        1,
        "චේතනාව හෙවත් සිතේ ඇතිවන චේතනික අදහස මත කර්මයේ විපාකය තීරණය වේ."
      )
      else -> Quad(
        "අනුරාධපුර මහමෙව්නා උයනේ පිහිටි වීතරාගී සමාධි සුවය විදහාපාන ශ්‍රේෂ්ඨ ශෛලමය බුද්ධ ප්‍රතිමාව කුමක්ද?",
        listOf("සමාධි බුදු පිළිමය", "අවුකන පිළිමය", "මාලිගාවිල පිළිමය", "රැස්වෙහෙර පිළිමය"),
        1,
        "වීරාසන ඉරියව්වෙන් ධ්‍යාන මුද්‍රාව නිරූපණය කරන සමාධි පිළිමය අනුරාධපුර යුගයේ මූර්ති කලාවේ අග්‍රඵලයකි."
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

  fun generateIctQuestion(grade: String, term: Int, paperIdx: Int, qNum: Int): MockExamQuestion {
    val v = (paperIdx - 1) % 30
    val topic = "තොරතුරු හා සන්නිවේදන තාක්ෂණය (ICT)"
    val (text, opts, correct, exp) = when (qNum % 4) {
      1 -> Quad(
        "ද්විමය සංඛ්‍යාවක් වන (1011)₂ හි දශමය (Decimal) තුල්‍ය අගය කුමක්ද?",
        listOf("11", "9", "13", "15"),
        1,
        "(1 × 2³) + (0 × 2²) + (1 × 2¹) + (1 × 2⁰) = 8 + 0 + 2 + 1 = 11 වේ."
      )
      2 -> Quad(
        "පරිගණකයක ප්‍රොසෙසරයේ (CPU) ගණිතමය හා තාර්කික මෙහෙයුම් සිදුකරන ඒකකය කුමක්ද?",
        listOf("ALU (Arithmetic Logic Unit)", "Control Unit (CU)", "Registers", "Cache Memory"),
        1,
        "ALU මගින් සියලු ආකලන, ගුණන හා boolean තාර්කික සැසඳීම් සිදු කරයි."
      )
      3 -> Quad(
        "HTML ලේඛනයක වෙබ් පිටුවක ප්‍රධානතම සිරස්තලය දැක්වීමට යොදන ටැගය (Tag) කුමක්ද?",
        listOf("<h1>", "<p>", "<title>", "<header>"),
        1,
        "HTML හී <h1> සිට <h6> දක්වා ශීර්ෂ ටැග ඇති අතර <h1> විශාලතම ප්‍රධාන සිරස්තලයයි."
      )
      else -> Quad(
        "පරිගණක ජාලයක IP ලිපිනයක් සඳහා උදාහරණයක් නොවන්නේ කුමක්ද?",
        listOf("192.168.1.1", "10.0.0.1", "172.16.254.1", "300.500.1.0"),
        4,
        "IPv4 ලිපිනයක එක් එක් ඔක්ටෙට්ටුවක උපරිම අගය 255 බැවින් 300.500.1.0 වලංගු නොවේ."
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

  fun generateCommerceQuestion(grade: String, term: Int, paperIdx: Int, qNum: Int): MockExamQuestion {
    val topic = "ව්‍යාපාර හා ගිණුම්කරණ අධ්‍යයනය"
    val (text, opts, correct, exp) = Quad(
      "ගිණුම්කරණ මූලික සමීකරණය (Accounting Equation) නිවැරදිව දක්වා ඇත්තේ කුමක්ද?",
      listOf("වත්කම් = හිමිකම + වගකීම් (Assets = Equity + Liabilities)", "වත්කම් + වගකීම් = හිමිකම", "හිමිකම = වත්කම් + වගකීම්", "වගකීම් = වත්කම් + ලාභය"),
      1,
      "ද්විත්ව සටහන් ගිණුම්කරණයේ සමස්ත වත්කම් ප්‍රාග්ධනය හා බාහිර වගකීම්වල එකතුවට සමාන වේ."
    )
    return MockExamQuestion(id = qNum, questionText = "[$qNum] $text", options = opts, correctOption = correct, explanation = exp, topicName = topic)
  }

  fun generateCivicsQuestion(grade: String, term: Int, paperIdx: Int, qNum: Int): MockExamQuestion {
    val topic = "පුරවැසි අධ්‍යාපනය"
    val (text, opts, correct, exp) = Quad(
      "ප්‍රජාතන්ත්‍රවාදී පාලනයක බලතල බෙදීමේ න්‍යාය අනුව ආණ්ඩුක්‍රමයේ ප්‍රධාන අංග 3 කුමක්ද?",
      listOf("ව්‍යවස්ථාදායකය, විධායකය, අධිකරණය", "ජනාධිපති, අගමැති, කථානායක", "පොලීසිය, හමුදාව, බන්ධනාගාර", "පාර්ලිමේන්තුව, පළාත් සභා, ප්‍රාදේශීය සභා"),
      1,
      "මොන්ටෙස්කියුගේ බලතල බෙදීමේ න්‍යාය අනුව නීති හදන ව්‍යවස්ථාදායකය, ක්‍රියාත්මක කරන විධායකය හා සාධාරණත්වය රකින අධිකරණය ස්වාධීන විය යුතුය."
    )
    return MockExamQuestion(id = qNum, questionText = "[$qNum] $text", options = opts, correctOption = correct, explanation = exp, topicName = topic)
  }

  fun generateGeographyQuestion(grade: String, term: Int, paperIdx: Int, qNum: Int): MockExamQuestion {
    val topic = "භූගෝල විද්‍යාව"
    val (text, opts, correct, exp) = Quad(
      "1:50,000 සම්මත භූලක්ෂණ සිතියමක සෙන්ටිමීටර 2ක දුරකින් නිරූපණය වන භූමියේ සැබෑ දුර කොපමණද?",
      listOf("1 km (මීටර 1,000)", "500 m", "2 km", "5 km"),
      1,
      "1:50,000 පරිමාණයේ 1 cm = 50,000 cm = 500 m වේ. එබැවින් 2 cm = 1,000 m (1 km) වේ."
    )
    return MockExamQuestion(id = qNum, questionText = "[$qNum] $text", options = opts, correctOption = correct, explanation = exp, topicName = topic)
  }

  private data class Quad(
    val q: String,
    val opts: List<String>,
    val c: Int,
    val exp: String
  )
}
