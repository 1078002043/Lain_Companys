package util;

/**
 * 新的API调用文档
 */
public class LainNewApi {

    private static LainNewApi lainNewApi;

    //获取单例
    public static LainNewApi getInstance() {

        if (lainNewApi == null)
            synchronized (LainNewApi.class) {
                if (lainNewApi == null)
                    lainNewApi = new LainNewApi();
            }

        return lainNewApi;
    }

    public String getRootPath() {

        if (PORT.isEmpty())
            return getRootUrl();
        else
            return IP + ":" + PORT + "/" + PATH + "/";

    }

    public String getRootPath(String host) {
        return host + "://" + IP + ":" + PORT + "/" + PATH + "/";
    }

    public String getRootUrl() {
        return IP + "/" + PATH + "/";
    }

    //    http://192.168.1.240
    //根路径

    public String IP = "";
    //只保存IP
    public String IP_Text = "";
    //自动获取的IP地址
    public String AutoIp = "";
//    public String IP = "http://192.168.1.240";
//    public String IP = "http://192.168.43.45";
//    public String IP = "https://www.kitozer.net";

    //        public String PORT = "9000";
    public String PORT = "8080";
    public String PATH = "software";
//    public String PATH = "lain";

    //保存获取到的IP数量
    public static int ipCount = 0;

    //保存 Intent 跳转时的TAG，用来取出传递的值，设备名称
    public static String INTENT_TAG = "";
    //保存 Intent 跳转时的TAG，用来取出传递的值，设备所属
    public static String DEVICE_TAG = "环境监控";
    //保存设备的图标路径
    public static String DEVICE_IMAGE = "";

    //登陆
    public static String login = "login";

    //查询8052的设备
    public static String all8052 = "ktr8052/findKtr8052All/3";

    //轮询时间间隔
    public static final int SECOND = 3000;

    //APP版本检查
    public static final String CHECK_VERSION = "http://39.106.38.83/version/version_c.php";
    //更新APP URL
    public static final String UPDATE_VERSION = "http://39.106.38.83:8080/app/lain_company.apk";
    //温湿度-实时
    public static final String temperatureAndHumidity = "humiture/findHumitureManageAll";
    //温湿度-报警
    public static final String tempAlert = "humiture/findHumitureAlarm/";
    //温湿度-历史
    public static final String tempHistory = "humiture/findHumitureHistory/";
    //温湿度-设备
    public static final String tempManage = "humiture/findHumitureManageAll";
    //温湿度设备-修改
    public static String tempChange = "humiture/updateHumitureManage";
    //温湿度设备-添加
    public static String tempAdd = "humiture/insertHumitureManage";
    //温湿度设备-删除
    public static final String tempDelete = "humiture/deleteHumitureManageById/";

    //查询所有的组
    public static String findGroup = "group/findGroupAll";

    //8052报警
    public static String alert8052 = "ktr8052/findKtr8052Alarm/";
    //门禁-实时
    public static String doorNo = "ktr8052/getKtr8052/11";

    //红外-实时
    public static String infrared = "ktr8052/getKtr8052/6";


    //烟感-实时
    public static String smoke = "ktr8052/getKtr8052/2";


    //消防-实时
    public static String fire = "ktr8052/getKtr8052/5";

    //玻璃-实时
    public static String wave = "ktr8052/getKtr8052/8";


    //门磁-实时
    public static String doorMagnetic = "ktr8052/getKtr8052/7";


    //新风机
    public static final String newFan = "ktr8060/find8060ByDevice/2";
    //灯光
    public static final String lighting = "ktr8060/find8060ByDevice/5";
    //抽湿加湿
    public static final String humidification = "ktr8060/find8060ByDevice/3";
    //非定位漏水
    public static final String nonLocation = "ktr8052/getKtr8052/1";
    //市电监控
    public static String mains = "ktr8052/getKtr8052/3";
    //配电监控
    public static String distribution = "ktr8052/getKtr8052/4";
    //点式漏水
    public static final String dotData = "ktr8052/getKtr8052/9";
    //防雷
    public static final String thunder = "ktr8052/getKtr8052/10";

    //定位漏水-实时
    public static final String location = "location/getLocationAll";
    //定位漏水-报警
    public static final String locationAlert = "location/findLocationAlarm/";
    //定位漏水-删除
    public static final String locationDelete = "location/deleteLocation/";
    //定位漏水-添加
    public static final String locationInsert = "location/insertLocation";
    //定位漏水-修改
    public static final String locationChange = "location/updateLocation";
    //定位漏水-IP
    public static final String locationIP = "location/findLocationDeviceIp";


    //分体空调
    public static final String airFission = "fission/findFissionAirManageAll";
    //添加分体空调
    public static final String airFissionAdd = "fission/insertFissionAir";
    //删除分体空调  最后要加上 efmId
    public static final String airFissionDel = "fission/deleteFissionAir/";
    //更新分体空调
    public static final String airFissionChange = "fission/updateFissionAir";
    //精密空调-实时
    public static final String precisionAir = "crac/getCracManageAll";
    //精密空调-报警
    public static final String precisionAirAlert = "crac/findCracAlarm/";
    //精密空调-添加设备
    public static final String precisionAirAdd = "crac/insertCracManage";
    //精密空调-删除设备 {ecmId}
    public static final String precisionAirDelete = "crac/deleteCracManage/";
    //精密空调-修改名称
    public static final String precisionAirChange = "crac/updateCracManage";
    //噪声-实时
    public static final String noise = "noise/selectNoiseManageAll";
    //噪声-报警
    public static final String noiseAlert = "noise/findNoiseAlarm/";
    //噪声-历史
    public static final String noiseHistory = "noise/findNoiseHistory/";
    //噪声-设备管理
    public static final String noiseManage = "noise/selectNoiseManageAll";
    //删除噪声设备
    public static final String deleteNoise = "noise/deleteNoiseManage/";
    //修改噪声设备
    public static final String changeNoise = "noise/updateNoiseManage";
    //添加噪声设备
    public static final String insertNoise = "noise/insertNoiseManage";

