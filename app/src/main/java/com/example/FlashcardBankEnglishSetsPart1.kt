package com.example

/**
 * Curated, 100% syllabus-aligned, distinct Flashcards for English (G.C.E. O/L).
 * Part 1: Categories 1 to 10 (20 distinct questions per category).
 * Strictly guarantees no repeated questions within the same category or across different categories.
 */
object FlashcardBankEnglishSetsPart1 {

  fun getCard(setNum: Int, cardNum: Int): CardContentTuple? {
    val c = cardNum.coerceIn(1, 20)
    return when (setNum) {
      1 -> set1[(c - 1) % set1.size]
      2 -> set2[(c - 1) % set2.size]
      3 -> set3[(c - 1) % set3.size]
      4 -> set4[(c - 1) % set4.size]
      5 -> set5[(c - 1) % set5.size]
      6 -> set6[(c - 1) % set6.size]
      7 -> set7[(c - 1) % set7.size]
      8 -> set8[(c - 1) % set8.size]
      9 -> set9[(c - 1) % set9.size]
      10 -> set10[(c - 1) % set10.size]
      else -> FlashcardBankEnglishSetsPart2.getCard(setNum, cardNum)
    }
  }

  // --- SET 1: Present Tenses (Simple Present & Present Continuous) ---
  private val set1 = listOf(
    CardContentTuple("When do we use the Simple Present Tense?", "To express habitual actions, general truths, permanent situations, and timetables.", "Use base form or base form + s/es for third person singular (he/she/it).", "Habits & facts: He plays / They play"),
    CardContentTuple("What is the rule for adding '-s' or '-es' in third person singular?", "Add '-es' to verbs ending in -ch, -sh, -s, -x, -z, and -o (e.g., watches, washes, goes). Others take '-s'.", "Consonant + y changes to -ies (study -> studies).", "Verb + s/es rule"),
    CardContentTuple("Form a negative sentence in Simple Present for 'He writes essays.'", "He does not (doesn't) write essays.", "Notice 'write' returns to base form after 'does not'.", "Subject + do/does not + base verb"),
    CardContentTuple("Form an interrogative (question) sentence: 'They live in Kandy.'", "Do they live in Kandy?", "Use 'Do' for I/we/you/they and 'Does' for he/she/it.", "Do/Does + subject + base verb?"),
    CardContentTuple("When do we use the Present Continuous Tense?", "To describe actions happening right now at the moment of speaking, or temporary situations.", "Form: am/is/are + verb-ing.", "Happening now: is writing / are studying"),
    CardContentTuple("What are stative verbs and why are they rarely used in continuous tenses?", "Verbs of thinking, feeling, and senses (e.g., know, believe, like, understand, hear, belong).", "Say 'I understand this', NOT 'I am understanding this'.", "Stative verbs = No continuous form"),
    CardContentTuple("Convert to Present Continuous: 'She reads a book now.'", "She is reading a book now.", "Signal words: now, at the moment, look!, listen!.", "is reading"),
    CardContentTuple("What is the spelling rule when adding '-ing' to verbs ending in consonant-vowel-consonant (CVC)?", "Double the final consonant if stressed (e.g., run -> running, sit -> sitting, swim -> swimming).", "Do not double if verb ends in w, x, y (e.g., snow -> snowing).", "CVC doubling rule"),
    CardContentTuple("Explain the difference: 'I live in Colombo' vs 'I am living in Colombo.'", "'I live in Colombo' implies a permanent home. 'I am living in Colombo' implies a temporary stay.", "Simple Present = permanent; Present Continuous = temporary.", "Permanent vs Temporary"),
    CardContentTuple("What signal words indicate the Simple Present Tense?", "Always, usually, often, sometimes, rarely, never, every day/week, twice a month.", "These adverbs of frequency go before the main verb.", "Frequency adverbs"),
    CardContentTuple("Correct the error: 'The sun is rising in the east every morning.'", "The sun rises in the east every morning (universal truth uses Simple Present).", "Never use continuous tense for universal scientific laws.", "Universal truth -> Simple Present"),
    CardContentTuple("Correct the error: 'He don't know the answer.'", "He doesn't know the answer.", "Third person singular 'he' requires 'doesn't', not 'don't'.", "Subject-verb agreement"),
    CardContentTuple("Form a question tag: 'You speak English, ________?'", "don't you?", "Positive statement takes a negative tag in Simple Present.", "Statement (+) -> Tag (-)"),
    CardContentTuple("Form a question tag: 'Nimal doesn't eat meat, ________?'", "does he?", "Negative statement takes a positive tag.", "Statement (-) -> Tag (+)"),
    CardContentTuple("What auxiliary verb is used for Present Continuous questions?", "Am / Is / Are (e.g., 'Are you listening to the teacher?')", "Invert auxiliary and subject.", "Am/Is/Are + Subject + V-ing?"),
    CardContentTuple("Give the Present Continuous of 'lie' (recline) and 'die'.", "lying and dying (verbs ending in -ie change -ie to -y before -ing).", "lie -> lying; tie -> tying; die -> dying.", "-ie changes to -ying"),
    CardContentTuple("How is Present Continuous used to express future arrangements?", "With a future time marker when an action is already planned: 'We are leaving tomorrow morning.'", "Pre-arranged definite plans.", "Present Continuous for future"),
    CardContentTuple("Fill in the blank: 'Listen! Somebody ________ (knock) at the door.'", "is knocking", "'Listen!' is a trigger signaling an action in progress right now.", "Present Continuous context"),
    CardContentTuple("Fill in the blank: 'Water ________ (boil) at 100 degrees Celsius.'", "boils", "Scientific fact and law of nature requires Simple Present.", "Scientific fact"),
    CardContentTuple("What is the negative form of 'I am playing'?", "I am not playing.", "Short form: I'm not playing.", "Subject + am/is/are + not + V-ing")
  )

