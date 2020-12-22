package com.example.myapplication.Bean;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TestMessage<M> {
    private ArrayList<MsgInfo> list;

    public TestMessage(ArrayList<MsgInfo> list) {
        this.list = list;
    }
}
