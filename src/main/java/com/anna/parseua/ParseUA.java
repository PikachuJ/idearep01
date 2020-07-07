package com.anna.parseua;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;

import java.io.IOException;

public class ParseUA {
    static UASparser uaSparser = null;

    static {
        try {
            uaSparser = new UASparser(OnlineUpdater.getVendoredInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String str = "Mozilla/5.0(WindowsNT6.1)AppleWebKit/537.36(KHTML,likeGecko)Chrome/29.0.1547.66Safari/537.36";
        UserAgentInfo userAgentInfo = null;
        try {
            userAgentInfo = ParseUA.uaSparser.parse(str);
            //操作系统名
            String osFamily = userAgentInfo.getOsFamily();
            System.out.println(osFamily);
            System.out.println("--------");
            //操作系统
            String osName = userAgentInfo.getOsName();
            System.out.println(osName);
            System.out.println("--------");
            //浏览器名称
            String uaFamily = userAgentInfo.getUaFamily();
            System.out.println(uaFamily);
            System.out.println("--------");
            //浏览器版本信息
            String browserVersionInfo = userAgentInfo.getBrowserVersionInfo();
            System.out.println(browserVersionInfo);
            System.out.println("--------");
            //设备类型
            String deviceType = userAgentInfo.getDeviceType();
            System.out.println(deviceType);
            System.out.println("--------");
            //浏览器
            String uaName = userAgentInfo.getUaName();
            System.out.println(uaName);
            System.out.println("--------");
            //类型
            String type = userAgentInfo.getType();
            System.out.println(type);
            System.out.println("--------");

            System.out.println(userAgentInfo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
