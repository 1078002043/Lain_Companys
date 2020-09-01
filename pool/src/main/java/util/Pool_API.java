package util;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/12/15 10:41
 * Description：水质 API 工具类
 **/
public class Pool_API {

    public static String Pool_IP = "http://192.168.1.29:8080";
    //溶氧仪
    public static String dissolved_real = "/software/requestO2Data.html?lId=1";
    //酸碱仪
    public static String acid_base = "/software/phmeter/findPHMeterManageAll";
    //溶氧仪 水温检测
    public static String dissolvedOxgen = "/software/waterTempController/getWaterTempManageAll";
    //增氧泵
    public static String oxygenIncreasing = "/software/requestHumidificationData.html?lId=1";
    //电导率
    public static String conductivity = "/software/conductance/getConductanceManageAll";
    //水流量
    public static String discharge = "/software/waterFlowController/getWaterFlowManageAll";
    //水位检测
    public static String waterLevel = "/software/waterLevelController/getWaterLevelManageAll";
    //硫化氢
    public static String hydrogen = "/software/requestHsData.html?lId=1";
    //盐度
    public static String salinity = "/software/salinityController/getSalinityManageAll";
    //氨氮监测
    public static String ammoniaNitrogen = "/software/ammoniaController/getAmmoniaManageAll";
    //亚硝酸盐
    public static String nitrite = "/software/nitriteController/getNitriteManageAll";
    //取样泵
    public static String samplingPump = "/software/requestSampleData.html?lId=1";

}
