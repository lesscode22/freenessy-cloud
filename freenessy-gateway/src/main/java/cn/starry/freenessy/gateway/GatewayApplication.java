package cn.starry.freenessy.gateway;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigChangeEvent;
import com.alibaba.nacos.client.config.listener.impl.AbstractConfigChangeListener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Component
    public class NacosListener extends AbstractConfigChangeListener implements InitializingBean {

        @Resource
        private NacosConfigManager nacosConfigManager;

        @Override
        public void receiveConfigChange(ConfigChangeEvent event) {
            System.out.println("====");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            nacosConfigManager.getConfigService().addListener("gateway-server.yaml", "DEFAULT_GROUP", this);
            nacosConfigManager.getConfigService().addListener("common-file.yaml", "DEFAULT_GROUP", this);
        }
    }
}
