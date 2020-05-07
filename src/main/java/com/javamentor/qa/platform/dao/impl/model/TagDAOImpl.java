package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.TagDAO;
import com.javamentor.qa.platform.models.entity.question.Tag;
import org.springframework.stereotype.Repository;

@Repository
public class TagDAOImpl extends ReadWriteDAOImpl<Tag, Long> implements TagDAO {
}
