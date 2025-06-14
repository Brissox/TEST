package com.nspTECH.productos.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.nspTECH.productos.controller.ProductoController;
import com.nspTECH.productos.model.producto;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<producto, EntityModel<producto>> {

    @Override
    public EntityModel<producto> toModel(producto p) {
        return EntityModel.of(
            p,
            linkTo(methodOn(ProductoController.class).BuscarProducto(p.getID_PRODUCTO())).withRel("LINKS"),
            linkTo(methodOn(ProductoController.class).ListarProductos()).withRel("todas-los-productos"),
            linkTo(methodOn(ProductoController.class).EliminarProducto(p.getID_PRODUCTO())).withRel("elimina-una-venta"),
            linkTo(methodOn(ProductoController.class).ActualizarProducto(p.getID_PRODUCTO(), p)).withRel("actualiza-una-venta")
        );
    }
}