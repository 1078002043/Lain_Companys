package computer_room.bean;

import java.util.List;

/**
 * 电量仪 历史数据
 */
public class ElectricityHistoryBean {


    /**
     * pageInfo : {"total":5,"list":[{"pehId":5,"pehAvol":13,"pehAcur":26,"pehBvol":27,"pehBcur":36,"pehCvol":37,"pehCcur":41,"pehTpap":32,"pehElequantity":54,"pehElecharge":2,"pehQuetime":"3","pehUserdTime":2,"pehTime":"2020-01-09 05:25:59","pemId":1,"pemName":"电量仪","diId":4},{"pehId":4,"pehAvol":16,"pehAcur":27,"pehBvol":26,"pehBcur":35,"pehCvol":35,"pehCcur":42,"pehTpap":23,"pehElequantity":56,"pehElecharge":2,"pehQuetime":"3","pehUserdTime":2,"pehTime":"2020-01-09 04:25:59","pemId":1,"pemName":"电量仪","diId":4},{"pehId":3,"pehAvol":14,"pehAcur":25,"pehBvol":25,"pehBcur":38,"pehCvol":38,"pehCcur":46,"pehTpap":12,"pehElequantity":54,"pehElecharge":2,"pehQuetime":"3","pehUserdTime":2,"pehTime":"2020-01-09 03:25:59","pemId":1,"pemName":"电量仪","diId":4},{"pehId":2,"pehAvol":15,"pehAcur":23,"pehBvol":24,"pehBcur":35,"pehCvol":35,"pehCcur":47,"pehTpap":34,"pehElequantity":56,"pehElecharge":2,"pehQuetime":"3","pehUserdTime":2,"pehTime":"2020-01-09 02:25:59","pemId":1,"pemName":"电量仪","diId":4},{"pehId":1,"pehAvol":10,"pehAcur":20,"pehBvol":24,"pehBcur":35,"pehCvol":34,"pehCcur":45,"pehTpap":23,"pehElequantity":45,"pehElecharge":2,"pehQuetime":"3","pehUserdTime":2,"pehTime":"2020-01-09 01:25:59","pemId":1,"pemName":"电量仪","diId":4}],"pageNum":1,"pageSize":10,"size":5,"startRow":1,"endRow":5,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"lastPage":1,"firstPage":1}
     * pehElequantity : 265.0
     */

    private PageInfoBean pageInfo;
    private double pehElequantity;

