package com.github.wkicior.helyeah.application;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.glassfish.jersey.client.ClientProperties;

@Startup
@Singleton
public class Connector {

    private Client client;

    @PostConstruct
    public void init() {
        this.client = ClientBuilder.newClient();
        this.client.property(ClientProperties.CONNECT_TIMEOUT, 1000);
        this.client.property(ClientProperties.READ_TIMEOUT, 1000);
        //without runtime dependency:
        this.client.property("jersey.config.client.connectTimeout", 1000);
        this.client.property("jersey.config.client.readTimeout", 1000);
    }

    @Produces
    @TargetURI
    public WebTarget expose(InjectionPoint ip) {
        Annotated annotated = ip.getAnnotated();
        TargetURI annotation = annotated.getAnnotation(TargetURI.class);
        return client.target(annotation.value());

    }

}
