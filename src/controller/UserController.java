package controller;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.PrIS;
import server.Conversation;
import server.Handler;

class UserController implements Handler {
	private PrIS informatieSysteem;
	
	/**
	 * De UserController klasse moet alle algemene aanvragen afhandelen. 
	 * Methode handle() kijkt welke URI is opgevraagd en laat dan de juiste 
	 * methode het werk doen. Je kunt voor elke nieuwe URI een nieuwe methode 
	 * schrijven.
	 * 
	 * @param infoSys - het toegangspunt tot het domeinmodel
	 */
	public UserController(PrIS infoSys) {
		informatieSysteem = infoSys;
	}
	
	public void handle(Conversation conversation) {
		if (conversation.getRequestedURI().startsWith("/login")) {
			login(conversation);
		}
	}
	
	/**
	 * Deze methode haalt eerst de opgestuurde JSON-data op. Daarna worden
	 * de benodigde gegevens uit het domeinmodel gehaald. Deze gegevens worden
	 * dan weer omgezet naar JSON en teruggestuurd naar de Polymer-GUI!
	 * 
	 * @param conversation - alle informatie over het request
	 */
	private void login(Conversation conversation) {
		JsonObject jsonObjIn = (JsonObject) conversation.getRequestBodyAsJSON();
		
		String gebruikersnaam = jsonObjIn.getString("username");					// Uitlezen van opgestuurde inloggegevens... 
		String wachtwoord = jsonObjIn.getString("wachtwoord");
		
		String rol = informatieSysteem.login(gebruikersnaam, wachtwoord);			// inloggen methode aanroepen op domeinmodel...
		
		JsonObjectBuilder job = Json.createObjectBuilder().add("rol", rol);			// en teruggekregen gebruikersrol als JSON-object...
		String jsonOut = job.build().toString();
		
		conversation.sendJSONMessage(jsonOut);										// terugsturen naar de Polymer-GUI!
	}
}