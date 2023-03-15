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

import com.idat.springboot.sistematienda.entity.MetodoPago;
import com.idat.springboot.sistematienda.entity.Utiles;
import com.idat.springboot.sistematienda.service.MetodoService;
import com.idat.springboot.sistematienda.service.ProductosService;

@Controller
public class MetodoPagoController {

	@Autowired
    private ProductosService productoServicio;
	
	@Autowired
	private MetodoService metodoServicio;
	
	@GetMapping(value="/metodo")
	public String listarMetodo(Model model, @Param("search") String search) {
		List<MetodoPago> listarMetodos = metodoServicio.listAll(search);
		model.addAttribute("productos", productoServicio.listAll(null));
		model.addAttribute("metodo", listarMetodos);
		model.addAttribute("titulo","LISTADO DE METODOS DE PAGO");
		model.addAttribute("fechayhora", Utiles.obtenerFechaYHoraActual());
		return "/metodo/metodoListar";
	}
	

	@GetMapping(value="/metodos")
	public String crear( Model model) {
		MetodoPago met = new MetodoPago();
		model.addAttribute("metodo", met);
		model.addAttribute("titulo","REGISTRAR METODO DE PAGO");
		return "/metodo/metodoRegistrar";
	}
	
	
	@PostMapping(value="/metodos")
	public String guardar(@Validated MetodoPago metodopago, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/metodo/metodoRegistrar";
		}
		metodoServicio.save(metodopago);
		return "redirect:/metodo";
	}

	@GetMapping(value="/metodo/{codigo}")
	public String editar(@PathVariable(value="codigo") String codigo, Map<String, Object> model, Model models) {
		MetodoPago metodopago= null;
		if(codigo != null) {
			metodopago = metodoServicio.get(codigo);
		}else {
			return "redirect:/metodo";
		}
		models.addAttribute("titulo","EDITAR METODO DE PAGO: "+ metodopago.getDescripcion());
		model.put("metodo", metodopago);
		return "/metodo/metodoRegistrar";
	}
	
	@GetMapping(value="/metodo/eliminar/{codigo}")
	public String eliminar(@PathVariable(value="codigo")String codigo) {
		if(codigo != null) {
			metodoServicio.delete(codigo);
		}
		return "redirect:/metodo";
	}
}
