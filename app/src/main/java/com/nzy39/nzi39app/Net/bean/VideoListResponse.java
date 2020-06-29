package com.nzy39.nzi39app.Net.bean;

import com.nzy39.nzi39app.Net.bean.VideoInfo;

import java.util.List;

public class VideoListResponse {

    private String result;
    private List<VideoInfo> list;
    public void setList(List<VideoInfo> list){this.list = list;}
    public List<VideoInfo> getList(){return list;}

    public void setResult(String result){ this.result = result;}
    public  String getResult(){return result;}
}
