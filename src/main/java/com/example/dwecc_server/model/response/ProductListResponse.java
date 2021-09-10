package com.example.dwecc_server.model.response;

import java.util.ArrayList;

public class ProductListResponse {

    ArrayList<ProductResponse> productArrayList;

    public ArrayList<ProductResponse> getProductArrayList() {
        return productArrayList;
    }

    public void setProductArrayList(ArrayList<ProductResponse> productArrayList) {
        this.productArrayList = productArrayList;
    }
}
