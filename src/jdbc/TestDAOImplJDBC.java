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


public class TestDAOImplJDBC implements DAO<Test> {

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Test> listeTests = new ArrayList<>();

	private static final String SQL_INSERT = "INSERT INTO TEST(libelle,description,duree,seuil_haut,seuil_bas)VALUES(?,?,?,?,?)";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM TEST WHERE idtest=?";
	private static final String SQL_SELECTALL = "SELECT * FROM TEST";
	private static final String SQL_UPDATE = "UPDATE TEST SET libelle=?,description=?,duree=?,seuil_haut=?,seuil_bas=? WHERE idtest=?";

	
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
	public void insert(Test data) throws DALException {
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, data.getLibelle());
			pstmt.setString(2, data.getDescription());
			pstmt.setInt(3, data.getDuree());
			pstmt.setInt(4, data.getSeuilHaut());
			pstmt.setInt(5, data.getSeuilBas());

			int nbRows = pstmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					data.setIdTest(rs.getInt(1));
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
	public void update(Test data) throws DALException {
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE);
			pstmt.setString(1,data.getLibelle());
			pstmt.setString(2, data.getDescription());
			pstmt.setInt(3, data.getDuree());
			pstmt.setInt(4, data.getSeuilHaut());
			pstmt.setInt(5, data.getSeuilBas());
			pstmt.setInt(6, data.getIdTest());		
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
		// TODO Auto-generated method stub

	}

	@Override
	public Test selectById(int id) throws DALException {
		con = null;
		pstmt = null;
		ResultSet rs = null;
		Test test = null;

		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				test = new Test();
				test.setIdTest(rs.getInt("idtest"));
				test.setDescription(rs.getString("description"));
				test.setDuree(rs.getInt("duree"));
				test.setLibelle(rs.getString("libelle"));
				test.setSeuilBas(rs.getInt("seuil_bas"));
				test.setSeuilHaut(rs.getInt("seuil_haut"));
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
		return test;
	}

	@Override
	public Test selectByIdentifiant(String identifiant, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Test> selectAll() throws DALException {
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_SELECTALL);

			Test test = null;
			listeTests.clear();
			while (rs.next()) {
				test = new Test();
				test.setIdTest(rs.getInt("idtest"));
				test.setLibelle(rs.getString("libelle"));
				test.setDescription(rs.getString("description"));
				test.setDuree(rs.getInt("duree"));
				test.setSeuilHaut(rs.getInt("seuil_haut"));
				test.setSeuilBas(rs.getInt("seuil_bas"));
				listeTests.add(test);
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
		return listeTests;
	}

	@Override
	public List<Test> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Test selectByUtilisateur(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Test> selectAllById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Epreuve> selectCandidatEpreuve(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertWithIdReturn(Test data) throws DALException {
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
	public void updateEtat(Test data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Proposition> selectByEstBonne(int idQuestion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateNote(int idEpreuve, int note) throws DALException {
		
	}

	public Test selectSectionByIdTestAndIdTheme(int idTest, int idTheme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSectionByIdTestAndIdTheme(Test data, int idTest, int idTheme) throws DALException {
		// TODO Auto-generated method stub
		
	}
	
}