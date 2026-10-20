package com.example

import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

// ==============================================================================
// 10 හා 11 ශ්‍රේණි භූගෝල විද්‍යාව - INTERACTIVE AUTO-CHECKER MODELS
// ==============================================================================

enum class GeoGradeLevel(val label: String, val badgeColor: Color) {
  ALL("සියලු ශ්‍රේණි (10, 11)", Color(0xFF0F172A)),
  GRADE_10("10 ශ්‍රේණිය", Color(0xFF0369A1)),
  GRADE_11("11 ශ්‍රේණිය (O/L)", Color(0xFF047857))
}

enum class GeoUnitCategory(
  val displayName: String,
  val iconEmoji: String,
  val primaryColor: Color,
  val bgLightColor: Color
) {
  ALL("සියලු පාඩම්", "🌍", Color(0xFF1E293B), Color(0xFFF1F5F9)),
  EARTH_COMPOSITION("පෘථිවියේ සංයුතිය හා ව්‍යුහය", "🪐", Color(0xFF0284C7), Color(0xFFE0F2FE)),
  PHYSICAL_FEATURES("ප්‍රධාන භෞතික ලක්ෂණ & සිතියම්", "⛰️", Color(0xFF0D9488), Color(0xFFCCFBF1)),
  AGRICULTURE_LAND("කෘෂිකාර්මික භූමි පරිභෝගය", "🌾", Color(0xFF15803D), Color(0xFFDCFCE7)),
  SRI_LANKA_INDUSTRIES("ශ්‍රී ලංකාවේ කර්මාන්ත & ඛනිජ", "🏭", Color(0xFFB45309), Color(0xFFFEF3C7)),
  NATURAL_RESOURCES("ලෝක හා ලංකා ස්වාභාවික සම්පත්", "💎", Color(0xFF7C3AED), Color(0xFFEDE9FE)),
  POPULATION_DEMOGRAPHY("ලෝක හා ලංකා ජන සංඛ්‍යාව", "👥", Color(0xFFBE185D), Color(0xFFFCE7F3)),
  DEVELOPMENT_HDI("සංවර්ධනය & මානව සංවර්ධන දර්ශකය", "📈", Color(0xFF2563EB), Color(0xFFDBEAFE)),
  NATURAL_HAZARDS("ස්වාභාවික උපද්‍රව & ආපදා කළමනාකරණය", "⚠️", Color(0xFFDC2626), Color(0xFFFEE2E2)),
  CLIMATE_CHANGE("දේශගුණික වෙනස්වීම් & ගෝලීය උණුසුම", "🌡️", Color(0xFFEA580C), Color(0xFFFFEDD5)),
  TOPO_MAPS_50000("1:50,000 භූ ලක්ෂණ සිතියම් & ප්‍රස්තාර", "🗺️", Color(0xFF0F766E), Color(0xFFD1FAE5))
}

data class GeoAutoCheckQuestion(
  val id: String,
  val number: Int,
  val gradeLevel: GeoGradeLevel,
  val category: GeoUnitCategory,
  val unitTitle: String,
  val questionPrompt: String,
  val subQuestionOrContext: String? = null,
  val options: List<String>,
  val correctOptionIndex: Int,
  val explanationSinhala: String,
  val syllabusKeyFact: String,
  val examTip: String,
  val pdfPageRef: String
)

// ==============================================================================
// 100% SYLLABUS ACCURATE DATASET FOR 10 & 11 GEOGRAPHY WORKBOOK
// ==============================================================================

object GeographyAutoCheckerRepository {

  const val pdfDriveUrl = "https://drive.google.com/file/d/1Fc1DqvZWVv7aiIC35KgWfl5Spjj-5WV_/preview"

