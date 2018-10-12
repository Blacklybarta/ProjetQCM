package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Epreuve;
import bo.Proposition;
import bo.Question;
import bo.QuestionTirage;
import bo.Section;
import bo.Test;
import bo.Utilisateur;
import dal.DALException;
import dal.DAO;
import dal.DBConnection;

public class EpreuveDAOImplJDBC implements DAO<Epreuve>{

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Epreuve> listeEpreuves = new ArrayList<>();
	
	private static final String SQL_SELECT_BY_CANDIDAT = "SELECT * FROM EPREUVE WHERE idUtilisateur=?";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM EPREUVE WHERE idEpreuve=?";
	private static final String SQL_UPDATE = "UPDATE EPREUVE SET etat=? WHERE idepreuve=?";
	
	public void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = null;
		}
	}
	
	@Override
	public void insert(Epreuve data) throws DALException {
		// TODO Auto-generated method stub	
	}

	@Override
	public void update(Epreuve data) throws DALException {
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, data.getEtat());			
			pstmt.setInt(2, data.getIdEpreuve());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Update theme failed - " + data, e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();
		}		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub	
	}

	@Override
	public Epreuve selectById(int id) throws DALException {
		con = null;
		pstmt = null;
		ResultSet rs = null;
		Epreuve epreuve = new Epreuve();
		
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs.next())
			{
				Test test = new Test();
				
				epreuve.setIdEpreuve(rs.getInt("idepreuve"));
				epreuve.setDateDebutValidite(rs.getDate("dateDebutValidite"));
				epreuve.setDateFinValidite(rs.getDate("dateFinValidite"));
				epreuve.setTempsEcoule(rs.getInt("tempsEcoule"));
				epreuve.setEtat(rs.getString("etat"));
				epreuve.setNoteObtenu(rs.getFloat("note_obtenu"));
				epreuve.setNiveauObtenu(rs.getString("niveau_obtenu"));
				epreuve.setTest(rs.getInt("idTest"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		return epreuve;
	}
	
	public List<Epreuve> selectCandidatEpreuve(int idUtilistaeur){
		// TODO Auto-generated method stub
		con = null;
		pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_CANDIDAT);
			pstmt.setInt(1, idUtilistaeur);

			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Epreuve epreuve = new Epreuve();
				Test test = new Test();
				
				epreuve.setIdEpreuve(rs.getInt("idepreuve"));
				epreuve.setDateDebutValidite(rs.getDate("dateDebutValidite"));
				epreuve.setDateFinValidite(rs.getDate("dateFinValidite"));
				epreuve.setTempsEcoule(rs.getInt("tempsEcoule"));
				epreuve.setEtat(rs.getString("etat"));
				epreuve.setNoteObtenu(rs.getFloat("note_obtenu"));
				epreuve.setNiveauObtenu(rs.getString("niveau_obtenu"));
				epreuve.setTest(rs.getInt("idTest"));
				
				listeEpreuves.add(epreuve);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		return listeEpreuves;
	}

	@Override
	public Epreuve selectByIdentifiant(String identifiant, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Epreuve> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Epreuve> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Epreuve selectByUtilisateur(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Epreuve> selectAllById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertWithIdReturn(Epreuve data) throws DALException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Section> selectByIdTest(int idtest) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> selectRandomQuestions(int idTheme, int nbQuestions, List<Question> questions)
			throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionTirage selectByIdEpreuve(int idEpreuve, int nbQuestion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proposition> selectByIdQuestion(int idQuestion) {
		// TODO Auto-generated method stub
		return null;
	}

}
