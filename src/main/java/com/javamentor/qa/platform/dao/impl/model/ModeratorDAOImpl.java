package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ModeratorDAO;
import com.javamentor.qa.platform.models.entity.user.Moderator;
import org.springframework.stereotype.Repository;

@Repository
public class ModeratorDAOImpl extends ReadWriteDAOImpl<Moderator, Long> implements ModeratorDAO {
}
