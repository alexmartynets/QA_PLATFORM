package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.RelatedTagDAO;
import com.javamentor.qa.platform.models.entity.question.RelatedTag;
import com.javamentor.qa.platform.service.abstracts.model.RelatedTagService;
import com.javamentor.qa.platform.service.impl.model.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RelatedTagServiceImpl extends ReadWriteServiceImpl<RelatedTag, Long> implements RelatedTagService {

    private final RelatedTagDAO relatedTagDAO;

    public RelatedTagServiceImpl(RelatedTagDAO relatedTagDAO) {
        super(relatedTagDAO);
        this.relatedTagDAO = relatedTagDAO;
    }
}
