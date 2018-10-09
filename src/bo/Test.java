package bo;

import java.util.List;

public class Test {

	private int idTest;
	private String libelle;
	private String description;
	private int duree;
	private int seuilBas;
	private int seuilHaut;
	private List<Section> listeSection;
	
	public Test() {
		super();
	}

	public int getIdTest() {
		return idTest;
	}

	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getSeuilBas() {
		return seuilBas;
	}

	public void setSeuilBas(int seuilBas) {
		this.seuilBas = seuilBas;
	}

	public int getSeuilHaut() {
		return seuilHaut;
	}

	public void setSeuilHaut(int seuilHaut) {
		this.seuilHaut = seuilHaut;
	}

	public List<Section> getListeSection() {
		return listeSection;
	}

	public void setListeSection(List<Section> listeSection) {
		this.listeSection = listeSection;
	}

	@Override
	public String toString() {
		return "Test [idTest=" + idTest + ", libelle=" + libelle + "]";
	}
	
	
	
}
