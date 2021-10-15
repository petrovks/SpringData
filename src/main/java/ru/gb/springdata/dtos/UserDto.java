package ru.gb.springdata.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.springdata.model.User;

@NoArgsConstructor
@Data
public class UserDto {
    private String username;
    private String password;
    private String repeatPassword;
    private String email;

    public UserDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.repeatPassword = null;
    }
}
