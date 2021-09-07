package com.example.dwecc_server.model.response;

import com.example.dwecc_server.model.entity.Product;

import java.util.ArrayList;

public class ProductListResponse {

    ArrayList<Product> productArrayList;

    public ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }

    public void setProductArrayList(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }
}
