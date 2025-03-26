package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.PositionTresoG;

import java.util.List;

public interface IPositionTresoGService {

    List<PositionTresoG> retrieveAllPositionTresoGs();

    PositionTresoG retrievePositionTresoG(Long id);
}
