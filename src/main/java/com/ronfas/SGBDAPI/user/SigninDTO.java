package com.ronfas.SGBDAPI.user;

import com.ronfas.SGBDAPI.inscription.Inscription;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class SigninDTO {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