  // --- SET 2: Past Tenses (Simple Past & Past Continuous) ---
  private val set2 = listOf(
    CardContentTuple("When do we use the Simple Past Tense?", "To express actions completed at a specific definite time in the past.", "Form: Regular verbs add -ed; irregular verbs have unique past forms.", "Completed past actions"),
    CardContentTuple("Give the past form of: go, buy, write, bring, choose.", "went, bought, wrote, brought, chose.", "Always memorize irregular verb tables for O/L paper.", "Irregular verbs list"),
    CardContentTuple("How do you form a negative sentence in Simple Past?", "Subject + did not (didn't) + base verb (e.g., 'She didn't come to school yesterday.').", "Never write 'didn't came' - use base form.", "did not + base verb"),
    CardContentTuple("How do you ask a question in Simple Past?", "Did + subject + base verb? (e.g., 'Did you see the news?')", "Use 'Did' for all subjects regardless of person.", "Did + subject + base verb?"),
    CardContentTuple("When do we use the Past Continuous Tense?", "To describe an action that was in progress at a specific time in the past.", "Form: was/were + verb-ing.", "Action in progress in the past"),
    CardContentTuple("Which pronouns take 'was' and which take 'were'?", "I, he, she, it take 'was'; we, you, they take 'were'.", "Singular -> was; Plural/You -> were.", "was vs were"),
    CardContentTuple("Explain the combination: Past Continuous + Simple Past with 'while' and 'when'.", "A long ongoing action (Past Continuous) was interrupted by a short action (Simple Past).", "Example: 'While I was studying, the telephone rang.'", "Interrupted past actions"),
    CardContentTuple("Complete: 'When mother arrived home, the children ________ (sleep).'", "were sleeping", "Ongoing action when the mother arrived.", "Past Continuous ongoing"),
    CardContentTuple("What are common time signal words for Simple Past?", "Yesterday, last night/week/year, two days ago, in 2010, once upon a time.", "Points clearly to a finished past time.", "Past time markers"),
    CardContentTuple("Form a question tag: 'They finished their homework, ________?'", "didn't they?", "Positive past statement takes 'didn't + pronoun'.", "Past tag: didn't they?"),
    CardContentTuple("Give the past tense of: catch, teach, think, seek.", "caught, taught, thought, sought.", "Note the -ought / -aught spelling pattern.", "Irregular verb patterns"),
    CardContentTuple("What is the past tense of 'read' and how is it pronounced?", "Spelled 'read', but pronounced /red/ like the color red.", "Present /ri:d/, Past /red/.", "read /red/"),
    CardContentTuple("Convert to negative: 'Kamal wrote a letter.'", "Kamal did not write a letter.", "Change 'wrote' back to base form 'write'.", "did not write"),
    CardContentTuple("Correct the sentence: 'She didn't went to the market.'", "She didn't go to the market.", "After 'did/didn't', always use the base form 'go'.", "didn't + base verb"),
    CardContentTuple("Combine using 'While': 'Sunil was riding his bicycle. He fell down.'", "While Sunil was riding his bicycle, he fell down.", "'While' introduces the continuous background action.", "While + Past Continuous"),
    CardContentTuple("Give the past form of: fly, blow, grow, know.", "flew, blew, grew, knew.", "All follow the -ew past ending pattern.", "Irregular vowel shift"),
    CardContentTuple("Explain 'used to' + base verb in past habits.", "Refers to a repeated habit or state in the past that is no longer true today.", "Example: 'I used to live in Galle when I was a child.'", "used to + base verb"),
    CardContentTuple("Fill in the blank: 'At 8.00 p.m. last night, I ________ (watch) television.'", "was watching", "Specific past moment in progress takes Past Continuous.", "was watching"),
    CardContentTuple("Give the past form of: cut, put, shut, cost, hit.", "cut, put, shut, cost, hit (they remain unchanged in base, past, and past participle).", "Invariable verbs.", "Unchanged past verbs"),
    CardContentTuple("What is the difference: 'When the bell rang, we entered' vs 'When the bell rang, we were eating'?", "In the first, actions happened sequentially. In the second, eating was already ongoing.", "Sequential vs ongoing actions.", "Past sequence vs progress")
  )

