package com.homework.loan.service.request;

import java.util.Objects;

public final class EncounteredRequest {

    private final long currentTimeAtStart;

    private final int requestCount;

    protected EncounteredRequest(final long start, final int count) {
        this.currentTimeAtStart = start;
        this.requestCount = count;
    }

    public long getCurrentTimeAtStart() {
        return currentTimeAtStart;
    }

    public int getRequestCount() {
        return requestCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EncounteredRequest that = (EncounteredRequest) o;
        return currentTimeAtStart == that.currentTimeAtStart &&
                requestCount == that.requestCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentTimeAtStart, requestCount);
    }

}
