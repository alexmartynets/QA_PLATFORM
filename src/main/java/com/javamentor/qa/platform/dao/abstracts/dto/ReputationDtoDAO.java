package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.ReputationDto;
import com.javamentor.qa.platform.models.entity.user.User;

public interface ReputationDtoDAO {

    ReputationDto getUserReputation(Long user_id);

    ReputationDto findByUserIdAndDate(User user);
}
