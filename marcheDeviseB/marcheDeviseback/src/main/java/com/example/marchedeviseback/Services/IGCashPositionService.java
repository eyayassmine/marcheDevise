package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.GCashPosition;

import java.util.List;

public interface IGCashPositionService {

    List<GCashPosition> retrieveAllGCashPositions();

    GCashPosition retrieveGCashPosition(Long id);
}