    //二氧化碳-实时-设备管理
    public static String carbonReal = "co2/getCo2ManageAll";
    //二氧化碳-报警 {ecmId}/{startTime}/{endTime}
    public static String carbonAlert = "co2/findCo2Alarm/";
    //二氧化碳-插入
    public static String carbonInsert = "co2/insertCo2Manage";
    //二氧化碳-修改
    public static String carbonChange = "co2/updateCo2Manage";
    //二氧化碳-删除 {ecmId}
    public static String carbonDelete = "co2/deleteCo2Manage/";

    //查询粉尘设备
    public static final String dustReal = "dust/getDustAll";
    //查询粉尘报警
    public static final String dustAlert = "dust/findDustAlarm/";
    //插入粉尘设备
    public static final String dustInsert = "dust/insertDust";
    //更新粉尘设备
    public static final String dustChange = "dust/updateDust";
    //删除粉尘设备  最后要加上 id
    public static final String dustDelete = "dust/deleteDust/";

    //有毒气体-实时
    public static final String toxicReal = "poisonous/getPoisonousAll";
    //有毒气体-报警
    public static final String toxicAlert = "poisonous/findPoisonousAlarm/";
    //有毒气体-历史
    public static final String toxicHistory = "poisonous/findPoisonousHistory/";
    //有毒气体-设备管理
    public static final String toxicManage = "poisonous/getPoisonousAll";
    //有毒气体-删除
    public static final String toxicDelete = "poisonous/deletePoisonous/";
    //有毒气体-添加
    public static final String toxicInsert = "poisonous/insertPoisonous";
    //有毒气体-修改
    public static final String toxicUpdate = "poisonous/updatePoisonous";

    //资产管理-实时
    public static String assetReal = "management/selectManagementManageAll";
    //资产管理-报警
    public static String assetAlert = "management/findManagementAlarm/";
    //资产管理-管理
    public static String assetManage = "management/selectManagementManageAll";
    //资产管理-删除设备
    public static String assetDelete = "management/deleteManagementManage/";
    //资产管理-修改
    public static String assetChange = "management/updateManagementManage";
    //资产管理-添加
    public static String assetInsert = "management/insertManagementManage";
    //资产管理-改变颜色
    public static String assetChangeColor = "management/ChangeManagementColor/";

    //电量仪-实时
    public static String electricReal = "electricmeter/findElectricmeterAll";
    //电量仪-报警
    public static String electricAlert = "electricmeter/findElectricmeterAlarmAll/";
    //电量仪-历史
    public static String electricHistory = "electricmeter/findElectricmeterHistory/";
    //电量仪-设备管理
    public static String electricManage = "electricmeter/findElectricmeterAll";
    //电量仪设备添加
    public static String electricityDeviceAdd = "electricmeter/insertElectricmeterManage";
    //电量仪设备修改
    public static String electricityDeviceUpdate = "electricmeter/updateElectricmeterManage";
    //删除电量仪
    public static final String deleteElectricity = "electricmeter/deleteElectricmeterManage/";

    //UPS-实时
    public static String upsReal = "ups/getUPSManageAll";
    //UPS-报警
    public static String upsAlert = "ups/findUPSAlarm/";
    //UPS-设备管理
    public static String upsManage = "ups/getUPSManageAll";
    //UPS-删除
    public static String upsDelete = "ups/deleteUPSManage/";
    //UPS-修改，只能修改名称
    public static String upsChange = "ups/updateUPSManage";
    //UPS-添加
    public static String upsInsert = "ups/insertUPSManage";

    //日志查询接口
    public static String selectSysLog = "log/selectSysLog";

    //查询所有的日志，不分页，用于导出Excel表
    public static String selectLogNoPage = "log/getSysLogNoPage";

    //查询所有设备 IP
    public static String findDeviceIP = "deviceIp/findDeviceIpAll";
    //查询所有设备
    public static String findDevice = "deviceIp/findDeviceAll";

    //查询IP设置列表，包含设备的IP，名称
    public static String findDeviceIPList = "deviceIp/findDeviceAndDeviceIpAll";

    //查询用户管理信息和所拥有权限
    public static String userRoleInfo = "config/findUserAndRoleAll";
    //查询解析信息和所拥有权限
    public static String rolePermissions = "config/getRoleAndPermissionAll";

    //上传用户头像
    public static String uploadIcon = "config/uploadHeadImg";
    //登陆成功后，获取用户的相关信息，发送空的请求即可
    public static String userLoginInfo = "config/findUserByToken";

    //获取所有权限
    public static String fileAllRole = "config/listPermission";
    //添加权限管理
    public static String insertRole = "config/addPermission";
    //删除权限管理 后面要加上 ID
    public static String deleteRole = "config/deletePermission/";
    //更新权限管理
    public static String updateRole = "config/updatePermission";


    //更新角色管理
    public static String updateCharacter = "config/updateRole";
    //添加用户角色
    public static String insertCharacter = "config/addRole";
    //删除用户角色 后面要加上 ID
    public static String deleteCharacter = "config/deleteRole/";


    //添加用户
    public static String addNewUser = "config/insertUseAndRole";
    //添加 / 更新 用户的同时上传图片
    public static String addNewUserIcon = "config/insertOrUpdateUser";
    //删除用户
    public static String delUser = "config/deleteUserById/";
    //更新用户，只更新用户，不更新用户的权限
    public static String updateUser = "config/updateUserById";

    //编辑用户的权限   后面要加上  用户ID/角色ID，角色ID  config/updateUserRoles/36/1,2,3
    public static String updateUserRole = "config/updateUserRoles/";
    public static String updateUserAndRole = "config/updateUserAndRoles";

    //设备管理的通信
    public static String deviceCommunication = "deviceIp/upadateIpConnect/";
    //插入设备
    public static String insertDevice = "deviceIp/insertDeviceIpAll";
    //删除设备
    public static String deviceDelete = "deviceIp/deleteDeviceIpById/";
    //更新设备信息
    public static String deviceUpdate = "deviceIp/updateDeviceIpById";

    //查询8060所有设备的名称和IP
    public static String device8060List = "ktr8060/findKtr8060AndIpAll";

    //查询 报警设置
    public static String deviceAlertList = "deviceIp/findDeviceAlarmAll";

    //查询 资产管理 IP 和 名称
    public static String deviceAssetsList = "management/findManagementAndIpAll";


