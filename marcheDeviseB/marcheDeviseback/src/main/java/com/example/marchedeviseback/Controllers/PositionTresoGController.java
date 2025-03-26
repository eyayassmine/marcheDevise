package com.example.marchedeviseback.Controllers;


import com.example.marchedeviseback.Entities.PositionTresoG;
import com.example.marchedeviseback.Services.PositionTresoGServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/PTresoG")
@CrossOrigin(origins = "http://localhost:5173")
public class PositionTresoGController {

    @Autowired
    private PositionTresoGServiceImp ptresoGService;

    @GetMapping("/retrieve-all-ptresoGs")
    public List<PositionTresoG> getAllPositionTresoGs() {
        return ptresoGService.retrieveAllPositionTresoGs();
    }

    // Endpoint to get a specific devise by ID
    @GetMapping("/retrieve-ptresoG/{id}")
    public PositionTresoG getPositionTresoD(@PathVariable Long id) {
        return ptresoGService.retrievePositionTresoG(id);
    }

}
