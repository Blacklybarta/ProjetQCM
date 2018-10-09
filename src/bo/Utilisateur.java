package bo;

public class Utilisateur {

	private int idUtilistaeur;
	private String nom;
	private String prenom;
	private String email;
	private String password;
	private int codeProfil;
	private boolean isCollaborateur;
	private boolean isCandidat;
	
	public Utilisateur() {
		super();
	}
	
	public int getIdUtilistaeur() {
		return idUtilistaeur;
	}
	
	public void setIdUtilistaeur(int idUtilistaeur) {
		this.idUtilistaeur = idUtilistaeur;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCodeProfil() {
		return codeProfil;
	}
	public void setCodeProfil(int codeProfil) {
		this.codeProfil = codeProfil;
	}
	public boolean isCollaborateur() {
		return isCollaborateur;
	}
	public void setCollaborateur(boolean isCollaborateur) {
		this.isCollaborateur = isCollaborateur;
	}
	public boolean isCandidat() {
		return isCandidat;
	}
	public void setCandidat(boolean isCandidat) {
		this.isCandidat = isCandidat;
	}
	@Override
	public String toString() {
		return "Utilisateur [nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
	}
	
	
	
}
