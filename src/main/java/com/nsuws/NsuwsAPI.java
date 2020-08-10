package com.nsuws;

import com.nsuws.resources.StatisticsAPI;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class NsuwsAPI extends Application<NsuwsAPIConfig> {
    public static void main(String[] args) throws Exception {
        new NsuwsAPI().run(args);
    }

    @Override
    protected void bootstrapLogging() {
    }

    @Override
    public String getName() {
        return "crypto-api";
    }

    @Override
    public void initialize(Bootstrap<NsuwsAPIConfig> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }

    @Override
    public void run(NsuwsAPIConfig configuration,
                    Environment environment) {
        environment.jersey().register(new StatisticsAPI(configuration.getEncryptionKey()));
    }


}
