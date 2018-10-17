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
import bo.Theme;
import bo.Utilisateur;
import dal.DALException;
import dal.DAO;
import dal.DBConnection;

public class SectionDAOImplJDBC implements DAO<Section> {

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Section> listeSections = new ArrayList<>();

	private static final String SQL_SELECT_BY_ID_TEST = "SELECT * FROM SECTION_TEST WHERE idtest=?";
	private static final String SQL_INSERT = "INSERT INTO SECTION_TEST(nbquestionsatirer,idtest,idtheme)VALUES(?,?,?)";
	private static final String SQL_SELECTALL = "SELECT * FROM SECTION_TEST;";
	private static final String SQL_SELECT_BY_ID_TEST_ID_THEME = "SELECT * FROM SECTION_TEST WHERE idtest=? AND idtheme=?";
	private static final String SQL_UPDATE_BY = "UPDATE SECTION_TEST SET nbquestionsatirer=?,idtest=?,idtheme=? WHERE idtest=? AND idtheme=?";
	
	
	public void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con = null;
		}
	}

	@Override
	public void insert(Section data) throws DALException {
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, data.getNbQuestionATirer());
			pstmt.setInt(2, data.getTest().getIdTest());
			pstmt.setInt(3, data.getTheme().getIdTheme());

			pstmt.executeUpdate();
			// int nbRows = pstmt.executeUpdate();
			// if (nbRows == 1) {
			// ResultSet rs = pstmt.getGeneratedKeys();
			// if (rs.next()) {
			// data.setIdTest(rs.getInt(1));
			// }
			// }
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
	public int insertWithIdReturn(Section data) throws DALException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Section data) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public Section selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Section selectByIdentifiant(String identifiant, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Section> selectAll() throws DALException {
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_SELECTALL);

			Section section = null;
			listeSections.clear();
			while (rs.next()) {
				section = new Section();
				section.setNbQuestionATirer(rs.getInt("nbquestionsatirer"));
				section.setTest(rs.getInt("idtest"));
				section.setTheme(rs.getInt("idtheme"));

				listeSections.add(section);
			}
		} catch (SQLException e) {
			throw new DALException("selectAll failed - ", e);
		} finally {
			try {

				if (stmt != null) {
					stmt.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		return listeSections;
	}

	@Override
	public List<Section> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Section selectByUtilisateur(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Section> selectAllById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Epreuve> selectCandidatEpreuve(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Section> selectByIdTest(int idtest) throws DALException
	{
		con = null;
		pstmt = null;
		ResultSet rs = null;
		List<Section> sections = new ArrayList();

		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_ID_TEST);
			pstmt.setInt(1, idtest);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Section section = new Section();

				section.setNbQuestionATirer(rs.getInt("nbquestionsatirer"));
				section.setTest(rs.getInt("idtest"));
				section.setTheme(rs.getInt("idtheme"));

				sections.add(section);
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
		return sections;
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
	public void updateEtat(Section data) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Proposition> selectByEstBonne(int idQuestion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateNote(int idEpreuve, int note, String niveau) throws DALException {
		// TODO Auto-generated method stub
		
	}
		
	public Section selectSectionByIdTestAndIdTheme(int idTest, int idTheme) {
		con = null;
		pstmt = null;
		ResultSet rs = null;
		Section section = null;		
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_ID_TEST_ID_THEME);
			pstmt.setInt(1, idTest);
			pstmt.setInt(2, idTheme);

			rs = pstmt.executeQuery();
			while (rs.next())
			{
				section = new Section();
				section.setNbQuestionATirer(rs.getInt("nbquestionsatirer"));
				System.out.println(rs.getInt("nbquestionsatirer"));
				section.setTest(rs.getInt("idtest"));
				section.setTheme(rs.getInt("idtheme"));			
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
		return section;
	}

	@Override
	public void updateSectionByIdTestAndIdTheme(Section data, int idTest, int idTheme) throws DALException {
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_BY);
			pstmt.setInt(1, data.getNbQuestionATirer());
			pstmt.setInt(2, data.getTest().getIdTest());
			pstmt.setInt(3, data.getTheme().getIdTheme());
			pstmt.setInt(4,	idTest);
			pstmt.setInt(5, idTheme);
			
			pstmt.executeUpdate();

		} catch (SQLException | DALException e) {
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
}
