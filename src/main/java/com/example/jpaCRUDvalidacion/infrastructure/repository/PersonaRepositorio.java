package com.example.jpaCRUDvalidacion.infrastructure.repository;

import com.example.jpaCRUDvalidacion.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonaRepositorio extends JpaRepository<Persona, Integer> {
    @Query("select p from Persona p where p.usuario = ?1")
    Persona findByUsuario(String usuario);



}
