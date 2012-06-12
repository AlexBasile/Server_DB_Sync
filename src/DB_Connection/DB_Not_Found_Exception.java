/**
 * 
 */
package DB_Connection;

/**
 * @author Alessandro Basile
 *
 */
public class DB_Not_Found_Exception extends Exception {
	
	private static final long serialVersionUID = 1L;

	public String toString()
	{
		return "DB non connesso o non trovato!";
	}

}
