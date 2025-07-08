package com.user.session.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequest {

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "O sobrenome é obrigatório")
    private String lastname;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String password;

    @NotBlank(message = "O telefone é obrigatório")
    private String cellphone;


    public UserRequest() {}

    // Getters e Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellphone() {
        return cellphone;
    }
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
}
