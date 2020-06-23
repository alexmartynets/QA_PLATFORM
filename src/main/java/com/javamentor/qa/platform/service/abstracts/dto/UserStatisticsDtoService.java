package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.dto.UserStatisticDto;

public interface UserStatisticsDtoService {
    UserStatisticDto getUserStatistic(UserDto user, String tab, String sort, int page);
}
