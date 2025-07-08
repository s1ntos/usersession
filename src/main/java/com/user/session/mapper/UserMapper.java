package com.user.session.mapper;

import com.user.session.dto.UserRequest;
import com.user.session.model.Usuario;

public class UserMapper {

    public static Usuario toEntity(UserRequest dto) {
        Usuario users = new Usuario();
        users.setName(dto.getName());
        users.setLastname(dto.getLastname());
        users.setPassword(dto.getPassword());
        users.setCellphone(dto.getCellphone());
        return users;
    }

}
