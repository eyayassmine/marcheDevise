package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.DCashPosition;
import com.example.marchedeviseback.Repositories.DCashPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DCashPositionServiceImp implements IDCashPositionService {

    @Autowired
    DCashPositionRepository dCashPositionr;
    @Override
    public List<DCashPosition> retrieveAllDCashPositions() {
        return  (List<DCashPosition>) dCashPositionr.findAll();
    }

    @Override
    public DCashPosition retrieveDCashPosition(Long id) {
        return dCashPositionr.findById(id).orElse(null);
    }

}
