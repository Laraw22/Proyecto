package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name="factura")


public class Factura implements Serializable{
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_factura")
    private Long idFactura;
    private Long idUsuario;
    private Date fecha;
    private double total;
    private int estado;
    
    
    public Factura() {
    }

    public Factura(Long idUSuario) {
        this.idUsuario = idUSuario;
        this.fecha = Calendar.getInstance().getTime();
        this.estado=1;
    }  
}
