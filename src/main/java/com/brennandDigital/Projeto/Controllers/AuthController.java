package com.brennandDigital.Projeto.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brennandDigital.Projeto.Domain.User;
import com.brennandDigital.Projeto.Services.AuthServices;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/auth")
@Slf4j
public class AuthController {

    private final AuthServices authServices;

    public AuthController(AuthServices authServices){
        this.authServices = authServices;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User userid){
        try{
            boolean success = authServices.checkLogin(userid.getUserName(), userid.getPassword());
            if(success){
                return ResponseEntity.ok("Login bem-sucedido");
            }
            else {
                return ResponseEntity.badRequest().body("Login ou senha inválidos");
            }
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
