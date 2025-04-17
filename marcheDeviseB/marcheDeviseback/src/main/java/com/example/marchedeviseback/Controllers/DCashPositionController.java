package com.example.marchedeviseback.Controllers;


import com.example.marchedeviseback.Entities.DCashPosition;
import com.example.marchedeviseback.Services.DCashPositionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CashPosD")
@CrossOrigin(origins = "http://localhost:5173")
public class DCashPositionController {

        @Autowired
        private DCashPositionServiceImp DCashPositionService;

        @GetMapping("/retrieve-all-cashpositionDs")
        public List<DCashPosition> getAllDCashPositions() {
            return DCashPositionService.retrieveAllDCashPositions();
        }

        // Endpoint to get a specific devise by ID
        @GetMapping("/retrieve-cashpositionD/{id}")
        public DCashPosition getDCashPosition(@PathVariable Long id) {
            return DCashPositionService.retrieveDCashPosition(id);
        }



    }
