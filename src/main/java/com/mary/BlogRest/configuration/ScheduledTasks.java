package com.mary.BlogRest.configuration;

import com.mary.BlogRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    @Autowired
    UserService userService;

    @Scheduled(fixedRate = 3000)
    public void scheduleTaskWithFixedRate() {
        userService.deactivatedUserScheduler();
    }
}