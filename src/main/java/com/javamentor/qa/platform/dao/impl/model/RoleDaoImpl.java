package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.RoleDAO;
import com.javamentor.qa.platform.models.entity.user.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleDaoImpl extends ReadWriteDAOImpl<Role, Long> implements RoleDAO {
    @Override
    public Optional<Role> getByRoleName(String roleName) {
        return Optional.empty();
    }
}
