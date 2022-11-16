package com.woniu.utils;

/**
 * @Author liuchou.ma
 * @desc: 地理位置工具类  
 * @Date 2022/04/24 14:35
 **/
public class GeoUtils {

    /**
     * 计算两点位置的距离，返回两点的距离，单位(米)
     * 该公式为GOOGLE提供，误差小于0.2米
     * @param lat1 第一点纬度
     * @param lng1 第一点经度
     * @param lat2 第二点纬度
     * @param lng2 第二点经度
     * @param radius 范围
     * @return
     */
    public static double GetDistance(double lat1, double lng1, double lat2, double lng2, double radius)
    {
        double EARTH_RADIUS = 6378137;
        double radLat1 = Rad(lat1);//第一点纬度
        double radLng1 = Rad(lng1);//第一点经度
        double radLat2 = Rad(lat2);//第二点纬度
        double radLng2 = Rad(lng2);// 第二点经度
        double a = radLat1 - radLat2;
        double b = radLng1 - radLng2;
        double result = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2))) * EARTH_RADIUS;
        if (result > 0)
        {
            result = result - radius;
            result = result < 0 ? 0 : result;
        }
        return result;
    }


    /**
     * 经纬度转化成弧度
     * @param d
     * @return
     */
    private static double Rad(double d)
    {
        return d * Math.PI / 180d;
    }
}
