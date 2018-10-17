package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Epreuve;
import bo.Proposition;
import bo.Question;
import bo.QuestionTirage;
import bo.Section;
import dal.DALException;
import dal.DAOFactory;

public class DoStartEpreuve extends HttpServlet {

	private static final String EN_COURS = "EC";
	private static final String EN_ATTENTE = "EA";
	Epreuve epreuve = null;
	List<QuestionTirage> allQuestions = new ArrayList();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String[] params = uri.split("/");
		String questionNum = params[params.length-1];
		
		HttpSession session = req.getSession();
		int nbQuestionsTotal = 0;
		List<Section> sections = new ArrayList();
		
		try {
			epreuve = DAOFactory.getEpreuveDAO().selectById(Integer.valueOf(req.getParameter("idEpreuve")));
			sections = DAOFactory.getSectionDAO().selectByIdTest(epreuve.getIdEpreuve());
		} catch (NumberFormatException | DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("currentEpreuveId", epreuve.getIdEpreuve());

		//Calcul du nombre de questions dans le test
		for (Section section : sections)
		{
			nbQuestionsTotal += section.getNbQuestionATirer();
		}
		
		session.setAttribute("nbQuestionsTotal", nbQuestionsTotal);
		
		//marquer la question
		if (req.getParameterMap().containsKey("mark"))
		{
			try {
				DAOFactory.getQuestionTirageDAO().updateMarque(Boolean.valueOf(req.getParameter("mark")), Integer.valueOf(req.getParameter("idQuestion")), Integer.valueOf(req.getParameter("idEpreuve")));
			} catch (NumberFormatException | DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (null == session.getAttribute("answers"))
		{
			session.setAttribute("answers", new ArrayList() {{ add(new ArrayList() {{ add("-1"); }}); }});
		}
		
		if (questionNum.equals("start"))
		{	
			if (epreuve.getEtat().equals(EN_ATTENTE))
			{
				generateQuestions(epreuve);
			}

			
			if(session.getAttribute("answers") == null)
			{
				session.setAttribute("answers", new ArrayList<ArrayList<String>>());
			}
			
			recupererQuestion(session, 1);
				
			this.getServletContext().getRequestDispatcher("/candidat/epreuve/question.jsp").forward(req, resp);
		}
		else if(questionNum.equals("terminer"))
		{
			int note;
			int epreuveId = epreuve.getIdEpreuve();
			String niveau;
			
			updateAnswers(req.getParameterValues("proposition"), session, req.getParameter("idQuestion"));
			
			note = calculNote(session);
			
			System.out.println("note : " + note);
			
			if(note >= epreuve.getTest().getSeuilHaut())
			{
				niveau = "A";
			}
			else if (note <= epreuve.getTest().getSeuilBas())
			{
				niveau = "NA";
			}
			else
			{
				niveau = "ECA";
			}
			
			try {
				DAOFactory.getEpreuveDAO().updateNote(epreuveId, note, niveau);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.setAttribute("note", note);
			
			this.getServletContext().getRequestDispatcher("/candidat/epreuve/terminer.jsp").forward(req, resp);
		}
		else if(isNumeric(questionNum))
		{
			
			if (req.getParameterValues("proposition") != null)
			{
				updateAnswers(req.getParameterValues("proposition"), session, req.getParameter("idQuestion"));
			}
			
			if (Integer.valueOf(questionNum) <= nbQuestionsTotal)
			{
				recupererQuestion(session, Integer.valueOf(questionNum));
				
				System.out.println("Questions total : " + allQuestions.size() + " current : " + questionNum);
				
				this.getServletContext().getRequestDispatcher("/candidat/epreuve/question.jsp").forward(req, resp);
			}
			else
			{
				System.out.println("La question n'existe pas");
			}
		}
	}
	
	private int calculNote(HttpSession session) {
		int note = 0;
		int total = 0;
		
		ArrayList<ArrayList<String>> answers = new ArrayList<ArrayList<String>>();
		
		answers = (ArrayList<ArrayList<String>>) session.getAttribute("answers");
		
		for (ArrayList<String> answerList : answers)
		{
			int i = 0;
			int idQuestion;
			int bonneReponsesDonnees = 0;
			Question question = null;
			List<Proposition> bonnesPropositions = null;
			
			for (String answer : answerList)
			{
				i++;
				
				if (i == 1)
				{
					//Récupération de la question
					idQuestion = Integer.valueOf(answer);
					try {
						question = DAOFactory.getQuestionDAO().selectById(idQuestion);
					} catch (DALException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//récupération des propositions bonnes
					try {
						bonnesPropositions = DAOFactory.getPropositionDAO().selectByEstBonne(idQuestion);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					//comparaison des réponses choisies
					for (Proposition bonnesProposition : bonnesPropositions)
					{
						if (Integer.valueOf(answer) == bonnesProposition.getIdProposition())
						{
							bonneReponsesDonnees ++;
						}
					}
				}
				
				//Attribution des points
				if (bonneReponsesDonnees == bonnesPropositions.size())
				{
					note += question.getPoints();
				}
				
				total += question.getPoints();
			}
		}
		
		//Pourcentagation
		note = (note / total)*100;
		
		return note;
	}

	private void updateAnswers(String[] results, HttpSession session, String idQuestion) {
		ArrayList<ArrayList<String>> answers = new ArrayList<ArrayList<String>>();
		ArrayList<String> answer = new ArrayList();
		
		answers = (ArrayList<ArrayList<String>>) session.getAttribute("answers");
		
		answer.add(idQuestion);
		
		if (results != null)
		{
			int i = 0;
			
			//suppression de la liste si question déjà répondue
			for (Iterator<ArrayList<String>> iter = answers.listIterator(); iter.hasNext();)
			{
				ArrayList<String> nanswer = iter.next();
				if (nanswer.get(0).equals(idQuestion))
				{
					iter.remove();
				}
				i ++;
			}
			
			for (String result : results) {
				answer.add(result); 
			}
			
			answers.add(answer);
			
			session.setAttribute("answers", answers);
		}
		
		//Affichage des réponses données
		for (ArrayList<String> nanswer : answers)
		{
			System.out.print("Question :");
			for (String panswer : nanswer)
			{
				System.out.print(" " + panswer);
			}
			System.out.println();
		}
	}

	private void recupererQuestion(HttpSession session, int numQuestion)
	{
		int epreuveId = epreuve.getIdEpreuve();
		System.out.println(epreuveId);
		QuestionTirage question = null;
		
		question = DAOFactory.getQuestionTirageDAO().selectByIdEpreuve(epreuveId, numQuestion);
		allQuestions = DAOFactory.getQuestionTirageDAO().selectAllByIdEpreuve(epreuve.getIdEpreuve());

		session.setAttribute("questionTirage", question);
		session.setAttribute("allQuestions", allQuestions);
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
				DAOFactory.getEpreuveDAO().updateEtat(epreuve);
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

				QuestionTirage questionTirage = questionToTirage(question, epreuve, index);
				
				try {
					DAOFactory.getQuestionTirageDAO().insert(questionTirage);
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
