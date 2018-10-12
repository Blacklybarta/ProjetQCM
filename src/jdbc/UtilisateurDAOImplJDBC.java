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
import bo.Utilisateur;
import dal.DALException;
import dal.DAO;
import dal.DBConnection;

public class UtilisateurDAOImplJDBC implements DAO<Utilisateur> {

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Utilisateur> listUtilisateurs = new ArrayList<>();

	private static final String SQL_SELECT_BY_IDENTIFIANT = "SELECT * FROM UTILISATEUR WHERE email=? and password=?";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM UTILISATEUR WHERE idUtilisateur=?";
	private static final String SQL_INSERT = "INSERT INTO UTILISATEUR(nom,prenom,email,password,isCandidat,isCollaborateur,codeProfil,codepromo) VALUES(?,?,?,?,?,?,?,?)";
	private static final String SQL_SELECTALL = "SELECT * FROM UTILISATEUR ORDER BY nom ASC;";
	private static final String SQL_DELETE = "UPDATE UTILISATEUR SET administrateur=?,conducteur=? WHERE idUtilisateur=?";
	private static final String SQL_UPDATE = "UPDATE UTILISATEUR SET identifiant=?,mdp=?,nom=?,prenom=?,administrateur=?,conducteur=? WHERE idUtilisateur=?";

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
	public void insert(Utilisateur data) throws DALException {
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);	
			pstmt.setString(1, data.getNom());
			pstmt.setString(2, data.getPrenom());
			pstmt.setString(3, data.getEmail());
			pstmt.setString(4, data.getPassword());
			pstmt.setBoolean(5, data.isCandidat());
			pstmt.setBoolean(6, data.isCollaborateur());
			pstmt.setInt(7, data.getCodeProfil());
			pstmt.setInt(8, data.getCodePromo());

			int nbRows = pstmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					data.setIdUtilistaeur(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new DALException("Insert utilisateur failed - " + data, e);
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
	public void update(Utilisateur data) throws DALException {
		// TODO Auto-generated method stub
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
//			pstmt = con.prepareStatement(SQL_UPDATE);
//			pstmt.setString(1, data.getIdentifiant());
//			pstmt.setString(2, data.getMdp());
//			pstmt.setString(3, data.getNom());
//			pstmt.setString(4, data.getPrenom());
//			pstmt.setBoolean(5, data.isAdministrateur());
//			pstmt.setBoolean(6, data.isConducteur());
//			pstmt.setInt(7, data.getId());

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

	/**
	 * On ne supprime pas un utilisateur, on lui retire son droit
	 * d'administrateur et son droit de conducteur
	 */
	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_DELETE);

			pstmt.setBoolean(1, false);
			pstmt.setBoolean(2, false);
			pstmt.setInt(3, id);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("delete utilisateur failed - " + e);
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
	public Utilisateur selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		con = null;
		pstmt = null;
		ResultSet rs = null;
		Utilisateur utilisateur = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setIdUtilistaeur(rs.getInt("idutilisateur"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setPassword(rs.getString("password"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setCollaborateur(rs.getBoolean("isCollaborateur"));
				utilisateur.setCandidat(rs.getBoolean("isCandidat"));
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
		return utilisateur;
	}

	@Override
	public Utilisateur selectByIdentifiant(String email, String password) throws DALException {
		// TODO Auto-generated method stub
		con = null;
		pstmt = null;
		ResultSet rs = null;
		Utilisateur utilisateur = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_IDENTIFIANT);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setIdUtilistaeur(rs.getInt("idutilisateur"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setPassword(rs.getString("password"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setCollaborateur(rs.getBoolean("isCollaborateur"));
				utilisateur.setCandidat(rs.getBoolean("isCandidat"));

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
		return utilisateur;
	}

	@Override
	public List<Utilisateur> selectAll() throws DALException {
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_SELECTALL);

			Utilisateur utilisateur = null;
			listUtilisateurs.clear();
			while (rs.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setIdUtilistaeur(rs.getInt("idutilisateur"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setPassword(rs.getString("password"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setCollaborateur(rs.getBoolean("isCollaborateur"));
				utilisateur.setCandidat(rs.getBoolean("isCandidat"));				
				
				listUtilisateurs.add(utilisateur);
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
		return listUtilisateurs;
	}

	@Override
	public List<Utilisateur> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur selectByUtilisateur(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> selectAllById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Epreuve> selectCandidatEpreuve(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertWithIdReturn(Utilisateur data) throws DALException {
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
