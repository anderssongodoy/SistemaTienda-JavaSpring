package com.idat.springboot.sistematienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.idat.springboot.sistematienda.entity.Venta;
import com.idat.springboot.sistematienda.service.EmailService;
import com.idat.springboot.sistematienda.service.ProductosService;
import com.idat.springboot.sistematienda.service.VentasService;

@Controller
public class VentasController {
	
	@Autowired
	private EmailService emailServicio;
	
	@Autowired
    private ProductosService productoServicio;
	
    @Autowired
    VentasService ventasServicio;

    @GetMapping(value = "/ventas")
    public String mostrarVentas(Model model, @Param("search") String search, @Param("correo") String correo) {
    	List<Venta> listaVentas = ventasServicio.listAll(search);
    	model.addAttribute("titulo", "LISTA DE VENTAS");
    	model.addAttribute("productos", productoServicio.listAll(null));
        model.addAttribute("ventas", listaVentas);
        return "ventas/ventas";
    }
    
    @PostMapping(value = "/sendMail")
    public String sendEmail() {
    	String message = "TU BOLETA" ;
    	emailServicio.sendMail("losgemelostienda123@gmail.com","gonzalesjhuliann@gmail.com", "prueba", message);
    	
    	return "redirect:/ventas";
    }
}
