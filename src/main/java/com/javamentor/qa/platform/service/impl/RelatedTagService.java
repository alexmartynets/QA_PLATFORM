package com.javamentor.qa.platform.service.impl;

import com.javamentor.qa.platform.dao.impl.model.RelatedTagDAOImpl;
import com.javamentor.qa.platform.models.entity.question.RelatedTag;
import org.springframework.stereotype.Service;

@Service
public class RelatedTagService extends RelatedTagDAOImpl<RelatedTag, Long> {
}
