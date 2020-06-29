package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.UserBadgesDto;
import com.javamentor.qa.platform.models.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserDtoDAO {

    List<UserDto> getUserDtoList();

    Optional<UserDto> getUserDtoById(Long id);

    Long getCountNewUsersByReputation(long weeks);

    List<UserDto> getListNewUsersByReputation(int page, int count, long weeks);

    Long getCountUsersByCreationDate(long weeks);

    List<UserDto> getListUsersByCreationDate(int page, int count, long weeks);

    Long getCountUsersByQuantityEditedText(long weeks);

    List<UserDto> getListUsersByQuantityEditedText(int page, int count, long weeks);

    Long getCountUsersByReputation(long weeks);

    List<UserDto> getListUsersByReputation(int page, int count, long weeks);

    Long getCountUsersByVoice(long weeks);

    List<UserDto> getListUsersByVoice(int page, int count, long weeks);

    Long getCountUsersByName(String name, long weeks);

    List<UserDto> getListUsersByNameToSearch(String name, int page, int count, long weeks);

    List<UserDto> getListUsersByModerator();

    //    methods for statistics
    List<UserBadgesDto> getUserBadges(Long userId, Integer page);

    Long getCountOfUserBadges(Long userId);

    Long getAllViews(Long userId);
}