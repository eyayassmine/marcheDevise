package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.DeviseH;

import java.time.LocalDateTime;
import java.util.List;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface IDeviseHService {


    List<DeviseH> getNewOrUpdatedDevises(LocalDateTime lastTimestamp);

//    void addEmitter(SseEmitter emitter);
    void simulateRates();
    //void notifyClients(DeviseH deviseH);
    List<DeviseH> retrieveAllDeviseHs();

    DeviseH retrieveDeviseH(Long id);
}
