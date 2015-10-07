package com.zuppelli.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by pedro.zuppelli on 07/10/2015.
 */
public abstract class Recurso<T> {
    @Context
    protected UriInfo uriInfo;
    @Context
    protected Request request;

    @GET
    @Produces( MediaType.APPLICATION_JSON)
    public abstract T get();
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public abstract Response add(T entity);
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public abstract void delete(T entity);
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public abstract Response update(T entity);

}
