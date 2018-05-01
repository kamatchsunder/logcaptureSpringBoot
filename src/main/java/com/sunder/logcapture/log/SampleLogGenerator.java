package com.sunder.logcapture.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class SampleLogGenerator {

    @Autowired
    private RabbitConfiguration rabbitConfiguration;


    @Scheduled(fixedDelay = 50000)
    public void logRandom(){

        Random r = new Random();

        LogModel model = new LogModel();
        model.setLog(r.nextInt());
        model.setName("name-"+r.nextInt());

        rabbitConfiguration.indexTemplate().convertAndSend(model);

    }

}
