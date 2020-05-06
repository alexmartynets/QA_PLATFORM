package com.javamentor.qa.platform.service.impl;

import com.javamentor.qa.platform.dao.impl.model.TagDAOImpl;
import com.javamentor.qa.platform.models.entity.question.Tag;
import org.springframework.stereotype.Service;

@Service
public class TagService extends TagDAOImpl<Tag, Long> {
}
