package com.example

/**
 * Curated, 100% syllabus-aligned, distinct Flashcards for English (G.C.E. O/L).
 * Part 2: Categories 11 to 20 (20 distinct questions per category).
 * Strictly guarantees no repeated questions within the same category or across different categories.
 */
object FlashcardBankEnglishSetsPart2 {

  fun getCard(setNum: Int, cardNum: Int): CardContentTuple {
    val c = cardNum.coerceIn(1, 20)
    return when (setNum) {
      11 -> set11[(c - 1) % set11.size]
      12 -> set12[(c - 1) % set12.size]
      13 -> set13[(c - 1) % set13.size]
      14 -> set14[(c - 1) % set14.size]
      15 -> set15[(c - 1) % set15.size]
      16 -> set16[(c - 1) % set16.size]
      17 -> set17[(c - 1) % set17.size]
      18 -> set18[(c - 1) % set18.size]
      19 -> set19[(c - 1) % set19.size]
      20 -> set20[(c - 1) % set20.size]
      else -> set11[(c - 1) % set11.size]
    }
  }

  // --- SET 11: Notice & Note Writing Templates ---
  private val set11 = listOf(
    CardContentTuple("What are the 5 mandatory elements of a school Notice in O/L English?", "1. Heading: NOTICE (centered, bold/capital)\n2. Purpose/Topic (clear and eye-catching)\n3. Details (Date, Time, Venue, Target Audience)\n4. Date of issue\n5. Sign-off (Designation of issuer, e.g., Secretary, English Club).", "Always enclose the notice inside a neat box.", "5 elements of a Notice"),
    CardContentTuple("Why must a Notice be enclosed within a rectangular border/box?", "It is the standard examination format convention demonstrating that the notice is a visual public display poster.", "Drawing a box earns format marks.", "Enclose in a box"),
    CardContentTuple("What are the '5 Ws' that every effective notice must answer?", "What (the event), When (date & time), Where (venue/location), Who (target group/participants), Whom to contact.", "Answers all practical queries.", "The 5 Ws"),
    CardContentTuple("Write a standard opening line for a notice announcing an English Day Competition.", "\"The English Literary Association of our school has organized an Inter-house English Day Competition.\"", "States organizer and event clearly.", "Notice opening line"),
    CardContentTuple("How should the date, time, and venue be presented in a notice for clarity?", "In bullet points:\n• Date: 25th March 2026\n• Time: 9.00 a.m. - 1.00 p.m.\n• Venue: School Main Auditorium.", "Bullet points ensure rapid visual scanning.", "Bullet point details"),
    CardContentTuple("Write a standard closing line inviting participation in a notice.", "\"All students of Grades 10 and 11 are cordially invited to participate. For registrations, contact the secretary.\"", "Direct call to action.", "Notice closing call"),
    CardContentTuple("What is the typical word limit for an O/L Notice (Question 4/5)?", "Around 40 to 50 words.", "Be concise; avoid lengthy discursive sentences.", "40 - 50 words"),
    CardContentTuple("What are the key elements of an informal Note to a friend or family member?", "1. Salutation (e.g., Dear Maya,)\n2. Reason for writing (the message/apology/instruction)\n3. Follow-up action\n4. Sign-off (e.g., Nimal).", "Keep it brief, natural, and friendly.", "Informal note elements"),
    CardContentTuple("Write a short note apologizing for not being able to attend a birthday party.", "\"Dear Rohan, I am really sorry that I cannot attend your birthday party this evening as I have a sudden dental appointment. Wishing you a very happy birthday! Nimal.\"", "Friendly and polite.", "Apology note"),
    CardContentTuple("Write a short note giving instructions to a brother before leaving home.", "\"Dear Amal, Please feed the fish and lock the front gate before you leave for tuition. I will be back by 5 p.m. Sahan.\"", "Clear directive message.", "Instructional note"),
    CardContentTuple("Where is the issuer's designation placed in a notice?", "At the bottom right or left corner below the signature (e.g., 'K. Silva, Secretary, Science Society').", "Clearly identifies authority.", "Designation placement"),
    CardContentTuple("Should a notice use active or passive voice for formal tone?", "Passive voice is frequently preferred (e.g., 'Entries must be submitted before 15th March').", "Gives an authoritative public tone.", "Passive tone in notices"),
    CardContentTuple("What phrase is used to invite all students to attend?", "\"All are cordially invited\" or \"Attendance is compulsory for all members.\"", "Standard notice phraseology.", "All are cordially invited"),
    CardContentTuple("Why is the date of issue important in a notice?", "It informs the public when the announcement was posted so they can judge deadlines and urgency.", "Place at top or bottom left.", "Date of issue"),
    CardContentTuple("What is the difference in tone between a Notice and an informal Note?", "A Notice is formal, objective, and addressed to the public. A Note is informal, personal, and addressed to an individual.", "Public vs private communication.", "Tone contrast"),
    CardContentTuple("Write a note requesting a friend to return a borrowed book.", "\"Dear Kasun, Could you please bring my Science textbook to school tomorrow? I need it to prepare for the term test. Thanks, Kamal.\"", "Polite request note.", "Book return note"),
    CardContentTuple("How can passive voice be used in a notice deadline sentence?", "\"Completed application forms should be handed over to the teacher-in-charge on or before 10th October.\"", "Formal passive instruction.", "Deadline sentence"),
    CardContentTuple("Can abbreviations like 'e.g.' and 'p.m.' be used in a notice?", "Yes, standard abbreviations like a.m., p.m., info, Tel., and e.g. are widely accepted to keep it concise.", "Saves words within limit.", "Abbreviations permitted"),
    CardContentTuple("What is the penalty for exceeding the word limit in notice writing?", "Marks may be deducted for lack of conciseness and failing to adhere to examination instructions.", "Stay within 40-50 words.", "Word limit precision"),
    CardContentTuple("What heading should be used for a notice about a lost item?", "\"LOST! LOST! LOST!\" or \"NOTICE: LOST BICYCLE\"", "Captures immediate attention.", "Lost & Found notice")
  )

