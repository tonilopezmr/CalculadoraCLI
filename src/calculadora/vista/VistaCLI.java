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
package calculadora.vista;

import calculadora.controlador.ControladorCLI;
import java.util.Scanner;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;

/**
 * Clase VistaCLI que maneja toda la interfaz visual linea de comandos mostrando
 * los mensajes pertinentes, inicio de la calculadora y carga de las opciones de
 * la linea de comandos.
 *
 * Se comunica con el controlador, y la mayoria de sus metodos son publicos para
 * su facil acceso.
 *
 * @author Antonio López Marín
 * @author Antonio Rodrigo Gea López
 * @version 2.0
 */
public class VistaCLI {

    /*
     * ControladorCLI estatico para poder poner el metodo
     * {@link VistaCLI#startCalculadora} estatico 
     * y poder iniciar la calculadora sin crear instancia.
     * 
     */
    private ControladorCLI controlador;
    //Scanner para escribir linea de comandos
    private Scanner sc;
    //Las opciones de la linea de comando
    private static Options opciones;
    //Contador de errores si se abre la linea de comandos
    private static int cont;

    /**
     * Constructor de la clase VistaCLI.
     *
     * Se inicializa el controlador para comunicarse con el.
     */
    public VistaCLI() {
        controlador = new ControladorCLI(this);

        //Cuando la vista se quiere comunicar con el controlador 
        //cargo las opciones para ser procesadas a continuacion.
        loadCommandLine();
    }

    /**
     * Metodo que inicia la calculadora.
     *
     * Es estatico para que la aplicacion pueda iniciar la vista de la
     * calculadora sin tener que crear un objeto, sino que el objeto de la vista
     * se inicializa a si mismo en este metodo.
     *
     * Lo llama la aplicacion y da la orden de comenzar.
     *
     * @see ControladorCLI#openCalculadora(java.lang.String[])
     * @param args los argumentos de entrada de la linea de comandos
     * @exception NumberFormatException Numeros no validos
     * @exception ArithmeticException Operador incorrecto
     * @exception IllegalArgumentException Argumentos incorrectos.
     */
    public void startCalculadora(String[] args) {
        //Llamo al controlador para que ma abra la calculadora.
        try {
            controlador.openCalculadora(args, opciones);
        } catch (NumberFormatException nfe) {
            mostrarError("Tipo de argumentos no validos.");
        } catch (ArithmeticException | IllegalArgumentException ex) {
            mostrarError(ex.getMessage());
        }
    }

