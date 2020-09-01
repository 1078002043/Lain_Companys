package com.example.sample.system_lib;

import com.example.infinitecycleviewpager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成系统的信息
 */
public class SystemApi {

    public static List<SystemInfoBean> getSystemList() {

        List<SystemInfoBean> systemInfoBeanList = new ArrayList<>();

        SystemInfoBean aquaculture = new SystemInfoBean();
        aquaculture.setSystemTitle("水产养殖");
        aquaculture.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(aquaculture);
        //这个是首先显示的 ITEM
        SystemInfoBean computerRoomMonitoring = new SystemInfoBean();
        computerRoomMonitoring.setSystemTitle("机房监控");
        computerRoomMonitoring.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(computerRoomMonitoring);

        SystemInfoBean livestockFarming = new SystemInfoBean();
        livestockFarming.setSystemTitle("畜牲养殖");
        livestockFarming.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(livestockFarming);

        SystemInfoBean agricultural = new SystemInfoBean();
        agricultural.setSystemTitle("农业大棚");
        agricultural.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(agricultural);

        SystemInfoBean swimmingPool = new SystemInfoBean();
        swimmingPool.setSystemTitle("泳池水质");
        swimmingPool.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(swimmingPool);

        SystemInfoBean archives = new SystemInfoBean();
        archives.setSystemTitle("档案室管理");
        archives.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(archives);

        SystemInfoBean airConditioning = new SystemInfoBean();
        airConditioning.setSystemTitle("空调监控");
        airConditioning.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(airConditioning);

        SystemInfoBean powerTelemetry = new SystemInfoBean();
        powerTelemetry.setSystemTitle("电力遥测");
        powerTelemetry.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(powerTelemetry);

        SystemInfoBean grainSituation = new SystemInfoBean();
        grainSituation.setSystemTitle("粮情监测");
        grainSituation.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(grainSituation);

        SystemInfoBean leakageAlarm = new SystemInfoBean();
        leakageAlarm.setSystemTitle("漏水报警");
        leakageAlarm.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(leakageAlarm);

        SystemInfoBean coldChain = new SystemInfoBean();
        coldChain.setSystemTitle("冷链环境监测");
        coldChain.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(coldChain);

        SystemInfoBean noiseDust = new SystemInfoBean();
        noiseDust.setSystemTitle("噪音粉尘监测");
        noiseDust.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(noiseDust);

        SystemInfoBean cableTemperature = new SystemInfoBean();
        cableTemperature.setSystemTitle("电缆温度监测");
        cableTemperature.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(cableTemperature);

        SystemInfoBean sewageMonitoring = new SystemInfoBean();
        sewageMonitoring.setSystemTitle("污水在线监测");
        sewageMonitoring.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(sewageMonitoring);

        SystemInfoBean battery = new SystemInfoBean();
        battery.setSystemTitle("蓄电池监测");
        battery.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(battery);

        SystemInfoBean electricalFire = new SystemInfoBean();
        electricalFire.setSystemTitle("电气火灾监控");
        electricalFire.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(electricalFire);

        SystemInfoBean weigh = new SystemInfoBean();
        weigh.setSystemTitle("称重自动记录监控");
        weigh.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(weigh);

        SystemInfoBean intelligentLighting = new SystemInfoBean();
        intelligentLighting.setSystemTitle("智能照明");
        intelligentLighting.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(intelligentLighting);

        SystemInfoBean buildingAutomation = new SystemInfoBean();
        buildingAutomation.setSystemTitle("楼宇自控");
        buildingAutomation.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(buildingAutomation);

        SystemInfoBean ibms = new SystemInfoBean();
        ibms.setSystemTitle("智能化集成系统 IBMS");
        ibms.setSystemCover(R.drawable.ic_design);
        systemInfoBeanList.add(ibms);

        return systemInfoBeanList;
    }

}
