package control;

import android.util.Log;

import androidx.fragment.app.Fragment;

import computer_room.fragment.AssetManagement;
import computer_room.fragment.CarbonDioxide;
import computer_room.fragment.CentralAir;
import computer_room.fragment.Dehumidifier;
import computer_room.fragment.Device8052Fragment;
import computer_room.fragment.DeviceAdministrator;
import computer_room.fragment.DeviceSwitchFragment;
import computer_room.fragment.DustProof;
import computer_room.fragment.ElectricityMeter;
import computer_room.fragment.Locating_Detail;
import computer_room.fragment.New_DeviceManage;
import computer_room.fragment.Noise;
import computer_room.fragment.PrecisionAirConditioner;
import computer_room.fragment.SplitAirConditioner;
import computer_room.fragment.SystemLogManagement;
import computer_room.fragment.SystemServer;
import computer_room.fragment.Temperature8060;
import computer_room.fragment.ToxicQiti;
import computer_room.fragment.Ups;
import environment.view.DS18B20Fragment;
import environment.view.ElectricalFireFragment;
import environment.view.FiberFragment;
import environment.view.GasPressureFragment;
import environment.view.GeneratorFragment;
import environment.view.Illumination2Fragment;
import environment.view.IlluminationFragment;
import environment.view.OutdoorTempFragment;
import environment.view.PT100Fragment;
import environment.view.PatchTempFragment;
import environment.view.SingleBatteryFragment;
import environment.view.SoilmoistureFragment;
import environment.view.TransformerFragment;
import environment.view.WaterproofFragment;
import environment.view.WeightFragment;
import environment.view.WindFragment;
import environment.view.WindSenseFragment;
import fragment.VideoMainPage;
import util.LainNewApi;
import view.AcidBaseFragment;
import view.Ammonia2Fragment;
import view.AmmoniaFragment;
import view.ChlorophyllFragment;
import view.CirculationFragment;
import view.CodcrFragment;
import view.ConductivityFragment;
import view.CyaninFragment;
import view.DischargeFragment;
import view.DissolvedFragment;
import view.HydrogenFragment;
import view.NitriteFragment;
import view.OrpFragment;
import view.OxygenFragment;
import view.ResidualFragment;
import view.SalinityFragment;
import view.SewageFragment;
import view.TotalphosphorusFragment;
import view.TurbidityFragment;
import view.UreaFragment;
import view.WaterLevelFragment;
import view.WaterPressure;
import view.WaterTempFragment;

/**
 * 控制显示设备详情的主类
 */
public class MainControl {

    private static MainControl mainControl;

    //使用单例返回实例
    public static MainControl getInstance() {
        if (mainControl == null)
            synchronized (MainControl.class) {
                if (mainControl == null) {
                    mainControl = new MainControl();
                }
            }

        return mainControl;
    }

    /**
     * 先判断是在哪个模块中，再去查询某个模块的设备
     *
     * @param tag        设备所属Tag
     * @param deviceName 设备名称
     * @return 设备对应的Fragment
     */
    public Fragment deviceControl(String tag, String deviceName) {

        switch (tag) {
            case "环境监控":
                return computerRoom(deviceName);
            case "动力监控":
                return powerMonitoring(deviceName);
            case "安防监控":
                return securityMonitoring(deviceName);
            case "运维监控":
                return operationalMonitoring(deviceName);
            case "系统管理":
                return systemManagement(deviceName);
            case "水质监控":
                return waterQuality(deviceName);
        }
        return null;
    }

