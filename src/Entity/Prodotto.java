package Entity;

/**
 * @author Alessandro Basile
 * @version 1.0
 */
/*
 * Classe per la gestione dei Prodotti;
 */
public class Prodotto
{
	private String cod_Articolo;
	private String cod_Azienda;
	private int form_prezzo;
	private String descrizione;
	private String unit_misura;
	private float costo_std;
	private float confezioni;
	private float iva;
	private String fornitore;
	private String nota1;
	private String nota2;
	
	public Prodotto()
	{
		
	}
	
	public Prodotto(String cod_Art,
					String cod_Az,
					int riga_prezzo,
					String descr,
					String um,
					float cost_s,
					float conf,
					float iva,
					String fornitore,
					String nota1,
					String nota2)
	{
		this.setCod_Articolo(cod_Art);
		this.setCod_Azienda(cod_Az);
		this.setForm_prezzo(riga_prezzo);
		this.setDescrizione(descr);
		this.setUnit_misura(um);
		this.setCosto_std(cost_s);
		this.setConfezioni(conf);
		this.setIva(iva);
		this.setFornitore(fornitore);
		this.setNota1(nota1);
		this.setNota2(nota2);
	}

	public String getCod_Articolo() {
		return cod_Articolo;
	}

	public void setCod_Articolo(String cod_Articolo) {
		this.cod_Articolo = cod_Articolo;
	}

	public String getCod_Azienda() {
		return cod_Azienda;
	}

	public void setCod_Azienda(String cod_Azienda) {
		this.cod_Azienda = cod_Azienda;
	}

	public int getForm_prezzo() {
		return form_prezzo;
	}

	public void setForm_prezzo(int form_prezzo) {
		this.form_prezzo = form_prezzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getUnit_misura() {
		return unit_misura;
	}

	public void setUnit_misura(String unit_misura) {
		this.unit_misura = unit_misura;
	}

	public float getCosto_std() {
		return costo_std;
	}

	public void setCosto_std(float costo_std) {
		this.costo_std = costo_std;
	}

	public float getConfezioni() {
		return confezioni;
	}

	public void setConfezioni(float confezioni) {
		this.confezioni = confezioni;
	}

	public float getIva() {
		return iva;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

	public String getNota1() {
		return nota1;
	}

	public void setNota1(String nota1) {
		this.nota1 = nota1;
	}

	public String getNota2() {
		return nota2;
	}

	public void setNota2(String nota2) {
		this.nota2 = nota2;
	}

	public String getFornitore() {
		return fornitore;
	}

	public void setFornitore(String fornitore) {
		this.fornitore = fornitore;
	}
	

}
