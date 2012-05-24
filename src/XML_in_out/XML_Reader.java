package XML_in_out;

/**
 * @author Alessandro Basile
 * @version 1.0
 */

import java.io.*;
import java.util.*;

//Librerie per la gestione del parser SAX
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XML_Reader
{

	protected static LinkedList<Tag> readXML_fromFile(String nomeFile, final LinkedList<String> tags, final String attr)
	{
		//Struttura per memorizzare i valori ritrovati per ogni tag
		final LinkedList<Tag> valori = new LinkedList<Tag>();

		try
		{
			//CREO UN PARSER XML TI TIPO SAX LA LETTURA DEI FILE
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			//IMPOSTO UN HASH MAP per gestire la presenza dei tag nel file
			final Map<String, Boolean> el_toFind = new HashMap<String, Boolean>();

			//Controllo che la lista di tag in input non sia vuota
			if(tags != null)
			{
				for(String elem : tags)
				{
					el_toFind.put(elem, new Boolean(false));
				}
			} else throw new Exception("TAG Nulli");

			//CREO un HANDLER per la lettura ed il parsing del file
			DefaultHandler handler = new DefaultHandler()
			{
				boolean attributo = false;
				String tag_Padre = null;
				String attr_tmp = attr;

				//Cerca il TAG di apertura, quando lo trova il tag di apertura 
				public void startElement(String uri, String localName,String qName, 
						Attributes attributes) throws SAXException
						{
					//Gestione dell'attributo di ogni oggetto
					String tag_attr = attributes.getValue(0);
					if(attr == null && tag_attr != null)
					{	
						attr_tmp = tag_attr;
						//System.out.print(attributes.getValue(0) + " attr_tmp = " + attr_tmp);
						//System.out.print("\n");
					}
					//Gestione di tutte le componenti di ogni nodo con lo stesso attributo
					if(attributes.getLength() > 0 &&  attributes.getValue(0).equalsIgnoreCase(attr_tmp) && attr_tmp != null)
					{
						attributo = true;
						tag_Padre = qName;
					}

					if(attributo)
					{
						//System.out.println("Start Element :" + qName);

						for (String tag : tags)
						{
							if (qName.equalsIgnoreCase(tag))
							{
								el_toFind.put(tag, new Boolean(true));
							}
						}

					}
						}

				public void endElement(String uri, String localName,
						String qName) throws SAXException
						{
					if(qName == tag_Padre)
					{
						attributo = false;
					}
						}

				public void characters(char ch[], int start, int length) throws SAXException
				{
					if(attributo)
					{

						for(String tmp : tags)
						{	
							if(el_toFind.get(tmp).booleanValue())
							{
								valori.add(new Tag(tag_Padre, tmp, new String(ch, start, length), attr_tmp));
								el_toFind.put(tmp, new Boolean(false));
							}
						}
					}
				}
			};

			//Apro il file e creo una struttura di input per la gestione del contenuto
			File file = new File(nomeFile);
			InputStream inputStream= new FileInputStream(file);
			Reader reader = new InputStreamReader(inputStream,"UTF-8");
			InputSource is = new InputSource(reader);
			//Setto la condifica dei dati in utf8
			is.setEncoding("UTF-8");

			//effettuo il parsing del contenuto del file;
			saxParser.parse(file, handler);

			// STAMPA DI PROVA DELLA LISTA DEI TAG TROVATI
			/*
			System.out.println("\n\n||||||||||||||||||| STAMPA LISTA |||||||||\n");
			for(Tag t : valori)
			{
				System.out.println(t.toString());
			}
			*/
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return valori;
	}

}