package com.ua.serveping.service.service;

import com.ua.serveping.service.models.CheckInterval;
import com.ua.serveping.service.models.CheckValue;
import com.ua.serveping.service.scheduler.AbstractWorker;
import com.ua.serveping.service.scheduler.HttpOrHttpsJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
@Slf4j
public class SchedulerService {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private HttpOrHttpsJob httpOrHttpsJob;

    private final Map<Object, ScheduledFuture> scheduledTask = new IdentityHashMap<>();

    @Async
    public void scheduleTask(Long id, CheckValue checkValue, CheckInterval checkInterval) {
        AbstractWorker abstractWorker = getAbstractWorker(checkValue);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Runnable work = () -> abstractWorker.execute(id);
        ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.scheduleAtFixedRate(work, checkInterval.getTimePeriod());
        scheduledTask.put(id, scheduledFuture);
    }


    private AbstractWorker getAbstractWorker(CheckValue checkValue) {
        switch (checkValue) {
            case HTTP_HTTPS:
                return this.httpOrHttpsJob;
            default:
                throw new RuntimeException(String.format("Invalid check value %s", checkValue));
        }
    }


    public boolean cancelTask(Long id) {
        log.info("Deleting the task {}", id);
        ScheduledFuture scheduledFuture = scheduledTask.get(id);
        if(scheduledFuture == null) {
            log.info("Id {} does not exist or may be deleted already", id);
            return false;
        }

        boolean cancel = scheduledFuture.cancel(true);
        log.info("Deletion status {}", cancel);
        scheduledTask.remove(id);
        return cancel;
    }

}
