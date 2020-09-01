package action;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.R;
import com.basgeekball.awesomevalidation.ValidationHolder;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.custom.CustomErrorReset;
import com.basgeekball.awesomevalidation.utility.custom.CustomValidation;
import com.basgeekball.awesomevalidation.utility.custom.CustomValidationCallback;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;
import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;
import static com.basgeekball.awesomevalidation.ValidationStyle.TEXT_INPUT_LAYOUT;
import static com.basgeekball.awesomevalidation.ValidationStyle.UNDERLABEL;

/**
 * @ClassName: ActionControl
 * @Description: 所有添加设备需要的输入信息控件全由类来控制
 * @Author: YIN LUO FEI
 * @Date: 2020/4/20 16:35
 */
public class ActionControl extends AppCompatActivity implements View.OnClickListener, TextWatcher, View.OnFocusChangeListener {


    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ScrollView scrollView;
    private LinearLayout containerEditText;
    //设备名称
    private EditText aDeviceName;
    //更新时间
    private EditText aDeviceInterval;
    //报警值
    private EditText aDeviceAlert;
    //线长度
    private EditText aDeviceLine;
    //温度最小值
    private EditText tempRangeMin;
    //温度最大值
    private EditText tempRangeMax;
    //氨气最小值
    private EditText ammoniaRangeMin;
    //氨气最大值
    private EditText ammoniaRangeMax;
    //氮气最小值
    private EditText nitrogenRangeMin;
    //氮气最大值
    private EditText nitrogenRangeMax;
    //浓度最小值
    private EditText concentrationRangeMin;
    //浓度最大值
    private EditText concentrationRangeMax;
    //湿度最小值
    private EditText humRangeMin;
    //湿度最大值
    private EditText humRangeMax;
    //噪音最小值
    private EditText noiseRangeMin;
    //噪音最大值
    private EditText noiseRangeMax;
    //范围区间最小值
    private EditText rangeMin;
    //范围区间最大值
    private EditText rangeMax;
    //剩于电流最小值
    private EditText electricMin;
    //剩于电流最大值
    private EditText electricMax;
    //A相电流最小值
    private EditText phaseAMin;
    //A相电流最大值
    private EditText phaseAMax;
    //A相电压最小值
    private EditText voltageAMin;
    //A相电压最大值
    private EditText voltageAMax;
    //B相电流最小值
    private EditText phaseBMin;
    //B相电流最大值
    private EditText phaseBMax;
    //B相电压最小值
    private EditText voltageBMin;
    //B相电压最小值
    private EditText voltageBMax;
    //C相电流最小值
    private EditText phaseCMin;
    //C相电流最大值
    private EditText phaseCMax;
    //C相电压最小值
    private EditText voltageCMin;
    //C相电压最大值
    private EditText voltageCMax;
    //含氧量最小值
    private EditText oxygenRangeMin;
    //含氧量最大值
    private EditText oxygenRangeMax;
    //设备地址
    private Spinner deviceAddress;
    //设备通道
    private Spinner deviceGallery;
    //所属分组
    private Spinner classifySpinner;
    //IP地址
    private Spinner ipAddressSpinner;
    //功能码
    private Spinner functionCode;

    private EditText edtUserid;
    private EditText edtEmail;
    private EditText edtIp;
    private EditText edtTel;
    private EditText edtZipcode;
    private EditText edtYear;
    private EditText edtHeight;
    private EditText edtBirthday;

    private ConstraintLayout containerTextInputLayout;
    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private TextInputLayout tilPasswordConfirmation;
    private TextInputLayout tilYear;
    private LinearLayout containerSuccess;
    //取消
    private Button btnClr;
    //确定
    private Button btnDone;

    private ListView leftDrawer;
    //添加设备 或 修改设备 的URL
    private String actionURL;


    private LinearLayout aDeviceIntervalPanel;
    private LinearLayout aDeviceAlertPanel;
    private LinearLayout aDeviceLinePanel;
    private LinearLayout tempRangePanel;
    private LinearLayout ammoniaRangePanel;
    private LinearLayout nitrogenRangePanel;
    private LinearLayout concentrationRangePanel;
    private LinearLayout humRangePanel;
    private LinearLayout noiseRangePanel;
    private LinearLayout rangePanel;
    private LinearLayout electricPanel;
    private LinearLayout phaseAPanel;
    private LinearLayout voltageAPanel;
    private LinearLayout phaseBPanel;
    private LinearLayout voltageBPanel;
    private LinearLayout phaseCPanel;
    private LinearLayout voltageCPanel;
    private LinearLayout oxygenRangePanel;

    private LinearLayout deviceAddressPanel;
    private LinearLayout deviceGalleryPanel;

    private LinearLayout classifySpinnerPanel;
    private LinearLayout ipSpinnerPanel;
    private LinearLayout functionCodePanel;
    private LinearLayout aDeviceNamePanel;

    private String[] mStyles;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;

    private int mPosition = 0;
    private AwesomeValidation mAwesomeValidation;

    private ScrollView mScrollView;
    private View mViewContainerEditText;
    private View mViewContainerTextInputLayout;
    //设备标识，调用者调入指定的的TAG，再根据不同的Tag来显示或者隐藏
    private List<String> deviceTag;

    //确定，取消，修改按钮的回调方法，由调用者来实现
    private DeviceAction deviceAction;
    //保存需要提交的View
    Map<String, EditText> keyArray = new HashMap<>();
    Map<String, Spinner> spinnerMap = new HashMap<>();

    //以下是 最大值 和 最小值 的错误提示
    private TextView tempAlert;
    private TextView ammoniaAlert;
    private TextView nitrogenAlert;
    private TextView concentrationAlert;
    private TextView humAlert;
    private TextView noiseAlert;
    private TextView rangeAlert;
    private TextView electricAlert;
    private TextView phaseAAlert;
    private TextView voltageAAlert;
    private TextView phaseBAlert;
    private TextView voltageBAlert;
    private TextView phaseCAlert;
    private TextView voltageCAlert;
    private TextView oxygenAlert;

