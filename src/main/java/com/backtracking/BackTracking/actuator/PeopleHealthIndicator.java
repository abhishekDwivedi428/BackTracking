package com.backtracking.BackTracking.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class PeopleHealthIndicator implements HealthIndicator{

    private static final String SERVICE_NAME = "PeopleService";

    @Override
    public Health health() {
        if (!isRunningServicePeopleService()) {
            return Health.down().withDetail(SERVICE_NAME, "Not Available").build();
        }
        return Health.up().withDetail(SERVICE_NAME, "Available").build();
    }

    private Boolean isRunningServicePeopleService() {
        Boolean isRunning = false;
        // Add real logic here to test if People Service is running; skipped for demo purposes
        return isRunning;
    }
}
