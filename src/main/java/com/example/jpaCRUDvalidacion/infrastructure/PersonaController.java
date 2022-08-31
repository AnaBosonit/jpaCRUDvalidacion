package com.example.jpaCRUDvalidacion.infrastructure;

import com.example.jpaCRUDvalidacion.domain.Persona;
import com.example.jpaCRUDvalidacion.application.PersonaRepositorio;
import com.example.jpaCRUDvalidacion.application.PersonaService;
import com.example.jpaCRUDvalidacion.infrastructure.DTO.PersonaInputDTO;
import com.example.jpaCRUDvalidacion.infrastructure.DTO.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return personaService.alterPersona(personaInputDTO);

    }
    /*delete*/
    @DeleteMapping("deletePersona/{id}")
    public void deletePersona(@PathVariable int id) throws Exception {
        personaService.deletePersona(id);

    }

    /*read*/
    /*
    * Buscar por ID */
    @GetMapping("{id}")
    public PersonaOutputDTO getById(@PathVariable int id) throws Exception {
        return personaService.getById(id);
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
