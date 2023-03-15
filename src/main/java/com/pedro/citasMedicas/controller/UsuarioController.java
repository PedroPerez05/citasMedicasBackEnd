package com.pedro.citasMedicas.controller;

import com.pedro.citasMedicas.dto.AuthRequest;
import com.pedro.citasMedicas.model.Usuario;
import com.pedro.citasMedicas.service.JwtService;
import com.pedro.citasMedicas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @PostMapping("/new") //creamos el usuario en la base de datos
    public String addNewUser(@RequestBody Usuario userInfo){
        return usuarioService.addUser(userInfo);
    }
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) { //autenticamos al usuario
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername()); //devuelve el token si se autentica correctamente
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }
}
