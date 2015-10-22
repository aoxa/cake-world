package com.zuppelli.service;

import com.zuppelli.cake.modelo.comercio.Usuario;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuario extends ServicioGenerico<Usuario> {
    public ServicioUsuario() {
        super( Usuario.class );
    }

    /**
     * Devuelve el usuario a partir de un login.
     * @param login - El login del usuario.
     * @return - El usuario.
     */
    public Usuario get( String login ) {
        for ( Usuario usuario : this.get() ) {
            if ( usuario.getLogin().equals( login ) ) {
                return usuario;
            }
        }
        return null;
    }
}
