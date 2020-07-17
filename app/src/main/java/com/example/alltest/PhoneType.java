package com.example.alltest;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;

/**
 * Created by zhaobinsir
 * on 2020/7/17. 各大手机厂商判断
 */
public class PhoneType {


    /**
     * 跳转到指定应用的首页
     */
    private static void showActivity(@NonNull String packageName) {
        Intent intent = App.getInstance().getPackageManager().getLaunchIntentForPackage(packageName);
        App.getInstance().startActivity(intent);
    }

    /**
     * 跳转到指定应用的指定页面
     */
    private static void showActivity(@NonNull String packageName, @NonNull String activityDir) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, activityDir));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getInstance().startActivity(intent);
    }

    //是否为华为
    public static boolean isHuawei() {
        if (Build.BRAND == null) {
            return false;
        } else {
            return Build.BRAND.toLowerCase().equals("huawei") || Build.BRAND.toLowerCase().equals("honor");
        }
    }

    //    跳转华为手机管家的启动管理页  操作步骤：应用启动管理 -> 关闭应用开关 -> 打开允许自启动
    public static void goHuaweiSetting() {
        try {
            showActivity("com.huawei.systemmanager",
                    "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");
        } catch (Exception e) {
            showActivity("com.huawei.systemmanager",
                    "com.huawei.systemmanager.optimize.bootstart.BootStartActivity");
        }
    }


    public static boolean isXiaomi() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("xiaomi");
    }

    //跳转小米安全中心的自启动管理页面：
    public static void goXiaomiSetting() {
        showActivity("com.miui.securitycenter",
                "com.miui.permcenter.autostart.AutoStartManagementActivity");
    }


    public static boolean isOPPO() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("oppo");
    }

    //跳转 OPPO 手机管家： 操作步骤：权限隐私 -> 自启动管理 -> 允许应用自启动
    public static void goOPPOSetting() {
        try {
            showActivity("com.coloros.phonemanager");
        } catch (Exception e1) {
            try {
                showActivity("com.oppo.safe");
            } catch (Exception e2) {
                try {
                    showActivity("com.coloros.oppoguardelf");
                } catch (Exception e3) {
                    showActivity("com.coloros.safecenter");
                }
            }
        }
    }

    public static boolean isVIVO() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("vivo");
    }

    //    跳转 VIVO 手机管家： 操作步骤：权限管理 -> 自启动 -> 允许应用自启动
    public static void goVIVOSetting() {
        showActivity("com.iqoo.secure");
    }


    public static boolean isMeizu() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("meizu");
    }

    //跳转魅族手机管家： 操作步骤：权限管理 -> 后台管理 -> 点击应用 -> 允许后台运行
    public static void goMeizuSetting() {
        showActivity("com.meizu.safe");
    }


    public static boolean isSamsung() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("samsung");
    }

    //    跳转三星智能管理器：  操作步骤：自动运行应用程序 -> 打开应用开关 -> 电池管理 -> 未监视的应用程序 -> 添加应用
    public static void goSamsungSetting() {
        try {
            showActivity("com.samsung.android.sm_cn");
        } catch (Exception e) {
            showActivity("com.samsung.android.sm");
        }
    }

    //乐视
    public static boolean isLeTV() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("letv");
    }

    //    跳转乐视手机管家： 操作步骤：自启动管理 -> 允许应用自启动
    public static void goLetvSetting() {
        showActivity("com.letv.android.letvsafe",
                "com.letv.android.letvsafe.AutobootManageActivity");
    }

    //    锤子
    public static boolean isSmartisan() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("smartisan");
    }

    //    跳转手机管理： 操作步骤：权限管理 -> 自启动权限管理 -> 点击应用 -> 允许被系统启动
    public static void goSmartisanSetting() {
        showActivity("com.smartisanos.security");
    }


}
