package bo;

import dal.DALException;
import dal.DAOFactory;

public class QuestionTirage {

	private boolean estMarquee;
	private int numOrdre;
	private Epreuve epreuve;
	private Question question;

	public QuestionTirage() {
		super();
	}

	public boolean isEstMarquee() {
		return estMarquee;
	}

	public void setEstMarquee(boolean estMarquee) {
		this.estMarquee = estMarquee;
	}

	public int getNumOrdre() {
		return numOrdre;
	}

	public void setNumOrdre(int numOrdre) {
		this.numOrdre = numOrdre;
	}

	public Epreuve getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}
	
	public void setEpreuve(int idEpreuve) {
		try {
			this.epreuve = DAOFactory.getEpreuveDAO().selectById(idEpreuve);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public void setQuestion(int idQuestion) {
		try {
			this.question = DAOFactory.getQuestionDAO().selectById(idQuestion);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
