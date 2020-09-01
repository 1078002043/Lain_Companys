package computer_room.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.device_detail_lib.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.swipe.SwipeItemManagerInterface;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.ActionControl;
import action.SaveInterface;
import base.DynamicDeviceManage;
import bean.Device_Detail_AddBean;
import computer_room.adapter.BaseDeviceDetailManageAdapter;
import computer_room.adapter.SplitAirAdapter;
import computer_room.bean.SplitAirBean;
import computer_room.i_interface.I_SplitAir;
import computer_room.present.SplitAirPresenter;
import http.MyGson;
import http.OkHttpUtil;
import i_v.I_DeviceDetailManageLink;
import util.DynamicMaterialEdit;
import util.LainNewApi;

/**
 * 分体空调
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/11 20 : 52
 */
public class SplitAirConditioner extends Base8052Fragment<SplitAirBean, SplitAirBean> implements I_SplitAir, I_DeviceDetailManageLink<Device_Detail_AddBean> {
    //列表数据
    private List<SplitAirBean> splitAirBeans = new ArrayList<>();
    //Presenter
    private SplitAirPresenter presenter;
    //设备列表适配器
    private SplitAirAdapter splitAdapter;
    //设备管理的数据
    private ArrayList<Device_Detail_AddBean> addBeans = new ArrayList<>();
    //修改设备的URL
    private String changeUrl;

    private BaseDeviceDetailManageAdapter<Device_Detail_AddBean, SplitAirBean> manageAdapter;

    //分体空调的设备管理列表
//    public SplitAir_Add deviceSplitAir_AddAdapter;

    public SplitAirConditioner(String linkReal8052, String linkAlert8052) {
        super(linkReal8052, linkAlert8052);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化 Presenter
        presenter = new SplitAirPresenter();

        //分体空调列表
        UltimateRecyclerView splitAirRecycler = viewList.get(0).findViewById(R.id.split_air_recycler);
        //实时适配器
        splitAdapter = new SplitAirAdapter(getActivity(), splitAirBeans, R.layout.split_air_template);
        splitAirRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        splitAirRecycler.setAdapter(splitAdapter);

        //初始化实时数据列表
        ((DefaultItemAnimator)splitAirRecycler.getItemAnimator()).setSupportsChangeAnimations(false);

        initDeviceManage(1,"分体空调");

        //设备管理的列表
        String deleteUrl = LainNewApi.airFissionDel;
        changeUrl = LainNewApi.airFissionChange;
        manageAdapter = new BaseDeviceDetailManageAdapter<>(addBeans,splitAirBeans,getActivity(), R.layout.split_air_manage_swiper, deleteUrl, changeUrl,this,"分体空调");
//        deviceSplitAir_AddAdapter = new SplitAir_Add(addBeans,splitAirBeans,getActivity(), R.layout.split_air_manage_swiper, deleteUrl, changeUrl);
        manageAdapter.setMode(SwipeItemManagerInterface.Mode.Single);

        //只显示实时数据，设备管理
        setToNavShow(true,false,false,true);

        //设置设备的信息
        newDeviceRecycler.setAdapter(manageAdapter);
        //实时数据
        base8052Present.queryRealData(SplitAirBean.class);
        //执行所有初始化工作后，再获取设备的IP
    }

    @Override
    public List<Integer> getViewPagerView() {
        ArrayList<Integer> viewList = new ArrayList<>();
        //实时数据
        viewList.add(R.layout.split_air_real_time);
        //设备管理
        viewList.add(R.layout.device_detail_manage);
        return viewList;
    }

    /**
     * 实时获取数据
     */
    @Override
    public void run() {
    }

    @Override
    protected void responseAlert8052() {

    }

    @Override
    protected void responseReal8052() {
        splitAirBeans.clear();
        splitAirBeans.addAll(getRealList());
        splitAdapter.notifyDataSetChanged();
        //设备管理
        devicesManage(splitAirBeans);
    }

    //实时数据
    @Override
    public void dealWitchReal(String requestTag, String requestUrl, String responseStr) {

    }

