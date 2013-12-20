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
package calculadora.modelo;

/**
 * Clase Calculadora que implementa métodos de realización de operaciones.
 *
 * Realiza operaciones aritméticas, operaciones trigonométricas, tabla de verdad
 * y conversiones entre binario, octal, decimal y hexadecimal.
 *
 * @author Antonio López Marín
 * @author Antonio Rodrigo Gea López
 * @version 2.0
 */
public class Calculadora {

    //Variable estatica con la ecuacion de los radianes
    //que hace falta para seno, coseno y tangente.
    private final static double RADIANES = 2.0 * Math.PI / 360.0;
    private static Calculadora calculadora;

    /**
     * Constructor vacio y privado utilizando el patron singleton.
     *
     * No siempre es necesario pero por utilizarlo y ir aprendiendo patrones y
     * porque de verdad puede cumplir las caracteristicas de que queremos de que
     * se instancie una unica vez en todo el programa lo utilizamos.
     */
    private Calculadora() {
    }

    /**
     * Metodo que devuelve el objeto calculadora.
     *
     * @return Calculadora
     */
    public static Calculadora getInstance() {
        if (calculadora == null) {
            calculadora = new Calculadora();
        }
        return calculadora;
    }

    /**
     * Método que realiza la suma de los números que se le pasan por párametro.
     *
     * @param n1
     * @param n2
     * @return double
     * @since 1.0
     */
    public double suma(double n1, double n2) {

        return n1 + n2;
    }

    /**
     * Método que realiza la resta de los números que se le pasan por parámetro.
     *
     * @param n1
     * @param n2
     * @return double
     * @since 1.0
     */
    public double resta(double n1, double n2) {

        return n1 - n2;
    }

    /**
     * Método que realiza la multiplicación de los números que se le pasan por
     * parámetro.
     *
     * @param n1
     * @param n2
     * @return double
     * @since 1.0
     */
    public double multiplicacion(double n1, double n2) {

        return n1 * n2;
    }

    /**
     * Método que realiza la división de los números que se le pasan por
     * parámetro.
     *
     * Si el parámtro divisor es igual a cero lanzamos la excepción
     * ArithmeticException para capturarla luego en la vista.
     *
     * @param dividendo
     * @param divisor
     *
     * @return double
     * @throws ArithmeticException
     * @since 1.0
     */
    public double division(double dividendo, double divisor)
            throws ArithmeticException {
        if (divisor == 0) {
            //Lanza excepcion con el mensaje
            throw new ArithmeticException("No se puede dividir por cero.");
        }
        return dividendo / divisor;
    }

    /**
     * Metodo que realiza el resto de dos numeros que se le pasan por parámetro.
     *
     * @param dividendo
     * @param divisor
     * @return double
     * @throws ArithmeticException
     */
    public double resto(double dividendo, double divisor) throws ArithmeticException {
        if (divisor == 0) {
            //Lanza excepcion con el mensaje
            throw new ArithmeticException("El divisor no puede ser 0.");
        }
        return dividendo % divisor;
    }

    /**
     * Metodo que realiza la potencia de un número que le pasamos por parámetro.
     *
     * Al método le pasamos dos números por parámetro, uno es la base(param
     * base) y el otro es el exponente (param exp) al que bamos a elevar la
     * base.
     *
     * @param base
     * @param exp
     * @return double
     * @since 1.0
     */
    public double potencia(double base, double exp) {

        return Math.pow(base, exp);
    }

    /**
     * Metodo que realiza la raiz enesima con dos parametros.
     *
     * @param radicando
     * @param indice
     * @return double
     * @throws ArithmeticException
     */
    public double raiz(double radicando, int indice) throws ArithmeticException {

        //El indice no puede ser menor de 1 porque es un numero natural
        if (indice < 1) {
            //Lo pongo asi porque quiero distinguir si es 0 o no para mostrar 
            //el mensaje
            if (indice == 0) {
                throw new ArithmeticException("El indice de la raiz no "
                        + "puede ser 0.");
            }

            //Si no es 0 muestra que no puede ser negativo.
            throw new ArithmeticException("El indice es un numero natural, "
                    + "nunca puede ser negativo.");
        } else {

            //si es par el índice es mayor de 1 o menor de -1 y el número
            //radicando es negativo, no existe la raiz
            if (indice > 1 && radicando < 0) {
                throw new ArithmeticException("El numero indice no puede "
                        + "ser mayor de uno si el radicando es menor de 0.");
            }

            //Paso el indice a doble para poder hacer la division correctamente
            //Seguira siendo un numero natural.
            double indiceDouble = indice;

            double auxiliar = 1 / indiceDouble;
            /*
             * si es par el índice y el número de dentro es positivo, hay dos 
             * soluciones una positiva y otra negativa si es impar el índice 
             * entonces existe siempre una única solucón sea cual sea el 
             * radicando. Aquí tienes una única solución, con el signo negativo 
             * (si radicando es negativo) o con signo positivo si el (radicando)
             * es positivo.
             */
            return Math.pow(radicando, auxiliar);
        }
    }

