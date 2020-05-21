package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import com.javamentor.qa.platform.service.abstracts.dto.UserDtoService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import com.javamentor.qa.platform.webapp.converter.UserConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@Api(value="UserApi", description = "Операции с пользователем (создание, изменение, получение списка, получение пользователя по ID)")
public class UserResourceController {

    private final UserService userService;
    private final UserDtoService userDtoService;
    private final UserConverter userConverter;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserResourceController(UserService userService, UserDtoService userDtoService, UserConverter userConverter) {
        this.userService = userService;
        this.userDtoService = userDtoService;
        this.userConverter = userConverter;
    }

    @ApiOperation(value = "Добавление пользователя")
    @PostMapping
    public ResponseEntity<UserDto> addUser(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        logger.info("enter to addUser method Post");
        userService.persist(userConverter.toEntity(userDto));
        logger.info(userDto.toString() + " saved into database successfully");
        return ResponseEntity.ok().body(userDto);
    }

    @ApiOperation(value = "получение списка доступных пользователей")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успех"),
            @ApiResponse(code = 401, message = "Вы не авторизованы для просмотра ресурса"),
            @ApiResponse(code = 403, message = "Доступ к ресурсу запрещен"),
            @ApiResponse(code = 404, message = "Ресурс не найден")
    }
    )

    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers() {
        logger.info("enter to findAllUsers method Get and return all users from database");
        return ResponseEntity.ok(userDtoService.getUserDtoList());
    }

    @ApiOperation(value = "Изменение пользователя (параметр ID обязателен)")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Validated(OnUpdate.class) @RequestBody UserDto userDto) {
        logger.info("enter to updateUser method Put and try to convert userDTO to User");
        User user = userConverter.toEntity(userDto);
        logger.info(user.toString() + " converted successfully");
        user.setId(id);
        logger.info("try to update user");
        userService.update(user);
        logger.info("user updated successfully");
        return ResponseEntity.ok().body(userConverter.toDto(user));
    }

    @ApiOperation(value = "Поиск пользователя по ID")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDto>> findUser (@PathVariable Long id) {
        logger.info("enter to findUser by id: " + id);
        return ResponseEntity.ok(userDtoService.getUserDtoById(id));
    }
}