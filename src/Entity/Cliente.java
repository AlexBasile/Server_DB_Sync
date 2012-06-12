package Entity;

/**
 * @author Alessandro Basile
 * @version 1.0
 */
/*
 * Classe per la gestione dell'entita' Cliente;
 */
public class Cliente
{
	private String cod_rag;
	private String cod_cliente;
	private String rag_sociale;
	private String p_iva;
	private String indirizzo;
	private Integer cap;
	private String localita;
	private String prov;
	private String loc_spedizione;
	private String telefono;
	
	
	public Cliente()
	{
		
	}
	
	public Cliente(String codR,
				   String codC,
				   String rag_s,
				   String pIVA,
				   String address,
				   Integer cod_AP,
				   String local,
				   String pr,
				   String spediz,
				   String tel)
	{
		this.setCod_rag(codR);
		this.setCod_cliente(codC);
		this.setRag_sociale(rag_s);
		this.setP_iva(pIVA);
		this.setIndirizzo(address);
		this.setCap(cod_AP);
		this.setLocalita(local);
		this.setLoc_spedizione(spediz);
		this.setProv(pr);
		this.setTelefono(tel);
	}
	
	//METODI PER LEGGERE E SETTARE LE VARIABILI DEGLI
	//OGGETTI DI TIPO CLIENTE
	
	private static boolean check_Equals(Object o1, Object o2)
	{
		if(o1 != null && o2 != null)
			return o1.equals(o2);
		else if(o1 == null && o2 == null)
			return true;
		else return false;
	}
	
	/*
	 * 	Metodo per la gestione dell'ugualianza tra entita' di clienti
	 */
	public boolean equals(Cliente c2)
	{
		return (check_Equals(this.cod_rag, c2.getCod_rag()) &&
				check_Equals(this.cod_cliente, c2.getCod_cliente()) &&
				check_Equals(this.rag_sociale, c2.getRag_sociale()) &&
				check_Equals(this.p_iva, c2.getP_iva()) &&
				check_Equals(this.indirizzo, c2.getIndirizzo()) &&
				check_Equals(this.cap, c2.getCap()) &&
				check_Equals(this.localita, c2.getLocalita()) &&
				check_Equals(this.loc_spedizione, c2.getLoc_spedizione()) &&
				check_Equals(this.prov, c2.getProv()) &&
				check_Equals(this.telefono, c2.getTelefono()));
	}
	
	public String StringToDb()
	{
		String values;
		
		//Scrivo i valori sicuramente diversi da null
		values = "\""+this.cod_cliente+"\", \""+this.cod_rag+"\", \"";
		
		//effettuo i controlli su tutto i valori successivi
		if(this.rag_sociale != null)
			values += this.rag_sociale.replace("\"", " ");
		values += "\", \"";
		
		if(this.p_iva != null)
			values += this.p_iva;
		values += "\", \"";
		
		if(this.indirizzo != null)
			values += this.indirizzo;
		values += "\", \"";
		
		if(this.cap != null)
			values += this.cap;
		values += "\", \"";
		
		if(this.localita != null)
			values += this.localita;
		values += "\", \"";
		
		if(this.loc_spedizione != null)
			values += this.loc_spedizione;
		values += "\", \"";
		
		if(this.prov != null)
			values += this.prov;
		values += "\", \"";
		
		if(this.telefono != null)
			values += this.telefono;
		values += "\"";
		
		return values;
	}
	
	public String getCod_cliente() {
		return cod_cliente;
	}

	public void setCod_cliente(String cod_cliente) {
		this.cod_cliente = cod_cliente;
	}

	public String getRag_sociale() {
		return rag_sociale;
	}

	public void setRag_sociale(String rag_sociale) {
		this.rag_sociale = rag_sociale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getP_iva() {
		return p_iva;
	}

	public void setP_iva(String p_iva) {
		this.p_iva = p_iva;
	}

	public Integer getCap() {
		return cap;
	}

	public void setCap(Integer cap) {
		this.cap = cap;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getLoc_spedizione() {
		return loc_spedizione;
	}

	public void setLoc_spedizione(String loc_spedizione) {
		this.loc_spedizione = loc_spedizione;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	/**
	 * @return the cod_rag
	 */
	public String getCod_rag() {
		return cod_rag;
	}

	/**
	 * @param cod_rag the cod_rag to set
	 */
	public void setCod_rag(String cod_rag) {
		this.cod_rag = cod_rag;
	}
}