    /**
     * Método que calcula el seno pasandole por parametros el angulo.
     *
     * Con el angulo le pasamos la ecuacion de los radianes por el angulo, para
     * que nos devuelva el resultado correcto.
     *
     * @param angle el angulo a calcular
     * @return double
     *
     * @see Math#sin(double)
     */
    public double seno(double angle) {

        return Math.sin(angle * RADIANES);
    }

    /**
     * Método que calcula el coseno pasandole por parametros el angulo.
     *
     * Con el angulo le pasamos la ecuacion de los radianes por el angulo, para
     * que nos devuelva el resultado correcto.
     *
     * @param angle el angulo a calcular
     * @return double
     *
     * @see Math#cos(double)
     */
    public double coseno(double angle) {

        return Math.cos(angle * RADIANES);
    }

    /**
     * Método que calcula el tangente pasandole por parametros el angulo.
     *
     * Con el angulo le pasamos la ecuacion de los radianes por el angulo, para
     * que nos devuelva el resultado correcto.
     *
     * @param angle el angulo a calcular
     * @return double
     *
     * @see Math#tan(double)
     */
    public double tangente(double angle) {

        return Math.tan(angle * RADIANES);
    }

    /**
     * Método and correspondiente a las tablas de verdad &&.
     *
     * AND ejemplos:
     *
     * "true & true debe ser true" "true & false debe ser false" "false & true
     * debe ser false" "false & false debe ser false"
     *
     * @param a primer parámetro booleano
     * @param b segundo parámetro booleano
     * @return boolean
     */
    public boolean and(boolean a, boolean b) {
        return a && b;
    }

    /**
     * Método or correspondiente a las tablas de verdad ||.
     *
     * OR ejemplos:
     *
     * "true | true debe ser true" "true | false debe ser true" "false | true
     * debe ser true" "false | false debe ser false"
     *
     * @param a primer parámetro booleano
     * @param b segundo parámetro booleano
     * @return boolean
     */
    public boolean or(boolean a, boolean b) {
        return a || b;
    }

    /**
     * Método xor correspondiente a las tablas de verdad ^.
     *
     * XOR ejemplos:
     *
     * "true ^ true debe ser false" "true ^ false debe ser true" "false ^ true
     * debe ser true" "false ^ false debe ser false"
     *
     * @param a primer parámetro booleano
     * @param b segundo parámetro booleano
     * @return boolean
     */
    public boolean xor(boolean a, boolean b) {
        return a ^ b;
    }

    /**
     * Método NOT debe devolver lo contrario del booleano que se le pase por
     * parámetros.
     *
     * @param operador
     * @return boolean, lo contrario de operador
     */
    public boolean not(boolean operador) {
        return !operador;
    }

    /**
     * Método que realiza la conversion de Decimal a Binario del número que
     * llega como parámetro de tipo long para numeros grandes.
     *
     * Para ello utilizamos el método toBinaryString() para convertir el número
     * que llega por parámetro a Binario.
     *
     * @param decimal long para numeros decimales muy grandes
     * @return String pasado en binario
     * @see Long#toBinaryString(long)
     */
    public String deDecimalABinario(long decimal) {

        return Long.toBinaryString(decimal);
    }

    /**
     * Método que realiza la conversion de Decimal a Octal del número que llega
     * como parámetro de tipo long para numeros grandes.
     *
     * Para ello utilizamos el método toOctalString() para convertir el número
     * que llega por parámetro a Octal.
     *
     * @param decimal long para numeros decimales muy grandes
     * @return String pasado en octal
     * @see Long#toOctalString(long)
     */
    public String deDecimalAOctal(long decimal) {

        return Long.toOctalString(decimal);
    }

    /**
     * Método que realiza la conversion de Decimal a Hexadecimal del número que
     * llega como parámetro de tipo long para numeros grandes.
     *
     * Para ello utilizamos el método toHexString() para convertir el número que
     * llega por parámetro a Hexadecimal.
     *
     * @param decimal long para numeros decimales muy grandes
     * @return String pasado en hexadecimal
     * @see Long#toHexString(long)
     */
    public String deDecimalAHexadecimal(long decimal) {

        return Long.toHexString(decimal).toUpperCase();
    }

    /**
     * Método que realiza la conversión de Binario a Decimal del parámetro que
     * le llega.
     *
     * Para ello utilizamos el método parseLong() y le pasamos como argumento el
     * string que recive el método y un 2 (base) para que lo pase decimal.
     *
     * @param binario
     * @return long pasado a decimal que puede ser muy largo
     * @see Long#parseLong(java.lang.String)
     */
    public long deBinarioADecimal(String binario) {

        return Long.parseLong(binario, 2);
    }

