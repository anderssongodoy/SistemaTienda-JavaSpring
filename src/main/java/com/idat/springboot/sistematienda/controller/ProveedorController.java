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

import com.idat.springboot.sistematienda.entity.Proveedor;
import com.idat.springboot.sistematienda.service.ProductosService;
import com.idat.springboot.sistematienda.service.ProveedorService;

@Controller
public class ProveedorController {
	
	@Autowired
    private ProductosService productoServicio;
	
	@Autowired
	private ProveedorService proveedorServicio;
	
	@GetMapping(value="/proveedor")
	public String listarProveedores(Model model, @Param("search") String search) {
		List<Proveedor> listaProveedores = proveedorServicio.listAll(search);
		model.addAttribute("productos", productoServicio.listAll(null));
		model.addAttribute("proveedores", listaProveedores);
		model.addAttribute("titulo","LISTADO DE PROVEEDORES");
		return "/proveedor/proveedorListar";
	}
	
	
	@GetMapping(value="/proveedores")
	public String crear(Model model) {
		Proveedor pro = new Proveedor();
		model.addAttribute("proveedor", pro);
		model.addAttribute("titulo","REGISTRAR PROVEEDOR");
		return "/proveedor/proveedorRegistrar";
	}
	
	
	@PostMapping(value="/proveedores")
	public String guardar(@Validated Proveedor proveedor, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/proveedor/proveedorRegistrar";
		}
		proveedorServicio.save(proveedor);
		return "redirect:/proveedor";
	}
	
	@GetMapping(value="proveedordetalle/{codigo}")
	public String verDetalles(@PathVariable(value="codigo") String codigo, Model model) {
		Proveedor proveedor = proveedorServicio.get(codigo);
		if (proveedor == null) {
			return "redirect:/proveedor";
		}
		model.addAttribute("titulo","DETALLES DEL PROVEEDOR: "+ proveedor.getEmpresa());
		model.addAttribute("proveedor", proveedor);
		return "/proveedor/proveedorDetalle";
	}
	
	@GetMapping(value="/proveedores/{codigo}")
	public String editar(@PathVariable(value="codigo") String codigo, 
			Map<String, Object> model, Model models) {
		Proveedor proveedor = null;
		if(codigo != null) {
			proveedor = proveedorServicio.get(codigo);
		}else {
			return "redirect:/proveedor";
		}
		models.addAttribute("titulo","EDITAR PROVEEDOR: " + proveedor.getEmpresa());
		model.put("proveedor", proveedor);
		return "/proveedor/proveedorRegistrar";
	}
	
	@GetMapping(value="/proveedor/eliminar/{codigo}")
	public String eliminar(@PathVariable(value="codigo")String codigo) {
		if(codigo != null) {
			proveedorServicio.delete(codigo);
		}
		return "redirect:/proveedor";
	}
}