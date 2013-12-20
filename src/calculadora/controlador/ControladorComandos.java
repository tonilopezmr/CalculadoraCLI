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

import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

/**
 * ControladorComandos etapa de interrogatorios controla los comandos de la
 * linea de entrada y segun el comando se comunica con el controlador de la
 * calculadora {@link ControladorCalc}.
 *
 * Comunicacion directa con el controlador de la calculadora y devuelve los
 * resultados al {@link ControladorCLI}
 *
 * Todos los metodos son privados excepto el constructor y la busqueda de
 * comandos.
 *
 * @author Antonio López Marín
 * @author Antonio Rodrigo Gea López
 * @see ControladorCalc Controlador de la calculadora.
 */
public class ControladorComandos {
    //Comunicacion con el controlador de la calculadora

    private ControladorCalc controlCalc;
    //Recoger linea comandos
    private CommandLine cmdLine;
    /*
     * Constantes de los comandos de facil acceso.
     * 
     * Si hubiera que cambiar algun comando no habria que buscarlo
     * en el codigo sino cambiarlo aqui, y no podra ser modificado
     * por el programa en toda la ejecucion.
     * 
     */
    //Opcion de ayuda
    private final String HELP = "help";
    //Opcion de salida
    private final String EXIT = "exit";
    //OperacionesAritmetricas
    private final String SUMA = "s";
    private final String RESTA = "r";
    private final String MULTI = "m";
    private final String DIVISION = "d";
    private final String RESTO = "re";
    private final String POTENCIA = "p";
    private final String RAIZ = "ra";
    //OperacionesTrigonometricas
    private final String SENO = "seno";
    private final String COSENO = "coseno";
    private final String TANGENTE = "tangente";
    //Tablas de verdad
    private final String AND = "and";
    private final String OR = "or";
    private final String XOR = "xor";
    private final String NOT = "not";
    //Propiedades
    private final String PROPERTY = "D";

    /**
     * Constructor que le pasan por argumentos la linea de comandos para buscar
     * el comando que recibe.
     *
     * Y creo el objeto de la controladora de la calculadora para poder
     * notificar que operacion se desea realizar segun el comando que le pasen.
     *
     * @param cmdLine la linea de comandos de commons cli
     */
    public ControladorComandos(CommandLine cmdLine) throws ParseException {
        this.cmdLine = cmdLine;
        controlCalc = new ControladorCalc();
        buenosComandos(); //Compruebo que el cmd es correcto de entrada
    }

    /**
     * Ya que no estan los comandos agrupados para poder abrir una linea de
     * comandos, hay que controlarlo con este pequeño metodo y ademas la
     * informacion del error, si lo hay, puede ser mas informativa.
     *
     * Tambien compruebo que no hay argumentos que no reconoce el programa.
     *
     * NOTA: Se refactorizo este metodo ya que tenia una complegidad ciclomatica
     * de 14 demasiado grande.
     *
     * @throws ParseException demasiados comandos
     */
    private void buenosComandos() throws ParseException {
        String[] badArgs = cmdLine.getArgs();
        //Si hay argumentos no reconocidos
        if (badArgs.length > 0) {
            if (!badArgs[0].equals("")) { //Si es distinto de un ENTER.
                throw new ParseException("'" + badArgs[0] + "' no "
                        + "se reconoce como comando interno del programa.");
            }
        }
        //Obtengo las opciones del cmd
        Option[] opt = cmdLine.getOptions();

        //Si es igual a dos que las compruebe
        if (opt.length == 2) {
            hasBuenosComandos(opt);
        } else if (opt.length == 3) { //Si es igual a tres directamente error
            throw errorComandos();
        }
    }

    /**
     * Metodo que devuelve una excepcion de error de comandos.
     *
     * @return ParseException devuelve la excepcion con el mensaje pertinente
     * @throws ParseException error de comandos.
     */
    private ParseException errorComandos() throws ParseException {
        return new ParseException("Demasiados comandos, "
                + "solo se puede realizar una "
                + "operacion cada vez.");
    }