  // --- SET 3: Future Forms & Modal Auxiliaries ---
  private val set3 = listOf(
    CardContentTuple("What are the 3 main ways to express the future in English?", "1. 'will' + base verb (spontaneous decision/prediction)\n2. 'be going to' + base verb (prior intention/evidence)\n3. Present Continuous (fixed schedule/arrangement).", "Choose based on speaker's intention and evidence.", "Future expressions"),
    CardContentTuple("When do we use 'be going to' instead of 'will'?", "For future plans decided before speaking, or predictions based on present physical evidence.", "Example: 'Look at those dark clouds! It is going to rain.'", "be going to = prior plan or visual evidence"),
    CardContentTuple("When do we use 'will' for future actions?", "For sudden decisions made at the moment of speech, offers, promises, and general forecasts.", "Example: 'The phone is ringing. I will answer it.'", "will = spontaneous decision"),
    CardContentTuple("What is the short form of 'will not'?", "won't (e.g., 'I won't be late tomorrow.').", "Essential for informal writing and question tags.", "won't = will not"),
    CardContentTuple("What are modal auxiliary verbs?", "Helping verbs that express ability, permission, obligation, possibility, or advice.", "Examples: can, could, may, might, must, should, will, would.", "Modal auxiliaries"),
    CardContentTuple("Which modal verb expresses strong obligation or compulsory duty?", "'must' or 'have to' (e.g., 'Students must wear their school uniform.').", "'must' comes from internal duty or rule; 'have to' from external circumstance.", "must = strong duty"),
    CardContentTuple("Which modal verb expresses polite request or permission?", "'May' or 'Could' (e.g., 'May I come in, sir?' / 'Could you please pass the salt?').", "'May' is formal; 'Could' is polite; 'Can' is informal.", "May / Could"),
    CardContentTuple("Which modal verb is used to give advice or recommendations?", "'should' or 'ought to' (e.g., 'You should consult a doctor immediately.').", "Used for suggestions and good advice.", "should = advice"),
    CardContentTuple("What is the negative form of 'must' and what does it express?", "'must not' (mustn't), expressing strict prohibition.", "Example: 'You mustn't smoke here.' (It is forbidden).", "must not = prohibition"),
    CardContentTuple("Explain the difference between 'can' and 'could'.", "'can' expresses present ability or informal permission. 'could' expresses past ability or polite request.", "Example: 'I can swim now' vs 'I could swim when I was six.'", "can vs could"),
    CardContentTuple("What does 'might' express compared to 'may'?", "A weaker possibility (remote chance).", "Example: 'It might rain today, but the sky is quite clear.'", "might = weak possibility"),
    CardContentTuple("What form of the verb follows all modal auxiliaries?", "The base form (bare infinitive) without 'to'.", "Always say 'She can swim', NEVER 'She can to swim' or 'She can swims'.", "Modal + bare infinitive"),
    CardContentTuple("Form a question tag: 'You will help me, ________?'", "won't you?", "Positive 'will' takes 'won't'.", "will -> won't you?"),
    CardContentTuple("Form a question tag: 'They won't forget, ________?'", "will they?", "Negative 'won't' takes 'will'.", "won't -> will they?"),
    CardContentTuple("Express absence of obligation using 'need'.", "need not (needn't) or don't have to (e.g., 'You needn't bring your textbook today.').", "Means there is no necessity.", "needn't = no obligation"),
    CardContentTuple("How do you express future continuous tense?", "will be + verb-ing (e.g., 'This time tomorrow, I will be flying to London.').", "Action in progress at a specific future time.", "will be + V-ing"),
    CardContentTuple("How do you express future perfect tense?", "will have + past participle (e.g., 'By 2026, I will have finished my O/L exams.').", "Action that will be completed before a future deadline.", "will have + V3"),
    CardContentTuple("Fill in the blank: 'You ________ drive without a valid driving license.'", "must not / cannot", "Strict legal prohibition.", "Prohibition"),
    CardContentTuple("Complete the offer: '________ I carry that heavy bag for you?'", "Shall / Can / May", "'Shall I' is commonly used to make polite offers in British English.", "Shall I...?"),
    CardContentTuple("What is the past form of 'must' for completed past obligation?", "'had to' (e.g., 'Yesterday I had to walk to school because the bus was late.').", "'must' has no past form; use 'had to'.", "had to = past obligation")
  )

  // --- SET 4: Passive Voice Rules & Transformations ---
  private val set4 = listOf(
    CardContentTuple("What is the general formula for transforming Active to Passive Voice?", "Object becomes Subject + appropriate form of 'be' + Past Participle (V3) + (by + agent).", "Active: Subject + Verb + Object -> Passive: Object + be + V3 + by Subject.", "Object + be + V3"),
    CardContentTuple("Change to passive: 'The gardener waters the plants every morning.'", "The plants are watered by the gardener every morning.", "Simple Present: am/is/are + watered.", "are watered"),
    CardContentTuple("Change to passive: 'The carpenter made this table.'", "This table was made by the carpenter.", "Simple Past: was/were + made.", "was made"),
    CardContentTuple("Change to passive: 'She is writing a letter.'", "A letter is being written by her.", "Present Continuous: am/is/are + being + written.", "is being written"),
    CardContentTuple("Change to passive: 'They were painting the wall.'", "The wall was being painted by them.", "Past Continuous: was/were + being + painted.", "was being painted"),
    CardContentTuple("Change to passive: 'He has finished the project.'", "The project has been finished by him.", "Present Perfect: has/have + been + finished.", "has been finished"),
    CardContentTuple("Change to passive: 'Someone will repair the computer.'", "The computer will be repaired.", "Future Simple: will be + repaired.", "will be repaired"),
    CardContentTuple("When is the 'by + agent' omitted in passive voice?", "When the agent is unknown, obvious, unimportant, or general (e.g., someone, they, people).", "Example: 'My pocket was picked.' (by someone is omitted).", "Omission of agent"),
    CardContentTuple("Change to passive: 'You must clean this room.'", "This room must be cleaned.", "Modal passive: Modal + be + V3.", "Modal + be + V3"),
    CardContentTuple("Change to passive with two objects: 'The teacher gave him a prize.'", "1. He was given a prize by the teacher. (preferred)\n2. A prize was given to him by the teacher.", "Personal object is usually preferred as passive subject.", "Two objects passive"),
    CardContentTuple("Change to passive imperative: 'Open the gate.'", "Let the gate be opened.", "Imperative formula: Let + object + be + past participle.", "Let + obj + be + V3"),
    CardContentTuple("Change to passive: 'People speak English all over the world.'", "English is spoken all over the world.", "'by people' is omitted because it is obvious.", "is spoken"),
    CardContentTuple("Change interrogative to passive: 'Did Columbus discover America?'", "Was America discovered by Columbus?", "Simple past question: Was/Were + subject + V3?", "Was/Were + obj + V3?"),
    CardContentTuple("Why is Passive Voice frequently used in scientific reports and newspapers?", "To maintain objectivity by focusing on the action or result rather than the person who did it.", "Common in lab experiments: '5 ml of acid was added to the tube.'", "Objective scientific style"),
    CardContentTuple("Change to passive: 'They are building a new hospital in our town.'", "A new hospital is being built in our town.", "Continuous requires 'being'.", "is being built"),
    CardContentTuple("Change to passive: 'Nobody can answer this difficult question.'", "This difficult question cannot be answered by anybody.", "'Nobody' becomes 'cannot be answered'.", "Negative passive"),
    CardContentTuple("What is the past participle (V3) of: write, break, drive, speak, choose?", "written, broken, driven, spoken, chosen.", "Crucial for correct passive constructions.", "Past participles"),
    CardContentTuple("Can intransitive verbs (verbs without an object) be changed to passive voice?", "No, because there is no direct object to become the new subject (e.g., sleep, arrive, smile).", "Only transitive verbs can be passivized.", "No passive for intransitive"),
    CardContentTuple("Change to passive: 'They had already sold the tickets.'", "The tickets had already been sold.", "Past Perfect: had + been + V3.", "had been sold"),
    CardContentTuple("Correct the sentence: 'The letter was wrote yesterday.'", "The letter was written yesterday.", "Always use the past participle 'written', not the past tense 'wrote'.", "was + V3 (written)")
  )