  // --- SET 12: Describing Bar Charts, Pie Charts & Trend Analysis ---
  private val set12 = listOf(
    CardContentTuple("What are the 3 structural sections needed to describe a Bar Chart or Pie Chart in O/L English?", "1. Introduction (What the chart represents, data source, year)\n2. Body Paragraph (Comparing highest, lowest, trends, and key figures)\n3. Conclusion (Overall summary or deduction).", "Ensures comprehensive coverage of Question 14.", "Intro, Body, Conclusion"),
    CardContentTuple("Write a model introductory sentence for a bar chart on students' favorite leisure activities.", "\"This bar chart illustrates the preferred leisure activities of Grade 11 students at Central College in 2025.\"", "Rephrases chart title professionally.", "Chart intro sentence"),
    CardContentTuple("What vocabulary is used to describe an upward trend (increase)?", "Increased, rose, climbed, surged, grew, upward trend, sharp rise.", "Example: 'The number of computer users increased significantly.'", "Upward trend verbs"),
    CardContentTuple("What vocabulary is used to describe a downward trend (decrease)?", "Decreased, dropped, fell, declined, plummeted, downward trend.", "Example: 'Expenditure on books fell noticeably.'", "Downward trend verbs"),
    CardContentTuple("What vocabulary is used when data remains unchanged or constant?", "Remained stable, remained steady, leveled off, stayed constant, plateaued.", "No fluctuation.", "Steady / Stable trend"),
    CardContentTuple("What terms are used to describe the highest and lowest points?", "\"The highest percentage / The largest proportion / The peak was recorded by...\"\n\"The lowest figure / The smallest percentage was...\"", "Mandatory comparative analysis.", "Highest vs lowest"),
    CardContentTuple("What vocabulary describes speed and degree of change?", "Sharply, dramatically, significantly (large/fast); gradually, steadily, slightly, marginally (slow/small).", "Adverbs enrich descriptions.", "Degree of change adverbs"),
    CardContentTuple("How do you describe proportions in a Pie Chart?", "\"Half of the students (50%)\", \"A quarter of respondents (25%)\", \"A vast majority\", \"A small minority\", \"One third (33%)\".", "Converts raw percentages into descriptive prose.", "Proportions & fractions"),
    CardContentTuple("Write a sentence comparing two categories: Reading books (40%) vs Watching TV (20%).", "\"While 40% of the students preferred reading books, only half that number (20%) chose watching television.\"", "Uses comparative connector 'while'.", "Comparative sentence"),
    CardContentTuple("What connectors are essential for comparing chart data?", "In contrast, compared to, in comparison with, whereas, while, on the other hand.", "Binds comparisons together smoothly.", "Chart connectors"),
    CardContentTuple("Write a concluding summary sentence for a chart.", "\"In conclusion, it is evident that reading was the most popular activity, whereas gardening was the least favored.\"", "Highlights key takeaway.", "Conclusion takeaway"),
    CardContentTuple("What tense should be used if the chart represents past data (e.g., year 2020)?", "Simple Past Tense (e.g., 'was, recorded, accounted for, represented').", "Match tense to the chart's timestamp.", "Past tense for past years"),
    CardContentTuple("What tense is used if the chart represents current or timeless survey data?", "Simple Present Tense (e.g., 'shows, represents, accounts for').", "General presentation tense.", "Present tense for current data"),
    CardContentTuple("Correct the sentence: 'The percentage of boys were 60%.'", "'The percentage of boys was 60%.' (The singular noun 'percentage' takes the singular verb 'was').", "Common agreement error.", "Percentage was..."),
    CardContentTuple("Explain 'accounted for' and 'represented' in chart writing.", "They mean 'formed a part of': 'Agriculture accounted for 35% of the total national income.'", "Professional collocation.", "accounted for / represented"),
    CardContentTuple("What phrase indicates that two values are equal?", "\"Equal proportions of...\" or \"Both categories were on par at 25% each.\"", "Signifies equality.", "Equal proportions"),
    CardContentTuple("Explain 'fluctuated' in line graph descriptions.", "Rose and fell erratically over time without a steady pattern.", "Fluctuation over time.", "Fluctuated = up and down"),
    CardContentTuple("Can you express personal opinions in a chart description?", "No. Stick strictly to facts and figures visible on the chart; do not invent outside causes not shown.", "Maintain strict objectivity.", "Stick to visible data"),
    CardContentTuple("What is the difference between 'majority' and 'minority'?", "'Majority' means more than 50% (the larger group). 'Minority' means less than 50% (the smaller group).", "Key statistical concepts.", "Majority vs Minority"),
    CardContentTuple("Write a sentence showing double: Category A is 60%, Category B is 30%.", "\"The percentage for Category A was exactly twice that of Category B.\"", "Proportional comparison.", "Twice as high as")
  )