    //查询所有的8060模块数据
    public static String deviceManage8060 = "ktr8060/findKtr8060All/3";
    //查询所有8060的IP
    public static String deviceManage8060IP = "ktr8060/find8060DeviceIp";
    //查询所有8060的设备
    public static String deviceManage8060Device = "ktr8060/selectKtr8060Device";
    //更新8060的设备
    public static String update8060 = "ktr8060/updateKtr8060";
    //插入8060的设备
    public static String insert8060 = "ktr8060/insertKtr8060";
    //删除8060的设备
    public static String delete8060 = "ktr8060/deleteKtr8060/";

    //查找所有的8052设备
    public static String find8052 = "ktr8052/findKtr8052All/3";

    //查询8052所有设备的名称和IP
    public static String device8052List = "ktr8052/findKtr8052AndIpAll";

    //删除8052设备
    public static String delete8052 = "ktr8052/deleteKtr8052Manage/";
    //8052的IP
    public static String device8052IP = "ktr8052/find8052DeviceIp";
    //8052的所有设备
    public static String device8052AllDevice = "ktr8052/findKtr8052Device";
    //更新8052设备
    public static String update8052 = "ktr8052/updateKtr8052Manage";
    //插入8052设备
    public static String insert8052 = "ktr8052/insertKtr8052Manage";

    //资产管理IP
    public static String assetIP = "management/findManagementDeviceIp";
    //设备管理中的资产管理
    public static String assetManageDevice = "management/selectManagementDeviceAll";
    //插入资产管理
    public static String assetInsertManage = "management/insertManagementDevice";
    //删除资产管理  mId
    public static String assetDeleteManage = "management/deleteManagementDevice/";
    //修改资产管理
    public static String assetUpdateManage = "management/updateManagementDevice";

    //------------------------------------------水质系统

    //溶氧仪-实时
    public static String dissolvedReal = "dissolvedOxygen/findDissolvedOxygenManageAll";
    //溶氧仪-报警
    public static String dissolvedAlert = "dissolvedOxygen/findDissolvedOxygenAlarm/";
    //溶氧仪-历史
    public static String dissolvedHistory = "dissolvedOxygen/findDissolvedOxygenHistory/";
    //溶氧仪-设备管理
    public static String dissolvedManage = "dissolvedOxygen/findDissolvedOxygenManageAll";
    //溶氧仪的添加
    public static String dissolvedInsert = "dissolvedOxygen/insertDissolvedOxygenManage";
    //溶氧仪的更新
    public static String dissolvedUpdate = "dissolvedOxygen/updateDissolvedOxygenManage";
    //溶氧仪的删除
    public static String dissolvedDelete = "dissolvedOxygen/deleteDissolvedOxygenManage/";

    //酸碱仪PH-实时
    public static String acidBaseReal = "phmeter/findPHMeterManageStatus";
    //酸碱仪PH-报警
    public static String acidBaseAlert = "phmeter/findPHMeterAlarm/";
    //酸碱仪PH-历史
    public static String acidBaseHistory = "phmeter/findPHMeterHistory/";
    //酸碱仪PH-设备管理
    public static String acidBaseManage = "phmeter/findPHMeterManageStatus";
    //酸碱仪PH的添加
    public static String acidBaseInsert = "phmeter/insertPHMeterManage";
    //酸碱仪PH的更新
    public static String acidBaseUpdate = "phmeter/updatePHMeterManage";
    //酸碱仪PH的删除
    public static String acidBaseDelete = "phmeter/deletePHMeterManage/";

    //水温-实时
    public static String waterTempReal = "waterTempController/getWaterTempManageAll";
    //水温-报警
    public static String waterTempAlert = "waterTempController/findWaterTempAlarm/";
    //水温-历史
    public static String waterTempHistory = "waterTempController/findWaterTempHistory/";
    //水温-设备管理
    public static String waterTempManage = "waterTempController/getWaterTempManageAll";
    //水温的添加
    public static String waterTempInsert = "waterTempController/insertWaterTempManage";
    //水温的更新
    public static String waterTempUpdate = "waterTempController/updateWaterTempManage";
    //水温的删除
    public static String waterTempDelete = "waterTempController/deleteWaterTempManage/";

    //电导-实时
    public static String conductivityReal = "conductance/getConductanceManageAll";
    //电导-报警
    public static String conductivityAlert = "conductance/findConductanceAlarm/";
    //电导-历史
    public static String conductivityHistory = "conductance/findConductanceHistory/";
    //电导-设备管理
    public static String conductivityManage = "conductance/getConductanceManageAll";
    //电导的添加
    public static String conductivityInsert = "conductance/insertConductanceManage";
    //电导的更新
    public static String conductivityUpdate = "conductance/updateConductanceManage";
    //电导的删除
    public static String conductivityDelete = "conductance/deleteConductanceManage/";

    //水流量-实时
    public static String dischargeReal = "waterFlowController/getWaterFlowManageAll";
    //水流量-报警
    public static String dischargeAlert = "waterFlowController/findWaterFlowAlarm/";
    //水流量-历史
    public static String dischargeHistory = "waterFlowController/findWaterFlowHistory/";
    //水流量-设备管理
    public static String dischargeManage = "waterFlowController/getWaterFlowManageAll";
    //水流量的添加
    public static String dischargeInsert = "waterFlowController/insertWaterFlowManage";
    //水流量的更新
    public static String dischargeUpdate = "waterFlowController/updateWaterFlowManage";
    //水流量的删除
    public static String dischargeDelete = "waterFlowController/deleteWaterFlowManage/";

    //水位监测-实时
    public static String waterLevelReal = "waterLevelController/getWaterLevelManageAll";
    //水位监测-报警
    public static String waterLevelAlert = "waterLevelController/findWaterLevelAlarm/";
    //水位监测-历史
    public static String waterLevelHistory = "waterLevelController/findWaterLevelHistory/";
    //水位监测-设备管理
    public static String waterLevelManage = "waterLevelController/getWaterLevelManageAll";
    //水位监测的添加
    public static String waterLevelInsert = "waterLevelController/insertWaterLevelManage";
    //水位监测的更新
    public static String waterLevelUpdate = "waterLevelController/updateWaterLevelManage";
    //水位监测的删除
    public static String waterLevelDelete = "waterLevelController/deleteWaterLevelManage/";

