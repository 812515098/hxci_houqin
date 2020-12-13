package com.example.myapplication.Bean;

import java.util.List;

public class TiaoWenBean {


    /**
     * total : 1
     * temperatures : [{"temId":1,"temAdress":"4","detailAdress":"1","temReason":"1","biginTime":null,"endTime":null,"status":0,"statusStr":"未调温","handlerName":null,"handlerTel":null,"subTime":"2020-07-26T01:55:05.000+0000","subTimeStr":"2020-07-26","handTeme":null,"userId":1}]
     */

    private int total;
    private List<TemperaturesBean> temperatures;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TemperaturesBean> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(List<TemperaturesBean> temperatures) {
        this.temperatures = temperatures;
    }

    public static class TemperaturesBean {
        /**
         * temId : 1
         * temAdress : 4
         * detailAdress : 1
         * temReason : 1
         * biginTime : null
         * endTime : null
         * status : 0
         * statusStr : 未调温
         * handlerName : null
         * handlerTel : null
         * subTime : 2020-07-26T01:55:05.000+0000
         * subTimeStr : 2020-07-26
         * handTeme : null
         * userId : 1
         */

        private int temId;
        private String temAdress;
        private String detailAdress;
        private String temReason;
        private Object biginTime;
        private Object endTime;
        private int status;
        private String statusStr;
        private Object handlerName;
        private Object handlerTel;
        private String subTime;
        private String subTimeStr;
        private Object handTeme;
        private int userId;

        public int getTemId() {
            return temId;
        }

        public void setTemId(int temId) {
            this.temId = temId;
        }

        public String getTemAdress() {
            return temAdress;
        }

        public void setTemAdress(String temAdress) {
            this.temAdress = temAdress;
        }

        public String getDetailAdress() {
            return detailAdress;
        }

        public void setDetailAdress(String detailAdress) {
            this.detailAdress = detailAdress;
        }

        public String getTemReason() {
            return temReason;
        }

        public void setTemReason(String temReason) {
            this.temReason = temReason;
        }

        public Object getBiginTime() {
            return biginTime;
        }

        public void setBiginTime(Object biginTime) {
            this.biginTime = biginTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatusStr() {
            return statusStr;
        }

        public void setStatusStr(String statusStr) {
            this.statusStr = statusStr;
        }

        public Object getHandlerName() {
            return handlerName;
        }

        public void setHandlerName(Object handlerName) {
            this.handlerName = handlerName;
        }

        public Object getHandlerTel() {
            return handlerTel;
        }

        public void setHandlerTel(Object handlerTel) {
            this.handlerTel = handlerTel;
        }

        public String getSubTime() {
            return subTime;
        }

        public void setSubTime(String subTime) {
            this.subTime = subTime;
        }

        public String getSubTimeStr() {
            return subTimeStr;
        }

        public void setSubTimeStr(String subTimeStr) {
            this.subTimeStr = subTimeStr;
        }

        public Object getHandTeme() {
            return handTeme;
        }

        public void setHandTeme(Object handTeme) {
            this.handTeme = handTeme;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
