package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.EditorDto;
import com.javamentor.qa.platform.models.dto.ReputationDto;
import com.javamentor.qa.platform.models.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserDtoDAO {

    List<UserDto> getUserDtoList();

    Optional<UserDto> getUserDtoById(Long id);

    Long getCountUserByCreationDate(long weeks);

    Long getCountUsersByQuantityEditedText(long weeks);

    List<ReputationDto> getListUserByCreationDate(int page, int count, long weeks);

    List<EditorDto> getListUsersByQuantityEditedText(int page, int count, long weeks);

    Long getCountUsers(long weeks);

    Long getCountUsersByName(String name, long weeks);

    List<ReputationDto> getListUsersByReputation(int page, int count, long weeks);

    List<ReputationDto> getListUsersByVoice(int page, int count, long weeks);

    List<ReputationDto> getListUsersByNameToSearch(String name, int page, int count, long weeks);

    List<UserDto> getListUserByRole(String role);
}
