package com.nspTECH.productos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nspTECH.productos.model.producto;
import com.nspTECH.productos.services.productoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



@RestController
@RequestMapping("/api/v1/Productos")

public class ProductoController {

    @Autowired

    private productoService productoService;

    @GetMapping
    @Operation(summary = "Productos", description = "Operacion que lista todos los productos")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se listaron correctamente las ventas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = producto.class))), 
        @ApiResponse(responseCode = "404", description = "No se encontro ninguna venta", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))


    })

    public ResponseEntity<?> ListarProductos(){
        List<producto> productos = productoService.BuscarTodoProducto();
        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran dato");
        } else {
            return ResponseEntity.ok(productos);
        }
    }
    @GetMapping("/{ID_PRODUCTO}")
    public ResponseEntity<?> BuscarProducto(@PathVariable Long ID_PRODUCTO){

        try {
            producto productoBuscado = productoService.BuscarUnProducto(ID_PRODUCTO);
            return ResponseEntity.ok(productoBuscado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran Producto");
        }
        
    }

    @PostMapping
    public ResponseEntity<?> GuardarProducto(@RequestBody producto productoGuardar){
       try {
            producto productoRegistrar = productoService.GuardarProducto(productoGuardar);
            return ResponseEntity.ok(productoRegistrar);
       } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar el Producto");
       }
    }
    
    @DeleteMapping("/{ID_PRODUCTO}")
        public ResponseEntity<String> EliminarProducto(@PathVariable Long ID_PRODUCTO){
            try {
                producto productoBuscado = productoService.BuscarUnProducto(ID_PRODUCTO);
                productoService.EliminarProducto(ID_PRODUCTO);
                return ResponseEntity.status(HttpStatus.OK).body("Se elimina producto");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no esta registrado");
            }
        }
    @PutMapping("/{ID_PRODUCTO}") //SOLO PERMITE ACTUALIZAR ESCRIBIENDO TODOS LOS DATOS
        
    public ResponseEntity<?> ActualizarProducto(@PathVariable Long ID_PRODUCTO, @RequestBody producto productoActualizar){
        try {
            producto productoActualizado = productoService.BuscarUnProducto(ID_PRODUCTO);
            productoActualizado.setCANTIDAD(productoActualizar.getCANTIDAD());
            productoActualizado.setNOMBRE(productoActualizar.getNOMBRE());
            productoActualizado.setPRECIO(productoActualizar.getPRECIO());
            productoActualizado.setSKU(productoActualizar.getSKU());
            productoService.GuardarProducto(productoActualizado);
            return ResponseEntity.ok(productoActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no esta registrado");
        }
    }
    


}
