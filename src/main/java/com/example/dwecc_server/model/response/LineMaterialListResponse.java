package com.example.dwecc_server.model.response;

import java.util.ArrayList;

public class LineMaterialListResponse {
    ArrayList<LineMaterialResponse> lineMaterialResponseArrayList;

    public ArrayList<LineMaterialResponse> getLineMaterialResponseArrayList() {
        return lineMaterialResponseArrayList;
    }

    public void setLineMaterialResponseArrayList(ArrayList<LineMaterialResponse> lineMaterialResponseArrayList) {
        this.lineMaterialResponseArrayList = lineMaterialResponseArrayList;
    }
}
