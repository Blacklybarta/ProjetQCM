package servlet.epreuve;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Epreuve;
import bo.Promotion;
import bo.Test;
import bo.Utilisateur;
import dal.DALException;
import dal.DAOFactory;

public class DoCreerEpreuve extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (String.valueOf(session.getAttribute("collaborateur")).equals("true")) {
			try {
				List<Test> listeTest = DAOFactory.getTestDAO().selectAll();
				List<Utilisateur> listeUtilisateur = DAOFactory.getUtilisateurDAO().selectAll();
				req.setAttribute("listeTests", listeTest);
				req.setAttribute("listeUtilisateurs", listeUtilisateur);
			} catch (DALException e) {
				e.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher("/collaborateur/epreuve/addEpreuve.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "Droit insuffisant, prendre contact avec un ADMINISTRATEUR");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Epreuve epreuve = new Epreuve();
			Test test = new Test();
			Utilisateur utilisateur = new Utilisateur();
			Date debut = Date.valueOf(req.getParameter("dateDebut"));
			Date fin = Date.valueOf(req.getParameter("dateFin"));
			epreuve.setDateDebutValidite(debut);
			epreuve.setDateFinValidite(fin);
			epreuve.setEtat("EA");
			test.setIdTest(Integer.parseInt(req.getParameter("idTest")));
			epreuve.setTest(test);
			utilisateur.setIdUtilistaeur(Integer.parseInt(req.getParameter("idUser")));
			epreuve.setUtilisateur(utilisateur);
			DAOFactory.getEpreuveDAO().insert(epreuve);
			resp.sendRedirect("/ProjetQCM/validerAcces");
		} catch (NumberFormatException e) {			
			e.printStackTrace();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}


}
