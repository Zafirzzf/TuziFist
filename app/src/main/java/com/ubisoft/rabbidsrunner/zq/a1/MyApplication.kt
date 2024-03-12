package com.ubisoft.rabbidsrunner.zq.a1

/**
 * Created by zzf on 2024/3/11 .
 */
import android.util.Log
import android.app.Application

import com.bytedance.sdk.openadsdk.TTAdConfig
import com.bytedance.sdk.openadsdk.TTAdSdk
import com.bytedance.sdk.openadsdk.TTAdConstant
import kotlinx.coroutines.delay
import java.util.Timer
import kotlin.concurrent.schedule


class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timer().schedule(5000) {
            initCSJ()
        }
    }

    fun initCSJ() {
        val config = TTAdConfig.Builder()
            .appId("5491449") // 替换为你的穿山甲媒体平台注册的应用ID
            .useTextureView(true)
            .appName("大尤快跑")
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
                Log.d("csj", "初始化成功")
            }

            override fun fail(p0: Int, p1: String?) {
                Log.d("csj", "初始化失败$p0 and $p1")
            }
        })
    }
}