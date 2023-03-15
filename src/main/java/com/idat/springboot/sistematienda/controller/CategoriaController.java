package com.idat.springboot.sistematienda.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.idat.springboot.sistematienda.entity.Categoria;
import com.idat.springboot.sistematienda.entity.Utiles;
import com.idat.springboot.sistematienda.service.CategoriaService;
import com.idat.springboot.sistematienda.service.ProductosService;

@Controller
public class CategoriaController {
	
	@Autowired
    private ProductosService productoServicio;
	
	@Autowired
	private CategoriaService categoriaServicio;
	
	@GetMapping(value="/categoria")
	public String listarCategorias(Model model, @Param("search") String search) {
		List<Categoria> listaCategorias = categoriaServicio.listAll(search);
		model.addAttribute("productos", productoServicio.listAll(null));
		model.addAttribute("categorias", listaCategorias);
		model.addAttribute("search", search);
		model.addAttribute("titulo","LISTADO DE CATEGORIAS");
		model.addAttribute("fechayhora", Utiles.obtenerFechaYHoraActual());
		return "/categoria/categoriaListar";
	}
	
	
	@GetMapping(value="/categorias")
	public String crear( Model model) {
		Categoria cat = new Categoria();
		model.addAttribute("categoria", cat);
		model.addAttribute("titulo","REGISTRAR CATEGORIA");
		return "/categoria/categoriaRegistrar";
	}
	
	
	@PostMapping(value="/categorias")
	public String guardar(@Validated Categoria categoria, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/categoria/categoriaRegistrar";
		}
		categoriaServicio.save(categoria);
		return "redirect:/categoria";
	}
	
	@GetMapping(value="/categorias/{codigo}")
	public String editar(@PathVariable(value="codigo") String codigo, 
			Map<String, Object> model, Model models) {
		Categoria categoria = null;
		if(codigo != null) {
			categoria = categoriaServicio.get(codigo);
		}else {
			return "redirect:/categoria";
		}
		models.addAttribute("titulo","EDITAR CATEGORIA: "+ categoria.getNombre());
		model.put("categoria", categoria);
		return "/categoria/categoriaRegistrar";
	}
	
	@GetMapping(value="/categoria/eliminar/{codigo}")
	public String eliminar(@PathVariable(value="codigo")String codigo) {
		if(codigo != null) {
			categoriaServicio.delete(codigo);
		}
		return "redirect:/categoria";
	}
}

