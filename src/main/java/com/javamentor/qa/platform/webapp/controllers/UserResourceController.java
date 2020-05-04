package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.impl.UserServiceImpl;
import com.javamentor.qa.platform.webapp.converter.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserResourceController {
    private final UserServiceImpl userService;
    private final UserConverter userConverter;

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        userService.persist(userConverter.toUser(userDto));
        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers() {
        return ResponseEntity.ok(userConverter.toUserDtos(userService.getAll()));
    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userConverter.toUser(userDto);
        user.setId(id);
        userService.persist(user);
        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<UserDto> findUser (@PathVariable Long id) {
        Optional<User> user = Optional.ofNullable(userService.getByKey(id));
        return ResponseEntity.ok(userConverter.toUserDto(user.get()));
    }
}