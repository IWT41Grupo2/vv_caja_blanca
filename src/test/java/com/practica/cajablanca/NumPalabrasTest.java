package com.practica.cajablanca;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class NumPalabrasTest {

    private Editor editor;

    @BeforeEach
    void setUp() {
        editor = new Editor();
    }

    @Test //1-2-3-1
    @DisplayName("Path: 1-2-3-1")
    void inicioMenorIgualCero() {
        String esperando = "La línea de inicio no puede ser menor o igual a cero";
        editor.leerFichero("src/test/resources/lineaVacia.txt");
        Exception e = assertThrows(IllegalArgumentException.class, () -> {editor.numPalabras(0,10,"PALABRA");});
        String mensaje = e.getMessage();
        assertTrue(mensaje.contains(esperando));
    }

    @Test //1-2-4-5-1
    @DisplayName("Path: 1-2-4-5-1")
    void finMayorTam() {
        String esperando = "La línea fin no puede ser mayor que el máximo de líneas";
        editor.leerFichero("src/test/resources/lineaVacia.txt");
        Exception e = assertThrows(IllegalArgumentException.class, () -> {editor.numPalabras(1,10,"PALABRA");});
        String mensaje = e.getMessage();
        assertTrue(mensaje.contains(esperando));
    }

    @Test //1-2-4-6-7-21
    @DisplayName("Path: 1-2-4-6-7-21")
    void editorVacio() {
        assertEquals(0, editor.numPalabras(1,0,"PALABRA"));
    }

    @Test //1-2-4-6-7-8-21
    @DisplayName("Path: 1-2-4-6-7-8-21")
    void inicioIgualFin() {
        editor.leerFichero("src/test/resources/unaLinea.txt");
        assertEquals(0, editor.numPalabras(1,1,"PALABRA"));
    }

    @Test //1-2-4-6-7-8-9-10-11-18-8-21
    @DisplayName("Path: 1-2-4-6-7-8-9-10-11-18-8-21")
    void lineaVacia() {
        editor.leerFichero("src/test/resources/lineaVacia.txt");
        assertEquals(0, editor.numPalabras(1,1, "PALABRA"));
    }

    @Test //1-2-4-6-7-8-9-10-11-12-13-14-16-11-18-8-21
    @DisplayName("Path: 1-2-4-6-7-8-9-10-11-12-13-14-16-11-18-8-21")
    void general() {
        editor.leerFichero("src/test/resources/variasLineas.txt");
        assertEquals(4, editor.numPalabras(1,2, "PALABRA"));
    }

}
