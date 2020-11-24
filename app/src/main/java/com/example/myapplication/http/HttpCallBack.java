package com.example.myapplication.http;

public interface HttpCallBack {
    void onSuccess();

    void onFaild(HttpError httpError);
}
