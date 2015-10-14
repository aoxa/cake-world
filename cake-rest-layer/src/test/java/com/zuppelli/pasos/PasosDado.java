package com.zuppelli.pasos;

import com.zuppelli.cake.modelo.comercio.Usuario;
import com.zuppelli.cake.modelo.dominio.Cobertura;
import com.zuppelli.cake.modelo.dominio.Relleno;
import com.zuppelli.helper.CucumberContext;
import com.zuppelli.helper.HttpClientHelper;
import cucumber.api.java.Before;
import cucumber.api.java.es.Dado;
import org.apache.commons.lang.RandomStringUtils;
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

    @Before
    public void reset() {
        context = CucumberContext.getInstance();
        context.reset();
        client = context.getClient();
    }


    @Dado( "^que quiero comprar una torta usando$" )
    public void quiero_comprar() {
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
    public void costo_por_kilo( String porKilo ) throws Throwable {
        HttpResponse response = client.execute( HttpClientHelper
                                                        .postStringEntity( HttpClientHelper.RECURSO_TORTA_POR_KILO,
                                                                           porKilo, MediaType.TEXT_PLAIN ) );

    }

    @Dado("^soy un usuario nuevo$")
    public void soy_un_usuario_nuevo() throws Throwable {
        String login = RandomStringUtils.randomAlphabetic( 8 );
        Usuario usuario = new Usuario();
        usuario.setLogin( login );
        usuario.setEmail( login + "@mail.com" );
        HttpResponse response = client.execute( HttpClientHelper
                                                        .postStringEntity( HttpClientHelper.RECURSO_USUARIO,
                                                                           context.getObjectMapper()
                                                                                   .writeValueAsString( usuario ) ) );
        context.add( CucumberContext.ContentKeys.USER_ID, EntityUtils.toString( response.getEntity() ) );
    }


}
