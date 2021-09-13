package com.example.dwecc_server.model.response;

import com.example.dwecc_server.model.entity.Material;

import java.util.ArrayList;

public class MaterialListResponse {
    ArrayList<Material> materialArrayList;

    public ArrayList<Material> getMaterialArrayList() {
        return materialArrayList;
    }

    public void setMaterialArrayList(ArrayList<Material> materialArrayList) {
        this.materialArrayList = materialArrayList;
    }
}
