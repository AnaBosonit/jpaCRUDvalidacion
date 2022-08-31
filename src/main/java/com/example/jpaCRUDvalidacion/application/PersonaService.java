package com.example.jpaCRUDvalidacion.application;

import com.example.jpaCRUDvalidacion.NotFoundException;
import com.example.jpaCRUDvalidacion.infrastructure.dto.PersonaInputDTO;
import com.example.jpaCRUDvalidacion.infrastructure.dto.PersonaOutputDTO;

import java.util.List;


public interface PersonaService {



    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws Exception;


    public void deletePersona(int id) throws NotFoundException;

    public PersonaOutputDTO getById(int id) throws Exception;

    public PersonaOutputDTO getByUser(String user);

    public List<PersonaOutputDTO> getAll();

    public PersonaOutputDTO alterPersona(PersonaInputDTO personaInputDTO);




}
