package bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

import java.util.List;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/19 23:24
 * Description：导出Excel日志
 **/
@SmartTable(name = "日志管理")
public class AddEventBean {
    /**
     * total : 79
     * pages : 8
     * sysLog : {"total":79,"list":[{"id":1285,"username":"admin","operationType":"添加操作","operationName":"添加一个用户","method":null,"params":null,"ip":"192.168.1.119","createDate":"2020-07-29 17:14:26","startTime":null,"endTime":null,"pageNum":null,"pageSize":null},{"id":1282,"username":"admin","operationType":"添加操作","operationName":"添加一个用户","method":null,"params":null,"ip":"192.168.1.119","createDate":"2020-07-29 16:57:39","startTime":null,"endTime":null,"pageNum":null,"pageSize":null},{"id":1276,"username":"admin","operationType":"添加操作","operationName":"添加一个用户","method":null,"params":null,"ip":"192.168.1.119","createDate":"2020-07-29 16:48:57","startTime":null,"endTime":null,"pageNum":null,"pageSize":null},{"id":681,"username":"admin","operationType":"添加操作","operationName":"添加角色","method":null,"params":null,"ip":"192.168.1.119","createDate":"2020-07-28 08:13:53","startTime":null,"endTime":null,"pageNum":null,"pageSize":null},{"id":678,"username":"admin","operationType":"添加操作","operationName":"添加一个用户","method":null,"params":null,"ip":"192.168.1.119","createDate":"2020-07-28 08:12:27","startTime":null,"endTime":null,"pageNum":null,"pageSize":null},{"id":662,"username":"admin","operationType":"添加操作","operationName":"添加权限","method":null,"params":null,"ip":"192.168.1.253","createDate":"2020-07-27 21:58:25","startTime":null,"endTime":null,"pageNum":null,"pageSize":null},{"id":661,"username":"admin","operationType":"添加操作","operationName":"添加角色","method":null,"params":null,"ip":"192.168.1.253","createDate":"2020-07-27 21:58:07","startTime":null,"endTime":null,"pageNum":null,"pageSize":null},{"id":567,"username":"admin","operationType":"添加操作","operationName":"添加一个用户","method":null,"params":null,"ip":"192.168.1.234","createDate":"2020-07-27 17:17:11","startTime":null,"endTime":null,"pageNum":null,"pageSize":null},{"id":563,"username":"admin","operationType":"添加操作","operationName":"添加一个用户","method":null,"params":null,"ip":"192.168.1.234","createDate":"2020-07-27 17:04:00","startTime":null,"endTime":null,"pageNum":null,"pageSize":null},{"id":560,"username":"admin","operationType":"添加操作","operationName":"添加一个用户","method":null,"params":null,"ip":"192.168.1.234","createDate":"2020-07-27 17:01:07","startTime":null,"endTime":null,"pageNum":null,"pageSize":null}],"pageNum":1,"pageSize":10,"size":10,"startRow":1,"endRow":10,"pages":8,"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8],"navigateFirstPage":1,"navigateLastPage":8,"firstPage":1,"lastPage":8}
     */

    private int total;
    private int pages;
    private SysLogBean sysLog;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public SysLogBean getSysLog() {
        return sysLog;
    }

    public void setSysLog(SysLogBean sysLog) {
        this.sysLog = sysLog;
    }

    public static class SysLogBean {
        /**
         * total : 79
         * list : [{"id":1285,"username":"admin","operationType":"添加操作","operationName":"添加一个用户","method":null,"params":null,"ip":"192.168.1.119","createDate":"2020-07-29 17:14:26","startTime":null,"endTime":null,"pageNum":null,"pageSize":null},{"id":1282,"username":"admin","operationType":"添加操作","operationName":"添加一个用户","method":null,"params":null,"ip":"192.168.1.119","createDate":"2020-07-29 16:57:39","startTime":null,"endTime":null,"pageNum":null,"pageSize":null},{"id":1276,"username":"admin","operationType":"添加操作","operationName":"添加一个用户","method":null,"params":null,"ip":"192.168.1.119","createDate":"2020-07-29 16:48:57","startTime":null,"endTime":null,"pageNum":null,"pageSize":null},{"id":681,"username":"admin","operationType":"添加操作","operationName":"添加角色","method":null,"params":null,"ip":"192.168.1.119","createDate":"2020-07-28 08:13:53","startTime":null,"endTime":null,"pageNum":null,"pageSize":null},{"id":678,"username":"admin","operationType":"添加操作","operationName":"添加一个用户","method":null,"params":null,"ip":"192.168.1.119","createDate":"2020-07-28 08:12:27","startTime":null,"endTime":null,"pageNum":null,"pageSize":null},{"id":662,"username":"admin","operationType":"添加操作","operationName":"添加权限","method":null,"params":null,"ip":"192.168.1.253","createDate":"2020-07-27 21:58:25","startTime":null,"endTime":null,"pageNum":null,"pageSize":null},{"id":661,"username":"admin","operationType":"添加操作","operationName":"添加角色","method":null,"params":null,"ip":"192.168.1.253","createDate":"2020-07-27 21:58:07","startTime":null,"endTime":null,"pageNum":null,"pageSize":null},{"id":567,"username":"admin","operationType":"添加操作","operationName":"添加一个用户","method":null,"params":null,"ip":"192.168.1.234","createDate":"2020-07-27 17:17:11","startTime":null,"endTime":null,"pageNum":null,"pageSize":null},{"id":563,"username":"admin","operationType":"添加操作","operationName":"添加一个用户","method":null,"params":null,"ip":"192.168.1.234","createDate":"2020-07-27 17:04:00","startTime":null,"endTime":null,"pageNum":null,"pageSize":null},{"id":560,"username":"admin","operationType":"添加操作","operationName":"添加一个用户","method":null,"params":null,"ip":"192.168.1.234","createDate":"2020-07-27 17:01:07","startTime":null,"endTime":null,"pageNum":null,"pageSize":null}]
         * pageNum : 1
         * pageSize : 10
         * size : 10
         * startRow : 1
         * endRow : 10
         * pages : 8
         * prePage : 0
         * nextPage : 2
         * isFirstPage : true
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2,3,4,5,6,7,8]
         * navigateFirstPage : 1
         * navigateLastPage : 8
         * firstPage : 1
         * lastPage : 8
         */

