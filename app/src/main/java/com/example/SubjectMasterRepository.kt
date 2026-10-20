package com.example

object SubjectMasterRepository {
  fun getCategoriesForSubject(subjectName: String?): List<SubjectMasterCategory> {
    if (subjectName == null) return getGeographyCategories()

    val lower = subjectName.lowercase()
    return when {
      subjectName.contains("භූගෝල") || lower.contains("geo") -> getGeographyCategories()
      subjectName.contains("විද්‍යාව") || lower.contains("science") -> SubjectMasterScience20x20.getCategories()
      subjectName.contains("ව්‍යාපාර") || subjectName.contains("ගිණුම්") || subjectName.contains("වාණිජ") || lower.contains("commerce") -> SubjectMasterCommerce20x20.getCategories()
      subjectName.contains("ඉතිහාස") || lower.contains("history") -> SubjectMasterHistory20x20.getCategories()
      subjectName.contains("ඉංග්‍රීසි") || lower.contains("english") -> SubjectMasterEnglish20x20.getCategories()
      subjectName.contains("සිංහල") || lower.contains("sinhala") -> SubjectMasterSinhala20x20.getCategories()
      subjectName.contains("බුද්ධ") || subjectName.contains("ආගම") || lower.contains("buddhism") -> SubjectMasterBuddhism20x20.getCategories()
      subjectName.contains("ගණිත") || lower.contains("math") -> SubjectMasterMaths20x20.getCategories()
      subjectName.contains("පුරවැසි") || lower.contains("civic") -> SubjectMasterCivics20x20.getCategories()
      subjectName.contains("තොරතුරු") || lower.contains("ict") -> SubjectMasterOtherSubjects20x20.getIctCategories()
      subjectName.contains("නර්තන") || lower.contains("dance") -> SubjectMasterOtherSubjects20x20.getDancingCategories()
      else -> getGeographyCategories()
    }
  }

  fun getGeographyCategories(): List<SubjectMasterCategory> {
    return SubjectMasterGeography20x20.getCategories() +
        SubjectMasterGeographyPart2.getCategories() +
        SubjectMasterGeographyPart3.getCategories() +
        SubjectMasterGeographyPart4.getCategories()
  }
}
