package com.ubisoft.rabbidsrunner.zq.a1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.hjq.toast.Toaster
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
import com.bytedance.sdk.openadsdk.AdSlot
import com.bytedance.sdk.openadsdk.TTAdConfig
import com.bytedance.sdk.openadsdk.TTAdConstant
import com.bytedance.sdk.openadsdk.TTAdLoadType
import com.bytedance.sdk.openadsdk.TTAdNative
import com.bytedance.sdk.openadsdk.TTAdSdk
import com.bytedance.sdk.openadsdk.TTRewardVideoAd

class MainActivity : ComponentActivity() {

    private var mTTAdNative: TTAdNative? = null
    private var videoAd: TTRewardVideoAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var showWebView by remember { mutableStateOf(false) }

            if (showWebView) {
                // 用你想展示的网页URL替换“https://www.example.com”
                MyWebView(url = "https://m.weibo.cn/")
            } else {
                Button(onClick = {
                    initCSJ()
                }) {
                    Text("Open WebView")
                }
            }
        }
    }

    fun initCSJ() {
        val config = TTAdConfig.Builder()
            .appId("5518096") // 替换为你的穿山甲媒体平台注册的应用ID
            .useTextureView(true)
            .appName("忽地笑z1")
            .titleBarTheme(TTAdConstant.TITLE_BAR_THEME_DARK)
            .allowShowNotify(true)
            .debug(true)
            .directDownloadNetworkType(TTAdConstant.NETWORK_STATE_WIFI)
            .supportMultiProcess(false)
            // .asyncInit(true) // 从3.4.50版本开始废弃
            // .httpStack(MyOkStack3()) // 如果需要自定义网络库
            .build()
        TTAdSdk.init(this, config)
        TTAdSdk.start(object: TTAdSdk.Callback {
            override fun success() {
                showVideoAd()
                Toaster.show("穿山甲初始化成功")
            }

            override fun fail(p0: Int, p1: String?) {
                Toaster.show("穿山甲初始化失败")
                Log.d("csj", "初始化失败$p0 and $p1")
            }
        })
    }

    fun showVideoAd() {
        mTTAdNative = TTAdSdk.getAdManager().createAdNative(this)
        val adSlot = AdSlot.Builder()
            .setCodeId("956924575")
            .setAdLoadType(TTAdLoadType.LOAD)
            .build()
        mTTAdNative?.loadRewardVideoAd(adSlot, object: TTAdNative.RewardVideoAdListener {
            override fun onRewardVideoAdLoad(p0: TTRewardVideoAd?) {
                videoAd = p0
                showVideoAdAndListener()
            }

            override fun onRewardVideoCached() {
                Log.d("csj", "广告缓存了没参数")
            }

            override fun onRewardVideoCached(p0: TTRewardVideoAd?) {
                videoAd = p0
                showVideoAdAndListener()
            }

            override fun onError(p0: Int, p1: String?) {
                Toaster.show("广告请求失败 $p1")

                Log.d("csj", "请求广告失败: $p0  $p1")
            }
        })
    }

    fun showVideoAdAndListener() {
        if (videoAd == null) {
            Log.d("csj", "广告视频对象异常")
        }
        videoAd?.setRewardAdInteractionListener(object: TTRewardVideoAd.RewardAdInteractionListener {
            override fun onAdShow() {
                Log.d("csj", "广告视频对象展示了")
            }

            override fun onAdVideoBarClick() {
            }

            override fun onAdClose() {
            }

            override fun onVideoComplete() {
            }

            override fun onVideoError() {
                Toaster.show("广告展示失败")
                Log.d("csj", "广告视频对象出错")
            }

            override fun onRewardVerify(p0: Boolean, p1: Int, p2: String?, p3: Int, p4: String?) {
            }

            override fun onRewardArrived(p0: Boolean, p1: Int, p2: Bundle?) {
            }

            override fun onSkippedVideo() {

            }

        })
        videoAd?.showRewardVideoAd(this)
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

