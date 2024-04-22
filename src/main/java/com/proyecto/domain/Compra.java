package com.proyecto.domain;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="compra")


public class Compra implements Serializable {
     private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_compra")
    private Long idCompra;
    private Long idFactura;
    private Long idRopa;    
    private double precio;
    private int cantidad;    
    
    public Compra() {
    }

    public Compra(Long idFactura, Long idRopa, double precio, int cantidad) {
        this.idFactura = idFactura;
        this.idRopa = idRopa;
        this.precio = precio;
        this.cantidad = cantidad;
    }
}
