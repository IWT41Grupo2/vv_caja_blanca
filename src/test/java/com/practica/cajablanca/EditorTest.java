package com.practica.cajablanca;

import com.cajanegra.EmptyCollectionException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class EditorTest {

    private Editor editor;

    @BeforeEach
    void setUp() {
        editor = new Editor();
    }

    @Nested
    class NumPalabrasTest {

        @Test //1-2-3-1
        void inicioMenorCero() {
            assertThrows(IllegalArgumentException.class, () -> {editor.numPalabras(0,10,"PALABRA");});
        }

        @Test //1-2-4-5-1
        void finMayorTam() {
            assertThrows(IllegalArgumentException.class, () -> {editor.numPalabras(1,10,"PALABRA");});
        }

        @Test //1-2-4-6-7-21
        void editorVacio() {
            assertEquals(0, editor.numPalabras(1,0,"PALABRA"));
        }

        @Test //1-2-4-6-7-8-21
        void inicioIgualFin() {
            editor.leerFichero("src/test/resources/unaLinea.txt");
            assertEquals(0, editor.numPalabras(1,1,"PALABRA"));
        }

        @Test //1-2-4-6-7-8-9-10-11-18-8-21
        void lineaVacia() {
            editor.leerFichero("src/test/resources/lineaVacia.txt");
            assertEquals(0, editor.numPalabras(1,1, "PALABRA"));
        }

        @Test //1-2-4-6-7-8-9-10-11-12-13-14-16-11-18-8-21
        void general() {
            editor.leerFichero("src/test/resources/variasLineas.txt");
            assertEquals(4, editor.numPalabras(1,2, "PALABRA"));
        }

    }

    @Nested
    class SustituirPalabraTest {

        String antes, despues;

        @Test //1-2-22
        void editorVacio() throws EmptyCollectionException {
            Editor editorDesp = new Editor();
            editor.leerFichero("src/test/resources/vacio.txt");
            editor.sustituirPalabra("PALABRA", "NUEVAPALABRA");
            editorDesp.leerFichero("src/test/resources/vacio.txt");
            assertEquals(true, compararEditores(editor,editorDesp));
        }

        @Test //1-2-3-4-5-6-7-8-9-10-18-19-20-21-22
        void lineaVacia() throws EmptyCollectionException {
            Editor editorDesp = new Editor();
            editor.leerFichero("src/test/resources/lineaVacia.txt");
            editor.sustituirPalabra("PALABRA", "NUEVAPALABRA");
            editorDesp.leerFichero("src/test/resources/lineaVacia.txt");
            assertEquals(true, compararEditores(editor,editorDesp));
        }

        @Test //1-2-3-4-5-6-7-8-9-10-11-12-16-10-18-19-20-21-22
        void palabraBuscada() throws EmptyCollectionException {
            Editor editorDesp = new Editor();
            editor.leerFichero("src/test/resources/unaPalabraBuscada.txt");
            editor.sustituirPalabra("PALABRA","NUEVAPALABRA");
            editorDesp.leerFichero("src/test/resources/unaNuevaPalabra.txt");
            assertEquals(true, compararEditores(editor,editorDesp));
        }

        @Test //1-2-3-4-5-6-7-8-9-10-11-13-14-16-10-18-19-20-21-22
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

    @Nested
    class mayorLongitudTest {

        @Test //1-2-3-16
        void editorVacio() throws EmptyCollectionException {
            editor.leerFichero("src/test/resources/vacio.txt");
            assertEquals(null, editor.mayorLongitud());
        }

        //Este camino es imposible de implementar
        @Test //1-2-3-4-16
        void lineaVacia() throws EmptyCollectionException {
            editor.leerFichero("src/test/resources/lineaVacia.txt");
            assertEquals(null, editor.mayorLongitud());
        }

        @Test //1-2-3-4-5-6-7-8-9-6-4-16
        void unaPalabra() throws EmptyCollectionException {
            editor.leerFichero("src/test/resources/unaPalabraBuscada.txt");
            assertEquals("PALABRA", editor.mayorLongitud());
        }

        @Test //1-2-3-4-5-6-7-8-10-6-4-16
        void primeraLarga() throws EmptyCollectionException {
            editor.leerFichero("src/test/resources/primeraLarga.txt");
            assertEquals("PALABRALARGA", editor.mayorLongitud());
        }

        @Test //1-2-3-4-5-6-7-8-10-11-6-4-16
        void primeraCorta() throws EmptyCollectionException {
            editor.leerFichero("src/test/resources/primeraCorta.txt");
            assertEquals("PALABRALARGA", editor.mayorLongitud());
        }

    }

}
