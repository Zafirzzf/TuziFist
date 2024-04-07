package com.ubisoft.rabbidsrunner.zq.a1

/**
 * Created by zzf on 2024/3/11 .
 */
import android.util.Log
import android.app.Application

import com.bytedance.sdk.openadsdk.TTAdConfig
import com.bytedance.sdk.openadsdk.TTAdSdk
import com.bytedance.sdk.openadsdk.TTAdConstant
import com.hjq.toast.Toaster
import kotlinx.coroutines.delay
import java.util.Timer
import kotlin.concurrent.schedule


class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Toaster.init(this);
    }
}