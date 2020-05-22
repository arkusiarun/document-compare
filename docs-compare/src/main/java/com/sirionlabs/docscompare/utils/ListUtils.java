package com.sirionlabs.docscompare.utils;

import org.springframework.util.CollectionUtils;

import java.util.List;

public class ListUtils {

    private ListUtils() {
    }

    public static boolean isEmpty(final List<?> values) {
        return values == null || CollectionUtils.isEmpty(values);
    }
}
