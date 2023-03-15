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

import com.idat.springboot.sistematienda.entity.Empleado;
import com.idat.springboot.sistematienda.service.EmpleadoService;
import com.idat.springboot.sistematienda.service.ProductosService;



@Controller
public class EmpleadoController {
	
	@Autowired
    private ProductosService productoServicio;
	
	@Autowired
	private EmpleadoService empleadoServicio;
	
	@GetMapping(value = "/empleado")
	public String listarEmpleado(Model model, @Param("search") String search) {
		List<Empleado> listaEmpleados = empleadoServicio.listAll(search);
		model.addAttribute("productos", productoServicio.listAll(null));
		model.addAttribute("empleados", listaEmpleados);
		model.addAttribute("titulo","LISTADO DE EMPLEADOS");
		return "/empleado/empleadoListar";
	}
	
	@GetMapping(value = "/empleados")
	public String crear(Model model) {
		Empleado empleado = new Empleado();
		model.addAttribute("titulo", "REGISTRAR EMPLEADO");
		model.addAttribute("empleado", empleado);
		return "/empleado/empleadoRegistrar";
	}
	
	@PostMapping(value = "/empleados")
	public String guardar(@Validated Empleado empleado, 
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "/empleado/empleadoRegistrar";
		}
		
		empleadoServicio.save(empleado);
		return "redirect:/empleado";
	}
	
	@GetMapping(value = "/empleados/{id}")
	public String editar(@PathVariable(value = "id") String id, Map<String, Object> model) {
		Empleado empleado = null;
		if (id != null) {
			empleado = empleadoServicio.get(id);
		} else {
			return "redirect:/empleado";
		}
		model.put("titulo", "EDITAR EMPLEADO: "+ empleado.getNombre());
		model.put("empleado", empleado);
		return "/empleado/empleadoRegistrar";
	}
	
	@GetMapping(value = "/empleado/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") String id) {
		if (id != null) {
			empleadoServicio.delete(id);
		}
		return "redirect:/empleado";
	}
	
	@GetMapping(value="/empleadodetalle/{id}")
	public String ver(@PathVariable(value="id") String id, Map<String, Object> model) {
			Empleado empleado = empleadoServicio.get(id);
		if(empleado==null) {
			return "redirect:/empleadoListar";			
		}
		model.put("empleado",empleado);
		model.put("titulo", "DETALLE DEL EMPLEADO: "+empleado.getNombre());
		return "/empleado/empleadoDetalle";		
	}
}