    /**
     * Controlo si un comando con posix largo se escribio a la vez que otro
     * posix largo como puede ser (--help) junto con --(seno) de error.
     *
     * Controlo si un comando posix largo se escribio a la vez que otro de posix
     * corto que no sean --help y -D(ya que si ocurriera esto mostraria la
     * ayuda), y viceversa tiene que dar error.
     *
     * @param opt dos opciones
     * @see Option
     * @throws ParseException Demasiados comandos
     */
    private void hasBuenosComandos(Option[] opt) throws ParseException {
        //Obtengo la primera y la segunda opcion
        Option op1 = opt[0];
        Option op2 = opt[1];

        boolean isLongOpt1 = op1.hasLongOpt();
        boolean isLongOpt2 = op2.hasLongOpt();

        //Si los dos comandos son posix largo error
        if (isLongOpt1 && isLongOpt2) {
            throw errorComandos(); //Error de comandos
            //Si el primero es posix largo y el segundo no pero son el comando
            //-help y -D no debe ocurrir nada
        } else if ((isLongOpt1 && !isLongOpt2
                && op1.getLongOpt().equals(HELP)
                && op2.getOpt().equals(PROPERTY))) {
            //No debe hacer nada importante esto vacio.
            //Si el primero es poxis largo y el segundo posix corto y viceversa
        } else if (isLongOpt1 && !isLongOpt2
                || !isLongOpt1 && isLongOpt2) {
            throw errorComandos(); //Error de comandos
        }
    }

    /**
     * Compruebo que los argumentos de un comando son correctos, en este caso
     * deben de haber dos argumentos.
     *
     * @param args
     * @throws IllegalArgumentException
     */
    private void dosArgs(String[] args) throws IllegalArgumentException {
        if (args.length != 2) { //Imposible ser distinto de dos, error
            throw new IllegalArgumentException("No hay suficientes argumentos"
                    + " de entrada, se requieren dos.");
        }
    }

