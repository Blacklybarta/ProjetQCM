package bo;

public class Theme {

	private int idTheme;
	private String libelle;

	public Theme() {
		super();
	}

	public int getIdTheme() {
		return idTheme;
	}

	public void setIdTheme(int idTheme) {
		this.idTheme = idTheme;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Theme [idTheme=" + idTheme + ", libelle=" + libelle + "]";
	}
	
}
