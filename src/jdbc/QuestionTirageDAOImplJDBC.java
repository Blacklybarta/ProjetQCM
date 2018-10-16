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
import dal.DALException;
import dal.DAO;
import dal.DBConnection;

public class QuestionTirageDAOImplJDBC implements DAO<QuestionTirage> {

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<QuestionTirage> questionTirage = new ArrayList<>();
	
	private static final String SQL_INSERT = "INSERT INTO QUESTION_TIRAGE(estmarquee,numordre,idepreuve,idquestion)VALUES(0,?,?,?)";
	private static final String SQL_SELECT_BY_IDEPREUVE_NUMORDRE = "SELECT * FROM QUESTION_TIRAGE WHERE idEpreuve=? AND numordre=?";
	
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
	public void insert(QuestionTirage data) throws DALException {
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1, data.getNumOrdre());
			pstmt.setInt(2, data.getEpreuve().getIdEpreuve());
			pstmt.setInt(3, data.getQuestion().getIdQuestion());
			
			int nbRows = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Insert Question Tirage failed - " + data, e);
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
	public int insertWithIdReturn(QuestionTirage data) throws DALException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(QuestionTirage data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public QuestionTirage selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionTirage selectByIdentifiant(String identifiant, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionTirage> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionTirage> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionTirage selectByUtilisateur(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionTirage> selectAllById(int id) throws DALException {
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
	public List<Question> selectRandomQuestions(int idTheme, int nbQuestions, List<Question> questions)
			throws DALException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public QuestionTirage selectByIdEpreuve(int idEpreuve, int nbQuestion)
	{
		con = null;
		pstmt = null;
		ResultSet rs = null;
		QuestionTirage question = new QuestionTirage();
		
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_IDEPREUVE_NUMORDRE);
			pstmt.setInt(1, idEpreuve);
			pstmt.setInt(2, nbQuestion);

			rs = pstmt.executeQuery();
			if (rs.next())
			{
				question.setEpreuve(rs.getInt("idepreuve"));
				question.setEstMarquee(rs.getBoolean("estmarquee"));
				question.setNumOrdre(rs.getInt("numordre"));
				question.setQuestion(rs.getInt("idquestion"));
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
	public List<Proposition> selectByIdQuestion(int idQuestion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEtat(QuestionTirage data) throws DALException {
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

	public QuestionTirage selectSectionByIdTestAndIdTheme(int idTest, int idTheme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSectionByIdTestAndIdTheme(QuestionTirage data, int idTest, int idTheme) throws DALException {
		// TODO Auto-generated method stub		
	}

}
