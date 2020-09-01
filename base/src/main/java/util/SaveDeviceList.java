package util;

import org.litepal.LitePal;

import java.util.List;

/**
 * 设备列表中的 增 删 改 查，暂时没有用
 */
public class SaveDeviceList {
    //本类引用
    private volatile static SaveDeviceList saveDeviceList;

    /**
     * 单例工具类的引用
     * @return SaveDeviceList
     */
    public static SaveDeviceList getInstance() {
        if (saveDeviceList == null)
            synchronized (SaveDeviceList.class) {
                if (saveDeviceList == null)
                    saveDeviceList = new SaveDeviceList();
            }

        return saveDeviceList;
    }

    /**
     * 保存设备列表
     * @param listMaps
     */
    public void saveList(List<DeviceListMap> listMaps){
        LitePal.saveAll(listMaps);
    }

    /**
     * 保存单条数据
     * @param deviceListMap 需要保存的数据
     */
    public void saveDeviceListData(DeviceListMap deviceListMap) {

        deviceListMap.save();

    }

    /**
     * 只更新 isShow 字段
     * @param isShow 是否显示
     * @param condition 更新字段的条件
     */
    public void updateIsShow(boolean isShow, String condition) {

        DeviceListMap updateIsShow = new DeviceListMap();
        //必须 isShow 为 false ，则恢复默认值
        if (!isShow)
            updateIsShow.setToDefault("isShow");
        else
            updateIsShow.setShow(isShow);
        //更新数据
        updateIsShow.updateAll(condition);

    }

    /**
     * 只更新 标题 字段
     * @param title 更新的内容标题
     * @param condition 更新标题的条件
     */
    public void updateTitle(String title, String condition) {
        DeviceListMap updateTitle = new DeviceListMap();
        updateTitle.setTitle(title);
        updateTitle.updateAll(condition);
    }

    /**
     * 更新图片的路径
     * @param imagePath 新的图片路径
     * @param condition 更新的条件
     */
    public void updateImagePath(String imagePath, String condition) {
        DeviceListMap updateImagePath = new DeviceListMap();
        updateImagePath.setImagePath(imagePath);
        updateImagePath.updateAll(condition);
    }

    /**
     * 更新一整条数据
     * @param deviceListMap 更新一整条数据的对象
     * @param condition 更新的条件
     */
    public void updateList(DeviceListMap deviceListMap, String condition) {

        deviceListMap.updateAll(condition);

    }

    /**
     * 查询 设备列表 中所有数据
     * @return 查询设备列表的数据
     */
    public List<DeviceListMap> findAllDeviceList() {

        return LitePal.findAll(DeviceListMap.class);

    }

    /**
     * 只查询 isShow 字段
     * @param isShow 查询true显示 或者 false不显示的
     * @return 根据 isShow 查询到的结果
     */
    public List<DeviceListMap> findFromIsShow(boolean isShow) {

        if (isShow)
            return LitePal.where("isShow == 1").find(DeviceListMap.class);
        else
            return LitePal.where("isShow == 0").find(DeviceListMap.class);

    }

    /**
     * 查询指定 Title 字段的数据
     * @param title 需要查询的标题
     * @return 根据标题查询到的结果
     */
    public List<DeviceListMap> findFromTitle(String title) {
        return LitePal.where("title == " + "'" +title + "'").find(DeviceListMap.class);
    }

    /**
     * 根据 标题 来查询对应的图片路径字段
     * @param title 查询条件
     * @return 查询的结果
     */
    public List<DeviceListMap> findFromImagePath(String title) {
        return LitePal.where("title == " + "'" +title + "'").find(DeviceListMap.class);
    }

    /**
     * 根据条件查询设备列表中的数据
     * @param condition 查询条件
     * @return 查询到的数据
     */
    public List<DeviceListMap> findDeviceListMap(String condition) {

        return LitePal.where(condition).find(DeviceListMap.class);

    }

    /**
     * 根据 ID 删除数据
     * @param deviceListMapID 需要删除数据的ID
     */
    public void deleteDeviceListMap(int deviceListMapID) {
        LitePal.delete(DeviceListMap.class, deviceListMapID);
    }

    /**
     * 删除表中所有的数据
     */
    public void deleteAllDeviceListMap() {
        LitePal.deleteAll(DeviceListMap.class);
    }

    /**
     * 删除表中所有符合条件的数据
     * @param condition 删除所有数据的条件
     */
    public void deleteAllConditions(String condition) {

        LitePal.deleteAll(DeviceListMap.class, condition);

    }

}
