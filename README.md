CALCULADORA CLI 2.0  
=============

Calculadora de linea de comandos escrita en java usando el patron MVC.

INFORMACION 
=============
La Calculadora CLI escrita en Java 7 con licencia Apache 2 la misma 
licencia que la libreria que se ha usado Apache Commons CLI.

El repositorio esta subido a GitHub: https://github.com/tonilopezmr/CalculadoraCLI.git

-Licencia Apache 2: http://www.apache.org/licenses/LICENSE-2.0

Las librerias que se han utilizado son:
	- Apache commons CLI v1.2: http://commons.apache.org/proper/commons-cli/download_cli.cgi

	Y para las pruebas unitarias:
	- JUnit v4.10: http://junit.org/ View on GitHub

La calculadora realiza las siguientes operaciones: 
	- Operaciones aritmetricas: suma, resta, multiplicación, división, resto, potencia y raíz desde la 1.0
	- Operaciones trigonometricas: seno, coseno, tangente
	- Las tabla de verdad: AND, OR, XOR, NOT
	- Conversiones entre binario, octal, decimal y hexadecimal

El ejecutable CalculadoraCLI.jar, fue empaquetado con la libreria Commons cli para no tener que añadirla
en el path del sistema, aunque pese un poco más, es muy poco lo que pesa el programa.


FUNCIONALIDAD 
=============

La Calculadora CLI tiene dos funcionalidades, una es si se escriben argumentos de entrada realiza esa 
operacion y ya esta, y otra es si se quiere realizar muchas operaciones seguidas y para no tener el 
engorro de tener que poner cada vez el nombre del ejecutable y la operacion, pues abres la linea de 
comandos de la Calculadora CLI de forma que tienes una calculadora en linea de comandos real y vas
poniendo las operaciones hasta que decidas salirte.

Ej: 	Sin abrir la linea de comandos:
	calc -r 10 10 
	El resultado es: 0

	Abriendo la linea de comandos:
	calc
	Calculadora > -r 10 10
	El resultado es: 0
	Calculadora > (Aqui esperaria la siguiente operacion)
	Calculadora >
	
De esta forma tenemos una linea de comandos lo mas real posible como si abrieras la calculadora
completamente para poder realizar muchas operaciones seguidas sin tener que escribir el nombre 
de la aplicacion cada vez.

PRUEBAS UNITARIAS
=============

Se han echo pruebas unitarias al modelo, y al programa en general intentando cubrir la mayor parte
de codigo comprobando con cobertura y las mas posibles situaciones para ver que el programa en ningun
momento se interrumpe, las pruebas unitarias son independientes del programa sirven para todas las
calculadoras de lineas de comandos, tan solo hay que configurarla antes de hacer las pruebas, cambiar
los comandos si son diferentes y poner el nombre de la clase donde este el main que recibe el cmd.

Hay una testSuite para ejecutar las dos pruebas.


FLUJO DE LA CALCULADORA
=============

El controlador dividido en tres clases para evitar la "clase dios" y se reparte mejor el trabajo.

De modo que tenemos una clase que se comunica con la vista, otra clase se comunica con el modelo, 
de formas mucho mas independiente, cuando hay que hacer algun cambio en el codigo es mucho mas 
facil, y sin que lo demas tenga que trabajar diferente.

Como funciona
=============

	Ej: Si abro la linea de comandos de la calculadora.
	
	Calculadora > -s 10 10
	El resultado es: 20.0
	Calculadora > -r 20 10
	El comando '-r' no se reconoce como comando del programa.
	
	Ya solo me dejaria restar en toda la ejecucion del programa, ya que 
	serian todos miembros de un mismo grupo.

	Tambien de esta forma puedo hacer una ayuda personalizada para el 
	comando de conversiones (-D) al poder poner -h y -D a la vez y poder
	ayudar a utilizar el comando ya que es mas complejo que los demas.
