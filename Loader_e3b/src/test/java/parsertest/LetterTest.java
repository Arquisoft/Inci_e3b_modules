package parsertest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;

import com.lowagie.text.DocumentException;

import model.Agent;
import model.util.ModelException;
import parser.cartas.*;

public class LetterTest {

	@Test
	public void creadasCorrectamente() throws FileNotFoundException, DocumentException, IOException, ModelException {
		Letter letter = new PdfLetter();		
		Agent user1 = new Agent("Paco", null, "francisco@gmail.com","paco123","Ciudadano");
		letter.createLetter(user1);

		File file = new File("cartas/pdf/paco123.pdf");
		assertTrue(file.exists());
		file.delete();

		letter = new WordLetter();
		letter.createLetter(user1);

		file = new File("cartas/word/paco123.docx");
		assertTrue(file.exists());
		file.delete();

		letter = new TxtLetter();
		letter.createLetter(user1);

		file = new File("cartas/txt/paco123.txt");
		assertTrue(file.exists());
		file.delete();
	}
	
}