  val allQuestions: List<GeoAutoCheckQuestion> = listOf(
    // --------------------------------------------------------------------------
    // 🌍 10 ශ්‍රේණිය - 1 වන පාඩම: පෘථිවියේ සංයුතිය
    // --------------------------------------------------------------------------
    GeoAutoCheckQuestion(
      id = "geo_q1_atmosphere_gases",
      number = 1,
      gradeLevel = GeoGradeLevel.GRADE_10,
      category = GeoUnitCategory.EARTH_COMPOSITION,
      unitTitle = "10 ශ්‍රේණිය - පෘථිවියේ සංයුතිය (වායුගෝලීය සංයුතිය)",
      questionPrompt = "වායුගෝලයේ අඩංගු වායූන්ගේ පරිමාව අනුව 78.09% ක් සහ 20.95% ක් ලෙස පිළිවෙළින් අඩංගු වන ප්‍රධාන වායු යුගලය කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 2 - වායුගෝලයේ වායු වර්ග නම් කිරීමේ වට ප්‍රස්තාරය",
      options = listOf(
        "1. ඔක්සිජන් සහ කාබන්ඩයොක්සයිඩ්",
        "2. නයිට්‍රජන් (78.09%) සහ ඔක්සිජන් (20.95%)",
        "3. ආගන් සහ හීලියම්",
        "4. කාබන්ඩයොක්සයිඩ් සහ නයිට්‍රජන්"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "පෘථිවි වායුගෝලයේ මුළු වායු පරිමාවෙන් 78.09% ක් නයිට්‍රජන් (N₂) ද, 20.95% ක් ඔක්සිජන් (O₂) ද අඩංගු වේ. ආගන් 0.93% ක් සහ කාබන්ඩයොක්සයිඩ් 0.03% ක් වන අතර අනෙකුත් සියලු වායු 0.04% ක් පමණ වේ.",
      syllabusKeyFact = "නයිට්‍රජන් 78.09% • ඔක්සිජන් 20.95% • ආගන් 0.93% • කාබන්ඩයොක්සයිඩ් 0.03%",
      examTip = "සමස්ත වායුගෝලීය වායූන්ගෙන් 99% ක්ම නයිට්‍රජන් සහ ඔක්සිජන් වායු දෙකෙන් සමන්විත වේ.",
      pdfPageRef = "පිටුව 2"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q2_earth_spheres",
      number = 2,
      gradeLevel = GeoGradeLevel.GRADE_10,
      category = GeoUnitCategory.EARTH_COMPOSITION,
      unitTitle = "10 ශ්‍රේණිය - පෘථිවියේ සංයුතිය (උප පද්ධති 4)",
      questionPrompt = "පෘථිවි පද්ධතිය සමන්විත වන ප්‍රධාන උප පද්ධති 4 නිවැරදිව දක්වා ඇති පිළිතුර කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 1 - ප්‍රශ්න අංක 01 (i)",
      options = listOf(
        "1. ශිලා ගෝලය, ජල ගෝලය, වායු ගෝලය, ජෛව ගෝලය",
        "2. සූර්ය ගෝලය, චන්ද්‍ර ගෝලය, ගුරුත්ව ගෝලය, චුම්බක ගෝලය",
        "3. මහාද්වීපික කබොල, සාගරික කබොල, ප්‍රාවරය, හරය",
        "4. පරිවර්තී ගෝලය, අපරිවර්තී ගෝලය, මධ්‍ය ගෝලය, තාප ගෝලය"
      ),
      correctOptionIndex = 0,
      explanationSinhala = "පෘථිවි පද්ධතියේ ප්‍රධාන උප පද්ධති 4 වන්නේ: 1. ශිලා ගෝලය (Lithosphere), 2. ජල ගෝලය (Hydrosphere), 3. වායු ගෝලය (Atmosphere), 4. ජෛව ගෝලය (Biosphere) යි. මෙම පද්ධති 4 අතර අන්තර් ක්‍රියාකාරිත්වයෙන් ජීවය පවත්වා ගනී.",
      syllabusKeyFact = "පෘථිවියේ ප්‍රධාන උප පද්ධති 4: ශිලා, ජල, වායු සහ ජෛව ගෝලයයි.",
      examTip = "මෙම පද්ධති 4 එකිනෙක මත රඳා පවතින අන්තර් සම්බන්ධිත ජාලයක් ලෙස ක්‍රියා කරයි.",
      pdfPageRef = "පිටුව 1"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q3_earth_crust_layers",
      number = 3,
      gradeLevel = GeoGradeLevel.GRADE_10,
      category = GeoUnitCategory.EARTH_COMPOSITION,
      unitTitle = "10 ශ්‍රේණිය - පෘථිවියේ සංයුතිය (අභ්‍යන්තර ව්‍යුහය)",
      questionPrompt = "පෘථිවි කබොල සහ ප්‍රාවරය අතර පවතින සීමාකාරී කලාපය හඳුන්වන නම කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 5 - ප්‍රශ්න අංක 05 (iii) C අක්ෂරය",
      options = listOf(
        "1. ගුටෙන්බර්ග් අසන්තතිය",
        "2. මොහොරොවිසික් අසන්තතිය (Mohorovicic Discontinuity)",
        "3. ලේමන් අසන්තතිය",
        "4. තැටි මායිම"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "පෘථිවි කබොල (Crust) සහ ඉහළ ප්‍රාවරය (Upper Mantle) අතර පිහිටි ඝනත්ව වෙනස සනිටුහන් කරන සීමාව 'මොහොරොවිසික් අසන්තතිය' (Moho) ලෙස හැඳින්වේ. ප්‍රාවරය සහ පිටත හරය අතර සීමාව ගුටෙන්බර්ග් අසන්තතියයි.",
      syllabusKeyFact = "කබොල හා ප්‍රාවරය අතර සීමාව = මොහොරොවිසික් අසන්තතිය (Moho)",
      examTip = "මහාද්වීපික කබොල ප්‍රධාන වශයෙන් සියල් (SiAl) වලින්ද, සාගරික කබොල සිමා (SiMa) වලින්ද සමන්විතය.",
      pdfPageRef = "පිටුව 5"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q4_water_distribution",
      number = 4,
      gradeLevel = GeoGradeLevel.GRADE_10,
      category = GeoUnitCategory.EARTH_COMPOSITION,
      unitTitle = "10 ශ්‍රේණිය - ජල ගෝලය (ජල ව්‍යාප්තිය)",
      questionPrompt = "පෘථිවියේ ඇති සමස්ත මිරිදිය ජල ප්‍රතිශතය (2.5%) අතුරින් වැඩිම ප්‍රමාණයක් (79% ක් පමණ) අඩංගු වන්නේ කුමන ස්වරූපයෙන්ද?",
      subQuestionOrContext = "PDF පිටුව 3 - ප්‍රශ්න අංක 03 (i) ජල ගෝල සටහන",
      options = listOf(
        "1. ගංගා සහ ඇළ දොළ ජලය ලෙස",
        "2. ග්ලැසියර හා ධ්‍රැවීය අයිස් තට්ටු (Ice Caps & Glaciers) ලෙස",
        "3. භූගත ජලය ලෙස",
        "4. වායුගෝලීය ජල වාෂ්ප ලෙස"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "පෘථිවියේ මුළු ජලයෙන් 97.5% ක් ලවණ සාගර ජලය වන අතර මිරිදිය ඇත්තේ 2.5% කි. එම මිරිදියෙන් 79% ක් ග්ලැසියර හා අයිස් තට්ටු ලෙස ඝනීභවනය වී ඇති අතර, 20% ක් භූගත ජලයද, මිනිසාට සෘජුව භාවිත කළ හැකි මතුපිට මිරිදිය ඇත්තේ 1% කටත් අඩු ප්‍රමාණයකි.",
      syllabusKeyFact = "මිරිදිය (2.5%) -> ග්ලැසියර 79% + භූගත ජලය 20% + මතුපිට භාවිත ජලය 1%",
      examTip = "මිනිසාට භාවිත කළ හැකි මිරිදිය ප්‍රමාණය සමස්ත ලෝක ජලයෙන් 0.03% ක් තරම් ඉතා සීමිත අගයකි.",
      pdfPageRef = "පිටුව 3"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q5_major_tectonic_plates",
      number = 5,
      gradeLevel = GeoGradeLevel.GRADE_10,
      category = GeoUnitCategory.EARTH_COMPOSITION,
      unitTitle = "10 ශ්‍රේණිය - පෘථිවියේ සංයුතිය (භූ තැටි සංචලනය)",
      questionPrompt = "ලෝකයේ විශාලතම භූ තැටිය (Major Tectonic Plate) වන්නේ කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 6 - ප්‍රධාන හා සුළු තැටි සිතියම",
      options = listOf(
        "1. යුරේසියානු තැටිය",
        "2. පැසිෆික් තැටිය (Pacific Plate)",
        "3. අප්‍රිකානු තැටිය",
        "4. ඉන්දු-ඕස්ට්‍රේලියානු තැටිය"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "පෘථිවියේ විශාලතම භූ තැටිය පැසිෆික් තැටියයි. ප්‍රධාන තැටි 7 කි: පැසිෆික්, යුරේසියානු, අප්‍රිකානු, උතුරු ඇමෙරිකානු, දකුණු ඇමෙරිකානු, ඉන්දු-ඕස්ට්‍රේලියානු සහ ඇන්ටාක්ටික් තැටිය. සුළු තැටි ලෙස නාස්කා, කොකෝස්, කැරිබියන්, අරාබි, පිලිපීන තැටි දැක්විය හැක.",
      syllabusKeyFact = "ප්‍රධාන තැටි 7 කි. විශාලතම තැටිය පැසිෆික් තැටියයි.",
      examTip = "ශ්‍රී ලංකාව සහ ඉන්දියාව පිහිටා ඇත්තේ ඉන්දු-ඕස්ට්‍රේලියානු තැටිය මතයි.",
      pdfPageRef = "පිටුව 6"
    ),

    // --------------------------------------------------------------------------
    // ⛰️ 10 ශ්‍රේණිය - 2 වන පාඩම: පෘථිවියේ ප්‍රධාන භෞතික ලක්ෂණ
    // --------------------------------------------------------------------------
    GeoAutoCheckQuestion(
      id = "geo_q6_continents_oceans",
      number = 6,
      gradeLevel = GeoGradeLevel.GRADE_10,
      category = GeoUnitCategory.PHYSICAL_FEATURES,
      unitTitle = "10 ශ්‍රේණිය - ලෝකයේ ප්‍රධාන භෞතික ලක්ෂණ (මහාද්වීප හා සාගර)",
      questionPrompt = "වපසරිය සහ ජනගහනය අනුව ලොව විශාලතම මහාද්වීපය සහ විශාලතම සාගරය නිවැරදිව දක්වා ඇත්තේ කුමන පිළිතුරෙහිද?",
      subQuestionOrContext = "PDF පිටුව 7 - මහාද්වීප 7 සහ සාගර 5 සිතියම",
      options = listOf(
        "1. අප්‍රිකා මහාද්වීපය සහ අත්ලාන්තික් සාගරය",
        "2. ආසියා මහාද්වීපය සහ පැසිෆික් සාගරය (Asia & Pacific Ocean)",
        "3. උතුරු ඇමෙරිකාව සහ ඉන්දියන් සාගරය",
        "4. යුරෝපා මහාද්වීපය සහ ආක්ටික් සාගරය"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "ලෝකයේ විශාලතම මහාද්වීපය ආසියාව (වර්ග කි.මී. මිලියන 44.5 ක් සහ ජනගහනය 60%) වන අතර, විශාලතම හා ගැඹුරුම සාගරය පැසිෆික් සාගරයයි. කුඩාම මහාද්වීපය ඕස්ට්‍රේලියාව වන අතර කුඩාම සාගරය ආක්ටික් සාගරයයි.",
      syllabusKeyFact = "විශාලතම මහාද්වීපය: ආසියාව | විශාලතම සාගරය: පැසිෆික් සාගරය",
      examTip = "සාගර 5: පැසිෆික්, අත්ලාන්තික්, ඉන්දියන්, දක්ෂිණ/ඇන්ටාක්ටික්, ආක්ටික්.",
      pdfPageRef = "පිටුව 7"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q7_world_mountains",
      number = 7,
      gradeLevel = GeoGradeLevel.GRADE_10,
      category = GeoUnitCategory.PHYSICAL_FEATURES,
      unitTitle = "10 ශ්‍රේණිය - ලෝකයේ කඳු වැටි (Mountain Ranges)",
      questionPrompt = "දකුණු ඇමෙරිකා මහාද්වීපයේ බටහිර වෙරළ දිගේ විහිදෙන ලොව දිගම නව නැමි කඳුවැටිය කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 10 - කඳු වැටි, සානු සහ තැනිතලා සිතියම් සටහන",
      options = listOf(
        "1. රොකී කඳු වැටිය (Rocky Mountains)",
        "2. හිමාලය කඳු වැටිය (Himalayas)",
        "3. ඇන්ඩීස් කඳු වැටිය (Andes Mountains)",
        "4. ඇල්ප්ස් කඳු වැටිය (Alps Mountains)"
      ),
      correctOptionIndex = 2,
      explanationSinhala = "ඇන්ඩීස් කඳු වැටිය දකුණු ඇමෙරිකාවේ බටහිර දිගින් කි.මී. 7,000 කට වඩා දිගට විහිදෙන ලොව දිගම කඳුවැටියයි. උතුරු ඇමෙරිකාවේ බටහිර රොකී කඳුවැටියද, ආසියාවේ උසම කඳුවැටිය වන හිමාලයද පිහිටා ඇත.",
      syllabusKeyFact = "දකුණු ඇමෙරිකාව -> ඇන්ඩීස් | උතුරු ඇමෙරිකාව -> රොකී | යුරෝපය -> ඇල්ප්ස් | ආසියාව -> හිමාලය",
      examTip = "යුරෝපය හා ආසියාව වෙන් කරන මායිම් කඳුවැටිය 'යූරල්' කඳුවැටියයි.",
      pdfPageRef = "පිටුව 10"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q8_world_rivers_lakes",
      number = 8,
      gradeLevel = GeoGradeLevel.GRADE_10,
      category = GeoUnitCategory.PHYSICAL_FEATURES,
      unitTitle = "10 ශ්‍රේණිය - ලෝකයේ ගංගා සහ විල් (Rivers & Lakes)",
      questionPrompt = "ලොව දිගම ගංගාව වන නයිල් ගඟ ගලා බසින්නේ කුමන මහාද්වීපයේද? ලොව විශාලතම මිරිදිය විල කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 11 - ලෝක ගංගා සහ විල් සිතියම",
      options = listOf(
        "1. දකුණු ඇමෙරිකාව (නයිල්) සහ කැස්පියන් විල",
        "2. අප්‍රිකා මහාද්වීපය (නයිල් ගඟ) සහ සුපීරියර් විල (Lake Superior)",
        "3. ආසියාව (නයිල්) සහ වික්ටෝරියා විල",
        "4. උතුරු ඇමෙරිකාව (නයිල්) සහ බයිකල් විල"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "නයිල් ගඟ අප්‍රිකා මහාද්වීපයේ කි.මී. 6,650 ක් දිගින් යුතුව මධ්‍යධරණී මුහුදට ගලා බසී (ලොව දිගම ගඟ). උතුරු ඇමෙරිකාවේ පිහිටි සුපීරියර් විල ලොව විශාලතම මිරිදිය විලයි. ලොව ගැඹුරුම මිරිදිය විල රුසියාවේ බයිකල් විලයි.",
      syllabusKeyFact = "නයිල් ගඟ -> අප්‍රිකාව | ඇමසන් (විශාලතම ජල ධාරිතාව) -> දකුණු ඇමෙරිකාව | සුපීරියර් -> විශාලතම මිරිදිය විල",
      examTip = "චීනයේ 'යැන්සි' සහ 'හොවැංහෝ' ආසියාවේ ප්‍රධාන ගංගා වේ.",
      pdfPageRef = "පිටුව 11"
    ),

    // --------------------------------------------------------------------------
    // 🌾 10 ශ්‍රේණිය - 3 වන පාඩම: ප්‍රධාන කෘෂිකාර්මික භූමි පරිභෝග
    // --------------------------------------------------------------------------
    GeoAutoCheckQuestion(
      id = "geo_q9_paddy_cultivation",
      number = 9,
      gradeLevel = GeoGradeLevel.GRADE_10,
      category = GeoUnitCategory.AGRICULTURE_LAND,
      unitTitle = "10 ශ්‍රේණිය - වී වගාව (Paddy Cultivation Conditions)",
      questionPrompt = "වී වගාව සාර්ථකව සිදු කිරීම සඳහා අවශ්‍ය වන ප්‍රශස්ත භෞතික සාධක (Physical Factors) නිවැරදිව දැක්වෙන්නේ කුමන පිළිතුරෙහිද?",
      subQuestionOrContext = "PDF පිටුව 16 - වී වගාව සහ තිරිඟු වගාවේ භෞතික ලක්ෂණ සංසන්දන වගුව",
      options = listOf(
        "1. 10°C ට අඩු උෂ්ණත්වය, 500mm වර්ෂාපතනය සහ වැලි සහිත පස",
        "2. 20°C - 30°C උෂ්ණත්වය, 1500mm - 2000mm වර්ෂාපතනය, ජලය රඳවාගත හැකි මැටි සහිත ලොම් පස සහ පැතලි තැනිතලා",
        "3. හිම පතනය සහිත ශීත දේශගුණය සහ කඳුකර බෑවුම්",
        "4. දැඩි වියළි කාලගුණය සහ ලවණ සහිත පස"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "වී යනු නිවර්තන තෙත් කලාපීය බෝගයකි. එයට 20°C - 30°C අතර ඉහළ උෂ්ණත්වයක්, 1500mm ට වැඩි ප්‍රමාණවත් වර්ෂාපතනයක් (හෝ වාරිමාර්ග පහසුකම්), ජලය රඳවා තබාගත හැකි මැටි හා දියළු පස සහ ජලය බැඳ තැබිය හැකි තැනිතලා භූමි අවශ්‍ය වේ.",
      syllabusKeyFact = "වී වගාව: උෂ්ණත්වය 20°-30°C • වර්ෂාව 1500mm+ • මැටි/දියළු පස • තැනිතලා බිම්",
      examTip = "ලෝකයේ වී නිෂ්පාදනයෙන් 90% කට වැඩි ප්‍රමාණයක් ආසියාතික මෝසම් කලාපයේ රටවල් (චීනය, ඉන්දියාව, ඉන්දුනීසියාව, බංග්ලාදේශය, වියට්නාමය) මඟින් සිදු කෙරේ.",
      pdfPageRef = "පිටුව 16"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q10_wheat_cultivation",
      number = 10,
      gradeLevel = GeoGradeLevel.GRADE_10,
      category = GeoUnitCategory.AGRICULTURE_LAND,
      unitTitle = "10 ශ්‍රේණිය - තිරිඟු වගාව (Wheat Cultivation)",
      questionPrompt = "ලෝකයේ වාණිජ තිරිඟු වගාව බහුලව සිදු වන සෞම්‍ය තෘණ බිම් (Temperate Grasslands) නිවැරදිව යුගලනය කර ඇත්තේ කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 16 & 17 - තිරිඟු වගා කරන කලාප",
      options = listOf(
        "1. උතුරු ඇමෙරිකාවේ ප්‍රෙයාරීස් (Prairies), යුරේසියාවේ ස්ටෙප්ස් (Steppes), ආර්ජන්ටිනාවේ පැම්පාස් (Pampas)",
        "2. අප්‍රිකාවේ සැවානා සහ ඇමසන් වැසි වනාන්තර",
        "3. ශ්‍රී ලංකාවේ පතන සහ විල්ලු",
        "4. ආක්ටික් ටුන්ඩ්‍රා කලාපය"
      ),
      correctOptionIndex = 0,
      explanationSinhala = "වාණිජ තිරිඟු වගාව සෞම්‍ය තෘණ බිම් ආශ්‍රිතව යාන්ත්‍රිකව විශාල ගොවිපළවල සිදු කෙරේ. උතුරු ඇමෙරිකාවේ ප්‍රෙයාරීස් (Prairies), යුරේසියානු ස්ටෙප්ස් (Steppes), ආර්ජන්ටිනාවේ පැම්පාස් (Pampas), සහ ඕස්ට්‍රේලියාවේ ඩවුන්ස් (Downs) ප්‍රධාන තිරිඟු බිම් වේ.",
      syllabusKeyFact = "තිරිඟු තෘණ බිම්: උතුරු ඇමෙරිකාව (ප්‍රෙයාරීස්) • යුරේසියාව (ස්ටෙප්ස්) • දකුණු ඇමෙරිකාව (පැම්පාස්) • ඕස්ට්‍රේලියාව (ඩවුන්ස්)",
      examTip = "තිරිඟු වගාවට උෂ්ණත්වය 15°-20°C සහ 500-750mm මධ්‍යස්ථ වර්ෂාපතනයක් හා චර්නොසෙම් කළු පස සුදුසුය.",
      pdfPageRef = "පිටුව 17"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q11_plantation_agriculture",
      number = 11,
      gradeLevel = GeoGradeLevel.GRADE_10,
      category = GeoUnitCategory.AGRICULTURE_LAND,
      unitTitle = "10 ශ්‍රේණිය - වතු කෘෂිකර්මාන්තය (Plantation Agriculture)",
      questionPrompt = "වතු කෘෂිකර්මාන්තයේ (Plantation Agriculture) දක්නට ලැබෙන ප්‍රධානතම ලක්ෂණ 3 නිවැරදිව දක්වා ඇති පිළිතුර කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 18 - ප්‍රශ්න අංක 04 (i & ii)",
      options = listOf(
        "1. පවුලේ පරිභෝජනය සඳහා පමණක් වගා කිරීම සහ කුඩා ඉඩම්",
        "2. විශාල ඉඩම් වපසරිය, විශාල ප්‍රාග්ධනය හා කුලී ශ්‍රමය, අපනයනය ඉලක්ක කරගත් ඒක බෝග වගාව (Monoculture)",
        "3. නිතර වගා බිම් මාරු කරන හේන් වගා ක්‍රමය",
        "4. කිසිදු කර්මාන්තශාලාවක් හෝ සැකසුම් මධ්‍යස්ථානයක් නොමැති වීම"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "වතු කෘෂිකර්මාන්තයේ සුවිශේෂී ලක්ෂණ වන්නේ: 1. විශාල භූමි ප්‍රමාණයක ඒක බෝගයක් (තේ, රබර්, තෙල් පාම්, උක් ආදී) වගා කිරීම, 2. විශාල ප්‍රාග්ධන ආයෝජනය, 3. විදේශ හෝ කුලී ශ්‍රම බලකායක් යෙදවීම, 4. වත්ත තුළම සැකසුම් කම්හල් පැවතීම, 5. අපනයනය මුල් කරගත් වාණිජ නිෂ්පාදනයක් වීමයි.",
      syllabusKeyFact = "වතු වගා ලක්ෂණ: ඒක බෝග (Monoculture) • විශාල ඉඩම් • විශාල ප්‍රාග්ධනය • කුලී ශ්‍රමය • අපනයන ඉලක්කය",
      examTip = "ශ්‍රී ලංකාව, ඉන්දියාව, කෙන්යාව (තේ), මැලේසියාව, ඉන්දුනීසියාව (පාම් තෙල්/රබර්), කියුබාව, බ්‍රසීලය (උක්/කෝපි) ප්‍රධාන වතු වගා රටවල් වේ.",
      pdfPageRef = "පිටුව 18"
    ),

    // --------------------------------------------------------------------------
    // 🏭 10 ශ්‍රේණිය - 4, 5, 6 පාඩම්: ශ්‍රී ලංකාවේ කර්මාන්ත හා ඛනිජ
    // --------------------------------------------------------------------------
    GeoAutoCheckQuestion(
      id = "geo_q12_research_institutes_sl",
      number = 12,
      gradeLevel = GeoGradeLevel.GRADE_10,
      category = GeoUnitCategory.SRI_LANKA_INDUSTRIES,
      unitTitle = "10 ශ්‍රේණිය - ශ්‍රී ලංකාවේ ප්‍රධාන බෝග පර්යේෂණ ආයතන",
      questionPrompt = "ශ්‍රී ලංකාවේ තේ, පොල් සහ රබර් පර්යේෂණ ආයතන පිහිටි ස්ථාන පිළිවෙළින් නිවැරදිව දක්වා ඇත්තේ කුමන පිළිතුරෙහිද?",
      subQuestionOrContext = "PDF පිටුව 23 - තේ, පොල්, රබර් පර්යේෂණ ආයතන වගුව",
      options = listOf(
        "1. ගන්නොරුව, කුණ්ඩසාලේ, බතලගොඩ",
        "2. තලවකැලේ (තේ - TRI), ලුණුවිල (පොල් - CRI), අගලවත්ත/ද Dartonfield (රබර් - RRI)",
        "3. මහනුවර, අනුරාධපුරය, ගාල්ල",
        "4. කුරුණෑගල, හම්බන්තොට, රත්නපුරය"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "ශ්‍රී ලංකාවේ ප්‍රධාන අපනයන බෝග පර්යේෂණ ආයතන: 1. තේ පර්යේෂණායතනය (TRI) - තලවකැලේ (Talawakelle), 2. පොල් පර්යේෂණායතනය (CRI) - බණ්ඩිරිප්පුව, ලුණුවිල (Lunuwila), 3. රබර් පර්යේෂණායතනය (RRI) - ඩාර්ටන්ෆීල්ඩ්, අගලවත්ත (Agalawatta).",
      syllabusKeyFact = "තේ -> තලවකැලේ (TRI) | පොල් -> ලුණුවිල (CRI) | රබර් -> අගලවත්ත (RRI) | වී -> බතලගොඩ",
      examTip = "වී පර්යේෂණ ආයතන බතලගොඩ, බෝඹුවල, ලබුදූව, අම්බලන්තොට පිහිටා ඇත.",
      pdfPageRef = "පිටුව 23"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q13_graphite_minerals_sl",
      number = 13,
      gradeLevel = GeoGradeLevel.GRADE_10,
      category = GeoUnitCategory.SRI_LANKA_INDUSTRIES,
      unitTitle = "10 ශ්‍රේණිය - ශ්‍රී ලංකාවේ මිනිරන් හා ඛනිජ සම්පත්",
      questionPrompt = "ශ්‍රී ලංකාවේ ඉහළම කාබන් ප්‍රතිශතයක් (95%-99%) සහිත ශිරා මිනිරන් (Vein Graphite) හමුවන ප්‍රධාන පතල් 2 කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 33 & 34 - මිනිරන් කර්මාන්තය හා ඛනිජ ව්‍යාප්තිය",
      options = listOf(
        "1. බොරලැස්ගමුව සහ මීටියාගොඩ",
        "2. බෝගල (Bogala) සහ කහටගහ-කොලොන්ගහ (Kahatagaha-Kolongaha)",
        "3. පුල්මුඩේ සහ කන්කසන්තුරේ",
        "4. එප්පාවල සහ දේදියවල"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "ශ්‍රී ලංකාවේ උසස්ම වාණිජ මට්ටමේ ශිරා මිනිරන් පතල් වන්නේ කෑගල්ල දිස්ත්‍රික්කයේ 'බෝගල' පතල සහ කුරුණෑගල දිස්ත්‍රික්කයේ 'කහටගහ-කොලොන්ගහ' පතලයි. බොරලැස්ගමුව/මීටියාගොඩ කැයොලින් ද, පුල්මුඩේ ඛනිජ වැලි ද, එප්පාවල ඇපටයිට්/පොස්පේට් ද හමුවේ.",
      syllabusKeyFact = "මිනිරන්: බෝගල (කෑගල්ල), කහටගහ (කුරුණෑගල) • කැයොලින්: බොරලැස්ගමුව, මීටියාගොඩ • ඛනිජ වැලි: පුල්මුඩේ",
      examTip = "ශ්‍රී ලංකාවේ මිනිරන් කාබන් ප්‍රතිශතය 95%-99% තරම් ඉහළ සංශුද්ධතාවයකින් යුක්තය.",
      pdfPageRef = "පිටුව 33"
    ),

    // --------------------------------------------------------------------------
    // 💎 11 ශ්‍රේණිය - 1 වන පාඩම: පෘථිවියේ ස්වාභාවික සම්පත් (පාෂාණ, පස, ජලය)
    // --------------------------------------------------------------------------
    GeoAutoCheckQuestion(
      id = "geo_q14_three_rock_types",
      number = 14,
      gradeLevel = GeoGradeLevel.GRADE_11,
      category = GeoUnitCategory.NATURAL_RESOURCES,
      unitTitle = "11 ශ්‍රේණිය - පාෂාණ වර්ග 3 (Three Major Rock Types)",
      questionPrompt = "මැග්මා හෝ ලාවා සිසිල් වී ඝනීභවනය වීමෙන් නිර්මාණය වන පාෂාණ වර්ගය සහ එයට උදාහරණයක් නිවැරදිව දක්වා ඇත්තේ කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 38 - ප්‍රශ්න අංක 01 (iv) පාෂාණ වර්ග 3 වගුව",
      options = listOf(
        "1. විපරීත පාෂාණ - කිරිගරුඬ",
        "2. ආග්නේය පාෂාණ (Igneous Rocks) - ග්‍රැනයිට් (Granite) සහ බැසෝල්ට් (Basalt)",
        "3. අවසාදිත පාෂාණ - හුණුගල්",
        "4. කාබනික පාෂාණ - පීට්"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "1. ආග්නේය පාෂාණ (Igneous) - මැග්මා/ලාවා සිසිල් වීමෙන් සෑදේ (උදා: ග්‍රැනයිට්, බැසෝල්ට්, ඔබ්සිඩියන්). 2. අවසාදිත පාෂාණ (Sedimentary) - අවසාදිත තැන්පත්ව පීඩනයෙන් සෑදේ (උදා: හුණුගල්, වැලිගල්, මැටි පාෂාණ). 3. විපරීත පාෂාණ (Metamorphic) - අධික පීඩනය හා තාපය නිසා විපර්යාස වී සෑදේ (උදා: නයිස්, කිරිගරුඬ, ක්වාට්සයිට්, චානොකයිට්).",
      syllabusKeyFact = "ආග්නේය (ග්‍රැනයිට්, බැසෝල්ට්) • අවසාදිත (හුණුගල්, වැලිගල්) • විපරීත (නයිස්, කිරිගරුඬ)",
      examTip = "ශ්‍රී ලංකාවේ භූමියෙන් 90% කට වඩා සමන්විත වන්නේ ප්‍රාග්-කේම්බ්‍රීය යුගයේ විපරීත පාෂාණ වලිනි.",
      pdfPageRef = "පිටුව 38"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q15_soil_profile_layers",
      number = 15,
      gradeLevel = GeoGradeLevel.GRADE_11,
      category = GeoUnitCategory.NATURAL_RESOURCES,
      unitTitle = "11 ශ්‍රේණිය - පාංශු පැතිකඩ (Soil Profile Layers)",
      questionPrompt = "පරිණත පාංශු පැතිකඩක A, B, C ස්තර අතුරින් කාබනික ද්‍රව්‍ය හා හියුමස් බහුලව අඩංගු සාරවත් 'ඉහළ පස' (Topsoil) ලෙස හඳුන්වන්නේ කුමන ස්තරයද?",
      subQuestionOrContext = "PDF පිටුව 41 - පාංශු පැතිකඩ රූප සටහන (A, B, C ස්තර)",
      options = listOf(
        "1. C ස්තරය (දිරාපත් වන මාතෘ පාෂාණය)",
        "2. B ස්තරය (යටි පස / Subsoil)",
        "3. A ස්තරය (ඉහළ පස / Topsoil)",
        "4. පාදස්ථ පාෂාණ ස්තරය (Bedrock)"
      ),
      correctOptionIndex = 2,
      explanationSinhala = "පාංශු පැතිකඩක: A ස්තරය = ඉහළ පස (Topsoil) - ශාක හා සත්ත්ව අවශේෂ දිරාපත්ව සෑදුණු හියුමස් සහ පෝෂක බහුල අඳුරු ස්තරයයි. B ස්තරය = යටි පස (Subsoil) - ඉහළින් කාන්දු වන ඛනිජ හා මැටි තැන්පත් වේ. C ස්තරය = අර්ධ වශයෙන් දිරාපත් වූ මාතෘ පාෂාණ කැබලි අඩංගු ස්තරයයි.",
      syllabusKeyFact = "A ස්තරය = ඉහළ පස/හියුමස් • B ස්තරය = යටි පස • C ස්තරය = දිරාපත් වන මාතෘ පාෂාණය",
      examTip = "පාංශු ඛාදනයෙන් ප්‍රධාන වශයෙන්ම හානි වන්නේ වඩාත්ම සාරවත් A ස්තරයටයි.",
      pdfPageRef = "පිටුව 41"
    ),

    // --------------------------------------------------------------------------
    // 🌊 11 ශ්‍රේණිය - 2 වන පාඩම: ශ්‍රී ලංකාවේ ස්වාභාවික සම්පත් (මුහුදු සීමා, පස්, ගංගා)
    // --------------------------------------------------------------------------
    GeoAutoCheckQuestion(
      id = "geo_q16_sl_maritime_zones",
      number = 16,
      gradeLevel = GeoGradeLevel.GRADE_11,
      category = GeoUnitCategory.NATURAL_RESOURCES,
      unitTitle = "11 ශ්‍රේණිය - ශ්‍රී ලංකාවේ මුහුදු සීමා (Maritime Zones of Sri Lanka)",
      questionPrompt = "1982 එක්සත් ජාතීන්ගේ මුහුදු නීති සම්මුතිය (UNCLOS) අනුව ශ්‍රී ලංකාවේ ප්‍රාදේශීය මුහුදු සීමාව (Territorial Sea) සහ අනන්‍ය ආර්ථික කලාපය (EEZ) වෙරළේ සිට විහිදෙන දුර පිළිවෙළින් කොපමණද?",
      subQuestionOrContext = "PDF පිටුව 45 - ශ්‍රී ලංකාව සතු රාජ්‍ය මුහුදු සීමාවන්",
      options = listOf(
        "1. නාවික සැතපුම් 5 සහ නාවික සැතපුම් 50",
        "2. නාවික සැතපුම් 12 (ප්‍රාදේශීය මුහුද) සහ නාවික සැතපුම් 200 (අනන්‍ය ආර්ථික කලාපය - EEZ)",
        "3. නාවික සැතපුම් 24 සහ නාවික සැතපුම් 100",
        "4. නාවික සැතපුම් 50 සහ නාවික සැතපුම් 500"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "ශ්‍රී ලංකාවේ සමුද්‍රීය කලාප: 1. අභ්‍යන්තර ජලය (Internal Waters), 2. ප්‍රාදේශීය මුහුද (Territorial Sea) = වෙරළේ සිට නාවික සැතපුම් 12 (කි.මී. 22.2), 3. යාබද කලාපය (Contiguous Zone) = නාවික සැතපුම් 24, 4. අනන්‍ය ආර්ථික කලාපය (Exclusive Economic Zone - EEZ) = නාවික සැතපුම් 200 (කි.මී. 370.4).",
      syllabusKeyFact = "ප්‍රාදේශීය මුහුද = නාවික සැතපුම් 12 | අනන්‍ය ආර්ථික කලාපය (EEZ) = නාවික සැතපුම් 200",
      examTip = "ශ්‍රී ලංකාවේ EEZ මුහුදු ප්‍රමාණය ගොඩබිම් භූමි ප්‍රමාණය මෙන් 8 ගුණයකටත් වඩා විශාලය.",
      pdfPageRef = "පිටුව 45"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q17_sl_major_soils",
      number = 17,
      gradeLevel = GeoGradeLevel.GRADE_11,
      category = GeoUnitCategory.NATURAL_RESOURCES,
      unitTitle = "11 ශ්‍රේණිය - ශ්‍රී ලංකාවේ පාංශු කලාප (Soils of Sri Lanka)",
      questionPrompt = "ශ්‍රී ලංකාවේ වියළි කලාපයේ (Dry Zone) බහුලවම ව්‍යාප්ත වී ඇති ප්‍රධාන පස සහ තෙත් කලාපයේ (Wet Zone) බහුලවම ව්‍යාප්ත වී ඇති පස පිළිවෙළින් කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 47 & 48 - පාංශු වර්ග සහ සුදුසු බෝග",
      options = listOf(
        "1. දියළු පස සහ ලැටරයිට් පස",
        "2. රතු දුඹුරු පස (RBE) සහ රතු කහ පොඩ්සෝල් පස (RYP)",
        "3. රෙගොසෝල් පස සහ නොමේරූ දුඹුරු ලොම් පස",
        "4. ලවණ පස සහ බොරළු පස"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "ශ්‍රී ලංකාවේ: 1. වියළි කලාපයේ බහුලවම ඇත්තේ 'රතු දුඹුරු පස' (Reddish Brown Earths - RBE) වන අතර එය හේන් හා වාරි වී වගාවට සුදුසුය. 2. තෙත් කලාපයේ හා කඳුකරයේ බහුලවම ඇත්තේ 'රතු කහ පොඩ්සෝල් පස' (Red Yellow Podzolic - RYP) වන අතර එය තේ, රබර් හා එළවළු සඳහා සුදුසුය.",
      syllabusKeyFact = "වියළි කලාපය -> රතු දුඹුරු පස (RBE) | තෙත් කලාපය -> රතු කහ පොඩ්සෝල් පස (RYP)",
      examTip = "වෙරළබඩ තීරයේ වැලි සහිත 'රෙගොසෝල්' (Regosol) පස පොල් වගාවට බෙහෙවින් යෝග්‍ය වේ.",
      pdfPageRef = "පිටුව 47"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q18_sl_rivers",
      number = 18,
      gradeLevel = GeoGradeLevel.GRADE_11,
      category = GeoUnitCategory.NATURAL_RESOURCES,
      unitTitle = "11 ශ්‍රේණිය - ශ්‍රී ලංකාවේ ගංගා පද්ධතිය (River Network of SL)",
      questionPrompt = "ශ්‍රී ලංකාවේ දිගම ගංගාව (කි.මී. 335) සහ වැඩිම වාර්ෂික ජල පරිමාවක් මුහුදට ගෙන යන ගංගාව පිළිවෙළින් කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 50 - ශ්‍රී ලංකාවේ ගංගා සිතියම",
      options = listOf(
        "1. කැළණි ගඟ සහ වලවේ ගඟ",
        "2. මහවැලි ගඟ (Mahaweli River) සහ කළු ගඟ (Kalu River)",
        "3. දැදුරු ඔය සහ මල්වතු ඔය",
        "4. නිල්වලා ගඟ සහ යාන් ඔය"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "ශ්‍රී ලංකාවේ දිගම ගංගාව මෙන්ම විශාලතම ද්‍රෝණිය (ව.කි.මී. 10,448) හිමි ගඟ 'මහවැලි ගඟ' (කි.මී. 335) වේ. ශ්‍රී ලංකාවේ වැඩිම වාර්ෂික ජල ධාරිතාව මුහුදට මුදාහරින ගංගාව 'කළු ගඟ' (වාර්ෂික ජල පරිමාව ඝන මීටර් මිලියන 7,860) වේ.",
      syllabusKeyFact = "දිගම ගඟ = මහවැලි (335 km) | වැඩිම ජල ධාරිතාව ගෙන යන ගඟ = කළු ගඟ",
      examTip = "ශ්‍රී ලංකාවේ ගංගා මධ්‍යම කඳුකරයේ සිට අරීය රටාවකට (Radial Pattern) මුහුද දෙසට ගලා බසී.",
      pdfPageRef = "පිටුව 50"
    ),

    // --------------------------------------------------------------------------
    // 👥 11 ශ්‍රේණිය - 3 හා 4 පාඩම්: ලෝක හා ලංකා ජන සංඛ්‍යාව
    // --------------------------------------------------------------------------
    GeoAutoCheckQuestion(
      id = "geo_q19_world_population_dense",
      number = 19,
      gradeLevel = GeoGradeLevel.GRADE_11,
      category = GeoUnitCategory.POPULATION_DEMOGRAPHY,
      unitTitle = "11 ශ්‍රේණිය - ලෝක ජනගහනය (අධිජන සංකේන්ද්‍රණ කලාප)",
      questionPrompt = "ලෝකයේ ප්‍රධාන අධිජන සංකේන්ද්‍රණ කලාප (Major High Population Density Clusters) 4 නිවැරදිව දැක්වෙන්නේ කුමන පිළිතුරෙහිද?",
      subQuestionOrContext = "PDF පිටුව 58 - ප්‍රශ්න අංක 07 & 10",
      options = listOf(
        "1. සහරා කාන්තාරය, ඇමසන් වනාන්තරය, ග්‍රීන්ලන්තය, ටිබෙට් සානුව",
        "2. නැගෙනහිර ආසියාව, දකුණු ආසියාව, බටහිර යුරෝපය, උතුරු ඇමෙරිකාවේ ඊසානදිග (East Asia, South Asia, Western Europe, NE North America)",
        "3. ඕස්ට්‍රේලියානු කාන්තාරය, සයිබීරියාව, ඇන්ටාක්ටිකාව, පැටගෝනියාව",
        "4. ඇලස්කාව, මැඩගස්කරය, අයිස්ලන්තය, සුමාත්‍රාව"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "ලෝක ජනගහනයෙන් 75% කට වැඩි පිරිසක් ජීවත් වන්නේ ප්‍රධාන අධිජන කලාප 4 තුළය: 1. නැගෙනහිර ආසියාව (චීනය, ජපානය, කොරියාව), 2. දකුණු ආසියාව (ඉන්දියාව, පකිස්ථානය, බංග්ලාදේශය), 3. බටහිර හා මධ්‍යම යුරෝපය, 4. උතුරු ඇමෙරිකාවේ ඊසානදිග වෙරළබඩ කලාපය.",
      syllabusKeyFact = "ප්‍රධාන අධිජන කලාප 4: නැගෙනහිර ආසියාව • දකුණු ආසියාව • බටහිර යුරෝපය • ඊසානදිග උතුරු ඇමෙරිකාව",
      examTip = "අධික ශීතල (ධ්‍රැව), අධික වියළි (කාන්තාර), අධික උස් (කඳුකර) හා ඝන වනාන්තර 'ජනහීන' කලාප වේ.",
      pdfPageRef = "පිටුව 58"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q20_sl_population_census",
      number = 20,
      gradeLevel = GeoGradeLevel.GRADE_11,
      category = GeoUnitCategory.POPULATION_DEMOGRAPHY,
      unitTitle = "11 ශ්‍රේණිය - ශ්‍රී ලංකාවේ ජන සංඛ්‍යාව (සංගණන හා ව්‍යාප්තිය)",
      questionPrompt = "ශ්‍රී ලංකාවේ ප්‍රථම නිල විද්‍යාත්මක ජන සංගණනය පැවැත්වූ වර්ෂය සහ වර්තමානයේ වැඩිම හා අඩුම ජන ඝනත්වයක් සහිත දිස්ත්‍රික්ක පිළිවෙළින් කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 60 - ප්‍රශ්න අංක 04 & 07",
      options = listOf(
        "1. 1948 - මහනුවර (වැඩිම) සහ මාතලේ (අඩුම)",
        "2. 1871 - කොළඹ (වැඩිම ජන ඝනත්වය) සහ මුලතිව් (අඩුම ජන ඝනත්වය)",
        "3. 1972 - ගම්පහ සහ මොනරාගල",
        "4. 1900 - කුරුණෑගල සහ මන්නාරම"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "ශ්‍රී ලංකාවේ ප්‍රථම නිල ජන සංගණනය 1871 දී පැවැත්වුණු අතර එවකට ජනගහනය මිලියන 2.4 කි. වර්තමානයේ වැඩිම ජන ඝනත්වය කොළඹ දිස්ත්‍රික්කයේද (වර්ග කි.මී. ට 3,400+), අඩුම ජන ඝනත්වය මුලතිව් දිස්ත්‍රික්කයේද (වර්ග කි.මී. ට 40 ට අඩු) සටහන් වේ.",
      syllabusKeyFact = "ප්‍රථම සංගණනය = 1871 | වැඩිම ඝනත්වය = කොළඹ | අඩුම ඝනත්වය = මුලතිව්",
      examTip = "ජන ඝනත්වය = මුළු ජනගහනය ÷ මුළු භූමි ප්‍රමාණය (වර්ග කි.මී.)",
      pdfPageRef = "පිටුව 60"
    ),

    // --------------------------------------------------------------------------
    // 📈 11 ශ්‍රේණිය - 5 වන පාඩම: සංවර්ධනය හා මානව සංවර්ධන දර්ශකය (HDI)
    // --------------------------------------------------------------------------
    GeoAutoCheckQuestion(
      id = "geo_q21_human_development_index",
      number = 21,
      gradeLevel = GeoGradeLevel.GRADE_11,
      category = GeoUnitCategory.DEVELOPMENT_HDI,
      unitTitle = "11 ශ්‍රේණිය - සංවර්ධනය (මානව සංවර්ධන දර්ශකය - HDI)",
      questionPrompt = "එක්සත් ජාතීන්ගේ සංවර්ධන වැඩසටහන (UNDP) මඟින් සකස් කරන මානව සංවර්ධන දර්ශකය (HDI) ගණනය කිරීමට යොදාගන්නා මූලික දර්ශක 3 කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 63 - ප්‍රශ්න අංක 05 (මානව සංවර්ධන දර්ශකයේ අංග)",
      options = listOf(
        "1. මෝටර් රථ සංඛ්‍යාව, විදුලි පරිභෝජනය, රූපවාහිනී සංඛ්‍යාව",
        "2. උපතේදී ආයු අපේක්ෂාව (දීර්ඝ නිරෝගී දිවිය), අධ්‍යාපනය (පාසල් යාමේ අපේක්ෂිත හා මධ්‍යන්‍ය වසර), ජීවන මට්ටම (ඒක පුද්ගල දළ ජාතික ආදායම - GNI per capita PPP)",
        "3. අපනයන ආදායම, ආනයන වියදම, ණය බර",
        "4. හමුදා ශක්තිය, ජන ඝනත්වය, භූමි ප්‍රමාණය"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "මානව සංවර්ධන දර්ශකය (HDI) ප්‍රධාන මානයන් 3 ක් මත පදනම් වේ: 1. සෞඛ්‍යය/දීර්ඝායුෂ (උපතේදී ආයු අපේක්ෂාව - Life Expectancy), 2. අධ්‍යාපනය (වැඩිහිටි සාක්ෂරතාව හා පාසල්ගත වීමේ වසර - Education index), 3. යහපත් ජීවන මට්ටම (මිලදී ගැනීමේ හැකියාව මත ඒක පුද්ගල ආදායම - GNI per capita PPP). අගය 0 සිට 1 දක්වා පරාසයක පවතී.",
      syllabusKeyFact = "HDI ප්‍රධාන මාන 3: 1. ආයු අපේක්ෂාව (සෞඛ්‍ය) • 2. අධ්‍යාපනය • 3. ඒක පුද්ගල ආදායම (GNI PPP)",
      examTip = "ශ්‍රී ලංකාව දකුණු ආසියාවේ ඉහළම HDI අගයක් (ඉහළ මානව සංවර්ධන කාණ්ඩය) හිමි රටකි.",
      pdfPageRef = "පිටුව 63"
    ),

    // --------------------------------------------------------------------------
    // ⚠️ 11 ශ්‍රේණිය - 6 & 7 පාඩම්: ස්වාභාවික උපද්‍රව හා ආපදා කළමනාකරණය
    // --------------------------------------------------------------------------
    GeoAutoCheckQuestion(
      id = "geo_q22_hazard_vs_disaster",
      number = 22,
      gradeLevel = GeoGradeLevel.GRADE_11,
      category = GeoUnitCategory.NATURAL_HAZARDS,
      unitTitle = "11 ශ්‍රේණිය - ස්වාභාවික උපද්‍රව හා ආපදා (Hazard vs Disaster)",
      questionPrompt = "'උපද්‍රවයක්' (Hazard) සහ 'ආපදාවක්' (Disaster) අතර ඇති ප්‍රධාන වෙනස නිවැරදිව පැහැදිලි කර ඇත්තේ කුමන පිළිතුරෙහිද?",
      subQuestionOrContext = "PDF පිටුව 66 - ප්‍රශ්න අංක 01 (i & ii)",
      options = listOf(
        "1. උපද්‍රව සහ ආපදා යනු කිසිදු වෙනසක් නැති එකම අර්ථය දෙන වචන දෙකකි",
        "2. උපද්‍රවයක් යනු ජීවිත, දේපළ හෝ පරිසරයට තර්ජනයක් විය හැකි ස්වාභාවික සිදුවීමකි; එම උපද්‍රවය මිනිස් සමාජයට හා දේපළවලට සැබෑ හානි සිදු කළ විට එය ආපදාවක් බවට පත්වේ",
        "3. ආපදා සිදුවන්නේ පරිසරයට පමණක් වන අතර උපද්‍රව සිදුවන්නේ මිනිසුන්ට පමණි",
        "4. සියලුම උපද්‍රව මිනිසා විසින් පමණක් ඇති කරන සිදුවීම් වේ"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "උපද්‍රවය (Hazard): ජීවිත, දේපළ හෝ පරිසරයට හානි කිරීමේ විභවයක් (හැකියාවක්) ඇති ස්වාභාවික හෝ මානවජනක තර්ජනයකි (උදා: මුහුද මැද භූමිකම්පාවක් වීම). ආපදාව (Disaster): එම උපද්‍රවය ජනාවාස ප්‍රදේශයකට බලපා ජීවිත, ආර්ථික වත්කම් හා පරිසරයට පුළුල් විනාශයක් සිදු කිරීමයි (උදා: සුනාමි රළ ගොඩබිමට කඩාවැදී මිනිසුන් මියයාම).",
      syllabusKeyFact = "උපද්‍රවය (හැකියාව/තර්ජනය) + අවදානමට ලක්වීම = ආපදාව (සැබෑ විනාශය)",
      examTip = "ආපදා කළමනාකරණ චක්‍රය: 1. පූර්ව ආපදා (සූදානම/අවම කිරීම) -> 2. ආපදා අවස්ථාව (හදිසි සහන) -> 3. පසු ආපදා (ප්‍රතිසාධනය).",
      pdfPageRef = "පිටුව 66"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q23_landslide_warning_signs",
      number = 23,
      gradeLevel = GeoGradeLevel.GRADE_11,
      category = GeoUnitCategory.NATURAL_HAZARDS,
      unitTitle = "11 ශ්‍රේණිය - ශ්‍රී ලංකාවේ නායයෑම් ආපදා (Landslides in SL)",
      questionPrompt = "නායයෑමක් සිදුවීමට පෙර දැකගත හැකි පූර්ව අනතුරු ඇඟවීමේ ලක්ෂණ (Pre-warning Signs) නොවන ලක්ෂණය කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 73 - ප්‍රශ්න අංක 05 (නායයෑම් පූර්ව ලක්ෂණ)",
      options = listOf(
        "1. කඳු බෑවුම්වල හා ගොඩනැගිලිවල හදිසියේ පැලුම් හා ඉරිතැලීම් ඇතිවීම",
        "2. ගස්, විදුලි කණු සහ දුරකථන කණු බෑවුම දෙසට ඇලවීම",
        "3. උල්පත් හා ළිං ජලය හදිසියේ බොරවීම හෝ අලුතින් උල්පත් මතුවීම",
        "4. දැඩි සුළඟක් සමඟ වායුගෝලීය උෂ්ණත්වය එකවර ඉහළ යාම"
      ),
      correctOptionIndex = 3,
      explanationSinhala = "නායයෑම් පූර්ව අනතුරු ලක්ෂණ: 1. පොළොවේ හා ගොඩනැගිලිවල පැලුම්/ඉරිතැලීම් මතුවීම, 2. දොර ජනෙල් සිරවීම හා බිත්ති පිපිරීම, 3. ගස් හා කණු බෑවුම දෙසට ඇලවීම, 4. උල්පත් ජලය එකවර සිඳී යාම හෝ බොරවී ගලා ඒම, 5. පොළොව යටින් ගොරවන ශබ්ද ඇසීම. (උෂ්ණත්වය ඉහළ යාම නායයෑම් ලක්ෂණයක් නොවේ).",
      syllabusKeyFact = "නායයෑම් පූර්ව ලක්ෂණ: බිම් පැලුම් • ගස්/කණු ඇලවීම • උල්පත් බොරවීම • භූගත ගෙරවුම් ශබ්ද",
      examTip = "ශ්‍රී ලංකාවේ නායයෑම් පර්යේෂණ හා අනතුරු ඇඟවීම් නිකුත් කරන ප්‍රධාන ආයතනය NBRO (ජාතික ගොඩනැගිලි පර්යේෂණ සංවිධානය) වේ.",
      pdfPageRef = "පිටුව 73"
    ),

    // --------------------------------------------------------------------------
    // 🌡️ 11 ශ්‍රේණිය - 8 වන පාඩම: දේශගුණික වෙනස්කම්
    // --------------------------------------------------------------------------
    GeoAutoCheckQuestion(
      id = "geo_q24_greenhouse_gases",
      number = 24,
      gradeLevel = GeoGradeLevel.GRADE_11,
      category = GeoUnitCategory.CLIMATE_CHANGE,
      unitTitle = "11 ශ්‍රේණිය - දේශගුණික වෙනස්වීම් (හරිතාගාර වායු & ගෝලීය උණුසුම)",
      questionPrompt = "ගෝලීය උණුසුම ඉහළ යාමට (Global Warming) ප්‍රධාන වශයෙන් දායක වන ප්‍රමුඛතම හරිතාගාර වායුව සහ එහි ප්‍රධාන ප්‍රභවය කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 75 & 76 - දේශගුණික වෙනස්කම් සහ මානව ක්‍රියාකාරකම්",
      options = listOf(
        "1. ඔක්සිජන් - ශාක ප්‍රභාසංස්ලේෂණය",
        "2. කාබන්ඩයොක්සයිඩ් (CO₂) - ෆොසිල ඉන්ධන (ගල් අඟුරු, ඛනිජ තෙල්) දහනය සහ වන විනාශය",
        "3. හීලියම් - ගුවන් යානා ධාවනය",
        "4. නයිට්‍රජන් - කෘෂිකාර්මික පොහොර භාවිතය"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "ගෝලීය උණුසුමට 60% කට වඩා දායක වන ප්‍රධානතම හරිතාගාර වායුව කාබන්ඩයොක්සයිඩ් (CO₂) වේ. ගල් අඟුරු, පෙට්‍රෝලියම් හා ඩීසල් වැනි ෆොසිල ඉන්ධන දහනය සහ දැවැන්ත වන විනාශය මඟින් වායුගෝලයට CO₂ එකතු වේ. මීට අමතරව මීතේන් (CH₄), නයිට්‍රස් ඔක්සයිඩ් (N₂O) සහ CFC වායුද හරිතාගාර වායු වේ.",
      syllabusKeyFact = "ප්‍රධාන හරිතාගාර වායු: කාබන්ඩයොක්සයිඩ් (CO₂), මීතේන් (CH₄), නයිට්‍රස් ඔක්සයිඩ් (N₂O), CFC",
      examTip = "ගෝලීය උණුසුම නිසා ධ්‍රැවීය අයිස් දියවීම, මුහුදු මට්ටම ඉහළ යාම සහ ආන්තික කාලගුණික ආපදා (නියඟ, කුණාටු) බහුල වේ.",
      pdfPageRef = "පිටුව 76"
    ),

    // --------------------------------------------------------------------------
    // 🗺️ 10 - 11 ශ්‍රේණි: 1:50,000 භූ ලක්ෂණ සිතියම් & ප්‍රස්තාර
    // --------------------------------------------------------------------------
    GeoAutoCheckQuestion(
      id = "geo_q25_map_reading_oldest_map",
      number = 25,
      gradeLevel = GeoGradeLevel.ALL,
      category = GeoUnitCategory.TOPO_MAPS_50000,
      unitTitle = "10-11 ශ්‍රේණි - 1:50,000 භූ ලක්ෂණ සිතියම් (සිතියම් ඉතිහාසය)",
      questionPrompt = "ලොව මෙතෙක් සොයාගෙන ඇති පැරණිතම සිතියම (ක්‍රි.පූ. 2300 බැබිලෝනියානු මැටි පුවරු සිතියම) සොයාගනු ලැබුවේ කුමන පුරාවිද්‍යාඥයා විසින්ද?",
      subQuestionOrContext = "PDF පිටුව 77 - ප්‍රශ්න අංක 01 (ii)",
      options = listOf(
        "1. මහාචාර්ය සෙනරත් පරණවිතාන මහතා",
        "2. ශ්‍රීමත් ලෙනාඩ් වූලි (Sir Leonard Woolley)",
        "3. ටොලමි (Claudius Ptolemy)",
        "4. ක්‍රිස්ටෝපර් කොලම්බස්"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "ලොව පැරණිතම සිතියම ලෙස සැලකෙන ක්‍රි.පූ. 2300 දී පමණ බැබිලෝනියාවේ (වර්තමාන ඉරාකය) මැටි පුවරුවක අඳින ලද 'ගා-සුර්' (Ga-Sur) සිතියම සොයාගනු ලැබුවේ බ්‍රිතාන්‍ය ජාතික පුරාවිද්‍යාඥ ශ්‍රීමත් ලෙනාඩ් වූලි (Sir Leonard Woolley) විසිනි.",
      syllabusKeyFact = "පැරණිතම සිතියම: ක්‍රි.පූ. 2300 බැබිලෝනියානු මැටි පුවරු සිතියම (සොයාගත්තේ: ශ්‍රීමත් ලෙනාඩ් වූලි)",
      examTip = "සිතියම් විද්‍යාවේ පියා ලෙස ක්ලෝඩියස් ටොලමි (Ptolemy) හඳුන්වනු ලැබේ.",
      pdfPageRef = "පිටුව 77"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q26_map_reading_north_types",
      number = 26,
      gradeLevel = GeoGradeLevel.ALL,
      category = GeoUnitCategory.TOPO_MAPS_50000,
      unitTitle = "10-11 ශ්‍රේණි - 1:50,000 භූ ලක්ෂණ සිතියම් (උතුරු දිශා 3)",
      questionPrompt = "1:50,000 භූ ලක්ෂණ සිතියමක දැක්වෙන TN, GN, MN යන කෙටි යෙදුම්වලින් අදහස් වන්නේ කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 77 - ප්‍රශ්න අංක 01 (v) සිතියමේ උතුරු 3 සටහන",
      options = listOf(
        "1. TN = සත්‍ය උතුර (True North), GN = ජාල උතුර (Grid North), MN = චුම්බක උතුර (Magnetic North)",
        "2. TN = තාවකාලික උතුර, GN = ගෝලීය උතුර, MN = මධ්‍යම උතුර",
        "3. TN = භෞතික උතුර, GN = මිනින්දෝරු උතුර, MN = නාවික උතුර",
        "4. TN = නගර උතුර, GN = ග්‍රාම උතුර, MN = මහාමාර්ග උතුර"
      ),
      correctOptionIndex = 0,
      explanationSinhala = "1:50,000 භූ ලක්ෂණ සිතියමක උතුරු 3 කි: 1. TN (True North) = සත්‍ය උතුර (උත්තර ධ්‍රැවය දෙසට), 2. GN (Grid North) = ජාල උතුර (සිතියමේ සිරස් ජාල රේඛා ඔස්සේ), 3. MN (Magnetic North) = චුම්බක උතුර (මාලිමා කටුව යොමුවන උත්තර චුම්බක ධ්‍රැවය දෙසට).",
      syllabusKeyFact = "TN = සත්‍ය උතුර (True North) • GN = ජාල උතුර (Grid North) • MN = චුම්බක උතුර (Magnetic North)",
      examTip = "සිතියම් පත්‍ර 92 කින් මුළු ශ්‍රී ලංකාවම 1:50,000 පරිමාණයෙන් ආවරණය කර ඇත.",
      pdfPageRef = "පිටුව 77"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q27_contour_landforms",
      number = 27,
      gradeLevel = GeoGradeLevel.ALL,
      category = GeoUnitCategory.TOPO_MAPS_50000,
      unitTitle = "10-11 ශ්‍රේණි - සමෝච්ච රේඛා මඟින් භූ රූප හඳුනාගැනීම",
      questionPrompt = "සමෝච්ච රේඛා (Contour Lines) එකිනෙකට ඉතා ආසන්නව පිහිටා ඇති විට නිරූපණය වන භූ බෑවුම් ලක්ෂණය කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 86 & 87 - සමෝච්ච රේඛා භූ රූප හඳුනාගැනීම",
      options = listOf(
        "1. මෘදු / මධ්‍යස්ථ බෑවුම (Gentle Slope)",
        "2. බෑවුම් රහිත සමතලා තැනිතලාව",
        "3. ප්‍රපාතාකාර / දැඩි බෑවුම (Steep Slope / Cliff)",
        "4. කේතුකාකාර කඳු මුදුන"
      ),
      correctOptionIndex = 2,
      explanationSinhala = "සමෝච්ච රේඛා එකිනෙකට ඉතා ළංව (නිරතුරුව) ඇඳ ඇති විට එයින් 'දැඩි / ප්‍රපාතාකාර බෑවුමක්' (Steep Slope) නිරූපණය වේ. සමෝච්ච රේඛා එකිනෙකට ඈත්ව පිහිටි විට 'මෘදු බෑවුමක්' (Gentle Slope) නිරූපණය වේ.",
      syllabusKeyFact = "සමෝච්ච රේඛා ළංව ඇත්නම් -> දැඩි බෑවුම | සමෝච්ච රේඛා ඈත්ව ඇත්නම් -> මෘදු බෑවුම",
      examTip = "සමෝච්ච රේඛා V හැඩයෙන් උස් බිම් දෙසට යොමුව ඇත්නම් එය ගංගා නිම්නයකි; පහත් බිම් දෙසට යොමුව ඇත්නම් එය වැටියකි (Spur).",
      pdfPageRef = "පිටුව 86"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q28_pie_chart_calculation",
      number = 28,
      gradeLevel = GeoGradeLevel.GRADE_11,
      category = GeoUnitCategory.TOPO_MAPS_50000,
      unitTitle = "11 ශ්‍රේණිය - ප්‍රස්තාර (බෙදූ වෘත්ත ප්‍රස්තාර අංශක ගණනය)",
      questionPrompt = "ලෝක ජනගහනයෙන් 60% ක් ආසියා මහාද්වීපයේ වාසය කරයි නම්, බෙදූ වෘත්ත ප්‍රස්තාරයක (Pie Chart) ආසියාව නිරූපණය කිරීමට වෙන් කළ යුතු කෝණයේ අගය කොපමණද?",
      subQuestionOrContext = "PDF පිටුව 92 - ප්‍රශ්න අංක 04 (වෘත්ත ප්‍රස්තාර අංශක ගණනය)",
      options = listOf(
        "1. 60°",
        "2. 120°",
        "3. 216° (ගණනය: 60/100 × 360° = 216°)",
        "4. 180°"
      ),
      correctOptionIndex = 2,
      explanationSinhala = "වෘත්තයක කේන්ද්‍රය වටා සම්පූර්ණ කෝණය 360° කි. ප්‍රතිශතයක් අංශක බවට පත් කිරීමේ සූත්‍රය: (දත්ත අගය ÷ මුළු එකතුව) × 360°. ඒ අනුව ආසියාව සඳහා: (60 ÷ 100) × 360° = 216° වේ.",
      syllabusKeyFact = "කෝණය ගණනය = (අගය / මුළු එකතුව) × 360° -> (60/100) × 360° = 216°",
      examTip = "අප්‍රිකාව (16%) = 57.6° | යුරෝපය (10%) = 36° | උතුරු ඇමෙරිකාව (8%) = 28.8° | දකුණු ඇමෙරිකාව (5%) = 18° | ඕස්ට්‍රේලියාව (1%) = 3.6°.",
      pdfPageRef = "පිටුව 92"
    ),

    // --------------------------------------------------------------------------
    // 🗺️ ප්‍රායෝගික භූගෝල විද්‍යාව සහ 1:50,000 භූ ලක්ෂණ සිතියම් (සම්පූර්ණ ප්‍රශ්නාවලිය)
    // --------------------------------------------------------------------------
    GeoAutoCheckQuestion(
      id = "geo_q29_map_types_classification",
      number = 29,
      gradeLevel = GeoGradeLevel.ALL,
      category = GeoUnitCategory.PHYSICAL_FEATURES,
      unitTitle = "10-11 ශ්‍රේණි - සිතියම් වර්ගීකරණය (තේමා & භූ ලක්ෂණ සිතියම්)",
      questionPrompt = "භූමිය මත දැක්වෙන භෞතික හා සංස්කෘතික ලක්ෂණ පැතලි තලයක් මත පරිමාණානුකූලව ඉදිරිපත් කිරීම හඳුන්වන්නේ කිනම් සිතියම් වර්ගය ලෙසද?",
      subQuestionOrContext = "PDF පිටුව 01/02 - සිතියම් වර්ගීකරණය",
      options = listOf(
        "1. තේමා සිතියම් (Thematic Maps)",
        "2. භූ ලක්ෂණ සිතියම් (Topographical Maps)",
        "3. දේශගුණික සිතියම් පමණි",
        "4. ජන ව්‍යාප්ති සිතියම්"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "භූමිය මත පවතින භෞතික (භූ විෂමතාව, ජලවහනය, ස්වාභාවික වෘක්ෂලතා, වෙරළ ලක්ෂණ) සහ සංස්කෘතික (මාර්ග රටා, ජනාවාස, කෘෂි භෝග, නගර, පරිපාලන මායිම්) යන සියලු ලක්ෂණ නිශ්චිත පරිමාණයකට අනුව පැතලි තලයක දැක්වීම 'භූ ලක්ෂණ සිතියම්' (Topographical Maps) වේ. එක් සුවිශේෂී කරුණක් පමණක් දක්වන්නේ 'තේමා සිතියම්' (Thematic Maps) මඟිනි.",
      syllabusKeyFact = "භූ ලක්ෂණ සිතියම් = භෞතික + සංස්කෘතික ලක්ෂණ | තේමා සිතියම් = එක් සුවිශේෂී කරුණක් පමණි (උදා: දේශගුණික, ජන ව්‍යාප්ති).",
      examTip = "භෞතික ලක්ෂණ: භූ විෂමතාව, ගංගා, වනාන්තර. සංස්කෘතික ලක්ෂණ: පරිපාලන මායිම්, මාර්ග, ජනාවාස, වගාබිම්, ගොඩනැගිලි.",
      pdfPageRef = "පිටුව 01"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q30_metric_base_origin",
      number = 30,
      gradeLevel = GeoGradeLevel.ALL,
      category = GeoUnitCategory.TOPO_MAPS_50000,
      unitTitle = "10-11 ශ්‍රේණි - ශ්‍රී ලංකාවේ මෙට්‍රික් සිතියම් පදනම් මූලය (False Origin)",
      questionPrompt = "ශ්‍රී ලංකාවේ 1:50,000 මෙට්‍රික් සිතියම් පද්ධතියේ පදනම් මූලය (False Origin) ස්ථානගත කර ඇත්තේ කෙසේද?",
      subQuestionOrContext = "PDF පිටුව 02/03 - පදනම් මූලය සහ ජාල පද්ධතිය (Grid System)",
      options = listOf(
        "1. පීදුරුතලාගල කඳු මුදුනේ සිට 200km බටහිරට හා 200km දකුණට හමුවන ලක්ෂ්‍යය",
        "2. කොළඹ කොටුව ඔරලෝසු කණුව කේන්ද්‍ර කරගනිමින්",
        "3. දෙවුන්දර තුඩුවේ සිට 500km උතුරට පිහිටි ලක්ෂ්‍යය",
        "4. මහනුවර දළදා මාළිගාව කේන්ද්‍ර කරගනිමින්"
      ),
      correctOptionIndex = 0,
      explanationSinhala = "ශ්‍රී ලංකාවේ උසම කඳු මුදුන වන පිදුරුතලාගල කඳු මුදුනේ සිට 200km බටහිරට ගොස් එතැන් සිට දකුණු දිශාවට අඳින ලද සිරස් රේඛාවත්, 200km දකුණට ගොස් එතැන් සිට බටහිරට අඳින සිරස් රේඛාවත් හමුවන මුහුදේ පිහිටි ලක්ෂ්‍යය ශ්‍රී ලංකාවේ මෙට්‍රික් සිතියම් නිර්මාණයට 'පදනම් මූලය' (False Origin) ලෙස සකස් කර ඇත.",
      syllabusKeyFact = "පදනම් මූලය = පිදුරුතලාගල සිට 200km බටහිරට + 200km දකුණට හමුවන ලක්ෂ්‍යය. මුළු ලංකාවම ආවරණය වන පරිදි කොටු 92 ක් සකසා ඇත.",
      examTip = "සෑම සිතියම් පත්‍රයකම ජාතික මෙට්‍රික් ඛණ්ඩාංක කිලෝමීටර් 5 න් 5 ට (සෙ.මී. 10 න් 10 ට) නිල් පාටින් සලකුණු කර ඇත.",
      pdfPageRef = "පිටුව 02"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q31_scale_area_calculation",
      number = 31,
      gradeLevel = GeoGradeLevel.ALL,
      category = GeoUnitCategory.TOPO_MAPS_50000,
      unitTitle = "10-11 ශ්‍රේණි - 1:50,000 සිතියම් පරිමාණය සහ වර්ගඵලය ගණනය",
      questionPrompt = "1:50,000 සිතියමක දිග 8cm සහ පළල 6cm වන චතුරස්‍ර කොටසක සැබෑ බිමේ වර්ගඵලය වර්ග කිලෝමීටර් කීයද?",
      subQuestionOrContext = "PDF පිටුව 05 - සිතියම් කොටසක වර්ගඵලය සෙවීම (ගණනය කිරීම)",
      options = listOf(
        "1. වර්ග කිලෝමීටර් 48 km²",
        "2. වර්ග කිලෝමීටර් 12 km² (සැබෑ දිග 4km × සැබෑ පළල 3km = 12km²)",
        "3. වර්ග කිලෝමීටර් 24 km²",
        "4. වර්ග කිලෝමීටර් 6 km²"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "1:50,000 සිතියමක සෙ.මී. 2 ක් = භූමියේ කි.මී. 1 කි. එබැවින් සෙ.මී. 8 ක සැබෑ දිග = 8 ÷ 2 = 4 km වේ. සෙ.මී. 6 ක සැබෑ පළල = 6 ÷ 2 = 3 km වේ. වර්ගඵලය = සැබෑ දිග × සැබෑ පළල = 4 km × 3 km = 12 km² (වර්ග කිලෝමීටර් 12) වේ.",
      syllabusKeyFact = "1:50,000 සිතියමේ 1cm = 50,000cm = 500m -> 2cm = 1km. වර්ගඵලය සෙවීමේදී (දිග cm ÷ 2) × (පළල cm ÷ 2) = වර්ග කිලෝමීටර්.",
      examTip = "සිතියමේ දිග 6cm නම් සැබෑ දිග 3km වේ. සිතියමේ 2cm × 2cm කොටුවක සැබෑ වර්ගඵලය = 1km × 1km = 1km² වේ.",
      pdfPageRef = "පිටුව 05"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q32_drainage_patterns",
      number = 32,
      gradeLevel = GeoGradeLevel.ALL,
      category = GeoUnitCategory.PHYSICAL_FEATURES,
      unitTitle = "10-11 ශ්‍රේණි - ජලවහන රටා (Drainage Patterns)",
      questionPrompt = "මධ්‍යම කඳු මුදුනක සිට සෑම දිශාවකටම අරීයව (රෝදයක අර මෙන්) ගලා බසින ජලවහන රටාව හඳුන්වන්නේ කුමන නමකින්ද?",
      subQuestionOrContext = "PDF පිටුව 09 - ජලවහන රටා හඳුනාගැනීම",
      options = listOf(
        "1. ශාඛීය ජලවහන රටාව (Dendritic Pattern)",
        "2. අරීය ජලවහන රටාව (Radial Pattern)",
        "3. ජාලාකාර ජලවහන රටාව (Trellis Pattern)",
        "4. වලයාකාර ජලවහන රටාව (Annular Pattern)"
      ),
      correctOptionIndex = 1,
      explanationSinhala = "හුදකලා කන්දක හෝ ගෝලාකාර කඳු මුදුනක සිට පිටතට විහිදෙන පරිදි ගංගා ගලා බසින රටාව 'අරීය ජලවහන රටාව' (Radial Pattern) නම් වේ. ගසක අතු විහිදෙන ආකාරයට ප්‍රධාන ගංගාවට අතු ගංගා එක්වන රටාව 'ශාඛීය ජලවහන රටාව' වන අතර, සමාන්තර කඳු වැටි ඔස්සේ ඍජුකෝණාස්‍රාකාරව එක්වන රටාව 'ජාලාකාර ජලවහන රටාව' වේ.",
      syllabusKeyFact = "ප්‍රධාන ජලවහන රටා 4: 1. ශාඛීය (Dendritic) 2. අරීය (Radial) 3. ජාලාකාර (Trellis) 4. වලයාකාර (Annular).",
      examTip = "ගංගාවක පහළ නිම්නයේ දැකිය හැකි ලක්ෂණ: ගංමෝය, ඩෙල්ටාව, අපශාඛා, ගංදඟර (Meanders), හැඩපළු විල / දුනු විල (Ox-bow Lake), වගුරු බිම්.",
      pdfPageRef = "පිටුව 09"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q33_slopes_and_landforms",
      number = 33,
      gradeLevel = GeoGradeLevel.ALL,
      category = GeoUnitCategory.TOPO_MAPS_50000,
      unitTitle = "10-11 ශ්‍රේණි - භූ විෂමතා ලක්ෂණ & බෑවුම් වර්ග (Slopes & Landforms)",
      questionPrompt = "සමෝච්ච රේඛා ඉහළ ප්‍රදේශයේදී එකිනෙකට ඈත්ව පිහිටා, පහළ ප්‍රදේශයේදී එකිනෙකට ළංව පිහිටන විට ඇතිවන බෑවුම කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 06/08 - බෑවුම් වර්ග හඳුනාගැනීම",
      options = listOf(
        "1. උත්තල බෑවුම (Convex Slope)",
        "2. අවතල බෑවුම (Concave Slope)",
        "3. ඒකාකාර මෘදු බෑවුම (Uniform Slope)",
        "4. මෝහොර බෑවුම (Escarpment / Cliff)"
      ),
      correctOptionIndex = 0,
      explanationSinhala = "ඉහළදී සමෝච්ච රේඛා ඈත්ව පිහිටා පහළට එත්ම ළංවන විට එය 'උත්තල බෑවුමකි' (Convex Slope). ඊට ප්‍රතිවිරුද්ධව ඉහළදී ළංව පිහිටා පහළට එත්ම ඈත්වන්නේ නම් එය 'අවතල බෑවුමකි' (Concave Slope). තනි බෑවුමක එක් පැත්තක් අතිශය දැඩි බෑවුමක් සහිත නම් එය 'මෝහොර බෑවුමකි'.",
      syllabusKeyFact = "උත්තල බෑවුම: ඉහළ මෘදුයි (ඈත්ව ඇත) -> පහළ දැඩියි (ළංව ඇත). අවතල බෑවුම: ඉහළ දැඩියි (ළංව ඇත) -> පහළ මෘදුයි (ඈත්ව ඇත).",
      examTip = "ප්‍රධාන භූ රූප: කඳු වැටිය (Ridge), නිම්නය (Valley), නෙරුව (Spur), සානුව (Plateau), කපොල්ල (Pass/Col), හුදකලා කන්ද (Isolated Hill), කොත් කන්ද (Peak).",
      pdfPageRef = "පිටුව 08"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q34_kotmale_map_analysis",
      number = 34,
      gradeLevel = GeoGradeLevel.GRADE_11,
      category = GeoUnitCategory.TOPO_MAPS_50000,
      unitTitle = "10-11 ශ්‍රේණි - කොත්මලේ 1:50,000 සිතියම් විශ්ලේෂණය",
      questionPrompt = "කොත්මලේ ජලාශය පටු දිගටි හැඩයක් ගැනීමට ප්‍රධානතම භෞතික හේතුව කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 12/13/14 - කොත්මලේ භූ ලක්ෂණ සිතියම් අභ්‍යාසය",
      options = listOf(
        "1. දෙපස පිහිටි දැඩි බෑවුම් සහිත කඳු වැටි අතර පටු නිම්නයක් ඔස්සේ ජලය එක්රැස් කර වේල්ල බැඳ තිබීම",
        "2. මිනිසුන් විසින් හිතාමතාම දිගට කැණීම් කර තිබීම",
        "3. ගංගාවේ ජලය හිඟ වීම",
        "4. මුහුදු වෙරළ ආසන්නයේ පිහිටා තිබීම"
      ),
      correctOptionIndex = 0,
      explanationSinhala = "කොත්මලේ ජලාශය පටු දිගටි හැඩයක් ගන්නේ එය දෙපස පිහිටි කඳු වැටි අතර ගැඹුරු පටු නිම්නයක් ඔස්සේ ගලා ගිය කොත්මලේ ඔය හරස් කර වේල්ල ඉදිකර ජලය රඳවා ඇති බැවිනි. එම ප්‍රදේශයේ තේ වගාව බහුල වීමට හේතු වන්නේ උසැති කඳුකර භූමිය, මනා ජලවහනය, සහ සිසිල් දේශගුණයයි.",
      syllabusKeyFact = "කොත්මලේ ජලාශය = පටු කඳු නිම්නයක වේල්ල බැඳ තැනූ ජලාශයකි. කඳු බෑවුම්වල ප්‍රධාන ආර්ථික භෝගය තේ වගාවයි.",
      examTip = "කඳුකර සිතියමක මාර්ග ඍජු නොවී වක්‍රාකාරව (සර්පිලාකාරව) විහිදෙන්නේ දැඩි බෑවුම් සහ කඳු විෂමතා මඟහැරීමටයි.",
      pdfPageRef = "පිටුව 13"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q35_mahiyangana_monaragala_maps",
      number = 35,
      gradeLevel = GeoGradeLevel.GRADE_11,
      category = GeoUnitCategory.TOPO_MAPS_50000,
      unitTitle = "10-11 ශ්‍රේණි - මහියංගනය, මොනරාගල & හපුතලේ සිතියම් අධ්‍යයනය",
      questionPrompt = "මොනරාගල සහ මහියංගනය වැනි වියළි/අන්තර් කලාපීය සිතියම් ප්‍රදේශවල වැව් විශාල සංඛ්‍යාවක් විසිරී පැවතීමට හේතුව කුමක්ද?",
      subQuestionOrContext = "PDF පිටුව 15/17/23 - අංක 55 මහියංගනය සහ මොනරාගල සිතියම් කොටස්",
      options = listOf(
        "1. වියළි කාලගුණය සහ සෘතුමය වර්ෂාව නිසා කෘෂිකර්මාන්තයට (වී වගාවට) ජලය ගබඩා කරගැනීමේ අවශ්‍යතාවය",
        "2. අධික ගංවතුර නිසා මුහුදට ජලය බැසයාම වැළැක්වීමට පමණි",
        "3. මත්ස්‍ය කර්මාන්තය පමණක් සිදු කිරීමට",
        "4. දැඩි හිමපතනයෙන් ආරක්ෂා වීමට"
      ),
      correctOptionIndex = 0,
      explanationSinhala = "වියළි සහ අන්තර් කලාපීය ප්‍රදේශවල (මහියංගනය, මොනරාගල, හපුතලේ පහළ ප්‍රදේශ) වැසි නොමැති වියළි කාලවලදී වී ගොවිතැනට සහ ජනතා අවශ්‍යතාවලට ජලය රඳවා ගැනීමට සමෝච්ච මට්ටම් උපයෝගී කරගෙන වැව් පද්ධති නිර්මාණය කර ඇත.",
      syllabusKeyFact = "වියළි කලාපීය සිතියම්වල ලක්ෂණ: වැව් බහුල වීම, කුඹුරු ඉඩම්, ලඳු කැලෑ/හේන් වගා, විසිරුණු හෝ මාර්ගබඩ ජනාවාස.",
      examTip = "සොරබොර වැවේ අක්‍රමවත් හැඩයට හේතුව ස්වාභාවික නිම්න සමෝච්ච රේඛා ඔස්සේ ජලය පැතිරී තිබීමයි.",
      pdfPageRef = "පිටුව 15"
    ),

    GeoAutoCheckQuestion(
      id = "geo_q36_world_continents_features",
      number = 36,
      gradeLevel = GeoGradeLevel.ALL,
      category = GeoUnitCategory.PHYSICAL_FEATURES,
      unitTitle = "10-11 ශ්‍රේණි - ලෝක මහාද්වීප සහ ලංකා සිතියමේ ප්‍රධාන ස්ථාන",
      questionPrompt = "ලෝක සිතියමේ දැක්වෙන 'ඇන්ඩීස් කඳු වැටිය', 'ඇමසන් ගංගාව' සහ 'පැම්පාස් තෘණ බිම' පිහිටා ඇත්තේ කිනම් මහාද්වීපයේද?",
      subQuestionOrContext = "PDF පිටුව 30-33 - මහාද්වීප සිතියම් සහ ලකුණු කිරීම්",
      options = listOf(
        "1. උතුරු ඇමෙරිකා මහාද්වීපය",
        "2. අප්‍රිකා මහාද්වීපය",
        "3. දකුණු ඇමෙරිකා මහාද්වීපය (South America)",
        "4. ඕස්ට්‍රේලියා මහාද්වීපය"
      ),
      correctOptionIndex = 2,
      explanationSinhala = "දකුණු ඇමෙරිකා මහාද්වීපයේ ප්‍රධාන භූගෝලීය ලක්ෂණ වන්නේ: බටහිර වෙරළ දිගේ විහිදෙන ඇන්ඩීස් කඳු වැටිය (ලොව දිගම කඳු වැටිය), ලොව විශාලතම ජල ධාරිතාව සහිත ඇමසන් ගංගාව, අටකාමා කාන්තාරය, සහ ආර්ජන්ටිනාවේ පැම්පාස් තෘණ බිමයි.",
      syllabusKeyFact = "දකුණු ඇමෙරිකාව: ඇන්ඩීස් කඳු වැටිය, ඇමසන් ගඟ, පැම්පාස් තෘණ බිම, ටිටිකාකා විල, අටකාමා කාන්තාරය.",
      examTip = "ලංකා සිතියමේ වැදගත් ස්ථාන: වී පර්යේෂණාගාර (බත් Run/බතලගොඩ, මහාඉලුප්පල්ලම), තේ (තලවකැලේ), රබර් (අගලවත්ත), පොල් (ලුණුවිල), සුළු පොල් ත්‍රිකෝණය (රන්න-මිද්දෙනිය-තංගල්ල).",
      pdfPageRef = "පිටුව 33"
    )
  )
}

// ==============================================================================
// COMPOSABLE: GEOGRAPHY AUTO-CHECKER & 100% COMPREHENSIVE EXPLANATIONS SCREEN
// ==============================================================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeographyAutoCheckerScreen(
  onBack: () -> Unit
) {
  var selectedGrade by remember { mutableStateOf(GeoGradeLevel.ALL) }
  var selectedCategory by remember { mutableStateOf(GeoUnitCategory.ALL) }
  var searchQuery by remember { mutableStateOf("") }
  var isPdfViewerOpen by remember { mutableStateOf(false) }

  // State to track user selections for each question: questionId -> selectedOptionIndex
  val userSelections = remember { mutableStateMapOf<String, Int>() }
  // State to track whether explanation is expanded: questionId -> Boolean
  val expandedExplanations = remember { mutableStateMapOf<String, Boolean>() }

  val filteredQuestions = remember(selectedGrade, selectedCategory, searchQuery) {
    GeographyAutoCheckerRepository.allQuestions.filter { q ->
      val matchesGrade = (selectedGrade == GeoGradeLevel.ALL) || (q.gradeLevel == selectedGrade) || (q.gradeLevel == GeoGradeLevel.ALL)
      val matchesCategory = (selectedCategory == GeoUnitCategory.ALL) || (q.category == selectedCategory)
      val matchesSearch = searchQuery.isBlank() ||
          q.questionPrompt.contains(searchQuery, ignoreCase = true) ||
          q.unitTitle.contains(searchQuery, ignoreCase = true) ||
          q.explanationSinhala.contains(searchQuery, ignoreCase = true)
      matchesGrade && matchesCategory && matchesSearch
    }
  }

  // Calculate score statistics
  val totalAnswered = userSelections.size
  val correctCount = userSelections.count { (qId, selectedIdx) ->
    val question = GeographyAutoCheckerRepository.allQuestions.find { it.id == qId }
    question?.correctOptionIndex == selectedIdx
  }

  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Column {
            Text(
              text = "10/11 භූගෝලය කෙටි සටහන් Auto-Checker",
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis
            )
            Text(
              text = "100% නිවැරදි ස්වයංක්‍රීය පරීක්ෂාව & සවිස්තර විවරණ",
              fontSize = 11.sp,
              color = Color(0xFF0284C7)
            )
          }
        },
        navigationIcon = {
          IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "ආපසු")
          }
        },
        actions = {
          IconButton(
            onClick = { isPdfViewerOpen = true },
            modifier = Modifier.testTag("geo_open_pdf_btn")
          ) {
            Icon(Icons.Default.PictureAsPdf, contentDescription = "PDF බලන්න", tint = Color(0xFFDC2626))
          }
          IconButton(
            onClick = {
              userSelections.clear()
              expandedExplanations.clear()
            },
            modifier = Modifier.testTag("geo_reset_answers_btn")
          ) {
            Icon(Icons.Default.Refresh, contentDescription = "නැවත මුල සිට", tint = Color(0xFF0284C7))
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = MaterialTheme.colorScheme.surface
        )
      )
    }
  ) { paddingValues ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        .background(Color(0xFFF8FAFC)),
      contentPadding = PaddingValues(bottom = 24.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      // 1. Top Score & Summary Banner
      item {
        GeoScoreBanner(
          totalQuestions = filteredQuestions.size,
          totalAnswered = totalAnswered,
          correctCount = correctCount,
          onOpenPdf = { isPdfViewerOpen = true }
        )
      }

      // 2. Grade Tabs
      item {
        LazyRow(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 2.dp),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          items(GeoGradeLevel.values()) { grade ->
            val isSelected = selectedGrade == grade
            FilterChip(
              selected = isSelected,
              onClick = { selectedGrade = grade },
              label = {
                Text(
                  text = grade.label,
                  fontSize = 12.sp,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
              },
              colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = grade.badgeColor,
                selectedLabelColor = Color.White
              )
            )
          }
        }
      }

      // 3. Unit / Category Tabs
      item {
        LazyRow(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 2.dp),
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          items(GeoUnitCategory.values()) { cat ->
            val isSelected = selectedCategory == cat
            Surface(
              shape = RoundedCornerShape(20.dp),
              color = if (isSelected) cat.primaryColor else cat.bgLightColor,
              border = BorderStroke(1.dp, if (isSelected) cat.primaryColor else cat.primaryColor.copy(alpha = 0.2f)),
              modifier = Modifier
                .clickable { selectedCategory = cat }
                .clip(RoundedCornerShape(20.dp))
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(cat.iconEmoji, fontSize = 13.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = cat.displayName,
                  fontSize = 11.sp,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                  color = if (isSelected) Color.White else cat.primaryColor
                )
              }
            }
          }
        }
      }

      // 4. Search Bar
      item {
        OutlinedTextField(
          value = searchQuery,
          onValueChange = { searchQuery = it },
          placeholder = { Text("ප්‍රශ්න හෝ මාතෘකා සොයන්න...", fontSize = 12.sp) },
          leadingIcon = { Icon(Icons.Default.Search, contentDescription = "සොයන්න", tint = Color(0xFF64748B)) },
          trailingIcon = {
            if (searchQuery.isNotEmpty()) {
              IconButton(onClick = { searchQuery = "" }) {
                Icon(Icons.Default.Close, contentDescription = "Clear")
              }
            }
          },
          singleLine = true,
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .height(50.dp),
          colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
          )
        )
      }

      // 5. Questions List
      if (filteredQuestions.isEmpty()) {
        item {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .padding(32.dp),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "ප්‍රශ්න හමු නොවීය. කරුණාකර වෙනත් කාණ්ඩයක් හෝ සෙවුම් වචනයක් තෝරන්න.",
              textAlign = TextAlign.Center,
              color = Color(0xFF64748B),
              fontSize = 13.sp
            )
          }
        }
      } else {
        itemsIndexed(filteredQuestions, key = { _, q -> q.id }) { index, question ->
          val userSelectedOption = userSelections[question.id]
          val isExpanded = expandedExplanations[question.id] ?: false

          Box(modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)) {
            GeoQuestionInteractiveCard(
              index = index + 1,
              question = question,
              userSelectedOption = userSelectedOption,
              isExplanationExpanded = isExpanded,
              onOptionSelected = { optionIdx ->
                userSelections[question.id] = optionIdx
                // Auto expand explanation if answered incorrectly
                if (optionIdx != question.correctOptionIndex) {
                  expandedExplanations[question.id] = true
                }
              },
              onToggleExplanation = {
                expandedExplanations[question.id] = !isExpanded
              }
            )
          }
        }
      }
    }
  }

  // Full Screen PDF Viewer Modal
  if (isPdfViewerOpen) {
    GeoPdfViewerDialog(
      pdfUrl = GeographyAutoCheckerRepository.pdfDriveUrl,
      onDismiss = { isPdfViewerOpen = false }
    )
  }
}

@Composable
private fun GeoScoreBanner(
  totalQuestions: Int,
  totalAnswered: Int,
  correctCount: Int,
  onOpenPdf: () -> Unit
) {
  Card(
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 12.dp, vertical = 6.dp)
      .clip(RoundedCornerShape(14.dp))
      .background(
        Brush.horizontalGradient(
          colors = listOf(Color(0xFF0F172A), Color(0xFF0369A1), Color(0xFF0284C7))
        )
      )
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
          modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.2f)),
          contentAlignment = Alignment.Center
        ) {
          Text("📊", fontSize = 22.sp)
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
          Text(
            text = "ප්‍රශ්න ප්‍රගතිය: $totalAnswered / $totalQuestions",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
          Text(
            text = if (totalAnswered > 0) "නිවැරදි පිළිතුරු: $correctCount ($((correctCount * 100) / totalAnswered)%)" else "පිළිතුරු ලබාදී ස්වයංක්‍රීයව පරීක්ෂා කරන්න",
            fontSize = 11.sp,
            color = if (totalAnswered > 0 && (correctCount * 100 / totalAnswered) >= 75) Color(0xFF86EFAC) else Color(0xFFBAE6FD)
          )
        }
      }

      Button(
        onClick = onOpenPdf,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC2626)),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
        shape = RoundedCornerShape(8.dp)
      ) {
        Icon(Icons.Default.PictureAsPdf, contentDescription = null, modifier = Modifier.size(14.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text("මුල් PDF", fontSize = 11.sp, fontWeight = FontWeight.Bold)
      }
    }
  }
}

@Composable
private fun GeoQuestionInteractiveCard(
  index: Int,
  question: GeoAutoCheckQuestion,
  userSelectedOption: Int?,
  isExplanationExpanded: Boolean,
  onOptionSelected: (Int) -> Unit,
  onToggleExplanation: () -> Unit
) {
  val isAnswered = userSelectedOption != null
  val isCorrect = isAnswered && userSelectedOption == question.correctOptionIndex

  Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = when {
        !isAnswered -> Color.White
        isCorrect -> Color(0xFFF0FDF4)
        else -> Color(0xFFFEF2F2)
      }
    ),
    border = BorderStroke(
      1.dp,
      when {
        !isAnswered -> Color(0xFFE2E8F0)
        isCorrect -> Color(0xFF22C55E)
        else -> Color(0xFFEF4444)
      }
    ),
    modifier = Modifier
      .fillMaxWidth()
      .testTag("geo_q_card_${question.id}")
  ) {
    Column(modifier = Modifier.padding(14.dp)) {
      // Header: Grade badge, Category & PDF Page
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = question.category.primaryColor
          ) {
            Text(
              text = "ප්‍රශ්න $index",
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = question.unitTitle,
            fontSize = 10.5.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF64748B),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }

        Surface(
          shape = RoundedCornerShape(4.dp),
          color = Color(0xFFE2E8F0)
        ) {
          Text(
            text = question.pdfPageRef,
            fontSize = 9.5.sp,
            color = Color(0xFF475569),
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Question Prompt
      Text(
        text = question.questionPrompt,
        fontSize = 13.5.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF0F172A),
        lineHeight = 20.sp
      )

      // Sub Context if present
      if (!question.subQuestionOrContext.isNullOrBlank()) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "📌 ${question.subQuestionOrContext}",
          fontSize = 11.sp,
          color = Color(0xFF0369A1),
          fontWeight = FontWeight.Medium
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Interactive Options List
      question.options.forEachIndexed { optIndex, optionText ->
        val isThisOptionSelected = userSelectedOption == optIndex
        val isThisOptionCorrect = optIndex == question.correctOptionIndex

        val optionBgColor = when {
          !isAnswered -> Color(0xFFF8FAFC)
          isThisOptionCorrect -> Color(0xFFDCFCE7)
          isThisOptionSelected && !isCorrect -> Color(0xFFFEE2E2)
          else -> Color(0xFFF8FAFC)
        }

        val optionBorderColor = when {
          !isAnswered -> Color(0xFFE2E8F0)
          isThisOptionCorrect -> Color(0xFF16A34A)
          isThisOptionSelected && !isCorrect -> Color(0xFFDC2626)
          else -> Color(0xFFE2E8F0)
        }

        Surface(
          shape = RoundedCornerShape(8.dp),
          color = optionBgColor,
          border = BorderStroke(1.dp, optionBorderColor),
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
            .clickable { onOptionSelected(optIndex) }
            .testTag("geo_q_${question.id}_opt_$optIndex")
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            // Radio-like Icon / Status Icon
            Box(
              modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(
                  when {
                    !isAnswered -> Color.White
                    isThisOptionCorrect -> Color(0xFF16A34A)
                    isThisOptionSelected && !isCorrect -> Color(0xFFDC2626)
                    else -> Color.White
                  }
                )
                .then(
                  if (!isAnswered) Modifier.background(Color.White, CircleShape) else Modifier
                ),
              contentAlignment = Alignment.Center
            ) {
              if (isAnswered) {
                if (isThisOptionCorrect) {
                  Icon(Icons.Default.Check, contentDescription = "හරි", tint = Color.White, modifier = Modifier.size(14.dp))
                } else if (isThisOptionSelected) {
                  Icon(Icons.Default.Close, contentDescription = "වැරදියි", tint = Color.White, modifier = Modifier.size(14.dp))
                }
              }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
              text = optionText,
              fontSize = 12.sp,
              color = when {
                !isAnswered -> Color(0xFF1E293B)
                isThisOptionCorrect -> Color(0xFF14532D)
                isThisOptionSelected && !isCorrect -> Color(0xFF7F1D1D)
                else -> Color(0xFF64748B)
              },
              fontWeight = if (isThisOptionCorrect || isThisOptionSelected) FontWeight.Bold else FontWeight.Normal
            )
          }
        }
      }

      // Auto-Feedback Strip after Answered
      if (isAnswered) {
        Spacer(modifier = Modifier.height(10.dp))
        Surface(
          shape = RoundedCornerShape(8.dp),
          color = if (isCorrect) Color(0xFFDCFCE7) else Color(0xFFFEE2E2),
          border = BorderStroke(1.dp, if (isCorrect) Color(0xFF86EFAC) else Color(0xFFFCA5A5)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Cancel,
                contentDescription = null,
                tint = if (isCorrect) Color(0xFF16A34A) else Color(0xFFDC2626),
                modifier = Modifier.size(18.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = if (isCorrect) "නිවැරදියි! (100% Correct)" else "වැරදියි! නිවැරදි පිළිතුර: ${question.options[question.correctOptionIndex]}",
                fontSize = 11.5.sp,
                fontWeight = FontWeight.Bold,
                color = if (isCorrect) Color(0xFF14532D) else Color(0xFF7F1D1D)
              )
            }

            TextButton(
              onClick = onToggleExplanation,
              contentPadding = PaddingValues(0.dp)
            ) {
              Text(
                text = if (isExplanationExpanded) "විවරණය සඟවන්න" else "විවරණය බලන්න",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = if (isCorrect) Color(0xFF047857) else Color(0xFFB91C1C)
              )
            }
          }
        }
      }

      // 100% Comprehensive Syllabus-Accurate Detailed Explanation & Exam Tips
      AnimatedVisibility(
        visible = isExplanationExpanded || (isAnswered && !isCorrect),
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF1F5F9))
            .padding(10.dp)
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text("💡", fontSize = 14.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = "සවිස්තරාත්මක විෂය නිර්දේශ විවරණය (Detailed Explanation):",
              fontSize = 11.5.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF0F172A)
            )
          }

          Spacer(modifier = Modifier.height(4.dp))

          Text(
            text = question.explanationSinhala,
            fontSize = 11.sp,
            color = Color(0xFF334155),
            lineHeight = 16.sp
          )

          Spacer(modifier = Modifier.height(6.dp))

          // Key Fact Box
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = Color(0xFFE0F2FE),
            border = BorderStroke(1.dp, Color(0xFFBAE6FD)),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text("📌", fontSize = 12.sp)
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = question.syllabusKeyFact,
                fontSize = 10.5.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF0369A1)
              )
            }
          }

          Spacer(modifier = Modifier.height(4.dp))

          // Exam Tip
          Text(
            text = "🎯 විභාග ඉඟිය: ${question.examTip}",
            fontSize = 10.5.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFFB45309)
          )
        }
      }
    }
  }
}

