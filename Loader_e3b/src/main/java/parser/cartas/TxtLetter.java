package parser.cartas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import model.Agent;

public class TxtLetter extends Letter{
	private Writer writer;

	public void createLetter(Agent user) throws IOException{
		File letter = new File("cartas/txt/" + user.getIdentificador() + ".txt");
		writer = new FileWriter(letter);
		writer.write("Nombre: " + user.getNombre());
		writer.write(System.lineSeparator());
		writer.write("Identificador: " + user.getIdentificador());
		writer.write(System.lineSeparator());
		writer.write("Clave: " + user.getClave());
	}
}
