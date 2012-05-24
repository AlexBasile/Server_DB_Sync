package XML_in_out;

/**
 * @author Alessandro Basile
 * @version 1.0
 */

/*
 * Classe per la gestione dei tag trovati nei file XML
 */

public class Tag
{
	private String nomeTag_Padre;
	private String nomeTag;
	private String contenuto;
	private String attributo;
	
	public Tag(String tag_P, String nomeTag, String contenuto, String attributo)
	{
		this.nomeTag_Padre = tag_P;
		this.nomeTag = nomeTag;
		this.contenuto = contenuto;
		this.attributo = attributo;
	}
	
	public String getNomeTag_Padre()
	{
		return nomeTag_Padre;
	}
	
	public String getNomeTag()
	{
		return nomeTag;
	}
	
	public String getContenuto()
	{
		return contenuto;
	}
	
	public String getAttributo()
	{
		return attributo;
	}
	
	public String toString()
	{
		return nomeTag + " " + contenuto + " --- PADRE: " + nomeTag_Padre + "  attr: " + attributo; 
	}
	
	public boolean equals(Tag other)
	{
		return (this.nomeTag_Padre.equalsIgnoreCase(other.getNomeTag_Padre()) &&
				this.nomeTag.equalsIgnoreCase(other.getNomeTag()) &&
				this.contenuto.equalsIgnoreCase(other.getContenuto()) &&
				this.attributo.equalsIgnoreCase(other.getAttributo()));
	}
	
	
}