  // --- SET 5: Reported Speech (Direct & Indirect Speech) ---
  private val set5 = listOf(
    CardContentTuple("What is the difference between Direct and Indirect (Reported) Speech?", "Direct speech gives the exact words of the speaker inside quotes. Indirect speech reports what was said without quotes.", "Direct: He said, \"I am busy.\" -> Indirect: He said that he was busy.", "Direct vs Indirect"),
    CardContentTuple("How do tenses shift back when the reporting verb is in the past (e.g., 'said')?", "Simple Present -> Simple Past\nPresent Continuous -> Past Continuous\nPresent Perfect -> Past Perfect\nSimple Past -> Past Perfect\nwill -> would, can -> could.", "Known as the 'backshift' rule.", "Tense backshift rules"),
    CardContentTuple("When does the tense NOT change in reported speech?", "When the statement expresses a universal truth, scientific fact, or habit.", "Example: He said, \"Water freezes at 0°C.\" -> He said that water freezes at 0°C.", "Universal truth exception"),
    CardContentTuple("How do time and place adverbs change in reported speech?", "today -> that day; yesterday -> the day before; tomorrow -> the next day; now -> then; here -> there; this -> that; ago -> before.", "Time and place shift back.", "Adverb shifts"),
    CardContentTuple("Change to reported speech: Kamal said, \"I am reading an interesting novel.\"", "Kamal said that he was reading an interesting novel.", "am reading -> was reading; I -> he.", "was reading"),
    CardContentTuple("Change to reported speech: Maya said, \"I have bought two tickets.\"", "Maya said that she had bought two tickets.", "have bought -> had bought; I -> she.", "had bought"),
    CardContentTuple("How do you report a Wh-question (e.g., where, what, why, when)?", "Keep the question word, change question order to statement order, and apply tense backshift.", "Example: She asked, \"Where do you live?\" -> She asked where I lived.", "Wh- word + subject + verb"),
    CardContentTuple("How do you report a Yes/No question?", "Use 'if' or 'whether' as the connector + statement word order.", "Example: He asked, \"Are you ready?\" -> He asked if I was ready.", "if / whether + statement"),
    CardContentTuple("Change to reported speech: The teacher said to the students, \"Sit down quietly.\"", "The teacher ordered/told the students to sit down quietly.", "Imperative uses 'to' + base verb.", "Reporting imperatives: told/ordered to"),
    CardContentTuple("Change to reported speech: Mother said, \"Don't touch that hot pan.\"", "Mother warned/told me not to touch that hot pan.", "Negative imperative uses 'not to' + base verb.", "not to + base verb"),
    CardContentTuple("Change to reported speech: He said, \"I will call you tomorrow.\"", "He said that he would call me the following day.", "will -> would; tomorrow -> the following day.", "would call the following day"),
    CardContentTuple("What is the difference between 'said' and 'told' in reporting verbs?", "'told' requires a personal object (told someone). 'said' does not take a personal object directly.", "Correct: He told me... OR He said that... (NOT He said me).", "told + person vs said that"),
    CardContentTuple("Change to reported speech: \"Can you swim?\" the coach asked Nimal.", "The coach asked Nimal if he could swim.", "can -> could; Yes/No question uses 'if'.", "if he could swim"),
    CardContentTuple("Change to reported speech: She said, \"I saw him yesterday.\"", "She said that she had seen him the day before.", "saw (Simple Past) shifts to had seen (Past Perfect).", "had seen the day before"),
    CardContentTuple("How do modal verbs change in reported speech?", "will -> would; can -> could; may -> might; must -> had to; would, could, should remain unchanged.", "Past modals do not shift.", "Modal backshifts"),
    CardContentTuple("Change to reported speech: \"Please help me with this box,\" she said.", "She requested/asked me to help her with that box.", "'Please' becomes requested + to help; this -> that.", "requested to help"),
    CardContentTuple("Change to reported speech: The doctor said, \"You should take this medicine twice daily.\"", "The doctor advised me that I should take that medicine twice daily.", "'should' remains unchanged; advised conveys the tone.", "advised to take"),
    CardContentTuple("Change to reported speech: He asked, \"What time does the train leave?\"", "He asked what time the train left.", "does leave -> left (statement word order, not inverted).", "what time the train left"),
    CardContentTuple("Explain the punctuation rule for direct speech.", "Direct words go inside double quotation marks with a comma before opening quote, and period/comma inside quotes.", "He said, \"I am ready.\"", "Quotation marks & comma"),
    CardContentTuple("Change to direct speech: She said that she was feeling tired.", "She said, \"I am feeling tired.\"", "Reverse tense and pronoun shifts.", "Direct speech conversion")
  )

