package io.github.haifeng303.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.Properties;

/**
 * @PACKAGE_NAME: io.github.haifeng303.config
 * @author: rhf
 * @ProjectName: feng-cloud
 * @description:
 * @DATE: 2021/5/14
 **/
@Configuration
public class BeanConfiguration {

    /**
     * @description 生成验证码工具类
     * @date 2021/5/15
     * @return com.google.code.kaptcha.impl.DefaultKaptcha
     */
    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.font.size", "30");
        properties.put("kaptcha.textproducer.char.space", "2");
        properties.put("kaptcha.image.width", "100");
        properties.put("kaptcha.image.height", "42");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    /**
     * @description websocket配置
     * @date 2021/5/15
     * @return org.springframework.web.socket.server.standard.ServerEndpointExporter
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
