package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Epreuve;
import bo.Question;
import bo.QuestionTirage;
import bo.Section;
import dal.DALException;
import dal.DAOFactory;

public class DoStartEpreuve extends HttpServlet {

	private static final String EN_COURS = "EC";
	private static final String EN_ATTENTE = "EA";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String[] params = uri.split("/");
		String questionNum = params[params.length-1];
		
		HttpSession session = req.getSession();
		
		System.out.println(questionNum);
		
		if (questionNum.equals("start"))
		{
			Epreuve epreuve = null;
			
			try {
				epreuve = DAOFactory.getEpreuveDAO().selectById(Integer.valueOf(req.getParameter("idEpreuve")));
			} catch (NumberFormatException | DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.setAttribute("currentEpreuveId", epreuve.getIdEpreuve());
			
			if (epreuve.getEtat().equals(EN_ATTENTE))
			{
				generateQuestions(epreuve);
			}
			else if (epreuve.getEtat().equals(EN_COURS))
			{
				recupererQuestion(session, 1);
				
				this.getServletContext().getRequestDispatcher("/candidat/epreuve/question.jsp").forward(req, resp);
			}
		}
		else if(isNumeric(questionNum))
		{
			recupererQuestion(session, Integer.valueOf(questionNum));
			
			this.getServletContext().getRequestDispatcher("/candidat/epreuve/question.jsp").forward(req, resp);
		}
	}
	
	private void recupererQuestion(HttpSession session, int numQuestion)
	{
		int epreuveId = (Integer) session.getAttribute("currentEpreuveId");
		System.out.println(epreuveId);
		QuestionTirage question = null;
		
		question = DAOFactory.getQuestionTirageDAO().selectByIdEpreuve(epreuveId, numQuestion);

		session.setAttribute("questionTirage", question);
	}
	
	private void generateQuestions(Epreuve epreuve)
	{
		List<Section> sections = new ArrayList();
		List<Question> questions = new ArrayList();
		
		System.out.println("Etat = " + epreuve.getEtat());
		
		if (epreuve.getEtat().equals(EN_ATTENTE))
		{
			epreuve.setEtat(EN_COURS);
			
			//Récupération des sections de l'épreuve
			try {
				DAOFactory.getEpreuveDAO().update(epreuve);
				sections = DAOFactory.getSectionDAO().selectByIdTest(epreuve.getTest().getIdTest());
			} catch (DALException e) {
				e.printStackTrace();
			}
			
			for (Section section : sections)
			{
				int idTheme = section.getTheme().getIdTheme();
				int nbQuestions = section.getNbQuestionATirer();

				//Récupération des questions de la section
				try {
					questions = DAOFactory.getQuestionDAO().selectRandomQuestions(idTheme, nbQuestions, questions);
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			Collections.shuffle(questions);
			
			//Création des QuestionsTirage
			int index = 0;
			for (Question question : questions)
			{
				index ++;

				System.out.println("On y passe 1");
				System.out.println(question.getEnonce());
				QuestionTirage questionTirage = questionToTirage(question, epreuve, index);
				
				try {
					DAOFactory.getQuestionTirageDAO().insert(questionTirage);

					System.out.println("On y passe 2");
					System.out.println(questionTirage.getQuestion().getEnonce());
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	private QuestionTirage questionToTirage(Question question, Epreuve epreuve, int index)
	{
		QuestionTirage qTirage = new QuestionTirage();
		
		qTirage.setEpreuve(epreuve);
		qTirage.setNumOrdre(index);
		qTirage.setQuestion(question);
		
		return qTirage;
	}
}
