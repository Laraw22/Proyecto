package com.proyecto.service;

import com.proyecto.domain.Cosas;
import java.util.ArrayList;
import java.util.List;

public interface CosasService {
    
    List<Cosas> listaCosas = new ArrayList<>();
    
    public List<Cosas> gets();
    
    //Se recupera el registro que tiene el idCosas pasado por parámetro
    //si no existe en la tabla se retorna null
    public Cosas get(Cosas cosas);
    
    //Se elimina el registro que tiene el idCosas pasado por parámetro
    public void delete(Cosas cosas);
    
    //Si el objeto cosas tiene un idCosas que existe en la tabla cosas
    //El registro de actualiza con la nueva información
    //Si el idCosas NO existe en la tabla, se crea el registro con esa información
    public void save(Cosas cosas);
    
    public void actualiza(Cosas cosas);
    
    public void facturar();
}
