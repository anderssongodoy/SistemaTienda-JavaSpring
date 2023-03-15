package com.idat.springboot.sistematienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.idat.springboot.sistematienda.entity.Cliente;
import com.idat.springboot.sistematienda.entity.Empleado;
import com.idat.springboot.sistematienda.entity.MetodoPago;
import com.idat.springboot.sistematienda.entity.Producto;
import com.idat.springboot.sistematienda.entity.ProductoParaVender;
import com.idat.springboot.sistematienda.entity.ProductoVendido;
import com.idat.springboot.sistematienda.entity.Venta;
import com.idat.springboot.sistematienda.repository.ProductosRepository;
import com.idat.springboot.sistematienda.repository.ProductosVendidosRepository;
import com.idat.springboot.sistematienda.repository.VentasRepository;
import com.idat.springboot.sistematienda.service.ClienteService;
import com.idat.springboot.sistematienda.service.EmpleadoService;
import com.idat.springboot.sistematienda.service.MetodoService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/vender")
public class VenderController {
	
    @Autowired
    private ProductosRepository productosRepository;
    
    @Autowired
    private VentasRepository ventasRepository;
    
    @Autowired
    private ProductosVendidosRepository productosVendidosRepository;
    
    @Autowired
    private EmpleadoService empleadoServicio;
    
    @Autowired
    private ClienteService clienteServicio;
    
    @Autowired
    private MetodoService metodoServicio;

    @PostMapping(value = "/quitar/{indice}")
    public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request) {
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
            carrito.remove(indice);
            this.guardarCarrito(carrito, request);
        }
        return "redirect:/vender/";
    }
   
    private void limpiarCarrito(HttpServletRequest request) {
        this.guardarCarrito(new ArrayList<>(), request);
    }

    @GetMapping(value = "/limpiar")
    public String cancelarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        this.limpiarCarrito(request);
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta cancelada")
                .addFlashAttribute("clase", "info");
        return "redirect:/vender/";
    }

    @PostMapping(value = "/terminar")
    public String terminarVenta(Venta venta, HttpServletRequest request, RedirectAttributes redirectAttrs) {
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);

        if (carrito == null || carrito.size() <= 0) {
            return "redirect:/vender/";
        }
        Venta v = ventasRepository.save(venta);

        for (ProductoParaVender productoParaVender : carrito) {

            Producto p = productosRepository.findFirstByCodigo(productoParaVender.getCodigo());
            if (p == null) continue; 
            p.restarStock(productoParaVender.getCantidad());
            productosRepository.save(p);
            ProductoVendido productoVendido = new ProductoVendido(productoParaVender.getCantidad(), productoParaVender.getVenta(),
            		productoParaVender.getDescripcion(),v);
            productosVendidosRepository.save(productoVendido);
        }

        this.limpiarCarrito(request);
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta realizada correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/vender/";
    }

    @GetMapping(value = "/")
    public String interfazVender(Model model, HttpServletRequest request) {
    	List<MetodoPago> listaPagos = metodoServicio.listAll(null);
    	List<Empleado> listaEmpleados = empleadoServicio.listAll(null);
    	List<Cliente> listaClientes = clienteServicio.listAll(null);
    	model.addAttribute("venta", new Venta());
        model.addAttribute("producto", new Producto());
        float total = 0;
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        for (ProductoParaVender p: carrito) total += p.getTotal();
        model.addAttribute("titulo", "REGISTRAR VENTA");
        model.addAttribute("total", total);
        model.addAttribute("productos", productosRepository.findAll());
        model.addAttribute("pagos", listaPagos);
        model.addAttribute("empleados", listaEmpleados);
        model.addAttribute("clientes", listaClientes);
        return "vender/vender";
    }

    private ArrayList<ProductoParaVender> obtenerCarrito(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
		ArrayList<ProductoParaVender> carrito = (ArrayList<ProductoParaVender>) request.getSession().getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        return carrito;
    }

    private void guardarCarrito(ArrayList<ProductoParaVender> carrito, HttpServletRequest request) {
        request.getSession().setAttribute("carrito", carrito);
    }

    @PostMapping(value = "/agregar")
    public String agregarAlCarrito(@ModelAttribute Producto producto, HttpServletRequest request, 
    		RedirectAttributes redirectAttrs, Model model) {
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        Producto productoBuscadoPorCodigo = productosRepository.findFirstByCodigo(producto.getCodigo());

        if (productoBuscadoPorCodigo.sinStock()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El producto está agotado")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/vender/";
        }
        boolean encontrado = false;
        for (ProductoParaVender productoParaVenderActual : carrito) {
            if (productoParaVenderActual.getCodigo().equals(productoBuscadoPorCodigo.getCodigo())) {
                productoParaVenderActual.aumentarCantidad();
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            carrito.add(new ProductoParaVender(productoBuscadoPorCodigo.getCodigo(), productoBuscadoPorCodigo.getProveedor(), 
            		productoBuscadoPorCodigo.getCategoria(), productoBuscadoPorCodigo.getDescripcion(), 
            		productoBuscadoPorCodigo.getCosto(), productoBuscadoPorCodigo.getVenta(), 
            		productoBuscadoPorCodigo.getMinimo(), productoBuscadoPorCodigo.getActual(), 
            		productoBuscadoPorCodigo.getFoto(), 1));
        }
        this.guardarCarrito(carrito, request);
        return "redirect:/vender/";
    }
}
