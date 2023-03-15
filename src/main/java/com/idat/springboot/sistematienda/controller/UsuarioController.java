package com.idat.springboot.sistematienda.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.idat.springboot.sistematienda.entity.Empleado;
import com.idat.springboot.sistematienda.entity.Usuario;
import com.idat.springboot.sistematienda.service.EmpleadoService;
import com.idat.springboot.sistematienda.service.ProductosService;
import com.idat.springboot.sistematienda.service.UsuarioService;
import com.idat.springboot.sistematienda.service.UsuarioServicio;
import com.idat.springboot.sistematienda.util.UsuarioRegistroDTO;

@Controller
public class UsuarioController {

	@Autowired
    private ProductosService productoServicio;
	
	@Autowired
	private UsuarioService usuarioServicio;
	
	@Autowired
	private UsuarioServicio userServicio;
	
	@Autowired
	private EmpleadoService empleadoServicio;
	
	public UsuarioController(UsuarioServicio userServicio) {
		super();
		this.userServicio = userServicio;
	}

	@GetMapping(value="/usuario")
	public String listarUsuario(Model model, @Param("search") String search) {
		List<Usuario> listaUsuarios = usuarioServicio.listAll(search);
		model.addAttribute("productos", productoServicio.listAll(null));
		model.addAttribute("usuarios", listaUsuarios);
		model.addAttribute("titulo","LISTADO DE USUARIOS");
		return "/usuario/usuarioListar";
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
		return new UsuarioRegistroDTO();
	}
	
	@GetMapping(value="/usuarios")
	public String crear(@Valid Model model) {
		Usuario user = new Usuario();
		List<Empleado> listaEmpleados = empleadoServicio.listAll(null);
		model.addAttribute("usuario", user);
		model.addAttribute("titulo","REGISTRAR USUARIO");
		model.addAttribute("empleados", listaEmpleados);
		return "/usuario/usuarioRegistrar";
	}
	
	@PostMapping(value="/usuarios")
	public String guardar(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO , Usuario usuario) {
		userServicio.guardar(registroDTO);
//		usuarioServicio.save(usuario);
		return "redirect:/usuario";
	}
	
	@GetMapping(value="/usuario/detalle/{codigo}")
	public String verDetalles(@PathVariable(value="codigo") String codigo, Model model) {
		Usuario usuario = usuarioServicio.get(codigo);
		if (usuario == null) {
			return "redirect:/usuario";
		}
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo","DETALLE DEL USUARIO: " + usuario.getUsername());
		return "/usuario/usuarioDetalle";
	}
	
	@GetMapping(value="/usuario/{codigo}")
	public String editar(@PathVariable(value="codigo") String codigo, Map<String, Object> model, Model models) {
		Usuario usuario = null;
		List<Empleado> listaEmpleados = empleadoServicio.listAll(null);
		if(codigo != null) {
			usuario = usuarioServicio.get(codigo);
		}else {
			return "redirect:/usuario";
		}
		models.addAttribute("empleados", listaEmpleados);
		models.addAttribute("titulo","EDITAR USUARIO: "+ usuario.getUsername());
		model.put("usuario", usuario);
		return "/usuario/usuarioRegistrar";
	}
	
	@GetMapping(value="/usuario/eliminar/{codigo}")
	public String eliminar(@PathVariable(value="codigo") String codigo) {
		if(codigo != null) {
			usuarioServicio.get(codigo);
		}
		return "redirect:/usuario";
	}
}
