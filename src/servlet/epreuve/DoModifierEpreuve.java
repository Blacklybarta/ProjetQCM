package servlet.epreuve;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Epreuve;
import bo.Test;
import bo.Utilisateur;
import dal.DALException;
import dal.DAOFactory;

public class DoModifierEpreuve extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
	
		if (String.valueOf(session.getAttribute("collaborateur")).equals("true")) {	
			try {
				List<Epreuve> listeEpreuve = DAOFactory.getEpreuveDAO().selectAll();
				req.setAttribute("listeEpreuves", listeEpreuve);
			} catch (DALException e) {
				e.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher("/collaborateur/epreuve/updateEpreuve.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "Droit insuffisant, prendre contact avec un ADMINISTRATEUR");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Epreuve epreuve = null;
		Test test = new Test();
		Utilisateur utilisateur = new Utilisateur();
		try {
			if (String.valueOf(req.getParameter("select")).equals("true")) {
				String[] values = req.getParameterValues("idEpreuve");
				epreuve = DAOFactory.getEpreuveDAO().selectById(Integer.parseInt(values[0]));
				req.setAttribute("epreuve", epreuve);
				List<Utilisateur> listeUtilisateur = DAOFactory.getUtilisateurDAO().selectAll();
				req.setAttribute("listeUtilisateurs", listeUtilisateur);
				List<Test> listeTest = DAOFactory.getTestDAO().selectAll();
				req.setAttribute("listeTests", listeTest);
				this.getServletContext().getRequestDispatcher("/collaborateur/epreuve/updateEpreuve.jsp")
						.forward(req, resp);
			} else if (String.valueOf(req.getParameter("update")).equals("true")) {
				epreuve = new Epreuve();
				Date debut = Date.valueOf(req.getParameter("dateDebut"));
				Date fin = Date.valueOf(req.getParameter("dateFin"));
				epreuve.setDateDebutValidite(debut);
				epreuve.setDateFinValidite(fin);
				epreuve.setEtat("EA");
				test.setIdTest(Integer.parseInt(req.getParameter("idTest")));
				epreuve.setTest(test);
				utilisateur.setIdUtilistaeur(Integer.parseInt(req.getParameter("idUser")));
				epreuve.setUtilisateur(utilisateur);
				epreuve.setIdEpreuve(Integer.parseInt(req.getParameter("id")));
				DAOFactory.getEpreuveDAO().update(epreuve);
				resp.sendRedirect("/ProjetQCM/validerAcces");
			}
		} catch (NumberFormatException | DALException e) {
			req.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
}
