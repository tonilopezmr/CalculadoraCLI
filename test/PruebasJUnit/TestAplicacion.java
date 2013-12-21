/*
 * Copyright 2013 Antonio López Marín and Antonio Rodrigo Gea López.
 * Calculadora CLI 2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package PruebasJUnit;

import calculadora.api.CalculadoraApp;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test de la aplicacion entera de la calculadora en linea de comandos esta
 * prueba pretende pasar por todos los caminos posibles para ver que no se
 * interrumpe la ejecucion o algunos fallos de caminos.
 *
 * Es impracticable recorrer todos los caminos normalmente aunque este al ser un
 * proyecto pequeño puede ser que si se pueda conseguir, pero no es el fin de la
 * cobertura.
 *
 * Realizo la llamada a la aplicacion principal mediante el main y le paso la
 * linea de argumentos.
 *
 * NOTA:
 *
 * Estas pruebas son reutilizables a cualquier calculadora de linea de comandos
 * solo hay que cambiar los parametros de las variables con los comandos de la
 * calculadora a testear.
 *
 *
 * @see CalculadoraApp
 * @see CalculadoraApp#main(java.lang.String[])
 * @author Antonio López Marín
 * @author Rodrigo Gea López
 */
public class TestAplicacion {

    public TestAplicacion() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    /*
     * Configuracion de comandos, de las pruebas de funcionamiento y 
     * cobertura(que va por el maximo numero de lineas de codigo y no 
     * se para la ejecucion por algun error).
     * 
     * Constantes de los comandos de facil acceso.
     * 
     * Si hubiera que cambiar algun comando no habria que buscarlo
     * en el codigo sino cambiarlo aqui.
     * 
     */
    //Opcion de ayuda
    private final String HELP = "-h";
    //OperacionesAritmetricas
    private final String SUMA = "-s";
    private final String RESTA = "-r";
    private final String MULTI = "-m";
    private final String DIVISION = "-d";
    private final String RESTO = "-re";
    private final String POTENCIA = "-p";
    private final String RAIZ = "-ra";
    //OperacionesTrigonometricas
    private final String SENO = "--seno";
    private final String COSENO = "--coseno";
    private final String TANGENTE = "--tangente";
    //Tablas de verdad
    private final String AND = "--and";
    private final String OR = "--or";
    private final String XOR = "--xor";
    private final String NOT = "--not";
    //Propiedades
    private final String PROPERTY = "-D";
    private final String ORIGEN = "convert.source";
    private final String DESTINO = "convert.dest";
    //Valores de las propiedades, estos valores deberian ser siempre estos.
    private final String BINARIO = "bin";
    private final String DECIMAL = "dec";
    private final String OCTAL = "oct";
    private final String HEXADECIMAL = "hex";

    /**
     * Método que comprueba que da los fallos pertinentes en relacion a la
     * aplicacion.
     *
     * Como que no se puedan hacer dos operaciones a la vez, si no le pasas nada
     * por arguentos, que a los comandos le faltan argumentos, etc...
     */
    @Test
    public void fallosAplicacion() {
        System.out.println("==================== FALLOS ====================");
        //No dos operaciones a la vez
        String[] args = {SUMA, "10", "10", RESTA, "3", "3"};
        CalculadoraApp.main(args);

        //No dos operaciones a la vez uno posix largo y otro posix corto
        String[] args1 = {SUMA, "10", "10", TANGENTE, "322"};
        CalculadoraApp.main(args1);

        //Compruebo muestra mensaje de que no hay argumentos
        String[] args2 = {"asdfasd", NOT, "false"};
        CalculadoraApp.main(args2);

        //Compruebo muestra mensaje de que no hay argumentos
        String[] args3 = {""};
        CalculadoraApp.main(args3);


        //Debe saltar error de argumentos del comando suma
        String[] args4 = {SUMA, "3"};
        CalculadoraApp.main(args4);

        //Fallo de tipo de argumentos
        String[] args5 = {TANGENTE, "asdf", "2"};
        CalculadoraApp.main(args5);

        //Fallo de tipo de argumentos en and
        String[] args6 = {NOT, "23"};
        CalculadoraApp.main(args6);

        //Fallo de argumentos nulos
        String[] args7 = {PROPERTY, "false", "false"};
        CalculadoraApp.main(args7);

        //Fallo en la primera propiedad
        String[] args8 = {PROPERTY + "convert=" + BINARIO,
            PROPERTY + DESTINO + "=" + DECIMAL, "1001"};
        CalculadoraApp.main(args8);

        //Fallo en la segunda propiedad
        String[] args9 = {PROPERTY + ORIGEN + "=" + BINARIO,
            PROPERTY + "ERROR" + "=" + DECIMAL, "1001"};
        CalculadoraApp.main(args9);

        //Fallo de valor de la primera propiedad
        String[] args10 = {PROPERTY + ORIGEN + "=error",
            PROPERTY + DESTINO + "=" + OCTAL, "sdffa"};
        CalculadoraApp.main(args10);

        //Fallo de valor de la segunda propiedad
        String[] args11 = {PROPERTY + ORIGEN + "=" + OCTAL,
            PROPERTY + DESTINO + "=error", "54712"};
        CalculadoraApp.main(args11);

        //Fallo porque no puedo convertir a la misma operacion
        String[] args12 = {PROPERTY + ORIGEN + "=" + OCTAL,
            PROPERTY + DESTINO + "=" + OCTAL, "54712"};
        CalculadoraApp.main(args12);

        System.out.println("==============================================");
    }

