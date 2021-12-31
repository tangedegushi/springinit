package com.tangedegushi.model.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//通过此配置http访问自动转https访问，如果不需要http访问，可不用此配置
@Configuration
public class HttpsConfig {

    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(Connector connector) {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory(){
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint=new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection=new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
                System.out.println("--------------------------DDDDDDDDDDDDDDDDDDDD");
            }
        };
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }

    @Bean
    public Connector connector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //http访问端口,访问80端口号可不携带端口号
        connector.setPort(80);
        connector.setSecure(false);
        //https访问端口，关于访问端口，日志中有输出
        //Tomcat started on port(s): 8069 (http) 80 (http) with context path ''
        connector.setRedirectPort(8069);
        return connector;
    }
}
