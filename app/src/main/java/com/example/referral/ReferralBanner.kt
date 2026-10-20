package com.example.referral

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun ReferralBanner(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var progress by remember { mutableStateOf(0.0) }

    LaunchedEffect(Unit) {
        android.util.Log.d("ReferralBanner", "ReferralBanner rendered")
        progress = ReferralManager.getProgress(context)
    }
    
    fun shareToApp(packageName: String, appName: String, isGroup: Boolean) {
        val shareText = """
            අ.පො.ස. සාමාන්‍ය පෙළ (10 සහ 11 වසර) හදාරන දූදරුවන්ටම විශේෂයි! 📚✨

            ඔන්න ඔබේ විභාග සාර්ථක කරගන්න අවශ්‍ය සියලුම පහසුකම් දැන් එකම ඇප් එකකින්!

            පහත විශේෂාංග රැසක් මෙහි අන්තර්ගත වේ:

            10 සහ 11 ශ්‍රේණි සඳහා අදාළ සියලුම විෂයන්ගේ කෙටි සටහන් කට්ටල 📖

            සියලුම විෂයන්ගේ වාර විභාග ප්‍රශ්න පත්‍ර සහ වාර විභාග අනුමාන ප්‍රශ්න පත්‍ර 500ක් (සජීවීව උත්තර දී ප්‍රතිඵල බලාගත හැක) 📝

            සා/පෙළ පසුගිය ප්‍රශ්න පත්‍ර හා අනුමාන ප්‍රශ්න පත්‍ර 🎯

            සෑම විෂයකම පාඩමින් පාඩමට ප්‍රශ්න කාණ්ඩ සහ ක්විස් (Quizzes) ආකාරයේ ප්‍රශ්න 20,000ක් ❓

            කෙටි ප්‍රශ්න හා සත්‍ය/අසත්‍ය ප්‍රකාශන ප්‍රශ්න 3,000ක් 💡

            සවන් දිය හැකි ආකාරයේ වොයිස් (Voice) ආශ්‍රිත පාඩම් 300ක් 🎧

            සෑම විෂයකටම විශේෂාංග කොටසක් සහ ගණිතය, විද්‍යාව, ඉතිහාසය විෂයන් සඳහා විශේෂ පාඩම් මාලා, මතක තබා ගැනීමේ ක්‍රමවේද හා සිතියම් 🗺️

            ඉංග්‍රීසි භාෂාව මුල සිටම උගන්වන විශේෂ පංති විශේෂාංග 🇬🇧

            මේ සියල්ල දැන්ම ලබාගෙන ඔබේ විභාග ජයග්‍රහණය තහවුරු කරගන්න!
            🎁👇
            https://github.com/prabathakila450/OL-Guru/releases/latest
        """.trimIndent()
        
        try {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareText)
                setPackage(packageName)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareText)
            }
            context.startActivity(Intent.createChooser(intent, "Share via $appName"))
        }

        scope.launch {
            ReferralManager.addShare(context, isGroup)
            progress = ReferralManager.getProgress(context)
        }
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("🎁", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "ශෙයා කර රු.999 රීලෝඩ් දිනාගන්න... (${String.format("%.1f", progress)}%)",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF34D399)
                    )
                }
                Text(
                    text = "රු. 999 රීලෝඩ්",
                    fontSize = 10.sp,
                    color = Color(0xFF94A3B8)
                )
            }
            
            LinearProgressIndicator(
                progress = { (progress / 100.0).toFloat().coerceIn(0f, 1f) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .padding(vertical = 6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = Color(0xFF10B981),
                trackColor = Color(0xFF334155)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // WhatsApp Button (Green)
                Button(
                    onClick = { shareToApp("com.whatsapp", "WhatsApp", true) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                    modifier = Modifier.weight(1f).height(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "WhatsApp",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "WhatsApp",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                // Telegram Button (Blue)
                Button(
                    onClick = { shareToApp("org.telegram.messenger", "Telegram", false) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF229ED9)),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                    modifier = Modifier.weight(1f).height(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Telegram",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Telegram",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}
