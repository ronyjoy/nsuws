package com.nsuws.core;

import com.nsuws.core.dto.Statistics;

/**
 * Inteface to encapsulate Statistics object store
 */
public interface StatisticsStore {
    void add (Statistics data);
    Statistics get();
}
