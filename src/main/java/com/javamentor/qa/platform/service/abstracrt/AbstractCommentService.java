package com.javamentor.qa.platform.service.abstracrt;

import com.javamentor.qa.platform.dao.impl.model.AbstractDAOImpl;
import com.javamentor.qa.platform.models.entity.Comment;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractCommentService extends AbstractDAOImpl<Comment, Long> {
}
