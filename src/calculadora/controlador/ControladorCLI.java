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

import calculadora.vista.VistaCLI;
import java.util.StringTokenizer;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.cli.UnrecognizedOptionException;

/**
 * ControladorCLI es el controlador principal que esta dividido en otras dos
 * clases para evitar el malo olor de <b>"clase dios"</b> y para que cada
 * controlador controle las diferentes relaciones de la aplicacion.
 *
 * Este controlador inicia la lectura del cmd y se comunica con la vista para
 * mostrar los mensajes pertinentes.
 *
 * ControladorCLI se comunica con ControladorComandos que busca el comando y
 * este se comunica con el ControladorCalc para que este se comunique con el
 * modelo para obtener el resultado y volver a este controlador para poder
 * mostrar el resultado mediante la comunicacion con la vista.
 *
 * La calculadora tiene una funcionalidad que si no se le pasan argumentos por
 * la linea de comandos quiere decir que quieren abrir una linea de comandos de
 * la calculadora para realizar muchas operaciones seguidas, sin tener que
 * llamar al ejecutable cada dos por tres.
 *
 * Controladores:
 *
 * @see ControladorCLI Controlador de las relaciones con la vista
 * @see ControladorCalc Controlador de las relaciones con la calculadora
 * @see ControladorComandos Controlador de las relaciones con los comandos
 *
 * @author Antonio López Marín
 * @author Antonio Rodrigo Gea López
 * @version 2.0
 */
public class ControladorCLI {

    //Me comunico con la vistaCLI
    private VistaCLI vista;
    //Las opciones de la linea de comando
    private Options opciones;
    /*
     * Para salir de la linea de comandos de la calculadora si es que
     * la han abierto.
     */
    private boolean exit;
    /*
     * Me comunico con el controlador de busqueda de comandos
     * lo declaro al principio de la clase para que sea visible
     * la comunicacion
     */
    private ControladorComandos busqueda;

    /**
     * Se recoge la vista para comunicarme con ella y creo una instancia de la
     * calculadora, para poder enviar y recoger los resultados.
     *
     * Inicializa los atributos, carga las opciones de lineas de comandos.
     *
     * @param vista VistaCLI
     */
    public ControladorCLI(VistaCLI vista) {
        this.vista = vista;
    }

    /**
     * "Encendemos" la calculadora y segun se reciban parametros de primeras o
     * no "abrimos" completamente la calculadora o solo realizamos la
     * operaciondirectamente.
     *
     * Recibe por parametros los argumentos de entrada y las opciones de la
     * interfaz de linea de comandos para que trabaje el controlador con ellas.
     *
     * Ej:
     *
     * Si hacemos un <b>java CalculadoraApp -s 10 10"</b> solo se ejecutara esa
     * suma y mostrara el resultado.
     *
     * Pero si solo ponemos <b>java CalculadoraApp</b> abrimos completamente la
     * calculadora y podemos realizar muchas operaciones, sin tener que estar
     * poniendo <b>java CalculadoraApp</b> cada vez que queremos realizar una
     * operacion.
     *
     * Esto esta pensado por si solo quieres saber una cosa en un momento dado o
     * quieres realizar muchas operaciones seguidas.
     *
     * Lanza las excepciones para que las capture la vista.
     *
     * @see ControladorCLI#abrirLineaComandos()
     * @param args los argumentos de entrada de la linea de comandos
     * @param opciones las opciones que crea la vista las pasa al controlador
     * @throws IllegalArgumentException argumentos erroneos
     * @throws NumberFormatException Tipo de argumentos erroneos
     */
    public void openCalculadora(String[] args, Options opciones)
            throws IllegalArgumentException, NumberFormatException {
        //Si no recibe argumentos abrimos completamente la calculadora
        if (args.length == 0) {
            //Cargo las opciones con el exit
            this.opciones = vista.loadExitOption();
            //abro la linea de comandos de la calculadora
            vista.mostrarBienvenida();
            abrirLineaComandos();
        } else {
            this.opciones = opciones; //Cargo las opciones por default
            leerComando(args);
        }
    }

