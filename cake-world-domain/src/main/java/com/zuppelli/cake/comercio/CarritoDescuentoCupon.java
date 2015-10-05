package com.zuppelli.cake.comercio;

public class CarritoDescuentoCupon extends Carrito
{
    private Cupon cupon;

    public double getPrecio() {
        return super.getPrecio() * cupon.getDescuento();
    }

}