    /**
     * Este metodo realiza la primera etapa de deficion de las opciones de la
     * interfaz de linea de comandos.
     *
     * @see Options
     * @see Options#addOptionGroup(org.apache.commons.cli.OptionGroup)
     */
    private void loadCommandLine() {
        opciones = new Options();

        //help
        opciones.addOption(new Option("h", "help", false,
                "Muestra este mensaje de ayuda."));

        //suma
        opciones.addOption(OptionBuilder.withArgName("num1> <num2")
                .hasArgs(2)
                .withDescription("Calcula la suma dos numeros reales.")
                .create("s"));
        //resta
        opciones.addOption(OptionBuilder.withArgName("num1> <num2")
                .hasArgs(2)
                .withDescription("Calcula la resta dos numeros reales.")
                .create("r"));
        //multiplicacion
        opciones.addOption(OptionBuilder.withArgName("num1> <num2")
                .hasArgs(2)
                .withDescription("Calcula la multiplicacion dos numeros reales.")
                .create("m"));
        //resto
        opciones.addOption(OptionBuilder.withArgName("num1> <num2")
                .hasArgs(2)
                .withDescription("Calcula el resto de dos numeros reales.")
                .create("re"));

        //division
        opciones.addOption(OptionBuilder.withArgName("dividendo> <divisor")
                .hasArgs(2)
                .withDescription("Calcula la division de dos numeros reales.")
                .create("d"));
        //potencia
        opciones.addOption(OptionBuilder.withArgName("base> <exp")
                .hasArgs(2)
                .withDescription("Calcula la potencia "
                + " de base elevado a expontente.")
                .create("p"));
        //raiz
        opciones.addOption(OptionBuilder.withArgName("radicando> <indice")
                .hasArgs(2)
                .withDescription("Calcula la raiz enesima "
                + "de dos numeros, radicando e indice.")
                .create("ra"));

        //Operaciones trigonometricas POSIX largo
        //seno
        opciones.addOption(OptionBuilder.withArgName("angulo")
                .hasArgs()
                .withLongOpt("seno")
                .withDescription("Calcula el seno de un angulo.")
                .create());
        //coseno
        opciones.addOption(OptionBuilder.withArgName("angulo")
                .hasArgs()
                .withLongOpt("coseno")
                .withDescription("Calcula el coseno de un angulo.")
                .create());
        //tangente
        opciones.addOption(OptionBuilder.withArgName("angulo")
                .hasArgs()
                .withLongOpt("tangente")
                .withDescription("Calcula la tangente de un angulo.")
                .create());

        //Tablas de verdad POSIX largo
        //and
        opciones.addOption(OptionBuilder.withArgName("boolean> <boolean")
                .hasArgs(2)
                .withLongOpt("and")
                .withDescription("Calcula la tabla de verdad segun AND.")
                .create());
        //or
        opciones.addOption(OptionBuilder.withArgName("boolean> <boolean")
                .hasArgs(2)
                .withLongOpt("or")
                .withDescription("Calcula la tabla de verdad segun OR.")
                .create());
        //xor
        opciones.addOption(OptionBuilder.withArgName("boolean> <boolean")
                .hasArgs(2)
                .withLongOpt("xor")
                .withDescription("Calcula la tabla de verdad segun XOR.")
                .create());
        //not
        opciones.addOption(OptionBuilder.withArgName("!boolean")
                .hasArgs()
                .withLongOpt("not")
                .withDescription("Calcula la tabla de verdad segun NOT.")
                .create());

        //Conversiones con propiedad 
        //Fuera del grupo para poder poner la ayuda con este comando
        opciones.addOption(OptionBuilder.withArgName("property=value")
                .hasArgs(3)
                .withDescription("Conversion entre binario, octal, decimal "
                + "y hexadecimal, para mas informacion -h o "
                + "--help seguido de -Dconvert.")
                .withValueSeparator()
                .create("D"));
    }

    /**
     * Este metodo se ejecuta si se decidio abrir la calculadora linea de
     * comandos, que le añade el comando de exit para poder salir de la
     * calculadora.
     *
     * @return las opciones con el exit añadido
     */
    public Options loadExitOption() {
        opciones.addOption(new Option(null, "exit", false,
                "Salir de la Calculadora linea de comandos."));
        return opciones;
    }
    
    /**
     * Instancia la clase Scanner cuando se quiere abrir un flujo de entrada
     * de teclado.
     * 
     * Y se inicializa el contador a 0, por si se equivoca el usuario mucho.
     */
    public void loadOutputTeclado(){
        sc = new Scanner(System.in);
        cont = 0; //Inicio el contador
    }
    
    /**
     * Muestra las lineas para poner los comandos si se decidio este modo de la
     * calculadora "abierto".
     *
     * Lo llama el controlador si hace decidio este modo.
     *
     * @see String#split(java.lang.String)
     * @return args nueva Linea de comandos.
     */
    public String[] mostrarLineaCalculadora() {
        System.out.print("Calculadora > ");
        return sc.nextLine().split(" +");
    }

