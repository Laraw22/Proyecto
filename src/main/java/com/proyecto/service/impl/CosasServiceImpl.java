
package com.proyecto.service.impl;

import com.proyecto.Dao.CompraDao;
import com.proyecto.Dao.FacturaDao;
import com.proyecto.Dao.RopaDao;
import com.proyecto.domain.Compra;
import com.proyecto.domain.Cosas;
import com.proyecto.domain.Factura;
import com.proyecto.domain.Ropa;
import com.proyecto.domain.Usuario;
import com.proyecto.service.CosasService;
import static com.proyecto.service.CosasService.listaCosas;
import com.proyecto.service.UsuarioService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;


@Service
public class CosasServiceImpl implements CosasService{
     @Override
    public List<Cosas> gets() {
        return listaCosas;
    }

    //Se usa en el addCarrito... agrega un elemento
    @Override
    public void save(Cosas cosas) {
        boolean existe = false;
        for (Cosas i : listaCosas) {
            //Busca si ya existe el producto en el carrito
            if (Objects.equals(i.getIdRopa(), cosas.getIdRopa())) {
                //Valida si aún puede colocar un cosas adicional -segun existencias-
                if (i.getCantidad() < cosas.getExistencias()) {
                    //Incrementa en 1 la cantidad de elementos
                    i.setCantidad(i.getCantidad() + 1);
                }
                existe = true;
                break;
            }
        }
        if (!existe) {//Si no está el producto en el carrito se agrega cantidad =1.            
            cosas.setCantidad(1);
            listaCosas.add(cosas);
        }
    }

    //Se usa para eliminar un producto del carrito
    @Override
    public void delete(Cosas cosas) {
        var posicion = -1;
        var existe = false;
        for (Cosas i : listaCosas) {
            ++posicion;
            if (Objects.equals(i.getIdRopa(), cosas.getIdRopa())) {
                existe = true;
                break;
            }
        }
        if (existe) {
            listaCosas.remove(posicion);
        }
    }

    //Se obtiene la información de un producto del carrito... para modificarlo
    @Override
    public Cosas get(Cosas cosas) {
        for (Cosas i : listaCosas) {
            if (Objects.equals(i.getIdRopa(), cosas.getIdRopa())) {
                return i;
            }
        }
        return null;
    }

    //Se usa en la página para actualizar la cantidad de productos
    @Override
    public void actualiza(Cosas cosas) {
        for (Cosas i : listaCosas) {
            if (Objects.equals(i.getIdRopa(), cosas.getIdRopa())) {
                i.setCantidad(cosas.getCantidad());
                break;
            }
        }
    }

    @Autowired
    private UsuarioService usarioService;

    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private CompraDao compraDao;
    @Autowired
    private RopaDao ropaDao;

    @Override
    public void facturar() {
        System.out.println("Facturando");

        //Se obtiene el usuario autenticado
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }

        if (username.isBlank()) {
            return;
        }

        Usuario uuario = usarioService.getUsuarioPorUsername(username);

        if (uuario == null) {
            return;
        }

        Factura factura = new Factura(uuario.getIdUsuario());
        factura = facturaDao.save(factura);

        double total = 0;
        for (Cosas i : listaCosas) {
            System.out.println("Producto: " + i.getDescripcion()
                    + " Cantidad: " + i.getCantidad()
                    + " Total: " + i.getPrecio() * i.getCantidad());
            Compra compra = new Compra(factura.getIdFactura(), i.getIdRopa(), i.getPrecio(), i.getCantidad());
            compraDao.save(compra);
            Ropa ropa = ropaDao.getReferenceById(i.getIdRopa());
            ropa.setExistencias(ropa.getExistencias()-i.getCantidad());
            ropaDao.save(ropa);
            total += i.getPrecio() * i.getCantidad();
        }
        factura.setTotal(total);
        facturaDao.save(factura);
        listaCosas.clear();
    }
}
