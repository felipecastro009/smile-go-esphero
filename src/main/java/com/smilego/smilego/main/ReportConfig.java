package com.smilego.smilego.main;

import com.smilego.smilego.application.cache.CacheAdapter;
import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.application.usecases.reports.GetReportUseCase;
import com.smilego.smilego.domain.Report;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportConfig {
    @Bean
    public GetReportUseCase getReportUseCase(
            SubscriptionRepository subscriptionRepository,
            CacheAdapter<Report> cacheAdapter
    ) {
        return new GetReportUseCase(subscriptionRepository, cacheAdapter);
    }
}