  // --- SET 6: Conditionals (Types 0, 1, 2, and 3) ---
  private val set6 = listOf(
    CardContentTuple("What is the structure and meaning of Zero Conditional?", "If + Simple Present, Simple Present. Expresses universal scientific facts or automatic results.", "Example: 'If you heat ice, it melts.'", "Zero Conditional: If + Present, Present"),
    CardContentTuple("What is the structure and meaning of First Conditional (Type 1)?", "If + Simple Present, will + base verb. Expresses real and likely future possibilities.", "Example: 'If it rains, we will cancel the match.'", "First Conditional: real future"),
    CardContentTuple("What is the structure and meaning of Second Conditional (Type 2)?", "If + Simple Past, would + base verb. Expresses hypothetical, unreal, or imaginary present/future situations.", "Example: 'If I had wings, I would fly around the world.'", "Second Conditional: unreal present"),
    CardContentTuple("Why is 'were' used with all subjects in formal Type 2 conditionals?", "In the subjunctive mood, 'were' is used for I, he, she, it (e.g., 'If I were you, I would apologize.').", "Always use 'If I were you' for giving advice.", "If I were you, I would..."),
    CardContentTuple("What is the structure and meaning of Third Conditional (Type 3)?", "If + Past Perfect (had + V3), would have + Past Participle (V3). Expresses unreal past regret that cannot be changed.", "Example: 'If I had studied harder, I would have passed the exam.'", "Third Conditional: past regret"),
    CardContentTuple("Identify the conditional type: 'If water reaches 100°C, it boils.'", "Zero Conditional (scientific law).", "Both clauses are in Simple Present.", "Zero Conditional"),
    CardContentTuple("Complete the sentence: 'If you don't hurry, you ________ (miss) the school bus.'", "will miss (First Conditional).", "If clause in Simple Present -> Main clause in 'will' + base verb.", "will miss"),
    CardContentTuple("Complete the sentence: 'If I won a million rupees, I ________ (build) a library.'", "would build (Second Conditional).", "'won' is Simple Past -> main clause uses 'would + build'.", "would build"),
    CardContentTuple("Complete the sentence: 'If she had known the truth, she ________ (tell) me.'", "would have told (Third Conditional).", "had known (Past Perfect) -> would have told.", "would have told"),
    CardContentTuple("What conjunction means 'if not'?", "'Unless' (e.g., 'Unless you work hard, you will fail' = 'If you do not work hard, you will fail').", "Never put a negative verb immediately after 'unless'.", "Unless = If not"),
    CardContentTuple("Rewrite using 'Unless': 'If you do not practice daily, you cannot improve.'", "Unless you practice daily, you cannot improve.", "'Unless' replaces 'If ... not'.", "Unless you practice"),
    CardContentTuple("Can the 'if' clause be placed second in a sentence? How does punctuation change?", "Yes. If the 'if' clause comes second, no comma is needed (e.g., 'We will go for a walk if it stops raining.').", "If clause first -> comma; Main clause first -> no comma.", "Comma placement in conditionals"),
    CardContentTuple("Complete: 'If I ________ (be) the President, I would eradicate poverty.'", "were (Subjunctive mood in Type 2).", "Hypothetical unreal wish.", "were (subjunctive)"),
    CardContentTuple("Correct the sentence: 'If it will rain tomorrow, we will stay at home.'", "If it rains tomorrow, we will stay at home.", "Never use 'will' inside the 'if' clause.", "No 'will' in 'if' clause"),
    CardContentTuple("Rewrite in Third Conditional: 'He drove fast and met with an accident.'", "If he hadn't driven fast, he wouldn't have met with an accident.", "Expresses past contrary-to-fact situation.", "Hadn't driven -> wouldn't have met"),
    CardContentTuple("Which modal verbs can replace 'would' in conditional main clauses?", "Could (ability) or Might (possibility) (e.g., 'If it stopped raining, we could play tennis.').", "Modulates degree of certainty.", "could / might in conditionals"),
    CardContentTuple("What does 'as long as' or 'provided that' mean in conditional sentences?", "They mean 'only if' or 'on condition that' (e.g., 'You can borrow my camera provided that you handle it carefully.').", "Alternative conditional connectors.", "provided that / as long as"),
    CardContentTuple("Identify the error: 'If she had listened to me, she would be safe yesterday.'", "'she would have been safe yesterday' (past reference requires 'would have been' in Type 3).", "Time reference 'yesterday' mandates full Type 3.", "would have been"),
    CardContentTuple("How is 'In case' used compared to 'If'?", "'In case' means doing something beforehand for protection (e.g., 'Take an umbrella in case it rains', meaning take it now before it rains).", "Precautionary action.", "in case = precaution"),
    CardContentTuple("Complete: 'If you heat sugar, it ________ (turn) brown.'", "turns (Zero conditional - general culinary/chemical fact).", "Present fact.", "turns")
  )