    private DrawerItemClickListener mDrawerItemClickListener = new DrawerItemClickListener();

    //修改时需要的设备ID
    private String actionID;
    //标题
    private Toolbar baseToolbar;
    //数据携带的Bean类
    DeviceManageEditBean editBean;

    //保存设备IP选中的位置
    private int ipSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏的颜色
        setStatusBarColor(this, R.color.colorPrimary);
        setContentView(R.layout.edit_device);

        initView();

        //获取保存的接口实例
        try {
            deviceAction = SaveInterface.getInstance().getDeviceAction();
        } catch (NullPointerException e) {
            Log.d("ActionControl", "操作接口为NULL，请检测是否有传入 DeviceAction 接口，" + e.getMessage());
        }

        //获取数据
        editBean = (DeviceManageEditBean) getIntent().getSerializableExtra("editBean");

        //顶部Toolbar
        baseToolbar = findViewById(R.id.insert_toolbar);
        setSupportActionBar(baseToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && editBean != null) {
            actionBar.setTitle(editBean.getChangeOrAdd() + "设备");
            //显示Toolbar的返回按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置Toolbar的返回图标
            actionBar.setHomeAsUpIndicator(R.drawable.back_icon);
        }

        clearValidation();
        initValidation("BASIC");
        mScrollView = (ScrollView) findViewById(R.id.scroll_view);
        mViewContainerEditText = findViewById(R.id.container_edit_text);

        mStyles = getResources().getStringArray(R.array.styles);

        //如果有获取到 所有组 数据才执行
        if (editBean != null && editBean.getGroupBeans() != null) {
            //获取设备的所有组
            try {
                List<String> classifyList = new ArrayList<>();
                for (DeviceGroupBean.ChildrenBean childrenBean : editBean.getGroupBeans().getChildren()) {
                    classifyList.add(childrenBean.getGName());
                }

                editBean.setClassifyArr(classifyList);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }


        setupSpinner();
        // AwesomeValidation.disableAutoFocusOnFirstFailure();

        //修改状态下才需要执行该操作
        if (editBean != null && editBean.getChangeOrAdd().equals("修改")) {
            //获取设备的ID
            actionID = editBean.getActionID();
            //通过EditText保存到集合中返回给调用者来处理
            EditText idEditText = new EditText(this);
            idEditText.setText(editBean.getActionID());
            keyArray.put("actionID", idEditText);
        }

        //如果有获取到设备的 IP ,才进行设置
        if (editBean != null && editBean.getDeviceIPBean() != null) {
            //获取设备所属IP地址
            List<String> ipList = new ArrayList<>();
            for (int i = 0; i < editBean.getDeviceIPBean().size(); i++) {

                //获取设备拥有的IP
                ipList.add(editBean.getDeviceIPBean().get(i).getDiAddress() + ":" + editBean.getDeviceIPBean().get(i).getDiPort());
                //保存设备拥有的IP
                if (editBean.getDiId() == editBean.getDeviceIPBean().get(i).getDiId())
                    ipSelect = i;

            }

            editBean.setIpArr(ipList);
        }

        //获取添加或者修改的URL
        actionURL = getIntent().getStringExtra("actionURL");
        //获取设备的名称，用于处理不同设备，显示不同的输入框
        deviceTag = getIntent().getStringArrayListExtra("deviceTag");

        //设置数据，如果不为空，代表修改信息，反之添加设备
        if (editBean != null) {
            setData(editBean);
        }

    }

