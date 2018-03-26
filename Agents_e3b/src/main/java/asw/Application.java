package asw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import asw.db_management.model.Agent;
import asw.db_management.repository.AgentRepository;

import java.text.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initDB(AgentRepository repository) throws ParseException {
		// Formato para la fecha ya no es necesario este año.
		// DateFormat formatter1 = new SimpleDateFormat("mm/DD/yyyy");

		return (args) -> {
			repository.save(new Agent("Paco González","123456","","paco@gmail.com","paco","Person"));
			repository.save(new Agent("Pepe Fernandez","123456","","pepe@gmail.com","pepe","Person"));
			repository.save(new Agent("Sensor_123 2018","123456","43.361368, -5.853591","admin@sensores.com","sensor_123","Sensor"));
			repository.save(new Agent("Ministerio medioambiente","123456","43.359486, -5.846986","ambiente@ministerio.com","medioambiente","Entity"));
			repository.save(new Agent("Space X sensor model A","123456","33.921209, -118.327940","elonmusk@spacex.com","spacex","Sensor"));
		};
	}
}