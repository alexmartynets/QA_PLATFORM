package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.ReputationDto;

import java.util.List;

public interface ReputationDtoDAO {

    Long getCountUsers(long weeks);

    Long getCountUsersByName(String name, long weeks);

    List<ReputationDto> getListUsersByReputationToPagination(int page, int count, long weeks);

    List<ReputationDto> getListUsersByVoiceToPagination(int page, int count, long weeks);

    List<ReputationDto> getListUsersByNameToSearch(String name, int page, int count, long weeks);
}
