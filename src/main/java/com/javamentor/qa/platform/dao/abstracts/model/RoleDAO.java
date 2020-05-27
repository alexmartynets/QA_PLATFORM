package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.user.Role;

import java.util.Optional;

public interface RoleDAO extends ReadWriteDAO<Role, Long> {
    Optional<Role> getByRoleName(String roleName);
}
