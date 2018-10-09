package bo;

import java.time.LocalDate;
import java.util.List;

public class Epreuve {

	private int idEpreuve;
	private LocalDate dateDebutValiite;
	private LocalDate dateFinValidite;
	private int tempsEcoule;
	private String etat;
	private float noteObtenu;
	private String niveauObtenu;
	private Test idTest;
	private List<QuestionTirage> listeQuestions;

	public Epreuve() {
		super();
	}

	public int getIdEpreuve() {
		return idEpreuve;
	}

	public void setIdEpreuve(int idEpreuve) {
		this.idEpreuve = idEpreuve;
	}

	public LocalDate getDateDebutValiite() {
		return dateDebutValiite;
	}

	public void setDateDebutValiite(LocalDate dateDebutValiite) {
		this.dateDebutValiite = dateDebutValiite;
	}

	public LocalDate getDateFinValidite() {
		return dateFinValidite;
	}

	public void setDateFinValidite(LocalDate dateFinValidite) {
		this.dateFinValidite = dateFinValidite;
	}

	public int getTempsEcoule() {
		return tempsEcoule;
	}

	public void setTempsEcoule(int tempsEcoule) {
		this.tempsEcoule = tempsEcoule;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public float getNoteObtenu() {
		return noteObtenu;
	}

	public void setNoteObtenu(float noteObtenu) {
		this.noteObtenu = noteObtenu;
	}

	public String getNiveauObtenu() {
		return niveauObtenu;
	}

	public void setNiveauObtenu(String niveauObtenu) {
		this.niveauObtenu = niveauObtenu;
	}

	public Test getIdTest() {
		return idTest;
	}

	public void setIdTest(Test idTest) {
		this.idTest = idTest;
	}

	public List<QuestionTirage> getListeQuestions() {
		return listeQuestions;
	}

	public void setListeQuestions(List<QuestionTirage> listeQuestions) {
		this.listeQuestions = listeQuestions;
	}

	@Override
	public String toString() {
		return "Epreuve [idEpreuve=" + idEpreuve + ", noteObtenu=" + noteObtenu + ", niveauObtenu=" + niveauObtenu
				+ ", idTest=" + idTest + "]";
	}

}