    //硫化氢-实时
    public static String hydrogenReal = "hydrogenSulfideController/getHydrogenSulfideManageAll";
    //硫化氢-报警
    public static String hydrogenAlert = "hydrogenSulfideController/findHydrogenSulfideAlarm/";
    //硫化氢-历史
    public static String hydrogenHistory = "hydrogenSulfideController/findHydrogenSulfideHistory/";
    //硫化氢-设备管理
    public static String hydrogenManage = "hydrogenSulfideController/getHydrogenSulfideManageAll";
    //硫化氢的添加
    public static String hydrogenInsert = "hydrogenSulfideController/insertHydrogenSulfideManage";
    //硫化氢的更新
    public static String hydrogenUpdate = "hydrogenSulfideController/updateHydrogenSulfideManage";
    //硫化氢的删除
    public static String hydrogenDelete = "hydrogenSulfideController/deleteHydrogenSulfideManage/";

    //盐度-实时
    public static String salinityReal = "salinityController/getSalinityManageAll";
    //盐度-报警
    public static String salinityAlert = "salinityController/findSalinityAlarm/";
    //盐度-历史
    public static String salinityHistory = "salinityController/findSalinityHistory/";
    //盐度-设备管理
    public static String salinityManage = "salinityController/getSalinityManageAll";
    //盐度的添加
    public static String salinityInsert = "salinityController/insertSalinityManage";
    //盐度的更新
    public static String salinityUpdate = "salinityController/updateSalinityManage";
    //盐度的删除
    public static String salinityDelete = "salinityController/deleteSalinityManage/";

    //氨氮-实时
    public static String ammoniaReal = "ammoniaNitrogen/findAmmoniaNitrogenStatus";
    //氨氮-报警
    public static String ammoniaAlert = "ammoniaNitrogen/findAmmoniaNitrogenAlarm/";
    //氨氮-历史
    public static String ammoniaHistory = "ammoniaNitrogen/findAmmoniaNitrogenHistory/";
    //氨氮-设备管理
    public static String ammoniaManage = "ammoniaNitrogen/findAmmoniaNitrogenStatus";
    //氨氮的添加
    public static String ammoniaInsert = "ammoniaNitrogen/insertAmmoniaNitrogenManage";
    //氨氮的更新
    public static String ammoniaUpdate = "ammoniaNitrogen/updateAmmoniaNitrogenManage";
    //氨氮的删除
    public static String ammoniaDelete = "ammoniaNitrogen/deleteAmmoniaNitrogenManage/";

    //亚硝酸盐-实时
    public static String nitriteReal = "nitriteController/findNitriteManageStatus";
    //亚硝酸盐-报警
    public static String nitriteAlert = "nitriteController/findNitriteAlarm/";
    //亚硝酸盐-历史
    public static String nitriteHistory = "nitriteController/findNitriteHistory/";
    //亚硝酸盐-设备管理
    public static String nitriteManage = "nitriteController/findNitriteManageStatus";
    //亚硝酸盐的添加
    public static String nitriteInsert = "nitriteController/insertNitriteManage";
    //亚硝酸盐的更新
    public static String nitriteUpdate = "nitriteController/updateNitriteManage";
    //亚硝酸盐的删除
    public static String nitriteDelete = "nitriteController/deleteNitriteManage/";

    //水压力-实时
    public static String waterPressureReal = "waterPressureController/getWaterPressureManageAll";
    //水压力-报警
    public static String waterPressureAlert = "waterPressureController/findWaterPressureAlarm/";
    //水压力-历史
    public static String waterPressureHistory = "waterPressureController/findWaterPressureHistory/";
    //水压力-设备管理
    public static String waterPressureManage = "waterPressureController/getWaterPressureManageAll";
    //水压力的添加
    public static String waterPressureInsert = "waterPressureController/insertWaterPressureManage";
    //水压力的更新
    public static String waterPressureUpdate = "waterPressureController/updateWaterPressureManage";
    //水压力的删除
    public static String waterPressureDelete = "waterPressureController/deleteWaterPressureManage/";

    //氨气-实时
    public static String ammonia2Real = "ammoniaController/getAmmoniaManageAll";
    //氨气-报警
    public static String ammonia2Alert = "ammoniaController/findAmmoniaAlarm/";
    //氨气-历史
    public static String ammonia2History = "ammoniaController/findAmmoniaHistory/";
    //氨气-设备管理
    public static String ammonia2Manage = "ammoniaController/getAmmoniaManageAll";
    //氨气的添加
    public static String ammonia2Insert = "ammoniaController/insertAmmoniaManage";
    //氨气的更新
    public static String ammonia2Update = "ammoniaController/updateAmmoniaManage";
    //氨气的删除
    public static String ammonia2Delete = "ammoniaController/deleteAmmoniaManage/";

    //余氯-实时
    public static String residualReal = "residualChlorineController/findResidualChlorineManageStatus";
    //余氯-报警
    public static String residualAlert = "residualChlorineController/findResidualChlorineAlarm/";
    //余氯-历史
    public static String residualHistory = "residualChlorineController/findResidualChlorineHistory/";
    //余氯-设备管理
    public static String residualManage = "residualChlorineController/findResidualChlorineManageStatus";
    //余氯的添加
    public static String residualInsert = "residualChlorineController/insertResidualChlorineManage";
    //余氯的更新
    public static String residualUpdate = "residualChlorineController/updateResidualChlorineManage";
    //余氯的删除
    public static String residualDelete = "residualChlorineController/deleteResidualChlorineManage/";

    //浑浊度-实时
    public static String turbidityReal = "muddyController/getMuddyManageAll";
    //浑浊度-报警
    public static String turbidityAlert = "muddyController/findMuddyAlarm/";
    //浑浊度-历史
    public static String turbidityHistory = "muddyController/findMuddyHistory/";
    //浑浊度-设备管理
    public static String turbidityManage = "muddyController/getMuddyManageAll";
    //浑浊度的添加
    public static String turbidityInsert = "muddyController/insertMuddyManage";
    //浑浊度的更新
    public static String turbidityUpdate = "muddyController/updateMuddyManage";
    //浑浊度的删除
    public static String turbidityDelete = "muddyController/deleteMuddyManage/";

