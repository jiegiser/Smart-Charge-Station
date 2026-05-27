package org.jiegiser.smartchargestation.demo.mqtt.FacoryMode.service;

import org.jiegiser.smartchargestation.demo.mqtt.FacoryMode.model.MqttConstants;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

@IntegrationComponentScan
@MessagingGateway(defaultRequestChannel = MqttConstants.OUT_CHANNEL)
public interface MqttService {
    /**
     * 注意：
     * topic 这个参数，
     * 必须使用 @Header(MqttHeaders.TOPIC)修饰
     * @param topic
     * @param message
     */

    // 发送消息
    void send(@Header(MqttHeaders.TOPIC)String topic, String message);
}