  // --- SET 7: Relative Clauses (Defining & Non-defining) ---
  private val set7 = listOf(
    CardContentTuple("What is a relative clause?", "A clause that gives more information about a noun, introduced by relative pronouns (who, whom, whose, which, that, where, when).", "Functions as an adjective modifying a noun.", "Relative clause definition"),
    CardContentTuple("Which relative pronoun is used for people as the subject?", "'who' or 'that' (e.g., 'The teacher who taught us English has retired.').", "'who' is preferred for people.", "who = people subject"),
    CardContentTuple("Which relative pronoun is used for animals and things?", "'which' or 'that' (e.g., 'The train which leaves at 7 a.m. is always crowded.').", "'which' or 'that'.", "which / that = things"),
    CardContentTuple("Which relative pronoun shows possession?", "'whose' (e.g., 'I met a boy whose father is an airline pilot.').", "Replaces 'his/her/their' to show ownership.", "whose = possession"),
    CardContentTuple("Which relative pronoun refers to places?", "'where' (e.g., 'This is the hospital where I was born.').", "Refers to a geographical location or building.", "where = places"),
    CardContentTuple("Which relative pronoun refers to time?", "'when' (e.g., '1948 was the year when Sri Lanka gained independence.').", "Refers to a specific year, day, or moment.", "when = time"),
    CardContentTuple("What is a defining (restrictive) relative clause?", "A clause essential to identify which person or thing is being talked about; no commas are used.", "Example: 'The students who passed the exam received certificates.'", "Defining = essential, NO commas"),
    CardContentTuple("What is a non-defining (non-restrictive) relative clause?", "A clause that adds extra, non-essential information; it is separated by commas.", "Example: 'Sigiriya, which was built by King Kashyapa, is a world heritage site.'", "Non-defining = extra info, WITH commas"),
    CardContentTuple("Can the pronoun 'that' be used in non-defining relative clauses?", "No. 'that' can only be used in defining clauses. In non-defining clauses, use 'which' for things and 'who' for people.", "Never use 'that' after a comma.", "No 'that' in non-defining"),
    CardContentTuple("Combine using a relative pronoun: 'The man was very kind. He helped me.'", "The man who helped me was very kind.", "'who' joins the two sentences seamlessly.", "The man who helped me"),
    CardContentTuple("Combine: 'I bought a laptop. It is very fast.'", "The laptop which (or that) I bought is very fast.", "Object of the first sentence becomes relative clause.", "laptop which I bought"),
    CardContentTuple("When can the relative pronoun be omitted in a defining relative clause?", "When it acts as the object of the verb in the relative clause (e.g., 'The book (that) I read was interesting.').", "It cannot be omitted if it is the subject.", "Omission of relative pronoun"),
    CardContentTuple("Combine using 'whose': 'The girl is crying. Her puppy is lost.'", "The girl whose puppy is lost is crying.", "'whose' replaces 'her'.", "whose puppy is lost"),
    CardContentTuple("Explain the difference: 'whom' vs 'who'.", "'whom' is formal and used when the person is the object of the verb or follows a preposition (e.g., 'The lady to whom I spoke').", "In modern spoken English, 'who' often replaces 'whom'.", "who (subject) vs whom (object)"),
    CardContentTuple("Correct the sentence: 'Kandy that is surrounded by hills is a beautiful city.'", "Kandy, which is surrounded by hills, is a beautiful city.", "Proper nouns take non-defining clauses with commas and 'which', not 'that'.", "Commas + which for proper nouns"),
    CardContentTuple("Combine: 'We visited the ancient temple. The sacred tooth relic is kept there.'", "We visited the ancient temple where the sacred tooth relic is kept.", "'where' replaces 'there'.", "where sacred relic is kept"),
    CardContentTuple("Identify the relative clause: 'The mobile phone that fell into the water still works.'", "'that fell into the water' (defining relative clause).", "Tells us which phone.", "defining clause"),
    CardContentTuple("Combine: 'Sunday is a holiday. We usually play cricket then.'", "Sunday is the day when we usually play cricket.", "'when' connects the time reference.", "when we play cricket"),
    CardContentTuple("Fill in the blank: 'The scientist ________ discovered penicillin was Alexander Fleming.'", "who", "Refers to a human subject.", "who"),
    CardContentTuple("Why is punctuation vital in relative clauses?", "Omitting commas changes the entire meaning from extra background information to essential identification of a subset.", "Punctuation defines meaning.", "Comma changes meaning")
  )

