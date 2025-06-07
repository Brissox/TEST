package com.nspTECH.productos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nspTECH.productos.model.producto;
import com.nspTECH.productos.services.productoService;




@SpringBootTest
public class ProductoServiceIntegrationTest {

    // Esto hace que levante el contexto completo, incluyendo la conexión a la BD

    @Autowired
    private productoService productoservice;

    @Test
    public void testBuscarProductoPorIdReal() {
        Long ID_PRODUCTO = 3L;

        producto prod = productoservice.BuscarUnProducto(ID_PRODUCTO);
        System.out.println("Producto encontrado: " + prod.getNOMBRE());
        assertNotNull(prod); // Verifica que encontró algo
        assertEquals(ID_PRODUCTO, prod.getID_PRODUCTO());

    }

    @Test
    public void testGuardarProductoEnBD() {
        producto nuevoProd = new producto();
        nuevoProd.setNOMBRE("Producto Test Real 2");
        nuevoProd.setSKU(123123123L);
        nuevoProd.setPRECIO(9999L);
        nuevoProd.setCANTIDAD(1L);
      

        producto guardado = productoservice.GuardarProducto(nuevoProd);

        assertNotNull(guardado.getID_PRODUCTO()); // Ya tiene ID generado
        assertEquals("Producto Test Real 2", guardado.getNOMBRE());
    }

}
