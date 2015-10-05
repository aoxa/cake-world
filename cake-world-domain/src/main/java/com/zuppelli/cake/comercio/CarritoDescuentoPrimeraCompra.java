package com.zuppelli.cake.comercio;

public class CarritoDescuentoPrimeraCompra extends Carrito
{
    public double getPrecio() {
        return super.getPrecio() * 0.8;
    }
}