    /**
     * Compruebo que los argumentos de un comando son correctos, en este caso
     * debe de haber un argumento.
     *
     * @param args
     * @throws IllegalArgumentException
     */
    private void unArg(String[] args) throws IllegalArgumentException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Los argumentos de "
                    + "entrada son erroneos, se requiere uno.");
        }
    }

    /**
     * Paso los numeros de dos argumentos a un array de numeros double,
     * asegurandome que son correctos, si hay algun error son lanzados.
     *
     *
     * @param numeros los argumentos que deben ser numeros
     * @return nums dos numeros pasados en double
     * @throws IllegalArgumentException error de argumentos
     * @throws NumberFormatException error al parsear los argumentos
     * @see Double#parseDouble(java.lang.String)
     */
    private double[] dosNumerosDouble(String[] numeros)
            throws IllegalArgumentException, NumberFormatException {
        //Verificamos que los argumentos son correctos
        dosArgs(numeros);

        //Siempre sera 2
        double[] nums = new double[2];

        //Mete los numeros en un array de double
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Double.parseDouble(numeros[i]);
        }

        return nums;
    }

    /**
     * Recoge dos booleanos de los argumentos y los pasa a un array tipo
     * booleano {@link Boolean}, si hay algun error se lanzan.
     *
     *
     * @param booleanos array de parametros
     * @return boo array con dos boleanos
     * @throws IllegalArgumentException
     * @throws NumberFormatException
     * @see Boolean#parseBoolean(java.lang.String)
     */
    private boolean[] dosBooleanos(String[] booleanos)
            throws IllegalArgumentException, NumberFormatException {

        //Verificamos que los argumentos son corerctos
        //sino salta excepcion
        dosArgs(booleanos);

        //Siempre va a ser dos
        boolean[] boo = new boolean[2];

        //Mete los argumentos booleanos en el array
        for (int i = 0; i < booleanos.length; i++) {
            //Si los argumentos son distintos de true y false..
            if (!booleanos[i].equalsIgnoreCase("true")
                    && !booleanos[i].equalsIgnoreCase("false")) {
                //Para poder personalizar el mensaje
                throw errorBooleano();
            }
            boo[i] = Boolean.parseBoolean(booleanos[i]);
        }

        return boo;
    }

    /**
     * Metodo que devuelve una excepcion diciendo el error de tipo de booleanos.
     *
     * @return IllegalArgumentException con el mensaje de error.
     * @see IllegalArgumentException
     */
    private IllegalArgumentException errorBooleano() {
        return new IllegalArgumentException("Tipo de argumentos erroneos, "
                + "escribe booleanos (true o false).");
    }

    /**
     * Busca el comando que se le paso al constructor y llama a la calculadora
     * para que ejecute el comando con los argumentos de entrada.
     *
     * Este metodo utiliza el <b>return</b> para devolver los resultados una vez
     * encuentra un comando devuelve el resultado y no pasa por los demas
     * sentencias.
     *
     * Se debe controlar cada uno de los comandos y pasarle los numeros a la
     * calculadora.
     *
     * Nota: -Si en la linea de comandos de entrada no hay ningun comando de la
     * aplicacion no devuelve una cadena vacia para que no muestre nada en
     * pantalla.
     *
     * -Si la opcion es de ayuda se le pasa una cadena unica que interpretara el
     * controlador principal para mostrar la ayuda.
     *
     * -Nunca se devuelve null
     *
     * Refacotorizado varias veces, antes estaba toda la busqueda concentrada en
     * el metodo.
     *
     * @see ControladorCalc#Todos los metodos
     * @see Object#toString()
     * @return String el resultado de la operacion
     * @throws IllegalArgumentException argumentos no son correctos
     * @throws NumberFormatException tipos de argumentos erroneos
     */
    public String busquedaDeComandos() throws IllegalArgumentException,
            NumberFormatException {

        String resultado = "";
        //Resultado de las posibles operaciones
        Double result;
        Boolean resultBoo;

        //Si el comando es help
        if (cmdLine.hasOption(HELP)) {
            if (cmdLine.hasOption(PROPERTY)) {
                //Devuelvo un valor unico posible para decirle al controlador
                //principal que tiene que mostrar la ayuda de el comando -D
                resultado = "AYUDAPROPERTY";
            } else {
                //Devuelvo un valor unico posible para decirle al controlador
                //principal que tiene que mostrar la ayuda a la vista
                resultado = "AYUDA";
            }
        } else //Si el comando es exit
        if (cmdLine.hasOption(EXIT)) {
            //Devuelvo un valor unico posible para decirle al controlador
            //principal que tiene que salirse de la linea de comandos
            resultado = "EXIT";
        } else /* Propiedades */ if (cmdLine.hasOption("D")) {
            //Devuelve el resultado de la conversion
            resultado = conversor();
        } else if ((result = operacionesAritmetricas()) != null) {
            /* OperacionesAritmetricas */
            //Resultado de las posibles operaciones
            resultado = result.toString();
        } else if ((result = operacionesTrigonometricas()) != null) {
            /* OperacionesTrigonometricas */
            resultado = result.toString();
        } else if ((resultBoo = tablasVerdad()) != null) {
            /* Tablas de verdad */
            //Resultado de las posibles operaciones booleanas
            resultado = resultBoo.toString();
        }

        //Devuelve el resultado 
        return resultado;
    }

    /**
     * Metodo de operaciones aritmetricas que devuelve un {@link Double} en
     * forma de Objeto para luego poder parsear rapidamente a cadena y poder
     * devolver el resultado.
     *
     * Devuelve <b>null</b> si el comando no es ninguno de las operaciones
     * aritmetricas.
     *
     * @see Double
     * @see Double#parseDouble(java.lang.String)
     * @see Integer#parseInt(java.lang.String)
     * @return Double resultado de las operaciones
     * @throws IllegalArgumentException argumentos no son correctos
     * @throws NumberFormatException tipos de argumentos erroneos
     */
    private Double operacionesAritmetricas() throws IllegalArgumentException,
            NumberFormatException {
        //Preparo el array de numeros
        double[] numeros;
        //Resultado a devolver
        Double resultado = null;

        //Comando de suma
        if (cmdLine.hasOption(SUMA)) {
            //Utilizo el metodo para no duplicar codigo
            numeros = dosNumerosDouble(cmdLine.getOptionValues(SUMA));

            //le paso a el controlador el primer numero y el segundo
            resultado = controlCalc.calcularSuma(numeros[0], numeros[1]);
        } else //Comando de resta
        if (cmdLine.hasOption(RESTA)) {
            numeros = dosNumerosDouble(cmdLine.getOptionValues(RESTA));

            resultado = controlCalc.calcularResta(numeros[0], numeros[1]);
        } else //Comando de multiplicacion
        if (cmdLine.hasOption(MULTI)) {
            numeros = dosNumerosDouble(cmdLine.getOptionValues(MULTI));

            resultado = controlCalc.calcularMultipilicacion(numeros[0],
                    numeros[1]);
        } else //Comando de division
        if (cmdLine.hasOption(DIVISION)) {
            numeros = dosNumerosDouble(cmdLine.getOptionValues(DIVISION));

            resultado = controlCalc.calcularDivision(numeros[0], numeros[1]);
        } else //Comando de resto
        if (cmdLine.hasOption(RESTO)) {
            numeros = dosNumerosDouble(cmdLine.getOptionValues(RESTO));

            resultado = controlCalc.calcularResto(numeros[0], numeros[1]);
        } else //Comando de potencia
        if (cmdLine.hasOption(POTENCIA)) {
            numeros = dosNumerosDouble(cmdLine.getOptionValues(POTENCIA));

            resultado = controlCalc.calcularPotencia(numeros[0], numeros[1]);
        } else //Comando de raiz
        if (cmdLine.hasOption(RAIZ)) {
            String[] nums = cmdLine.getOptionValues(RAIZ);

            dosArgs(nums);
            //Paso cada uno a su tipo
            double radicando = Double.parseDouble(nums[0]);
            int indice = Integer.parseInt(nums[1]);

            resultado = controlCalc.calcularRaiz(radicando, indice);
        }

        return resultado;
    }

    /**
     * Metodo de operaciones trigonometricas que devuelve un {@link Double} en
     * forma de Objeto para luego poder parsear rapidamente a cadena y poder
     * devolver el resultado.
     *
     * Devuelve <b>null</b> si el comando no es ninguno de las operaciones
     * trigonometricas.
     *
     * @see Double
     * @see Double#parseDouble(java.lang.String)
     * @return Double resultado de las operaciones
     * @throws IllegalArgumentException argumentos no son correctos
     * @throws NumberFormatException tipos de argumentos erroneos
     */
    private Double operacionesTrigonometricas() throws IllegalArgumentException,
            NumberFormatException {
        //Preparo el angulo que pueden utilizar para los proximos metodos
        double angulo;
        //Resultado
        Double resultado = null;

        //Comando de seno
        if (cmdLine.hasOption(SENO)) {
            unArg(cmdLine.getOptionValues(SENO));
            angulo = Double.parseDouble(cmdLine.getOptionValue(SENO));

            resultado = controlCalc.calcularseno(angulo);
        } else //Comando de coseno
        if (cmdLine.hasOption(COSENO)) {
            unArg(cmdLine.getOptionValues(COSENO));
            angulo = Double.parseDouble(cmdLine.getOptionValue(COSENO));

            resultado = controlCalc.calcularCoseno(angulo);
        } else //Comando de la tangente
        if (cmdLine.hasOption(TANGENTE)) {
            unArg(cmdLine.getOptionValues(TANGENTE));
            angulo = Double.parseDouble(cmdLine.getOptionValue(TANGENTE));

            resultado = controlCalc.calcularTangente(angulo);
        }

        return resultado;
    }

    /**
     * Metodo de operaciones de las tablas de verdad que devuelve un
     * {@link Double} en forma de Objeto para luego poder parsear rapidamente a
     * cadena y poder devolver el resultado.
     *
     * Devuelve <b>null</b> si el comando no es ninguno de las operaciones de
     * las tablas de verdad.
     *
     * @see Double
     * @see Boolean#parseBoolean(java.lang.String)
     * @return Boolean resultado de las operaciones
     * @throws IllegalArgumentException argumentos no son correctos
     * @throws NumberFormatException tipos de argumentos erroneos
     */
    private Boolean tablasVerdad() throws IllegalArgumentException,
            NumberFormatException {
        //Preparo el array de booleanos
        boolean[] verdades;
        Boolean resultado = null;

        //Comando de and
        if (cmdLine.hasOption(AND)) {
            verdades = dosBooleanos(cmdLine.getOptionValues(AND));

            resultado = controlCalc.calcularAnd(verdades[0], verdades[1]);
        } else //Comando de or
        if (cmdLine.hasOption(OR)) {
            verdades = dosBooleanos(cmdLine.getOptionValues(OR));

            resultado = controlCalc.calcularOr(verdades[0], verdades[1]);
        } else //Comando de xor
        if (cmdLine.hasOption(XOR)) {
            verdades = dosBooleanos(cmdLine.getOptionValues(XOR));

            resultado = controlCalc.calcularXor(verdades[0], verdades[1]);
        } else //Comando de not
        if (cmdLine.hasOption(NOT)) {
            unArg(cmdLine.getOptionValues(NOT));

            String boleano = cmdLine.getOptionValue(NOT);
            //Compruebo si es true o false, sino lanzo excepcion.
            if (!boleano.equalsIgnoreCase("true")
                    && !boleano.equalsIgnoreCase("false")) {
                throw errorBooleano();
            }
            //Si todo correcto lo paso a boleano
            boolean boo = Boolean.parseBoolean(boleano);

            resultado = controlCalc.calcularNot(boo);
        }

        return resultado;
    }

    /**
     * Metodo que utiliza el metodo {@link ControladorComandos#conversor()} para
     * lanzar la excepcion de si un valor de la propiedad son incorrectos.
     *
     *
     * @return new IllegalArgumentException
     * @throws IllegalArgumentException valores son no son correctos
     */
    private IllegalArgumentException errorPropiedad()
            throws IllegalArgumentException {
        return new IllegalArgumentException("Valor/es de la propiedad incorrecto");
    }

    /**
     * Metodo del comando -D para Conversión entre binario, octal, decimal y
     * hexadecimal: origen y destino se establecen con propiedades al estilo
     * java (Dconversion.source=hex Dconversion.dest=bin).
     *
     * Recupera las propiedades con sus valores con el operador a convertir
     * segun las propiedades y devuelve el resultado.
     *
     * @see Map
     * @see Map#get(java.lang.Object)
     * @see String#valueOf(java.lang.Object)
     * @see ControladorComandos#conversorOrigenDestino(java.lang.String,
     * java.lang.String, java.lang.String)
     * @return String el resultado convertido en el destino
     * @throws IllegalArgumentException error de argumentos o valores
     * @throws NumberFormatException error de tipo de argumentos
     */
    private String conversor() throws IllegalArgumentException,
            NumberFormatException {
        //Mapa de las propiedades con sus valores del comando -D
        Map propiedades = cmdLine.getOptionProperties(PROPERTY);
        //Recojo los valores de las propiedades origen y destino
        Object source = propiedades.get("convert.source");
        Object dest = propiedades.get("convert.dest");

        //Recojo los argumentos, el ultimo elemento debe ser el operando(numero)
        String[] values = cmdLine.getOptionValues(PROPERTY);
        String value = values[values.length - 1];

        if (source == null || dest == null) {
            throw new IllegalArgumentException("Propiedades erroneas, "
                    + "debes expecificar el origen y el destino correctamente."
                    + "\nPara mas informacion comando de ayuda seguido "
                    + "de -Dconvert.");
        }
        //Los valores origen y destino salvados de ser nulos
        String origen = source.toString();
        String destino = dest.toString();

        return conversorOrigenDestino(origen, destino, value);
    }

    /**
     * Segun los valores de las propiedades se comunica con la calculadora y
     * devuelve el resultado.
     *
     * @param origen valor de origen de la conversion
     * @param destino valor de destino de la conversion
     * @param value el operador a convertir
     * @return String el resultado de la conversion
     * @throws IllegalArgumentException error de argumentos o valores
     * @throws NumberFormatException error de tipo de argumentos
     */
    private String conversorOrigenDestino(String origen, String destino,
            String value) throws IllegalArgumentException,
            NumberFormatException {
        //Variable que almacena el resultado y lo devuelve.
        String solucion = "";

        //Calcula segun los valores de las propiedades
        switch (origen) {
            case "dec":
                switch (destino) {
                    case "bin":
                        solucion = controlCalc
                                .calcularDecBin(Long.parseLong(value));
                        break;
                    case "oct":
                        solucion = controlCalc
                                .calcularDecOct(Long.parseLong(value));
                        break;
                    case "hex":
                        solucion = controlCalc
                                .calcularDecHex(Long.parseLong(value));
                        break;
                    default:
                        throw errorPropiedad();
                }
                break;
            case "bin":
                switch (destino) {
                    case "dec":
                        solucion = String
                                .valueOf(controlCalc.calcularBinDec(value));
                        break;
                    case "oct":
                        solucion = controlCalc.calcularBinOct(value);
                        break;
                    case "hex":
                        solucion = controlCalc.calcularBinHex(value);
                        break;
                    default:
                        throw errorPropiedad();
                }

                break;
            case "oct":
                switch (destino) {
                    case "dec":
                        solucion = String.valueOf(controlCalc
                                .calcularOctDec(value));
                        break;
                    case "bin":
                        solucion = controlCalc.calcularOctBin(value);
                        break;
                    case "hex":
                        solucion = controlCalc.calcularOctHex(value);
                        break;
                    default:
                        throw errorPropiedad();
                }

                break;
            case "hex":
                switch (destino) {
                    case "dec":
                        solucion = String.valueOf(controlCalc
                                .calcularHexDec(value));
                        break;
                    case "bin":
                        solucion = controlCalc.calcularHexBin(value);
                        break;
                    case "oct":
                        solucion = controlCalc.calcularHexOct(value);
                        break;
                    default:
                        throw errorPropiedad();
                }
                break;
            default:
                throw errorPropiedad();
        }
        //Devuelve la solucion
        return solucion;
    }
}