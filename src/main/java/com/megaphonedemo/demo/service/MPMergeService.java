package com.megaphonedemo.demo.service;

import java.io.InputStream;
import java.util.Collection;
import java.util.Vector;

public interface MPMergeService {

    /**
     *
     * @param urls
     * @return
     */
    Vector<InputStream> downloadDataToMerge(Collection<String> urls);
}
