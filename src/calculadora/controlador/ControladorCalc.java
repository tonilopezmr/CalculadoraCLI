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
package calculadora.controlador;

import calculadora.modelo.Calculadora;

/**
 * ControladorCalc es el controlador de la calculadora que tiene un método por
 * cada uno del modelo {@link Calculadora}
 *
 * Este controlador se comunica con la calculadora para poder realizar
 * operaciones y devolver su resultado.
 *
 * NOTA: Esta clase se podria quitar y que el ControladorComandos se comunicara
 * directamente con el modelo obteniendo un reslutado pero lo hacemos asi la
 * primera vez que hacemos este patron para mostrar mejor como el controlador se
 * reparte el trabajo.
 *
 * @author Antonio López Marín
 * @author Antonio Rodrigo Gea López
 * @see Calculadora
 */
public class ControladorCalc {
    //Me comunico con la calculadora

    private Calculadora calc;

    /**
     * Constructor del controlador de la calculadora que crea una instancia de
     * la calculadora del modelo.
     */
    public ControladorCalc() {
        calc = Calculadora.getInstance();
    }

    /**
     * Me comunico con el método sumar() de la calculadora.
     *
     * @param n1 double primer numero
     * @param n2 double segundo numero
     * @see Calculadora#suma(double, double)
     * @return double el resultado de la operación suma
     */
    public double calcularSuma(double n1, double n2) {
        return calc.suma(n1, n2);
    }

    /**
     * Me comunico con el método restar() de la calculadora.
     *
     * @param n1 double primer numero
     * @param n2 double segundo numero
     * @see Calculadora#resta(double, double)
     * @return double el resultado de la operación resta
     */
    public double calcularResta(double n1, double n2) {
        return calc.resta(n1, n2);
    }

    /**
     * Me comunico con el método multiplicación() de la calculadora.
     *
     * @param n1 double primer numero
     * @param n2 double segundo numero
     * @see Calculadora#multiplicacion(double, double)
     * @return double el resultado de la operación multiplicar
     */
    public double calcularMultipilicacion(double n1, double n2) {
        return calc.multiplicacion(n1, n2);
    }

    /**
     * Me comunico con el método dividir() de la calculadora.
     *
     * @param dividendo double
     * @param divisor double
     * @see Calculadora#division(double, double)
     * @return double el resultado de la operación dividir
     */
    public double calcularDivision(double dividendo, double divisor) {
        return calc.division(dividendo, divisor);
    }

    /**
     * Me comunico con el metodo resto() de la calculadora.
     *
     * @param n1
     * @param n2
     * @see Calculadora#resto(double, double)
     * @return double el resultado de la operacion resto
     */
    public double calcularResto(double n1, double n2) {
        return calc.resto(n1, n2);
    }

    /**
     * Me comunico con el método potencia() de la calculadora.
     *
     * @param base double
     * @param exp double exponente
     * @see Calculadora#potencia(double, double)
     * @return double el resultado de la operación potencia
     */
    public double calcularPotencia(double base, double exp) {
        return calc.potencia(base, exp);
    }

    /**
     * Me comunico con el método raíz() de la calculadora.
     *
     * @param radicando double
     * @param indice int numeros naturales
     * @see Calculadora#raiz(double, int)
     * @return double el resultado de la operación raíz
     */
    public double calcularRaiz(double radicando, int indice) {
        return calc.raiz(radicando, indice);
    }

    /**
     * Me comunico con el método seno() de la calculadora.
     *
     * @param angle el angulo para calcularlo
     * @see Calculadora#seno(double)
     * @return double el resultado de la operación seno
     */
    public double calcularseno(double angle) {
        return calc.seno(angle);
    }

    /**
     * Me comunico con el método coseno() de la calculadora.
     *
     * @param angle el angulo para calcularlo
     * @see Calculadora#coseno(double)
     * @return double el resultado de la operación seno
     */
    public double calcularCoseno(double angle) {
        return calc.coseno(angle);
    }

    /**
     * Me comunico con el método tangente() de la calculadora.
     *
     * @param angle el angulo para calcularlo
     * @see Calculadora#tangente(double)
     * @return double el resultado de la operación tangente
     */
    public double calcularTangente(double angle) {
        return calc.tangente(angle);
    }

    /**
     * Me comunico con el método and() de la calculadora.
     *
     * @param a primer booleano
     * @param b segundo booleano
     * @see Calculadora#and(boolean, boolean)
     * @return boolean del resultado de la tabla de verdad and
     */
    public boolean calcularAnd(boolean a, boolean b) {
        return calc.and(a, b);
    }

    /**
     * Me comunico con el método or() de la calculadora.
     *
     * @param a primer booleano
     * @param b segundo booleano
     * @see Calculadora#or(boolean, boolean)
     * @return boolean del resultado de la tabla de verdad or
     */
    public boolean calcularOr(boolean a, boolean b) {
        return calc.or(a, b);
    }