    /**
     * Metodo realiza la segunda etapa de analisis del commons cli de Apache.
     *
     * Lee la linea de comandos y una vez la lee con exito pasa el comando a que
     * busque la funcion de ese comando.
     *
     * Si los argumentos recibidos son mayores de tres no conviene parsear, ya
     * que nunca deveria ser mayor de 3.
     *
     * Controlo aqui la excepcion para poder personalizarla y poder mostrar la
     * informacion correcta por pantalla con el comando equivocado.
     *
     * @see PosixParser POSIX
     * @see CommandLineParser
     * @see CommandLine
     * @exception MissingArgumentException error de argumentos de comandos
     * @exception UnrecognizedOptionException no reconoce el comando
     * @exception ParseException error al parsear los comandos
     * @param args Linea de comandos
     * @throws IllegalArgumentException error argumentos de java o programa
     * @throws NumberFormatException error de tipo de argumentos
     */
    private void leerComando(String[] args) throws IllegalArgumentException,
            NumberFormatException {
        try {
            //Si hay mas de 3 argumentos directamente error, no conviene parsear
            if (args.length > 3) {
                notificarError("Demasiados comandos o argumentos, solo se puede "
                        + "realizar una operacion cada vez.");
            } else {
                CommandLineParser parser = new PosixParser();
                CommandLine cmdLine = parser.parse(opciones, args);
                buscarComando(cmdLine);
            }
        } catch (MissingArgumentException mae) {
            //Si la opcion es de posix largo muestro el nombre del comando largo
            //Sino muestro el posix corto de error.
            notificarError(mae.getOption().hasLongOpt() ? "El comando '--"
                    + mae.getOption().getLongOpt()
                    + "' necesita mas argumentos." : "El comando '-"
                    + mae.getOption().getOpt()
                    + "' necesita mas argumentos.");
        } catch (UnrecognizedOptionException uoe) {
            notificarError("'" + uoe.getOption() + "' no se reconoce como "
                    + "comando interno del programa.");
        } catch (ParseException pe) {
            notificarError(pe.getMessage());
        }
    }

    /**
     * Metodo que llama ControladorComandos para realizar ultima etapa del
     * commons cli de Apache de interrogatorios.
     *
     * La llamada devuelve el resultado que se le envia a la vista para que
     * decida tiene que hacer y finalice el programa.
     *
     * @param cmdLine la linea de comandos parseada
     * @throws IllegalArgumentException error argumentos de entrada
     * @throws NumberFormatException error tipo de argumentos
     */
    private void buscarComando(CommandLine cmdLine)
            throws IllegalArgumentException, NumberFormatException,
            ParseException {
        //Me comunico con el controlador de la busqueda
        busqueda = new ControladorComandos(cmdLine);
        //Inicio la busqueda de comandos y recogo el resultado
        String resultado = busqueda.busquedaDeComandos();

        vista.salidaPrograma(resultado);
    }

    /**
     * Metodo para editar la salida del programa, para saber cuando debe salir.
     *
     * @param exit boolean
     */
    public void setExit(boolean exit) {
        this.exit = exit;
    }

    /**
     * Compongo la linea de comandos en un array de String para poder poder leer
     * los comandos.
     *
     * En un momento dado se iba a utilizar para separar por espacios una cadena
     * para poder leer los comandos.
     *
     * Pero se quedo obsoleta porque se hizo con un metodo de java.
     *
     * @see VistaCLI#mostrarLineaCalculadora()
     * @see String#split(java.lang.String)
     *
     * @see StringTokenizer
     * @param arg Linea de comandos en una cadena
     * @return arg String[] un array con los comandos
     * @deprecated Mejorado usando {@link String#split(java.lang.String)}
     */
    private String[] montarLineaComando(String arg) {
        StringTokenizer st = new StringTokenizer(arg);
        String[] args = new String[st.countTokens()];
        int cont = 0;

        while (st.hasMoreTokens()) {
            args[cont] = st.nextToken();
            cont++;
        }
        return args;
    }

    /**
     * Con este metodo "abro" la linea de comandos de la calculadora para
     * realizar muchas operaciones seguidas.
     *
     * Controlo las excepciones aqui en vez de la vista, ya que se necesita
     * controlar dentro del bucle para que no se salga de la calculadora linea
     * de comandos.
     *
     * Hasta el momento que el usuario le de a exit y se salga de la
     * calculadora.
     *
     * @see VistaCLI#mostrarDespedida()
     */
    public void abrirLineaComandos() {
        exit = false;
        //Notifica a la vista que se abra un flujo de datos de teclado.
        vista.loadOutputTeclado();
        while (!exit) {
            String[] args = vista.mostrarLineaCalculadora();
            try {
                //Leo la linea de comandos
                leerComando(args);

                //Controlo aqui las excepciones sino se sale del bucle
            } catch (NumberFormatException nfe) {
                notificarError("Tipo de argumentos no validos.");
            } catch (ArithmeticException | IllegalArgumentException ex) {
                notificarError(ex.getMessage());
            }
        }
        //Muestra una despedida de la aplicacion
        vista.mostrarDespedida();
    }

    /**
     * Notifica a la vista que tiene que mostrar un error.
     *
     * @param error mensaje de error
     */
    private void notificarError(String error) {
        vista.mostrarError(error);
    }
}