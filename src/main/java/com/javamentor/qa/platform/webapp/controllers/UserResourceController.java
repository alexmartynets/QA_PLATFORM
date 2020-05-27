package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import com.javamentor.qa.platform.service.abstracts.dto.UserDtoService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import com.javamentor.qa.platform.webapp.converter.UserConverter;
import javafx.util.Pair;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserResourceController {

    private final UserService userService;
    private final UserDtoService userDtoService;
    private final UserConverter userConverter;

    public UserResourceController(UserService userService, UserDtoService userDtoService, UserConverter userConverter) {
        this.userService = userService;
        this.userDtoService = userDtoService;
        this.userConverter = userConverter;
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        userService.persist(userConverter.toEntity(userDto));
        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers() {
        return ResponseEntity.ok(userDtoService.getUserDtoList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Validated(OnUpdate.class) @RequestBody UserDto userDto) {
        User user = userConverter.toEntity(userDto);
        user.setId(id);
        userService.update(user);
        return ResponseEntity.ok().body(userConverter.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDto>> findUser (@PathVariable Long id) {
        return ResponseEntity.ok(userDtoService.getUserDtoById(id));
    }

    @GetMapping("/{count}/page/{page}")
    public ResponseEntity<Pair<List<UserDto>, Long>> getListUsersToPagination(@PathVariable @NonNull Long page,
                                                                               @PathVariable @NonNull Long count) {
        return ResponseEntity.ok().body(userDtoService.getListUsersToPagination(page.intValue(), count.intValue()));
    }

    @GetMapping("/name")   // ?name=Андрей
    public ResponseEntity<Pair<List<UserDto>, Long>> getListUsersToPagination(@RequestParam @NonNull String name) {
        return ResponseEntity.ok().body(userDtoService.getListUsersByNameToSearch(name));
    }
}