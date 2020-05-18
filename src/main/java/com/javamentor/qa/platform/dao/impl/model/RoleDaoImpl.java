package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.RoleDao;
import com.javamentor.qa.platform.models.entity.user.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends ReadWriteDAOImpl<Role, Long> implements RoleDao {
}
