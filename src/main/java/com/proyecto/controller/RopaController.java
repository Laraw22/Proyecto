
package com.proyecto.controller;

import com.proyecto.domain.Ropa;
import com.proyecto.service.RopaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ropa")
public class RopaController {
     @Autowired
    private RopaService ropaService;
    
    
    @GetMapping("/listado")
    private String listado(Model model) {
        var ropas = ropaService.getRopas(false);
        model.addAttribute("ropas", ropas);
        
       
        model.addAttribute("totalRopas",ropas.size());
        return "/ropa/listado";
    }
    
     @GetMapping("/nuevo")
    public String ropaNuevo(Ropa ropa) {
        return "/ropa/modifica";
    }

       
    @GetMapping("/eliminar/{idRopa}")
    public String ropaEliminar(Ropa ropa) {
        ropaService.delete(ropa);
        return "redirect:/ropa/listado";
    }

    
}
