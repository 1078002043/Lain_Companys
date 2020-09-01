package computer_room.bean;

import java.util.List;

/**
 * 运维监控的 服务器 数据
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/15 10 : 09
 */
public class SystemServerBean {


    /**
     * computerMessage : {"cpuCombined":22,"cpuIdle":78,"cpuMhz":2900,"cpuNice":0,"cpuSys":22,"cpuUser":0,"cpuWait":0,"memFree":2115.89,"memTotal":8132.42,"memUsed":6016.52,"swapFree":2792.35,"swapTotal":17152.38,"swapUsed":14360.03}
     * diskList : [{"diskFreeSpace":6.8780544E10,"diskTotalSpace":1.07376275456E11,"diskUsedSpace":3.8595731456E10,"spaceName":"C盘使用情况:"},{"diskFreeSpace":3.2753328128E10,"diskTotalSpace":1.32230221824E11,"diskUsedSpace":9.9476893696E10,"spaceName":"D盘使用情况:"},{"diskFreeSpace":3.22588807168E11,"diskTotalSpace":4.80101003264E11,"diskUsedSpace":1.57512196096E11,"spaceName":"E盘使用情况:"}]
     */

    private ComputerMessageBean computerMessage;
    private List<DiskListBean> diskList;

    public ComputerMessageBean getComputerMessage() {
        return computerMessage;
    }

    public void setComputerMessage(ComputerMessageBean computerMessage) {
        this.computerMessage = computerMessage;
    }

    public List<DiskListBean> getDiskList() {
        return diskList;
    }

    public void setDiskList(List<DiskListBean> diskList) {
        this.diskList = diskList;
    }

    public static class ComputerMessageBean {
        /**
         * cpuCombined : 22
         * cpuIdle : 78
         * cpuMhz : 2900
         * cpuNice : 0
         * cpuSys : 22
         * cpuUser : 0
         * cpuWait : 0
         * memFree : 2115.89
         * memTotal : 8132.42
         * memUsed : 6016.52
         * swapFree : 2792.35
         * swapTotal : 17152.38
         * swapUsed : 14360.03
         */

        private int cpuCombined;
        private int cpuIdle;
        private int cpuMhz;
        private int cpuNice;
        private int cpuSys;
        private int cpuUser;
        private int cpuWait;
        private double memFree;
        private double memTotal;
        private double memUsed;
        private double swapFree;
        private double swapTotal;
        private double swapUsed;

        public int getCpuCombined() {
            return cpuCombined;
        }

        public void setCpuCombined(int cpuCombined) {
            this.cpuCombined = cpuCombined;
        }

        public int getCpuIdle() {
            return cpuIdle;
        }

        public void setCpuIdle(int cpuIdle) {
            this.cpuIdle = cpuIdle;
        }

        public int getCpuMhz() {
            return cpuMhz;
        }

        public void setCpuMhz(int cpuMhz) {
            this.cpuMhz = cpuMhz;
        }

        public int getCpuNice() {
            return cpuNice;
        }

        public void setCpuNice(int cpuNice) {
            this.cpuNice = cpuNice;
        }

        public int getCpuSys() {
            return cpuSys;
        }

        public void setCpuSys(int cpuSys) {
            this.cpuSys = cpuSys;
        }

        public int getCpuUser() {
            return cpuUser;
        }

        public void setCpuUser(int cpuUser) {
            this.cpuUser = cpuUser;
        }

        public int getCpuWait() {
            return cpuWait;
        }

        public void setCpuWait(int cpuWait) {
            this.cpuWait = cpuWait;
        }

        public double getMemFree() {
            return memFree;
        }

        public void setMemFree(double memFree) {
            this.memFree = memFree;
        }

        public double getMemTotal() {
            return memTotal;
        }

        public void setMemTotal(double memTotal) {
            this.memTotal = memTotal;
        }

        public double getMemUsed() {
            return memUsed;
        }

        public void setMemUsed(double memUsed) {
            this.memUsed = memUsed;
        }

        public double getSwapFree() {
            return swapFree;
        }

        public void setSwapFree(double swapFree) {
            this.swapFree = swapFree;
        }

        public double getSwapTotal() {
            return swapTotal;
        }

        public void setSwapTotal(double swapTotal) {
            this.swapTotal = swapTotal;
        }

        public double getSwapUsed() {
            return swapUsed;
        }

        public void setSwapUsed(double swapUsed) {
            this.swapUsed = swapUsed;
        }
    }

    public static class DiskListBean {
        /**
         * diskFreeSpace : 6.8780544E10
         * diskTotalSpace : 1.07376275456E11
         * diskUsedSpace : 3.8595731456E10
         * spaceName : C盘使用情况:
         */

        private double diskFreeSpace;
        private double diskTotalSpace;
        private double diskUsedSpace;
        private String spaceName;

        public double getDiskFreeSpace() {
            return diskFreeSpace;
        }

        public void setDiskFreeSpace(double diskFreeSpace) {
            this.diskFreeSpace = diskFreeSpace;
        }

        public double getDiskTotalSpace() {
            return diskTotalSpace;
        }

        public void setDiskTotalSpace(double diskTotalSpace) {
            this.diskTotalSpace = diskTotalSpace;
        }

        public double getDiskUsedSpace() {
            return diskUsedSpace;
        }

        public void setDiskUsedSpace(double diskUsedSpace) {
            this.diskUsedSpace = diskUsedSpace;
        }

        public String getSpaceName() {
            return spaceName;
        }

        public void setSpaceName(String spaceName) {
            this.spaceName = spaceName;
        }
    }
}
