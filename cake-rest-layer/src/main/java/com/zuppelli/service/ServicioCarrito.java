package com.zuppelli.service;

import com.zuppelli.cake.modelo.comercio.Carrito;
import org.springframework.stereotype.Service;

@Service
public class ServicioCarrito extends ServicioGenerico<Carrito> {
    public ServicioCarrito() {
        super(Carrito.class);
    }
}
