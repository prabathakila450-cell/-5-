package com.example

object SubjectMasterOtherSubjects20x20 {
  fun getIctCategories(): List<SubjectMasterCategory> {
    return SubjectMasterIctFull.getCategories()
  }

  fun getDancingCategories(): List<SubjectMasterCategory> {
    return SubjectMasterDancingFull.getCategories()
  }
}

