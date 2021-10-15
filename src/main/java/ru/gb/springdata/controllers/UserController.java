package ru.gb.springdata.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springdata.dtos.UserDto;
import ru.gb.springdata.exceptions.DataValidationException;
import ru.gb.springdata.services.UserService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/create_user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDto save(@RequestBody @Validated UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        if (!userDto.getPassword().equals(userDto.getRepeatPassword())) {
            throw new RuntimeException("Пароли не совпадают");
        }
        return userService.createNewUser(userDto);
    }
}