    /**
     * 修改状态栏颜色，支持4.4以上版本
     *
     * @param activity
     * @param colorId
     */
    public static void setStatusBarColor(Activity activity, int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(colorId));
        }
    }

    private void deviceVisibleConfig() {

    }

    /**
     * 控制EditText的显示
     */
    private void showVisible(LinearLayout linearLayout) {

        linearLayout.setVisibility(View.VISIBLE);

    }

    /**
     * 控制EditText的隐藏
     *
     * @param editText
     */
    private void showGone(EditText editText) {

        editText.setVisibility(View.GONE);

    }

    /**
     * 获取过来的数据填入输入框中
     * 如果数据不为 NULL 才显示控件
     *
     * @param editBean
     */
    private void setData(DeviceManageEditBean editBean) {
        //判断是添加还是修改，如果是修改，则将某些字段禁用
        boolean addOrChange = editBean.getChangeOrAdd().equals("添加");

        //显示设备地址
        if (editBean.isShowDeviceAddress()) {
            deviceAddressPanel.setVisibility(View.VISIBLE);
            //保存Spinner的实例，提交时需要值
            spinnerMap.put("deviceAddress", deviceAddress);
            deviceAddress.setEnabled(addOrChange);
        }

        //显示设备通道
        if (editBean.isShowDeviceGallery()) {
            deviceAddressPanel.setVisibility(View.VISIBLE);
            //保存Spinner的实例，提交时需要值
            spinnerMap.put("deviceGallery", deviceGallery);
            deviceAddress.setEnabled(addOrChange);
        }

        //显示功能码
        if (editBean.isShowFunctionCode()) {

            functionCodePanel.setVisibility(View.VISIBLE);
            //保存Spinner的实例，提交时需要值
            spinnerMap.put("functionCode", functionCode);
            functionCode.setEnabled(addOrChange);

        }
        //分组
        if (editBean.getClassifyArr() != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, editBean.getClassifyArr());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            classifySpinner.setAdapter(adapter);
            classifySpinnerPanel.setVisibility(View.VISIBLE);

            //检测是否允许修改设备分组，如果为false（默认），根据标识来确定是否允许修改
            if (editBean.isShowDeviceClassify()) {
                //允许修改
                classifySpinner.setEnabled(editBean.isShowDeviceClassify());
            } else {
                classifySpinner.setEnabled(addOrChange);
            }


            //保存Spinner的实例，提交时需要值
            spinnerMap.put("classify", classifySpinner);
        }
        //IP
        if (editBean.getIpArr() != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, editBean.getIpArr());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ipAddressSpinner.setAdapter(adapter);
            ipSpinnerPanel.setVisibility(View.VISIBLE);

            ipAddressSpinner.setEnabled(addOrChange);
            //保存Spinner的实例，提交时需要值
            spinnerMap.put("ipAddress", ipAddressSpinner);

            //设置选中时的值
            ipAddressSpinner.setSelection(ipSelect);
        }

        //设备名称
        if (editBean.getaDeviceName() != null) {
            //设置控制的值
            aDeviceName.setText(editBean.getaDeviceName());
            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("deviceName", aDeviceName);
            //设备名称
            mAwesomeValidation.addValidation(this, R.id.a_device_name, "^[\\u4E00-\\u9FA5A-Za-z0-9]+$", R.string.err_deviceName);

            //显示控件
            showVisible(aDeviceNamePanel);
        }

        //更新时间
        if (editBean.getaDeviceInterval() != null) {
            //设置控制的值
            aDeviceInterval.setText(editBean.getaDeviceInterval());
            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("interval", aDeviceInterval);
            //更新时间
            mAwesomeValidation.addValidation(this, R.id.a_device_interval, "[0-9]+", R.string.err_deviceInterval);
            //显示控件
            showVisible(aDeviceIntervalPanel);
        }

        //报警值
        if (editBean.getaDeviceAlert() != null) {
            //设置控制的值
            aDeviceAlert.setText(editBean.getaDeviceAlert());
            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("alertValue", aDeviceAlert);
            //显示控件
            showVisible(aDeviceAlertPanel);
            //报警值提示
            //最大值
            mAwesomeValidation.addValidation(this, R.id.a_device_alert, "^(-?\\d+)(\\.\\d+)?$", R.string.err_alertValue);
        }

        //线长度
        if (editBean.getaDeviceLine() != null) {
            //设置控制的值
            aDeviceLine.setText(editBean.getaDeviceLine());
            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("deviceLine", aDeviceLine);
            //显示控件
            showVisible(aDeviceLinePanel);
            //线长度提示
            mAwesomeValidation.addValidation(this, R.id.a_device_line, "^(-?\\d+)(\\.\\d+)?$", R.string.err_line);
        }

        //温度最小值
        if (editBean.getTempRangeMin() != null) {
            //设置控制的值
            tempRangeMin.setText(editBean.getTempRangeMin());
            //设置EditText焦点监听
            tempRangeMin.setOnFocusChangeListener(this);
            //温度最大值
            tempRangeMax.setText(editBean.getTempRangeMax());
            //设置EditText焦点监听
            tempRangeMax.setOnFocusChangeListener(this);

            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("tempMin", tempRangeMin);
            keyArray.put("tempMax", tempRangeMax);
            //显示控件
            showVisible(tempRangePanel);

            //温度大小值错误信息
            setErrorMsg(R.id.temp_range_min, R.id.temp_range_max);
        }

        //氨气最小值
        if (editBean.getAmmoniaRangeMin() != null) {
            //设置控制的值
            ammoniaRangeMin.setText(editBean.getAmmoniaRangeMin());
            //氨气最大值
            ammoniaRangeMax.setText(editBean.getAmmoniaRangeMax());
            //设置EditText焦点监听
            ammoniaRangeMin.setOnFocusChangeListener(this);
            ammoniaRangeMax.setOnFocusChangeListener(this);

            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("ammoniaMin", ammoniaRangeMin);
            keyArray.put("ammoniaMax", ammoniaRangeMax);
            //显示控件
            showVisible(ammoniaRangePanel);

            //氨气大小值错误信息
            setErrorMsg(R.id.ammonia_range_min, R.id.ammonia_range_max);
        }

        //氮气最小值
        if (editBean.getNitrogenRangeMin() != null) {
            //设置控制的值
            nitrogenRangeMin.setText(editBean.getNitrogenRangeMin());
            //氮气最大值
            nitrogenRangeMax.setText(editBean.getNitrogenRangeMax());
            //设置EditText焦点监听
            nitrogenRangeMin.setOnFocusChangeListener(this);
            nitrogenRangeMax.setOnFocusChangeListener(this);

            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("nitrogenMin", nitrogenRangeMin);
            keyArray.put("nitrogenMax", nitrogenRangeMax);
            //显示控件
            showVisible(nitrogenRangePanel);

            //氮气大小值错误信息
            setErrorMsg(R.id.nitrogen_range_min, R.id.nitrogen_range_max);
        }

        //浓度最小值
        if (editBean.getConcentrationRangeMin() != null) {
            //设置控制的值
            concentrationRangeMin.setText(editBean.getConcentrationRangeMin());
            //浓度最大值
            concentrationRangeMax.setText(editBean.getConcentrationRangeMax());
            //设置EditText焦点监听
            concentrationRangeMin.setOnFocusChangeListener(this);
            concentrationRangeMax.setOnFocusChangeListener(this);


            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("concentrationMin", concentrationRangeMin);
            keyArray.put("concentrationMax", concentrationRangeMax);
            //显示控件
            showVisible(concentrationRangePanel);
            //浓度大小值错误信息
            setErrorMsg(R.id.concentration_range_min, R.id.concentration_range_max);
        }

        //湿度最小值
        if (editBean.getHumRangeMin() != null) {
            //设置控制的值
            humRangeMin.setText(editBean.getHumRangeMin());
            //湿度最大值
            humRangeMax.setText(editBean.getHumRangeMax());
            //设置EditText焦点监听
            humRangeMin.setOnFocusChangeListener(this);
            humRangeMax.setOnFocusChangeListener(this);

            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("humMin", humRangeMin);
            keyArray.put("humMax", humRangeMax);
            //显示控件
            showVisible(humRangePanel);
            //湿度大小值错误信息
            setErrorMsg(R.id.hum_range_min, R.id.hum_range_max);
        }

        //噪音最小值
        if (editBean.getNoiseRangeMin() != null) {
            //设置控制的值
            noiseRangeMin.setText(editBean.getNoiseRangeMin());
            //噪音最大值
            noiseRangeMax.setText(editBean.getNoiseRangeMax());
            //设置EditText焦点监听
            noiseRangeMin.setOnFocusChangeListener(this);
            noiseRangeMax.setOnFocusChangeListener(this);

            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("noiseMin", noiseRangeMin);
            keyArray.put("noiseMax", noiseRangeMax);
            //显示控件
            showVisible(noiseRangePanel);
            //噪音区间大小值错误信息
            setErrorMsg(R.id.noise_range_min, R.id.noise_range_max);
        }

        //范围区间最小值
        if (editBean.getRangeMin() != null) {
            //设置控制的值
            rangeMin.setText(editBean.getRangeMin());
            //范围区间最大值
            rangeMax.setText(editBean.getRangeMax());
            //设置EditText焦点监听
            rangeMin.setOnFocusChangeListener(this);
            rangeMax.setOnFocusChangeListener(this);

            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("rangeMin", rangeMin);
            keyArray.put("rangeMax", rangeMax);
            //显示控件
            showVisible(rangePanel);
            //范围区间大小值错误信息
            setErrorMsg(R.id.range_min, R.id.range_max);
        }

        //剩于电流最小值
        if (editBean.getElectricMin() != null) {
            //设置控制的值
            electricMin.setText(editBean.getElectricMin());
            //剩于电流最大值
            electricMax.setText(editBean.getElectricMax());
            //设置EditText焦点监听
            electricMin.setOnFocusChangeListener(this);
            electricMax.setOnFocusChangeListener(this);

            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("electricMin", electricMin);
            keyArray.put("electricMax", electricMax);
            //显示控件
            showVisible(electricPanel);
            //电流区间大小值错误信息
            setErrorMsg(R.id.electric_min, R.id.electric_max);
        }

        //A相电流最小值
        if (editBean.getPhaseAMin() != null) {
            //设置控制的值
            phaseAMin.setText(editBean.getPhaseAMin());
            //A相电流最大值
            phaseAMax.setText(editBean.getPhaseAMax());
            //设置EditText焦点监听
            phaseAMin.setOnFocusChangeListener(this);
            phaseAMax.setOnFocusChangeListener(this);

            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("phaseAMin", phaseAMin);
            keyArray.put("phaseAMax", phaseAMax);
            //显示控件
            showVisible(phaseAPanel);
            //A相电流区间大小值错误信息
            setErrorMsg(R.id.phase_a_min, R.id.phase_a_max);
        }

        //A相电压最小值
        if (editBean.getVoltageAMin() != null) {
            //设置控制的值
            voltageAMin.setText(editBean.getVoltageAMin());
            //A相电压最大值
            voltageAMax.setText(editBean.getVoltageAMax());
            //设置EditText焦点监听
            voltageAMin.setOnFocusChangeListener(this);
            voltageAMax.setOnFocusChangeListener(this);

            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("voltageAMin", voltageAMin);
            keyArray.put("voltageAMax", voltageAMax);
            //显示控件
            showVisible(voltageAPanel);
            //A相电压区间大小值错误信息
            setErrorMsg(R.id.voltage_a_min, R.id.voltage_a_max);
        }

        //B相电流最小值
        if (editBean.getPhaseBMin() != null) {
            //设置控制的值
            phaseBMin.setText(editBean.getPhaseBMin());
            //B相电流最大值
            phaseBMax.setText(editBean.getPhaseBMax());
            //设置EditText焦点监听
            phaseBMin.setOnFocusChangeListener(this);
            phaseBMax.setOnFocusChangeListener(this);

            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("phaseBMin", phaseBMin);
            keyArray.put("phaseBMax", phaseBMax);
            //显示控件
            showVisible(phaseBPanel);
            //B相电流区间大小值错误信息
            setErrorMsg(R.id.phase_b_min, R.id.phase_b_max);
        }

        //B相电压最小值
        if (editBean.getVoltageBMin() != null) {
            //设置控制的值
            voltageBMin.setText(editBean.getVoltageBMin());
            //B相电压最小值
            voltageBMax.setText(editBean.getVoltageBMax());
            //设置EditText焦点监听
            voltageBMin.setOnFocusChangeListener(this);
            voltageBMax.setOnFocusChangeListener(this);

            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("voltageBMin", voltageBMin);
            keyArray.put("voltageBMax", voltageBMax);
            //显示控件
            showVisible(voltageBPanel);
            //B相电压区间大小值错误信息
            setErrorMsg(R.id.voltage_b_min, R.id.voltage_b_max);
        }

        //C相电流最小值
        if (editBean.getPhaseCMin() != null) {
            //设置控制的值
            phaseCMin.setText(editBean.getPhaseCMin());
            //C相电流最大值
            phaseCMax.setText(editBean.getPhaseCMax());
            //设置EditText焦点监听
            phaseCMin.setOnFocusChangeListener(this);
            phaseCMax.setOnFocusChangeListener(this);

            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("phaseCMin", phaseCMin);
            keyArray.put("phaseCMax", phaseCMax);
            //显示控件
            showVisible(phaseCPanel);
            //C相电流区间大小值错误信息
            setErrorMsg(R.id.phase_c_min, R.id.phase_c_max);
        }

        //C相电压最小值
        if (editBean.getVoltageCMin() != null) {
            //设置控制的值
            voltageCMin.setText(editBean.getVoltageCMin());
            //C相电压最大值
            voltageCMax.setText(editBean.getVoltageCMax());
            //设置EditText焦点监听
            voltageCMin.setOnFocusChangeListener(this);
            voltageCMax.setOnFocusChangeListener(this);

            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("voltageCMin", voltageCMin);
            keyArray.put("voltageCMax", voltageCMax);
            //显示控件
            showVisible(voltageCPanel);
            //C相电压区间大小值错误信息
            setErrorMsg(R.id.voltage_c_min, R.id.voltage_c_max);
        }

        //含氧量最小值
        if (editBean.getOxygenRangeMin() != null) {
            //设置控制的值
            oxygenRangeMin.setText(editBean.getOxygenRangeMin());
            //含氧量最大值
            oxygenRangeMax.setText(editBean.getOxygenRangeMax());
            //设置EditText焦点监听
            oxygenRangeMin.setOnFocusChangeListener(this);
            oxygenRangeMax.setOnFocusChangeListener(this);

            //保存实例，确定后需要用这个实例获取具体的值
            keyArray.put("oxygenRangeMin", oxygenRangeMin);
            keyArray.put("oxygenRangeMax", oxygenRangeMax);
            //显示控件
            showVisible(oxygenRangePanel);
            //含氧量区间大小值错误信息
            setErrorMsg(R.id.oxygen_range_min, R.id.oxygen_range_max);
        }


    }

    private void initView() {

        scrollView = findViewById(R.id.scroll_view);
        containerEditText = findViewById(R.id.container_edit_text);
        //设备名称
        aDeviceName = findViewById(R.id.a_device_name);
        //更新时间
        aDeviceInterval = findViewById(R.id.a_device_interval);
        //报警值
        aDeviceAlert = findViewById(R.id.a_device_alert);
        //线长度
        aDeviceLine = findViewById(R.id.a_device_line);
        //温度最小值
        tempRangeMin = findViewById(R.id.temp_range_min);
        //温度最大值
        tempRangeMax = findViewById(R.id.temp_range_max);
        //氨气最小值
        ammoniaRangeMin = findViewById(R.id.ammonia_range_min);
        //氨气最大值
        ammoniaRangeMax = findViewById(R.id.ammonia_range_max);
        //氮气最小值
        nitrogenRangeMin = findViewById(R.id.nitrogen_range_min);
        //氮气最大值
        nitrogenRangeMax = findViewById(R.id.nitrogen_range_max);
        //浓度最小值
        concentrationRangeMin = findViewById(R.id.concentration_range_min);
        //浓度最大值
        concentrationRangeMax = findViewById(R.id.concentration_range_max);
        //湿度最小值
        humRangeMin = findViewById(R.id.hum_range_min);
        //湿度最大值
        humRangeMax = findViewById(R.id.hum_range_max);
        //噪音最小值
        noiseRangeMin = findViewById(R.id.noise_range_min);
        //噪音最大值
        noiseRangeMax = findViewById(R.id.noise_range_max);
        //范围区间最小值
        rangeMin = findViewById(R.id.range_min);
        //范围区间最大值
        rangeMax = findViewById(R.id.range_max);
        //剩于电流最小值
        electricMin = findViewById(R.id.electric_min);
        //剩于电流最大值
        electricMax = findViewById(R.id.electric_max);
        //A相电流最小值
        phaseAMin = findViewById(R.id.phase_a_min);
        //A相电流最大值
        phaseAMax = findViewById(R.id.phase_a_max);
        //A相电压最小值
        voltageAMin = findViewById(R.id.voltage_a_min);
        //A相电压最大值
        voltageAMax = findViewById(R.id.voltage_a_max);
        //B相电流最小值
        phaseBMin = findViewById(R.id.phase_b_min);
        //B相电流最大值
        phaseBMax = findViewById(R.id.phase_b_max);
        //B相电压最小值
        voltageBMin = findViewById(R.id.voltage_b_min);
        //B相电压最大值
        voltageBMax = findViewById(R.id.voltage_b_max);
        //C相电流最小值
        phaseCMin = findViewById(R.id.phase_c_min);
        //C相电流最大值
        phaseCMax = findViewById(R.id.phase_c_max);
        //C相电压最小值
        voltageCMin = findViewById(R.id.voltage_c_min);
        //C相电压最大值
        voltageCMax = findViewById(R.id.voltage_c_max);
        //含氧量最小值
        oxygenRangeMin = findViewById(R.id.oxygen_range_min);
        //含氧量最大值
        oxygenRangeMax = findViewById(R.id.oxygen_range_max);
        //设备地址
        deviceAddress = findViewById(R.id.deviceAddress);
        //设备地址
        deviceAddress = findViewById(R.id.deviceAddress);
        //所属分组
        classifySpinner = findViewById(R.id.classify_spinner);
        //IP地址
        ipAddressSpinner = findViewById(R.id.ip_spinner);
        //功能码
        functionCode = findViewById(R.id.functionCode);

        //取消按钮
        btnClr = findViewById(R.id.btn_clr);
        btnClr.setOnClickListener(this);
        //确定按钮
        btnDone = findViewById(R.id.btn_done);
        btnDone.setOnClickListener(this);

        //以下的所有面板是为了控制显示或者隐藏的

        //设备名称-面板
        aDeviceNamePanel = findViewById(R.id.a_device_name_panel);
        //更新时间-面板
        aDeviceIntervalPanel = findViewById(R.id.a_device_interval_panel);
        //报警值-面板
        aDeviceAlertPanel = findViewById(R.id.a_device_alert_panel);
        //线长度-面板
        aDeviceLinePanel = findViewById(R.id.a_device_line_panel);
        //温度区间-面板
        tempRangePanel = findViewById(R.id.temp_range_panel);
        //氨气区间-面板
        ammoniaRangePanel = findViewById(R.id.ammonia_range_panel);
        //氮气区间-面板
        nitrogenRangePanel = findViewById(R.id.nitrogen_range_panel);
        //浓度区间-面板
        concentrationRangePanel = findViewById(R.id.concentration_range_panel);
        //湿度区间-面板
        humRangePanel = findViewById(R.id.hum_range_panel);
        //噪音区间-面板
        noiseRangePanel = findViewById(R.id.noise_range_panel);
        //范围区间-面板
        rangePanel = findViewById(R.id.range_panel);
        //电流区间-面板
        electricPanel = findViewById(R.id.electric_panel);
        //A相电流区间-面板
        phaseAPanel = findViewById(R.id.phase_a_panel);
        //A相电压区间-面板
        voltageAPanel = findViewById(R.id.voltage_a_panel);
        //B相电流区间-面板
        phaseBPanel = findViewById(R.id.phase_b_panel);
        //B相电压区间-面板
        voltageBPanel = findViewById(R.id.voltage_b_panel);
        //C相电流区间-面板
        phaseCPanel = findViewById(R.id.phase_c_panel);
        //C相电压区间-面板
        voltageCPanel = findViewById(R.id.voltage_c_panel);
        //含氧量区间-面板
        oxygenRangePanel = findViewById(R.id.oxygen_range_panel);
        //设备地址-面板
        deviceAddressPanel = findViewById(R.id.deviceAddressPanel);
        //设备通道-面板
        deviceGalleryPanel = findViewById(R.id.deviceGalleryPanel);
        //所属分组-面板
        classifySpinnerPanel = findViewById(R.id.classify_spinner_panel);
        //IP 地址-面板
        ipSpinnerPanel = findViewById(R.id.ip_spinner_panel);
        //功能码-面板
        functionCodePanel = findViewById(R.id.functionCodePanel);

        //温度的错误提示
        tempAlert = findViewById(R.id.temp_alert);
        //氨气的错误提示
        ammoniaAlert = findViewById(R.id.ammonia_alert);
        //氮气的错误提示
        nitrogenAlert = findViewById(R.id.nitrogen_alert);
        //浓度的错误提示
        concentrationAlert = findViewById(R.id.concentration_alert);
        //湿度的错误提示
        humAlert = findViewById(R.id.hum_alert);
        //噪音的错误提示
        noiseAlert = findViewById(R.id.noise_alert);
        //范围区间的错误提示
        rangeAlert = findViewById(R.id.range_alert);
        //电流区间的错误提示
        electricAlert = findViewById(R.id.electric_alert);
        //A相电流区间的错误提示
        phaseAAlert = findViewById(R.id.phase_a_alert);
        //A相电压区间的错误提示
        voltageAAlert = findViewById(R.id.voltage_a_alert);
        // B相电流区间的错误提示
        phaseBAlert = findViewById(R.id.phase_b_alert);
        //C相电流区间的错误提示
        voltageBAlert = findViewById(R.id.voltage_b_alert);
        //C相电流区间的错误提示
        phaseCAlert = findViewById(R.id.phase_c_alert);
        //C相电压区间的错误提示
        voltageCAlert = findViewById(R.id.voltage_c_alert);
        //含氧量区间的错误提示
        oxygenAlert = findViewById(R.id.oxygen_alert);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {

        if (mPosition > 0) {
            mDrawerItemClickListener.selectItem(0);
        } else {
            super.onBackPressed();
        }
    }

    private void setupSpinner() {
        Spinner spinner = findViewById(R.id.functionCode);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.functionCodeArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinnerAddress = findViewById(R.id.deviceAddress);
        ArrayAdapter<CharSequence> address = ArrayAdapter.createFromResource(this, R.array.deviceAddress, android.R.layout.simple_spinner_item);
        address.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAddress.setAdapter(address);


    }

    private void clearValidation() {
        if (mAwesomeValidation != null) {
            mAwesomeValidation.clear();
        }
    }

    private void initValidation(String style) {
        switch (ValidationStyle.valueOf(style)) {
            case BASIC:
                mAwesomeValidation = new AwesomeValidation(BASIC);
                break;
            case COLORATION:
                mAwesomeValidation = new AwesomeValidation(COLORATION);
                mAwesomeValidation.setColor(Color.YELLOW);
                break;
            case UNDERLABEL:
                mAwesomeValidation = new AwesomeValidation(UNDERLABEL);
                mAwesomeValidation.setContext(this);
                break;
            case TEXT_INPUT_LAYOUT:
                mAwesomeValidation = new AwesomeValidation(TEXT_INPUT_LAYOUT);
                mAwesomeValidation.setTextInputLayoutErrorTextAppearance(R.style.TextInputLayoutErrorStyle);
                break;
        }
    }

    /**
     * 只匹配浮点数字
     *
     * @param minID 最大值的Layout ID
     * @param maxID 最小值的Layout ID
     */
    public void setErrorMsg(int minID, int maxID) {

        //最大值
        mAwesomeValidation.addValidation(this, minID, "^(-?\\d+)(\\.\\d+)?$", R.string.err_min);
        //最小值
        mAwesomeValidation.addValidation(this, maxID, "^(-?\\d+)(\\.\\d+)?$", R.string.err_max);

    }

    private void addValidationForEditText(Activity activity) {

        //设备名称
        mAwesomeValidation.addValidation(activity, R.id.a_device_name, "^[\\u4E00-\\u9FA5A-Za-z0-9]+$", R.string.err_deviceName);
        //更新时间
        mAwesomeValidation.addValidation(activity, R.id.a_device_interval, "[0-9]+", R.string.err_deviceInterval);

        //温度大小值错误信息
        setErrorMsg(R.id.temp_range_min, R.id.temp_range_max);

        //氨气大小值错误信息
        setErrorMsg(R.id.ammonia_range_min, R.id.ammonia_range_max);

        //氮气大小值错误信息
        setErrorMsg(R.id.nitrogen_range_min, R.id.nitrogen_range_max);

        //浓度大小值错误信息
        setErrorMsg(R.id.concentration_range_min, R.id.concentration_range_max);

        //湿度大小值错误信息
        setErrorMsg(R.id.hum_range_min, R.id.hum_range_max);

        //噪音区间大小值错误信息
        setErrorMsg(R.id.noise_range_min, R.id.noise_range_max);

        //范围区间大小值错误信息
        setErrorMsg(R.id.range_min, R.id.range_max);

        //电流区间大小值错误信息
        setErrorMsg(R.id.electric_min, R.id.electric_max);

        //A相电流区间大小值错误信息
        setErrorMsg(R.id.phase_a_min, R.id.phase_a_max);
        //A相电压区间大小值错误信息
        setErrorMsg(R.id.voltage_a_min, R.id.voltage_a_max);

        //B相电流区间大小值错误信息
        setErrorMsg(R.id.phase_b_min, R.id.phase_b_max);
        //B相电压区间大小值错误信息
        setErrorMsg(R.id.voltage_b_min, R.id.voltage_b_max);

        //C相电流区间大小值错误信息
        setErrorMsg(R.id.phase_c_min, R.id.phase_c_max);
        //C相电压区间大小值错误信息
        setErrorMsg(R.id.voltage_c_min, R.id.voltage_c_max);

        //含氧量区间大小值错误信息
        setErrorMsg(R.id.oxygen_range_min, R.id.oxygen_range_max);

//        compareValue(tempRangeMin, tempRangeMax, activity, R.id.temp_range_min, R.id.temp_range_max);
//        //最大值
//        mAwesomeValidation.addValidation(activity, R.id.temp_range_min, "^(-?\\d+)(\\.\\d+)?$", R.string.err_min);
//        //最小值
//        mAwesomeValidation.addValidation(activity, R.id.range_max, "^(-?\\d+)(\\.\\d+)?$", R.string.err_max);

//        mAwesomeValidation.addValidation(activity, R.id.edt_password, "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}", R.string.err_password);
////        mAwesomeValidation.addValidation(activity, R.id.edt_password_confirmation, R.id.edt_password, R.string.err_password_confirmation);
////        mAwesomeValidation.addValidation(activity, R.id.edt_firstname, "[a-zA-Z\\s]+", R.string.err_name);
////        mAwesomeValidation.addValidation(activity, R.id.edt_lastname, "[a-zA-Z\\s]+", R.string.err_name);

        mAwesomeValidation.addValidation(activity, R.id.functionCode, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals("< Please select one >")) {
                    return false;
                } else {
                    return true;
                }
            }
        }, new CustomValidationCallback() {
            @Override
            public void execute(ValidationHolder validationHolder) {
                TextView textViewError = (TextView) ((Spinner) validationHolder.getView()).getSelectedView();
                textViewError.setError(validationHolder.getErrMsg());
                textViewError.setTextColor(Color.RED);
            }
        }, new CustomErrorReset() {
            @Override
            public void reset(ValidationHolder validationHolder) {
                TextView textViewError = (TextView) ((Spinner) validationHolder.getView()).getSelectedView();
                textViewError.setError(null);
                textViewError.setTextColor(Color.BLACK);
            }
        }, R.string.err_tech_stacks);

