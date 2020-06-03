package com.javamentor.qa.platform.dao.util;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class SingleResultUtil {

    public static <T> Optional<T> getSingleResultOrNull(TypedQuery<T> var) {
        T result;
        try {
            result = var.getSingleResult();
        } catch (NoResultException e) {
            return Optional.empty();
        }
        return Optional.of(result);
    }
}