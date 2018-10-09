package bo;

import java.util.List;

import javafx.scene.image.Image;

public class Question {

	private int idQuestion;
	private String enonce;
	private Image image;
	private int points;
	private Theme theme;
	private List<Proposition> listeProposition;
	
	
	public Question() {
		super();
	}

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public String getEnonce() {
		return enonce;
	}

	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public List<Proposition> getListeProposition() {
		return listeProposition;
	}

	public void setListeProposition(List<Proposition> listeProposition) {
		this.listeProposition = listeProposition;
	}

	@Override
	public String toString() {
		return "Question [enonce=" + enonce + "]";
	}
	
	
	
	
}
