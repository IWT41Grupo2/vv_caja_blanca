package com.practica.cajablanca;

import com.cajanegra.EmptyCollectionException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SustituirPalabraTest {

    private Editor editor;

    @BeforeEach
    void setUp() {
        editor = new Editor();
    }

    @Test //1-2-22
    @DisplayName("Path: 1-2-22")
    void editorVacio() throws EmptyCollectionException {
        Editor editorDesp = new Editor();
        editor.leerFichero("src/test/resources/vacio.txt");
        editor.sustituirPalabra("PALABRA", "NUEVAPALABRA");
        editorDesp.leerFichero("src/test/resources/vacio.txt");
        assertEquals(true, compararEditores(editor,editorDesp));
    }

    @Test //1-2-3-4-5-6-7-8-9-10-18-19-20-21-22
    @DisplayName("Path: 1-2-3-4-5-6-7-8-9-10-18-19-20-21-22")
    void lineaVacia() throws EmptyCollectionException {
        Editor editorDesp = new Editor();
        editor.leerFichero("src/test/resources/lineaVacia.txt");
        editor.sustituirPalabra("PALABRA", "NUEVAPALABRA");
        editorDesp.leerFichero("src/test/resources/lineaVacia.txt");
        assertEquals(true, compararEditores(editor,editorDesp));
    }

    @Test //1-2-3-4-5-6-7-8-9-10-11-12-16-10-18-19-20-21-22
    @DisplayName("Path: 1-2-3-4-5-6-7-8-9-10-11-12-16-10-18-19-20-21-22")
    void palabraBuscada() throws EmptyCollectionException {
        Editor editorDesp = new Editor();
        editor.leerFichero("src/test/resources/unaPalabraBuscada.txt");
        editor.sustituirPalabra("PALABRA","NUEVAPALABRA");
        editorDesp.leerFichero("src/test/resources/unaNuevaPalabra.txt");
        assertEquals(true, compararEditores(editor,editorDesp));
    }

    @Test //1-2-3-4-5-6-7-8-9-10-11-13-14-16-10-18-19-20-21-22
    @DisplayName("Path: 1-2-3-4-5-6-7-8-9-10-11-13-14-16-10-18-19-20-21-22")
    void palabraNoBuscada() throws EmptyCollectionException {
        Editor editorDesp = new Editor();
        editor.leerFichero("src/test/resources/unaPalabraNoBuscada.txt");
        editor.sustituirPalabra("PALABRA","NUEVAPALABRA");
        editorDesp.leerFichero("src/test/resources/unaPalabraNoBuscada.txt");
        assertEquals(true, compararEditores(editor,editorDesp));
    }


    private boolean compararEditores(Editor e1, Editor e2) throws EmptyCollectionException {
        boolean iguales = true;
        int i = 1;
        if(e1.size() == e2.size())
            while(iguales && i<=e1.size()) {
                iguales = e1.getLinea(i).toString().equals(e2.getLinea(i).toString());
                i++;
            }
        else
            iguales = false;
        return iguales;
    }
}
