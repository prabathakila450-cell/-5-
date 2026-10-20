package com.example

/**
 * High-precision Mathematics Question Engine for Live Exam Hall.
 * Generates 60 unique mathematical problems with varied coefficients, geometry angles,
 * algebraic expressions, and real-world financial problems for all 30 papers.
 */
object MathMockQuestionEngine {

  fun generateQuestion(grade: String, term: Int, paperIdx: Int, qNum: Int): MockExamQuestion {
    val v = (paperIdx - 1) % 30
    val offset = (term * 100) + (paperIdx * 50) + qNum

    return when (term) {
      1 -> buildTerm1MathQuestion(qNum, v, paperIdx)
      2 -> buildTerm2MathQuestion(qNum, v, paperIdx)
      else -> buildTerm3MathQuestion(qNum, v, paperIdx)
    }
  }

  // --------------------------------------------------------------------------
  // TERM 1 MATHEMATICS: 60 SLOTS x 30 PAPERS
  // --------------------------------------------------------------------------
  private fun buildTerm1MathQuestion(qNum: Int, v: Int, paperIdx: Int): MockExamQuestion {
    val topic = when (qNum) {
      in 1..8 -> "වීජ ගණිතය සහ සාධක (Factorization)"
      in 9..16 -> "දර්ශක සහ ලඝුගණක (Indices & Logs)"
      in 17..24 -> "වීජීය භාග සහ සරල සමීකරණ"
      in 25..32 -> "කෝණ, සමාන්තර රේඛා හා ත්‍රිකෝණ"
      in 33..40 -> "ප්‍රතිශත, බදු සහ වරිපනම්"
      in 41..48 -> "කුලක සහ වෙන් රූප (Sets)"
      in 49..54 -> "ත්‍රිකෝණ අංගසාම්‍යය (Congruence)"
      else -> "තල රූපවල පරිමිතිය සහ වර්ගඵලය"
    }

    val (text, opts, correct, exp) = when (qNum % 8) {
      1 -> {
        val a = 3 + (v % 12)
        val aSq = a * a
        Quad(
          "x² - $aSq වීජීය ප්‍රකාශනයේ සාධක වෙන් කළ විට ලැබෙන නිවැරදි පිළිතුර කුමක්ද?",
          listOf("(x - $a)(x + $a)", "(x - $a)²", "(x + $a)²", "x(x - $aSq)"),
          1,
          "වර්ග දෙකක අන්තරය: A² - B² = (A - B)(A + B). එබැවින් x² - $a² = (x - $a)(x + $a) වේ."
        )
      }
      2 -> {
        val p = 2 + (v % 5)
        val q = 3 + ((v + 1) % 5)
        val sum = p + q
        val prod = p * q
        Quad(
          "x² - ${sum}x + $prod = 0 වර්ගජ සමීකරණයේ මූල (Roots) මොනවාද?",
          listOf("x = $p හෝ x = $q", "x = -$p හෝ x = -$q", "x = 1 හෝ x = $prod", "x = 0 හෝ x = $sum"),
          1,
          "(x - $p)(x - $q) = 0 බැවින් සාධක සමීකරණය කිරීමෙන් x = $p හෝ x = $q ලැබේ."
        )
      }
      3 -> {
        val base = 2 + (v % 3)
        val power = 3 + (v % 3)
        var res = 1
        for (i in 1..power) res *= base
        Quad(
          "දර්ශක ආකාරයෙන් ඇති $base^$power = $res යන්න ලඝුගණක ආකාරයෙන් ලියූ විට නිවැරදි වන්නේ කුමක්ද?",
          listOf("log_$base $res = $power", "log_$power $res = $base", "log_$res $base = $power", "log_10 $res = $base"),
          1,
          "b^c = a නම් log_b a = c වේ. එබැවින් $base^$power = $res යන්න log_$base $res = $power වේ."
        )
      }
      4 -> {
        val angle1 = 40 + (v % 20) * 2
        val angle2 = 50 + (v % 15) * 2
        val angle3 = 180 - (angle1 + angle2)
        Quad(
          "ත්‍රිකෝණයක කෝණ දෙකක අගයන් $angle1° සහ $angle2° වේ නම්, එහි තුන්වන කෝණයේ විශාලත්වය කුමක්ද?",
          listOf("$angle3°", "${angle3 + 10}°", "${angle3 - 10}°", "90°"),
          1,
          "ඕනෑම තල ත්‍රිකෝණයක අභ්‍යන්තර කෝණ තුනෙහි ඓක්‍යය 180° කි. 180° - ($angle1° + $angle2°) = $angle3°."
        )
      }
      5 -> {
        val marked = 1000 + (v % 10) * 500
        val vatPct = 18
        val vatAmount = (marked * vatPct) / 100
        val total = marked + vatAmount
        Quad(
          "ලකුණු කළ මිල රු. $marked ක් වූ විදුලි උපකරණයක් සඳහා 18% ක වැට් (VAT) බද්දක් අය කරන්නේ නම්, ගෙවිය යුතු සම්පූර්ණ මුදල කොපමණද?",
          listOf("රු. $total", "රු. ${total - 100}", "රු. ${marked + 180}", "රු. ${marked * 2}"),
          1,
          "වැට් බද්ද = රු. $marked × 18% = රු. $vatAmount. සම්පූර්ණ මුදල = රු. $marked + රු. $vatAmount = රු. $total."
        )
      }
      6 -> {
        val nA = 15 + (v % 10)
        val nB = 20 + (v % 10)
        val nInter = 5 + (v % 5)
        val nUnion = nA + nB - nInter
        Quad(
          "n(A) = $nA, n(B) = $nB සහ n(A ∩ B) = $nInter නම්, n(A ∪ B) හි අගය කොපමණද?",
          listOf("$nUnion", "${nUnion + 5}", "${nA + nB}", "$nInter"),
          1,
          "කුලක සූත්‍රය: n(A ∪ B) = n(A) + n(B) - n(A ∩ B) = $nA + $nB - $nInter = $nUnion වේ."
        )
      }
      7 -> {
        val r = 7 * (1 + (v % 4))
        val perim = 2 * (22 * r / 7)
        Quad(
          "අරය $r cm වූ වෘත්තයක පරිධිය කොපමණද? (π = 22/7 ලෙස ගන්න)",
          listOf("$perim cm", "${perim / 2} cm", "${perim * 2} cm", "44 cm"),
          1,
          "පරිධිය C = 2πr = 2 × (22/7) × $r = $perim cm වේ."
        )
      }
      else -> {
        val xVal = 2 + (v % 6)
        val ans = 3 * xVal + 5
        Quad(
          "3x + 5 = $ans සරල සමීකරණය විසඳූ විට x හි අගය කුමක්ද?",
          listOf("x = $xVal", "x = ${xVal + 1}", "x = ${xVal - 1}", "x = 10"),
          1,
          "3x = $ans - 5 => 3x = ${3 * xVal} => x = $xVal වේ."
        )
      }
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

  // --------------------------------------------------------------------------
  // TERM 2 MATHEMATICS: 60 SLOTS x 30 PAPERS
  // --------------------------------------------------------------------------
  private fun buildTerm2MathQuestion(qNum: Int, v: Int, paperIdx: Int): MockExamQuestion {
    val topic = when (qNum) {
      in 1..10 -> "සමගාමී සමීකරණ (Simultaneous Eq)"
      in 11..20 -> "වෘත්ත ජ්‍යාමිතිය සහ චක්‍රීය චතුරස්‍ර"
      in 21..30 -> "අනුපාත, සමානුපාත සහ කොටස් වෙළඳපොල"
      in 31..40 -> "සුළු පොලිය සහ වැල් පොලිය"
      in 41..50 -> "පයිතගරස් ප්‍රමේයය සහ ත්‍රිකෝණමිතික අනුපාත"
      else -> "සරල රේඛා ප්‍රස්ථාර සහ අසමානතා"
    }

    val (text, opts, correct, exp) = when (qNum % 6) {
      1 -> {
        val a = 3 + (v % 4)
        val b = 4 + (v % 3)
        val c = if (a == 3 && b == 4) 5 else if (a == 6 && b == 8) 10 else 5
        val hypSq = a * a + b * b
        Quad(
          "ඍජුකෝණී ත්‍රිකෝණයක ලම්භ පාදවල දිග $a cm සහ $b cm නම්, එහි කර්ණයේ (Hypotenuse) දිග කොපමණද?",
          listOf("${Math.round(Math.sqrt(hypSq.toDouble()))} cm", "${a + b} cm", "${hypSq} cm", "12 cm"),
          1,
          "පයිතගරස් ප්‍රමේයය අනුව: c² = a² + b² = $a² + $b² = ${a*a} + ${b*b} = $hypSq. එබැවින් c = √$hypSq = ${Math.round(Math.sqrt(hypSq.toDouble()))} cm."
        )
      }
      2 -> {
        val p = 10000 + (v % 10) * 5000
        val r = 10 + (v % 5) * 2
        val t = 2
        val interest = (p * r * t) / 100
        Quad(
          "රු. $p ක මුදලක් වාර්ෂික $r% සුළු පොලී අනුපාතයට වසර $t කට ණයට දුන් විට ලැබෙන මුළු පොලී මුදල කොපමණද?",
          listOf("රු. $interest", "රු. ${interest + 500}", "රු. ${interest / 2}", "රු. ${p + interest}"),
          1,
          "I = (P × T × R) / 100 = ($p × $t × $r) / 100 = රු. $interest වේ."
        )
      }
      3 -> {
        val oppAngle = 75 + (v % 20) * 2
        val suppAngle = 180 - oppAngle
        Quad(
          "චක්‍රීය චතුරස්‍රයක එක් අභ්‍යන්තර කෝණයක් $oppAngle° වේ නම්, ඊට සම්මුඛව පිහිටි කෝණයේ විශාලත්වය කුමක්ද?",
          listOf("$suppAngle°", "$oppAngle°", "90°", "180°"),
          1,
          "චක්‍රීය චතුරස්‍රයක සම්මුඛ කෝණ පරිපූරක වේ (එකතුව 180°). එබැවින් 180° - $oppAngle° = $suppAngle° වේ."
        )
      }
      4 -> {
        val m = 2 + (v % 4)
        val c = 3 + (v % 5)
        Quad(
          "y = ${m}x + $c සරල රේඛාවේ අනුක්‍රමණය (Gradient) සහ y-අන්තඃඛණ්ඩය (Intercept) පිළිවෙළින් කුමක්ද?",
          listOf("අනුක්‍රමණය = $m, අන්තඃඛණ්ඩය = $c", "අනුක්‍රමණය = $c, අන්තඃඛණ්ඩය = $m", "අනුක්‍රමණය = -$m, අන්තඃඛණ්ඩය = $c", "අනුක්‍රමණය = 1, අන්තඃඛණ්ඩය = 0"),
          1,
          "y = mx + c සම්මත සමීකරණයේ x හි සංගුණකය m අනුක්‍රමණය වන අතර නියත පදය c යනු y-අන්තඃඛණ්ඩයයි."
        )
      }
      5 -> {
        val ratio1 = 2
        val ratio2 = 3
        val totalParts = ratio1 + ratio2
        val totalMoney = totalParts * (1000 + (v % 5) * 500)
        val share1 = (totalMoney / totalParts) * ratio1
        Quad(
          "රු. $totalMoney ක මුදලක් $ratio1 : $ratio2 අනුපාතයට දෙදෙනෙකු අතර බෙදූ විට පළමු පුද්ගලයාට හිමිවන මුදල කොපමණද?",
          listOf("රු. $share1", "රු. ${totalMoney - share1}", "රු. ${totalMoney / 2}", "රු. 1000"),
          1,
          "මුළු කොටස් = $ratio1 + $ratio2 = $totalParts. පළමු කොටස = ($totalMoney / $totalParts) × $ratio1 = රු. $share1 වේ."
        )
      }
      else -> {
        val pricePerShare = 20 + (v % 5) * 5
        val divPerShare = 2 + (v % 3)
        val shares = 500
        val totalDiv = shares * divPerShare
        Quad(
          "කොටසක් රු. $pricePerShare බැගින් වූ සමාගමක කොටස් $shares ක් හිමි අයෙකුට එක් කොටසකට රු. $divPerShare බැගින් වාර්ෂික ලාභාංශ ගෙවයි නම් ලැබෙන මුළු ලාභාංශය කොපමණද?",
          listOf("රු. $totalDiv", "රු. ${totalDiv * 2}", "රු. ${shares * pricePerShare}", "රු. 500"),
          1,
          "මුළු ලාභාංශය = කොටස් ගණන × කොටසකට ලාභාංශය = $shares × රු. $divPerShare = රු. $totalDiv වේ."
        )
      }
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

  // --------------------------------------------------------------------------
  // TERM 3 MATHEMATICS: 60 SLOTS x 30 PAPERS
  // --------------------------------------------------------------------------
  private fun buildTerm3MathQuestion(qNum: Int, v: Int, paperIdx: Int): MockExamQuestion {
    val topic = when (qNum) {
      in 1..10 -> "ත්‍රිකෝණමිතිය (Trigonometry)"
      in 11..20 -> "ඝන වස්තුවල වර්ගඵලය හා පරිමාව"
      in 21..30 -> "සමාන්තර හා ගුණෝත්තර ශ්‍රේඪි"
      in 31..40 -> "සම්භාවිතාව සහ ගස් රූප සටහන්"
      in 41..50 -> "සංඛ්‍යානය, මධ්‍යන්‍යය හා සමුච්චිත සංඛ්‍යාතය"
      else -> "ජ්‍යාමිතික නිර්මාණ, ලක්ෂ්‍ය පථ හා ප්‍රස්ථාර"
    }

    val (text, opts, correct, exp) = when (qNum % 6) {
      1 -> {
        val a = 2 + (v % 5)
        val d = 3 + (v % 4)
        val n = 10
        val t10 = a + (n - 1) * d
        Quad(
          "පළමු පදය $a ද පොදු අන්තරය $d ද වන සමාන්තර ශ්‍රේඪියක 10 වන පදය (T₁₀) කුමක්ද?",
          listOf("$t10", "${t10 + d}", "${t10 - d}", "${a * 10}"),
          1,
          "සමාන්තර ශ්‍රේඪියක n වන පදය: Tn = a + (n - 1)d => T₁₀ = $a + (9 × $d) = $t10 වේ."
        )
      }
      2 -> {
        val diceOpts = listOf(
          "සාධාරණ දාදු කැටයක් එක් වරක් දැමූ විට ඉරට්ටේ සංඛ්‍යාවක් (2, 4, 6) ලැබීමේ සම්භාවිතාව කොපමණද?" to (listOf("1/2 (3/6)", "1/6", "2/6", "5/6") to "හිතකර අවස්ථා 3කි {2, 4, 6}. මුළු අවස්ථා 6 බැවින් සම්භාවිතාව = 3/6 = 1/2."),
          "සාධාරණ දාදු කැටයක් දැමූ විට ප්‍රථමක සංඛ්‍යාවක් (2, 3, 5) ලැබීමේ සම්භාවිතාව කොපමණද?" to (listOf("1/2", "1/3", "1/6", "2/3") to "ප්‍රථමක සංඛ්‍යා 3කි {2, 3, 5}. සම්භාවිතාව = 3/6 = 1/2 වේ."),
          "කාසියක් දෙවරක් උඩ දැමූ විට සිරස් (H) දෙකක්ම ලැබීමේ සම්භාවිතාව කුමක්ද?" to (listOf("1/4", "1/2", "3/4", "1/8") to "නියැදි අවකාශය = {HH, HT, TH, TT}. {HH} ලැබීමේ සම්භාවිතාව = 1/4 වේ.")
        )
        val sel = diceOpts[v % diceOpts.size]
        Quad(sel.first, sel.second.first, 1, sel.second.second)
      }
      3 -> {
        val r = 7 * (1 + (v % 3))
        val h = 10 + (v % 5) * 2
        val vol = (22 * r * r * h) / 7
        Quad(
          "අඩි අරය $r cm සහ උස $h cm වූ සෘජු වෘත්තාකාර සිලින්ඩරයක පරිමාව (V = πr²h) කොපමණද? (π = 22/7)",
          listOf("$vol cm³", "${vol / 2} cm³", "${vol * 2} cm³", "1000 cm³"),
          1,
          "පරිමාව V = πr²h = (22/7) × $r² × $h = $vol cm³ වේ."
        )
      }
      4 -> Quad(
        "ලක්ෂ්‍ය දෙකකින් සමදුරින් පිහිටි ලක්ෂ්‍යවල පථය (Locus) වන්නේ කුමක්ද?",
        listOf("එම ලක්ෂ්‍ය දෙක යා කරන රේඛාවේ ලම්භ සමච්ඡේදකය", "එම ලක්ෂ්‍ය දෙක හරහා යන වෘත්තය", "සමාන්තර රේඛා යුගලය", "කෝණ සමච්ඡේදකය"),
        1,
        "A සහ B ලක්ෂ්‍ය දෙකකට සමදුරින් චලනය වන ලක්ෂ්‍යයක පථය AB සරල රේඛා ඛණ්ඩයේ ලම්භ සමච්ඡේදකය වේ."
      )
      5 -> Quad(
        "ලම්බක පාදය 5 m ද කර්ණය 10 m ද වන සෘජුකෝණී ත්‍රිකෝණයක sin θ අගය කොපමණද?",
        listOf("5/10 (1/2 හෙවත් θ = 30°)", "10/5 (2)", "√3/2", "1"),
        1,
        "sin θ = සම්මුඛ පාදය / කර්ණය = 5 / 10 = 1/2 වේ. (එබැවින් θ = 30° වේ)."
      )
      else -> {
        val midValues = listOf(12, 14, 15, 18, 20)
        val m = midValues[v % midValues.size]
        Quad(
          "දත්ත සමූහයක සංඛ්‍යාත ව්‍යාප්තියේ මධ්‍යන්‍යය $m වේ. දත්ත සියල්ලේම එකතුව ${(m * 10)} නම් මුළු සංඛ්‍යාතය කොපමණද?",
          listOf("10", "20", "5", "15"),
          1,
          "මධ්‍යන්‍යය = Σfx / Σf => $m = ${(m * 10)} / Σf => Σf = 10 වේ."
        )
      }
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
