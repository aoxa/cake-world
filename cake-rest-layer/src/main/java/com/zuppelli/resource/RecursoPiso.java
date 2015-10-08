package com.zuppelli.resource;

import com.sun.jersey.api.spring.Autowire;
import com.zuppelli.cake.modelo.Piso;
import com.zuppelli.cake.modelo.Relleno;
import com.zuppelli.service.ServicioTorta;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path( "/torta/{tortaId}/piso" )
@Autowire
public class RecursoPiso extends Recurso<Piso, Long>{

    @PathParam( "tortaId" ) private Long tortaId;
    private ServicioTorta servicioTorta;

    @Override
    public Collection<Piso> get() {
        return null;
    }

    @Override
    public Piso get( Long id ) {
        Piso piso = new Piso();
        Relleno relleno = new Relleno();
        relleno.setTipo( "Chispas" );
        piso.setRelleno( relleno );
        piso.setPrecio( 123 );
        return piso;
    }

    @Override
    public Response add( Piso entity ) {
        this.servicioTorta.agregarPiso( tortaId, entity );
        return Response.ok().build();
    }

    @Override
    public void delete( Long id ) {

    }

    @Override
    public Response update( Piso entity ) {
        return null;
    }

    public void setTortaId(Long tortaId) {
        this.tortaId = tortaId;
    }
}