    /**
     * 水质监控
     *
     * @param deviceName 设备名称
     * @return 设备对应的Fragment
     */
    private Fragment waterQuality(String deviceName) {

        switch (deviceName) {
            case "氨气":
                return new Ammonia2Fragment(LainNewApi.ammonia2Real, LainNewApi.ammonia2Alert, LainNewApi.ammonia2History, LainNewApi.ammonia2Manage, LainNewApi.ammonia2Delete, LainNewApi.ammonia2Update);
            case "余氯":
                return new ResidualFragment(LainNewApi.residualReal, LainNewApi.residualAlert, LainNewApi.residualHistory, LainNewApi.residualManage, LainNewApi.residualDelete, LainNewApi.residualUpdate);
            case "浑浊度":
                return new TurbidityFragment(LainNewApi.turbidityReal, LainNewApi.turbidityAlert, LainNewApi.turbidityHistory, LainNewApi.turbidityManage, LainNewApi.turbidityDelete, LainNewApi.turbidityUpdate);
            case "污水流量":
                return new SewageFragment(LainNewApi.sewageReal, LainNewApi.sewageAlert, LainNewApi.sewageHistory, LainNewApi.sewageManage, LainNewApi.sewageDelete, LainNewApi.sewageUpdate);
            case "叶绿素":
                return new ChlorophyllFragment(LainNewApi.chlorophyllReal, LainNewApi.chlorophyllAlert, LainNewApi.chlorophyllHistory, LainNewApi.chlorophyllManage, LainNewApi.chlorophyllDelete, LainNewApi.chlorophyllUpdate);
            case "蓝藻素":
                return new CyaninFragment(LainNewApi.cyaninReal, LainNewApi.cyaninAlert, LainNewApi.cyaninHistory, LainNewApi.cyaninManage, LainNewApi.cyaninDelete, LainNewApi.cyaninUpdate);
            case "总磷水质":
                return new TotalphosphorusFragment(LainNewApi.phosphorusReal, LainNewApi.phosphorusAlert, LainNewApi.phosphorusHistory, LainNewApi.phosphorusManage, LainNewApi.phosphorusDelete, LainNewApi.phosphorusUpdate);
            case "Codcr分析":
                return new CodcrFragment(LainNewApi.codrReal, LainNewApi.codrAlert, LainNewApi.codrHistory, LainNewApi.codrManage, LainNewApi.codrDelete, LainNewApi.codrUpdate);
            case "ORP变送器":
                return new OrpFragment(LainNewApi.orpReal, LainNewApi.orpAlert, LainNewApi.orpHistory, LainNewApi.orpManage, LainNewApi.orpDelete, LainNewApi.orpUpdate);
            case "流通池":
                return new CirculationFragment(LainNewApi.circulationReal, LainNewApi.circulationAlert, LainNewApi.circulationHistory, LainNewApi.circulationManage, LainNewApi.circulationDelete, LainNewApi.circulationUpdate);
            case "氧气变送器":
                return new OxygenFragment(LainNewApi.ureaReal, LainNewApi.ureaAlert, LainNewApi.ureaHistory, LainNewApi.ureaManage, LainNewApi.ureaDelete, LainNewApi.ureaUpdate);
            case "尿素变送器":
                return new UreaFragment(LainNewApi.ureaReal, LainNewApi.ureaAlert, LainNewApi.ureaHistory, LainNewApi.ureaManage, LainNewApi.ureaDelete, LainNewApi.ureaUpdate);
            case "溶氧仪":
                return new DissolvedFragment(LainNewApi.dissolvedReal, LainNewApi.dissolvedAlert, LainNewApi.dissolvedHistory, LainNewApi.dissolvedManage, LainNewApi.dissolvedDelete, LainNewApi.dissolvedUpdate);
            case "酸碱仪":
                return new AcidBaseFragment(LainNewApi.acidBaseReal, LainNewApi.acidBaseAlert, LainNewApi.acidBaseHistory, LainNewApi.acidBaseManage, LainNewApi.acidBaseDelete, LainNewApi.acidBaseUpdate);
            case "水温":
                return new WaterTempFragment(LainNewApi.waterTempReal, LainNewApi.waterTempAlert, LainNewApi.waterTempHistory, LainNewApi.waterTempReal, LainNewApi.waterTempDelete, LainNewApi.waterTempUpdate);
            case "氨氮":
                return new AmmoniaFragment(LainNewApi.ammoniaReal, LainNewApi.ammoniaAlert, LainNewApi.ammoniaHistory, LainNewApi.ammoniaManage, LainNewApi.ammoniaDelete, LainNewApi.ammoniaUpdate);
            case "电导":
                return new ConductivityFragment(LainNewApi.conductivityReal, LainNewApi.conductivityAlert, LainNewApi.conductivityHistory, LainNewApi.conductivityManage, LainNewApi.conductivityDelete, LainNewApi.conductivityUpdate);
            case "水流量":
                return new DischargeFragment(LainNewApi.dischargeReal, LainNewApi.dischargeAlert, LainNewApi.dischargeHistory, LainNewApi.dischargeManage, LainNewApi.dischargeDelete, LainNewApi.dischargeUpdate);
            case "硫化氢":
                return new HydrogenFragment(LainNewApi.hydrogenReal, LainNewApi.hydrogenAlert, LainNewApi.hydrogenHistory, LainNewApi.hydrogenManage);
            case "亚硝酸盐":
                return new NitriteFragment(LainNewApi.nitriteReal, LainNewApi.nitriteAlert, LainNewApi.nitriteHistory, LainNewApi.nitriteManage);
            case "盐度":
                return new SalinityFragment(LainNewApi.salinityReal, LainNewApi.salinityAlert, LainNewApi.salinityHistory, LainNewApi.salinityManage, LainNewApi.salinityDelete, LainNewApi.salinityUpdate);
            case "水位":
                return new WaterLevelFragment(LainNewApi.waterLevelReal, LainNewApi.waterLevelAlert, LainNewApi.waterLevelHistory, LainNewApi.waterLevelManage, LainNewApi.waterLevelDelete, LainNewApi.waterLevelUpdate);
            case "水压力":
                return new WaterPressure(LainNewApi.waterPressureReal, LainNewApi.waterPressureAlert, LainNewApi.waterPressureHistory, LainNewApi.waterPressureManage, LainNewApi.waterPressureDelete, LainNewApi.waterPressureUpdate);
        }

        return null;

    }

