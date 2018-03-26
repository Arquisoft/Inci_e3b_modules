package inciDashboard.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import inciDashboard.entities.Agent;
import inciDashboard.entities.Incidence;

public class ParserJsonToIncidence {
	
	public static Incidence JsonToIncidence(JSONObject json) {
		String username = json.getString("username");
		String password = json.getString("password");
		String kind = json.getString("kind");
		String incidenceName = json.getString("incidenceName");
		String descripcion = json.getString("descripcion");
		String location = json.getString("location");
		
		Set<String> labels = new HashSet<String>();
		JSONArray jlabels = json.getJSONArray("labels");
		for(int i=0;i<jlabels.length();i++)
			labels.add((String) jlabels.get(i));
		
		Set<String> others = new HashSet<String>();
		JSONArray jothers = json.getJSONArray("others");
		for(int i=0;i<jothers.length();i++)
			others.add((String) jothers.get(i));
		
		HashMap<String,String> fields = new HashMap<String,String>();
		JSONArray jfields = json.getJSONArray("fields");		
		for(int i=0;i<jfields.length();i++) {			
			Object field = jfields.get(i);
			JSONObject field1 = jfields.getJSONObject(i);
			fields.put("campo1",field1.getString("campo " + (i+1)));		
		}			
		
		Set<String> comments = new HashSet<String>();
		JSONArray jcomments = json.getJSONArray("comments");
		for(int i=0;i<jcomments.length();i++)
			comments.add((String) jcomments.get(i));
		
		Estado status = null;
		switch (json.getString("status")) {
        case "ABIERTA":  
        	status = Estado.ABIERTA;
        	break;
        case "EN_PROCESO":  
        	status = Estado.EN_PROCESO;
        	break;
        case "CERRADA":  
        	status = Estado.CERRADA;
        	break;
        case "ANULADA":  
        	status = Estado.ANULADA;
        	break;
		default:
			break;	
		}
		
		Boolean cacheable = json.getBoolean("cacheable");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Date expiration = new Date();
		try {
			expiration = formatter.parse(json.getString("expiration"));
		} catch (JSONException e) {			
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Agent agent = new Agent(username,password,location,username,"ident",kind);
		Incidence incidence = new Incidence(agent,incidenceName,descripcion,location,labels,comments,fields,status,expiration,cacheable,others,null);
		
		return incidence;		
	}

}