  // --- SET 13: Vocabulary, Synonyms & Antonyms ---
  private val set13 = listOf(
    CardContentTuple("Give synonyms for: HUGE, TINY, RAPID, ANCIENT.", "HUGE: Enormous, gigantic, vast.\nTINY: Miniature, minute, microscopic.\nRAPID: Swift, quick, brisk.\nANCIENT: Archaic, prehistoric, antique.", "Expands expressive range in essays.", "Synonyms: size & speed"),
    CardContentTuple("Give synonyms for: IMPORTANT, DIFFICULT, BEAUTIFUL, FAMOUS.", "IMPORTANT: Crucial, vital, essential, paramount.\nDIFFICULT: Arduous, challenging, tough.\nBEAUTIFUL: Gorgeous, stunning, attractive.\nFAMOUS: Renowned, eminent, celebrated.", "Replaces overused simple words.", "Advanced adjectives"),
    CardContentTuple("Give antonyms (opposites) for: ARTIFICIAL, ANCIENT, GENUINE, TEMPORARY.", "ARTIFICIAL: Natural\nANCIENT: Modern\nGENUINE: Fake / Counterfeit\nTEMPORARY: Permanent.", "Core vocabulary pairs.", "Antonyms: nature & time"),
    CardContentTuple("Give antonyms for: OPTIMISTIC, SUPERIOR, ABUNDANT, DILIGENT.", "OPTIMISTIC: Pessimistic\nSUPERIOR: Inferior\nABUNDANT: Scarce / Meager\nDILIGENT: Lazy / Indolent.", "Personality & quantity antonyms.", "Antonyms: traits"),
    CardContentTuple("What are homophones? Give 3 common pairs.", "Words that sound the same but have different spellings and meanings.\n1. Hear / Here\n2. Their / There / They're\n3. Weather / Whether.", "Frequent spelling traps.", "Homophones definition"),
    CardContentTuple("Explain the difference: 'Principal' vs 'Principle'.", "'Principal' = Head of a school / main item (e.g., School Principal).\n'Principle' = Fundamental truth or moral rule (e.g., Moral principles).", "Remember: 'The Principal is your pal'.", "Principal vs Principle"),
    CardContentTuple("Explain the difference: 'Complement' vs 'Compliment'.", "'Complement' = Something that completes or goes well with.\n'Compliment' = An expression of praise or admiration.", "Complement completes; Compliment praises.", "Complement vs Compliment"),
    CardContentTuple("What prefix turns these words into their opposites: happy, possible, legal, regular, visible?", "unhappy (un-), impossible (im-), illegal (il-), irregular (ir-), invisible (in-).", "Negative prefixes.", "Negative prefixes"),
    CardContentTuple("What suffix turns these adjectives into abstract nouns: kind, happy, brave, dark?", "kindness (-ness), happiness (-ness), bravery (-ery), darkness (-ness).", "Noun-forming suffixes.", "Abstract noun suffixes"),
    CardContentTuple("Give the noun form of: destroy, decide, conclude, permit.", "destruction, decision, conclusion, permission.", "Verb to noun conversion.", "Nominalization"),
    CardContentTuple("What does the idiom 'once in a blue moon' mean?", "Very rarely; almost never.", "Example: 'He visits his ancestral village once in a blue moon.'", "once in a blue moon"),
    CardContentTuple("What does the idiom 'piece of cake' mean?", "Something that is very easy to accomplish.", "Example: 'The English paper was a piece of cake for him.'", "piece of cake = very easy"),
    CardContentTuple("What does 'burn the midnight oil' mean?", "To work or study late into the night.", "Example: 'Students burn the midnight oil before the O/L exams.'", "burn the midnight oil"),
    CardContentTuple("Give a formal synonym for 'buy', 'help', 'start', 'end'.", "buy -> purchase; help -> assist; start -> commence; end -> conclude/terminate.", "Elevates formal vocabulary.", "Formal equivalents"),
    CardContentTuple("What is the meaning of the word 'diligent'?", "Showing steady, energetic, and careful effort in one's work.", "Diligent students pass with flying colors.", "diligent = hardworking"),
    CardContentTuple("What is the meaning of 'sustainable' in environmental contexts?", "Able to be maintained at a certain rate without exhausting natural resources.", "Sustainable development protects the future.", "sustainable"),
    CardContentTuple("Differentiate 'Stationary' vs 'Stationery'.", "'Stationary' (with 'a') means not moving/standing still.\n'Stationery' (with 'e') means writing materials (pens, paper, envelopes).", "Remember: 'e' for envelope in stationery.", "Stationary vs Stationery"),
    CardContentTuple("Differentiate 'Affect' vs 'Effect'.", "'Affect' is usually a verb meaning to influence (e.g., 'Smoking affects health').\n'Effect' is usually a noun meaning a result (e.g., 'The effect of exercise').", "A = Action (Verb); E = End result (Noun).", "Affect vs Effect"),
    CardContentTuple("What is a collective noun for: lions, fish, wolves, bees?", "A pride of lions, a school/shoal of fish, a pack of wolves, a swarm of bees.", "Standard animal collectives.", "Animal collective nouns"),
    CardContentTuple("Give the synonym of 'reluctant'.", "Unwilling, hesitant, disinclined.", "He was reluctant to leave.", "reluctant")
  )

  // --- SET 14: Common Spelling, Punctuation & Grammar Traps ---
  private val set14 = listOf(
    CardContentTuple("What are the 3 distinct uses of the Apostrophe ( ' ) in English?", "1. Contraction (short forms: can't, don't, it's = it is)\n2. Singular possession (the boy's dog)\n3. Plural possession ending in s (the boys' school).", "Never use an apostrophe for simple plurals (e.g., NOT apple's).", "Apostrophe uses"),
    CardContentTuple("What is the difference between 'Its' and 'It's'?", "'It's' = Contraction of 'it is' or 'it has' (e.g., 'It's raining').\n'Its' = Possessive pronoun showing ownership (e.g., 'The bird flapped its wings').", "Never put an apostrophe in possessive 'its'.", "Its vs It's"),
    CardContentTuple("How do you form possessive for plural nouns ending in 's'?", "Add only the apostrophe at the end without an extra 's' (e.g., 'teachers' room', 'students' books').", "Plural already ends in 's' -> add only apostrophe.", "Plural possessive"),
    CardContentTuple("How do you form possessive for irregular plural nouns not ending in 's'?", "Add 'apostrophe + s' (e.g., 'children's park', 'women's rights', 'men's wear').", "Treated like singular nouns.", "Irregular plural possessive"),
    CardContentTuple("What is the rule 'i before e except after c'?", "Spell with 'ie' in most words (belief, chief, piece, friend); but with 'ei' after 'c' (receive, ceiling, deceive, receipt).", "Exceptions: weird, neighbor, weigh.", "i before e except after c"),
    CardContentTuple("Spell correctly: accommodation, environment, embarrass, government.", "Accommodation (double c, double m), Environment (has 'n'), Embarrass (double r, double s), Government (has 'n').", "Top 4 misspelled words in O/L papers.", "Tricky spellings"),
    CardContentTuple("Correct the sentence: 'One of my friend is a doctor.'", "'One of my friends is a doctor.' ('One of' is followed by a plural noun 'friends', but takes a singular verb 'is').", "One of + plural noun + singular verb.", "One of my friends is"),
    CardContentTuple("When should a Comma be used in a sentence?", "1. To separate items in a list (apples, oranges, and bananas)\n2. After an introductory adverb or clause (Suddenly, ...)\n3. In non-defining relative clauses\n4. Before coordinating conjunctions joining independent clauses.", "Avoid run-on sentences.", "Comma rules"),
    CardContentTuple("When should a Semicolon ( ; ) be used?", "To connect two closely related independent clauses without a coordinating conjunction (e.g., 'My dog barks at strangers; my cat ignores everyone.').", "Stronger than a comma, softer than a period.", "Semicolon usage"),
    CardContentTuple("When should a Colon ( : ) be used?", "To introduce a list, an explanation, or a quotation (e.g., 'Please bring the following items: pen, ruler, and eraser.').", "Introduces what follows.", "Colon usage"),
    CardContentTuple("Explain Capitalization rules in English.", "Capitalize: First word of sentence, pronoun 'I', proper nouns (names, countries, languages, days, months), and main words in titles.", "Never capitalize ordinary common nouns in the middle of a sentence.", "Capitalization rules"),
    CardContentTuple("Correct the error: 'He gave me many advices.'", "'He gave me much advice' or 'many pieces of advice.' ('Advice' is an uncountable noun with no plural 'advices').", "Uncountable noun rule.", "Advice is uncountable"),
    CardContentTuple("Correct the error: 'I prefer tea than coffee.'", "'I prefer tea to coffee.' ('Prefer' takes the preposition 'to', never 'than').", "prefer ... to ...", "prefer X to Y"),
    CardContentTuple("Correct the error: 'She lives in Kandy since five years.'", "'She has lived in Kandy for five years.' (Duration takes 'for' + Present Perfect tense).", "for + duration", "has lived for 5 years"),
    CardContentTuple("What is a Dangling Participle and how to fix it?", "A modifying participle phrase whose implied subject does not match the grammatical subject of the main clause.", "Incorrect: 'Walking down the road, a brick fell on him.' Correct: 'While he was walking down the road, a brick fell on him.'", "Dangling participle fix"),
    CardContentTuple("Spell correctly: successfully, immediately, necessary, separate.", "Successfully (double c, double s, double l), Immediately (double m), Necessary (one c, double s), Separate (s-e-p-a-r-a-t-e, 'a' in the middle).", "Common exam orthography.", "Crucial spellings"),
    CardContentTuple("Correct the sentence: 'Between you and I, this is a secret.'", "'Between you and me, this is a secret.' (Preposition 'between' requires objective pronoun 'me', not 'I').", "Preposition + objective pronoun.", "Between you and me"),
    CardContentTuple("Correct: 'Everyone have done their work.'", "'Everyone has done his or her work.' ('Everyone/everybody' is grammatically singular and takes 'has').", "Singular indefinite pronouns.", "Everyone has"),
    CardContentTuple("What is the difference between 'Lie' (recline) and 'Lay' (put down)?", "'Lie' (past: lay, past participle: lain) is intransitive (to recline).\n'Lay' (past: laid, past participle: laid) is transitive and takes an object (lay the book on the table).", "Lie down vs Lay something down.", "Lie vs Lay"),
    CardContentTuple("Explain the punctuation of direct speech question inside quotes.", "Place the question mark INSIDE the quotation marks: \"Are you coming?\" he asked.", "Punctuation stays inside quotes.", "Quote punctuation")
  )

