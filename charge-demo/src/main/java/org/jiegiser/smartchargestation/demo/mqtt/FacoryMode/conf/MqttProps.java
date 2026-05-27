package org.jiegiser.smartchargestation.demo.mqtt.FacoryMode.conf;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
/*
 * @Auther: changjie
 * @Date:2026/5/27
 * @Description:
 * @Modified By:
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mqtt")
@Data
public class MqttProps {


    private String host;
    private String clientIdFactoryMode;
    private String topic;
    private MqttConnectOptions options;
}
