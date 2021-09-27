package com.example.dwecc_server.model.response;

import lombok.Data;

@Data
public class LineMaterialResponse {
    private String name;
    private String code;
    private String lot;
    private Long seq;
    private Long qty;

}