  // --- SET 15: O/L English Paper II Master Writing Tips & Essay Structure ---
  private val set15 = listOf(
    CardContentTuple("What are the 3 mandatory pillars of an O/L Essay (Question 16)?", "1. Introduction (hook + general topic background + thesis statement)\n2. Body Paragraphs (2 to 3 paragraphs, each developing ONE main point with supporting examples)\n3. Conclusion (rephrasing main thesis + final thought/message).", "Strict paragraphing is essential for coherence marks.", "Essay structure: 3 pillars"),
    CardContentTuple("What is the typical word count expected for O/L Essay (Question 16)?", "Between 150 and 200 words.", "Writing too little loses content marks; writing too much increases grammar error risk.", "150 - 200 words"),
    CardContentTuple("What is a 'Topic Sentence' in an essay body paragraph?", "The first sentence of the paragraph that states the single main idea to be discussed in that paragraph.", "Every supporting sentence in that paragraph must relate to the topic sentence.", "Topic sentence definition"),
    CardContentTuple("What are the 4 official marking criteria for O/L English Writing tasks?", "1. Content (relevance to prompt, covering all bullet points)\n2. Organization & Cohesion (paragraphing, flow, connectors)\n3. Language & Grammar (tense consistency, sentence structures)\n4. Vocabulary & Mechanics (spelling, punctuation, word choice).", "Evaluated out of 15 marks.", "4 marking criteria"),
    CardContentTuple("How can you ensure full marks in the 'Content' component?", "Carefully read all prompt guidelines or bullet points in the exam question and tick them off as you cover them in your essay.", "Never omit any suggested bullet point.", "Cover all prompt points"),
    CardContentTuple("What is 'Sentence Variety' and why does it boost language marks?", "Mixing simple, compound, and complex sentences rather than writing only short monotonous sentences.", "Demonstrates grammatical versatility.", "Sentence variety"),
    CardContentTuple("Write an engaging opening hook for an essay on 'The Importance of Protecting Trees'.", "\"Trees are often called the green lungs of our planet, quietly absorbing harmful pollutants and providing the breath of life.\"", "Vivid hook grabs reader interest.", "Essay hook sentence"),
    CardContentTuple("How do you write a balanced 'Advantages & Disadvantages' essay?", "Dedicate one body paragraph entirely to advantages (benefits) and the next body paragraph to disadvantages (drawbacks), using contrasting connectors.", "Structure ensures balance.", "Pros & Cons organization"),
    CardContentTuple("What transitional phrases connect paragraphs smoothly?", "Furthermore, On the other hand, In addition to this, Despite these challenges, Consequently.", "Builds cohesion across paragraphs.", "Paragraph transitions"),
    CardContentTuple("What should NEVER be included in an essay conclusion?", "Brand new arguments or facts that were never mentioned in the body paragraphs.", "Conclusion is only for summarizing.", "No new arguments in conclusion"),
    CardContentTuple("How should an article for a school magazine differ from a regular essay?", "An article must have an eye-catching Title, Byline ('By: [Your Name]'), an engaging journalistic style, and interactive questions to the reader.", "Magazines require catchy format.", "Magazine article format"),
    CardContentTuple("What are the essential elements of an O/L Speech script?", "1. Formal Greeting/Salutation to the audience ('Respected Principal, teachers, and dear friends,')\n2. Topic introduction\n3. Body points with rhetorical markers\n4. Inspiring conclusion + 'Thank you.'", "Always begin with greeting and end with 'Thank you.'", "Speech script structure"),
    CardContentTuple("Write a model opening salutation for an English Day Speech.", "\"Good morning, respected Principal, devoted teachers, and my fellow schoolmates. Today, I stand before you to speak on the topic...\"", "Polite and oratorical.", "Speech opening salutation"),
    CardContentTuple("How do you manage exam time effectively for Paper II?", "Spend 10 mins on Section A (notices/notes), 25 mins on Comprehension, 15 mins on Chart description, and 30 mins on Question 16 (Essay), leaving 10 mins for proofreading.", "Strict time budgeting prevents panic.", "Exam time management"),
    CardContentTuple("Why is proofreading the last 5-10 minutes crucial in Paper II?", "It catches careless errors in subject-verb agreement, missing articles, punctuation, and misspelled words that cost vital grade boundaries.", "Eliminates silly mistakes.", "Proofreading value"),
    CardContentTuple("How do you brainstorm ideas before writing an essay?", "Create a 2-minute mind map or jot down 4-5 bullet points covering causes, effects, solutions, and personal responsibility.", "Planning prevents getting stuck.", "Pre-writing mind map"),
    CardContentTuple("What is the danger of writing memorized 'canned' essays?", "If the essay does not directly address the specific prompt given in the exam paper, examiners award 0 marks for irrelevant content.", "Always adapt content to the prompt.", "Avoid canned essays"),
    CardContentTuple("How do you make an informal letter lively and engaging?", "Ask about the friend's wellbeing, use natural conversational language, share vivid feelings, and express anticipation of meeting.", "Warm and personal tone.", "Informal letter style"),
    CardContentTuple("Write a concluding sentence for a speech inspiring peers to conserve water.", "\"Let us remember that every drop saved today is a promise of life for tomorrow. Thank you very much for your patient listening.\"", "Inspiring closing call.", "Speech closing call"),
    CardContentTuple("What is the rule regarding handwriting and layout cleanliness?", "Neat handwriting, generous margins, clearly separated paragraphs, and minimal crossed-out words create an immediate positive impression on examiners.", "Visual presentation matters.", "Clean handwriting & layout")
  )

