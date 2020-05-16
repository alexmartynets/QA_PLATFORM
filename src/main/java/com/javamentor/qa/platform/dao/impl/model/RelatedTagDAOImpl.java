package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.RelatedTagDAO;
import com.javamentor.qa.platform.models.entity.question.RelatedTag;
import org.springframework.stereotype.Repository;

@Repository
public class RelatedTagDAOImpl extends ReadWriteDAOImpl<RelatedTag, Long> implements RelatedTagDAO {
}
