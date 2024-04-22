package com.proyecto.controller;

import com.proyecto.domain.Cosas;
import com.proyecto.domain.Ropa;
import com.proyecto.service.CosasService;
import com.proyecto.service.RopaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarritoController {
    @Autowired
    private CosasService cosasService;
    @Autowired
    private RopaService ropaService;

    @GetMapping("/")
    private String listado(Model model) {
        var ropas = ropaService.getRopas(false);
        model.addAttribute("ropas", ropas);

        return "/index";
    }

    //Para ver el carrito
    @GetMapping("/carrito/listado")
    public String inicio(Model model) {
        var cosas = cosasService.gets();
        model.addAttribute("cosas", cosas);
        var carritoTotalVenta = 0;
        for (Cosas i : cosas) {
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("carritoTotal",
                carritoTotalVenta);
        return "/carrito/listado";
    }

    //Para Agregar un producto al carrito
    @GetMapping("/carrito/agregar/{idRopa}")
    public ModelAndView agregarCosas(Model model, Cosas cosas) {
        Cosas cosas2 = cosasService.get(cosas);
        if (cosas2 == null) {
            Ropa ropa = ropaService.getRopa(cosas);
            cosas2 = new Cosas(ropa);
        }
        cosasService.save(cosas2);
        var lista = cosasService.gets();
        var totalCarritos = 0;
        var carritoTotalVenta = 0;
        for (Cosas i : lista) {
            totalCarritos += i.getCantidad();
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("listaCosass", lista);
        model.addAttribute("listaTotal", totalCarritos);
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }

    //Para mofificar un producto del carrito
    @GetMapping("/carrito/modificar/{idRopa}")
    public String modificarCosas(Cosas cosas, Model model) {
        cosas = cosasService.get(cosas);
        model.addAttribute("cosas", cosas);
        return "/carrito/modificar";
    }

    //Para eliminar un elemento del carrito
    @GetMapping("/carrito/eliminar/{idRopa}")
    public String eliminarCosas(Cosas cosas) {
        cosasService.delete(cosas);
        return "redirect:/carrito/listado";
    }

    //Para actualizar un producto del carrito (cantidad)
    @PostMapping("/carrito/guardar")
    public String guardarCosas(Cosas cosas) {
        cosasService.actualiza(cosas);
        return "redirect:/carrito/listado";
    }

    //Para facturar los productos del carrito... no implementado...
    @GetMapping("/facturar/carrito")
    public String facturarCarrito() {
        cosasService.facturar();
        return "redirect:/";
    }
}
