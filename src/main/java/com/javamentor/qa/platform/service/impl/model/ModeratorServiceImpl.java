package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ModeratorDAO;
import com.javamentor.qa.platform.models.entity.Moderator;
import com.javamentor.qa.platform.service.abstracts.model.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModeratorServiceImpl extends ReadWriteServiceImpl<Moderator, Long> implements ModeratorService {

    private final ModeratorDAO moderatorDAO;

    @Autowired
    public ModeratorServiceImpl(ModeratorDAO moderatorDAO) {
        super(moderatorDAO);
        this.moderatorDAO = moderatorDAO;
    }

}
