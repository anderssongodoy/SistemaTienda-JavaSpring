package com.idat.springboot.sistematienda.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.idat.springboot.sistematienda.service.EmpleadoService;
import com.idat.springboot.sistematienda.service.ProductosService;
import com.idat.springboot.sistematienda.service.RoleService;
import com.idat.springboot.sistematienda.service.UsuarioService;
import com.idat.springboot.sistematienda.entity.Empleado;
import com.idat.springboot.sistematienda.entity.Role;
import com.idat.springboot.sistematienda.entity.Usuario;

@Controller
public class RoleController {

	@Autowired
    private ProductosService productoServicio;
	
	@Autowired
	private RoleService roleServicio;
	
	@Autowired
	private EmpleadoService empleadoServicio;
	
	@Autowired
	private UsuarioService usuarioServicio;
	
	@GetMapping(value="/rol")
	public String listarUsuario(Model model, @Param("search") String search) {
		List<Role> listaRoles = roleServicio.listAll(search);
		model.addAttribute("productos", productoServicio.listAll(null));
		model.addAttribute("role", listaRoles);
		model.addAttribute("titulo","LISTADO DE ROLES");
		return "/rol/rolListar";
	}
	
	@GetMapping(value="/roles")
	public String crear(Model model) {
		Role role = new Role();
		List<Empleado> listaEmpleados = empleadoServicio.listAll(null);
		List<Usuario> listaUsuarios = usuarioServicio.listAll(null);
		model.addAttribute("role", role);
		model.addAttribute("titulo","REGISTRAR ROL");
		model.addAttribute("empleados", listaEmpleados);
		model.addAttribute("usuarios", listaUsuarios);
		return "/rol/rolRegistrar";
	}
	
	@PostMapping(value="/roles")
	public String guardar(Role role) {
		roleServicio.save(role);
		return "redirect:/rol";
	}
	
	@GetMapping(value="/rol/detalle/{codigo}")
	public String verDetalles(@PathVariable(value="codigo") String codigo, Model model) {
		Role role = roleServicio.get(codigo);
		if (role == null) {
			return "redirect:/rol";
		}
		model.addAttribute("role", role);
		model.addAttribute("titulo","DETALLE DEL ROL: " + role.getAuthority());
		return "/rol/rolDetalle";
	}
	
	@GetMapping(value="/rol/eliminar/{codigo}")
	public String eliminar(@PathVariable(value="codigo")String codigo) {
		if(codigo != null) {
			roleServicio.delete(codigo);
		}
		return "redirect:/rol";
	}
	
	@GetMapping(value="/rol/{codigo}")
	public String editar(@PathVariable(value="codigo") String codigo, Map<String, Object> model, Model models) {
		Role role = null;
		List<Empleado> listaEmpleados = empleadoServicio.listAll(null);
		List<Usuario> listaUsuarios = usuarioServicio.listAll(null);
		if(codigo != null) {
			role = roleServicio.get(codigo);
		}else {
			return "redirect:/rol";
		}
		models.addAttribute("empleados", listaEmpleados);
		models.addAttribute("usuarios", listaUsuarios);
		models.addAttribute("titulo","EDITAR ROL: "+ role.getAuthority());
		model.put("role", role);
		return "/rol/rolRegistrar";
	}
}
