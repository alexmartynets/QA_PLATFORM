package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDAO;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.service.abstracts.dto.UserDtoService;
import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDtoServiceImpl implements UserDtoService {

    private final UserDtoDAO userDtoDao;

    public UserDtoServiceImpl(UserDtoDAO userDtoDao) {
        this.userDtoDao = userDtoDao;
    }

    @Override
    public List<UserDto> getUserDtoList() {
        return userDtoDao.getUserDtoList();
    }

    @Override
    public Optional<UserDto> getUserDtoById(Long id) {
        return userDtoDao.getUserDtoById(id);
    }

    @Override
    public Pair<List<UserDto>, Long> getListNewUsersByReputation(int page, int count, long weeks) {
        List<UserDto> reputationDtoList = userDtoDao.getListNewUsersByReputation(page, count, weeks);
        Long countUsers = userDtoDao.getCountNewUsersByReputation(weeks);
        return new Pair<>(reputationDtoList, countUsers);
    }

    @Override
    public Pair<List<UserDto>, Long> getListUsersByCreationDate(int page, int count, long weeks) {
        List<UserDto> reputationDtoList = userDtoDao.getListUsersByCreationDate(page, count, weeks);
        Long countUsers = userDtoDao.getCountUsersByCreationDate(weeks);
        return new Pair<>(reputationDtoList, countUsers);
    }

    @Override
    public Pair<List<UserDto>, Long> getListUsersByReputation(int page, int count, long weeks) {
        List<UserDto> reputationDtoList = userDtoDao.getListUsersByReputation(page, count, weeks);
        Long counts = userDtoDao.getCountUsersByReputation(weeks);
        return new Pair<>(reputationDtoList, counts);
    }

    @Override
    public Pair<List<UserDto>, Long> getListUsersByVoice(int page, int count, long weeks) {
        List<UserDto> reputationDtoList = userDtoDao.getListUsersByVoice(page, count, weeks);
        Long counts = userDtoDao.getCountUsersByVoice(weeks);
        return new Pair<>(reputationDtoList, counts);
    }

    @Override
    public Pair<List<UserDto>, Long> getListUsersByNameToSearch(String name, int page, int count, long weeks) {
        List<UserDto> reputationDtoList = userDtoDao.getListUsersByNameToSearch(name, page, count, weeks);
        Long counts = userDtoDao.getCountUsersByName(name, weeks);
        return new Pair<>(reputationDtoList, counts);
    }

    @Override
    public Pair<List<UserDto>, Long> getListUsersByQuantityEditedText(int page, int count, long weeks) {
        List<UserDto> editorDtoList = userDtoDao.getListUsersByQuantityEditedText(page, count, weeks);
        Long counts = userDtoDao.getCountUsersByQuantityEditedText(weeks);
        return new Pair<>(editorDtoList, counts);
    }

    @Override
    public Pair<List<UserDto>, Long> getListUsersByModerator() {
        List<UserDto> moderatorDtoList = userDtoDao.getListUsersByModerator();
        long countUsers = moderatorDtoList.size();
        return new Pair<>(moderatorDtoList, countUsers);
    }

    @Override
    public boolean isNegativeNumber(Long count, Long page, Long weeks) {
        return count <= 0 || page <= 0 || weeks <= 0;
    }

    @Override
    public boolean isString(@NotNull String name) {
        return name.matches("[а-яА-ЯёЁa-zA-Z]+.*$");
    }
}


