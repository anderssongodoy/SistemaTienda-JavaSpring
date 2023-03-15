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

import com.idat.springboot.sistematienda.entity.Cliente;
import com.idat.springboot.sistematienda.service.ClienteService;
import com.idat.springboot.sistematienda.service.ProductosService;

@Controller
public class ClientesController {
	
	@Autowired
    private ProductosService productoServicio;
	
	@Autowired
	private ClienteService clienteServicio;
	
	@GetMapping(value="/cliente")
	public String listarClientes(Model model, @Param("search") String search) {
		List<Cliente> listaClientes = clienteServicio.listAll(search);
		model.addAttribute("search", search);
		model.addAttribute("productos", productoServicio.listAll(null));
		model.addAttribute("clientes", listaClientes);
		model.addAttribute("titulo","LISTADO DE CLIENTES");
		return "/cliente/clienteListar";
	}
	
	
	@GetMapping(value="/clientes")
	public String crear( Model model) {
		Cliente cli = new Cliente();
		model.addAttribute("cliente", cli);
		model.addAttribute("titulo","REGISTRAR CLIENTE");
		return "/cliente/clienteRegistrar";
	}
	
	
	@PostMapping(value="/clientes")
	public String guardar(@Validated Cliente clientes, BindingResult bindingResult) {
		if ( bindingResult.hasErrors()) {
			return "/cliente/clienteRegistrar";
		}
		clienteServicio.save(clientes);
		return "redirect:/cliente";
	}
	
	@GetMapping(value="/clientes/{cod}")
	public String editar(@PathVariable(value="cod") String cod, Map<String, Object> model, Model models) {
		Cliente cliente = null;
		if(cod != null) {
			cliente = clienteServicio.get(cod);
		}else {
			return "redirect:/cliente";
		}
		models.addAttribute("titulo","EDITAR CLIENTE: "+ cliente.getNombre());
		model.put("cliente", cliente);
		return "/cliente/clienteRegistrar";
	}
	
	@GetMapping(value="/clientes/eliminar/{cod}")
	public String eliminar(@PathVariable(value="cod")String cod) {
		if(cod != null) {
			clienteServicio.delete(cod);
		}
		return "redirect:/cliente";
	}
}

