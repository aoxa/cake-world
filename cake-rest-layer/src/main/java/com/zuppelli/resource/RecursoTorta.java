package com.zuppelli.resource;

import com.sun.jersey.api.spring.Autowire;
import com.zuppelli.cake.modelo.Cobertura;
import com.zuppelli.cake.modelo.Piso;
import com.zuppelli.cake.modelo.Relleno;
import com.zuppelli.cake.modelo.Torta;
import com.zuppelli.service.ServicioTorta;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path( "/torta" )
@Autowire
public class RecursoTorta extends Recurso<com.zuppelli.cake.modelo.Torta>
{
    @Autowired
    private ServicioTorta servicioTorta;

    @Override
    public com.zuppelli.cake.modelo.Torta get() {
        Torta torta = new Torta();
        torta.setCobertura( new Cobertura() );
        Piso piso = new Piso();
        torta.agregarPiso( piso );
        piso.setPrecio( 100d );
        piso.setRelleno( new Relleno() );
        piso.getRelleno().setTipo( "Chocolate" );
        torta.getCobertura().setTipo( "Chispas" );

        return torta;
    }

    public static void main(String[] args ) throws Exception{
        ObjectMapper mapper = new ObjectMapper();

            new JSONObject( "{\"cobertura\":{\"tipo\":\"Chispas\"}}" );

    }

    @Override
    public Response add( Torta entity ) {

        return Response.created( uriInfo.getAbsolutePath() ).entity( servicioTorta.store( entity ) ).build();
    }

    @Override
    public void delete( com.zuppelli.cake.modelo.Torta entity ) {
        servicioTorta.remove( entity );
    }

    @Override
    public Response update( com.zuppelli.cake.modelo.Torta entity ) {
        return Response.created( uriInfo.getAbsolutePath() ).entity( servicioTorta.store( entity ) ).build();
    }

    public void setServicioTorta( ServicioTorta servicioTorta ) {
        this.servicioTorta = servicioTorta;
    }
}
