package com.zuppelli.resource.commerce;

import com.sun.jersey.api.spring.Autowire;
import com.zuppelli.cake.modelo.comercio.Carrito;
import com.zuppelli.resource.Recurso;
import com.zuppelli.service.ServicioCarrito;
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
    private ServicioCarrito servicioCarrito;


    @Override
    public Collection<Carrito> get() {
        return servicioUsuario.get( userId ).getCarritos();
    }

    @Override
    public Carrito get( Long id ) {
        return servicioCarrito.get( id );
    }

    @Override
    public Response add( Carrito entity ) {
        String id = servicioCarrito.store( entity ).getId().toString();
        return Response.created( uriInfo.getAbsolutePathBuilder().path( id ).build(  ) ).entity( id ).build();
    }

    @Override
    public void delete( Long id ) {
        servicioCarrito.remove( id );
    }

    @Override
    public Response update( Carrito entity ) {
        String id = servicioCarrito.store( entity ).getId().toString();
        return Response.ok( uriInfo.getAbsolutePathBuilder().path( id ).build(  ) ).entity( id ).build();
    }

    public void setServicioCarrito( ServicioCarrito servicioCarrito ) {
        this.servicioCarrito = servicioCarrito;
    }

    public void setServicioUsuario( ServicioUsuario servicioUsuario ) {
        this.servicioUsuario = servicioUsuario;
    }
}
