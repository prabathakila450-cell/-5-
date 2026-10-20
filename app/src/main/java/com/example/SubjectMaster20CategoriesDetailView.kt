package com.example

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SubjectMaster20CategoriesDetailView(
  subjectName: String,
  categories: List<SubjectMasterCategory>,
  selectedCategoryNumber: Int, // 0 = ALL, 1..20 = specific category
  onSelectCategory: (Int) -> Unit,
  onOpenInteractiveTool: (() -> Unit)? = null,
  modifier: Modifier = Modifier
) {
  var searchQuery by remember { mutableStateOf("") }
  var showOnlyBookmarked by remember { mutableStateOf(false) }
  val bookmarkedPointIds = remember { mutableStateListOf<String>() }
  val context = LocalContext.current
  val clipboardManager = LocalClipboardManager.current

  // Filter categories to display
  val displayedCategories = remember(selectedCategoryNumber, categories) {
    if (selectedCategoryNumber in 1..categories.size) {
      listOf(categories[selectedCategoryNumber - 1])
    } else {
      categories
    }
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 14.dp, vertical = 10.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // Top Overview Banner
    item {
      Surface(
        shape = RoundedCornerShape(14.dp),
        color = Color(0xFF064E3B),
        border = BorderStroke(1.dp, Color(0xFF10B981)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text("📚", fontSize = 20.sp)
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = "$subjectName නිල කරුණු බැංකුව",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD1FAE5)
              )
            }

            Surface(
              shape = RoundedCornerShape(12.dp),
              color = Color(0xFF047857)
            ) {
              Text(
                text = "කාණ්ඩ 20 • කරුණු 400",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "සාමාන්‍ය පෙළ විභාග ප්‍රශ්න පත්‍ර I & II සඳහා 100% ක් නිවැරදි විෂය නිර්දේශගත කරුණු විවරණය.",
            fontSize = 11.5.sp,
            color = Color(0xFFA7F3D0)
          )

          if (onOpenInteractiveTool != null) {
            Spacer(modifier = Modifier.height(10.dp))
            Button(
              onClick = onOpenInteractiveTool,
              colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF059669)),
              shape = RoundedCornerShape(8.dp),
              contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
              modifier = Modifier.testTag("open_interactive_tool_btn")
            ) {
              Text("🌟 අන්තර්ක්‍රියාකාරී සිතියම්/මෙවලම් බලන්න", fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
            }
          }
        }
      }
    }

    // Search and Filter Bar
    item {
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(10.dp)) {
          OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("කරුණු 400 අතර සොයන්න...", fontSize = 12.sp, color = Color(0xFF94A3B8)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = Color(0xFF34D399)) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
              focusedTextColor = Color.White,
              unfocusedTextColor = Color.White,
              focusedBorderColor = Color(0xFF10B981),
              unfocusedBorderColor = Color(0xFF334155)
            ),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("fact_search_input")
          )

          Spacer(modifier = Modifier.height(8.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            FilterChip(
              selected = showOnlyBookmarked,
              onClick = { showOnlyBookmarked = !showOnlyBookmarked },
              label = {
                Text(
                  "⭐ සුරැකි කරුණු (${bookmarkedPointIds.size})",
                  fontSize = 11.sp,
                  color = if (showOnlyBookmarked) Color.Black else Color(0xFFFDE047)
                )
              },
              colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color(0xFFFDE047),
                containerColor = Color(0xFF0F172A)
              ),
              modifier = Modifier.testTag("filter_bookmarked_chip")
            )

            if (selectedCategoryNumber != 0) {
              TextButton(
                onClick = { onSelectCategory(0) },
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
              ) {
                Text("🌐 සියලු කාණ්ඩ 20 පෙන්වන්න", fontSize = 11.sp, color = Color(0xFF38BDF8))
              }
            }
          }
        }
      }
    }

    // Render Categories & Their 20 Facts
    displayedCategories.forEach { category ->
      val filteredPoints = category.points.filter { pt ->
        val pointKey = "${category.id}_${pt.number}"
        val matchesBookmark = !showOnlyBookmarked || bookmarkedPointIds.contains(pointKey)
        val matchesQuery = searchQuery.isBlank() ||
          pt.title.contains(searchQuery, ignoreCase = true) ||
          pt.detail.contains(searchQuery, ignoreCase = true) ||
          pt.examHighlight.contains(searchQuery, ignoreCase = true)
        matchesBookmark && matchesQuery
      }

      if (filteredPoints.isNotEmpty()) {
        // Category Header Card
        item(key = "header_${category.id}") {
          Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFF1E293B),
            border = BorderStroke(1.2.dp, category.color),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
              ) {
                Box(
                  modifier = Modifier
                    .size(36.dp)
                    .background(category.color.copy(alpha = 0.2f), CircleShape),
                  contentAlignment = Alignment.Center
                ) {
                  Text(category.icon, fontSize = 18.sp)
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                      shape = RoundedCornerShape(6.dp),
                      color = category.color
                    ) {
                      Text(
                        text = "කාණ්ඩය ${category.categoryNumber}",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                      )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                      text = "${category.points.size} කරුණු",
                      fontSize = 10.sp,
                      color = Color(0xFF94A3B8)
                    )
                  }
                  Spacer(modifier = Modifier.height(2.dp))
                  Text(
                    text = category.titleSinhala,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                  )
                }
              }
            }
          }
        }

        // 20 Individual Fact Cards
        items(filteredPoints, key = { "${category.id}_${it.number}" }) { point ->
          val pointKey = "${category.id}_${point.number}"
          val isBookmarked = bookmarkedPointIds.contains(pointKey)

          Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
            border = BorderStroke(1.dp, if (isBookmarked) Color(0xFFEAB308) else Color(0xFF334155)),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("fact_item_${point.number}")
          ) {
            Column(modifier = Modifier.padding(12.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier.weight(1f)
                ) {
                  Box(
                    modifier = Modifier
                      .size(24.dp)
                      .background(Color(0xFF1E293B), CircleShape),
                    contentAlignment = Alignment.Center
                  ) {
                    Text(
                      text = "${point.number}",
                      fontSize = 11.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color(0xFF34D399)
                    )
                  }
                  Spacer(modifier = Modifier.width(8.dp))
                  Text(
                    text = point.title,
                    fontSize = 12.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF1F5F9)
                  )
                }

                Row {
                  IconButton(
                    onClick = {
                      if (isBookmarked) {
                        bookmarkedPointIds.remove(pointKey)
                      } else {
                        bookmarkedPointIds.add(pointKey)
                        Toast.makeText(context, "කරුණ සුරකින ලදී ⭐", Toast.LENGTH_SHORT).show()
                      }
                    },
                    modifier = Modifier.size(28.dp)
                  ) {
                    Icon(
                      imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                      contentDescription = "Bookmark",
                      tint = if (isBookmarked) Color(0xFFFACC15) else Color(0xFF64748B),
                      modifier = Modifier.size(18.dp)
                    )
                  }

                  IconButton(
                    onClick = {
                      if (AppSecurityManager.canCopyContent(context)) {
                        clipboardManager.setText(AnnotatedString("${point.title}\n${point.detail}\n💡 ${point.examHighlight}"))
                        Toast.makeText(context, "පිටපත් කරගන්නා ලදී (Copied)", Toast.LENGTH_SHORT).show()
                      } else {
                        Toast.makeText(context, "ආරක්ෂක නීති අනුව සටහන් පිටපත් කිරීම (Copy) අවහිර කර ඇත (Admin Only).", Toast.LENGTH_SHORT).show()
                      }
                    },
                    modifier = Modifier.size(28.dp)
                  ) {
                    Icon(
                      imageVector = Icons.Default.ContentCopy,
                      contentDescription = "Copy",
                      tint = Color(0xFF64748B),
                      modifier = Modifier.size(16.dp)
                    )
                  }
                }
              }

              Spacer(modifier = Modifier.height(6.dp))
              Text(
                text = point.detail,
                fontSize = 11.5.sp,
                color = Color(0xFFCBD5E1),
                lineHeight = 16.5.sp
              )

              if (point.examHighlight.isNotBlank()) {
                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                  shape = RoundedCornerShape(6.dp),
                  color = Color(0xFF1E293B)
                ) {
                  Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                  ) {
                    Text(
                      text = "💡 ${point.examHighlight}",
                      fontSize = 10.5.sp,
                      fontWeight = FontWeight.Medium,
                      color = Color(0xFFFDE047),
                      lineHeight = 15.sp
                    )
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}
