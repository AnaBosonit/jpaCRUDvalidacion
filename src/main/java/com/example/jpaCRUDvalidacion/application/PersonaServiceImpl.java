package com.example.jpaCRUDvalidacion.application;

import com.example.jpaCRUDvalidacion.NotFoundException;
import com.example.jpaCRUDvalidacion.UnprocessableException;
import com.example.jpaCRUDvalidacion.domain.Persona;
import com.example.jpaCRUDvalidacion.infrastructure.dto.PersonaInputDTO;
import com.example.jpaCRUDvalidacion.infrastructure.dto.PersonaOutputDTO;
import com.example.jpaCRUDvalidacion.infrastructure.repository.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;


@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepositorio personaRepositorio;


    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws UnprocessableException {
        int id = personaInputDTO.getId_persona();
        if(personaRepositorio.findById(id) == null){
            throw new UnprocessableException("ID no puede ser nulo");}

        String usuario = personaInputDTO.getUsuario();
        if (usuario==null) {throw new UnprocessableException("Usuario no puede ser nulo");}
        if (usuario.length() > 10 || usuario.length() < 6) {
                throw new UnprocessableException("Longitud de usuario no puede ser superior a 10 ni inferior a 6 caracteres");
            }
        if(personaInputDTO.getPassword()==null) {throw new UnprocessableException("Password no puede ser nula");}
        if (personaInputDTO.getName()==null) {throw new UnprocessableException("Nombre no puede ser nulo");}
        if (personaInputDTO.getCompany_email()==null || personaInputDTO.getPersonal_email()==null) {throw new UnprocessableException("Los emails no pueden ser nulos");}
        if (personaInputDTO.getCity()==null) {throw new UnprocessableException("Ciudad no puede ser nula");}
        if (personaInputDTO.getActive()==null) {throw new UnprocessableException("Active no puede ser nulo");}
        if (personaInputDTO.getCreated_date()==null) {throw new UnprocessableException("Created_date no puede ser nula");}
        Persona persona = new Persona(personaInputDTO);

        System.out.println(persona);


        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(persona);

        System.out.println(personaOutputDTO);


        personaRepositorio.saveAndFlush(persona);

        return personaOutputDTO;

    }


   @Override
    public void deletePersona(int id) throws NotFoundException {
        try{
        personaRepositorio.deleteById(id);}
        catch(Exception e){
            throw new NotFoundException("No se ha podido encontrar ningún usuario con ese id. No se ha podido borrar.");
        }

    }

    @Override
    public PersonaOutputDTO getById(int id) throws NotFoundException {
        //personaRepositorio.findById(id).orElseThrow(()->new Exception("Persona no encontrada."));
        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(personaRepositorio.findById(id).orElseThrow(()->new NotFoundException("No se ha encontrado esa persona con ese id.")));
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
            Persona persona = personaRepositorio.findById(id).orElseThrow(()->new NotFoundException("No se ha encontrado esa persona con ese id."));
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
            if (personaInputDTO.getUsuario()==null) {throw new UnprocessableException("Usuario no puede ser nulo");}
            if (personaInputDTO.getUsuario().length() > 10 || personaInputDTO.getUsuario().length() < 6) {
                throw new UnprocessableException("Longitud de usuario no puede ser superior a 10 ni inferior a 6 caracteres");
            }
            if(personaInputDTO.getPassword()==null) {throw new UnprocessableException("Password no puede ser nula");}
            if (personaInputDTO.getName()==null) {throw new UnprocessableException("Nombre no puede ser nulo");}
            if (personaInputDTO.getCompany_email()==null || personaInputDTO.getPersonal_email()==null) {throw new UnprocessableException("Los emails no pueden ser nulos");}
            if (personaInputDTO.getCity()==null) {throw new UnprocessableException("Ciudad no puede ser nula");}
            if (personaInputDTO.getActive()==null) {throw new UnprocessableException("Active no puede ser nulo");}
            if (personaInputDTO.getCreated_date()==null) {throw new UnprocessableException("Created_date no puede ser nula");}
            personaRepositorio.saveAndFlush(persona);

            personaOutputDTO = new PersonaOutputDTO(persona);

        }
        return personaOutputDTO;
    }


}
