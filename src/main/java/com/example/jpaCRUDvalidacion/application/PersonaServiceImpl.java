package com.example.jpaCRUDvalidacion.application;

import com.example.jpaCRUDvalidacion.application.PersonaService;
import com.example.jpaCRUDvalidacion.domain.Persona;
import com.example.jpaCRUDvalidacion.infrastructure.DTO.PersonaInputDTO;
import com.example.jpaCRUDvalidacion.infrastructure.DTO.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;

import java.util.List;


@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepositorio personaRepositorio;


    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws Exception {




        int id = personaInputDTO.getId_persona();
        if(personaRepositorio.findById(id) == null){
            throw new Exception("ID no puede ser nulo");}

        String usuario = personaInputDTO.getUsuario();
        if (usuario==null) {throw new Exception("Usuario no puede ser nulo");}
        if (usuario.length() > 10 || usuario.length() < 6) {
                throw new Exception("Longitud de usuario no puede ser superior a 10 ni inferior a 6 caracteres");
            }
        if(personaInputDTO.getPassword()==null) {throw new Exception("Password no puede ser nula");}
        if (personaInputDTO.getName()==null) {throw new Exception("Nombre no puede ser nulo");}
        if (personaInputDTO.getCompany_email()==null || personaInputDTO.getPersonal_email()==null) {throw new Exception("Los emails no pueden ser nulos");}
        if (personaInputDTO.getCity()==null) {throw new Exception("Ciudad no puede ser nula");}
        if (personaInputDTO.getActive()==null) {throw new Exception("Active no puede ser nulo");}
        if (personaInputDTO.getCreated_date()==null) {throw new Exception("Created_date no puede ser nula");}
        Persona persona = new Persona(personaInputDTO);

        System.out.println(persona);


        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(persona);

        System.out.println(personaOutputDTO);


        personaRepositorio.saveAndFlush(persona);

        return personaOutputDTO;

    }


   @Override
    public void deletePersona(int id) throws Exception {
        personaRepositorio.deleteById(id);

    }

    @Override
    public PersonaOutputDTO getById(int id) throws Exception {
        //personaRepositorio.findById(id).orElseThrow(()->new Exception("Persona no encontrada."));
        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(personaRepositorio.findById(id).orElseThrow(()->new Exception("Persona no encontrada.")));
        return personaOutputDTO;
    }

    @Override
    public PersonaOutputDTO getByUser(String user) {
        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(personaRepositorio.findByUsuario(user));
        System.out.println("Usuario es " + user);
        return personaOutputDTO;
    }

    @Override
    public List<PersonaOutputDTO> getAll() {
        PersonaOutputDTO personaOutputDTO;
        List<PersonaOutputDTO> personaOutputDTOList = new ArrayList<>();
        for(Persona p:  personaRepositorio.findAll()) {
            personaOutputDTO = new PersonaOutputDTO(p);
            personaOutputDTOList.add(personaOutputDTO);

        }
        return personaOutputDTOList;
    }

    @Override
    public PersonaOutputDTO alterPersona(PersonaInputDTO personaInputDTO) {
        int id = personaInputDTO.getId_persona();
        PersonaOutputDTO personaOutputDTO = null;

        if(personaRepositorio.findById(id) != null){
            Persona persona = personaRepositorio.findById(id).orElseThrow(null);
            if(personaInputDTO.getName() != null){
                persona.setName(personaInputDTO.getName());
            }
            if(personaInputDTO.getSurname() != null){
                persona.setSurname(personaInputDTO.getSurname());
            }
            if(personaInputDTO.getActive() != null){
                persona.setActive(personaInputDTO.getActive());
            }
            if(personaInputDTO.getImagen_url() != null){
                persona.setImagen_url(personaInputDTO.getImagen_url());
            }
            if(personaInputDTO.getCity() != null){
                persona.setCity(personaInputDTO.getCity());
            }
            if(personaInputDTO.getCompany_email() != null){
                persona.setCompany_email(personaInputDTO.getCompany_email());
            }
            if(personaInputDTO.getPersonal_email() != null){
                persona.setPersonal_email(personaInputDTO.getPersonal_email());
            }
            if(personaInputDTO.getCreated_date() != null){
                persona.setCreated_date(personaInputDTO.getCreated_date());
            }
            if(personaInputDTO.getTermination_date() != null){
                persona.setTermination_date(personaInputDTO.getTermination_date());
            }
            if(personaInputDTO.getUsuario() != null){
                persona.setUsuario(personaInputDTO.getUsuario());
            }
            personaRepositorio.saveAndFlush(persona);

            personaOutputDTO = new PersonaOutputDTO(persona);

        }
        return personaOutputDTO;
    }


}
