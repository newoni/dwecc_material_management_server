package com.example.dwecc_server.model.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ProductListResponse {

    ArrayList<ProductResponse> productArrayList;
}