    /**
     * 系统管理
     *
     * @param deviceName 设备名称
     * @return 设备对应的Fragment
     */
    private Fragment systemManagement(String deviceName) {
        switch (deviceName) {
            case "设备管理":
                return new New_DeviceManage();
            case "日志管理":
                return new SystemLogManagement();
            case "用户管理":
                return new DeviceAdministrator();
        }

        return null;

    }

    /**
     * 运维监控 模块
     *
     * @param deviceName 设备名称
     * @return 设备对应的Fragment
     */
    private Fragment operationalMonitoring(String deviceName) {

        switch (deviceName) {
            case "服务器":
                return new SystemServer();
        }
        return null;
    }

    /**
     * 安防监控
     *
     * @param deviceName 设备名称
     * @return 设备对应的Fragment
     */
    private Fragment securityMonitoring(String deviceName) {

        switch (deviceName) {

            case "烟感监控":
                return new Device8052Fragment(LainNewApi.smoke, LainNewApi.alert8052);
            case "消防监控":
                return new Device8052Fragment(LainNewApi.fire, LainNewApi.alert8052);
            case "玻璃监控":
                return new Device8052Fragment(LainNewApi.wave, LainNewApi.alert8052);
            case "门磁监控":
                return new Device8052Fragment(LainNewApi.doorMagnetic, LainNewApi.alert8052);
            case "红外监控":
                return new Device8052Fragment(LainNewApi.infrared, LainNewApi.alert8052);
            case "门禁监控":
                return new Device8052Fragment(LainNewApi.doorNo, LainNewApi.alert8052);
            case "视频监控":
                return new VideoMainPage();

        }
        return null;
    }

