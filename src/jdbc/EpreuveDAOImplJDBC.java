package jdbc;

import java.sql.Connection;
import java.sql.Date;
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
import bo.Theme;
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
	private static final String SQL_UPDATE_ETAT = "UPDATE EPREUVE SET etat=? WHERE idepreuve=?";
	private static final String SQL_UPDATE_NOTE = "UPDATE EPREUVE SET note_obtenu=?, niveau_obtenu=?, etat='T' WHERE idepreuve=?";
	private static final String SQL_SELECTALL = "SELECT * FROM EPREUVE ";
	private static final String SQL_INSERT = "INSERT INTO EPREUVE(dateDebutValidite, dateFinValidite, etat, idTest, idUtilisateur)VALUES(?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE EPREUVE SET dateDebutValidite=?,dateFinValidite=?,etat=?,idTest=?,idUtilisateur=? WHERE idepreuve=?";
	private static final String SQL_DELETE = "DELETE FROM EPREUVE WHERE idepreuve=?";
	
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
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setDate(1, (Date)data.getDateDebutValidite());
			pstmt.setDate(2, (Date) data.getDateFinValidite());
			pstmt.setString(3, data.getEtat());
			pstmt.setInt(4, data.getTest().getIdTest());
			pstmt.setInt(5, data.getUtilisateur().getIdUtilistaeur());

			int nbRows = pstmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					data.setIdEpreuve(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new DALException("Insert theme failed - " + data, e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

			} catch (SQLException e) {
				throw new DALException("close failed - ", e);
			}
			closeConnection();
		}
	}

	@Override
	public void update(Epreuve data) throws DALException {
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE);
			pstmt.setDate(1, (Date) data.getDateDebutValidite());
			pstmt.setDate(2,(Date) data.getDateFinValidite());
			pstmt.setString(3, data.getEtat());
			pstmt.setInt(4, data.getTest().getIdTest());
			pstmt.setInt(5, data.getUtilisateur().getIdUtilistaeur());
			pstmt.setInt(6, data.getIdEpreuve());
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Update utilisateur failed - " + data, e);
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
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_DELETE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("delete epreuve failed - " + e);
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
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_SELECTALL);

			Epreuve epreuve= null;
			listeEpreuves.clear();
			while (rs.next()) {
				epreuve = new Epreuve();
				epreuve.setIdEpreuve(rs.getInt("idepreuve"));
				epreuve.setDateDebutValidite(rs.getDate("dateDebutValidite"));
				epreuve.setDateFinValidite(rs.getDate("dateFinValidite"));
				epreuve.setEtat(rs.getString("etat"));
				if(rs.getString("tempsEcoule") != null){
					epreuve.setTempsEcoule(Integer.parseInt(rs.getString("tempsEcoule")));
				}
				if(rs.getFloat("note_obtenu") != 0){
					epreuve.setNoteObtenu(rs.getFloat("note_obtenu"));
				}
				if(rs.getString("niveau_obtenu") != null){
					epreuve.setNiveauObtenu(rs.getString("niveau_obtenu"));
				}
				epreuve.setTest(rs.getInt("idTest"));
				Utilisateur user = new Utilisateur();
				user.setIdUtilistaeur(rs.getInt("idUtilisateur"));
				epreuve.setUtilisateur(user);			
				listeEpreuves.add(epreuve);
			}
		} catch (SQLException e) {
			throw new DALException("selectAll failed - ", e);
		} finally {
			try {

				if (stmt != null) {
					stmt.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection();
		}
		return listeEpreuves;
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

	@Override
	public void updateEtat(Epreuve data) throws DALException {
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_ETAT);
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
	public List<Proposition> selectByEstBonne(int idQuestion) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void updateNote(int idEpreuve, int note, String niveau) throws DALException {
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_NOTE);
			pstmt.setInt(1, note);
			pstmt.setString(2, niveau);			
			pstmt.setInt(3, idEpreuve);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Update Note failed - " + idEpreuve, e);
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
	public Epreuve selectSectionByIdTestAndIdTheme(int idTest, int idTheme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSectionByIdTestAndIdTheme(Epreuve data, int idTest, int idTheme) throws DALException {
		// TODO Auto-generated method stub
	}

	

	
	public void updateMarque(boolean mark, int idQuestion, int idEpreuve) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<QuestionTirage> selectAllByIdEpreuve(int idEpreuve) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByIdTestAndIdTheme(int idTest, int idTheme) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