  // --- SET 16: Phrasal Verbs & Idiomatic Expressions ---
  private val set16 = listOf(
    CardContentTuple("What is a phrasal verb?", "A verb combined with a preposition or adverb that creates a unique idiomatic meaning different from the original verb (e.g., look + after = take care of).", "Very common in O/L reading and writing.", "Phrasal verb definition"),
    CardContentTuple("What do these 'LOOK' phrasal verbs mean: look after, look for, look forward to, look up to?", "look after: take care of\nlook for: search for\nlook forward to: eagerly anticipate\nlook up to: admire / respect.", "Notice each preposition completely changes the meaning.", "Look phrasal verbs"),
    CardContentTuple("What do these 'GIVE' phrasal verbs mean: give up, give in, give away?", "give up: stop trying / quit\ngive in: surrender / yield\ngive away: distribute free or reveal a secret.", "Crucial O/L phrasal verbs.", "Give phrasal verbs"),
    CardContentTuple("What do these 'BREAK' phrasal verbs mean: break down, break into, break out?", "break down: stop functioning (machine) or lose emotional control\nbreak into: enter a building illegally by force\nbreak out: start suddenly (war, fire, epidemic).", "Frequently tested in comprehension.", "Break phrasal verbs"),
    CardContentTuple("What do these 'CALL' phrasal verbs mean: call off, call on, call up?", "call off: cancel an event\ncall on: visit someone briefly\ncall up: telephone someone.", "Example: 'The cricket match was called off due to heavy rain.'", "Call phrasal verbs"),
    CardContentTuple("What do these 'PUT' phrasal verbs mean: put off, put out, put up with?", "put off: postpone / delay\nput out: extinguish (a fire/candle)\nput up with: tolerate / endure.", "Example: 'Firefighters put out the fire quickly.'", "Put phrasal verbs"),
    CardContentTuple("What do these 'CARRY' phrasal verbs mean: carry on, carry out?", "carry on: continue doing something\ncarry out: perform or execute (an experiment, an order, research).", "Scientists carry out research.", "Carry phrasal verbs"),
    CardContentTuple("What do these 'RUN' phrasal verbs mean: run out of, run into, run over?", "run out of: deplete the entire supply of something\nrun into: meet someone unexpectedly\nrun over: hit with a vehicle.", "We have run out of sugar.", "Run phrasal verbs"),
    CardContentTuple("What do these 'TURN' phrasal verbs mean: turn down, turn up, turn into?", "turn down: reject an offer or lower the volume\nturn up: arrive unexpectedly or increase volume\nturn into: transform into.", "He turned down the job offer.", "Turn phrasal verbs"),
    CardContentTuple("What does 'take after' mean?", "To resemble a parent or older relative in appearance or character.", "Kamal takes after his father in artistic talent.", "take after = resemble"),
    CardContentTuple("What does 'bring up' mean?", "To raise and educate a child, or to mention a topic for discussion.", "She was brought up by her grandmother.", "bring up = raise / mention"),
    CardContentTuple("What does the idiom 'kill two birds with one stone' mean?", "To accomplish two different things with a single action.", "Achieve dual goals.", "kill two birds with one stone"),
    CardContentTuple("What does the idiom 'spill the beans' mean?", "To reveal a secret prematurely or indiscreetly.", "Who spilled the beans about the surprise party?", "spill the beans = reveal secret"),
    CardContentTuple("What does the idiom 'through thick and thin' mean?", "Under all circumstances, no matter how difficult or challenging.", "True friends support each other through thick and thin.", "through thick and thin"),
    CardContentTuple("What does the idiom 'a blessing in disguise' mean?", "An apparent misfortune that eventually results in good or beneficial outcome.", "Missing the bus was a blessing in disguise.", "blessing in disguise"),
    CardContentTuple("What does 'bite the bullet' mean?", "To face a difficult situation with courage and fortitude.", "Face hardship bravely.", "bite the bullet"),
    CardContentTuple("What does 'see eye to eye' mean?", "To agree fully with someone.", "They rarely see eye to eye on politics.", "see eye to eye = agree"),
    CardContentTuple("What does 'under the weather' mean?", "Feeling slightly sick, unwell, or tired.", "I am feeling a bit under the weather today.", "under the weather = sick"),
    CardContentTuple("What does 'cost an arm and a leg' mean?", "To be extremely expensive.", "That luxury sports car costs an arm and a leg.", "cost an arm and a leg"),
    CardContentTuple("What does 'cross your fingers' mean?", "To hope for good luck or a favorable outcome.", "Fingers crossed for your exam results!", "cross fingers = wish luck")
  )

