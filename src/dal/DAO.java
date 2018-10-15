package dal;

import java.util.List;

import bo.Epreuve;
import bo.Proposition;
import bo.Question;
import bo.QuestionTirage;
import bo.Section;

public interface DAO<T> {

	public void insert(T data) throws DALException ;
	
	public int insertWithIdReturn(T data) throws DALException ;

	public void update(T data) throws DALException ;

	public void delete(int id) throws DALException ;

	public T selectById(int id) throws DALException ;
	
	public T selectByIdentifiant(String identifiant, String mdp) throws DALException;

	public List<T> selectAll() throws DALException ;
	
	public List<T> selectByKeyWord(String recherche) throws DALException;
	
	public T selectByUtilisateur(int id) throws DALException;
	
	public List<T> selectAllById(int id) throws DALException;
	
	public List<Epreuve> selectCandidatEpreuve(int id) throws DALException;

	public List<Section> selectByIdTest(int idtest) throws DALException;

	public List<Question> selectRandomQuestions(int idTheme, int nbQuestions, List<Question> questions) throws DALException;
	
	public QuestionTirage selectByIdEpreuve(int idEpreuve, int nbQuestion);

	public List<Proposition> selectByIdQuestion(int idQuestion);

	public List<Proposition> selectByEstBonne(int idQuestion);
}