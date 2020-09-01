package bean;

/**
 * @ClassName: RecyclerCheckBean
 * @Description: RecyclerView 列表选中状态的Bean类，由子类继承
 * @Author: YIN LUO FEI
 * @Date: 2020/7/30 16:16
 */
public class RecyclerCheckBean {

    //保存是否点击的状态
    private boolean isCheck = false;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

}
