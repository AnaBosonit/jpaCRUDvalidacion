package com.example.jpaCRUDvalidacion.domain;

import com.example.jpaCRUDvalidacion.infrastructure.DTO.PersonaInputDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="Persona")

public class Persona {


    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private int id_persona;

    @Column(name="usuario")
    private String usuario;

    @Column(name="password")
    private String password;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="company_email")
    private String company_email;
    @Column(name="personal_email")
    private String personal_email;
    @Column(name="city")
    private String city;
    @Column(name="active")
    private Boolean active;
    @Column(name="created_date")
    private Date created_date;
    @Column(name="imagen_url")
    private String imagen_url;
    @Column(name="termination_date")
    private Date termination_date;

    public Persona(PersonaInputDTO personaInputDTO){
        if (personaInputDTO == null){
            return;
        }
        setId_persona(personaInputDTO.getId_persona());
        setUsuario(personaInputDTO.getUsuario());
        setPassword(personaInputDTO.getPassword());
        setName(personaInputDTO.getName());
        setSurname(personaInputDTO.getSurname());
        setCity(personaInputDTO.getCity());
        setCompany_email(personaInputDTO.getCompany_email());
        setPersonal_email(personaInputDTO.getPersonal_email());
        setActive(personaInputDTO.getActive());
        setCreated_date(personaInputDTO.getCreated_date());
        setImagen_url(personaInputDTO.getImagen_url());
        setTermination_date(personaInputDTO.getTermination_date());
    }


    public Persona() {

    }
}

