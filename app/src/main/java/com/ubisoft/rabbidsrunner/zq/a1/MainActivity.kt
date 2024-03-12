package com.ubisoft.rabbidsrunner.zq.a1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ubisoft.rabbidsrunner.zq.a1.ui.theme.TuziFirstTheme
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var showWebView by remember { mutableStateOf(false) }

            if (showWebView) {
                // 用你想展示的网页URL替换“https://www.example.com”
                MyWebView(url = "https://m.weibo.cn/")
            } else {
                Button(onClick = { showWebView = true }) {
                    Text("Open WebView")
                }
            }
        }
    }
}

@Composable
fun MyWebView(url: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    })
}