  // --- SET 8: Prepositions of Time, Place, and Direction ---
  private val set8 = listOf(
    CardContentTuple("What are the rules for prepositions of time: IN, ON, AT?", "AT: specific times, festivals (at 5 p.m., at noon, at night, at Christmas)\nON: days and dates (on Monday, on 4th February)\nIN: months, years, centuries, seasons, parts of day (in May, in 2026, in the morning).", "Pyramid of time: AT (narrow) -> ON (medium) -> IN (wide).", "AT (time) / ON (day) / IN (period)"),
    CardContentTuple("What are the rules for prepositions of place: IN, ON, AT?", "AT: specific point or address (at the bus stop, at 45 Main Street)\nON: surface or street name (on the table, on Galle Road)\nIN: enclosed space or large territory (in the room, in Sri Lanka).", "Pyramid of place.", "AT (point) / ON (surface) / IN (area)"),
    CardContentTuple("Fill in: 'The national day celebration is held ________ the 4th of February.'", "on", "Specific calendar date requires 'on'.", "on + date"),
    CardContentTuple("Fill in: 'Our school starts ________ 7.30 a.m.'", "at", "Exact clock time takes 'at'.", "at + clock time"),
    CardContentTuple("Explain the difference between 'between' and 'among'.", "'between' is used for two people or things; 'among' is used for three or more in a group.", "Example: 'between two trees' vs 'among the crowd'.", "between (2) vs among (3+)"),
    CardContentTuple("Explain the difference between 'since' and 'for' with Present Perfect.", "'since' specifies the starting point in time (since 2015, since morning); 'for' specifies the duration (for 5 years, for two hours).", "since = point in time; for = duration.", "since (point) vs for (duration)"),
    CardContentTuple("Explain the difference between 'in time' and 'on time'.", "'on time' means punctual according to a timetable. 'in time' means before it is too late.", "The train arrived on time; we arrived in time to catch it.", "on time (punctual) vs in time (not late)"),
    CardContentTuple("What preposition is used for means of transport?", "'by' for general mode (by bus, by car, by train, by air); but 'on foot'.", "Use 'in a car' / 'on a bus' when an article is present.", "by bus / on foot"),
    CardContentTuple("Fill in the blanks: 'The cat jumped ________ the table and ran ________ the door.'", "onto / off, through / out of", "Prepositions of movement and direction.", "movement prepositions"),
    CardContentTuple("Explain the difference: 'beside' vs 'besides'.", "'beside' means next to or at the side of. 'besides' means in addition to or moreover.", "Example: 'Sit beside me' vs 'Besides English, he speaks Tamil.'", "beside (next to) vs besides (in addition)"),
    CardContentTuple("Fill in: 'He is proficient ________ Mathematics and good ________ English.'", "in, at", "'good at' is a fixed collocation; 'proficient in' refers to subject mastery.", "good at / proficient in"),
    CardContentTuple("Fill in: 'She has been suffering ________ dengue fever.'", "from", "'suffer from' is a fixed prepositional phrase.", "suffer from"),
    CardContentTuple("Fill in: 'They congratulated him ________ his great victory.'", "on", "'congratulate on' is the standard English collocation.", "congratulate on"),
    CardContentTuple("Fill in: 'The dog jumped ________ the fence.'", "over", "'over' indicates movement across the top without touching.", "jump over"),
    CardContentTuple("Explain 'across' vs 'through'.", "'across' is movement from one side to the other of a flat surface (across the road). 'through' is movement in a 3D enclosed space (through the tunnel/forest).", "Surface vs volume.", "across vs through"),
    CardContentTuple("Fill in: 'He divided the mangoes ________ the two brothers.'", "between", "Two entities require 'between'.", "between two"),
    CardContentTuple("Fill in: 'He is afraid ________ dark places.'", "of", "'afraid of' is the correct preposition.", "afraid of"),
    CardContentTuple("Fill in: 'Smoking is injurious ________ health.'", "to", "Injurious to health.", "injurious to"),
    CardContentTuple("What is wrong with: 'He discussed about the matter'?", "'about' is redundant. 'discuss' is a transitive verb meaning 'talk about', so say 'He discussed the matter.'", "Do not add 'about' after discuss.", "discuss (no 'about')"),
    CardContentTuple("Fill in: 'We stayed at a hotel ________ our vacation.'", "during", "'during' indicates within the timeframe of an event.", "during the vacation")
  )

  // --- SET 9: Conjunctions, Connectors, and Cohesive Devices ---
  private val set9 = listOf(
    CardContentTuple("What is the function of coordinating conjunctions (FANBOYS)?", "They join words, phrases, or independent clauses of equal grammatical rank.", "FANBOYS: For, And, Nor, But, Or, Yet, So.", "FANBOYS coordinating"),
    CardContentTuple("What connectors are used to show contrast or concession?", "However, although, even though, but, yet, nevertheless, on the other hand, in spite of, despite.", "Introduces opposing ideas.", "Contrast connectors"),
    CardContentTuple("Explain the difference in grammar between 'Although' and 'Despite'.", "'Although' is followed by a subject + verb clause. 'Despite' / 'In spite of' is followed by a noun phrase or gerund (-ing).", "Although it rained... vs Despite the rain...", "Although + clause vs Despite + noun/-ing"),
    CardContentTuple("Combine using 'Although': 'He was very tired. He completed the project.'", "Although he was very tired, he completed the project.", "Shows unexpected contrast.", "Although + clause"),
    CardContentTuple("Rewrite using 'In spite of': 'Although it rained heavily, they played the match.'", "In spite of the heavy rain, they played the match. (or In spite of raining heavily...)", "'In spite of' must take a noun phrase.", "In spite of + noun"),
    CardContentTuple("What connectors are used to give reasons or causes?", "Because, as, since, due to, owing to, because of.", "'Because' + clause; 'Due to' / 'Because of' + noun phrase.", "Cause connectors"),
    CardContentTuple("What connectors are used to show results or consequences?", "Therefore, as a result, consequently, so, thus.", "Connects cause to outcome.", "Result connectors"),
    CardContentTuple("What connectors are used to add more information (addition)?", "Furthermore, moreover, in addition, besides, also, not only ... but also.", "Enriches formal essays and reports.", "Addition connectors"),
    CardContentTuple("How do you use the correlative conjunction 'not only ... but also'?", "Places parallel emphasis on two attributes: 'Kamal is not only intelligent but also hardworking.'", "Ensure parallel grammatical structure after both parts.", "not only ... but also"),
    CardContentTuple("How do you use 'either ... or' and 'neither ... nor'?", "'either ... or' indicates one of two alternatives. 'neither ... nor' negates both alternatives.", "Example: 'Neither Nimal nor Sunil was present.'", "either...or / neither...nor"),
    CardContentTuple("What is the subject-verb agreement rule for 'neither ... nor'?", "The verb agrees with the closer subject (e.g., 'Neither the teacher nor the students were ready.').", "Proximity rule applies.", "Agreement with closer subject"),
    CardContentTuple("Combine using 'Both ... and': 'She sings well. She dances well.'", "She both sings and dances well. (or She is both a good singer and dancer.)", "Emphasizes both qualities.", "both ... and"),
    CardContentTuple("What connector is used to express purpose?", "'so that' + modal clause, or 'in order to' / 'so as to' + base verb.", "Example: 'He ran fast so that he could catch the train.'", "so that / in order to"),
    CardContentTuple("Combine using 'so that': 'I woke up early. I wanted to catch the first bus.'", "I woke up early so that I could catch the first bus.", "'so that' expresses intention/purpose.", "so that I could..."),
    CardContentTuple("Explain 'too ... to' in negative results.", "Expresses that an excess of a quality prevents an action: 'He is too weak to walk' = 'He is so weak that he cannot walk.'", "Contains an inherent negative meaning.", "too ... to"),
    CardContentTuple("Convert 'The tea is too hot to drink' into 'so ... that'.", "The tea is so hot that I cannot drink it.", "'so + adjective + that + negative clause'.", "so ... that cannot"),
    CardContentTuple("What connectors are used to show sequence in writing?", "First, secondly, next, then, after that, finally, subsequently.", "Essential for process descriptions and recipe instructions.", "Sequence markers"),
    CardContentTuple("What connector gives examples?", "For example, for instance, such as, namely.", "Used in O/L essay writing.", "Giving examples"),
    CardContentTuple("What connector concludes an essay?", "In conclusion, to sum up, in summary, overall.", "Closes formal compositions.", "Conclusion connectors"),
    CardContentTuple("Fill in: 'You must apologize, ________ he will not speak to you.'", "otherwise / or else", "Indicates undesirable consequence.", "otherwise")
  )