@Composable
private fun GeoPdfViewerDialog(
  pdfUrl: String,
  onDismiss: () -> Unit
) {
  androidx.compose.ui.window.Dialog(
    onDismissRequest = onDismiss,
    properties = androidx.compose.ui.window.DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = Color(0xFF0F172A)
    ) {
      Column(modifier = Modifier.fillMaxSize()) {
        // Dialog Top Bar
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1E293B))
            .padding(horizontal = 12.dp, vertical = 10.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onDismiss) {
              Icon(Icons.Default.Close, contentDescription = "වසන්න", tint = Color.White)
            }
            Spacer(modifier = Modifier.width(6.dp))
            Column {
              Text(
                text = "10/11 භූගෝල විද්‍යාව කෙටි සටහන් මුල් PDF ගොනුව",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "Google Drive Secure Viewer (පිටු 93 ක්)",
                color = Color(0xFF94A3B8),
                fontSize = 10.5.sp
              )
            }
          }
        }

        // Webview to render Google Drive preview
        AndroidView(
          factory = { context ->
            WebView(context).apply {
              settings.javaScriptEnabled = true
              settings.domStorageEnabled = true
              settings.loadWithOverviewMode = true
              settings.useWideViewPort = true
              settings.builtInZoomControls = true
              settings.displayZoomControls = false
              webViewClient = WebViewClient()
              loadUrl(pdfUrl)
            }
          },
          modifier = Modifier.fillMaxSize()
        )
      }
    }
  }
}