    /**
     * Metodo para comprobar que funciona las ayudas que hay en la aplicacion.
     *
     */
    @Test
    public void testHelpCobertura() {
        String[] args = {HELP};
        CalculadoraApp.main(args);
        //Muestra la ayuda de ese comando
        String[] args2 = {HELP, PROPERTY + "convert"};
        CalculadoraApp.main(args2);
    }

    /**
     * Apartir de aqui es igual que en los test del modelo, pero a parte
     * compruebo los errores que deberia de dar el sistema para cada comando o
     * en el sistema en general.
     *
     * El siguiente metodo comprueba el test de la suma pero en el programa y le
     * paso una suma que le falta un argumento para que me lo notifique.
     */
    @Test
    public void testSumaCobertura() {
        String[] args = {SUMA, "2", "2"};
        CalculadoraApp.main(args);
    }

    @Test
    public void testRestaCobertura() {
        String[] args = {RESTA, "2", "2"};
        CalculadoraApp.main(args);
    }

    @Test
    public void testMultiplicacionCobertura() {
        String[] args = {MULTI, "3", "11"};
        CalculadoraApp.main(args);
    }

    @Test
    public void testDivisionCobertura() {
        String[] args = {DIVISION, "22", "2"};
        CalculadoraApp.main(args);

        //Sale mensaje de error
        String[] args4 = {DIVISION, "2", "0"};
        CalculadoraApp.main(args4);

    }

    @Test
    public void testRestoCobertura() {
        String[] args = {RESTO, "34", "456"};
        CalculadoraApp.main(args);

        //Sale mensaje de error
        String[] args2 = {RESTO, "34", "0"};
        CalculadoraApp.main(args2);
    }

    @Test
    public void testPotenciaCobertura() {
        String[] args = {POTENCIA, "22", "2"};
        CalculadoraApp.main(args);
    }

    @Test
    public void testRaizCobertura() {
        String[] args = {RAIZ, "23", "2"};
        CalculadoraApp.main(args);

        String[] args2 = {RAIZ, "23", "0"};
        CalculadoraApp.main(args2);

        String[] args3 = {RAIZ, "-23", "5"};
        CalculadoraApp.main(args3);
    }

    @Test
    public void testSenoCobertura() {
        String[] args2 = {SENO, "90"};
        CalculadoraApp.main(args2);
    }

    @Test
    public void testCosenoCobertura() {
        String[] args = {COSENO, "23"};
        CalculadoraApp.main(args);
    }

    @Test
    public void testTangenteCobertura() {
        String[] args = {TANGENTE, "23"};
        CalculadoraApp.main(args);
    }

    /**
     * Compruebo que todos sus posibles casos funcionan ya que son pocos.
     *
     * Aqui comienzan los Test de las Tablas de la verdad.
     *
     * @see calculadora.Calculadora#and(boolean, boolean)
     */
    @Test
    public void testAndCobertura() {
        String[] args2 = {AND, "true", "false"};
        CalculadoraApp.main(args2);
    }

    /**
     * Compruebo todos sus posibles casos funcionan.
     *
     * @see calculadora.Calculadora#or(boolean, boolean)
     */
    @Test
    public void testOrCobertura() {
        String[] args = {OR, "false", "false"};
        CalculadoraApp.main(args);
    }

    /**
     * Compruebo todos sus posibles casos funcionan.
     *
     * @see calculadora.Calculadora#xor(boolean, boolean)
     */
    @Test
    public void testXorCobertura() {
        String[] args = {XOR, "true", "false"};
        CalculadoraApp.main(args);
    }

    /**
     * Compruebo todos sus posibles casos funcionan.
     *
     * @see calculadora.Calculadora#not(boolean)
     */
    @Test
    public void testNotCobertura() {
        String[] args = {NOT, "false", "false"};
        CalculadoraApp.main(args);

        String[] args2 = {NOT, "true"};
        CalculadoraApp.main(args2);
    }

    /**
     * Test que compureba el método deDecimalABinario().
     *
     * @see calculadora.Calculadora#deDecimalABinario(int)
     */
    @Test
    public void testDeDecimalABinarioCobertura() {
        String[] args2 = {PROPERTY + ORIGEN + "=" + DECIMAL,
            PROPERTY + DESTINO + "=" + BINARIO, "10012"};
        CalculadoraApp.main(args2);
    }

