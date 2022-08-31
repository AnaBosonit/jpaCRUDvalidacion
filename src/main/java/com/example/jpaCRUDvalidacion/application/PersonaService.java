package com.example.jpaCRUDvalidacion.application;

import com.example.jpaCRUDvalidacion.domain.Persona;
import com.example.jpaCRUDvalidacion.infrastructure.DTO.PersonaInputDTO;
import com.example.jpaCRUDvalidacion.infrastructure.DTO.PersonaOutputDTO;

import java.util.List;


public interface PersonaService {



    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws Exception;


    public void deletePersona(int id) throws Exception;

    public PersonaOutputDTO getById(int id) throws Exception;

    public PersonaOutputDTO getByUser(String user);

    public List<PersonaOutputDTO> getAll();

    public PersonaOutputDTO alterPersona(PersonaInputDTO personaInputDTO);




}
