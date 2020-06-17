package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.ReputationDtoDAO;
import com.javamentor.qa.platform.service.abstracts.dto.ReputationDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReputationDtoServiceImpl implements ReputationDtoService {

    private final ReputationDtoDAO reputationDtoDAO;

    @Autowired
    public ReputationDtoServiceImpl(ReputationDtoDAO reputationDtoDAO) {
        this.reputationDtoDAO = reputationDtoDAO;
    }

}
