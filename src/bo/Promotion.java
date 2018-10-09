package bo;

public class Promotion {

	private int codePromo;
	private String libelle;
	
	public Promotion() {
		super();
	}

	public int getCodePromo() {
		return codePromo;
	}

	public void setCodePromo(int codePromo) {
		this.codePromo = codePromo;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Promotion [codePromo=" + codePromo + ", libelle=" + libelle + "]";
	}
	
	
	
	
}