//        setValidationButtons();
    }


    private void setValidationButtons() {
        Button btnDone = (Button) findViewById(R.id.btn_done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mAwesomeValidation.validate()) {
                    mScrollView.fullScroll(View.FOCUS_DOWN);


                } else {

                }
            }
        });

        Button btnClr = findViewById(R.id.btn_clr);
        btnClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAwesomeValidation.clear();

//                deviceAction.cancelMine();
            }
        });
    }

    public void compareValue(EditText min, EditText max, TextView view) {

        try {
            float minValue = Float.parseFloat(min.getText().toString());
            float maxValue = Float.parseFloat(max.getText().toString());

            if (minValue > maxValue) {
                //最小值不能大于最大值
                view.setText("最小值不能大于最大值");
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }

//            else if (maxValue < minValue) {
//                //最大值不能小于最小值
//                mAwesomeValidation.addValidation(activity, maxID, "", R.string.err_maxForMin);
//            } else {
//                //默认检查
//                //最大值
//                mAwesomeValidation.addValidation(activity, R.id.temp_range_min, "^(-?\\d+)(\\.\\d+)?$", R.string.err_min);
//                //最小值
//                mAwesomeValidation.addValidation(activity, R.id.range_max, "^(-?\\d+)(\\.\\d+)?$", R.string.err_max);
//            }
        } catch (NumberFormatException e) {
            Log.d("compareValue", "数据输换出错: 请检查传入的值是否有误");
        }

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.btn_done) {
            if (mAwesomeValidation.validate()) {
                //数据无误
                mScrollView.fullScroll(View.FOCUS_DOWN);
                //不显示成功的图标
//                mViewSuccess.setVisibility(View.VISIBLE);
                //如果返回真，代表数据有误
                if (alertVisible()) {
                    Toast.makeText(this, "数据有误，请检查后再提交", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (editBean.getChangeOrAdd().equals("添加") && editBean.getDeviceIPBean() != null) {
                    //如果数据检查无误后，需要获取所选IP的 diID，添加设备时需要用到
                    int selectPos = ipAddressSpinner.getSelectedItemPosition();

                    int diId = editBean.getDeviceIPBean().get(selectPos).getDiId();

                    EditText diIdEdit = new EditText(this);
                    diIdEdit.setText(String.valueOf(diId));
                    keyArray.put("diId", diIdEdit);

                    //添加时，也需要获取 gId
                    selectPos = classifySpinner.getSelectedItemPosition();
                    EditText gIdEdit = new EditText(this);
                    gIdEdit.setText(String.valueOf(selectPos));

                    keyArray.put("gId", gIdEdit);

                    //如果数据无误，则执行回调操作
                    deviceAction.deterMine(keyArray, spinnerMap);
                } else {
                    //修改
                    deviceAction.deterChange(keyArray, spinnerMap);
                }


            } else {
                //数据有误

            }
        } else if (id == R.id.btn_clr) {
            mAwesomeValidation.clear();

            finish();
        }
    }

    /**
     * 检查数据是否正确
     *
     * @return
     */
    public boolean deterValue() {
        if (mAwesomeValidation.validate()) {
            mScrollView.fullScroll(View.FOCUS_DOWN);


        } else {


        }

        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    /**
     * 每次输入数据时，都会回调该方法
     *
     * @param s      每次输入的值
     * @param start
     * @param before
     * @param count
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d("ljkdsfa", "onTextChanged: " + s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * 获取焦点和失败焦点都会回调该方法
     *
     * @param v        EditText View
     * @param hasFocus 是否聚焦
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        //获取View的ID
        int viewID = v.getId();

        //如果聚焦，则不进行做判断，失败焦点才进行数据的判断
        if (hasFocus)
            return;

        //温度的上限和下限
        if (viewID == R.id.temp_range_min || viewID == R.id.temp_range_max) {
            compareValue(tempRangeMin, tempRangeMax, tempAlert);
        }
        //湿度的上限和下限
        if (viewID == R.id.hum_range_min || viewID == R.id.hum_range_max) {
            compareValue(humRangeMin, humRangeMax, humAlert);
        }
        //氨气的上限和下限
        if (viewID == R.id.ammonia_range_min || viewID == R.id.ammonia_range_max) {
            compareValue(ammoniaRangeMin, ammoniaRangeMax, ammoniaAlert);
        }
        //氮气的上限和下限
        if (viewID == R.id.nitrogen_range_min || viewID == R.id.nitrogen_range_max) {
            compareValue(nitrogenRangeMin, nitrogenRangeMax, nitrogenAlert);
        }
        //浓度的上限和下限
        if (viewID == R.id.concentration_range_min || viewID == R.id.concentration_range_max) {
            compareValue(concentrationRangeMin, concentrationRangeMax, concentrationAlert);
        }
        //噪音的上限和下限
        if (viewID == R.id.noise_range_min || viewID == R.id.noise_range_max) {
            compareValue(noiseRangeMin, noiseRangeMax, noiseAlert);
        }
        //范围区间的上限和下限
        if (viewID == R.id.range_min || viewID == R.id.range_max) {
            compareValue(rangeMin, rangeMax, rangeAlert);
        }
        //电流区间的上限和下限
        if (viewID == R.id.electric_min || viewID == R.id.electric_max) {
            compareValue(electricMin, electricMax, electricAlert);
        }
        //A相电流区间的上限和下限
        if (viewID == R.id.phase_a_min || viewID == R.id.phase_a_max) {
            compareValue(phaseAMin, phaseAMax, phaseAAlert);
        }
        //A相电压区间的上限和下限
        if (viewID == R.id.voltage_a_min || viewID == R.id.voltage_a_max) {
            compareValue(voltageAMin, voltageAMax, voltageAAlert);
        }
        //B相电流区间的上限和下限
        if (viewID == R.id.phase_b_min || viewID == R.id.phase_b_max) {
            compareValue(phaseBMin, phaseBMax, phaseBAlert);
        }
        //B相电压区间的上限和下限
        if (viewID == R.id.voltage_b_min || viewID == R.id.voltage_b_max) {
            compareValue(voltageBMin, voltageBMax, voltageBAlert);
        }
        //C相电流区间的上限和下限
        if (viewID == R.id.phase_c_min || viewID == R.id.phase_c_max) {
            compareValue(phaseCMin, phaseCMax, phaseCAlert);
        }
        //C相电压区间的上限和下限
        if (viewID == R.id.voltage_c_min || viewID == R.id.voltage_c_max) {
            compareValue(voltageCMin, voltageCMax, voltageCAlert);
        }

    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

        private void selectItem(int position) {
            mDrawerList.setItemChecked(position, true);
            mPosition = position;
            String style = mStyles[mPosition];
            setTitle(style);
            mViewContainerEditText.setVisibility(position < TEXT_INPUT_LAYOUT.value() ? View.VISIBLE : View.GONE);
            mViewContainerTextInputLayout.setVisibility(position < TEXT_INPUT_LAYOUT.value() ? View.GONE : View.VISIBLE);

//            if (position < TEXT_INPUT_LAYOUT.value()) {
//                addValidationForEditText(ActionControl.this);
//            } else if (position == TEXT_INPUT_LAYOUT.value()) {
//                addValidationForTextInputLayout(ActionControl.this);
//            }
        }
    }

    /**
     * 检查数据是否正确，如果都正确，返回false
     */
    public boolean alertVisible() {

        if (visibleCheck(tempAlert))
            return true;

        if (visibleCheck(ammoniaAlert))
            return true;

        if (visibleCheck(nitrogenAlert))
            return true;

        if (visibleCheck(concentrationAlert))
            return true;

        if (visibleCheck(humAlert))
            return true;

        if (visibleCheck(noiseAlert))
            return true;

        if (visibleCheck(rangeAlert))
            return true;

        if (visibleCheck(electricAlert))
            return true;

        if (visibleCheck(phaseAAlert))
            return true;

        if (visibleCheck(voltageAAlert))
            return true;

        if (visibleCheck(phaseBAlert))
            return true;

        if (visibleCheck(voltageBAlert))
            return true;

        if (visibleCheck(phaseCAlert))
            return true;

        if (visibleCheck(voltageCAlert))
            return true;

        if (visibleCheck(oxygenAlert))
            return true;

        return false;
    }

    /**
     * 获取警告是否显示
     *
     * @param t
     * @return
     */
    public boolean visibleCheck(TextView t) {
        return t.getVisibility() == View.VISIBLE;
    }

}
