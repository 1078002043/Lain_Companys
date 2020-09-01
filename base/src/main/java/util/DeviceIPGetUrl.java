package util;

/**
 * @ClassName: DeviceIPGetUrl
 * @Description: 获取设备IP的URL工具类
 * @Author: YIN LUO FEI
 * @Date: 2020/8/3 9:12
 */
public class DeviceIPGetUrl {

    private static DeviceIPGetUrl deviceIPGetUrl;

    public static DeviceIPGetUrl getInstance() {

        if (deviceIPGetUrl == null)
            synchronized (DeviceIPGetUrl.class) {
                if (deviceIPGetUrl == null)
                    deviceIPGetUrl = new DeviceIPGetUrl();
            }

        return deviceIPGetUrl;
    }

    public String deviceIpUrl(String deviceName) {

        switch (deviceName) {
            case "温湿度":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.tempIP;
            case "定位漏水":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.locatingIP;
            case "分体空调":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.splitAirIP;
            case "精密空调":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.precisionAirIP;
            case "二氧化碳":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.carbonIP;
            case "有毒气体":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.poisonousIP;
            case "粉尘":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.dustIP;
            case "噪音":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.noiseIP;
            case "冷藏库":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.pt100IP;
            case "DS18B20":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.ds18b20IP;
            case "气压强度":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.gasPressureIP;
            case "光照强度":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.lightIP;
            case "贴片式温度":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.smtIP;
            case "光纤电缆测温":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.fiberIP;
            case "称重传感器":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.weightIP;
            case "照度传感器":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.illuminationIP;
            case "剩余电流电气火灾":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.surplusIP;
            case "风速风向传感器":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.windIP;
            case "变压器温控器":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.transformerIP;
            case "电缆贴片温度探头":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.patchIP;
            case "风管温度传感器":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.windSenseIP;
            case "土壤水分温度":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.patchIP;
            case "室外防水温湿度变送器":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.waterproofIP;
            case "室外温度变送器":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.outdoorIP;
            case "电量仪":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.electricMeterIP;
            case "UPS":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.upsIP;
            case "单体蓄电池":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.singleBatteryIP;
            case "发电机监控":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.generatorIP;
            case "氨气":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.ammoniaIP;
            case "余氯":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.residualIP;
            case "浑浊度":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.turbidityIP;
            case "水位":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.waterLevelIP;
            case "溶氧仪":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.dissolvedIP;
            case "水温":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.waterTempIP;
            case "水流量":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.waterFlowIP;
            case "水压力":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.waterPressureIP;
            case "污水流量":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.sewageIP;
            case "酸碱仪":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.phmeterIP;
            case "盐度":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.salinityIP;
            case "硫化氢":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.hydrogenIP;
            case "亚硝酸盐":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.nitriteIP;
            case "氨氮":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.ammoniaNitrogenIP;
            case "电导":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.conductanceIP;
            case "叶绿素":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.chlorophyllIP;
            case "蓝藻素":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.cyaninIP;
            case "总磷水质":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.totalLinIP;
            case "Codcr":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.codcrIP;
            case "ORP变送器":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.orptransmIP;
            case "流通池":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.circulatorIP;
            case "氧气变送器":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.oxygenIP;
            case "尿素变送器":
                return LainNewApi.getInstance().getRootPath() + LainNewApi.ureaIP;
        }

        return null;
    }

}
