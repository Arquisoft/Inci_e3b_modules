package main;

import com.lowagie.text.DocumentException;

import parser.ReaderSingleton;

/**
 * Main application
 * 
 * @author 1A1
 *
 */
public class LoadUsers {

	public static void main(String... args) throws DocumentException {
		final LoadUsers runner = new LoadUsers();
		runner.run(args);
	}

	private void run(String... args) throws DocumentException {
		if (args.length == 0 || args[0].equals("--help"))
			showHelp();
		else if (args[0].equals("info"))
			showInfo();
		else if (args[0].equals("load")) {
			if (args.length < 2)
				System.err.println(
						"La operacion load necesita al menos otro " + "argumento con la ubicacion del fichero");
			else {
				for (int i = 1; i <= args.length - 1; i++)
					ReaderSingleton.getInstance().loadFile(args[i]);
			}
		}
	}

	private void showInfo() {
		System.out.println("Proyecto Agents Loader E3B");
		System.out.println("El objetivo de este proyecto es actuar como modulo de carga de"
				+ "agentes para un sistema de participacion ciudadana");
		System.out.println("Realizado por el grupo E3B compuesto por: ");
		System.out.println("Pablo Gonzalez Martinez (UO245699)");
		System.out.println("Diego Álvarez Guinarte (UO251682)");
		System.out.println("Manuel Junco Diez (UO252010)");
	}

	private void showHelp() {
		System.out.println("Manual de ayuda para el uso de la aplicacion");
		System.out.println("La aplicacion tiene implementadas las operaciones info, load y help");
		System.out.println("	info: Muestra informacion relacionada con el proyecto, como los autores");
		System.out.println(
				"	load[file]: Permite cargar un conjunto de ficheros excel con usuarios " + "en la base de datos");
		System.out.println("	--help: Muestra este menu de ayuda. Si no se proporcionan parametros "
				+ "a la aplicacion se mostrara este menu");
	}
}
