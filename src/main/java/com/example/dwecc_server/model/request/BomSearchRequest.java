package com.example.dwecc_server.model.request;

import lombok.Data;

@Data
public class BomSearchRequest {
    String product;
    String model;
    String spec;
}
