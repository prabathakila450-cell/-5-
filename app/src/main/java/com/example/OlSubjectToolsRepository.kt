package com.example

import androidx.compose.ui.graphics.Color

object OlSubjectToolsRepository {
  val subjectsList = listOf(
    OlSubjectToolCategory("විද්‍යාව", "Science", "🔬", Color(0xFF0D9488), 100),
    OlSubjectToolCategory("ගණිතය", "Mathematics", "📐", Color(0xFF2563EB), 100),
    OlSubjectToolCategory("ඉංග්‍රීසි", "English Language", "✍️", Color(0xFF0284C7), 100),
    OlSubjectToolCategory("ඉතිහාසය", "History", "🏛️", Color(0xFFD97706), 100)
  )

  fun getToolsForSubject(subjectName: String): List<OlSubjectToolItem> {
    return when {
      subjectName.contains("විද්‍යා") || subjectName.contains("Science", ignoreCase = true) ->
        OlSubjectToolsScienceData.tools
      subjectName.contains("ගණිත") || subjectName.contains("Math", ignoreCase = true) ->
        OlSubjectToolsMathsData.tools
      subjectName.contains("ඉංග්‍රීසි") || subjectName.contains("English", ignoreCase = true) ->
        OlSubjectToolsEnglishData.tools
      subjectName.contains("ඉතිහාස") || subjectName.contains("History", ignoreCase = true) ->
        OlSubjectToolsHistoryData.historyTools
      else -> OlSubjectToolsScienceData.tools
    }
  }

  fun getToolsForSubjectAndGroup(subjectName: String, groupNumber: Int): List<OlSubjectToolItem> {
    val all = getToolsForSubject(subjectName)
    return all.filter { it.groupNumber == groupNumber }
  }

  fun getGroupName(subjectName: String, groupNumber: Int): String {
    return when {
      subjectName.contains("විද්‍යා") || subjectName.contains("Science", ignoreCase = true) -> when (groupNumber) {
        1 -> "ජීව විද්‍යාව I (සෛල, පටක & ඉන්ද්‍රිය පද්ධති)"
        2 -> "ජීව විද්‍යාව II (ප්‍රභාසංස්ලේෂණය, ජාන & පරිසරය)"
        3 -> "රසායන විද්‍යාව I (පදාර්ථය & පරමාණුක ව්‍යුහය)"
        4 -> "රසායන විද්‍යාව II (මවුලය, රසායනික ගණනය & ශක්තිය)"
        5 -> "රසායන විද්‍යාව III (අම්ල, භෂ්ම, ලවණ & ලෝහ විද්‍යාව)"
        6 -> "භෞතික විද්‍යාව I (චලිතය, නිව්ටන් නියම & බලය)"
        7 -> "භෞතික විද්‍යාව II (කාර්යය, ශක්තිය, ජලස්ථිතික පීඩනය & තාපය)"
        8 -> "භෞතික විද්‍යාව III (ආලෝකය & ප්‍රකාශ විද්‍යාව)"
        9 -> "භෞතික විද්‍යාව IV (තරංග, ශබ්දය & විද්‍යුත් චුම්භක තරංග)"
        10 -> "භෞතික විද්‍යාව V (ධාරා විද්‍යුතය & ඉලෙක්ට්‍රොනික විද්‍යාව)"
        else -> "කාණ්ඩය $groupNumber"
      }
      subjectName.contains("ගණිත") || subjectName.contains("Math", ignoreCase = true) -> when (groupNumber) {
        1 -> "සංඛ්‍යා, භාග, ප්‍රතිශත & මූලික ගණිතය"
        2 -> "වීජ ගණිත ප්‍රකාශන, සාධක & සූත්‍ර සුළුකිරීම්"
        3 -> "සමීකරණ & අසමානතා (රේඛීය, සමගාමී, වර්ගජ)"
        4 -> "ජ්‍යාමිතිය I (කෝණ, ත්‍රිකෝණ & සමාන්තරාස්‍ර)"
        5 -> "ජ්‍යාමිතිය II (වෘත්ත ප්‍රමේය & කෝණ සම්බන්ධතා)"
        6 -> "ත්‍රිකෝණමිතිය (Sin, Cos, Tan & උන්නතාංශ)"
        7 -> "වර්ගඵලය, පරිමාව & ඝන වස්තු සූත්‍ර"
        8 -> "ප්‍රස්තාර, අනුක්‍රමණය & සමීකරණ ආලේඛ"
        9 -> "සම්භාවිතාව & කුලක (වෙන් රූප & ගස් රූප)"
        10 -> "සංඛ්‍යානය, මධ්‍යන්‍යය, මාතය & සමුච්චිත සංඛ්‍යාතය"
        else -> "කාණ්ඩය $groupNumber"
      }
      subjectName.contains("ඉංග්‍රීසි") || subjectName.contains("English", ignoreCase = true) -> when (groupNumber) {
        1 -> "Essential Tenses (Present, Past, Future Mastery)"
        2 -> "Active to Passive Voice Transformation"
        3 -> "Direct and Reported Speech Precision"
        4 -> "Modal Auxiliary Verbs & Nuance"
        5 -> "Prepositions & Prepositional Phrases"
        6 -> "Conditionals (Zero, 1st, 2nd, 3rd Types)"
        7 -> "Conjunctions, Relative Clauses & Sentence Linkers"
        8 -> "Formal & Informal Letter Writing Frameworks"
        9 -> "Notice, Note, Invitation & Diary Entry Mastery"
        10 -> "Essay, Article & Speech Structure Frameworks"
        else -> "Group $groupNumber"
      }
      subjectName.contains("ඉතිහාස") || subjectName.contains("History", ignoreCase = true) -> when (groupNumber) {
        1 -> "මූලාශ්‍ර හා පුරාවිද්‍යා මෙවලම්"
        2 -> "මුල් ඓතිහාසික යුගය හා පණ්ඩුකාභය"
        3 -> "මහින්දාගමනය හා සංස්කෘතික විප්ලවය"
        4 -> "දුටුගැමුණු යුගය හා එක්සේසත් කිරීම"
        5 -> "වළගම්බා යුගය හා ත්‍රිපිටකය ග්‍රන්ථාරූඪ කිරීම"
        6 -> "වාරි ශිෂ්ටාචාරයේ ස්වර්ණ යුගය (වසභ, මහසෙන්, ධාතුසේන)"
        7 -> "සීගිරිය හා කාශ්‍යප යුගය"
        8 -> "පොළොන්නරු යුගය (1 වන විජයබාහු & පරාක්‍රමබාහු)"
        9 -> "දඹදෙණිය, යාපහුව, කුරුණෑගල, ගම්පොළ හා කෝට්ටේ"
        10 -> "යුරෝපීය ආක්‍රමණ, උඩරට රාජධානිය හා නිදහස් සටන්"
        else -> "කාණ්ඩය $groupNumber"
      }
      else -> "කාණ්ඩය $groupNumber"
    }
  }
}
