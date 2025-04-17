package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.RateH;

import java.time.LocalDateTime;
import java.util.List;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface IRateHService {


    List<RateH> getNewOrUpdatedCurrencies(LocalDateTime lastTimestamp);

//    void addEmitter(SseEmitter emitter);
    void simulateRates();
    //void notifyClients(DeviseH deviseH);
    List<RateH> retrieveAllRateHs();

    RateH retrieveRateH(Long id);
}