    /**
     * 动力监控
     *
     * @param deviceName 设备名称
     * @return 设备对应的Fragment
     */
    private Fragment powerMonitoring(String deviceName) {

        switch (deviceName) {

            case "配电监控":
                return new Device8052Fragment(LainNewApi.distribution, LainNewApi.alert8052);
            case "市电监控":
                return new Device8052Fragment(LainNewApi.mains, LainNewApi.alert8052);
            case "电量仪":
                return new ElectricityMeter(LainNewApi.electricReal, LainNewApi.electricAlert, LainNewApi.electricHistory, LainNewApi.electricManage);
            case "UPS":
                return new Ups(LainNewApi.upsReal, LainNewApi.upsAlert, LainNewApi.upsManage, LainNewApi.upsManage);
            case "单体蓄电池":
                return new SingleBatteryFragment(LainNewApi.singleBatteryReal, LainNewApi.singleBatteryAlert, LainNewApi.singleBatteryHistory, LainNewApi.singleBatteryManage);
            case "16A开关":
                return new SingleBatteryFragment(LainNewApi.singleBatteryReal, LainNewApi.singleBatteryAlert, LainNewApi.singleBatteryHistory, LainNewApi.singleBatteryManage);
            case "发电机监控":
                return new GeneratorFragment(LainNewApi.generatorReal, LainNewApi.generatorAlert, LainNewApi.generatorHistory, LainNewApi.generatorManage);
        }
        return null;
    }

