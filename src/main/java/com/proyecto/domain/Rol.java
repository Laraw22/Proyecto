package com.proyecto.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

import lombok.Data;

@Data
@Entity
@Table(name="rango")
public class Rol implements Serializable {
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_rango")
    private Long idRango;
    
    private String nombre;
    
    @Column(name="id_usuario")
    private Long idUsuario;
}


