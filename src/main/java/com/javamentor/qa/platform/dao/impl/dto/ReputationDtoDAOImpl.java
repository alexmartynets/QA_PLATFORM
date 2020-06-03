package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.ReputationDtoDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.models.dto.ReputationDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReputationDtoDAOImpl extends ReadWriteDAOImpl<ReputationDto, Long> implements ReputationDtoDAO {
    @Override
    public Long getCountUsers() {
        return null;
    }

    @Override
    public Long getCountUsersByName(String name) {
        return null;
    }

    @Override
    public List<ReputationDto> getListUsersToPagination(int page, int count) {
        return null;
    }

    @Override
    public List<ReputationDto> getListUsersByNameToSearch(String name, int page, int count) {
        return null;
    }
}
