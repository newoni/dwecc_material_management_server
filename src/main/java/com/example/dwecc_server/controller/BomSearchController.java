package com.example.dwecc_server.controller;

import com.example.dwecc_server.model.request.BomSearchRequest;
import com.example.dwecc_server.model.response.ProductListResponse;
import com.example.dwecc_server.service.BomSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/bomSearch")
@Slf4j
public class BomSearchController {
    @Autowired
    BomSearchService bomSearchService;

    @RequestMapping("/product")
    public ProductListResponse getProductList(@RequestBody BomSearchRequest request){

        System.out.println("getProductList is invoked");
        System.out.println("request value: " + request);
        System.out.println("request product value: " + request.getProduct());
        System.out.println("request model value: " + request.getModel());
        System.out.println("request spec value: " + request.getSpec());

        return bomSearchService.findProductByName(request);
    }

}
