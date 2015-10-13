package com.zuppelli.resource.commerce;

import com.zuppelli.cake.modelo.comercio.Usuario;
import com.zuppelli.resource.Recurso;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Created by pedro.zuppelli on 13/10/2015.
 */
@Path( "/commerce/user" )
public class RecursoUsuario extends Recurso<Usuario, Long> {
    @Override
    public Collection<Usuario> get() {
        return null;
    }

    @Override
    public Usuario get( Long id ) {
        return null;
    }

    @Override
    public Response add( Usuario entity ) {
        return null;
    }

    @Override
    public void delete( Long id ) {

    }

    @Override
    public Response update( Usuario entity ) {
        return null;
    }
}
