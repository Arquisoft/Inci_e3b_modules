package asw.agents.util;

import java.util.Calendar;
import java.util.Date;

import java.util.Properties;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;

public  class Utilidades {
	///////////////////////////////////////////////////////////////
	/// //
	/// Clase creada para realizar funciones que no //
	/// tengan que ver con la l�gica de los controladores //
	/// //
	//////////////////////////////////////////////////////////

//metido en assert
//
//	public static boolean validarCorreo(String mail) {
//		String[] mailSplit = mail.split("@");
//		if (mailSplit.length != 2 || mailSplit[0].length() == 0) {
//			return false;
//		}
//		mailSplit = mail.split(".");
//		if (mailSplit.length != 2 || mailSplit[0].length() == 0 || mailSplit[1].length() == 0) {
//			return false;
//		}
//
//		return true;
//	}

//	public static int getEdad(Date fechaNacimiento) {
//		Calendar calendarioNacimiento = Calendar.getInstance();
//		calendarioNacimiento.setTime(fechaNacimiento);
//		int dianacimiento = calendarioNacimiento.get(Calendar.DAY_OF_YEAR);
//
//		Calendar calendariohoy = Calendar.getInstance();
//		int diaHoy = calendariohoy.get(Calendar.DAY_OF_YEAR);
//		if (dianacimiento - diaHoy == 0) {// Si la resta de días que llevamos de
//											// año es 0, significa que hemos
//											// cumplido años
//			return calendariohoy.get(Calendar.YEAR) - calendarioNacimiento.get(Calendar.YEAR);
//
//		} else {
//
//			return calendariohoy.get(Calendar.YEAR) - calendarioNacimiento.get(Calendar.YEAR) - 1;
//
//		}
//
//	}

	public static int getKindCode(String kind) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		try(InputStream resourceStream = classLoader.getResourceAsStream("application.properties")) {
		    properties.load(resourceStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
        CSVReader csvReader = null;
        try {
        	csvReader = new CSVReader(new InputStreamReader(
        			classLoader.getResourceAsStream((properties.getProperty("csv.filepathname"))))
        	);
            String[] nextRow;
            while ((nextRow = csvReader.readNext()) != null) {
            	if(kind.equals(nextRow[1]))
        			return Integer.parseInt(nextRow[0]);
            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }        
        return -1;
	}	
}
