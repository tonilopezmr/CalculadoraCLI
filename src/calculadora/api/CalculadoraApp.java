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
package calculadora.api;

import calculadora.vista.VistaCLI;

/**
 * Clase principal de la Aplicacion de la calculadroa en linea de comandos.
 *
 *
 * @author Antonio López Marín
 * @author Antonio Rodrigo Gea López
 * @version 2.0
 */
public class CalculadoraApp {

    /**
     * Metodo principal de la aplicacion, inicia la calculadora pasandole la
     * linea de comandos.
     *
     * @param args los argumentos de entrada de la linea de comandos
     * @see VistaCLI#startCalculadora(java.lang.String[])
     */
    public static void main(String[] args) {
        new VistaCLI().startCalculadora(args);
    }
}
