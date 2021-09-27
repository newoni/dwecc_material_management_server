package com.example.dwecc_server.model.response;

import com.example.dwecc_server.model.entity.Material;
import lombok.Data;

import java.util.ArrayList;

@Data
public class MaterialListResponse {
    ArrayList<Material> materialArrayList;
}
