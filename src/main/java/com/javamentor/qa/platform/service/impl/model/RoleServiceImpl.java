package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.RoleDAO;
import com.javamentor.qa.platform.models.entity.user.Role;
import com.javamentor.qa.platform.service.abstracts.model.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl extends ReadWriteServiceImpl<Role, Long> implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        super(roleDAO);
        this.roleDAO = roleDAO;
    }

    @Override
    public Optional<Role> getByRoleName(String roleName) {
        return roleDAO.getByRoleName(roleName);
    }
}