  // --- SET 17: Subject-Verb Agreement Rules ---
  private val set17 = listOf(
    CardContentTuple("What is the golden rule of Subject-Verb Agreement?", "A singular subject takes a singular verb; a plural subject takes a plural verb.", "He plays (singular); They play (plural).", "Singular with singular, plural with plural"),
    CardContentTuple("What verb form follows subjects joined by 'and'?", "Plural verb (e.g., 'Kamal and Nimal are good athletes.').", "Compound subject with 'and' is plural.", "Compound subject = plural"),
    CardContentTuple("What happens when two nouns joined by 'and' represent a single unified concept or food?", "They take a SINGULAR verb (e.g., 'Rice and curry is our staple food.', 'Bread and butter is his daily breakfast.').", "Single combined concept.", "Rice and curry is..."),
    CardContentTuple("What verb form follows: each, every, everyone, everybody, nobody, someone?", "SINGULAR verb (e.g., 'Each of the boys was rewarded.', 'Everybody has a responsibility.').", "Treated as singular individuals.", "Indefinite pronouns take singular"),
    CardContentTuple("What is the rule when subjects are connected by 'as well as', 'together with', 'along with', or 'in addition to'?", "The verb agrees with the FIRST subject, ignoring parenthetical phrases.", "Example: 'The teacher, along with his students, was present.'", "Agreement with first subject"),
    CardContentTuple("What is the rule for collective nouns like 'team', 'family', 'committee', 'class'?", "Singular when viewed as a single collective unit; plural when individual members act separately.", "Example: 'Our team is winning the championship.'", "Collective nouns = usually singular"),
    CardContentTuple("What verb form follows nouns plural in form but singular in meaning (e.g., news, physics, mathematics, measles)?", "SINGULAR verb (e.g., 'The news is shocking.', 'Mathematics is an intriguing subject.').", "Form is plural, but meaning is singular.", "Singular academic nouns"),
    CardContentTuple("What verb form follows nouns that consist of two parts (e.g., scissors, trousers, glasses, binoculars)?", "PLURAL verb (e.g., 'My glasses are on the table.'); but SINGULAR with 'a pair of' ('A pair of scissors is on the desk.').", "Two-part objects.", "Scissors are / A pair of scissors is"),
    CardContentTuple("What verb form is used with expressions of time, distance, and money as a whole quantity?", "SINGULAR verb (e.g., 'Ten kilometers is a long distance to walk.', 'Five thousand rupees is too much.').", "Regarded as a single collective unit.", "Units of quantity = singular"),
    CardContentTuple("What verb follows 'None of' + plural noun in formal English?", "Singular verb is formally preferred ('None of the answers is correct'), though plural is accepted informally.", "Formal O/L preference: singular.", "None of ... is"),
    CardContentTuple("What verb follows 'A number of students' vs 'The number of students'?", "'A number of students are absent' (plural = many).\n'The number of students is fifty' (singular = the specific figure).", "Crucial contrast in O/L papers.", "A number (pl) vs The number (sg)"),
    CardContentTuple("What is the rule for 'either ... or' and 'neither ... nor'?", "The verb agrees with the subject closest to it (proximity rule).", "Example: 'Neither the captain nor the players were satisfied.'", "Proximity agreement"),
    CardContentTuple("Choose the correct verb: 'Neither Kamal nor his brothers (was / were) present.'", "were (agrees with the closer plural subject 'brothers').", "Proximity rule applied.", "Neither ... were"),
    CardContentTuple("Choose the correct verb: 'Neither his brothers nor Kamal (was / were) present.'", "was (agrees with the closer singular subject 'Kamal').", "Proximity rule applied.", "Neither ... was"),
    CardContentTuple("Choose the correct verb: 'There (is / are) several reasons for climate change.'", "are (the real subject 'several reasons' follows the introductory 'there').", "Introductory 'there' inversion.", "There are several reasons"),
    CardContentTuple("Choose the correct verb: 'The quality of these mangoes (is / are) exceptional.'", "is (the subject is the singular noun 'quality', not the prepositional object 'mangoes').", "Watch out for prepositional phrases.", "The quality ... is"),
    CardContentTuple("Choose the correct verb: 'Many a student (has / have) failed this test.'", "has ('Many a' is followed by a singular noun and singular verb).", "Archaic/formal singular idiom.", "Many a student has"),
    CardContentTuple("Choose the correct verb: 'All of the water (was / were) contaminated.'", "was ('water' is uncountable, so 'all of the water' takes a singular verb).", "Uncountable with quantifiers.", "All of the water was"),
    CardContentTuple("Choose the correct verb: 'All of the books (was / were) sold.'", "were ('books' is countable plural, so 'all of the books' takes a plural verb).", "Countable plural with quantifiers.", "All of the books were"),
    CardContentTuple("Correct the sentence: 'Politics are a dirty game.'", "'Politics is a dirty game.' ('Politics' as a field of study or activity is singular).", "Singular noun in -s.", "Politics is")
  )

