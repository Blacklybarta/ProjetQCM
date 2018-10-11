package dal;

import java.util.List;

import bo.Epreuve;
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

	Section selectByIdTest(int idtest) throws DALException;
	
}