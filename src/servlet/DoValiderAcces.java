package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Epreuve;
import bo.Question;
import bo.Theme;
import bo.Utilisateur;
import dal.DALException;
import dal.DAOFactory;

public class DoValiderAcces extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Utilisateur utilisateur = null;
		if (session.getAttribute("idUtilisateur") != null) {
			try {
				utilisateur = DAOFactory.getUtilisateurDAO().selectById((int) session.getAttribute("idUtilisateur"));
				if (utilisateur != null) {
					if (utilisateur.isCollaborateur()) {	
						List<Question> listeQuestion = DAOFactory.getQuestionDAO().selectAll();
						List<Utilisateur> listeUtilisateurs = DAOFactory.getUtilisateurDAO().selectAll();
						List<Theme> listeThemes = DAOFactory.getThemeDAO().selectAll();
						req.setAttribute("listeThemes", listeThemes);
						req.setAttribute("listeUtilisateurs", listeUtilisateurs);
						req.setAttribute("listeQuestions", listeQuestion);
						req.setAttribute("utilisateur", utilisateur);
						this.getServletContext().getRequestDispatcher("/collaborateur/gestion.jsp").forward(req, resp);
					} else if (utilisateur.isCandidat()) {
						req.setAttribute("utilisateur", utilisateur);
						this.getServletContext().getRequestDispatcher("/candidat/gestion.jsp").forward(req, resp);
					}
				}
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			resp.sendRedirect("/ProjetQCM/connexion.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Expires", "0");
		Utilisateur utilisateur = null;
		List<Epreuve> epreuves = new ArrayList<>();
		try {
			utilisateur = DAOFactory.getUtilisateurDAO().selectByIdentifiant(email, password);
			int idUser = utilisateur.getIdUtilistaeur();
			epreuves = DAOFactory.getEpreuveDAO().selectCandidatEpreuve(idUser);
			if (utilisateur != null) {
				if (utilisateur.isCandidat()) {
					req.setAttribute("utilisateur", utilisateur);
					session.setAttribute("idUtilisateur", utilisateur.getIdUtilistaeur());
					session.setAttribute("nomUtilisateur", utilisateur.getNom());
					session.setAttribute("candidat", utilisateur.isCandidat());
					
					session.setAttribute("epreuves", epreuves);
					
					this.getServletContext().getRequestDispatcher("/candidat/gestion.jsp").forward(req, resp);
				} else if (utilisateur.isCollaborateur()) {
					req.setAttribute("utilisateur", utilisateur);
					session.setAttribute("idUtilisateur", utilisateur.getIdUtilistaeur());
					session.setAttribute("nomUtilisateur", utilisateur.getNom());
					session.setAttribute("collaborateur", utilisateur.isCollaborateur());
					this.getServletContext().getRequestDispatcher("/collaborateur/gestion.jsp").forward(req, resp);
				} else {
					req.setAttribute("error", "Compte invalide - Ni candidat, Ni formateur");
					this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
				}
			} else {
				req.setAttribute("error", "Compte invalide - Utilisateur == null (DoValiderAcces)");
				this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
			}
		} catch (DALException e) {
			req.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
	
}
