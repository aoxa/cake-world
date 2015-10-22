package com.zuppelli.resource.commerce;

import com.sun.jersey.api.spring.Autowire;
import com.zuppelli.cake.modelo.comercio.Usuario;
import com.zuppelli.resource.Recurso;
import com.zuppelli.service.ServicioUsuario;

import java.util.Collection;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by pedro.zuppelli on 13/10/2015.
 */
@Path( "/commerce/user" )
@Autowire
public class RecursoUsuario extends Recurso<Usuario> {
    private ServicioUsuario servicioUsuario;

    @Override
    public Collection<Usuario> get() {
        return servicioUsuario.get();
    }

    @Override
    public Usuario get( Long id ) {
        return servicioUsuario.get( id );
    }

    @Path( "login/{username}" )
    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public Usuario get( @PathParam( "username" ) String username ) {
        return servicioUsuario.get( username );
    }

    @Override
    public Response add( Usuario entity ) {
        String id = servicioUsuario.store( entity ).getId().toString();

        return Response.created( uriInfo.getAbsolutePathBuilder().path( id ).build() ).entity( id ).build();
    }

    @Override
    public void delete( Long id ) {
        servicioUsuario.remove( id );
    }

    @Override
    public Response update( Usuario entity ) {

        return Response.ok( servicioUsuario.store( entity ) ).build();
    }

    public void setServicioUsuario( ServicioUsuario servicioUsuario ) {
        this.servicioUsuario = servicioUsuario;
    }
}