    /**
     * Me comunico con el método or() de la calculadora.
     *
     * @param a primer booleano
     * @param b segundo booleano
     * @see Calculadora#xor(boolean, boolean)
     * @return boolean del resultado de la tabla de verdad xor
     */
    public boolean calcularXor(boolean a, boolean b) {
        return calc.xor(a, b);
    }

    /**
     * Me comunico con el método not() de la calculadora.
     *
     * @param operador booleano para calcular not
     * @see Calculadora#not(boolean)
     * @return boolean del resultado de la tabla de verdad not
     */
    public boolean calcularNot(boolean operador) {
        return calc.not(operador);
    }

    /**
     * Me comunico con el método deDecimalAHexadecimal() de la calculadora.
     *
     * @param dec long para numeros decimales muy grandes
     * @see Calculadora#deDecimalAHexadecimal(long)
     * @return String del resultado de la operación de conversión.
     */
    public String calcularDecHex(long dec) {
        return calc.deDecimalAHexadecimal(dec);
    }

    /**
     * Me comunico con el método deDecimalAOctal() de la calculadora.
     *
     * @param dec long para numeros decimales muy grandes
     * @see Calculadora#deDecimalAOctal(long)
     * @return String del resultado de la operación de conversión.
     */
    public String calcularDecOct(long dec) {
        return calc.deDecimalAOctal(dec);
    }

    /**
     * Me comunico con el método deDecimalABinario() de la calculadora.
     *
     * @param dec long para numeros decimales muy grandes
     * @see Calculadora#deDecimalABinario(long)
     * @return String del resultado de la operación de conversión.
     */
    public String calcularDecBin(long dec) {
        return calc.deDecimalABinario(dec);
    }

    /**
     * Me comunico con el método deBinarioAHexadecimal() de la calculadora.
     *
     * @param bin
     * @see Calculadora#deBinarioAHexadecimal(java.lang.String)
     * @return String del resultado de la operación de conversión.
     */
    public String calcularBinHex(String bin) {
        return calc.deBinarioAHexadecimal(bin);
    }

    /**
     * Me comunico con el método deBinarioAOctal() de la calculadora.
     *
     * @param bin
     * @see Calculadora#deBinarioAOctal(java.lang.String)
     * @return String del resultado de la operación de conversión.
     */
    public String calcularBinOct(String bin) {
        return calc.deBinarioAOctal(bin);
    }

    /**
     * Me comunico con el método deBinarioADecimal() de la calculadora.
     *
     * @param bin
     * @see Calculadora#deBinarioADecimal(java.lang.String)
     * @return long del resultado de la operación de conversión.
     */
    public long calcularBinDec(String bin) {
        return calc.deBinarioADecimal(bin);
    }

    /**
     * Me comunico con el método deOctalAHexadecimal() de la calculadora.
     *
     * @param oct
     * @see Calculadora#deOctalAHexadecimal(java.lang.String)
     * @return String del resultado de la operación de conversión.
     */
    public String calcularOctHex(String oct) {
        return calc.deOctalAHexadecimal(oct);
    }

    /**
     * Me comunico con el método deOctalABinario() de la calculadora.
     *
     * @param oct
     * @see Calculadora#deOctalABinario(java.lang.String)
     * @return String del resultado de la operación de conversión.
     */
    public String calcularOctBin(String oct) {
        return calc.deOctalABinario(oct);
    }

    /**
     * Me comunico con el método deOctalADecimal() de la calculadora.
     *
     * @param oct
     * @see Calculadora#deOctalADecimal(java.lang.String)
     * @return long del resultado de la operación de conversión.
     */
    public long calcularOctDec(String oct) {
        return calc.deOctalADecimal(oct);
    }

    /**
     * Me comunico con el método deHexadecimalABinario() de la calculadora.
     *
     * @param hex
     * @see Calculadora#deHexadecimalABinario(java.lang.String)
     * @return String del resultado de la operación de conversión.
     */
    public String calcularHexBin(String hex) {
        return calc.deHexadecimalABinario(hex);
    }

    /**
     * Me comunico con el método deHexadecimalAOctal() de la calculadora.
     *
     * @param hex
     * @see Calculadora#deHexadecimalAOctal(java.lang.String)
     * @return String del resultado de la operación de conversión.
     */
    public String calcularHexOct(String hex) {
        return calc.deHexadecimalAOctal(hex);
    }

    /**
     * Me comunico con el método deHexadecimalADecimal() de la calculadora.
     *
     * @param hex
     * @see Calculadora#deHexadecimalADecimal(java.lang.String)
     * @return long del resultado de la operación de conversión.
     */
    public long calcularHexDec(String hex) {
        return calc.deHexadecimalADecimal(hex);
    }
}