    /**
     * 环境监控
     *
     * @param deviceName 设备名称
     * @return 设备对应的Fragment
     */
    private Fragment computerRoom(String deviceName) {
        switch (deviceName) {
            case "温湿度":
                return new Temperature8060(LainNewApi.temperatureAndHumidity, LainNewApi.tempAlert, LainNewApi.tempHistory, LainNewApi.temperatureAndHumidity, LainNewApi.tempDelete, LainNewApi.tempChange);
            case "定位漏水":
                return new Locating_Detail(LainNewApi.location, LainNewApi.locationAlert);
            case "精密空调":
                return new PrecisionAirConditioner(LainNewApi.precisionAir, LainNewApi.precisionAirAlert, "", LainNewApi.precisionAir, LainNewApi.precisionAirDelete, LainNewApi.precisionAirChange);
            //所有的8052
            case "非定位漏水":
                return new Device8052Fragment(LainNewApi.nonLocation, LainNewApi.alert8052);
            case "点式漏水":
                return new Device8052Fragment(LainNewApi.dotData, LainNewApi.alert8052);
            //所有的8060
            case "灯光照明":
                return new DeviceSwitchFragment(LainNewApi.lighting);
            case "新风机":
                return new DeviceSwitchFragment(LainNewApi.newFan);
            case "防雷":
                return new Device8052Fragment(LainNewApi.thunder, LainNewApi.alert8052);
            case "分体空调":
                return new SplitAirConditioner(LainNewApi.airFission, "");
            case "抽湿加湿":
                return new DeviceSwitchFragment(LainNewApi.humidification);
            case "二氧化碳":
                return new CarbonDioxide(LainNewApi.carbonReal, LainNewApi.carbonAlert, LainNewApi.carbonReal, LainNewApi.carbonReal);
            case "粉尘":
                return new DustProof(LainNewApi.dustReal, LainNewApi.dustAlert, LainNewApi.dustReal, LainNewApi.dustReal);
            case "中央空调":
                return new CentralAir();
            case "除湿机":
                return new Dehumidifier();
            case "有毒气体":
                return new ToxicQiti(LainNewApi.toxicReal, LainNewApi.toxicAlert, LainNewApi.toxicHistory, LainNewApi.toxicManage);
            case "噪声":
                return new Noise(LainNewApi.noise, LainNewApi.noiseAlert, LainNewApi.noiseHistory, LainNewApi.noiseManage);
            case "资产管理":
                return new AssetManagement(LainNewApi.assetReal, LainNewApi.assetAlert, LainNewApi.assetManage, LainNewApi.assetManage);
            case "常温库":
            case "冷藏车":
            case "冰箱":
            case "冷冻柜":
            case "冷藏库":
                return new PT100Fragment(LainNewApi.pt100Real, LainNewApi.pt100Alert, LainNewApi.pt100History, LainNewApi.pt100Manage, LainNewApi.pt100Delete, LainNewApi.pt100Update);
            case "DS18B20":
                return new DS18B20Fragment(LainNewApi.DS18B20Real, LainNewApi.DS18B20Alert, LainNewApi.DS18B20History, LainNewApi.DS18B20Manage, LainNewApi.DS18B20Delete, LainNewApi.DS18B20Update);
            case "气压强度":
                return new GasPressureFragment(LainNewApi.gasPressureReal, LainNewApi.gasPressureAlert, LainNewApi.gasPressureHistory, LainNewApi.gasPressureManage, LainNewApi.gasPressureDelete, LainNewApi.gasPressureUpdate);
            case "光照强度":
                return new IlluminationFragment(LainNewApi.illuminationReal, LainNewApi.illuminationAlert, LainNewApi.illuminationHistory, LainNewApi.illuminationManage, LainNewApi.illuminationDelete, LainNewApi.illuminationUpdate);
            case "贴片式温度":
                return new PatchTempFragment(LainNewApi.patchTempReal, LainNewApi.patchTempAlert, LainNewApi.patchTempHistory, LainNewApi.patchTempManage, LainNewApi.patchTempDelete, LainNewApi.patchTempUpdate);
            case "光纤电缆测温":
                return new FiberFragment(LainNewApi.fiberReal, LainNewApi.fiberAlert, LainNewApi.fiberHistory, LainNewApi.fiberManage, LainNewApi.fiberDelete, LainNewApi.fiberUpdate);
            case "称重传感器":
                return new WeightFragment(LainNewApi.weightReal, LainNewApi.weightAlert, LainNewApi.weightHistory, LainNewApi.weightManage, LainNewApi.weightDelete, LainNewApi.weightUpdate);
            case "照度传感器":
                return new Illumination2Fragment(LainNewApi.illumination2Real, LainNewApi.illumination2Alert, LainNewApi.illumination2History, LainNewApi.illumination2Manage, LainNewApi.illumination2Delete, LainNewApi.illumination2Update);
            case "剩余电流电气火灾":
                return new ElectricalFireFragment(LainNewApi.electricalFireReal, LainNewApi.electricalFireAlert, LainNewApi.electricalFireHistory, LainNewApi.electricalFireReal, LainNewApi.electricalDelete, LainNewApi.electricalUpdate);
            case "负20度以上冰柜":
                return new DS18B20Fragment(LainNewApi.DS18B20Real, LainNewApi.DS18B20Alert, LainNewApi.DS18B20History, LainNewApi.DS18B20Manage);
            case "负20度以下冰柜":
                return new DS18B20Fragment(LainNewApi.DS18B20Real, LainNewApi.DS18B20Alert, LainNewApi.DS18B20History, LainNewApi.DS18B20Manage);
            case "风速风向传感器":
                return new WindFragment(LainNewApi.windReal, LainNewApi.windAlert, LainNewApi.windHistory, LainNewApi.windManage, LainNewApi.windDelete, LainNewApi.windUpdate);
            case "变压器温控器":
                return new TransformerFragment(LainNewApi.transformerReal, LainNewApi.transformerAlert, LainNewApi.transformerHistory, LainNewApi.transformerManage, LainNewApi.transformerDelete, LainNewApi.transformerUpdate);
            case "电缆贴片温度探头":
                return new DS18B20Fragment(LainNewApi.DS18B20Real, LainNewApi.DS18B20Alert, LainNewApi.DS18B20History, LainNewApi.DS18B20Manage);
            case "风管温度传感器":
                return new WindSenseFragment(LainNewApi.windSenseReal, LainNewApi.windSenseAlert, LainNewApi.windSenseHistory, LainNewApi.windSenseManage);
            case "土壤水分温度":
                return new SoilmoistureFragment(LainNewApi.soilmoistureReal, LainNewApi.soilmoistureAlert, LainNewApi.soilmoistureHistory, LainNewApi.soilmoistureManage, LainNewApi.tempDelete, LainNewApi.tempChange);
            case "室外防水温湿度变送器":
                return new WaterproofFragment(LainNewApi.waterProofReal, LainNewApi.waterProofAlert, LainNewApi.waterProofHistory, LainNewApi.waterProofManage, LainNewApi.tempDelete, LainNewApi.tempChange);
            case "室外温度变送器":
                return new OutdoorTempFragment(LainNewApi.outdoorTempReal, LainNewApi.outdoorTempAlert, LainNewApi.outdoorTempHistory, LainNewApi.outdoorTempManage, LainNewApi.outdoorTempDelete, LainNewApi.outdoorTempUpdate);
        }

        return null;

    }


}

