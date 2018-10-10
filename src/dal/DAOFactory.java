package dal;

import bo.Epreuve;
import bo.Test;
import bo.Theme;
import bo.Utilisateur;
import jdbc.EpreuveDAOImplJDBC;
import jdbc.TestDAOImplJDBC;
import jdbc.ThemeDAOImplJDBC;
import jdbc.UtilisateurDAOImplJDBC;

public class DAOFactory {
	
	public static DAO<Utilisateur> getUtilisateurDAO(){
		DAO<Utilisateur> utilisateurDAO = null;
		utilisateurDAO = (DAO<Utilisateur>) new UtilisateurDAOImplJDBC();
		return utilisateurDAO;
	}
	
	public static DAO<Epreuve> getEpreuveDAO(){
		DAO<Epreuve> epreuveDAO = null;
		epreuveDAO = (DAO<Epreuve>) new EpreuveDAOImplJDBC();
		return epreuveDAO;
	}
	
	public static DAO<Theme> getThemeDAO(){
		DAO<Theme> themeDAO = null;
		themeDAO = (DAO<Theme>) new ThemeDAOImplJDBC();
		return themeDAO;
	}
	
	public static DAO<Test> getTestDAO(){
		DAO<Test> testDAO = null;
		testDAO = (DAO<Test>) new TestDAOImplJDBC();
		return testDAO;
	}
}