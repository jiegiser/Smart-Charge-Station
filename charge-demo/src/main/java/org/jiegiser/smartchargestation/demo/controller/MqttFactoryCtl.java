package org.jiegiser.smartchargestation.demo.controller;
/*
 * @Auther: changjie
 * @Date:2026/5/27
 * @Description:
 * @Modified By:
 */

import jakarta.annotation.Resource;
import org.jiegiser.smartchargestation.demo.mqtt.FacoryMode.service.MqttService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttFactoryCtl {

    @Resource
    private MqttService service;


    // mqtt 客户端发送消息

    @RequestMapping(value = "/factory/pub")
    public void pahoPub(
            @RequestParam("topic") String topic,
            @RequestParam("message") String message) {

        service.send(topic,message);

    }

}