    /**
     * Metodo de salida del programa que decide que mostrar, ayudas, resultado,
     * algun error, etc...
     *
     * @param resultado resultado numerico o bien ayudas etc...
     */
    public void salidaPrograma(String resultado) {
        //Si el resultado es mostrar la ayuda, la muestro
        if (resultado.equals("AYUDA")) {
            mostrarAyuda();
        } else if (resultado.equals("AYUDAPROPERTY")) {
            //Mensaje de ayuda de este comando
            ayudaConversion();
        } else if (resultado.equals("EXIT")) {
            //Le digo al controlador que debe de cerrar la calculadora
            controlador.setExit(true);
        } else if (resultado.length() == 0) {
            //Si no hay resultado, mostrar mensaje
            mostrarError("Expecifique algun comando de la Calculadora CLI.");
        } else {  //Sino, es un resultado de una operacion
            mostrarResultado(resultado);
        }
    }

    /**
     * Muestra la ayuda de la linea de comandos.
     *
     */
    public void mostrarAyuda() {
        String headerandFooter = "Para mas informacion sobre la conversion poner "
                + "-h o --help seguido del comando -Dconvert.\n";
        new HelpFormatter().printHelp(90, "Opciones Calculadora: \n\n",
                headerandFooter, opciones, "\n" + headerandFooter);
    }

    /**
     * Mostrar un pequeño mensaje par aque sepan el comando de la ayuda y la
     * vean cuando quieran.
     *
     */
    private static String miniAyuda() {
        return "Escriba -h o --help para mostrar la ayuda";
    }

    /**
     * Ayuda del comando -D (conversion origen, destino).
     *
     * Muestra la ayuda.
     */
    public void ayudaConversion() {
        System.out.println("Comando -D <property=value>:\n\n "
                + "\t\tComando para conversion entre binario, octal, "
                + "decimal y hexadecimal,\n\t\tse debe poner una propiedad "
                + "seguida de su valor, que pueden ser varios. \n\n\t\t"
                + "Posibles valores de las propiedades: \n\t\t\tbinario -> "
                + "bin \n\t\t\toctal -> oct \n\t\t\tdecimal -> dec \n\t\t\t"
                + "hexadecimal -> hex \n\n\t\tLa conversion se "
                + "expecifica con las propiedades convert.source (origen)"
                + "\n\t\ty convert.dest (destino) seguidas con su "
                + "valor y el operando.\n\n\t\tEjemplos: \n\t\t\t"
                + "-Dconvert.source=dec -Dconvert.dest=hex 5345 \n\t\t\t"
                + "-Dconvert.source=hex -Dconvert.dest=oct AA \n\t\t\t"
                + "-Dconvert.source=bin -Dconvert.dest=dec 101010111");
    }

    /**
     * Muestra el resultado de las operaciones.
     *
     * @param resultado el resultado que se debe mostrar.
     */
    public void mostrarResultado(String resultado) {
        System.out.println("El resultado es: " + resultado);
    }

    /**
     * Muestra los posibles errores que puedan haber en el controlador.
     *
     * Todos los errores pasan por este metodo, para controlar las pequeñas
     * ayudas, saber cuando ocurrio un error.
     *
     * Si se abrio la linea de comandos de la calculadora y se equivoca mas de
     * dos veces le muestra un mensaje para saber que tambien se puede salir.
     *
     * @param error el error que se debe mostrar.
     */
    public void mostrarError(String error) {
        cont++; //Suma el error
        String ayuda = miniAyuda();

        //Si el contador el mayor de dos, añade a la mini ayuda este mensaje
        if (cont > 2) {
            ayuda += " o -exit para salir.";
            cont = 0; //Restablece a 0 los errores
        }

        System.out.println(error + "\n" + ayuda);
    }

    /**
     * Si se abrio la linea de comandos de la calculadora mostramos una
     * bienvenida.
     *
     */
    final public void mostrarBienvenida() {
        System.out.println("Calculadora Command Line Interface 2.0\n");
    }

    /**
     * Al salir del programa mostrar una despedida, si se decidio abrir el modo
     * de la calculadora.
     *
     * Esta despedida no debe de ser cambiada a no ser que se cree una nueva
     * version de la calculadora.
     */
    final public void mostrarDespedida() {
        sc.close(); //Cierro el scanner
        System.out.println("\nbye bye ^.^ Calculadora Command Line "
                + "Interface 2.0");
    }
}