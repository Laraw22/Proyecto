
package com.proyecto.Dao;


import com.proyecto.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaDao extends JpaRepository <Factura,Long>{
    
}
