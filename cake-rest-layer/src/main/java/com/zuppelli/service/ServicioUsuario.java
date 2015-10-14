package com.zuppelli.service;

import com.zuppelli.cake.modelo.comercio.Usuario;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuario extends ServicioGenerico<Usuario> {
    public ServicioUsuario() {
        super(Usuario.class);
    }
}
