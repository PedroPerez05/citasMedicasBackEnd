package com.pedro.citasMedicas.service;

import com.pedro.citasMedicas.model.Usuario;
import com.pedro.citasMedicas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;
    public String addUser(Usuario userInfo) {
        userInfo.setClave(passwordEncoder.encode(userInfo.getClave())); //codificamos la contraseña
        usuarioRepository.save(userInfo); // y guardamos el usuario
        return "Usuario añadido al sistema";
    }
}
