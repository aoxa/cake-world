package com.zuppelli.resource;

import com.zuppelli.cake.modelo.Entity;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;

/**
 * Created by pedro.com.zuppelli on 07/10/2015.
 */
public abstract class Recurso<T extends Entity> {
    @Context
    protected SecurityContext securityContext;
    @Context
    protected UriInfo uriInfo;
    @Context
    protected Request request;

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public abstract Collection<T> get();

    @GET
    @Path( "{id}" )
    @Produces( MediaType.APPLICATION_JSON )
    public abstract T get( @PathParam( "id" ) Long id );

    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    public Response add( T entity ) {
        return Response.created( uriInfo.getAbsolutePathBuilder()
                .path( entity.getId().toString() ).build() )
                .entity( entity.getId().toString() ).build();
    }

    @DELETE
    @Path( "{id}" )
    public abstract void delete( @PathParam( "id" ) Long id );

    @PUT
    @Consumes( MediaType.APPLICATION_JSON )
    public abstract Response update( T entity );

}
