package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.PositionTresoD;
import com.example.marchedeviseback.Repositories.PositionTresoDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionTresoDServiceImp implements IPositionTresoDService{

    @Autowired
    PositionTresoDRepository ptresoDr;
    @Override
    public List<PositionTresoD> retrieveAllPositionTresoDs() {
        return  (List<PositionTresoD>) ptresoDr.findAll();
    }

    @Override
    public PositionTresoD retrievePositionTresoD(Long id) {
        return ptresoDr.findById(id).orElse(null);
    }

}
