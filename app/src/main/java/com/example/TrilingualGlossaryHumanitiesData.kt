package com.example

// Complete, rich Trilingual Glossary Terms for History, Civics, Geography, Buddhism & English
// Fully aligned with the official O/L Sri Lankan Curriculum.
// Each term includes authentic Sinhala, English, and Tamil terms, detailed definitions, and exam tips.
val humanitiesAndOtherGlossaryTerms = listOf(
  // =========================================================================
  // 1. HISTORY (ඉතිහාසය)
  // =========================================================================
  GlossaryTermItem(
    id = "his_01",
    englishTerm = "Archaeology",
    sinhalaTerm = "පුරාවිද්‍යාව",
    tamilTerm = "தொல்பொருளியல்",
    subject = "ඉතිහාසය",
    definitionSinhala = "අතීත මානව ක්‍රියාකාරකම් හා සංස්කෘතීන් පිළිබඳව ශේෂව පවතින භෞතික සාධක කැණීම් කර විද්‍යාත්මකව හදාරන විෂයය.",
    examTip = "සෙල්ලිපි, කාසි, මැටි බඳුන් සහ නටබුන් පුරාවිද්‍යාත්මක මූලාශ්‍ර ගණයට අයත් වේ."
  ),
  GlossaryTermItem(
    id = "his_02",
    englishTerm = "Epigraphy / Inscriptions",
    sinhalaTerm = "ශිලාලේඛන / සෙල්ලිපි",
    tamilTerm = "கல்வெட்டுகள்",
    subject = "ඉතිහාසය",
    definitionSinhala = "ගල් පර්වත, ලෙන් කටාරම් හෝ ගල් ටැම් මත අතීත රජවරුන් හා ප්‍රභූන් විසින් ලියවන ලද නිල ලිඛිත සාක්ෂි.",
    examTip = "ප්‍රාථමික මූලාශ්‍ර වන අතර කිසිවෙකුගේ සංස්කරණයට ලක් නොවීම මෙහි විශේෂත්වයයි."
  ),
  GlossaryTermItem(
    id = "his_03",
    englishTerm = "Chronicle",
    sinhalaTerm = "වංශකථාව",
    tamilTerm = "வரலாற்று நூல்",
    subject = "ඉතිහාසය",
    definitionSinhala = "ඓතිහාසික සිදුවීම් කාලානුක්‍රමිකව ලේඛනගත කර ඇති ඓතිහාසික සාහිත්‍ය ග්‍රන්ථ (උදා: දීපවංශය, මහාවංශය).",
    examTip = "දීපවංශය පැරණිතම වංශකථාව වන අතර මහාවංශය මහානාම හිමියන් විසින් රචනා කරන ලදී."
  ),
  GlossaryTermItem(
    id = "his_04",
    englishTerm = "Hydraulic Civilization",
    sinhalaTerm = "වාරි ශිෂ්ටාචාරය",
    tamilTerm = "நீர்ப்பாசன நாகரிகம்",
    subject = "ඉතිහාසය",
    definitionSinhala = "වියළි කලාපයේ කෘෂිකර්මාන්තය සඳහා වැව්, ඇළ මාර්ග, බිසෝකොටු සහ සොරොව් ආශ්‍රිතව ගොඩනැගුණු විශිෂ්ට ජල තාක්ෂණික පද්ධතිය.",
    examTip = "බිසෝකොටුව මගින් වැව් බැම්මට හානි නොවී පීඩනය පාලනය කර ජලය පිට කරයි."
  ),
  GlossaryTermItem(
    id = "his_05",
    englishTerm = "Feudalism",
    sinhalaTerm = "වැඩවසම් ක්‍රමය",
    tamilTerm = "நிலப்பிரபுத்துவம்",
    subject = "ඉතිහාසය",
    definitionSinhala = "ඉඩම් හිමිකම පදනම් කරගෙන රජු, ප්‍රභූන් සහ ගොවි ජනතාව අතර පැවති රාජකාරි සහ සේවා සම්බන්ධතා ක්‍රමය.",
    examTip = "මධ්‍යකාලීන යුගයේ සහ මහනුවර යුගයේ ඉඩම් පරිහරණය හා රාජකාරි ක්‍රමය මීට නිදසුනකි."
  ),
  GlossaryTermItem(
    id = "his_06",
    englishTerm = "Colonization",
    sinhalaTerm = "යටත්විජිතවාදය",
    tamilTerm = "குடியேற்றவாதம்",
    subject = "ඉතිහාසය",
    definitionSinhala = "ප්‍රබල යුරෝපීය ජාතීන් විසින් ආසියානු හා අප්‍රිකානු රටවල් බලහත්කාරයෙන් යටත් කරගෙන තම ආර්ථික හා දේශපාලන වාසියට යොදාගැනීම.",
    examTip = "පෘතුගීසි (1505), ලන්දේසි (1658) සහ බ්‍රිතාන්‍ය (1796) ලංකාව යටත්විජිතයක් බවට පත් කළහ."
  ),
  GlossaryTermItem(
    id = "his_07",
    englishTerm = "National Revival",
    sinhalaTerm = "ජාතික පුනරුදය",
    tamilTerm = "தேசிய மறுமலர்ச்சி",
    subject = "ඉතිහාසය",
    definitionSinhala = "යටත්විජිත පාලනය යටතේ පිරිහුණු දේශීය ආගමික, සංස්කෘතික හා භාෂා අනන්‍යතාව නැවත නඟාසිටුවීමට 19 වන සියවසේ ඇතිවූ ප්‍රබෝධය.",
    examTip = "පානදුරාවාදය, අනගාරික ධර්මපාල, හික්කඩුවේ ශ්‍රී සුමංගල හිමි හා ඕල්කට්තුමා මීට පුරෝගාමී වූහ."
  ),
  GlossaryTermItem(
    id = "his_08",
    englishTerm = "Ceded Territory",
    sinhalaTerm = "පවරාදුන් ප්‍රදේශ (ගිවිසුම්ගත)",
    tamilTerm = "விட்டுக்கொடுக்கப்பட்ட பிரதேசம்",
    subject = "ඉතිහාසය",
    definitionSinhala = "1815 උඩරට ගිවිසුම මඟින් බ්‍රිතාන්‍ය කිරීටයට පවරා දුන් සිංහලේ රාජධානියට අයත් භූමි භාගය.",
    examTip = "1815 මාර්තු 2 දින අත්සන් කළ ගිවිසුමේ 5 වන වගන්තියෙන් බුද්ධාගම ආරක්ෂා කිරීමට ප්‍රතිඥා දෙන ලදී."
  ),
  GlossaryTermItem(
    id = "his_09",
    englishTerm = "Industrial Revolution",
    sinhalaTerm = "කාර්මික විප්ලවය",
    tamilTerm = "தொழிற்புரட்சி",
    subject = "ඉතිහාසය",
    definitionSinhala = "18 වන සියවසේ බ්‍රිතාන්‍යයේ ඇරඹි අත්කම් වෙනුවට වාෂ්ප බලයෙන් ක්‍රියාත්මක යන්ත්‍ර සූත්‍ර මඟින් මහා පරිමාණ කර්මාන්තශාලා නිෂ්පාදනය ඇරඹීමේ යුගය.",
    examTip = "ජේම්ස් වොට්ගේ වාෂ්ප එන්ජිම සහ රෙදිපිළි කර්මාන්තයේ දියුණුව මීට මූලික විය."
  ),
  GlossaryTermItem(
    id = "his_10",
    englishTerm = "Universal Suffrage",
    sinhalaTerm = "සර්වජන ඡන්ද බලය",
    tamilTerm = "சர்வஜன வாக்குரிமை",
    subject = "ඉතිහාසය",
    definitionSinhala = "ධනය, අධ්‍යාපනය හෝ ස්ත්‍රී පුරුෂ භේදයකින් තොරව නිශ්චිත වයස සම්පූර්ණ වූ සෑම පුරවැසියෙකුටම හිමිවන ඡන්ද අයිතිය.",
    examTip = "1931 ඩොනමෝර් ප්‍රතිසංස්කරණ මඟින් ආසියාවේ ප්‍රථම වරට ශ්‍රී ලංකාවට හිමිවිය."
  ),
  GlossaryTermItem(
    id = "his_11",
    englishTerm = "Kandyan Convention",
    sinhalaTerm = "උඩරට ගිවිසුම",
    tamilTerm = "கண்டிய உடன்படிக்கை",
    subject = "ඉතිහාසය",
    definitionSinhala = "1815 මාර්තු 2 දින බ්‍රිතාන්‍ය ආණ්ඩුකාර රොබට් බ්‍රවුන්රිග් සහ උඩරට රදළ ප්‍රධානීන් අතර අත්සන් තැබූ ඓතිහාසික ගිවිසුම.",
    examTip = "ශ්‍රී වික්‍රම රාජසිංහ රජු බලයෙන් පහකර උඩරට පාලනය බ්‍රිතාන්‍ය රජුට පැවරිණි."
  ),
  GlossaryTermItem(
    id = "his_12",
    englishTerm = "Renaissance",
    sinhalaTerm = "පුනරුදය (යුරෝපීය)",
    tamilTerm = "மறுமலர்ச்சி காலம்",
    subject = "ඉතිහාසය",
    definitionSinhala = "14-16 සියවස්වල ඉතාලිය කේන්ද්‍ර කරගනිමින් කලා, සාහිත්‍ය, විද්‍යා හා මානවවාදී චින්තනයේ ඇතිවූ නව පිබිදීම.",
    examTip = "ලියනාඩෝ ඩා වින්චි, මයිකල් ආන්ජලෝ සහ ගැලීලියෝ ගැලීලි ප්‍රමුඛ චරිත වේ."
  ),

  // =========================================================================
  // 2. CIVICS (පුරවැසි අධ්‍යාපනය)
  // =========================================================================
  GlossaryTermItem(
    id = "civ_01",
    englishTerm = "Democracy",
    sinhalaTerm = "ප්‍රජාතන්ත්‍රවාදය",
    tamilTerm = "மக்களாட்சி",
    subject = "පුරවැසි අධ්‍යාපනය",
    definitionSinhala = "ජනතාවගේ පරමාධිපත්‍යය මත පදනම්ව, නිදහස් හා සාධාරණ මැතිවරණ මඟින් තෝරාපත් කරගන්නා මහජන නියෝජිතයන් මඟින් පාලනය ගෙන යන ක්‍රමය.",
    examTip = "ඒබ්‍රහම් ලින්කන්: 'ජනතාවගේ, ජනතාව විසින්, ජනතාව උදෙසා පවත්වන ආණ්ඩුවයි'."
  ),
  GlossaryTermItem(
    id = "civ_02",
    englishTerm = "Constitution",
    sinhalaTerm = "ආණ්ඩුක්‍රම ව්‍යවස්ථාව",
    tamilTerm = "அரசியலமைப்பு",
    subject = "පුරවැසි අධ්‍යාපනය",
    definitionSinhala = "රටක පාලන ව්‍යුහය, රාජ්‍ය බලතල බෙදී යාම සහ පුරවැසි මූලික අයිතිවාසිකම් තහවුරු කරන උත්තරීතර මූලික නීති සංග්‍රහය.",
    examTip = "ලංකාවේ වත්මන් ව්‍යවස්ථාව 1978 දෙවන ජනරජ ආණ්ඩුක්‍රම ව්‍යවස්ථාවයි."
  ),
  GlossaryTermItem(
    id = "civ_03",
    englishTerm = "Rule of Law",
    sinhalaTerm = "නීතියේ ආධිපත්‍යය",
    tamilTerm = "சட்டத்தின் ஆட்சி",
    subject = "පුරවැසි අධ්‍යාපනය",
    definitionSinhala = "නීතිය ඉදිරියේ සියලු පුරවැසියන් සමාන වන අතර කිසිදු පුද්ගලයෙකු හෝ ආයතනයක් නීතියට ඉහළින් නොසිටීමේ ප්‍රජාතන්ත්‍රවාදී මූලධර්මය.",
    examTip = "ඒ.වී. ඩයිසිගේ සංකල්පයක් වන අතර අධිකරණයේ ස්වාධීනත්වය මීට අත්‍යවශ්‍ය සාධකයකි."
  ),
  GlossaryTermItem(
    id = "civ_04",
    englishTerm = "Fundamental Rights",
    sinhalaTerm = "මූලික මිනිස් අයිතිවාසිකම්",
    tamilTerm = "அடிப்படை உரிமைகள்",
    subject = "පුරවැසි අධ්‍යාපනය",
    definitionSinhala = "මිනිසෙකු ලෙස උපත ලැබීම නිසාම හිමිවන, ආණ්ඩුක්‍රම ව්‍යවස්ථාව මඟින් නීත්‍යානුකූලව ආරක්ෂා කර ඇති ජීවත්වීමේ, සමානාත්මතාවේ හා නිදහසේ අයිතීන්.",
    examTip = "උල්ලංඝනය වූ විට දින 30ක් ඇතුළත ශ්‍රේෂ්ඨාධිකරණයට මූලික අයිතිවාසිකම් පෙත්සමක් ඉදිරිපත් කළ හැක."
  ),
  GlossaryTermItem(
    id = "civ_05",
    englishTerm = "Good Governance",
    sinhalaTerm = "යහපාලනය",
    tamilTerm = "நல்லாட்சி",
    subject = "පුරවැසි අධ්‍යාපනය",
    definitionSinhala = "විනිවිදභාවය, වගවීම, මහජන සහභාගිත්වය, නීතියේ ආධිපත්‍යය සහ කාර්යක්ෂමතාව මත පදනම්ව රාජ්‍ය සම්පත් මහජන යහපතට කළමනාකරණය කිරීම.",
    examTip = "දූෂණය පිටුදැකීමට සහ තොරතුරු දැනගැනීමේ අයිතිය (RTI) තහවුරු කිරීමට යහපාලනය ඉවහල් වේ."
  ),
  GlossaryTermItem(
    id = "civ_06",
    englishTerm = "Sovereignty",
    sinhalaTerm = "පරමාධිපත්‍යය",
    tamilTerm = "இறைமை",
    subject = "පුරවැසි අධ්‍යාපනය",
    definitionSinhala = "රාජ්‍යයක පවතින උත්තරීතරම හා පරම බලය; ශ්‍රී ලංකා ව්‍යවස්ථාවට අනුව පරමාධිපත්‍ය බලය ජනතාව සතු වන අතර එය අන්සතු කළ නොහැක.",
    examTip = "පාලන බලතල, මූලික අයිතිවාසිකම් සහ ඡන්ද බලය ජනතා පරමාධිපත්‍යයේ අංග වේ."
  ),
  GlossaryTermItem(
    id = "civ_07",
    englishTerm = "Local Government",
    sinhalaTerm = "පළාත් පාලන ආයතන",
    tamilTerm = "உள்ளூராட்சி மன்றங்கள்",
    subject = "පුරවැසි අධ්‍යාපනය",
    definitionSinhala = "ප්‍රදේශයේ ජනතාවගේ සනීපාරක්ෂාව, පොදු මංමාවත් හා මහජන පහසුකම් සැලසීමට බලයලත් මහා නගර සභා, නගර සභා සහ ප්‍රාදේශීය සභා.",
    examTip = "මහජනතාවට ආසන්නතම බිම් මට්ටමේ ප්‍රජාතන්ත්‍රවාදී පරිපාලන ස්ථරයයි."
  ),
  GlossaryTermItem(
    id = "civ_08",
    englishTerm = "United Nations Organization (UN)",
    sinhalaTerm = "එක්සත් ජාතීන්ගේ සංවිධානය",
    tamilTerm = "ஐக்கிய நாடுகள் சபை",
    subject = "පුරවැසි අධ්‍යාපනය",
    definitionSinhala = "1945 ඔක්තෝබර් 24 දින ලෝක සාමය, ආරක්ෂාව සහ ජාත්‍යන්තර සහයෝගීතාව තහවුරු කිරීමට පිහිටුවා ගත් ප්‍රධාන ගෝලීය සංවිධානය.",
    examTip = "ප්‍රධාන අංශ: මහා මණ්ඩලය, ආරක්ෂක මණ්ඩලය, ආර්ථික හා සමාජීය මණ්ඩලය, ජාත්‍යන්තර අධිකරණය."
  ),

  // =========================================================================
  // 3. GEOGRAPHY (භූගෝල විද්‍යාව)
  // =========================================================================
  GlossaryTermItem(
    id = "geo_01",
    englishTerm = "Topography",
    sinhalaTerm = "භූ විෂමතාව",
    tamilTerm = "நிலத்தோற்றம்",
    subject = "භූගෝල විද්‍යාව",
    definitionSinhala = "පෘථිවි පෘෂ්ඨයේ ඇති කඳු, සානුව, තැනිතලා, මිටියාවත් ආදී උස් පහත් ලක්ෂණවල ස්වභාවය සහ ව්‍යාප්තිය.",
    examTip = "ශ්‍රී ලංකාවේ භූ විෂමතාව ප්‍රධාන තලා 3කි: වෙරළබඩ තැනිතලාව, අභ්‍යන්තර සානුව සහ මධ්‍යම කඳුකරය."
  ),
  GlossaryTermItem(
    id = "geo_02",
    englishTerm = "Monsoon Winds",
    sinhalaTerm = "මෝසම් සුළං",
    tamilTerm = "பருவக்காற்று",
    subject = "භූගෝල විද්‍යාව",
    definitionSinhala = "සෘතුමය වශයෙන් දිශාව මුළුමනින්ම වෙනස් කරමින් හමන ප්‍රධාන කාලගුණික සුළං පද්ධති (නිරිතදිග හා ඊසානදිග මෝසම්).",
    examTip = "නිරිතදිග මෝසම (මැයි-සැප්) තෙත් කලාපයට ද, ඊසානදිග මෝසම (දෙසැ-පෙබ) වියළි කලාපයට ද වැසි ගෙනෙයි."
  ),
  GlossaryTermItem(
    id = "geo_03",
    englishTerm = "Contour Lines",
    sinhalaTerm = "සමෝච්ච රේඛා",
    tamilTerm = "சமவுயரக் கோடுகள்",
    subject = "භූගෝල විද්‍යාව",
    definitionSinhala = "භූ විෂමතා සිතියමක මුහුදු මට්ටමේ සිට සමාන උසකින් පිහිටි ස්ථාන යා කරමින් අඳිනු ලබන මනඃකල්පිත රේඛා.",
    examTip = "රේඛා ළඟින් පිහිටීමෙන් දැඩි බෑවුම් ද, ඈතින් පිහිටීමෙන් මෘදු බෑවුම් ද නිරූපණය වේ."
  ),
  GlossaryTermItem(
    id = "geo_04",
    englishTerm = "Sustainable Development",
    sinhalaTerm = "තිරසාර සංවර්ධනය",
    tamilTerm = "நிலையான அபிவிருத்தி",
    subject = "භූගෝල විද්‍යාව",
    definitionSinhala = "අනාගත පරපුරට සම්පත් හිඟයක් නොවන අයුරින් වර්තමාන මානව අවශ්‍යතා සපුරා ගනිමින් පරිසරය ආරක්ෂා කරන සංවර්ධන පිළිවෙත.",
    examTip = "එක්සත් ජාතීන් ඉදිරිපත් කළ SDGs අරමුණු 17ක් මීට ඇතුළත් වේ."
  ),
  GlossaryTermItem(
    id = "geo_05",
    englishTerm = "Ecosystem",
    sinhalaTerm = "පරිසර පද්ධතිය",
    tamilTerm = "சூழற்தொகுதி",
    subject = "භූගෝල විද්‍යාව",
    definitionSinhala = "කිසියම් භූගෝලීය ප්‍රදේශයක ජීවත්වන ජීවී ප්‍රජාව සහ ඔවුන්ගේ අජීවී භෞතික පරිසරය අතර පවතින අන්තර් සබඳතා ජාලය.",
    examTip = "සිංහරාජ වැසි වනාන්තරය ලෝක උරුම නිවර්තන තෙත් සදාහරිත පරිසර පද්ධතියකි."
  ),
  GlossaryTermItem(
    id = "geo_06",
    englishTerm = "Urbanization",
    sinhalaTerm = "නාගරීකරණය",
    tamilTerm = "நகரமயமாக்கல்",
    subject = "භූගෝල විද්‍යාව",
    definitionSinhala = "ග්‍රාමීය ප්‍රදේශවලින් නගර කරා ජනතාව සංක්‍රමණය වීම සහ නගරවල ජනගහනය හා යටිතල පහසුකම් ව්‍යාප්ත වීමේ ක්‍රියාවලිය.",
    examTip = "නාගරික තදබදය, අපද්‍රව්‍ය කළමනාකරණය හා නිවාස ගැටලුව මෙහි අභියෝග වේ."
  ),
  GlossaryTermItem(
    id = "geo_07",
    englishTerm = "Deforestation",
    sinhalaTerm = "වන විනාශය",
    tamilTerm = "காடழிப்பு",
    subject = "භූගෝල විද්‍යාව",
    definitionSinhala = "මිනිස් ක්‍රියාකාරකම් (කෘෂිකර්මය, දැව, ජනාවාස) හේතුවෙන් ස්වාභාවික වනාන්තර ශීඝ්‍රයෙන් විනාශ වී භූමිය එළිපෙහෙළි වීම.",
    examTip = "පාංශු ඛාදනය, නායයෑම්, ගංවතුර සහ ජෛව විවිධත්වය අහිමි වීමට ප්‍රධාන හේතුවකි."
  ),
  GlossaryTermItem(
    id = "geo_08",
    englishTerm = "Scale (Cartography)",
    sinhalaTerm = "සිතියම් පරිමාණය",
    tamilTerm = "வரைபட அளவுத்திட்டம்",
    subject = "භූගෝල විද්‍යාව",
    definitionSinhala = "සිතියමක ලක්ෂ්‍ය දෙකක් අතර දුර සහ පොළොවේ එම ලක්ෂ්‍ය දෙක අතර සත්‍ය භූමි දුර අතර පවතින අනුපාතය.",
    examTip = "නිරූපක භාගය (R.F.): 1:50,000 මඟින් සිතියමේ 1cm පොළොවේ 500m (0.5km) නිරූපණය කරයි."
  ),

  // =========================================================================
  // 4. BUDDHISM (බුද්ධ ධර්මය)
  // =========================================================================
  GlossaryTermItem(
    id = "bud_01",
    englishTerm = "Four Noble Truths",
    sinhalaTerm = "චතුරාර්ය සත්‍යය",
    tamilTerm = "நான்கு உன்னத உண்மைகள்",
    subject = "බුද්ධ ධර්මය",
    definitionSinhala = "බුදුරජාණන් වහන්සේ අවබෝධ කරගත් උතුම් සත්‍ය හතර: දුක්ඛ, සමුදය, නිරෝධ, මාර්ග.",
    examTip = "ධම්මචක්කප්පවත්තන සූත්‍රයේදී පස්වග මහණුන්ට ප්‍රථම වරට දේශනා කරන ලදී."
  ),
  GlossaryTermItem(
    id = "bud_02",
    englishTerm = "Noble Eightfold Path",
    sinhalaTerm = "ආර්ය අෂ්ටාංගික මාර්ගය",
    tamilTerm = "உன்னத எண்வழிப்பாதை",
    subject = "බුද්ධ ධර්මය",
    definitionSinhala = "නිවන් දැකීම සඳහා මධ්‍යම ප්‍රතිපදාව වන අංග අට: සම්මා දිට්ඨි, සංකප්ප, වාචා, කම්මන්ත, ආජීව, වායාම, සති, සමාධි.",
    examTip = "ශීල, සමාධි, ප්‍රඥා යන ත්‍රිශික්ෂාවට අනුකූලව බෙදී පවතී."
  ),
  GlossaryTermItem(
    id = "bud_03",
    englishTerm = "Dependent Origination",
    sinhalaTerm = "පටිච්චසමුප්පාදය",
    tamilTerm = "சார்புத் தோற்றம்",
    subject = "බුද්ධ ධර්මය",
    definitionSinhala = "හේතු ඵල දහම; කිසියම් ඵලයක් හටගන්නේ ඊට අදාළ හේතු ප්‍රත්‍යයන්ගේ එකතුවෙන් බව පැහැදිලි කරන බෞද්ධ දර්ශනය.",
    examTip = "'ඉමස්මිං සති ඉදං හෝති' - මෙය ඇති කල්හි මෙය වේ යන මූලධර්මයයි."
  ),
  GlossaryTermItem(
    id = "bud_04",
    englishTerm = "Tipitaka",
    sinhalaTerm = "ත්‍රිපිටකය",
    tamilTerm = "திரிபிடகம்",
    subject = "බුද්ධ ධර්මය",
    definitionSinhala = "බුද්ධ දේශනාව සංග්‍රහ කර ඇති ප්‍රධාන කොටස් තුන: විනය පිටකය, සූත්‍ර පිටකය සහ අභිධර්ම පිටකය.",
    examTip = "වළගම්බා රජ සමයේ මාතලේ අලුවිහාරයේදී ප්‍රථම වරට තල්පත්වල ග්‍රන්ථාරූඪ කරන ලදී."
  ),
  GlossaryTermItem(
    id = "bud_05",
    englishTerm = "Karma (Kamma)",
    sinhalaTerm = "කර්මය",
    tamilTerm = "கன்மம் (கர்மா)",
    subject = "බුද්ධ ධර්මය",
    definitionSinhala = "චේතනාව මුල් කරගෙන කයින්, වචනයෙන් හෝ සිතින් සිදුකරනු ලබන කුසල් හා අකුසල් ක්‍රියා.",
    examTip = "'චේතනාහං භික්ඛවේ කම්මං වදාමි' - චේතනාව කර්මය ලෙස බුදුරදුන් වදාළ සේක."
  ),
  GlossaryTermItem(
    id = "bud_06",
    englishTerm = "Three Characteristics of Existence (Tilakkhana)",
    sinhalaTerm = "ත්‍රිලක්ෂණය",
    tamilTerm = "முப்பண்புகள்",
    subject = "බුද්ධ ධර්මය",
    definitionSinhala = "සියලු සංස්කාර ධර්මයන්ගේ පොදු යථාර්ථය වන අනිත්‍ය (නොපැවැත්ම), දුක්ඛ (පීඩා සහගත බව) සහ අනත්ත (ආත්මයක් නොමැතිකම).",
    examTip = "අනත්තලක්ඛණ සූත්‍රයෙන් පස්වග තවුසන් මේ ධර්මය අසා අරහත්වයට පත්වූහ."
  ),
  GlossaryTermItem(
    id = "bud_07",
    englishTerm = "Nirvana (Nibbana)",
    sinhalaTerm = "නිර්වාණය",
    tamilTerm = "நிர்வாணம்",
    subject = "බුද්ධ ධර්මය",
    definitionSinhala = "තණ්හාව, ද්වේෂය හා මෝහය මුළුමනින්ම ක්ෂය කිරීමෙන් භව ගමන හා සියලු දුක් නිමා කර ලැබෙන පරම ශාන්ත සුවය.",
    examTip = "බෞද්ධ පිළිවෙතේ පරම නිෂ්ඨාව හෙවත් අවසාන ඉලක්කයයි."
  ),
  GlossaryTermItem(
    id = "bud_08",
    englishTerm = "Dharma Sangayana (Council)",
    sinhalaTerm = "ධර්ම සංගායනාව",
    tamilTerm = "தர்ம சங்காயனம்",
    subject = "බුද්ධ ධර්මය",
    definitionSinhala = "බුද්ධ ශාසනයේ චිරස්ථිතිය උදෙසා භික්ෂු සංඝයා එක්රැස්ව ධර්මය හා විනය සංශෝධනය කර සජ්ඣායනා කිරීමේ සම්මේලන.",
    examTip = "ප්‍රථම සංගායනාව අජාසත් රජුගේ අනුග්‍රහයෙන් මහා කාශ්‍යප හිමියන්ගේ ප්‍රධානත්වයෙන් රජගහනුවරදී පැවැත්විණි."
  ),

  // =========================================================================
  // 5. ENGLISH LANGUAGE (ඉංග්‍රීසි)
  // =========================================================================
  GlossaryTermItem(
    id = "eng_01",
    englishTerm = "Passive Voice",
    sinhalaTerm = "කර්මකාරක වාක්‍ය",
    tamilTerm = "செயப்பாட்டு வினை",
    subject = "English",
    definitionSinhala = "ක්‍රියාව කරන පුද්ගලයාට (Subject) වඩා ක්‍රියාවට ලක්වන දෙයට හෝ පුද්ගලයාට (Object) මුල්තැන දෙන වාක්‍ය රටාව (Be + Past Participle).",
    examTip = "Formal reports, science experiments සහ news reports ලිවීමේදී බහුලව යොදා ගැනේ."
  ),
  GlossaryTermItem(
    id = "eng_02",
    englishTerm = "Conditional Clause",
    sinhalaTerm = "කොන්දේසි වාක්‍යඛණ්ඩය (If-clause)",
    tamilTerm = "நிபந்தனை வாக்கியம்",
    subject = "English",
    definitionSinhala = "කිසියම් කොන්දේසියක් සපුරාලුවහොත් සිදුවිය හැකි ප්‍රතිඵල දක්වන වාක්‍ය (Zero, First, Second, Third Conditionals).",
    examTip = "Type 1: If + present, will + verb. Type 2: If + past, would + verb. Type 3: If + had + pp, would have + pp."
  ),
  GlossaryTermItem(
    id = "eng_03",
    englishTerm = "Relative Pronoun",
    sinhalaTerm = "සම්බන්ධක සර්වනාම",
    tamilTerm = "தொடர்பு சுட்டுப்பெயர்",
    subject = "English",
    definitionSinhala = "නාම පදයක් පිළිබඳ අමතර විස්තර සපයන වාක්‍යාංශ යා කිරීමට යොදන පද (Who, Whom, Which, That, Whose).",
    examTip = "පුද්ගලයන්ට Who/Whom ද, අජීවී දේවලට Which/That ද, අයිතියට Whose ද යෙදේ."
  ),
  GlossaryTermItem(
    id = "eng_04",
    englishTerm = "Direct & Reported Speech",
    sinhalaTerm = "ප්‍රත්‍යක්ෂ හා පරෝක්ෂ කථනය",
    tamilTerm = "நேர் கூற்று மற்றும் அயற் கூற்று",
    subject = "English",
    definitionSinhala = "යමෙකු පැවසූ දෙය ඒ අයුරින්ම උද්ධෘත පාඨ තුළ දැක්වීම (Direct) සහ වෙනත් අයෙකුට විස්තර කර කීම (Reported).",
    examTip = "Reported Speech වලදී tenses (Present → Past), pronouns සහ time expressions (now → then) වෙනස් වේ."
  ),
  GlossaryTermItem(
    id = "eng_05",
    englishTerm = "Conjunctions",
    sinhalaTerm = "සමුච්චය / සම්බන්ධක පද",
    tamilTerm = "இணைப்புச் சொற்கள்",
    subject = "English",
    definitionSinhala = "වචන, වාක්‍යාංශ හෝ උපවාක්‍ය එකිනෙක සම්බන්ධ කිරීමට යොදන පද (Although, Because, However, Furthermore, Therefore).",
    examTip = "Formal essays සහ argumentative compositions වලදී Coherence (අනුකූලතාව) සඳහා අත්‍යවශ්‍ය වේ."
  ),
  GlossaryTermItem(
    id = "eng_06",
    englishTerm = "Subject-Verb Agreement",
    sinhalaTerm = "උක්ත-ආඛ්‍යාත පෑහීම (Concord)",
    tamilTerm = "எழுவாய்-பயனிலை இசைவு",
    subject = "English",
    definitionSinhala = "වාක්‍යයක උක්තය ඒකවචන නම් ක්‍රියාපදය ඒකවචන ද, උක්තය බහුවචන නම් ක්‍රියාපදය බහුවචන ද විය යුතුය යන ව්‍යාකරණ නීතිය.",
    examTip = "Neither/Either, Everyone, Each සමඟ සැමවිටම ඒකවචන ක්‍රියාපද (Singular verb) යෙදේ."
  ),
  GlossaryTermItem(
    id = "eng_07",
    englishTerm = "Modal Auxiliaries",
    sinhalaTerm = "ආඛ්‍යාත සහායක ක්‍රියා (Modals)",
    tamilTerm = "துணை வினைகள்",
    subject = "English",
    definitionSinhala = "හැකියාව (Can), අවසරය (May), බැඳීම (Must/Should), සම්භාවිතාව (Could/Might) ප්‍රකාශ කරන විශේෂ උපකාරක ක්‍රියාපද.",
    examTip = "Modal පදයකට පසු සැමවිටම Base form (Infinitive without 'to') ක්‍රියාපදය යෙදිය යුතුය."
  ),
  GlossaryTermItem(
    id = "eng_08",
    englishTerm = "Prefix and Suffix (Affixes)",
    sinhalaTerm = "උපසර්ග හා ප්‍රත්‍ය (වචන නිර්මාණය)",
    tamilTerm = "முன்னொட்டு மற்றும் பின்னொட்டு",
    subject = "English",
    definitionSinhala = "මූල පදයකට ඉදිරියෙන් (Prefix: un-, dis-, re-) හෝ පසුපසින් (Suffix: -ful, -tion, -ly) එක්කර නව අරුත් දෙන පද සැකසීම.",
    examTip = "O/L Vocabulary ප්‍රශ්න පත්‍රයේ Word Classes (Noun, Verb, Adjective, Adverb) හඳුනාගැනීමට ඉවහල් වේ."
  )
)
