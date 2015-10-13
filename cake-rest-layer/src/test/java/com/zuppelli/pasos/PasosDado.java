package com.zuppelli.pasos;

import com.zuppelli.cake.modelo.Cobertura;
import com.zuppelli.cake.modelo.Relleno;
import com.zuppelli.helper.CucumberContext;
import com.zuppelli.helper.HttpClientHelper;
import cucumber.api.java.es.Dado;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * Created by pedro.zuppelli on 13/10/2015.
 */
public class PasosDado {
    private static Logger logger = Logger.getLogger( PasosDado.class.getName() );
    CucumberContext context;
    HttpClient client;

    @Dado( "^que quiero comprar una torta usando$" )
    public void quiero_comprar() {
        context = CucumberContext.getInstance();
        context.reset();
        client = context.getClient();
    }

    @Dado( "^una cobertura de '(.+)' con precio '(\\d+)'$" )
    public void cobertura_con_precio( String cobertura, Long coberturaPrecio ) throws Throwable {
        Cobertura cbt = new Cobertura();
        cbt.setTipo( cobertura );
        cbt.setPrecio( coberturaPrecio );

        HttpResponse response = client.execute( HttpClientHelper.postStringEntity( HttpClientHelper.RECURSO_COBERTURA,
                                                                                   context.getObjectMapper()
                                                                                           .writeValueAsString( cbt ) ) );
        cbt.setId( Long.parseLong( EntityUtils.toString( response.getEntity() ) ) );
        context.add( CucumberContext.ContentKeys.COBERTURA_BASE, cbt );
    }

    @Dado( "^un relleno de '(.+)' con precio '(\\d+)'$" )
    public void relleno_con_precio( String relleno, Long rellenoPrecio ) throws Throwable {
        Relleno rel = new Relleno();
        rel.setTipo( relleno );
        rel.setPrecio( rellenoPrecio );

        HttpResponse response = client.execute( HttpClientHelper.postStringEntity( HttpClientHelper.RECURSO_RELLENO,
                                                                                   context.getObjectMapper()
                                                                                           .writeValueAsString( rel ) ) );
        rel.setId( Long.parseLong( EntityUtils.toString( response.getEntity() ) ) );
        context.add( CucumberContext.ContentKeys.RELLENO_BASE, rel );
    }

    @Dado( "^un costo por kilo de '(.+)'$" )
    public void i_am_a_user_using_cobertura_and_relleno( String porKilo ) throws Throwable {
        HttpResponse response = client.execute( HttpClientHelper
                                                        .postStringEntity( HttpClientHelper.RECURSO_TORTA_POR_KILO,
                                                                           porKilo, MediaType.TEXT_PLAIN ) );

    }

}
