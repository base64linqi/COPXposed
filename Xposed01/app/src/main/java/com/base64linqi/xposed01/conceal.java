package com.base64linqi.xposed01;

/*
 * Author: Linqi
 * Created: 2023-03-06
 */

import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;

import android.util.Log;

import java.util.Date;
import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class conceal implements IXposedHookLoadPackage {

    private static String startFlag = "【command-start】";
    private static String endFlag = "【command-end】";
    private static String separator = "_";
    private static int i = 1;

    //信息开头
    private static void start() {
        XposedBridge.log("\r\n");
        XposedBridge.log(startFlag);
        }

    //信息结尾
    private static void end() {
            XposedBridge.log(endFlag);
        }


    //输出信息
    private static void writeInfo(String method, String call, String className, String desc) {

        XposedBridge.log("【time】" + separator + getNowDate());
        XposedBridge.log("【method】" + separator + method);
        XposedBridge.log("【call】" + separator + call);
        XposedBridge.log("【class】" + separator + className);
        if (desc != null) {
            XposedBridge.log("【desc】" + separator + desc);
        }
        XposedBridge.log("【stack】");

    }

    //获取时间
    private static String getNowDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    //主要监控逻辑如下
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.qiyi.video")) {
            return;
        }

        final String packageName = lpparam.packageName;

        XposedBridge.log("已加载应用: " + lpparam.packageName);
        final Class<?> telephonyManagerClass = Class.forName("android.telephony.TelephonyManager");

        //IMEI
        XposedBridge.hookAllMethods(telephonyManagerClass, "getImei", new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                String ret = (String) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getImei调用系统imei");
                writeInfo("getImei", "IMEI", "android.telephony.TelephonyManager", "getImei调用系统imei");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        //IMEI
        XposedBridge.hookAllMethods(telephonyManagerClass, "getImei", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                String ret = (String) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getImei(int)调用系统imei");
                writeInfo("getImei(int)", "IMEI", "android.telephony.TelephonyManager", "getImei(int)调用系统imei");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        //IMSI
        XposedBridge.hookAllMethods(telephonyManagerClass, "getSubscriberId", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                String ret = (String) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getSubscriberId调用用户IMSI");
                writeInfo("getSubscriberId", "IMSI", "android.telephony.TelephonyManager", "应用通过getSubscriberId调用用户IMSI");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }

        });

        //IMEI
        XposedBridge.hookAllMethods(telephonyManagerClass, "getDeviceId", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                String ret = (String) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getDeviceId(int)调用用户imei");
                writeInfo("getDeviceId(int)", "IMEI", "android.telephony.TelephonyManager", "getDeviceId(int)调用用户imei");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        // IMEI
        XposedBridge.hookAllMethods(telephonyManagerClass, "getDeviceId", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                String ret = (String) param.getResult();
                if (param.args.length > 0) {
                    int a1 = (int) param.args[0];
                    XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getDeviceId(" + a1 + ")调用用户IMEI");
                    writeInfo("getDeviceId(" + a1 + ")", "IMEI", "android.telephony.TelephonyManager", "getDeviceId(" + a1 + ")调用用户IMEI");
                } else {
                    XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getDeviceId()调用用户IMEI");
                    writeInfo("getDeviceId", "IMEI", "android.telephony.TelephonyManager", "getDeviceId()调用用户IMEI");
                }
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });


        //imsi/iccid
        XposedBridge.hookAllMethods(telephonyManagerClass, "getSimSerialNumber", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                String ret = (String) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getSimSerialNumber获取IMSI/iccid");
                writeInfo("getSimSerialNumber", "IMSI", "android.telephony.TelephonyManager", "getSimSerialNumber调用用户sn");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        //imsi/iccid
        XposedBridge.hookAllMethods(telephonyManagerClass, "getSimSerialNumber", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                String ret = (String) param.getResult();
                if (param.args.length > 0) {
                    int a1 = (int) param.args[0];
                    XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getSimSerialNumber(" + a1 + ")获取IMSI/iccid");
                    writeInfo("getSimSerialNumber(" + a1 + ")", "IMSI", "android.telephony.TelephonyManager", "getSimSerialNumber(" + a1 + ")调用用户sn");
                } else {
                    XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getSimSerialNumber()获取IMSI/iccid");
                    writeInfo("getSimSerialNumber", "IMSI", "android.telephony.TelephonyManager", "getSimSerialNumber调用用户sn");
                }
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        //SSID
        XposedBridge.hookAllMethods(WifiInfo.class, "getSSID", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                String ret = (String) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getMacAddress获取Wifi的SSID地址");
                writeInfo("SSID", "WiFi", "android.net.wifi.WifiInfo", "getMacAddress获取Wifi的SSID地址");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        //SSID
        XposedBridge.hookAllMethods(WifiInfo.class, "getSSID", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                String ret = (String) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getMacAddress获取Wifi的SSID地址");
                writeInfo("SSID", "WiFi", "android.net.wifi.WifiInfo", "getMacAddress获取Wifi的SSID地址");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        //Wifi
        XposedBridge.hookAllMethods(WifiInfo.class, "getIpAddress", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                int ret = (int) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getIpAddress获取Wifi的IP地址");
                writeInfo("getIpAddress", "WiFi", "android.net.wifi.WifiInfo", "getIpAddress获取Wifi的IP地址");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        //Wifi信息
        final Class<?> wifiManagerClass = Class.forName("android.net.wifi.WifiManager");
        final Class<?> inetAddressClass = Class.forName("java.net.InetAddress");

        //getConnectionInfo
        XposedBridge.hookAllMethods(wifiManagerClass, "getConnectionInfo", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object ret = param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getConnectionInfo获取wifi信息");
                writeInfo("getConnectionInfo", "WiFi", "android.net.wifi.WifiManager", "getConnectionInfo获取wifi信息");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        //getConfiguredNetworks
        XposedBridge.hookAllMethods(wifiManagerClass, "getConfiguredNetworks", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object ret = param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getConfiguredNetworks获取wifi信息");
                writeInfo("getConfiguredNetworks", "WiFi", "android.net.wifi.WifiManager", "getConfiguredNetworks获取wifi信息");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        //getScanResults
        XposedBridge.hookAllMethods(wifiManagerClass, "getScanResults", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object ret = param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getScanResults获取wifi信息");
                writeInfo("getScanResults", "WiFi", "android.net.wifi.WifiManager", "getScanResults获取wifi信息");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        //getHostAddress
        XposedBridge.hookAllMethods(inetAddressClass, "getHostAddress", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object ret = param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getHostAddress获取网络IP");
                writeInfo("getHostAddress", "MAC", "java.net.InetAddress", "getHostAddress获取网络IP");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        //网络信息
        final Class<?> networkInfoClass = Class.forName("android.net.NetworkInfo");
        XposedBridge.hookAllMethods(networkInfoClass, "isConnected", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                boolean ret = (boolean) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过isConnected获取网络是否连接");
                writeInfo("isConnected", "网络","android.net.NetworkInfo","isConnected获取网络是否连接");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        XposedBridge.hookAllMethods(networkInfoClass, "isAvailable", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                boolean ret = (boolean) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过isConnected获取网络是否可用");
                writeInfo("isAvailable", "网络","android.net.NetworkInfo","isConnected获取网络是否可用");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        XposedBridge.hookAllMethods(networkInfoClass, "getExtraInfo", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                String ret = (String) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getExtraInfo获取网络名称");
                writeInfo("getExtraInfo", "网络","android.net.NetworkInfo","getExtraInfo获取网络名称");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        XposedBridge.hookAllMethods(networkInfoClass, "getTypeName", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                String ret = (String) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getTypeName获取网络类型名称");
                writeInfo("getTypeName", "网络","android.net.NetworkInfo","getTypeName获取网络类型名称");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        XposedBridge.hookAllMethods(networkInfoClass, "getType", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                int ret = (int) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getType获取网络类型");
                writeInfo("getType", "网络","android.net.NetworkInfo","getType获取网络类型");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        // BSSID
        XposedBridge.hookAllMethods(WifiInfo.class, "getBSSID", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("【" + getNowDate() + "】" + i++ +". 应用通过getBSSID获取Wifi的BSSID");
                writeInfo("BSSID", "WiFi", "android.net.wifi.WifiInfo", "getBSSID获取Wifi的BSSID");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

// mac
        XposedBridge.hookAllMethods(WifiInfo.class, "getMacAddress", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                String ret = (String) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ +". 应用通过getMacAddress调用系统mac");
                writeInfo("getMacAddress", "MAC", "android.net.wifi.WifiInfo", "getMacAddress调用系统mac");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        XposedBridge.hookAllMethods(NetworkInterface.class, "getHardwareAddress", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }


            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                byte[] ret = (byte[]) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ +". 应用通过getHardwareAddress调用系统mac");
                writeInfo("getHardwareAddress", "MAC", "java.net.NetworkInterface", "getHardwareAddress调用系统mac");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        // 安卓ID
        final Class<?> secureClass = Class.forName("android.provider.Settings$Secure");
        XposedBridge.hookAllMethods(secureClass, "getString", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                String a2 = (String) param.args[1];
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getString调用获取安卓ID");
                writeInfo("getString", "安卓ID", "android.provider.Settings$Secure", "getString调用获取安卓ID");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        XposedBridge.hookAllMethods(secureClass, "getStringForUser", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getStringForUser获取安卓ID");
                writeInfo("getStringForUser", "安卓ID", "android.provider.Settings$Secure", "getStringForUser获取安卓ID");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        // App获取其他App信息ApplicationPackageManager
        Class<?> applicationPackageManagerClass = XposedHelpers.findClass("android.app.ApplicationPackageManager", null);
        XposedHelpers.findAndHookMethod(applicationPackageManagerClass, "getInstalledPackages", int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                int arg = (int) param.args[0];
                if (arg != PackageManager.GET_META_DATA) {
                    return;
                }
                XposedBridge.log( "【" + getNowDate() + "】" + i++ + ". 应用通过getInstalledPackages调用系统应用列表");
                writeInfo("getInstalledPackages", "应用列表", "android.app.ApplicationPackageManager", "getInstalledPackages调用系统应用列表");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        XposedHelpers.findAndHookMethod(applicationPackageManagerClass, "getInstalledApplications", int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                int arg = (int) param.args[0];
                if (arg != PackageManager.GET_META_DATA) {
                    return;
                }
                XposedBridge.log( "【" + getNowDate() + "】" + i++ + ". 应用通过getInstalledApplications调用系统应用列表");
                writeInfo("getInstalledApplications", "应用列表", "android.app.ApplicationPackageManager", "getInstalledApplications调用系统应用列表");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        XposedHelpers.findAndHookMethod(applicationPackageManagerClass, "queryIntentActivities", Intent.class, int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Intent intent = (Intent) param.args[0];
                int flags = (int) param.args[1];
                if ((flags & PackageManager.GET_META_DATA) == 0) {
                    return;
                }
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过queryIntentActivitiesh获取应用列表");
                writeInfo("queryIntentActivities", "应用列表", "android.app.ApplicationPackageManager", "queryIntentActivitiesh获取应用列表");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        XposedHelpers.findAndHookMethod(applicationPackageManagerClass, "queryIntentActivitiesAsUser", Intent.class, int.class, int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }


            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Intent intent = (Intent) param.args[0];
                int flags = (int) param.args[1];
                if ((flags & PackageManager.GET_META_DATA) == 0) {
                    return;
                }
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过queryIntentActivitiesh获取应用列表");
                writeInfo("queryIntentActivitiesAsUser", "应用列表", "android.app.ApplicationPackageManager", "queryIntentActivitiesAsUser获取应用列表");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        XposedHelpers.findAndHookMethod(applicationPackageManagerClass, "getPackageInfoAsUser", String.class, int.class, int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                String packageName = (String) param.args[0];
                int flags = (int) param.args[1];
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getPackageInfoAsUser获取应用列表");
                Log.i("debug", "afterHookedMethod: =================");
                writeInfo("getPackageInfoAsUser", "应用列表", "android.app.ApplicationPackageManager", "getPackageInfoAsUser获取应用列表");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });


        XposedHelpers.findAndHookMethod(ActivityManager.class, "getRunningAppProcesses", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                List<ActivityManager.RunningAppProcessInfo> ret = (List<ActivityManager.RunningAppProcessInfo>) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getRunningAppProcesses获取了正在运行的App");
                writeInfo("getRunningAppProcesses", "应用列表", "android.app.ActivityManager", "getRunningAppProcesses获取了正在运行的App");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        // GPS
        Class<?> locationManagerClass = XposedHelpers.findClass("android.location.LocationManager", null);

        XposedHelpers.findAndHookMethod(locationManagerClass, "getLastKnownLocation", String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getLastKnownLocation调用系统GPS");
                writeInfo("getLastKnownLocation", "GPS", "android.location.LocationManager", "getLastKnownLocation调用系统GPS");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        XposedHelpers.findAndHookMethod(locationManagerClass, "getLastLocation", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getLastLocation调用系统GPS");
                writeInfo("getLastLocation", "GPS", "android.location.LocationManager", "getLastLocation调用系统GPS");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        Class<?> locationClass = XposedHelpers.findClass("android.location.Location", null);

        XposedHelpers.findAndHookMethod(locationClass, "getLongitude", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getLongitude调用系统GPS");
                writeInfo("getLongitude", "GPS", "android.location.Location", "getLongitude调用系统GPS");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        //相机
        Class<?> cameraClass = XposedHelpers.findClass("android.hardware.Camera", null);
        Method takePictureMethod = cameraClass.getDeclaredMethod("takePicture", Class.forName("android.hardware.Camera$ShutterCallback"),
                Class.forName("android.hardware.Camera$PictureCallback"), Class.forName("android.hardware.Camera$PictureCallback"));
        Method originalMethod = XposedHelpers.findMethodExact(cameraClass, takePictureMethod.getName(), takePictureMethod.getParameterTypes());
        XposedBridge.hookMethod(originalMethod, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过takePicture调用系统相机");
                writeInfo("takePicture", "相机", "android.hardware.Camera", "takePicture调用系统相机");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
            }
        });

        //运营商
        XposedHelpers.findAndHookMethod(telephonyManagerClass, "getSimOperator", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getSimOperator获取了运营商");
                writeInfo("getSimOperator", "运营商", "android.telephony.TelephonyManager", "getSimOperator获取了运营商");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        XposedHelpers.findAndHookMethod(telephonyManagerClass, "getNetworkOperatorName", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getNetworkOperatorName获取运营商");
                writeInfo("getNetworkOperatorName", "运营商", "android.telephony.TelephonyManager", "getNetworkOperatorName获取运营商");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        //读取剪切板信息
        final Class<?> clipboardManagerClass = XposedHelpers.findClass("android.content.ClipboardManager", null);
        XposedHelpers.findAndHookMethod(clipboardManagerClass, "getPrimaryClip", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 应用通过getPrimaryClip读取剪切板信息");
                writeInfo("getPrimaryClip", "剪切板","android.content.ClipboardManager","getPrimaryClip读取剪切板信息");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }
        });

        //获取基站信息
        final Class<?> cdmaCellLocationClass = XposedHelpers.findClass("android.telephony.cdma.CdmaCellLocation", null);
        XposedHelpers.findAndHookMethod(cdmaCellLocationClass, "getBaseStationId", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object result = param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 获取基站信息:获取cid:");
                writeInfo("getBaseStationId", "电信卡", "android.telephony.cdma.CdmaCellLocation", "获取基站cid信息");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                param.setResult(result);
                end();
            }
        });

        XposedHelpers.findAndHookMethod(cdmaCellLocationClass, "getNetworkId", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object result = param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 获取基站信息:基站lac信");
                writeInfo("getNetworkId", "电信卡", "android.telephony.cdma.CdmaCellLocation", "获取基站lac信息");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
                param.setResult(result);
            }
        });

        final Class<?> gsmCellLocationClass = XposedHelpers.findClass("android.telephony.gsm.GsmCellLocation", null);
        XposedHelpers.findAndHookMethod(gsmCellLocationClass, "getCid", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object result = param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 获取基站信息:获取cid");
                writeInfo("getCid", "移动/联通卡", "android.telephony.gsm.GsmCellLocation", "获取基站cid信息");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
                param.setResult(result);
            }
        });

        XposedHelpers.findAndHookMethod(gsmCellLocationClass, "getLac", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                start();
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object result = param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 获取基站信息:获取lac");
                writeInfo("getLac", "移动/联通卡", "android.telephony.gsm.GsmCellLocation", "获取基站lac信息");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
                param.setResult(result);
            }
        });

        // 获取蓝牙设备信息
        XposedHelpers.findAndHookMethod("android.bluetooth.BluetoothDevice", null, "getName", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                String temp = (String) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 获取蓝牙设备名称");
                writeInfo("getName", "蓝牙", "android.bluetooth.BluetoothDevice", "获取蓝牙设备名称");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                start();
            }
        });

        XposedHelpers.findAndHookMethod("android.bluetooth.BluetoothDevice", null, "getAddress", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                String temp = (String) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 获取蓝牙设备Mac");
                writeInfo("getAddress", "蓝牙", "android.bluetooth.BluetoothDevice", "获取蓝牙设备Mac");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                start();
            }
        });

        XposedHelpers.findAndHookMethod("android.bluetooth.BluetoothAdapter", null, "getName", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                String temp = (String) param.getResult();
                XposedBridge.log("【" + getNowDate() + "】" + i++ + ". 获取蓝牙设备名称");
                writeInfo("getName", "蓝牙", "android.bluetooth.BluetoothAdapter", "获取蓝牙设备名称");
                XposedBridge.log(Log.getStackTraceString(new Throwable()));
                end();
            }

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                start();
            }
        });

    }
    }