    /**
     * Método que realiza la conversión de Binario a Octal del parámetro que le
     * llega.
     *
     * Para ello primero pasamos el parámetro que nos llega a decimal el cual
     * almacenamos en una valiable que llamamos decimal. Cuando ya tenemos el
     * número en decimal lo pasamos a octal con toOctalString().
     *
     * @param binario
     * @return String pasado en octal que puede ser muy largo
     * @see Long#parseLong(java.lang.String)
     * @see Long#toOctalString(long)
     */
    public String deBinarioAOctal(String binario) {

        long decimal = Long.parseLong(binario, 2);

        return Long.toOctalString(decimal);
    }

    /**
     * Método que realiza la conversión de Binario a Hexadecimal del parámetro
     * que le llega.
     *
     * Para ello primero pasamos el parámetro que nos llega a decimal el cual
     * almacenamos en una valiable que llamamos decimal. Cuando ya tenemos el
     * número en decimal lo pasamos a Hexadecimal con toHexString().
     *
     * @param binario
     * @return String pasado en hexadecimal que puede ser muy largo
     * @see Long#parseLong(java.lang.String)
     * @see Long#toHexString(long)
     */
    public String deBinarioAHexadecimal(String binario) {

        long decimal = Long.parseLong(binario, 2);

        return Long.toHexString(decimal).toUpperCase();
    }

    /**
     * Método que realiza la conversión de Octal a Decimal del parámetro que le
     * llega.
     *
     * Para ello utilizamos el método parseLong() y le pasamos como argumento el
     * string que recive el método y un 8 (base) para que lo pase a decimal.
     *
     * @param octal
     * @return long pasado a decimal que puede ser muy largo
     * @see Long#parseLong(java.lang.String) )
     */
    public long deOctalADecimal(String octal) {

        return Long.parseLong(octal, 8);
    }

    /**
     * Método que realiza la conversión de Octal a Binario del parámetro que le
     * llega.
     *
     * Para ello primero pasamos el parámetro que nos llega a decimal el cual
     * almacenamos en una valiable que llamamos "decimal". Cuando ya tenemos el
     * número en decimal lo pasamos a binario con toBinaryString().
     *
     * @param octal
     * @return String pasado a binario
     * @see Long#parseLong(java.lang.String)
     * @see Long#toBinaryString(long)
     */
    public String deOctalABinario(String octal) {

        long decimal = Long.parseLong(octal, 8);

        return Long.toBinaryString(decimal);
    }

    /**
     * Método que realiza la conversión de Octal a Hexadecimal del parámetro que
     * le llega.
     *
     * Para ello primero pasamos el parámetro que nos llega a decimal el cual
     * almacenamos en una valiable que llamamos "decimal". Cuando ya tenemos el
     * número en decimal lo pasamos a Hecadecimal con toHexString().
     *
     * @param octal
     * @return String pasado a hexadecimal
     * @see Long#parseLong(java.lang.String)
     * @see Long#toHexString(long)
     */
    public String deOctalAHexadecimal(String octal) {

        long decimal = Long.parseLong(octal, 8);

        return Long.toHexString(decimal).toUpperCase();
    }

    /**
     * Método que realiza la conversión de Hexadecimal a Decimal del parámetro
     * que le llega.
     *
     * Para ello utilizamos el método parseLong() y le pasamos como argumento el
     * string que recive el método y un 16 (base) para que lo pase decimal.
     *
     * @param hex de hexadecimal
     * @return long el resultado en decimal que puede ser muy largo
     * @see Long#parseLong(java.lang.String)
     */
    public long deHexadecimalADecimal(String hex) {
        return Long.parseLong(hex, 16);
    }

    /**
     * Método que realiza la conversión de Hexadecimal a Binario del parámetro
     * que le llega.
     *
     * Para ello primero pasamos el parámetro que nos llega a decimal el cual
     * almacenamos en una valiable que llamamos "decimal". Cuando ya tenemos el
     * número en decimal lo pasamos a Binario con toBinaryString().
     *
     * @param hex de hexadecimal
     * @return String pasado a binario que puede ser muy largo
     * @see Long#parseLong(java.lang.String)
     * @see Long#toBinaryString(long)
     */
    public String deHexadecimalABinario(String hex) {

        long decimal = Long.parseLong(hex, 16);

        return Long.toBinaryString(decimal);
    }

    /**
     * Método que realiza la conversión de Hexadecimal a Octal del parámetro que
     * le llega.
     *
     * Para ello primero pasamos el parámetro que nos llega a decimal el cual
     * almacenamos en una valiable que llamamos "decimal". Cuando ya tenemos el
     * número en decimal lo pasamos a Octal con toOctalString().
     *
     * @param hex de hexadecimal
     * @return String pasado a octal que puede ser muy largo
     * @see Long#parseLong(java.lang.String)
     * @see Long#toOctalString(long)
     */
    public String deHexadecimalAOctal(String hex) {

        long decimal = Long.parseLong(hex, 16);

        return Long.toOctalString(decimal);
    }
}