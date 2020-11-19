package com.example.myapplication.Bean;

public class LoginBean<T>
{


    /**
     * result : ok
     * user : {"userId":1,"userName":"张三","userTel":"110","identity":2,"workerKind":8}
     */

    private String result;
    private UserBean user;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * userId : 1
         * userName : 张三
         * userTel : 110
         * identity : 2
         * workerKind : 8
         */

        private int userId;
        private String userName;
        private String userTel;
        private int identity;
        private int workerKind;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserTel() {
            return userTel;
        }

        public void setUserTel(String userTel) {
            this.userTel = userTel;
        }

        public int getIdentity() {
            return identity;
        }

        public void setIdentity(int identity) {
            this.identity = identity;
        }

        public int getWorkerKind() {
            return workerKind;
        }

        public void setWorkerKind(int workerKind) {
            this.workerKind = workerKind;
        }
    }
}