    public PageInfoBean getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfoBean pageInfo) {
        this.pageInfo = pageInfo;
    }

    public double getPehElequantity() {
        return pehElequantity;
    }

    public void setPehElequantity(double pehElequantity) {
        this.pehElequantity = pehElequantity;
    }

    public static class PageInfoBean {
        /**
         * total : 5
         * list : [{"pehId":5,"pehAvol":13,"pehAcur":26,"pehBvol":27,"pehBcur":36,"pehCvol":37,"pehCcur":41,"pehTpap":32,"pehElequantity":54,"pehElecharge":2,"pehQuetime":"3","pehUserdTime":2,"pehTime":"2020-01-09 05:25:59","pemId":1,"pemName":"电量仪","diId":4},{"pehId":4,"pehAvol":16,"pehAcur":27,"pehBvol":26,"pehBcur":35,"pehCvol":35,"pehCcur":42,"pehTpap":23,"pehElequantity":56,"pehElecharge":2,"pehQuetime":"3","pehUserdTime":2,"pehTime":"2020-01-09 04:25:59","pemId":1,"pemName":"电量仪","diId":4},{"pehId":3,"pehAvol":14,"pehAcur":25,"pehBvol":25,"pehBcur":38,"pehCvol":38,"pehCcur":46,"pehTpap":12,"pehElequantity":54,"pehElecharge":2,"pehQuetime":"3","pehUserdTime":2,"pehTime":"2020-01-09 03:25:59","pemId":1,"pemName":"电量仪","diId":4},{"pehId":2,"pehAvol":15,"pehAcur":23,"pehBvol":24,"pehBcur":35,"pehCvol":35,"pehCcur":47,"pehTpap":34,"pehElequantity":56,"pehElecharge":2,"pehQuetime":"3","pehUserdTime":2,"pehTime":"2020-01-09 02:25:59","pemId":1,"pemName":"电量仪","diId":4},{"pehId":1,"pehAvol":10,"pehAcur":20,"pehBvol":24,"pehBcur":35,"pehCvol":34,"pehCcur":45,"pehTpap":23,"pehElequantity":45,"pehElecharge":2,"pehQuetime":"3","pehUserdTime":2,"pehTime":"2020-01-09 01:25:59","pemId":1,"pemName":"电量仪","diId":4}]
         * pageNum : 1
         * pageSize : 10
         * size : 5
         * startRow : 1
         * endRow : 5
         * pages : 1
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * lastPage : 1
         * firstPage : 1
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
        private int lastPage;
        private int firstPage;
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

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
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
             * pehId : 5
             * pehAvol : 13.0
             * pehAcur : 26.0
             * pehBvol : 27.0
             * pehBcur : 36.0
             * pehCvol : 37.0
             * pehCcur : 41.0
             * pehTpap : 32.0
             * pehElequantity : 54.0
             * pehElecharge : 2.0
             * pehQuetime : 3
             * pehUserdTime : 2.0
             * pehTime : 2020-01-09 05:25:59
             * pemId : 1
             * pemName : 电量仪
             * diId : 4
             */

            private int pehId;
            private double pehAvol;
            private double pehAcur;
            private double pehBvol;
            private double pehBcur;
            private double pehCvol;
            private double pehCcur;
            private double pehTpap;
            private double pehElequantity;
            private double pehElecharge;
            private String pehQuetime;
            private double pehUserdTime;
            private String pehTime;
            private int pemId;
            private String pemName;
            private int diId;

            public int getPehId() {
                return pehId;
            }

            public void setPehId(int pehId) {
                this.pehId = pehId;
            }

            public double getPehAvol() {
                return pehAvol;
            }

            public void setPehAvol(double pehAvol) {
                this.pehAvol = pehAvol;
            }

            public double getPehAcur() {
                return pehAcur;
            }

            public void setPehAcur(double pehAcur) {
                this.pehAcur = pehAcur;
            }

            public double getPehBvol() {
                return pehBvol;
            }

            public void setPehBvol(double pehBvol) {
                this.pehBvol = pehBvol;
            }

            public double getPehBcur() {
                return pehBcur;
            }

            public void setPehBcur(double pehBcur) {
                this.pehBcur = pehBcur;
            }

            public double getPehCvol() {
                return pehCvol;
            }

            public void setPehCvol(double pehCvol) {
                this.pehCvol = pehCvol;
            }

            public double getPehCcur() {
                return pehCcur;
            }

            public void setPehCcur(double pehCcur) {
                this.pehCcur = pehCcur;
            }

            public double getPehTpap() {
                return pehTpap;
            }

            public void setPehTpap(double pehTpap) {
                this.pehTpap = pehTpap;
            }

            public double getPehElequantity() {
                return pehElequantity;
            }

            public void setPehElequantity(double pehElequantity) {
                this.pehElequantity = pehElequantity;
            }

            public double getPehElecharge() {
                return pehElecharge;
            }

            public void setPehElecharge(double pehElecharge) {
                this.pehElecharge = pehElecharge;
            }

            public String getPehQuetime() {
                return pehQuetime;
            }

            public void setPehQuetime(String pehQuetime) {
                this.pehQuetime = pehQuetime;
            }

            public double getPehUserdTime() {
                return pehUserdTime;
            }

            public void setPehUserdTime(double pehUserdTime) {
                this.pehUserdTime = pehUserdTime;
            }

            public String getPehTime() {
                return pehTime;
            }

            public void setPehTime(String pehTime) {
                this.pehTime = pehTime;
            }

            public int getPemId() {
                return pemId;
            }

            public void setPemId(int pemId) {
                this.pemId = pemId;
            }

            public String getPemName() {
                return pemName;
            }

            public void setPemName(String pemName) {
                this.pemName = pemName;
            }

            public int getDiId() {
                return diId;
            }

            public void setDiId(int diId) {
                this.diId = diId;
            }
        }
    }
}
