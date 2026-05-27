package org.jiegiser.smartchargestation.demo;
/*
 * @Auther: changjie
 * @Date:2026/5/27
 * @Description:
 * @Modified By:
 */

import org.jiegiser.smartchargestation.demo.mqtt.FacoryMode.conf.MqttProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({MqttProps.class})
public class MqttApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqttApplication.class, args);
    }
}
