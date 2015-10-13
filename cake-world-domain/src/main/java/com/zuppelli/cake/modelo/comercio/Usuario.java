package com.zuppelli.cake.modelo.comercio;

import com.zuppelli.cake.modelo.Entity;
import com.zuppelli.livingdocs.ComportamientoCentral;
import com.zuppelli.livingdocs.ConceptoCentral;

import java.util.List;

/**
 * Un usuario que desea utilizar la aplicacion.
 */
@ConceptoCentral
public class Usuario extends Entity {
    private String login;
    private String email;
    private List<Carrito> carritos;

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin( String login ) {
        this.login = login;
    }

    /**
     * Un usuario tiene una lista de carritos. Cada uno indica una compra, no siempre exitosa.
     *
     * @return - una lista de carritos.
     */
    @ComportamientoCentral
    public List<Carrito> getCarritos() {
        return carritos;
    }

    public void setCarritos( List<Carrito> carritos ) {
        this.carritos = carritos;
    }
}