  // --- SET 10: Formal Letter Writing & Official Correspondence ---
  private val set10 = listOf(
    CardContentTuple("What are the 7 essential parts of a Formal / Business Letter in O/L English?", "1. Sender's Address\n2. Date\n3. Receiver's Designation & Address\n4. Salutation (Sir/Madam)\n5. Heading / Subject Line\n6. Body of the Letter (3 paragraphs)\n7. Subscription / Sign-off (Yours faithfully/sincerely + Signature + Full Name).", "Following standard format gains maximum format marks.", "7 parts of formal letter"),
    CardContentTuple("What is the correct Salutation when writing to an unknown official?", "\"Dear Sir / Madam,\" or \"Sir,\"", "Keep it formal and professional.", "Dear Sir / Madam"),
    CardContentTuple("What is the corresponding Subscription (Sign-off) when the salutation is 'Dear Sir/Madam'?", "\"Yours faithfully,\"", "Note: 'Yours' has NO apostrophe (never write Your's), and 'faithfully' begins with lowercase.", "Yours faithfully,"),
    CardContentTuple("When do we use 'Yours sincerely'?", "When you know the person by name in the salutation (e.g., 'Dear Mr. Perera,').", "Salutation with name -> Yours sincerely.", "Yours sincerely"),
    CardContentTuple("What is the purpose of the 'Heading' or 'Subject' line?", "To state the reason for writing clearly and concisely in one underlined phrase (e.g., 'Subject: Request for permission to use the school auditorium').", "Placed right after the salutation.", "Subject / Heading line"),
    CardContentTuple("What should the first paragraph of a formal letter contain?", "A clear statement of the purpose of writing (e.g., 'I am writing this letter on behalf of the English Literary Association to request...').", "State purpose directly without beating around the bush.", "First paragraph = purpose"),
    CardContentTuple("What should the body (second paragraph) contain?", "All specific details: dates, times, venue, participants, and reasons.", "Organized logically and politely.", "Body = details"),
    CardContentTuple("What should the concluding paragraph contain?", "A courteous call to action and expression of gratitude (e.g., 'I would be grateful if you could kindly grant us permission. Thanking you.').", "Polite conclusion.", "Conclusion = gratitude"),
    CardContentTuple("Why must contractions (e.g., don't, can't, I'm) be avoided in formal letters?", "Because formal writing requires standard uncontracted language to maintain professional decorum.", "Write 'do not', 'cannot', 'I am'.", "No contractions in formal writing"),
    CardContentTuple("Write a standard closing phrase before 'Yours faithfully'.", "\"Thanking you,\"", "Placed on a separate line.", "Thanking you,"),
    CardContentTuple("How should dates be written formally in letters?", "\"4th February 2026\" or \"February 4, 2026\" (avoid slashes like 04/02/2026).", "Write full month name.", "Formal date format"),
    CardContentTuple("Write an opening sentence applying for the post of Junior Clerk.", "\"I wish to apply for the post of Junior Clerk advertised in the 'Daily News' of 15th January 2026.\"", "Standard job application opening.", "Job application opening"),
    CardContentTuple("Write a sentence requesting permission from the Principal.", "\"I kindly request you to grant us permission to organize an English Day exhibition in the main hall.\"", "Polite request.", "Requesting permission"),
    CardContentTuple("Write a sentence complaining about an irregular water supply to the Municipal Council.", "\"I am writing to draw your kind attention to the severe irregular water supply in our residential area.\"", "Formal complaint tone.", "Letter of complaint"),
    CardContentTuple("Where is the sender's address placed in standard modern block format?", "At the top left corner, followed by the date, with all alignments flushed to the left margin.", "Full block format is modern standard.", "Left-aligned block format"),
    CardContentTuple("What is the tone required in formal correspondence?", "Polite, objective, concise, and respectful, avoiding emotional or aggressive words.", "Professional etiquette.", "Courteous formal tone"),
    CardContentTuple("Correct the error: 'Your's faithfully,'", "'Yours faithfully,' (There is NEVER an apostrophe in 'Yours').", "Frequent spelling trap in O/L exams.", "Yours (no apostrophe)"),
    CardContentTuple("Write a sentence apologising for absence from school.", "\"I am writing to explain that I was unable to attend school from 10th to 12th March due to a severe viral fever.\"", "Letter of excuse.", "Apology for absence"),
    CardContentTuple("What should be placed below the signature in a formal letter?", "The writer's full printed name and designation/post (e.g., 'Secretary, Science Club').", "Identifies sender clearly.", "Name & designation"),
    CardContentTuple("Why is spacing between paragraphs important?", "It creates visual clarity and distinctness, making the letter easy to read and evaluate.", "Leave one line between paragraphs.", "Paragraph spacing")
  )
}
