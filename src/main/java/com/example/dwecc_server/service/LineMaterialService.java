package com.example.dwecc_server.service;

import com.example.dwecc_server.model.entity.Linematerial;
import com.example.dwecc_server.repository.LinematerialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LineMaterialService {
    @Autowired
    LinematerialRepository linematerialRepository;

//    line의 모든 자재 리스트 출력
    public List<Linematerial> readAllLineMaterial(){
        List<Linematerial> linematerialArrayList = linematerialRepository.findAll();
        return linematerialArrayList;
    }
}