    @Override
    public void devicesManage(List<SplitAirBean> dataList) {
        //先清空原有的设备数据
        addBeans.clear();
        //适配添加设备的数据
        for (SplitAirBean b :
                dataList) {

            String ip = String.valueOf(b.getEfmAddress());

            Device_Detail_AddBean bean = new Device_Detail_AddBean();
            bean.setDeviceName(b.getEfmName());
            bean.setIp(ip);
            bean.setPosition(String.valueOf(b.getEfmAddress()));
            bean.setEhmId(String.valueOf(b.getEfmId()));

//            bean.setImageUrl(LainNewApi.getUserIP() + "/resources/icon/72x72/environment/localtion.png");
            addBeans.add(bean);
        }
        //设置 设备管理 中的 添加设备 列表
        manageAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteDevice(int position, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        String ehmId = String.valueOf(splitAirBeans.get(position).getEfmId());
        svHolder.deleteDevice(ehmId, position);
    }

    @Override
    public void withBindDeviceManage(BaseDeviceDetailManageAdapter.SVHolder svHolder, Device_Detail_AddBean data, int position) {
        //在这里设置视图

        //设置图标
        Glide.with(getActivity()).load(LainNewApi.DEVICE_IMAGE).into(svHolder.imageView);

        //在这里设置视图
        List<TextView> textViewList = new ArrayList<>();

        svHolder.manageDeviceName.setText(data.getDeviceName());

        TextView devicePos = new TextView(getActivity());
        devicePos.setText("设备地址：" + data.getPosition());
        textViewList.add(devicePos);

        TextView deviceIP = new TextView(getActivity());
        deviceIP.setText("IP地址：" + data.getIp());
        textViewList.add(deviceIP);

        DynamicDeviceManage.getInstance().addManageItem(textViewList, svHolder.linearLayout);

    }

    @Override
    public List<MaterialEditText> setDeviceAddEdit() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        DynamicMaterialEdit.getInstance().setLayoutInflater(inflater, getActivity());
        List<MaterialEditText> editTexts = new ArrayList<>();

        MaterialEditText name = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "设备名称","");
        MaterialEditText address = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "设备地址","");

        editTexts.add(address);
        editTexts.add(name);


        return editTexts;
    }

    @Override
    public void changeDeviceAction(List<MaterialEditText> materialEditTexts, int actionID, int position) {
        //进行解析
        Map<String, String> formBody = new HashMap();
        formBody.put("efmName", materialEditTexts.get(0).getText().toString());
        formBody.put("efmId", String.valueOf(actionID));
        //解析MAP 为 JSON
        String json = MyGson.getInstance().toJson(formBody);
        //发送请求
        OkHttpUtil.getInstance().sendPutOkHttp("change", LainNewApi.getInstance().getRootPath() + LainNewApi.airFissionChange, json, this);
    }

    /**
     点击修改按钮时会跳转到编辑页
     **/
    @Override
    public void changeDevice(String flag, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        //获取当前需要修改设备的位置
        int position = svHolder.getAdapterPosition();

        SplitAirBean temp = splitAirBeans.get(position);

        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();

        deviceManageEditBean.setaDeviceName(temp.getEfmName());
        //如果是修改，就必须设置设备的ID
        deviceManageEditBean.setActionID(temp.getEfmId());

        deviceManageEditBean.setDeviceIPBean(deviceIPBeanList);
        deviceManageEditBean.setGroupBeans(SaveInterface.getInstance().getGroupBeanList().get(0));

        deviceManageEditBean.setShowDeviceAddress(true);

        deviceManageEditBean.setChangeOrAdd(flag);

        intent.putExtra("editBean", deviceManageEditBean);

        startActivity(intent);

    }
    /**
     点击添加设备按钮时会跳转到编辑页
     **/
    @Override
    public void addNewDevice(String flag) {

        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();
        deviceManageEditBean.setaDeviceName("");
        deviceManageEditBean.setDeviceIPBean(deviceIPBeanList);

        deviceManageEditBean.setGroupBeans(SaveInterface.getInstance().getGroupBeanList().get(0));

        deviceManageEditBean.setShowDeviceAddress(true);

        deviceManageEditBean.setChangeOrAdd("添加");

        intent.putExtra("editBean", deviceManageEditBean);

        startActivity(intent);

    }

    /**
     * 提交添加设备数据时的回调
     *
     * @param keyArray   EditText 的储存集合
     * @param spinnerMap Spinner 的储存集合
     */
    @Override
    public void deterMine(Map<String, EditText> keyArray, Map<String, Spinner> spinnerMap) {

        //解析
        Map<String, String> formBody = new HashMap();
        //将输入的参数添加到 FormBody 中，进行发送给服务器
        formBody.put("efmAddress", getSpinnerString(spinnerMap.get("deviceAddress")));
        formBody.put("efmName", getTextString(keyArray.get("deviceName")));
        formBody.put("efmStudyOnOrder", "");
        formBody.put("efmStudyOffOrder", "");
        formBody.put("efmOnOrder", "");
        formBody.put("efmOffOrder", "");
        formBody.put("diId", getTextString(keyArray.get("diId")));
        formBody.put("gId", getTextString(keyArray.get("gId")));

        OkHttpUtil.getInstance().sendRowOkHttp("add", LainNewApi.getInstance().getRootPath() + LainNewApi.airFissionAdd, MyGson.getInstance().toJson(formBody), this);

    }

    /**
     * 提交修改设备数据时的回调
     *
     * @param keyArray   EditText 的储存集合
     * @param spinnerMap Spinner 的储存集合
     */
    @Override
    public void deterChange(Map<String, EditText> keyArray, Map<String, Spinner> spinnerMap) {
        //解析
        Map<String, String> formBody = new HashMap();
        //将输入的参数添加到 FormBody 中，进行发送给服务器
        formBody.put("efmId", getTextString(keyArray.get("actionID")));
        formBody.put("efmName", getTextString(keyArray.get("deviceName")));

        OkHttpUtil.getInstance().sendPutOkHttp("change", LainNewApi.getInstance().getRootPath() + changeUrl, MyGson.getInstance().toJson(formBody), this);

    }


    /**
     * 修改设备成功后会调用
     */
    @Override
    public void changeDeviceSuccess() {

        //刷新页面
        base8052Present.queryRealData(SplitAirBean.class);
        refreshDeviceList = true;
        //将报警，设备管理列表重置
        alertDeviceBeanList.clear();
        new Handler().postDelayed(() -> {
            base8052Present.queryDeviceManage();
        }, 1000);

    }

    /**
     * 添加设备成功后会调用
     */
    @Override
    public void addDeviceSuccess() {

        //刷新页面
        base8052Present.queryRealData(SplitAirBean.class);
        refreshDeviceList = true;
        //将报警，设备管理列表重置
        alertDeviceBeanList.clear();
        new Handler().postDelayed(() -> {
            base8052Present.queryDeviceManage();
        }, 1000);

    }

}
