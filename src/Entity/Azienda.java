package Entity;
/**
 * @author Alessandro Basile
 * @version 1.0
 */
/*
 * Classe per la gestione delle Aziende;
 */
public class Azienda
{
	private String cod_Azienda;
	private String ragione_soc;
	private String p_IVA;
	private String descrizione;
	private String indirizzo;
	private String localita;
	private Integer cap;
	private String prov;
	
	public Azienda()
	{
		
	}
	
	public Azienda(String cod_Azienda,
				   String ragione_soc,
				   String p_IVA,
				   String descrizione,
				   String indirizzo,
				   String localita,
				   Integer cap,
				   String prov)
	{
		this.setCod_Azienda(cod_Azienda);
		this.setRagione_soc(ragione_soc);
		this.setP_IVA(p_IVA);
		this.setDescrizione(descrizione);
		this.setIndirizzo(indirizzo);
		this.setLocalita(localita);
		this.setCap(cap);
		this.setProv(prov);
	}
	
	/*
	 *	Metodo per gestire l'ugualianza tra due aziende
	 */
	
	public String toString()
	{
		return cod_Azienda + " " + ragione_soc + " " + p_IVA + " " +
			   descrizione + " " + indirizzo + " " + localita + " " +
			   cap + " " + prov;	
	}
	
	private static boolean check_Equals(Object o1, Object o2)
	{
		if(o1 != null && o2 != null)
			return o1.equals(o2);
		else if(o1 == null && o2 == null)
			return true;
		else return false;
	}
	
	public boolean equals(Azienda az2)
	{
		return (check_Equals(this.cod_Azienda, az2.getCod_Azienda()) &&
				check_Equals(this.ragione_soc, az2.getRagione_soc()) &&
				check_Equals(this.p_IVA, az2.getP_IVA()) &&
				check_Equals(this.descrizione, az2.getDescrizione()) &&
				check_Equals(this.indirizzo, az2.getIndirizzo()) &&
				check_Equals(this.localita, az2.getLocalita()) &&
				check_Equals(this.cap, az2.getCap()) &&
				check_Equals(this.prov, az2.getProv()));
	}
	
	/**
	 * Genero uno stringa descrittiva per il salvataggio dei dati nel DB
	 * @return String
	 */
	
	public String StringToDb()
	{
		String values;
		
		values = "\""+this.cod_Azienda+"\", \"";
		
		if(this.ragione_soc != null)
			values += this.ragione_soc;
		values += "\", \"";
		
		if(this.p_IVA != null)
			values += this.p_IVA;
		values += "\", \"";
		
		if(this.descrizione != null)
			values += this.descrizione;
		values += "\", \"";
		
		if(this.indirizzo != null)
			values += this.indirizzo;
		values += "\", \"";
		
		if(this.localita != null)
			values += this.localita;
		values += "\", \"";
		
		if(this.cap != null)
			values += this.cap.toString();
		values += "\", \"";
		
		if(this.prov != null)
			values += this.prov;
		values += "\"";
		
		return values;
	}

	public String getCod_Azienda() {
		return cod_Azienda;
	}

	public void setCod_Azienda(String cod_Azienda) {
		this.cod_Azienda = cod_Azienda;
	}

	public String getRagione_soc() {
		return ragione_soc;
	}

	public void setRagione_soc(String ragione_soc) {
		this.ragione_soc = ragione_soc;
	}

	public String getP_IVA() {
		return p_IVA;
	}

	public void setP_IVA(String p_IVA) {
		this.p_IVA = p_IVA;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public Integer getCap() {
		return cap;
	}

	public void setCap(Integer cap) {
		this.cap = cap;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

}