    /**
     * Test que compureba el método deDecimalAOctal().
     *
     * @see calculadora.Calculadora#deDecimalAOctal(int)
     */
    @Test
    public void testDeDecimalAOctalCobertura() {
        String[] args2 = {PROPERTY + ORIGEN + "=" + DECIMAL,
            PROPERTY + DESTINO + "=" + OCTAL, "1001101"};
        CalculadoraApp.main(args2);
    }

    /**
     * Test que compureba el método deDecimalAHexadecimal()
     *
     * @see calculadora.Calculadora#deDecimalAHexadecimal(int)
     */
    @Test
    public void testDeDecimalAHexadecimalCobertura() {
        String[] args = {PROPERTY + ORIGEN + "=" + DECIMAL,
            PROPERTY + DESTINO + "=" + HEXADECIMAL, "10012"};
        CalculadoraApp.main(args);
    }

    /**
     * Test que compureba el método deBinarioADecimal()
     *
     * @see calculadora.Calculadora#deBinarioADecimal(int)
     */
    @Test
    public void testDeBinarioADecimalCobertura() {
        String[] args = {PROPERTY + ORIGEN + "=" + BINARIO,
            PROPERTY + DESTINO + "=" + DECIMAL, "1001"};
        CalculadoraApp.main(args);
    }

    /**
     * Test que compureba el método deBinarioAOctal()
     *
     * @see calculadora.Calculadora#deBinarioAOctal(String)
     */
    @Test
    public void testDeBinarioAOctalCobertura() {
        String[] args = {PROPERTY + ORIGEN + "=" + BINARIO,
            PROPERTY + DESTINO + "=" + OCTAL, "1101101"};
        CalculadoraApp.main(args);
    }

    /**
     * Test que compureba el método deBinarioAHexadecimal()
     *
     * @see calculadora.Calculadora#deBinarioAHexadecimal(String)
     */
    @Test
    public void testDeBinarioAHexadecimalCobertura() {
        String[] args = {PROPERTY + ORIGEN + "=" + BINARIO,
            PROPERTY + DESTINO + "=" + HEXADECIMAL, "100111"};
        CalculadoraApp.main(args);
    }

    /**
     * Test que compureba el método deOctalADecimal()
     *
     * @see calculadora.Calculadora#deOctalADecimal(int)
     */
    @Test
    public void testDeOctalADecimalCobertura() {
        String[] args = {PROPERTY + ORIGEN + "=" + OCTAL,
            PROPERTY + DESTINO + "=" + DECIMAL, "54712"};
        CalculadoraApp.main(args);
    }

    /**
     * Test que compureba el método deOctalABinario()
     *
     * @see calculadora.Calculadora#deOctalABinario(String)
     */
    @Test
    public void testDeOctalABinarioCobertura() {
        String[] args = {PROPERTY + ORIGEN + "=" + OCTAL,
            PROPERTY + DESTINO + "=" + BINARIO, "54712"};
        CalculadoraApp.main(args);
    }

    /**
     * Test que compureba el método deOctalAHexadecimal()
     *
     * @see calculadora.Calculadora#deOctalAHexadecimal(String)
     */
    @Test
    public void testDeOctalAHexadecimalCobertura() {
        String[] args = {PROPERTY + ORIGEN + "=" + OCTAL,
            PROPERTY + DESTINO + "=" + HEXADECIMAL, "54712"};
        CalculadoraApp.main(args);
    }

    /**
     * Test que compureba el método deHexadecimalADecimal()
     *
     * @see calculadora.Calculadora#deHexadecimalADecimal(int)
     */
    @Test
    public void testDeHexadecimalADecimalCobertura() {
        String[] args = {PROPERTY + ORIGEN + "=" + HEXADECIMAL,
            PROPERTY + DESTINO + "=" + DECIMAL, "AAA"};
        CalculadoraApp.main(args);
    }

    /**
     * Test que compureba el método deHexadecimalABinario()
     *
     * @see calculadora.Calculadora#deHexadecimalABinario(String)
     */
    @Test
    public void testDeHexadecimalABinarioCobertura() {
        String[] args = {PROPERTY + ORIGEN + "=" + HEXADECIMAL,
            PROPERTY + DESTINO + "=" + BINARIO, "23A"};
        CalculadoraApp.main(args);
    }

    /**
     * Test que compureba el método deHexadecimalAOctal()
     *
     * @see calculadora.Calculadora#deHexadecimalAOctal(String)
     */
    @Test
    public void testDeHexadecimalAOctalCobertura() {
        String[] args = {PROPERTY + ORIGEN + "=" + HEXADECIMAL,
            PROPERTY + DESTINO + "=" + OCTAL, "23A"};
        CalculadoraApp.main(args);
    }
}