  // --- SET 18: Articles (A, An, The) & Determiners ---
  private val set18 = listOf(
    CardContentTuple("What are the Indefinite Articles and when are they used?", "'a' and 'an'. Used before singular, countable nouns mentioned for the first time or non-specific.", "'a' before consonant sounds; 'an' before vowel sounds.", "Indefinite: a / an"),
    CardContentTuple("Why do we say 'a university' and 'a European country' instead of 'an'?", "Because 'university' and 'European' begin with the consonant sound /j/ (like 'you'), not a vowel sound.", "Based on SOUND, not letter spelling.", "Sound rule: a university"),
    CardContentTuple("Why do we say 'an honest man' and 'an hour' instead of 'a'?", "Because the 'h' is silent, so the word begins with a vowel sound /ɒ/ or /aʊ/.", "Silent 'h' takes 'an'.", "Silent 'h': an hour"),
    CardContentTuple("What is the Definite Article and when is it used?", "'The'. Used when the noun is specific, already mentioned, unique, or known to both speaker and listener.", "Example: 'I saw a dog. The dog was barking.'", "Definite article: The"),
    CardContentTuple("When is 'The' mandatory with geographical names?", "With mountain ranges (the Himalayas), oceans/seas (the Indian Ocean), rivers (the Mahaweli), island groups (the Maldives), and countries with plural/political words (the USA, the UK, the Netherlands).", "Never use 'the' with single mountains (Mt. Everest) or single lakes.", "Geographical uses of 'The'"),
    CardContentTuple("When is 'The' used with superlative adjectives?", "Always before superlatives (e.g., 'the tallest boy', 'the most beautiful flower').", "Superlative requires 'the'.", "The + superlative"),
    CardContentTuple("When do we OMIT articles (Zero Article)?", "Before proper names, sports (play cricket), meals (have lunch), languages (speak English), abstract nouns in general sense, and plural nouns in general.", "Example: 'Gold is a precious metal.'", "Zero article rules"),
    CardContentTuple("Explain the difference between 'a few' and 'few'.", "'a few' has a positive meaning (some, a small number). 'few' has a negative meaning (almost none, hardly any).", "Example: 'I have a few friends' (happy) vs 'I have few friends' (lonely).", "a few (positive) vs few (negative)"),
    CardContentTuple("Explain the difference between 'a little' and 'little'.", "'a little' has a positive meaning (some, a small quantity). 'little' has a negative meaning (almost none, scarce).", "Used with uncountable nouns.", "a little vs little"),
    CardContentTuple("When do we use 'some' and 'any'?", "'some' in positive statements and polite offers/requests ('I have some apples', 'Would you like some tea?').\n'any' in negative statements and general questions ('I don't have any money', 'Do you have any questions?').", "Basic determiner rule.", "some (+) vs any (-/?)"),
    CardContentTuple("Explain 'much' vs 'many'.", "'much' is used with uncountable nouns (much water, much time).\n'many' is used with countable plural nouns (many books, many students).", "Quantity vs Count.", "much (uncountable) vs many (countable)"),
    CardContentTuple("Fill in: 'Sri Lanka is ________ island in ________ Indian Ocean.'", "an, the", "'island' starts with vowel sound; 'Indian Ocean' is an ocean.", "an island, the Indian Ocean"),
    CardContentTuple("Fill in: 'He is ________ MLA and his brother is ________ engineer.'", "an, an", "'MLA' starts with vowel sound /em/; 'engineer' starts with /e/.", "Acronyms & vowel sounds"),
    CardContentTuple("Fill in: '________ sun rises in ________ east.'", "The, the", "Unique celestial bodies and cardinal directions take 'the'.", "The sun, the east"),
    CardContentTuple("Correct the sentence: 'He plays the cricket very well.'", "'He plays cricket very well.' (Names of sports and games take no article).", "No article for sports.", "play cricket (no 'the')"),
    CardContentTuple("Explain the difference: 'go to school' vs 'go to the school'.", "'go to school' means going as a student for education (primary purpose).\n'go to the school' means visiting the building for another purpose (e.g., parent meeting).", "Primary purpose vs physical building.", "Institution article rule"),
    CardContentTuple("Fill in: 'Could you give me ________ information about train times?'", "some / any", "'Information' is uncountable, so never say 'an information'.", "some information"),
    CardContentTuple("Fill in: 'There is ________ milk left in the bottle; it is almost empty.'", "little", "Negative sense (almost none) with uncountable noun.", "little milk"),
    CardContentTuple("Fill in: 'She has ________ friends who visit her regularly.'", "a few", "Positive sense (several/some) with countable noun.", "a few friends"),
    CardContentTuple("Correct the sentence: 'The English is a global language.'", "'English is a global language.' (Names of languages do not take 'the'. 'The English' means the people of England).", "Language vs nationality.", "English (no 'the')")
  )

  // --- SET 19: Adjectives, Adverbs & Degrees of Comparison ---
  private val set19 = listOf(
    CardContentTuple("What are the 3 degrees of comparison for adjectives?", "1. Positive Degree (base quality, e.g., tall)\n2. Comparative Degree (comparing two, e.g., taller)\n3. Superlative Degree (comparing three or more, e.g., tallest).", "Positive -> Comparative -> Superlative.", "3 degrees of comparison"),
    CardContentTuple("How are comparative and superlative forms made for 1-syllable adjectives?", "Add '-er' for comparative and '-est' for superlative (e.g., small -> smaller -> smallest).", "Regular short adjectives.", "-er and -est"),
    CardContentTuple("How are comparative and superlative forms made for adjectives of 2 or more syllables?", "Use 'more' for comparative and 'most' for superlative (e.g., beautiful -> more beautiful -> most beautiful).", "Long adjectives.", "more / most"),
    CardContentTuple("Give the irregular comparison of: good, bad, little, much/many, far.", "good -> better -> best\nbad -> worse -> worst\nlittle -> less -> least\nmuch/many -> more -> most\nfar -> farther/further -> farthest/furthest.", "Must be committed to memory.", "Irregular comparisons"),
    CardContentTuple("How do you form comparative equality using 'as ... as'?", "as + positive degree + as (e.g., 'Nimal is as tall as Kamal.').", "Indicates equal degree of quality.", "as ... as (equality)"),
    CardContentTuple("How do you express negative inequality using 'not as ... as'?", "not as / so + positive adjective + as (e.g., 'A bicycle is not as fast as a car.').", "Expresses disparity.", "not as ... as"),
    CardContentTuple("What preposition follows comparative adjectives?", "'than' (e.g., 'She is older than her sister.').", "Never write 'then' - always use 'than'.", "older than"),
    CardContentTuple("Which Latin comparative adjectives take 'to' instead of 'than'?", "Senior, junior, superior, inferior, prior, preferable.", "Example: 'He is senior to me in service.'", "Senior to / Superior to"),
    CardContentTuple("How do you form adverbs of manner from adjectives?", "Add '-ly' to the adjective (e.g., quick -> quickly, careful -> carefully, fluent -> fluently).", "Describes how an action is performed.", "Adjective + -ly = Adverb"),
    CardContentTuple("What adjectives and adverbs have the identical form?", "Fast, hard, late, early (e.g., 'a fast train' [adj] vs 'runs fast' [adv]).", "Never say 'fastly' - it is not an English word.", "fast, hard, late, early"),
    CardContentTuple("Explain the difference between 'hard' and 'hardly'.", "'hard' means with great effort/firm (e.g., 'He worked hard').\n'hardly' means almost not at all/scarcely (e.g., 'He hardly works' = he is lazy).", "Crucial semantic difference.", "hard vs hardly"),
    CardContentTuple("Explain the difference between 'late' and 'lately'.", "'late' means not on time / after expected hour.\n'lately' means recently / in recent times.", "He arrived late vs I haven't seen him lately.", "late vs lately"),
    CardContentTuple("Correct the sentence: 'Of the two brothers, Sunil is the tallest.'", "'Of the two brothers, Sunil is the taller.' (When comparing only TWO, use comparative degree, not superlative).", "Two items = comparative.", "the taller of the two"),
    CardContentTuple("Explain double comparatives for progressive increase.", "'The + comparative ..., the + comparative ...' (e.g., 'The higher you go, the cooler it becomes.').", "Parallel proportionate change.", "The higher, the cooler"),
    CardContentTuple("Correct the sentence: 'He speaks English very good.'", "'He speaks English very well.' ('Good' is an adjective; the action of speaking requires the adverb 'well').", "good (adj) vs well (adv)", "speaks well"),
    CardContentTuple("What order of adjectives is followed before a noun?", "Opinion -> Size -> Age -> Shape -> Color -> Origin -> Material -> Purpose (OSASCOMP).", "Example: 'A lovely small ancient rectangular brown wooden tea table.'", "OSASCOMP adjective order"),
    CardContentTuple("Fill in: 'Platinum is ________ (expensive) than gold.'", "more expensive", "Multi-syllable comparative takes 'more'.", "more expensive than"),
    CardContentTuple("Fill in: 'Mount Everest is the ________ (high) peak in the world.'", "highest", "Superlative of one-syllable adjective takes '-est'.", "the highest peak"),
    CardContentTuple("Correct the sentence: 'She is more cleverer than her sister.'", "'She is cleverer than her sister.' (Avoid double comparatives: never use 'more' with '-er').", "No double comparatives.", "cleverer (not more cleverer)"),
    CardContentTuple("Where do adverbs of frequency usually stand in a sentence?", "Before the main verb, but after the auxiliary/be verb (e.g., 'He always arrives early', 'She is never late').", "Frequency adverb position.", "Position of frequency adverbs")
  )

