package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
						req.setAttribute("utilisateur", utilisateur);
						this.getServletContext().getRequestDispatcher("/admin/gestion.jsp").forward(req, resp);
					} else if (utilisateur.isCandidat()) {
						req.setAttribute("utilisateur", utilisateur);
						this.getServletContext().getRequestDispatcher("/user/gestion.jsp").forward(req, resp);
					}
				}
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			resp.sendRedirect("/ProjetQCM/connexion.html");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		System.out.println(email);
		System.out.println(password);
		HttpSession session = req.getSession();
		resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Expires", "0");
		Utilisateur utilisateur = null;
		try {
			utilisateur = DAOFactory.getUtilisateurDAO().selectByIdentifiant(email, password);
			System.out.println(utilisateur);
			if (utilisateur != null) {
				if (utilisateur.isCandidat()) {
					req.setAttribute("utilisateur", utilisateur);
					session.setAttribute("idUtilisateur", utilisateur.getIdUtilistaeur());
					session.setAttribute("nomUtilisateur", utilisateur.getNom());
					session.setAttribute("candidat", utilisateur.isCandidat());
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
