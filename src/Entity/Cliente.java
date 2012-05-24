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
	private String cod_cliente;
	private String rag_sociale;
	private String p_iva;
	private String indirizzo;
	private int cap;
	private String localita;
	private String loc_spedizione;
	private String prov;
	private String telefono;
	private int zona;
	private String categoria;
	
	public Cliente()
	{
		
	}
	
	public Cliente(String codC,
				   String rag_s,
				   String pIVA,
				   String address,
				   int cod_AP,
				   String local,
				   String spediz,
				   String pr,
				   String tel,
				   int zona,
				   String cat)
	{
		this.setCod_cliente(codC);
		this.setRag_sociale(rag_s);
		this.setP_iva(pIVA);
		this.setIndirizzo(address);
		this.setCap(cod_AP);
		this.setLocalita(local);
		this.setLoc_spedizione(spediz);
		this.setProv(pr);
		this.setTelefono(tel);
		this.setZona(zona);
		this.setCategoria(cat);
	}
	
	//METODI PER LEGGERE E SETTARE LE VARIABILI DEGLI
	//OGGETTI DI TIPO CLIENTE

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
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

	public int getZona() {
		return zona;
	}

	public void setZona(int zona) {
		this.zona = zona;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}
}