    //污水流量-实时
    public static String sewageReal = "sewageDischargeController/findSewageDischargeManageStatus";
    //污水流量-报警
    public static String sewageAlert = "sewageDischargeController/findSewageDischargeAlarm/";
    //污水流量-历史
    public static String sewageHistory = "sewageDischargeController/findSewageDischargeHistory/";
    //污水流量-设备管理
    public static String sewageManage = "sewageDischargeController/findSewageDischargeManageStatus";
    //污水流量的添加
    public static String sewageInsert = "sewageDischargeController/insertSewageDischargeManage";
    //污水流量的更新
    public static String sewageUpdate = "sewageDischargeController/updateSewageDischargeManage";
    //污水流量的删除
    public static String sewageDelete = "sewageDischargeController/deleteSewageDischargeManage/";

    //叶绿素-实时
    public static String chlorophyllReal = "chlorophyllController/findChlorophyllManageStatus";
    //叶绿素-报警
    public static String chlorophyllAlert = "chlorophyllController/findChlorophyllAlarm/";
    //叶绿素-历史
    public static String chlorophyllHistory = "chlorophyllController/findChlorophyllHistory/";
    //叶绿素-设备管理
    public static String chlorophyllManage = "chlorophyllController/findChlorophyllManageStatus";
    //叶绿素的添加
    public static String chlorophyllInsert = "chlorophyllController/insertChlorophyllManage";
    //叶绿素的更新
    public static String chlorophyllUpdate = "chlorophyllController/updateChlorophyllManage";
    //叶绿素的删除
    public static String chlorophyllDelete = "chlorophyllController/deleteChlorophyllManage/";

    //蓝藻素-实时
    public static String cyaninReal = "cyaninController/findCyaninManageStatus";
    //蓝藻素-报警
    public static String cyaninAlert = "cyaninController/findCyaninAlarm/";
    //蓝藻素-历史
    public static String cyaninHistory = "cyaninController/findCyaninHistory/";
    //蓝藻素-设备管理
    public static String cyaninManage = "cyaninController/findCyaninManageStatus";
    //蓝藻素的添加
    public static String cyaninInsert = "cyaninController/insertCyaninManage";
    //蓝藻素的更新
    public static String cyaninUpdate = "cyaninController/updateCyaninManage";
    //蓝藻素的删除
    public static String cyaninDelete = "cyaninController/deleteCyaninManage/2";

    //总磷水质-实时
    public static String phosphorusReal = "phosphorusController/getPhosphorusManageAll";
    //总磷水质-报警
    public static String phosphorusAlert = "phosphorusController/findPhosphorusAlarm/";
    //总磷水质-历史
    public static String phosphorusHistory = "phosphorusController/findPhosphorusHistory/";
    //总磷水质-设备管理
    public static String phosphorusManage = "phosphorusController/getPhosphorusManageAll";
    //总磷水质的添加
    public static String phosphorusInsert = "phosphorusController/insertPhosphorusManage";
    //总磷水质的更新
    public static String phosphorusUpdate = "phosphorusController/updatePhosphorusManage";
    //总磷水质的删除
    public static String phosphorusDelete = "deletePhosphorusManage/";

    //Codcr分析-实时
    public static String codrReal = "codController/getCODManageAll";
    //Codcr分析-报警
    public static String codrAlert = "codController/findCODAlarm/";
    //Codcr分析-历史
    public static String codrHistory = "codController/findCODHistory/";
    //Codcr分析-设备管理
    public static String codrManage = "codController/getCODManageAll";
    //Codcr分析的添加
    public static String codrInsert = "codController/insertCODManage";
    //Codcr分析的更新
    public static String codrUpdate = "codController/updateCODManage";
    //Codcr分析的删除
    public static String codrDelete = "codController/deleteCODManage/";

    //ORP变送器-实时
    public static String orpReal = "ORPMeter/findORPMeterStatus";
    //ORP变送器-报警
    public static String orpAlert = "ORPMeter/findORPMeterAlarm/";
    //ORP变送器-历史
    public static String orpHistory = "ORPMeter/findORPMeterHistory/";
    //ORP变送器-设备管理
    public static String orpManage = "ORPMeter/findORPMeterStatus";
    //ORP的添加
    public static String orpInsert = "ORPMeter/insertORPMeterManage";
    //ORP的更新
    public static String orpUpdate = "ORPMeter/updateORPMeterManage";
    //ORP的删除
    public static String orpDelete = "ORPMeter/deleteORPMeterManage/";

    //流通池-实时
    public static String circulationReal = "flowCellMeter/findFlowCellMeterManageAll";
    //流通池-报警
    public static String circulationAlert = "flowCellMeter/findFlowCellMeterAlarm/";
    //流通池-历史
    public static String circulationHistory = "flowCellMeter/findFlowCellMeterHistory/";
    //流通池-设备管理
    public static String circulationManage = "flowCellMeter/findFlowCellMeterManageAll";
    //流通池的添加
    public static String circulationInsert = "flowCellMeter/insertFlowCellMeterManage";
    //流通池的更新
    public static String circulationUpdate = "flowCellMeter/updateFlowCellMeterManage";
    //流通池的删除
    public static String circulationDelete = "flowCellMeter/deleteFlowCellMeterManage/";

    //尿素-实时
    public static String ureaReal = "ureaController/findUreaManageStatus";
    //尿素-报警
    public static String ureaAlert = "ureaController/findUreaAlarm/";
    //尿素-历史
    public static String ureaHistory = "ureaController/findUreaHistory/";
    //尿素-设备管理
    public static String ureaManage = "ureaController/findUreaManageStatus";
    //尿素的添加
    public static String ureaInsert = "ureaController/insertUreaManage";
    //尿素的更新
    public static String ureaUpdate = "ureaController/updateUreaManage";
    //尿素的删除
    public static String ureaDelete = "ureaController/deleteUreaManage/";

    //PT100-实时
    public static String pt100Real = "humiturept100/findHumiturePT100Status";
    //PT100-报警
    public static String pt100Alert = "humiturept100/findHumiturePT100Alarm/";
    //PT100-历史
    public static String pt100History = "humiturept100/findHumiturePT100History/";
    //PT100-设备管理
    public static String pt100Manage = "humiturept100/findHumiturePT100Status";
    //PT100的添加
    public static String pt100Insert = "humiturept100/insertHumiturePT100Manage";
    //PT100的更新
    public static String pt100Update = "humiturept100/updateHumiturePT100Manage";
    //PT100的删除
    public static String pt100Delete = "humiturept100/deleteHumiturePT100Manage/";

