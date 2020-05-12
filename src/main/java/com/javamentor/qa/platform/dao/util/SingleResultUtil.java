package com.javamentor.qa.platform.dao.util;

import java.util.Optional;
import java.util.function.Supplier;

public class SingleResultUtil {

    @SuppressWarnings("unchecked")
    public static <T, K> Optional<T> getSingleResultOrNull(Supplier<K> var) {

        T result = null;

        try {
            result = (T) var.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(result);
    }
}