package com.pedro.citasMedicas.repository;

import com.pedro.citasMedicas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositoy extends JpaRepository<Usuario, Long> {
}