    //DS18B20-实时
    public static String DS18B20Real = "humiture18B20/findHumiture18B20Status";
    //DS18B20-报警
    public static String DS18B20Alert = "humiture18B20/findHumiture18B20Alarm/";
    //DS18B20-历史
    public static String DS18B20History = "humiture18B20/findHumiture18B20History/";
    //DS18B20-设备管理
    public static String DS18B20Manage = "humiture18B20/findHumiture18B20Status";
    //DS18B20的添加
    public static String DS18B20Insert = "humiture18B20/insertHumiture18B20Manage";
    //DS18B20的更新
    public static String DS18B20Update = "humiture18B20/updateHumiture18B20Manage";
    //DS18B20的删除
    public static String DS18B20Delete = "humiture18B20/deleteHumiture18B20Manage/";

    //气压强度-实时
    public static String gasPressureReal = "gasPressureController/findGasPressureManageStatus";
    //气压强度-报警
    public static String gasPressureAlert = "gasPressureController/findGasPressureAlarm/";
    //气压强度-历史
    public static String gasPressureHistory = "gasPressureController/findGasPressureHistory/";
    //气压强度-设备管理
    public static String gasPressureManage = "gasPressureController/findGasPressureManageStatus";
    //气压强度的添加
    public static String gasPressureInsert = "gasPressureController/insertGasPressureManage";
    //气压强度的更新
    public static String gasPressureUpdate = "gasPressureController/updateGasPressureManage";
    //气压强度的删除
    public static String gasPressureDelete = "gasPressureController/deleteGasPressureManage/";

    //光照度-实时
    public static String illuminationReal = "lightController/getLightManageAll";
    //光照度-报警
    public static String illuminationAlert = "lightController/findLightAlarm/";
    //光照度-历史
    public static String illuminationHistory = "lightController/findLightHistory/";
    //光照度-设备管理
    public static String illuminationManage = "lightController/getLightManageAll";
    //光照度的添加
    public static String illuminationInsert = "lightController/insertLightManage";
    //光照度的更新
    public static String illuminationUpdate = "lightController/updateLightManage";
    //光照度的删除
    public static String illuminationDelete = "lightController/deleteLightManage/";

    //贴片式温度-实时
    public static String patchTempReal = "patchController/findPatchTemperatureManageStatus";
    //贴片式温度-报警
    public static String patchTempAlert = "patchController/findPatchTemperatureAlarm/";
    //贴片式温度-历史
    public static String patchTempHistory = "patchController/findPatchTemperatureHistory/";
    //贴片式温度-设备管理
    public static String patchTempManage = "patchController/findPatchTemperatureManageStatus";
    //贴片式温度的添加
    public static String patchTempInsert = "patchController/insertPatchTemperatureManage";
    //贴片式温度的更新
    public static String patchTempUpdate = "patchController/updatePatchTemperatureManage";
    //贴片式温度的删除
    public static String patchTempDelete = "patchController/deletePatchTemperatureManage/";

    //光纤电缆测温-实时
    public static String fiberReal = "opticalController/findOpticalFiberTempManageStatus";
    //光纤电缆测温-报警
    public static String fiberAlert = "opticalController/findOpticalFiberTempAlarm/";
    //光纤电缆测温-历史
    public static String fiberHistory = "opticalController/findOpticalFiberTempHistory/";
    //光纤电缆测温-设备管理
    public static String fiberManage = "opticalController/findOpticalFiberTempManageStatus";
    //光纤电缆测温的添加
    public static String fiberInsert = "opticalController/insertOpticalFiberTempManage";
    //光纤电缆测温的更新
    public static String fiberUpdate = "opticalController/updateOpticalFiberTempManage";
    //光纤电缆测温的删除
    public static String fiberDelete = "opticalController/deleteOpticalFiberTempManage/";

    //压力传感器-实时
    public static String weightReal = "weighController/findWeighManageStatus";
    //压力传感器-报警
    public static String weightAlert = "weighController/findWeighAlarm/";
    //压力传感器-历史
    public static String weightHistory = "weighController/findWeighHistory/";
    //压力传感器-设备管理
    public static String weightManage = "weighController/findWeighManageStatus";
    //压力传感器的添加
    public static String weightInsert = "weighController/insertWeighManage";
    //压力传感器的更新
    public static String weightUpdate = "weighController/updateWeighManage";
    //压力传感器的删除
    public static String weightDelete = "weighController/deleteWeighManage/";

    //照度传感器-实时
    public static String illumination2Real = "lightController/findLightManageStatus";
    //照度传感器-报警
    public static String illumination2Alert = "lightController/findLightAlarm/";
    //照度传感器-历史
    public static String illumination2History = "lightController/findLightHistory/";
    //照度传感器-设备管理
    public static String illumination2Manage = "lightController/findLightManageStatus";
    //照度传感器的添加
    public static String illumination2Insert = "lightController/insertLightManage";
    //照度传感器的更新
    public static String illumination2Update = "lightController/updateLightManage";
    //照度传感器的删除
    public static String illumination2Delete = "lightController/deleteLightManage/";

    //剩余电流电气-实时
    public static String electricalFireReal = "electricalFireAlarm/findElectricalFireAlarmStatus";
    //剩余电流电气-报警
    public static String electricalFireAlert = "electricalFireAlarm/findElectricalFireAlarmAlarm/";
    //剩余电流电气-历史
    public static String electricalFireHistory = "electricalFireAlarm/findElectricalFireAlarmHistory/";
    //剩余电流电气-设备管理
    public static String electricalFireManage = "electricalFireAlarm/findElectricalFireAlarmStatus";
    //剩余电流电气的添加
    public static String electricalInsert = "electricalFireAlarm/insertElectricalFireAlarmManage";
    //剩余电流电气的更新
    public static String electricalUpdate = "electricalFireAlarm/updateElectricalFireAlarmManage";
    //剩余电流电气的删除
    public static String electricalDelete = "electricalFireAlarm/deleteElectricalFireAlarmManageById/";

