package dbupdate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lowagie.text.DocumentException;

import executer.ActionSingleton;
import model.Agent;
import model.util.ModelException;
import persistence.UserFinder;
import persistence.util.Jpa;

public class DbTest {

	private Agent user1,user2;
	
	
	@Before
	public void creacion() throws FileNotFoundException, DocumentException, IOException, ModelException
	{
		ActionSingleton aS = ActionSingleton.getInstance();
		user1 = new Agent("Dani",null,"dani35@gmail.com","dani123","Ciudadano");
		aS.getAF().saveData(user1);

	}
	
	
	
	
	/**
	 * Comprobamos que no se puedan añadir más de un agente de mismo Identificador
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * @throws IOException
	 * @throws ModelException 
	 */
	@Test
	public void usuarioYaExistente() throws FileNotFoundException, DocumentException, IOException, ModelException {
		
		ActionSingleton aS = ActionSingleton.getInstance();
		 user2 = new Agent("Dani",null,"dani35@hotmail.com","dani123","Ciudadano");
		 aS.getAF().saveData(user2);

		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		List<Agent> test = UserFinder.findByIdentificador("dani123");
		assertEquals(test.size(),1);
		assertEquals(test.get(0).getEmail(), "dani35@gmail.com");
		assertEquals(test.get(0).getIdentificador(), "dani123");
		assertEquals(test.get(0).getNombre(), "Dani");
		assertNull(test.get(0).getLocalizacion());

		trx.commit();
		mapper.close();
	}

	@Test
	public void usuarioYaExistenteEmail() throws FileNotFoundException, DocumentException, IOException, ModelException {
		
		ActionSingleton aS = ActionSingleton.getInstance();
		 user2 = new Agent("Dani",null,"dani35@gmail.com","dani1","Ciudadano");
		 aS.getAF().saveData(user2);

		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		
		List<Agent> test = UserFinder.findByEmail("dani35@gmail.com");
		assertEquals(test.size(),1);
		assertEquals(test.get(0).getIdentificador(), "dani123");
		assertEquals(test.get(0).getNombre(), "Dani");
		assertNull(test.get(0).getLocalizacion());

		trx.commit();
		mapper.close();

	}

	@Test
	public void deleting() {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		List<Agent> aBorrar = UserFinder.findByIdentificador("dani123");
		assertEquals(aBorrar.size(),1);
		Jpa.getManager().remove(aBorrar.get(0));
		List<Agent> test = UserFinder.findByEmail("dani35@gmail.com");
		assertEquals(test.size(),0);
		trx.commit();
		mapper.close();
	}

}
