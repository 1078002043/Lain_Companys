package util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;

import com.example.base.R;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * 动态添加MaterialEdit的工具类
 */
public class DynamicMaterialEdit {

    private static DynamicMaterialEdit dynamicMaterialEdit;
    private MaterialEditText dynamicEdit;
    private LayoutInflater layoutInflater;
    private Context context;

    /**
     * 创建单例
     *
     * @return 返回 DynamicMaterialEdit
     */
    public static DynamicMaterialEdit getInstance() {

        if (dynamicMaterialEdit == null)
            synchronized (DynamicMaterialEdit.class) {
                if (dynamicMaterialEdit == null)
                    dynamicMaterialEdit = new DynamicMaterialEdit();
            }

        return dynamicMaterialEdit;
    }

    /**
     * 设置动态添加View需要使用到的引用
     *
     * @param layoutInflater 动态添加控件的引用
     * @param context        上下文
     */
    public void setLayoutInflater(LayoutInflater layoutInflater, Context context) {
        this.layoutInflater = layoutInflater;
        this.context = context;
    }

    /**
     * 动态添加 EditText
     *
     * @param id    editText 的ID
     * @param hInt  editText 的默认提示文字
     * @param value 填充的值
     * @return MaterialEditText实例
     */
    public MaterialEditText setDynamicMaterialEdit(int id, String hInt, String value) {

        try {
            dynamicEdit = (MaterialEditText) layoutInflater.inflate(R.layout.dynamic_edit, null, false);
            //设置ID
            dynamicEdit.setId(id);
            //设置默认提示文字
            dynamicEdit.setHint(hInt);
            //设置字体大小
            dynamicEdit.setTextSize(14);
            //设置浮动提示文字
            dynamicEdit.setFloatingLabelText(hInt);
            //设置填充值
            dynamicEdit.setText(value);
            return dynamicEdit;
        } catch (Exception e) {
            Log.d("dynamic_view", "setDynamicMaterialEdit: LayoutInflater 可能是 NULL " + e.getMessage());
        }

        return null;
    }

}
