package com.javamentor.qa.platform.dao.util;

import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.Optional;
import java.util.function.Supplier;

public class SingleResultUtil {

    public static <T> Optional<T> getSingleResultOrNull(Query<T> var) {

        T result;

        try {
            result = var.getSingleResult();
        } catch (NoResultException e) {
            return Optional.empty();
        }

        return Optional.of(result);
    }
}