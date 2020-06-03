package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ReputationDAO;
import com.javamentor.qa.platform.models.entity.Reputation;
import org.springframework.stereotype.Repository;

@Repository
public class ReputationDAOImpl extends ReadWriteDAOImpl<Reputation, Long> implements ReputationDAO {
}