  // --- SET 20: Reading Comprehension Techniques & Context Clues ---
  private val set20 = listOf(
    CardContentTuple("What is 'Skimming' in reading comprehension?", "Reading quickly through a text to get the general overview, central idea, or gist without focusing on details.", "Run eyes over headings, first and last sentences.", "Skimming = general gist"),
    CardContentTuple("What is 'Scanning' in reading comprehension?", "Looking through a text rapidly to locate specific information such as a name, date, statistic, or keyword.", "Move eyes like a searchlight searching for a specific word.", "Scanning = specific facts"),
    CardContentTuple("What are 'Context Clues' and how do they help with unfamiliar words?", "Hints in the surrounding words, phrases, and sentences (synonyms, antonyms, examples) that help deduce the unknown word's meaning.", "Never panic over unknown words; read before and after.", "Context clues deduction"),
    CardContentTuple("What are Pronoun Reference questions (e.g., 'What does 'it' in line 12 refer to?')?", "Questions testing understanding of pronoun antecedents. Look at the preceding singular or plural noun in the previous clause.", "Check noun-pronoun agreement in number and gender.", "Pronoun reference tracking"),
    CardContentTuple("How should you answer direct Wh-questions in Comprehension?", "Answer in complete, grammatically correct sentences using information directly stated in the passage, avoiding irrelevant copy-pasting.", "Keep answers precise and to the point.", "Direct Wh-answers"),
    CardContentTuple("What is an 'Inference' question in reading tests?", "A question where the answer is not directly stated in words, but strongly implied by facts and clues in the passage.", "Read between the lines.", "Inference = reading between lines"),
    CardContentTuple("What is the difference between a 'Fact' and an 'Opinion'?", "A Fact is an objective reality that can be proven true with evidence. An Opinion is a personal belief, viewpoint, or judgment.", "Look for emotive words signaling opinion.", "Fact vs Opinion"),
    CardContentTuple("How should 'True / False / Not Given' statements be verified?", "TRUE if passage directly confirms it; FALSE if passage contradicts it; NOT GIVEN if passage contains no information about it.", "Do not assume external knowledge.", "True / False / Not Given"),
    CardContentTuple("What is the strategy for answering 'Find a word in paragraph 3 that means...'?", "Identify the target word's part of speech (noun, verb, adjective) and substitute candidates back into the question sentence to verify meaning.", "Match grammatical form.", "Vocabulary in context"),
    CardContentTuple("Why is reading the questions BEFORE reading the passage a recommended strategy?", "It primes your mind with keywords and focus areas, turning reading from passive consumption into active search.", "Pre-reading questions saves time.", "Read questions first"),
    CardContentTuple("How can heading-matching tasks be solved efficiently?", "Read the topic sentence (first sentence) and concluding sentence of each paragraph, which usually state the main theme.", "Identify paragraph topic.", "Matching headings"),
    CardContentTuple("What is the danger of lifting entire long paragraphs as an answer?", "Examiners penalize verbatim copying when only a single clause was asked; it shows lack of comprehension.", "Extract only the needed phrase.", "Avoid verbatim copying"),
    CardContentTuple("Explain how transition words like 'However', 'Therefore', 'Consequently' guide text navigation.", "'However' signals a change in direction or counter-argument; 'Therefore' signals a result; 'Consequently' signals an effect.", "Roadmaps of thought.", "Signpost words in reading"),
    CardContentTuple("What does the 'Tone' of a passage refer to?", "The author's emotional attitude towards the subject (e.g., critical, humorous, objective, sarcastic, enthusiastic).", "Deduced from adjective choices.", "Author's tone"),
    CardContentTuple("What is the author's 'Purpose' in informational passages?", "To inform, persuade, entertain, describe, or instruct the reader.", "Analyze why the text was written.", "Author's purpose"),
    CardContentTuple("How do you tackle multiple-choice comprehension questions?", "Eliminate options that are clearly contradicted, too extreme ('always', 'never'), or not mentioned, leaving the most accurate choice.", "Process of elimination.", "Elimination strategy"),
    CardContentTuple("What should you do when a comprehension question asks to write 'In your own words'?", "Paraphrase the passage information using synonyms and varied sentence structures without changing the original meaning.", "Paraphrase faithfully.", "In your own words"),
    CardContentTuple("How does identifying the main idea of each paragraph aid overall comprehension?", "It builds a mental outline of the passage structure, making it easy to return to the exact paragraph for specific questions.", "Mental text map.", "Paragraph summarization"),
    CardContentTuple("Why should you pay attention to qualifying words like 'some', 'often', 'mostly' vs 'all', 'always'?", "Extreme qualifiers often make a statement false; nuanced qualifiers maintain factual precision.", "Check quantifier accuracy.", "Extreme vs nuanced qualifiers"),
    CardContentTuple("What is the final check before submitting reading comprehension answers?", "Ensure spelling is copied accurately from the passage and that the answer directly fulfills the question's grammatical demand.", "Verify precision.", "Final answer verification")
  )
}