    //风速风向-实时
    public static String windReal = "windSpeedController/findWindSpeedManageStatus";
    //风速风向-报警
    public static String windAlert = "windSpeedController/findWindSpeedAlarm/";
    //风速风向-历史
    public static String windHistory = "windSpeedController/findWindSpeedHistory/";
    //风速风向-设备管理
    public static String windManage = "windSpeedController/findWindSpeedManageStatus";
    //风速风向的添加
    public static String windInsert = "windSpeedController/insertWindSpeedManage";
    //风速风向的更新
    public static String windUpdate = "windSpeedController/updateWindSpeedManage";
    //风速风向的删除
    public static String windDelete = "windSpeedController/deleteWindSpeedManage/";

    //温控器-实时
    public static String transformerReal = "transformer/findTransformerStatus";
    //温控器-报警
    public static String transformerAlert = "transformer/findTransformerAlarm/";
    //温控器-历史
    public static String transformerHistory = "transformer/findTransformerHistory/";
    //温控器-设备管理
    public static String transformerManage = "transformer/findTransformerStatus";
    //温控器的添加
    public static String transformerInsert = "transformer/insertTransformerManage";
    //温控器的更新
    public static String transformerUpdate = "transformer/updateTransformerManage";
    //温控器的删除
    public static String transformerDelete = "transformer/deleteTransformerManageById/";

    //室外温湿度-实时
    public static String outdoorTempReal = "outdoorHumiture/findOutdoorHumitureManageAll";
    //室外温湿度-报警
    public static String outdoorTempAlert = "outdoorHumiture/findOutdoorHumitureAlarm/";
    //室外温湿度-历史
    public static String outdoorTempHistory = "outdoorHumiture/findOutdoorHumitureHistory/";
    //室外温湿度-设备管理
    public static String outdoorTempManage = "outdoorHumiture/findOutdoorHumitureManageAll";
    //室外温湿度的添加
    public static String outdoorTempInsert = "outdoorHumiture/insertOutdoorHumitureManage";
    //室外温湿度的更新
    public static String outdoorTempUpdate = "outdoorHumiture/updateOutdoorHumitureManage";
    //室外温湿度的删除
    public static String outdoorTempDelete = "outdoorHumiture/deleteOutdoorHumitureManageById/";

    //风管温度-实时
    public static String windSenseReal = "lightController/findLightManageStatus";
    //风管温度-报警
    public static String windSenseAlert = "lightController/findLightAlarm/";
    //风管温度-历史
    public static String windSenseHistory = "lightController/findLightHistory/";
    //风管温度-设备管理
    public static String windSenseManage = "lightController/findLightManageStatus";
    //的添加
    public static String windSenseInsert = "";
    //的更新
    public static String windSenseUpdate = "";
    //的删除
    public static String windSenseDelete = "";

    //土壤水分温度-实时
    public static String soilmoistureReal = "humiture/findHumitureStatus";
    //土壤水分温度-报警
    public static String soilmoistureAlert = "humiture/findHumitureAlarm/";
    //土壤水分温度-历史
    public static String soilmoistureHistory = "humiture/findHumitureHistory/";
    //土壤水分温度-设备管理
    public static String soilmoistureManage = "humiture/findHumitureStatus";
    //的添加
    public static String soilmoistureInsert = "";
    //的更新
    public static String soilmoistureUpdate = "";
    //的删除
    public static String soilmoistureDelete = "";

    //室外防水温湿-实时
    public static String waterProofReal = "humiture/findHumitureStatus";
    //室外防水温湿-报警
    public static String waterProofAlert = "humiture/findHumitureAlarm/";
    //室外防水温湿-历史
    public static String waterProofHistory = "humiture/findHumitureHistory/";
    //室外防水温湿-设备管理
    public static String waterProofManage = "humiture/findHumitureStatus";
    //的添加
    public static String waterProofInsert = "";
    //的更新
    public static String waterProofUpdate = "";
    //的删除
    public static String waterProofDelete = "";

    //单体蓄电池-实时
    public static String singleBatteryReal = "batteryController/findBatteryManageStatus";
    //单体蓄电池-报警
    public static String singleBatteryAlert = "batteryController/findBatteryAlarm/";
    //单体蓄电池-历史
    public static String singleBatteryHistory = "batteryController/findBatteryHistory/";
    //单体蓄电池-设备管理
    public static String singleBatteryManage = "batteryController/findBatteryManageStatus";
    //单体蓄电池的添加
    public static String singleBatteryInsert = "batteryController/insertBatteryManage";
    //单体蓄电池的更新
    public static String singleBatteryUpdate = "batteryController/updateBatteryManage";
    //单体蓄电池的删除
    public static String singleBatteryDelete = "batteryController/deleteBatteryManage/";

    //发电机监控-实时
    public static String generatorReal = "alternatorController/findAlternatorManageStatus";
    //发电机监控-报警
    public static String generatorAlert = "alternatorController/findAlternatorAlarm/";
    //发电机监控-历史
    public static String generatorHistory = "alternatorController/findAlternatorHistory/";
    //发电机监控-设备管理
    public static String generatorManage = "alternatorController/findAlternatorManageStatus";
    //发电机监控的添加
    public static String generatorInsert = "alternatorController/insertAlternatorManage";
    //发电机监控的更新
    public static String generatorUpdate = "alternatorController/updateAlternatorManage";
    //发电机监控的删除
    public static String generatorDelete = "alternatorController/deleteAlternatorManage/";

    //--------------以下是每个设备获取的 设备IP 的URL

