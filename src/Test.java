import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import XML_in_out.*;
import DB_Connection.*;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		/*
		LinkedList<String> tags = new LinkedList<String>();
		tags.add("DRIVER_CLASS");
		tags.add("DBMS");
		tags.add("SERVER");
		tags.add("PORT");
		tags.add("DB");
		tags.add("USER_ID");
		tags.add("PASSW");
		
		
		LinkedList<Tag> read = XML_Interface.readFile_XML("CONFIG/connection_db.xml", tags, null);
		
		Tag vecchio = read.get(5);
		Tag nuovo = new Tag(vecchio.getNomeTag_Padre(), vecchio.getNomeTag(), "Sto Cazzo", vecchio.getAttributo());
		
		LinkedList<Tag> cambiata = XML_Interface.changeXML(read, vecchio, nuovo);
		
		System.out.println("\n CAMBIATA:\n");
		for (Tag t : cambiata)
		{
			System.out.println(t.toString());
		}
		
		XML_Interface.writeFile_XML("CONFIG/test", cambiata);
		
		System.out.println("\n CAMBIATA:\n");
		for (Tag t : cambiata)
		{
			System.out.println(t.toString());
		}
		
		read = XML_Interface.readFile_XML("CONFIG/test.xml", tags, null);
		*/
		DbAccess.initConnection("mexal_telecom");
		//DbAccess.closeConnection();
		
	}

}
