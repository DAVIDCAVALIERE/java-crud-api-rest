package com.cavaliere.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cavaliere.apirest.apirest.Entities.Producto;
import com.cavaliere.apirest.apirest.Repositories.ProductoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto id: " + id + " no encontrado" ));
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto){
        Producto productoToUpdate = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto id: " + id + " no encontrado" ));
        productoToUpdate.setNombre(producto.getNombre());
        productoToUpdate.setDescripcion(producto.getDescripcion());
        productoToUpdate.setPrecio(producto.getPrecio());
        return productoRepository.save(productoToUpdate);
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id){
        Producto productoToDelete = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto id: " + id + " no encontrado" ));
        productoRepository.delete(productoToDelete);
        return "Producto id: " + id +  " eliminado correctamente";
    }
     

}
