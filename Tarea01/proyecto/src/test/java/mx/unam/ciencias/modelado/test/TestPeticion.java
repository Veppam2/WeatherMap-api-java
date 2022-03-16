package mx.unam.ciencias.modelado.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
/**
 * Define un objeto prubea para ver el funcionamiento de la aplicación
 * @author Carlos Daniel Cortes Jimenez
 * @author Alexis de Jesús Arizmendi López
 * @author Víctor Emiliano Cruz Hérnandez
 * @version 2 15/03/2022
 */
public class TestPeticion extends TestCase{
    /**
     * 
     *  Crea una prueba para la aplicación
     * @param testName nombre de la pruaba
     */
    public TestPeticion( String testName )
    {
        super( testName );
    }

    /**
     * Realiza las pruebas a la aplicación
     * @return el conjunto de pruebas que fueron probadas
     */
    public static Test suite()
    {
        return new TestSuite( TestPeticion.class );
    }

    /**
     * Prueba rigurosa
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