        private int total;
        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int firstPage;
        private int lastPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * id : 1285
             * username : admin
             * operationType : 添加操作
             * operationName : 添加一个用户
             * method : null
             * params : null
             * ip : 192.168.1.119
             * createDate : 2020-07-29 17:14:26
             * startTime : null
             * endTime : null
             * pageNum : null
             * pageSize : null
             */

            private int id;
            @SmartColumn(id = 1, name = "操作人员", autoMerge = true)
            private String username;
            @SmartColumn(id = 2, name = "日志类型")
            private String operationType;
            @SmartColumn(id = 3, name = "日志内容")
            private String operationName;
            private Object method;
            private Object params;
            @SmartColumn(id = 4, name = "操作IP")
            private String ip;
            @SmartColumn(id = 5, name = "操作时间")
            private String createDate;

            private Object startTime;
            private Object endTime;
            private Object pageNum;
            private Object pageSize;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getOperationType() {
                return operationType;
            }

            public void setOperationType(String operationType) {
                this.operationType = operationType;
            }

            public String getOperationName() {
                return operationName;
            }

            public void setOperationName(String operationName) {
                this.operationName = operationName;
            }

            public Object getMethod() {
                return method;
            }

            public void setMethod(Object method) {
                this.method = method;
            }

            public Object getParams() {
                return params;
            }

            public void setParams(Object params) {
                this.params = params;
            }

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public Object getStartTime() {
                return startTime;
            }

            public void setStartTime(Object startTime) {
                this.startTime = startTime;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public Object getPageNum() {
                return pageNum;
            }

            public void setPageNum(Object pageNum) {
                this.pageNum = pageNum;
            }

            public Object getPageSize() {
                return pageSize;
            }

            public void setPageSize(Object pageSize) {
                this.pageSize = pageSize;
            }
        }
    }


    /**
     * id : 1
     * username : admin
     * operationType : 查询操作
     * operationName : 查询指定用户
     * method : null
     * params : null
     * ip : null
     * createDate : 2019-12-20 17:06:51
     */

//    private int id;
//    @SmartColumn(id = 1, name = "操作人员", autoMerge = true)
//    private String username;
//    @SmartColumn(id = 3, name = "日志类型")
//    private String operationType;
//    @SmartColumn(id = 3, name = "日志内容")
//    private String operationName;
//
//    private Object method;
//    private Object params;
//    @SmartColumn(id = 5, name = "登入IP", width = 150, minWidth = 150)
//    private String ip;
//    @SmartColumn(id = 6, name = "操作时间")
//    private String createDate;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getOperationType() {
//        return operationType;
//    }
//
//    public void setOperationType(String operationType) {
//        this.operationType = operationType;
//    }
//
//    public String getOperationName() {
//        return operationName;
//    }
//
//    public void setOperationName(String operationName) {
//        this.operationName = operationName;
//    }
//
//    public Object getMethod() {
//        return method;
//    }
//
//    public void setMethod(Object method) {
//        this.method = method;
//    }
//
//    public Object getParams() {
//        return params;
//    }
//
//    public void setParams(Object params) {
//        this.params = params;
//    }
//
//    public String getIp() {
//        return ip;
//    }
//
//    public void setIp(String ip) {
//        this.ip = ip;
//    }
//
//    public String getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(String createDate) {
//        this.createDate = createDate;
//    }
}
