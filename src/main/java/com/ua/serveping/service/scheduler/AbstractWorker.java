package com.ua.serveping.service.scheduler;

import com.ua.serveping.service.service.CheckReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractWorker {

    @Autowired
    protected CheckReportService checkReportService;

    public abstract void execute(Long id);

}
