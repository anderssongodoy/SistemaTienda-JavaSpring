package com.idat.springboot.sistematienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.idat.springboot.sistematienda.entity.Categoria;
import com.idat.springboot.sistematienda.entity.Producto;
import com.idat.springboot.sistematienda.entity.Proveedor;
import com.idat.springboot.sistematienda.service.CategoriaService;
import com.idat.springboot.sistematienda.service.ProductosService;
import com.idat.springboot.sistematienda.service.ProveedorService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Controller
public class ProductosController {
	
    @Autowired
    private ProductosService productoServicio;
    
    @Autowired
	private CategoriaService categoriaServicio;
    
    @Autowired
	private ProveedorService proveedorServicio;

    @GetMapping(value = "/producto")
    public String listarProductos(Model model, @Param("search") String search) {
    	List<Producto> listaProductos = productoServicio.listAll(search);
    	Producto producto = new Producto();
    	model.addAttribute("message", producto.getCodigo());
        model.addAttribute("productos", listaProductos);
        model.addAttribute("titulo", "LISTADO DE PRODUCTOS");
        return "productos/ver_productos";
    }
    
    @GetMapping(value = "/productos")
    public String crear(Model model) {
    	
    	List<Categoria> listaCategorias = categoriaServicio.listAll(null);
    	List<Proveedor> listaProveedores = proveedorServicio.listAll(null);
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", listaCategorias);
        model.addAttribute("proveedores", listaProveedores);
        model.addAttribute("titulo","REGISTRAR PRODUCTO");
        return "productos/agregar_producto";
    }
    
    @PostMapping(value = "/productos")
    public String guardar(Producto producto, BindingResult bindingResult, RedirectAttributes redirectAttrs, 
    		Model model, @RequestParam("file") MultipartFile foto) {
        if (bindingResult.hasErrors()) {
        	List<Categoria> listaCategorias = categoriaServicio.listAll(null);
        	List<Proveedor> listaProveedores = proveedorServicio.listAll(null);
        	model.addAttribute("categorias", listaCategorias);
            model.addAttribute("proveedores", listaProveedores);
            return "productos/agregar_producto";
        }
        
        if(!foto.isEmpty()) {
			Path directorioImagenes=Paths.get("src//main//resources//static/uploads");
			String rootPath = directorioImagenes.toFile().getAbsolutePath();
			
			try {
				byte[] bytes = foto.getBytes();
				Path rutaCompleta=Paths.get(rootPath+"//"+foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				producto.setFoto(foto.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        
        productoServicio.save(producto);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/productos";
    }

    @GetMapping(value = "/productos/{codigo}")
    public String editar(@PathVariable(value="codigo") String codigo, Map<String, Object> model, Model models) {
    	Producto producto = null;
    	if(codigo != null) {
			producto = productoServicio.get(codigo);
		}else {
			return "redirect:/categoria";
		}
    	List<Categoria> listaCategorias = categoriaServicio.listAll(null);
    	List<Proveedor> listaProveedores = proveedorServicio.listAll(null);
        models.addAttribute("producto", producto);
        models.addAttribute("categorias", listaCategorias);
        models.addAttribute("proveedores", listaProveedores);
        models.addAttribute("titulo","EDITAR PRODUCTO: "+ producto.getDescripcion());
        return "productos/agregar_producto";
    }
    

    @GetMapping(value = "/producto/eliminar/{codigo}")
    public String eliminarProducto(@PathVariable(value="codigo")String codigo, @ModelAttribute Producto producto, RedirectAttributes redirectAttrs) {
    	if(codigo != null) {
			productoServicio.delete(codigo);
		}
		return "redirect:/producto";
    }
    
    @GetMapping(value="/productodetalle/{id}")
	public String ver(@PathVariable(value="id") String id, Map<String, Object> model) {
			Producto producto = productoServicio.get(id);
		if(producto==null) {
			return "redirect:/producto";			
		}
		model.put("producto",producto);
		model.put("titulo", "DETALLE DEL PRODUCTO: "+ producto.getDescripcion());
		return "/productos/productoDetalle";		
	}
    
}
