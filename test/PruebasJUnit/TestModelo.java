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

/**
 * Clase de pruebas de la Calculadora.java
 *
 * @autor Antonio López Marín
 * @author Antonio Rodrigo Gea López
 * @version 2.0
 * @see Calculadora
 */
import calculadora.modelo.Calculadora;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test del modelo de la Calculadora, comprueba los valores validos y no validos
 * de cada caso.
 *
 * @see Calculadora
 * @author Antonio López Marín
 * @author Rodrigo Gea López
 */
public class TestModelo {

    public static Calculadora test;

    public TestModelo() {
        test = Calculadora.getInstance();
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

    /**
     * Comprueba que si sumo dos valores como 2 + 2 devuelve 4 y muestra por
     * pantalla el resultado.
     * <dl>
     * <dt>Las clases de equivalencia de el método suma():
     * <ul tipe="circle">
     * <li>válidas: 4
     * <li>no válidas: todos los demás números
     * </ul>
     * </dl>
     *
     * @see Calculadora#suma(double, double)
     */
    @Test
    public void testSuma() {

        //Si sumo suma(2, 2) devuelve 4
        assertEquals("2 + 2 debe salir 4", 4,
                test.suma(2, 2), 1e-6);

        //Si sumo 28+32 devuelve 60
        assertEquals("28 + 32 debe salir 60", 60,
                test.suma(28, 32), 1e-6);
    }

    /**
     * Comprueba que si resta dos valores como 2 - 2 devuelve 0 y muestra por
     * pantalla el resultado.
     * <dl>
     * <dt>Las clases de equivalencia de el metodo resta():
     * <ul tipe="circle">
     * <li>válidas: 0
     * <li>no válidas: todos los demás números
     * </ul>
     * </dl>
     *
     * @see Calculadora#resta(double, double)
     */
    @Test
    public void testResta() {

        //Si resto resta(2, 2) devuelve 0
        assertEquals("2 - 2 debe salir 0", 0,
                test.resta(2, 2), 1e-6);


        assertEquals("5 - 10 debe salir -5", -5,
                test.resta(5, 10), 1e-6);
    }

    /**
     * Comprueba que si hago la multiplicación 3 * 11 devuelve 33 por pantalla.
     * <dl>
     * <dt>Las clases de equivalencia de el método producto():
     * <ul tipe="circle">
     * <li>válidas: 33
     * <li>no válidas: todos los demás números
     * </ul>
     * </dl>
     *
     * @see Calculadora#multiplicacion(double, double)
     */
    @Test
    public void testMultiplicacion() {

        //Si hago el producto producto(3, 11) devuelve 33
        assertEquals("3 x 11 debe salir 33", 33,
                test.multiplicacion(3, 11), 1e-6);

        //Tiene que devolver negativo
        assertEquals("3 x -11 debe salir 33", -33,
                test.multiplicacion(3, -11), 1e-6);
    }

    /**
     * Comprueba que si divido 10 / 2 devuelve 5 por pantalla.
     * <dl>
     * <dt>Las clases de equivalencia de el método división():
     * <ul tipe="circle">
     * <li>válidas: 5
     * <li>no válidas: si el divisor es 0
     * </ul>
     * </dl>
     *
     * @see Calculadora#division(double, double)
     */
    @Test
    public void testDivision() {

        //Si divido division(10, 2) devuelve 5
        assertEquals("10 % 2 debe salir 5", 5.15,
                test.division(10.3, 2), 1e-6);
    }

    /**
     * Comprueba si lanza la excepción ya que le obligo a que se divida por 0.
     *
     * Debe lanzarla.
     *
     * @see Calculadora#division(double, double)
     */
    @Test(expected = ArithmeticException.class)
    public void testExcepcionDivision() {
        test.division(1.3, 0); //Salta exepcion
    }

    /**
     * Comprueba que si hago el resto de 43 MOD 3 devuelve 1 por pantalla.
     * <dl>
     * <dt>Las clases de equivalencia de el método resto():
     * <ul tipe="circle">
     * <li>válidas: 1
     * <li>no válidas: si el divisor es 0
     * </ul>
     * </dl>
     *
     * @see Calculadora#resto(double, double)
     */
    @Test
    public void testResto() {

        //Si realizo el resto de 43 3, devuelve 1
        assertEquals("43 MOD 3 debe salir 1", 1,
                test.resto(43, 3), 1e-6);
    }

    /**
     * Comprueba si lanza la excepción ya que le obligo a que se divida por 0.
     *
     * Debe lanzarla.
     *
     * @see Calculadora#division(double, double)
     */
    @Test(expected = ArithmeticException.class)
    public void testExcepcionResto() {
        test.resto(10, 0); //Salta exepcion
    }

    /**
     * Comprueba que si 2 elevado a 4 devuelve por pantalla 16.
     * <dl>
     * <dt>Las clases de equivalencia de el metodo potencia():
     * <ul tipe="circle">
     * <li>validas: 16
     * <li>no validas: todos los demas numeros
     * </ul>
     * </dl>
     *
     * @see Calculadora#potencia(double, double)
     */
    @Test
    public void testPotencia() {

        //Si realizo la potencia potencia(2, 4) devuelve 16
        assertEquals("2^4 debe salir 16", 16,
                test.potencia(2, 4), 1e-6);
    }

    /**
     * Comprueba que la raiz enesima cumple con las reglas matematicas
     * correspondientes.
     *
     * <p>La raiz enesima</p>
     * <dl>
     * <dt>Las clases de equivalencia de el metodo raiz():
     * <ul tipe="circle">
     * <li>validas: - radicando negativo si indice 1 - todos los demas
     *
     * <li>no validas: - indice es 0 - radicando negativo si indice mayor de 1 -
     * si el indice es menor de 0 - si cualquiera de los dos numeros son
     * infinitos
     * </ul>
     * </dl>
     *
     * @see Calculadora#raiz(double, double)
     */
    @Test
    public void testRaiz() {
        System.out.println("La raiz enesima de exponente 104 y indice 6 es "
                + ":" + test.raiz(104, 6));

        assertEquals("la raiz enesima de 2 y 2 debe salir 1.4142135",
                1.4142135624, test.raiz(2, 2), 1e-6);

        assertEquals("la raiz enesima de -5 y 1 debe salir -5.0", -5.0,
                test.raiz(-5, 1), 1e-6);

        assertEquals("la raiz enesima de 10 y 4 debe salir 1.14869835",
                1.14869835, test.raiz(4, 10), 1e-6);
    }

    //A continuacion ompruebo las clases de equivalencias no validas 
    //lanzando la excepcion.
    /**
     * Compruebo la excepcion del indice 0
     *
     * @see Calculadora#raiz(double, double)
     * @throws ArithmeticException
     */
    @Test(expected = ArithmeticException.class)
    public void testRaizIndiceCero() {
        test.raiz(8, 0);
    }

    /**
     * Compruebo que el radicando es negativo y el indice no es 1
     *
     * @see Calculadora#raiz(double, double)
     * @throws ArithmeticException
     */
    @Test(expected = ArithmeticException.class)
    public void testRaizRadicandoNegativo() {
        test.raiz(-5, 10);
    }

    /**
     * Compruebo que no se puede poner el indice menor de 0
     *
     * @see Calculadora#raiz(double, double)
     * @throws ArithmeticException
     */
    @Test(expected = ArithmeticException.class)
    public void testRaizInfinita() {
        test.raiz(10, -4);
    }

    /**
     * Compruebo que en el seno devuelve el numero correcto.
     *
     * Muestro por linea de comandos el resultado.
     *
     * @see Calculadora#seno(double)
     */
    @Test
    public void testSeno() {
        System.out.println("seno de 6º: " + test.seno(6));

        assertEquals("el seno de 6 debe salir 0,104528",
                0.104528, test.seno(6), 1e-6);
    }

    /**
     * Compruebo que en el coseno devuelve el numero correcto.
     *
     * Muestro por linea de comandos el resultado.
     *
     * @see Calculadora#coseno(double)
     */
    @Test
    public void testCoseno() {
        System.out.println("coseno de 20º: " + test.coseno(20));

        assertEquals("el coseno de 20 debe salir 0,93969262",
                0.93969262, test.coseno(20), 1e-6);
    }

    /**
     * Compruebo que en el coseno devuelve el numero correcto.
     *
     * Muestro por linea de comandos el resultado.
     *
     * @see Calculadora#tangente(double)
     */
    @Test
    public void testTangente() {
        System.out.println("tangente de 60º" + test.tangente(60));

        assertEquals("la tengente de 60 debe salir 1,7320508075059",
                1.73205080756887, test.tangente(60), 1e-6);
    }

    /**
     * Compruebo que todos sus posibles casos funcionan ya que son pocos.
     *
     * Aqui comienzan los Test de las Tablas de la verdad.
     *
     * @see Calculadora#and(boolean, boolean)
     */
    @Test
    public void testAnd() {
        assertTrue("true & true debe ser true", test.and(true, true));

        assertFalse("true & false debe ser false", test.and(true, false));

        assertFalse("false & true debe ser false", test.and(false, true));

        assertFalse("false & false debe ser false", test.and(false, false));
    }

    /**
     * Compruebo todos sus posibles casos funcionan.
     *
     * @see Calculadora#or(boolean, boolean)
     */
    @Test
    public void testOr() {
        assertTrue("true | true debe ser true", test.or(true, true));

        assertTrue("true | false debe ser true", test.or(true, false));

        assertTrue("false | true debe ser true", test.or(false, true));

        assertFalse("false | false debe ser false", test.or(false, false));
    }

    /**
     * Compruebo todos sus posibles casos funcionan.
     *
     * @see Calculadora#xor(boolean, boolean)
     */
    @Test
    public void testXor() {
        assertFalse("true ^ true debe ser false", test.xor(true, true));

        assertTrue("true ^ false debe ser true", test.xor(true, false));

        assertTrue("false ^ true debe ser true", test.xor(false, true));

        assertFalse("false ^ false debe ser false", test.xor(false, false));
    }

    /**
     * Compruebo todos sus posibles casos funcionan.
     *
     * @see Calculadora#not(boolean)
     */
    @Test
    public void testNot() {
        assertFalse("debe devolver false", test.not(true));

        assertTrue("debe devolver true", test.not(false));
    }

    /**
     * Test que compureba el método deDecimalABinario().
     *
     * @see Calculadora#deDecimalABinario(int)
     */
    @Test
    public void testDeDecimalABinario() {

        assertEquals("23 de Decimal a Binario es 10111", "10111",
                test.deDecimalABinario(23));
    }

    /**
     * Test que compureba el método deDecimalAOctal().
     *
     * @see Calculadora#deDecimalAOctal(int)
     */
    @Test
    public void testDeDecimalAOctal() {

        assertEquals("23 de Decimal a Octal es 27", "27",
                test.deDecimalAOctal(23));
    }

    /**
     * Test que compureba el método deDecimalAHexadecimal()
     *
     * @see Calculadora#deDecimalAHexadecimal(int)
     */
    @Test
    public void testDeDecimalAHexadecimal() {

        assertEquals("23 de Decimal a Hexadecimal es 17", "17",
                test.deDecimalAHexadecimal(23));
    }

    /**
     * Test que compureba el método deBinarioADecimal()
     *
     * @see Calculadora#deBinarioADecimal(int)
     */
    @Test
    public void testDeBinarioADecimal() {

        assertEquals("10010100 de Binario a Decimal es 148", 148,
                test.deBinarioADecimal("10010100"));
    }

    /**
     * Test que compureba el método deBinarioAOctal()
     *
     * @see Calculadora#deBinarioAOctal(String)
     */
    @Test
    public void testDeBinarioAOctal() {

        assertEquals("10010100 de Binario a Octal es 224", "224",
                test.deBinarioAOctal("10010100"));
    }

    /**
     * Test que compureba el método deBinarioAHexadecimal()
     *
     * @see Calculadora#deBinarioAHexadecimal(String)
     */
    @Test
    public void testDeBinarioAHexadecimal() {

        assertEquals("10010100 de Binario a Hexadecimal es 94", "94",
                test.deBinarioAHexadecimal("10010100"));
    }

    /**
     * Test que compureba el método deOctalADecimal()
     *
     * @see Calculadora#deOctalADecimal(int)
     */
    @Test
    public void testDeOctalADecimal() {
        assertEquals("2662 de Octal a Decimal es 1458", 1458,
                test.deOctalADecimal("2662"));
    }

    /**
     * Test que compureba el método deOctalABinario()
     *
     * @see Calculadora#deOctalABinario(String)
     */
    @Test
    public void testDeOctalABinario() {

        assertEquals("2662 de Octal a Binario es 10110110010", "10110110010",
                test.deOctalABinario("2662"));
    }

    /**
     * Test que compureba el método deOctalAHexadecimal()
     *
     * @see Calculadora#deOctalAHexadecimal(String)
     */
    @Test
    public void testDeOctalAHexadecimal() {

        assertEquals("2662 de Octal a Hexadecimal es 5B2", "5B2",
                test.deOctalAHexadecimal("2662"));
    }

    /**
     * Test que compureba el método deHexadecimalADecimal()
     *
     * @see Calculadora#deHexadecimalADecimal(int)
     */
    @Test
    public void testDeHexadecimalADecimal() {

        assertEquals("29A de Hexadecimal a Decimal es 666", 666,
                test.deHexadecimalADecimal("29A"));
    }

    /**
     * Test que compureba el método deHexadecimalABinario()
     *
     * @see Calculadora#deHexadecimalABinario(String)
     */
    @Test
    public void testDeHexadecimalABinario() {

        assertEquals("29A de Hexadecimal a Binario es 1010011010", "1010011010",
                test.deHexadecimalABinario("29A"));
    }

    /**
     * Test que compureba el método deHexadecimalAOctal()
     *
     * @see Calculadora#deHexadecimalAOctal(String)
     */
    @Test
    public void testDeHexadecimalAOctal() {

        assertEquals("29A de Hexadecimal a Octal es 1232", "1232",
                test.deHexadecimalAOctal("29A"));
    }
}