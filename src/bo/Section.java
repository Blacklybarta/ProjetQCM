package bo;

import dal.DALException;
import dal.DAOFactory;

public class Section {

	private int nbQuestionATirer;
	private Test test;
	private Theme theme;

	public Section() {
		super();
	}

	public int getNbQuestionATirer() {
		return nbQuestionATirer;
	}

	public void setNbQuestionATirer(int nbQuestionATirer) {
		this.nbQuestionATirer = nbQuestionATirer;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(int idTest) {
		try {
			this.test = DAOFactory.getTestDAO().selectById(idTest);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(int idTheme) {
		try {
			this.theme = DAOFactory.getThemeDAO().selectById(idTheme);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

}
