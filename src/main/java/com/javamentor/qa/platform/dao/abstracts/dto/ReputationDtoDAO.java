package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.ReputationDto;

import java.util.List;

public interface ReputationDtoDAO {

    Long getCountUsers();

    Long getCountUsersByName(String name);

    List<ReputationDto> getListUsersToPagination(int page, int count);

    List<ReputationDto> getListUsersByNameToSearch(String name, int page, int count);
}
