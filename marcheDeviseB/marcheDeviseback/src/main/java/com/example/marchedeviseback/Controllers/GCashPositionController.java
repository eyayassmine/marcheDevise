package com.example.marchedeviseback.Controllers;


import com.example.marchedeviseback.Entities.GCashPosition;
import com.example.marchedeviseback.Services.GCashPositionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CashPosG")
@CrossOrigin(origins = "http://localhost:5173")
public class GCashPositionController {

    @Autowired
    private GCashPositionServiceImp GCashPositionService;

    @GetMapping("/retrieve-all-cashpositionGs")
    public List<GCashPosition> getAllGCashPositions() {
        return GCashPositionService.retrieveAllGCashPositions();
    }

    // Endpoint to get a specific devise by ID
    @GetMapping("/retrieve-cashpositionG/{id}")
    public GCashPosition getDCashPosition(@PathVariable Long id) {
        return GCashPositionService.retrieveGCashPosition(id);
    }

}
