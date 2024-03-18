/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "ropa")
public class Ropa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ropa")
    private Long idRopa;    
    private String descripcion;
    private String talla;
    private int existencias;
    private double precio;
    private String rutaImagen;
    private boolean activo;

    
    public Ropa() {
    }

    public Ropa(String descripcion, boolean activo) {
        this.descripcion = descripcion;
        this.activo = activo;
    }

}
