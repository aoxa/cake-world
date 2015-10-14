package com.zuppelli.resource.commerce;

import com.sun.jersey.api.spring.Autowire;
import com.zuppelli.cake.modelo.comercio.Carrito;
import com.zuppelli.resource.Recurso;
import com.zuppelli.service.ServicioUsuario;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path( "/commerce/user/{userId}/carrito" )
@Autowire
public class RecursoCarrito extends Recurso<Carrito, Long>{
    @PathParam( "userId" )
    private Long userId;

    private ServicioUsuario servicioUsuario;


    @Override
    public Collection<Carrito> get() {
        return servicioUsuario.get( userId ).getCarritos();
    }

    @Override
    public Carrito get( Long id ) {
        return null;
    }

    @Override
    public Response add( Carrito entity ) {
        return null;
    }

    @Override
    public void delete( Long id ) {

    }

    @Override
    public Response update( Carrito entity ) {
        return null;
    }
}
