package com.homework.loan.service.request;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class DefaultRequestService implements RequestService {
    private final ConcurrentHashMap<String, EncounteredRequest> cache;

    @Value("${apiFilter.sameCountry.requestPeriodInSeconds}")
    private int requestPeriodInSeconds;

    @Value("${apiFilter.sameCountry.validRequestCount}")
    private int validRequestCount;

    public DefaultRequestService() {
        cache = new ConcurrentHashMap<>();
    }

    @Override
    public boolean requestCountUnderLimit(String country) {
        EncounteredRequest request = cache.get(country);

        if (request == null) {
            request = new EncounteredRequest(System.currentTimeMillis(), 1);
        } else {
            if ((System.currentTimeMillis() - request.getCurrentTimeAtStart()) / 1000 > requestPeriodInSeconds) {
                request = new EncounteredRequest(System.currentTimeMillis(), 1);
            } else {
                request = new EncounteredRequest(request.getCurrentTimeAtStart(), request.getRequestCount() + 1);
            }
        }

        cache.put(country, request);

        return validRequestCount >= request.getRequestCount();
    }

}
