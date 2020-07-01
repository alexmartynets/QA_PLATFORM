package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.BadgesDAO;
import com.javamentor.qa.platform.models.entity.Badges;
import org.springframework.stereotype.Repository;

@Repository
public class BadgesDAOImpl extends ReadWriteDAOImpl<Badges, Long> implements BadgesDAO {
}
