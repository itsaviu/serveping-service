package com.ua.serveping.service.scheduler;


import com.ua.serveping.service.models.domains.CheckReport;
import com.ua.serveping.service.models.domains.Checks;
import com.ua.serveping.service.repo.ChecksRepo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class HttpOrHttpsJob extends AbstractWorker {

    @Autowired
    private ChecksRepo checksRepo;

    @Autowired
    private OkHttpClient okHttpClient;

    @Override
    public void execute(Long id) {
        try {
            Checks checks = checksRepo.findById(id).orElseThrow(() -> new RuntimeException(String.format("Cannot find the scheduled id %s", id)));
            Request request = new Request.Builder().url(checks.getEndpoint()).build();
            Response response = okHttpClient.newCall(request).execute();
            Long responseTime = response.receivedResponseAtMillis() - response.sentRequestAtMillis();
            checkReportService.saveCheckReport(new CheckReport(checks, responseTime, (long) response.code(), response.isSuccessful(), Timestamp.valueOf(LocalDateTime.now())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
