package com.exercise.prabhat.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.exercise.prabhat.api.CrawlerApi;

@Component
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig()
    {
        register(CrawlerApi.class);
    }
}
