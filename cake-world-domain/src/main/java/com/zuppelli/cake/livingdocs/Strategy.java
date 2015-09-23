package com.zuppelli.cake.livingdocs;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * El patrón Estrategia (Strategy) es un patrón de diseño para el desarrollo de software.
 * <br />Se clasifica como patrón de comportamiento porque determina cómo se debe realizar el intercambio de mensajes
 * entre diferentes objetos para resolver una tarea. El patrón estrategia permite mantener un conjunto de algoritmos
 * de entre los cuales el objeto cliente puede elegir aquel que le conviene e intercambiarlo dinámicamente según sus necesidades.
 * <p/>
 * Referencia: Ver <a href="https://es.wikipedia.org/wiki/Strategy_(patr%C3%B3n_de_dise%C3%B1o)">Strategy pattern</a>
 */
@Retention( RetentionPolicy.RUNTIME )
@DesignPattern
public @interface Strategy
{
}
