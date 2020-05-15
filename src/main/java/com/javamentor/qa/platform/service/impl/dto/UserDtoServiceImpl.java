package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDAO;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.service.abstracts.dto.UserDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDtoServiceImpl implements UserDtoService {

    @Autowired
    private UserDtoDAO userDtoDao;

    @Override
    public List<UserDto> getUserDtoList() {
        return userDtoDao.getUserDtoList();
    }

    @Override
    public Optional<UserDto> getUserDtoById(Long id) {
        return userDtoDao.getUserDtoById(id);
    }

    @Override
    public Long getNumberUsers() {
        return userDtoDao.getNumberUsers();
    }

    @Override
    public List<UserDto> getListUsersForPagination(Long count, Long page) {
        return userDtoDao.getListUsersForPagination(count, page);
    }
}
