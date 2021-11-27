package com.practica.cajablanca;

import com.cajanegra.EmptyCollectionException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class MayorLongitudTest {

    private Editor editor;

    @BeforeEach
    void setUp() {
        editor = new Editor();
    }

    @Test //1-2-3-16
    @DisplayName("Path: 1-2-3-16")
    void editorVacio() throws EmptyCollectionException {
        editor.leerFichero("src/test/resources/vacio.txt");
        assertEquals(null, editor.mayorLongitud());
    }

    //Este camino es imposible de implementar
    @Test //1-2-3-4-16
    @Disabled("Este camino es imposible de implementar")
    @DisplayName("Path: 1-2-3-4-16")
    void lineaVacia() throws EmptyCollectionException {
        editor.leerFichero("src/test/resources/lineaVacia.txt");
        assertEquals(null, editor.mayorLongitud());
    }

    @Test //1-2-3-4-5-6-7-8-9-6-4-16
    @DisplayName("Path: 1-2-3-4-5-6-7-8-9-6-4-16")
    void unaPalabra() throws EmptyCollectionException {
        editor.leerFichero("src/test/resources/unaPalabraBuscada.txt");
        assertEquals("PALABRA", editor.mayorLongitud());
    }

    @Test //1-2-3-4-5-6-7-8-10-6-4-16
    @DisplayName("Path: 1-2-3-4-5-6-7-8-10-6-4-16")
    void primeraLarga() throws EmptyCollectionException {
        editor.leerFichero("src/test/resources/primeraLarga.txt");
        assertEquals("PALABRALARGA", editor.mayorLongitud());
    }

    @Test //1-2-3-4-5-6-7-8-10-11-6-4-16
    @DisplayName("Path: 1-2-3-4-5-6-7-8-10-11-6-4-16")
    void primeraCorta() throws EmptyCollectionException {
        editor.leerFichero("src/test/resources/primeraCorta.txt");
        assertEquals("PALABRALARGA", editor.mayorLongitud());
    }

}
