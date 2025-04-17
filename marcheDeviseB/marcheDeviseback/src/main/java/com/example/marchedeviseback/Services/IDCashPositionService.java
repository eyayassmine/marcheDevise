package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.DCashPosition;

import java.util.List;

public interface IDCashPositionService {

    List<DCashPosition> retrieveAllDCashPositions();

    DCashPosition retrieveDCashPosition(Long id);


}
