package com.example.myapplication.Bean;

import lombok.Data;

@Data
public class MsgInfo {


    /**
     * msgId : 2
     * title : a
     * content : 1123123
     * type : 2
     * typeStr : 公告
     * releaseTime : 2020-07-27T15:36:22.000+0000
     * releaseTimeStr : 2020-07-27
     * releaseUser : v
     * releaseTel : s
     * isDel : 0
     */

    private int msgId;
    private String title;
    private String content;
    private int type;
    private String typeStr;
    private String releaseTime;
    private String releaseTimeStr;
    private String releaseUser;
    private String releaseTel;
    private int isDel;

}
