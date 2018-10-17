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
import bo.Theme;
import dal.DALException;
import dal.DAO;
import dal.DBConnection;
import javafx.scene.image.Image;

public class QuestionDAOImplJDBC implements DAO<Question>{

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Question> listeQuestions = new ArrayList<>();
	
	private static final String SQL_INSERT = "INSERT INTO QUESTION(enonce,points,idtheme)VALUES(?,?,?)";
	private static final String SQL_SELECTALL = "SELECT * FROM QUESTION ";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM QUESTION WHERE idquestion=?";
	private static final String SQL_SELECT_BY_THEME_RANDOM = "SELECT TOP # * FROM QUESTION WHERE idTheme=? ORDER BY NEWID()";
	private static final String SQL_UPDATE = "UPDATE QUESTION SET enonce=?,points=?,idtheme=? WHERE idquestion=?";

	
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
	public void insert(Question data) throws DALException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int insertWithIdReturn(Question data) throws DALException {
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, data.getEnonce());
			//pstmt.setBytes(2, data.getImage());
			pstmt.setInt(2, data.getPoints());
			pstmt.setInt(3, data.getTheme().getIdTheme());
			
			

			int nbRows = pstmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					data.setIdQuestion(rs.getInt(1));
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
		return data.getIdQuestion();
	}

	@Override
	public void update(Question data) throws DALException {
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE);
			pstmt.setString(1,  data.getEnonce());
			pstmt.setInt(2, data.getPoints());
			pstmt.setInt(3, data.getTheme().getIdTheme());
			pstmt.setInt(4, data.getIdQuestion());
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
	public Question selectById(int id) throws DALException {
		con = null;
		pstmt = null;
		ResultSet rs = null;
		Question question = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				question = new Question();
				question.setIdQuestion(rs.getInt("idquestion"));
				question.setEnonce(rs.getString("enonce"));
				question.setPoints(rs.getInt("points"));
				Theme theme = new Theme();
				theme.setIdTheme(rs.getInt("idTheme"));
				question.setTheme(theme);
				question.setListeProposition();
				
				listeQuestions.add(question);
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
		return question;
	}

	@Override
	public Question selectByIdentifiant(String identifiant, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> selectAll() throws DALException {
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_SELECTALL);

			Question question = null;
			listeQuestions.clear();
			while (rs.next()) {
				question = new Question();
				question.setIdQuestion(rs.getInt("idquestion"));
				question.setEnonce(rs.getString("enonce"));
				question.setPoints(rs.getInt("points"));
				Theme theme = new Theme();
				theme.setIdTheme(rs.getInt("idTheme"));
				question.setTheme(theme);
				listeQuestions.add(question);
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
		return listeQuestions;
	}

	@Override
	public List<Question> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question selectByUtilisateur(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> selectAllById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Epreuve> selectCandidatEpreuve(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Section> selectByIdTest(int idtest) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> selectRandomQuestions(int idTheme, int nbQuestions, List<Question> questions) throws DALException {
		con = null;
		pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String prdStatmnt = SQL_SELECT_BY_THEME_RANDOM.replaceAll("#", String.valueOf(nbQuestions));
			System.out.println(prdStatmnt);
			pstmt = con.prepareStatement(prdStatmnt);
			pstmt.setInt(1, idTheme);
			
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Question question = new Question();
				
				question.setEnonce(rs.getString("enonce"));
				question.setIdQuestion(rs.getInt("idquestion"));
				question.setPoints(rs.getInt("points"));
				question.setTheme(rs.getInt("idTheme"));
				
				questions.add(question);
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
		return questions;
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
	public void updateEtat(Question data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Proposition> selectByEstBonne(int idQuestion) {
		// TODO Auto-generated method stub
		return null;
	}

	public Question selectSectionByIdTestAndIdTheme(int idTest, int idTheme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSectionByIdTestAndIdTheme(Question data, int idTest, int idTheme) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNote(int idEpreuve, int note, String niveau) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMarque(boolean mark, int idQuestion, int idEpreuve) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<QuestionTirage> selectAllByIdEpreuve(int idEpreuve) {
		// TODO Auto-generated method stub
		return null;
	}

}
