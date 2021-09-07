package com.example.dwecc_server.controller;

import com.example.dwecc_server.model.entity.Linematerial;
import com.example.dwecc_server.service.LineMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialManagementController {
    @Autowired
    LineMaterialService lineMaterialService;

    @RequestMapping("/readAll")
    public List<Linematerial> readAllLineMaterial(){
        return lineMaterialService.readAllLineMaterial();
    }
}
