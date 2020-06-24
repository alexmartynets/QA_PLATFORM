package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.TagDAO;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.service.abstracts.model.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagServiceImpl extends ReadWriteServiceImpl<Tag, Long> implements TagService {

    private final TagDAO tagDAO;

    @Autowired
    public TagServiceImpl(TagDAO tagDAO) {
        super(tagDAO);
        this.tagDAO = tagDAO;
    }

    public Long checkOrPersists(String tagName) {
        Optional<Long> optionalLong = tagDAO.getTagByName(tagName);
        if (!optionalLong.isPresent()) {
            Tag tag = Tag.builder()
                    .name(tagName)
                    .build();
            super.persist(tag);
            return tag.getId();
        } else {
            return optionalLong.get();
        }
    }
}
