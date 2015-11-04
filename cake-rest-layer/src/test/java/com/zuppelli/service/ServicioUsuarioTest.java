package com.zuppelli.service;

import com.zuppelli.cake.modelo.comercio.Usuario;
import com.zuppelli.repository.RepositorioGenerico;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.easymock.EasyMock.expect;

public class ServicioUsuarioTest {
    public static final String LOGIN = "test";
    private ServicioUsuario servicioUsuario = new ServicioUsuario();
    private RepositorioGenerico<Usuario> mockRepositorioGenerico;
    private IMocksControl control;
    private Usuario usuario;

    @Before
    public void setUp() {
        control = EasyMock.createControl();
        mockRepositorioGenerico = control.createMock( RepositorioGenerico.class );
        servicioUsuario.setRepositorioGenerico( mockRepositorioGenerico );
        usuario = new Usuario();
        usuario.setId( 10L );
        usuario.setLogin( LOGIN );
    }

    @After
    public void reset() {
        control.reset();
    }

    @Test
    public void testStore() {
        expect( mockRepositorioGenerico.store( usuario ) ).andReturn( usuario );
        control.replay();
        servicioUsuario.store( usuario );
        control.verify();
    }

    @Test
    public void testRemove() {
        mockRepositorioGenerico.remove( usuario.getId(), Usuario.class );
        control.replay();
        servicioUsuario.remove( usuario.getId() );
        control.verify();
    }

    @Test
    public void testGetOne() {
        expect( mockRepositorioGenerico.retrieve( usuario.getId(), Usuario.class ) ).andReturn( usuario );
        control.replay();
        servicioUsuario.get( usuario.getId() );
        control.verify();
    }

    @Test
    public void testGetByName() {
        expect( mockRepositorioGenerico.retrieve( Usuario.class ) ).andReturn( Arrays.asList( usuario ) );
        control.replay();
        servicioUsuario.get( LOGIN );
        control.verify();
    }

    @Test
    public void testGetAll() {
        expect( mockRepositorioGenerico.retrieve( Usuario.class ) ).andReturn( Arrays.asList( usuario ) );
        control.replay();
        servicioUsuario.get();
        control.verify();
    }
}
