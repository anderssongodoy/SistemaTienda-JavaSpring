package com.idat.springboot.sistematienda.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.idat.springboot.sistematienda.entity.Producto;
import com.idat.springboot.sistematienda.repository.ProductosRepository;
import com.idat.springboot.sistematienda.service.ProductosService;

@Controller
public class LoginController {
	@Autowired
    private ProductosService productoServicio;
	
	@GetMapping (value={"/login","/"})
	public String login(@RequestParam(value="error" ,required=false)String error,
			Model model, Principal principal) {

		if(principal!=null) {
			List<Producto> listaProductos = productoServicio.listAll(null);
			model.addAttribute("info", "Ha iniciado sesión");
			model.addAttribute("productos", productoServicio.listAll(null));
			model.addAttribute("producto", listaProductos);
			return "index";
		}
		if(error!=null) {
			model.addAttribute("error","Error: Usuario y/o contraseña incorrecta");
		}
		return "login";
	}

}

