package com.example.jpaCRUDvalidacion.infrastructure.controller;

import com.example.jpaCRUDvalidacion.NotFoundException;
import com.example.jpaCRUDvalidacion.application.PersonaService;
import com.example.jpaCRUDvalidacion.infrastructure.dto.PersonaInputDTO;
import com.example.jpaCRUDvalidacion.infrastructure.dto.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PersonaController {

    @Autowired
    PersonaService personaService;


    /*CRUD*/

    /*create*/
    @PostMapping("addPersona")
    public PersonaOutputDTO addPersona(@RequestBody PersonaInputDTO personaInputDTO) throws Exception {

        return personaService.addPersona(personaInputDTO);

    }


    /*update*/
    @PutMapping("alterPersona")
    public PersonaOutputDTO alterPersona(@RequestBody PersonaInputDTO personaInputDTO) throws Exception {
        try{
        return personaService.alterPersona(personaInputDTO);}
        catch(NoSuchElementException k){
            throw new NotFoundException("Persona con id " + personaInputDTO.getId_persona() + " no encontrada.");
        }

    }
    /*delete*/
    @DeleteMapping("deletePersona/{id}")
    public void deletePersona(@PathVariable int id) throws NotFoundException {
        try{
        personaService.deletePersona(id);}
        catch(NoSuchElementException k){
            throw new NotFoundException("Persona con id " + id + " no encontrada.");
        }
    }

    /*read*/
    /*
    * Buscar por ID */
    @GetMapping("{id}")
    public PersonaOutputDTO getById(@PathVariable int id) throws Exception {
        try{
        return personaService.getById(id);}
        catch(NoSuchElementException k){
            throw new NotFoundException("Persona con id " + id + " no encontrada.");
        }
        }


    /*- Buscar por nombre de usuario (campo usuario)*/

    @GetMapping("getByUsuario/{user}")
    public PersonaOutputDTO getByUsuario(@PathVariable String user){
        return personaService.getByUser(user);

    }

    /*- Mostrar todos los registros.*/
    @GetMapping("all")
    public List<PersonaOutputDTO> getAll(){

        return personaService.getAll();


    }
}
