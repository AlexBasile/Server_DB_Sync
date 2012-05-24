package Utility;

/*
 * INTERFACCIA PER LA GESTIONE DELLA COMUNICAZIONE CON L'UTENTE
 */

/**
 * @author Alessandro Basile
 * @version 1.0
 */
public interface iConsole
{	
	/*
	 * METODO PER LA STAMPA A VIDEO DI UN MESSAGGIO
	 */
	public void stampaMsg(String msg);
	
	/*
	 * METODO PER EFFETTUARE UNA DOMANDA CON RISPOSTA BINARIA
	 */
	public boolean chiediSiNo(String domanda);
	
	/*
	 * METODO PER CHIEDERE L'IMMISSIONE DI UN NUMERO DECIMALE
	 */
	public double chiediDouble(String domanda);
	
	/*
	 * METODO PER CHIEDERE L'IMMISIONE DI UNA STRINGA
	 */
	public String chiediTesto(String domanda);
	
}
