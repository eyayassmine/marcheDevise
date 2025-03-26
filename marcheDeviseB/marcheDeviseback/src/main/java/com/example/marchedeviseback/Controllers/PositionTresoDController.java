package com.example.marchedeviseback.Controllers;


import com.example.marchedeviseback.Entities.DeviseH;
import com.example.marchedeviseback.Entities.PositionTresoD;
import com.example.marchedeviseback.Services.DeviseHServiceImp;
import com.example.marchedeviseback.Services.PositionTresoDServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/PTresoD")
@CrossOrigin(origins = "http://localhost:5173")
public class PositionTresoDController {

        @Autowired
        private PositionTresoDServiceImp ptresoDService;

        @GetMapping("/retrieve-all-ptresoDs")
        public List<PositionTresoD> getAllPositionTresoDs() {
            return ptresoDService.retrieveAllPositionTresoDs();
        }

        // Endpoint to get a specific devise by ID
        @GetMapping("/retrieve-ptresoD/{id}")
        public PositionTresoD getPositionTresoD(@PathVariable Long id) {
            return ptresoDService.retrievePositionTresoD(id);
        }



    }
