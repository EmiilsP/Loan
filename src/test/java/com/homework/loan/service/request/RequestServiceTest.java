package com.homework.loan.service.request;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext
public class RequestServiceTest {

    @Autowired RequestService requestService;

    @Test
    public void isValid() {
        await();
        assertTrue(requestService.requestCountUnderLimit("LV"));
    }

    /**
     * Should improve this test, as currently relaying un performance.
     * Also changes is in application.properties will affect this test.
     * Potentially could fail on slow performance.
     */

    @Test
    public void isNotValid() {
        requestService.requestCountUnderLimit("LV");
        requestService.requestCountUnderLimit("LV");
        requestService.requestCountUnderLimit("LV");
        assertFalse(requestService.requestCountUnderLimit("LV"));
    }

    private void await() {
        final Lock lock = new ReentrantLock();
        final Condition goAhead = lock.newCondition();
        lock.lock();
        try {
            goAhead.await(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }
}
