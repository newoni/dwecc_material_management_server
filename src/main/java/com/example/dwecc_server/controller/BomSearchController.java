package com.example.dwecc_server.controller;

import com.example.dwecc_server.model.entity.Product;
import com.example.dwecc_server.model.request.BomSearchRequest;
import com.example.dwecc_server.model.response.ProductListResponse;
import com.example.dwecc_server.service.BomSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bomSearch")
public class BomSearchController {
    @Autowired
    BomSearchService bomSearchService;

    @RequestMapping("/product")
    public ProductListResponse getProductList(@RequestBody BomSearchRequest request){
        return bomSearchService.findProductByName(request);
    }

}
