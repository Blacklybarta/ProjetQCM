package bo;

public class Proposition {

	private int idProposition;
	private String enonce;
	private boolean estBonne;
	private Question question;
	
	public Proposition() {
		super();
	}

	public int getIdProposition() {
		return idProposition;
	}

	public void setIdProposition(int idProposition) {
		this.idProposition = idProposition;
	}

	public String getEnonce() {
		return enonce;
	}

	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}

	public boolean isEstBonne() {
		return estBonne;
	}

	public void setEstBonne(boolean estBonne) {
		this.estBonne = estBonne;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Proposition [enonce=" + enonce + ", estBonne=" + estBonne + "]";
	}
	
	
	
}
