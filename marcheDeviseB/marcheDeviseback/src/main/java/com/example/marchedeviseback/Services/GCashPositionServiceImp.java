package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.GCashPosition;
import com.example.marchedeviseback.Repositories.GCashPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GCashPositionServiceImp implements IGCashPositionService {

    @Autowired
    GCashPositionRepository gCashPositionr;
    @Override
    public List<GCashPosition> retrieveAllGCashPositions() {
        return  (List<GCashPosition>) gCashPositionr.findAll();
    }

    @Override
    public GCashPosition retrieveGCashPosition(Long id) {
        return gCashPositionr.findById(id).orElse(null);
    }

}
