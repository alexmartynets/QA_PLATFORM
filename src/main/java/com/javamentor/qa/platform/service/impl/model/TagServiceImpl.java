package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.RelatedTagDAO;
import com.javamentor.qa.platform.dao.abstracts.model.TagDAO;
import com.javamentor.qa.platform.models.entity.question.RelatedTag;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.service.abstracts.model.TagService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
public class TagServiceImpl extends ReadWriteServiceImpl<Tag, Long> implements TagService {

    private final TagDAO tagDAO;
    private final RelatedTagDAO relatedTagDAO;

    public TagServiceImpl(TagDAO tagDAO, RelatedTagDAO relatedTagDAO) {
        super(tagDAO);
        this.tagDAO = tagDAO;
        this.relatedTagDAO = relatedTagDAO;
    }


    @Override
    public void persist(Tag tag) {
        if (tagDAO.getTagByName(tag.getName()) == null)
            super.persist(tag);
    }

    @Override
    public void persistChildTag(Tag tag, Long mainTagId) {
        if (tagDAO.getTagByName(tag.getName()) == null) {
            super.persist(tag);
            Tag tag1 = tagDAO.getByKey(mainTagId);
            Tag tag2 = tagDAO.getTagByName(tag.getName());
            if (tag1 != null) {
                RelatedTag relatedTag = RelatedTag.builder()
                        .mainTag(tag1)
                        .childTag(tag2)
                        .build();
                relatedTagDAO.persist(relatedTag);
            }

        }
    }

}
