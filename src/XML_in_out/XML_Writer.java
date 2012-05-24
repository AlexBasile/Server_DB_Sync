package XML_in_out;

/**
 * @author Alessandro Basile
 * @version 1.0
 */

import java.io.*;
import java.util.*;

import org.jdom2.*;
import org.jdom2.output.*;

public class XML_Writer {

	private static String gen_attribute = "type"; 

	protected static void writeXML_toFile(String nomeFile, LinkedList<Tag> valori)
	{
		try {
			//IMPOSTO L'ELEMENTO DI ROOT
			Element root = new Element("ROOT");

			//CREO UN NUOVO DOCUMENTO
			Document xml_doc = new Document(root);


			//CREO UNA STRUTTURA DI SUPPORTO
			LinkedList<Tag> support = new LinkedList<Tag>();

			//Prendo il primo valore della lista dei tag e lo aggiungo 
			for(int i = 0; i < valori.size();)
			{
				//Considero il primo valore della lista
				Tag t = valori.get(i);
				
				//Setto il tag padre per quel tag e ne setto l'attributo
				Element tmp = new Element(t.getNomeTag_Padre());
				tmp.setAttribute(gen_attribute, t.getAttributo());
				
				//Aggiungo l'elemento trovato come primo figlio di quel tag
				Element inners = new Element(t.getNomeTag());
				inners.setText(t.getContenuto());
				tmp.addContent(inners);
				
				//Lo aggiungo alla lista di supporto che mi permettera'
				//di mantenere la struttura della stessa lista di partenza
				support.add(t);
				
				//ciclo sugli elementi successivi a quello appena inserito
				int j = i+1;
				while(j < valori.size())
				{
					//Per ogni elemento successivo controllo
					Tag tmp1 = valori.get(j);
					//Che il nuovo tag abbia stesso padre e stesso attributo
					//del primo tag considerato
					if(t.getNomeTag_Padre() == tmp1.getNomeTag_Padre() && t.getAttributo() == tmp1.getAttributo())
					{
						//se e' figlio dello stesso padre allora creo un nuovo elemento
						//setto il contenuto del tag e lo imposto come figlio
						//del tag padre considerato nel primo ciclo
						Element inner_t = new Element(tmp1.getNomeTag());
						inner_t.setText(tmp1.getContenuto());
						tmp.addContent(inner_t);
						
						//Lo aggiungo alla lista di supporto
						support.add(tmp1);
						//Lo elimino dalla lista di partenza in modo da non
						//considerarlo al ciclo successivo
						valori.remove(j);
					}
					// se non e' uguale incremento il valore di j per il prossimo elem
					// NB l'incremento viene saltato se entra nel primo if
					// in quanto se avviene l'eliminazione devo riconsiderare la
					// stessa posizione xke avviene lo shifting;
					else j++;
				}
				
				//Assegno alla radice del documento il padre costruito con tutti i
				//figli assegnatogli
				root.addContent(tmp);
				
				//rimuovo dalla lista di partenza il tag considetato
				valori.remove(i);

			}
			
			//Assegno alla lista di partenza (ormai vuota) la struttura di
			//supporto riempita con tutti TAG;
			for (Tag t : support)
			{
				valori.add(t);
			}
			
			//CREAZIONE DELL'OGGETTO XML di output;
			XMLOutputter outputter = new XMLOutputter();
			//IMPOSTO CHE L'XML SIA BEN FORMATO E LEGGIBILE
			outputter.setFormat(Format.getPrettyFormat());
			//PRODUCO L'OUTPUT NEL FILE
			outputter.output(xml_doc, new FileOutputStream(nomeFile+".xml"));
			
			//System.out.println("File creato:");
			//Stampo l'output anche sullo standard output
			//outputter.output(xml_doc, System.out);

		}catch(Exception e) 
		{
			e.printStackTrace();
		}

	}






}
