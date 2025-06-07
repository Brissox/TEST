package com.nspTECH.productos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nspTECH.productos.model.producto;
import com.nspTECH.productos.repository.productoRepository;
import com.nspTECH.productos.services.productoService;

public class productoServiceTest {

    @Mock
    private productoRepository productorepository;


    @InjectMocks
    private productoService productoservice;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarProducto(){
        producto v = new producto();
       v.setCANTIDAD((long) 1123);
        v.setNOMBRE("computador");
        v.setPRECIO((long) 1231231);
        v.setSKU((long) 123112312);
        
        when(productorepository.save(any())).thenReturn(v);

        producto productoGuardado = productoservice.GuardarProducto(v);
        assertEquals(123112312, productoGuardado.getSKU());
        verify(productorepository, times(1)).save(v);
    }

}
