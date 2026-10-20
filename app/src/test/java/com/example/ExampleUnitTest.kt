package com.example

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
  @Test
  fun testFlashcards() {
    val grades = listOf("9", "10", "11")
    val subjects = ComprehensiveFlashcardRepository.availableSubjects
    var emptyCount = 0
    for (g in grades) {
      for (sub in subjects) {
        val cards = ComprehensiveFlashcardRepository.getFlashcards(g, sub, null)
        for (c in cards) {
          if (c.questionFront.isBlank() || c.answerBack.isBlank()) {
            println("EMPTY CARD FOUND: Grade $g, Sub $sub, Set ${c.setNumber}, Index ${c.cardIndexInSet}")
            emptyCount++
          }
        }
      }
    }
    println(">>> Done checking all cards! Total empty cards: $emptyCount")
    assertEquals(0, emptyCount)
  }
}
