package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.PositionTresoG;
import com.example.marchedeviseback.Repositories.PositionTresoGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PositionTresoGServiceImp implements IPositionTresoGService{

    @Autowired
    PositionTresoGRepository ptresoGr;
    @Override
    public List<PositionTresoG> retrieveAllPositionTresoGs() {
        return  (List<PositionTresoG>) ptresoGr.findAll();
    }

    @Override
    public PositionTresoG retrievePositionTresoG(Long id) {
        return ptresoGr.findById(id).orElse(null);
    }

}
