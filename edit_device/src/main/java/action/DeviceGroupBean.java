package action;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: DeviceGroupBean
 * @Description: 设备的所有组
 * @Author: YIN LUO FEI
 * @Date: 2020/4/22 15:11
 */
public class DeviceGroupBean implements Serializable {


    /**
     * gId : 1
     * parentId : 0
     * gName : 总表
     * children : [{"gId":2,"parentId":1,"gName":"莱安","children":[]},{"gId":3,"parentId":1,"gName":"唯莱","children":[]}]
     */

    private int gId;
    private int parentId;
    private String gName;
    private List<ChildrenBean> children;

    public int getGId() {
        return gId;
    }

    public void setGId(int gId) {
        this.gId = gId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getGName() {
        return gName;
    }

    public void setGName(String gName) {
        this.gName = gName;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean implements Serializable {
        /**
         * gId : 2
         * parentId : 1
         * gName : 莱安
         * children : []
         */

        private int gId;
        private int parentId;
        private String gName;
        private List<?> children;

        public int getGId() {
            return gId;
        }

        public void setGId(int gId) {
            this.gId = gId;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getGName() {
            return gName;
        }

        public void setGName(String gName) {
            this.gName = gName;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }
    }
}
