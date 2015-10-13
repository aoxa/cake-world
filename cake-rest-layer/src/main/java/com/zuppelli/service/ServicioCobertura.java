package com.zuppelli.service;

import com.zuppelli.cake.modelo.dominio.Cobertura;
import org.springframework.stereotype.Service;

@Service
public class ServicioCobertura extends ServicioGenerico<Cobertura> {
    public ServicioCobertura() {
        super( Cobertura.class );
    }
}