    //温湿度的IP
    public static String tempIP = "humiture/findHumitureDeviceIp";
    //定位漏水的IP
    public static String locatingIP = "location/findLocationDeviceIp";
    //分体空调的IP
    public static String splitAirIP = "fission/findFissionDeviceIp";
    //精密空调的IP
    public static String precisionAirIP = "crac/findCracDeviceIp";
    //二氧化碳的IP
    public static String carbonIP = "co2/findCo2DeviceIp";
    //有毒气体的IP
    public static String poisonousIP = "poisonous/findPoisonousDeviceIp";
    //粉尘的IP
    public static String dustIP = "dust/findDustDeviceIp";
    //噪音的IP
    public static String noiseIP = "noise/findNoiseDeviceIp";
    //PT100的IP
    public static String pt100IP = "humiturept100/findHumiturePT100DeviceIp";
    //18b20探头的IP
    public static String ds18b20IP = "humiture18B20/findHumiture18B20DeviceIp";
    //气压强度的IP
    public static String gasPressureIP = "gasPressureController/findGasPressureDeviceIp";
    //光照强度的IP
    public static String lightIP = "lightController/findLightDeviceIp";
    //贴片式温度的IP
    public static String smtIP = "patchController/findPatchTemperatureDeviceIp";
    //电缆测温的IP
    public static String fiberIP = "opticalController/findOpticalFiberTempDeviceIp";
    //称重传感器的IP
    public static String weightIP = "weighController/findWeighDeviceIp";
    //照度传感器的IP
    public static String illuminationIP = "lightController/findLightDeviceIp";
    //剩余电流的IP
    public static String surplusIP = "electricalFireAlarm/findElectricalFireAlarmDeviceIp";
    //风速风向的IP
    public static String windIP = "windSpeedController/findWindSpeedDeviceIp";
    //变压器温控器的IP
    public static String transformerIP = "transformer/findTransformerDeviceIp";
    //电缆贴片的IP
    public static String patchIP = "lightController/findLightDeviceIp";
    //风管温度的IP
    public static String windSenseIP = "lightController/findLightDeviceIp";
    //土壤水分的IP
    public static String soilIP = "humiture/findHumitureDeviceIp";
    //室外防水的IP
    public static String waterproofIP = "humiture/findHumitureDeviceIp";
    //室外温度的IP
    public static String outdoorIP = "outdoorHumiture/findOutdoorHumitureDeviceIp";
    //电量仪的IP
    public static String electricMeterIP = "electricmeter/findElectricmeterDeviceIp";
    //ups的IP
    public static String upsIP = "ups/findUPSDeviceIp";
    //单体蓄电池的IP
    public static String singleBatteryIP = "batteryController/findBatteryDeviceIp";
    //发电机监控的IP
    public static String generatorIP = "alternatorController/findAlternatorDeviceIp";
    //氨气的IP
    public static String ammoniaIP = "ammoniaController/findAmmoniaDeviceIp";
    //余氯的IP
    public static String residualIP = "residualChlorineController/findResidualChlorineDeviceIp";
    //浑浊度的IP
    public static String turbidityIP = "muddyController/findMuddyDeviceIp";
    //水位的IP
    public static String waterLevelIP = "waterLevelController/findWaterLevelDeviceIp";
    //溶氧仪的IP
    public static String dissolvedIP = "dissolvedOxygen/findDissolvedOxygenDeviceIp";
    //水温的IP
    public static String waterTempIP = "waterTempController/findWaterTempDeviceIp";
    //水流量的IP
    public static String waterFlowIP = "waterFlowController/findWaterFlowDeviceIp";
    //水压力的IP
    public static String waterPressureIP = "waterPressureController/findWaterPressureDeviceIp";
    //污水流量的IP
    public static String sewageIP = "sewageDischargeController/findSewageDischargeDeviceIp";
    //酸碱仪的IP
    public static String phmeterIP = "phmeter/findPHMeterDeviceIp";
    //盐度的IP
    public static String salinityIP = "salinityController/findSalinityDeviceIp";
    //硫化氢的IP
    public static String hydrogenIP = "hydrogenSulfideController/findHydrogenSulfideDeviceIp";
    //亚硝酸盐的IP
    public static String nitriteIP = "nitriteController/findNitriteDeviceIp";
    //氨氮的IP
    public static String ammoniaNitrogenIP = "ammoniaNitrogen/findAmmoniaNitrogenDeviceIp";
    //电导的IP
    public static String conductanceIP = "conductance/findConductanceDeviceIp";
    //叶绿素的IP
    public static String chlorophyllIP = "chlorophyllController/findChlorophyllDeviceIp";
    //蓝藻素的IP
    public static String cyaninIP = "cyaninController/findCyaninDeviceIp";
    //总磷水质的IP
    public static String totalLinIP = "phosphorusController/findPhosphorusDeviceIp";
    //Codcr分析的IP
    public static String codcrIP = "codController/findCODDeviceIp";
    //ORP变送器的IP
    public static String orptransmIP = "ORPMeter/findORPMeterDeviceIp";
    //流通池的IP
    public static String circulatorIP = "flowCellMeter/findFlowCellMeterDeviceIp";
    //氧气变送器的IP
    public static String oxygenIP = "waterFlowController/findWaterFlowDeviceIp";
    //尿素变送器的IP
    public static String ureaIP = "ureaController/findUreaDeviceIp";

    //视频监控获取账号
    public static final String videoAccount = "video/findVideoManageAll/8000";
    //添加视频账号
    public static final String videoAddAccount = "video/insertVideoManage";
    //删除视频账号  最后要传入 vId
    public static final String videoDelAccount = "video/deleteVideoManage/";
    //修改视频账号
    public static final String videoChangeAccount = "video/updateVideoManage";

    //获取后台的IP地址
    public static final String serverIP = "getIp";

    //服务器
    public static final String systemServer = "sysMsgController/getSystemMessage";

    //查询所有用户
    public static final String userAll = "config/findUserAll";

    //根据时间查询主页报警数据，后面需要加上 开始时间/结束时间
    public static final String findAllAlertMsg = "totalAlarm/getTotalAlarmTime/";

    //更新报警信息的 是否已读 状态，后面要加上报警的 id
    public static final String updateAlertStatus = "totalAlarm/updateIsRead/";

    //获取主页展示的数据
    public static final String mainMessage = "homePage/getTotalMessage";

    //设置Cookies的过期时间
    public static final String cookies = "getCookie";

    //查询已读和未读
    public static final String isReadAlert = "totalAlarm/getTotalAlarmAll/";

    //更新报警设置信息
    public static final String alertInfoUpdate = "deviceIp/updateDeviceAlarmById";

    //更新报警设置的 报警方式
    public static final String alertUpdateAway = "deviceIp/updateDeviceAlarmAwayById";

    //获取设备的显示和隐藏
    public static final String menuConfig = "homePage/getDeviceIsShow";

    //获取电量仪的设备数据
    public static final String elecDataAll = "electricmeter/findElectricmeterDataAll";


}
