package view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.base.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

import bean.EelectricityBean;
import http.MyGson;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import util.LainNewApi;
import util.ToastManage;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/12/22 14:18
 * Description：电量仪添加设备
 **/
public class ElectricityDeviceAdd extends AppCompatActivity implements View.OnClickListener, OkHttpCallBack {

    /**
     * 设备名称
     */
    private MaterialEditText mNewDeviceName;
    /**
     * 设备地址
     */
    private MaterialEditText mElectricityAddress;
    /**
     * 设备通道
     */
    private MaterialEditText mDeviceGallery;
    /**
     * A相电压上限
     */
    private MaterialEditText mPemMaxAvol;
    /**
     * A相电压下限
     */
    private MaterialEditText mPemMinAvol;
    /**
     * B相电压上限
     */
    private MaterialEditText mPemMaxBvol;
    /**
     * B相电压下限
     */
    private MaterialEditText mPemMinBvol;
    /**
     * C相电压上限
     */
    private MaterialEditText mPemMaxCvol;
    /**
     * C相电压下限
     */
    private MaterialEditText mPemMinCvol;
    /**
     * A相电流上限
     */
    private MaterialEditText mPemMaxAcur;
    /**
     * A相电流下限
     */
    private MaterialEditText mPemMinAcur;
    /**
     * B相电流上限
     */
    private MaterialEditText mPemMaxBcur;
    /**
     * B相电流下限
     */
    private MaterialEditText mPemMinBcur;
    /**
     * C相电流上限
     */
    private MaterialEditText mPemMaxCcur;
    /**
     * C相电流下限
     */
    private MaterialEditText mPemMinCcur;
    /**
     * AB相电压上限
     */
    private MaterialEditText mPemMaxABvol;
    /**
     * AB相电压下限
     */
    private MaterialEditText mPemMinABvol;
    /**
     * BC相电压上限
     */
    private MaterialEditText mPemMaxBCvol;
    /**
     * BC相电压下限
     */
    private MaterialEditText mPemMinBCvol;
    /**
     * CA相电压上限
     */
    private MaterialEditText mPemMaxCAvol;
    /**
     * CA相电压下限
     */
    private MaterialEditText mPemMinCAvol;
    /**
     * A相有功功率上限
     */
    private MaterialEditText mPemMaxApap;
    /**
     * A相有功功率下限
     */
    private MaterialEditText mPemMinApap;
    /**
     * B相有功功率上限
     */
    private MaterialEditText mPemMaxBpap;
    /**
     * B相有功功率下限
     */
    private MaterialEditText mPemMinBpap;
    /**
     * C相有功功率上限
     */
    private MaterialEditText mPemMaxCpap;
    /**
     * C相有功功率下限
     */
    private MaterialEditText mPemMinCpap;
    /**
     * A相无功功率下限
     */
    private MaterialEditText mPemMaxAprp;
    /**
     * A相无功功率下限
     */
    private MaterialEditText mPemMinAprp;
    /**
     * B相无功功率上限
     */
    private MaterialEditText mPemMaxBprp;
    /**
     * B相无功功率下限
     */
    private MaterialEditText mPemMinBprp;
    /**
     * C相无功功率上限
     */
    private MaterialEditText mPemMaxCprp;
    /**
     * C相无功功率下限
     */
    private MaterialEditText mPemMinCprp;
    /**
     * A相功率因素上限
     */
    private MaterialEditText mPemMaxAppf;
    /**
     * A相功率因素下限
     */
    private MaterialEditText mPemMinAppf;
    /**
     * B相功率因素上限
     */
    private MaterialEditText mPemMaxBppf;
    /**
     * B相功率因素下限
     */
    private MaterialEditText mPemMinBppf;
    /**
     * C相功率因素上限
     */
    private MaterialEditText mPemMaxCppf;
    /**
     * C相功率因素下限
     */
    private MaterialEditText mPemMinCppf;
    /**
     * 总有功功率上限
     */
    private MaterialEditText mPemMaxTpap;
    /**
     * 总有功功率下限
     */
    private MaterialEditText mPemMinTpap;
    /**
     * 总无功功率上限
     */
    private MaterialEditText mPemMaxTprp;
    /**
     * 总无功功率下限
     */
    private MaterialEditText mPemMinTprp;
    /**
     * 总功率因素上限
     */
    private MaterialEditText mPemMaxTppf;
    /**
     * 总功率因素下限
     */
    private MaterialEditText mPemMinTppf;
    /**
     * 报警信息间隔
     */
    private MaterialEditText mElecIntervalTime;
    /**
     * 修改
     */
    private Button mAddNewDevice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.electricity_add_device);
        initView();

        //获取传递过来的数据
        EelectricityBean electricityBean = (EelectricityBean) getIntent().getSerializableExtra("electricity_manage");

        mNewDeviceName.setText(electricityBean.getPemName());
        mElectricityAddress.setText(electricityBean.getPemAddress() + "");
        mPemMaxAvol.setText(electricityBean.getPemMaxAvol() + "");
        mPemMinAvol.setText(electricityBean.getPemMinAvol() + "");
        mPemMaxBvol.setText(electricityBean.getPemMaxBvol() + "");
        mPemMinBvol.setText(electricityBean.getPemMinBvol() + "");
        mPemMaxCvol.setText(electricityBean.getPemMaxCvol() + "");
        mPemMinCvol.setText(electricityBean.getPemMinCvol() + "");
        mPemMaxAcur.setText(electricityBean.getPemMaxAcur() + "");
        mPemMinAcur.setText(electricityBean.getPemMinAcur() + "");
        mPemMaxBcur.setText(electricityBean.getPemMaxBcur() + "");
        mPemMinBcur.setText(electricityBean.getPemMinBcur() + "");
        mPemMaxCcur.setText(electricityBean.getPemMaxCcur() + "");
        mPemMinCcur.setText(electricityBean.getPemMinCcur() + "");
        mPemMaxABvol.setText(electricityBean.getPemMaxABvol() + "");
        mPemMinABvol.setText(electricityBean.getPemMinABvol() + "");
        mPemMaxBCvol.setText(electricityBean.getPemMaxBCvol() + "");
        mPemMinBCvol.setText(electricityBean.getPemMinBCvol() + "");
        mPemMaxCAvol.setText(electricityBean.getPemMaxCAvol() + "");
        mPemMinCAvol.setText(electricityBean.getPemMinCAvol() + "");
        mPemMaxApap.setText(electricityBean.getPemMaxApap() + "");
        mPemMinApap.setText(electricityBean.getPemMinApap() + "");
        mPemMaxBpap.setText(electricityBean.getPemMaxBpap() + "");
        mPemMinBpap.setText(electricityBean.getPemMinBpap() + "");
        mPemMaxCpap.setText(electricityBean.getPemMaxCpap() + "");
        mPemMinCpap.setText(electricityBean.getPemMinCpap() + "");
        mPemMaxAprp.setText(electricityBean.getPemMaxAprp() + "");
        mPemMinAprp.setText(electricityBean.getPemMinAprp() + "");
        mPemMaxBprp.setText(electricityBean.getPemMaxBprp() + "");
        mPemMinBprp.setText(electricityBean.getPemMinBprp() + "");
        mPemMaxCprp.setText(electricityBean.getPemMaxCprp() + "");
        mPemMinCprp.setText(electricityBean.getPemMinCprp() + "");
        mPemMaxAppf.setText(electricityBean.getPemMaxAppf() + "");
        mPemMinAppf.setText(electricityBean.getPemMinAppf() + "");
        mPemMaxBppf.setText(electricityBean.getPemMaxBppf() + "");
        mPemMinBppf.setText(electricityBean.getPemMinBppf() + "");
        mPemMaxCppf.setText(electricityBean.getPemMaxCppf() + "");
        mPemMinCppf.setText(electricityBean.getPemMinCppf() + "");
        mPemMaxTpap.setText(electricityBean.getPemMaxTpap() + "");
        mPemMinTpap.setText(electricityBean.getPemMinTpap() + "");
        mPemMaxTprp.setText(electricityBean.getPemMaxTprp() + "");
        mPemMinTprp.setText(electricityBean.getPemMinTprp() + "");
        mPemMaxTppf.setText(electricityBean.getPemMaxTppf() + "");
        mPemMinTppf.setText(electricityBean.getPemMinTppf() + "");
        mElecIntervalTime.setText(electricityBean.getIntervalTime() + "");

    }

    private void initView() {
        mNewDeviceName = findViewById(R.id.new_device_name);
        mElectricityAddress = findViewById(R.id.electricity_address);
        mDeviceGallery = findViewById(R.id.device_gallery);
        mPemMaxAvol = findViewById(R.id.pemMaxAvol);
        mPemMinAvol = findViewById(R.id.pemMinAvol);
        mPemMaxBvol = findViewById(R.id.pemMaxBvol);
        mPemMinBvol = findViewById(R.id.pemMinBvol);
        mPemMaxCvol = findViewById(R.id.pemMaxCvol);
        mPemMinCvol = findViewById(R.id.pemMinCvol);
        mPemMaxAcur = findViewById(R.id.pemMaxAcur);
        mPemMinAcur = findViewById(R.id.pemMinAcur);
        mPemMaxBcur = findViewById(R.id.pemMaxBcur);
        mPemMinBcur = findViewById(R.id.pemMinBcur);
        mPemMaxCcur = findViewById(R.id.pemMaxCcur);
        mPemMinCcur = findViewById(R.id.pemMinCcur);
        mPemMaxABvol = findViewById(R.id.pemMaxABvol);
        mPemMinABvol = findViewById(R.id.pemMinABvol);
        mPemMaxBCvol = findViewById(R.id.pemMaxBCvol);
        mPemMinBCvol = findViewById(R.id.pemMinBCvol);
        mPemMaxCAvol = findViewById(R.id.pemMaxCAvol);
        mPemMinCAvol = findViewById(R.id.pemMinCAvol);
        mPemMaxApap = findViewById(R.id.pemMaxApap);
        mPemMinApap = findViewById(R.id.pemMinApap);
        mPemMaxBpap = findViewById(R.id.pemMaxBpap);
        mPemMinBpap = findViewById(R.id.pemMinBpap);
        mPemMaxCpap = findViewById(R.id.pemMaxCpap);
        mPemMinCpap = findViewById(R.id.pemMinCpap);
        mPemMaxAprp = findViewById(R.id.pemMaxAprp);
        mPemMinAprp = findViewById(R.id.pemMinAprp);
        mPemMaxBprp = findViewById(R.id.pemMaxBprp);
        mPemMinBprp = findViewById(R.id.pemMinBprp);
        mPemMaxCprp = findViewById(R.id.pemMaxCprp);
        mPemMinCprp = findViewById(R.id.pemMinCprp);
        mPemMaxAppf = findViewById(R.id.pemMaxAppf);
        mPemMinAppf = findViewById(R.id.pemMinAppf);
        mPemMaxBppf = findViewById(R.id.pemMaxBppf);
        mPemMinBppf = findViewById(R.id.pemMinBppf);
        mPemMaxCppf = findViewById(R.id.pemMaxCppf);
        mPemMinCppf = findViewById(R.id.pemMinCppf);
        mPemMaxTpap = findViewById(R.id.pemMaxTpap);
        mPemMinTpap = findViewById(R.id.pemMinTpap);
        mPemMaxTprp = findViewById(R.id.pemMaxTprp);
        mPemMinTprp = findViewById(R.id.pemMinTprp);
        mPemMaxTppf = findViewById(R.id.pemMaxTppf);
        mPemMinTppf = findViewById(R.id.pemMinTppf);
        mElecIntervalTime = findViewById(R.id.elec_intervalTime);
        mAddNewDevice = findViewById(R.id.add_new_device);
        mAddNewDevice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_new_device) {
            changeElectricityDevice();
        }
    }

    /**
     * 修改电量仪的数据
     */
    private void changeElectricityDevice() {

        Map<String, String> electricityMap = new HashMap<>();

        electricityMap.put("pemName",  mNewDeviceName.getText().toString());
        electricityMap.put("pemAddress",  mElectricityAddress.getText().toString());
        electricityMap.put("pemMaxAvol",  mPemMaxAvol.getText().toString());
        electricityMap.put("pemMinAvol",  mPemMinAvol.getText().toString());
        electricityMap.put("pemMaxBvol",  mPemMaxBvol.getText().toString());
        electricityMap.put("pemMinBvol",  mPemMinBvol.getText().toString());
        electricityMap.put("pemMaxCvol", mPemMaxCvol.getText().toString());
        electricityMap.put("pemMinCvol", mPemMinCvol.getText().toString());
        electricityMap.put("pemMaxAcur",  mPemMaxAcur.getText().toString());
        electricityMap.put("pemMinAcur",  mPemMinAcur.getText().toString());
        electricityMap.put("pemMaxBcur", mPemMaxBcur.getText().toString());
        electricityMap.put("pemMinBcur",  mPemMinBcur.getText().toString());
        electricityMap.put("pemMaxCcur", mPemMaxCcur.getText().toString());
        electricityMap.put("pemMinCcur", mPemMinCcur.getText().toString());
        electricityMap.put("pemMaxTpap", mPemMaxTpap.getText().toString());
        electricityMap.put("pemMaxABvol",  mPemMaxABvol.getText().toString());
        electricityMap.put("pemMinABvol",  mPemMinABvol.getText().toString());
        electricityMap.put("pemMaxBCvol",  mPemMaxBCvol.getText().toString());
        electricityMap.put("pemMinBCvol",  mPemMinBCvol.getText().toString());
        electricityMap.put("pemMaxCAvol",  mPemMaxCAvol.getText().toString());
        electricityMap.put("pemMinCAvol",  mPemMinCAvol.getText().toString());
        electricityMap.put("pemMaxApap",  mPemMaxApap.getText().toString());
        electricityMap.put("pemMinApap",  mPemMinApap.getText().toString());
        electricityMap.put("pemMaxBpap",  mPemMaxBpap.getText().toString());
        electricityMap.put("pemMinBpap",  mPemMinBpap.getText().toString());
        electricityMap.put("pemMaxCpap",  mPemMaxCpap.getText().toString());
        electricityMap.put("pemMinCpap",    mPemMinCpap.getText().toString());
        electricityMap.put("pemMaxAprp",  mPemMaxAprp.getText().toString());
        electricityMap.put("pemMinAprp", mPemMinAprp.getText().toString());
        electricityMap.put("pemMaxBprp",  mPemMaxBprp.getText().toString());
        electricityMap.put("pemMinBprp",  mPemMinBprp.getText().toString());
        electricityMap.put("pemMaxCprp",  mPemMaxCprp.getText().toString());
        electricityMap.put("pemMinCprp", mPemMinCprp.getText().toString() );

        electricityMap.put("pemMaxAppf",  mPemMaxAppf.getText().toString());
        electricityMap.put("pemMinAppf",  mPemMinAppf.getText().toString());
        electricityMap.put("pemMaxBppf",  mPemMaxBppf.getText().toString());
        electricityMap.put("pemMinBppf",  mPemMinBppf.getText().toString());
        electricityMap.put("pemMaxCppf", mPemMaxCppf.getText().toString());
        electricityMap.put("pemMinCppf", mPemMinCppf.getText().toString() );
        electricityMap.put("pemMinTpap",  mPemMinTpap.getText().toString());
        electricityMap.put("pemMaxTprp",  mPemMaxTprp.getText().toString());
        electricityMap.put("pemMinTprp",mPemMinTprp.getText().toString() );
        electricityMap.put("pemMaxTppf", mPemMaxTppf.getText().toString());
        electricityMap.put("pemMinTppf", mPemMinTppf.getText().toString());
        electricityMap.put("intervalTime",  mElecIntervalTime.getText().toString());
        electricityMap.put("diId",  "5");
        electricityMap.put("gId",  "1");

        String elecDeviceJson = MyGson.getInstance().toJson(electricityMap);
        Log.d("erefrf", "initAddDevicePanel: "+elecDeviceJson);
        OkHttpUtil.getInstance().sendPutOkHttp("update_device", LainNewApi.getInstance().AutoIp + LainNewApi.electricityDeviceUpdate, elecDeviceJson, this);

    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

        if (responseStr.equals("true")) {
            ToastManage.getInstance().toastLongShow("修改设备成功");
            finish();
        }

    }

    @Override
    public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {

    }
}
