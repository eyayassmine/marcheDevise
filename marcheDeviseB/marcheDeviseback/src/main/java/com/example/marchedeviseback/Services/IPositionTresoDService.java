package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.PositionTresoD;

import java.util.List;

public interface IPositionTresoDService {

    List<PositionTresoD> retrieveAllPositionTresoDs();

    PositionTresoD retrievePositionTresoD(Long id);


}
