/**
 * PACKAGE PER LA GESTIONE DELLE UTILITY PER IL FUNZIONAMENTO
 * DELL'APPLICAZIONE
 */
package Utility;

/**
 * @author Alessandro Basile
 * @version 1.0
 */
public class Console implements iConsole {

	/*
	 * @see Utility.iConsole#stampaMsg(java.lang.String)
	 */
	@Override
	public void stampaMsg(String msg) {
		System.out.println(msg);
	}

	/*
	 * @see Utility.iConsole#chiediSiNo(java.lang.String)
	 */
	@Override
	public boolean chiediSiNo(String domanda) {
		
		char a;
		boolean risposta= false;
		boolean esito=true;

		stampaMsg(domanda);

		//lettura da tastiera di un carattere
		while (esito){
			a = Keyboard.readChar();
			switch(a){
				case 's' : risposta= true;esito=false ; break;
				case 'n' : risposta= false;esito=false; break;
				default  : stampaMsg("Inserire s oppure n \n");esito=true; 
			}
		}
		return risposta;
	}

	/* (non-Javadoc)
	 * @see Utility.iConsole#chiediFloat(java.lang.String)
	 */
	@Override
	public double chiediDouble(String domanda) {		
		// Stampa un messaggio e chiede un numero
		stampaMsg(domanda);
		return (Keyboard.readDouble());//legge da testiera un numero intero
	}

	/* (non-Javadoc)
	 * @see Utility.iConsole#chiediTesto(java.lang.String)
	 */
	@Override
	public String chiediTesto(String domanda) {
		//Stampa un messaggio e chiede una stringa 
		stampaMsg(domanda);
		return (Keyboard.readString());//legge da tastiera una stringa
	}

}
