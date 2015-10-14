package com.zuppelli.service;

import com.zuppelli.cake.modelo.dominio.Relleno;
import org.springframework.stereotype.Service;

@Service
public class ServicioRelleno extends ServicioGenerico<Relleno> {
    public ServicioRelleno() {
        super( Relleno.class );
    }
}
