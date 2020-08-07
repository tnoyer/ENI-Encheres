package fr.eni.javaee.eniencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.ArticleVendu;
import fr.eni.javaee.eniencheres.bo.Categorie;
import fr.eni.javaee.eniencheres.bo.Enchere;
import fr.eni.javaee.eniencheres.bo.Utilisateur;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	private static final String INSERT_ARTICLE = ""
			+ "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) "
			+ "VALUES(?,?,?,?,?,?,?,?)";
	private static final String INSERT_ENCHERE = ""
			+ "INSERT INTO ENCHERES(no_utilisateur, no_article, date_enchere, montant_enchere) "
			+ "VALUES(?,?,GETDATE(),?)";
	private static final String SELECT_EN_COURS = ""
			+ "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, av.no_utilisateur, pseudo, rue, code_postal, ville, c.no_categorie, libelle "
			+ "FROM ARTICLES_VENDUS av "
			+ "INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON av.no_categorie = c.no_categorie "
			+ "WHERE date_fin_encheres >= GETDATE() AND date_debut_encheres <= GETDATE()";
	private static final String SELECT_ALL = ""
			+ "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, av.no_utilisateur, pseudo, rue, code_postal, ville, c.no_categorie, libelle "
			+ "FROM ARTICLES_VENDUS av "
			+ "INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON av.no_categorie = c.no_categorie";
	private static final String SELECT_BY_ID= ""
			+ "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, av.no_utilisateur, pseudo, rue, code_postal, ville, c.no_categorie, libelle "
			+ "FROM ARTICLES_VENDUS av "
			+ "INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON av.no_categorie = c.no_categorie "
			+ "WHERE no_article=?";
	private static final String UPDATE_ARTICLE = ""
			+ "UPDATE ARTICLES_VENDUS "
			+ "SET prix_vente=? "
			+ "WHERE no_article=?";
	private static final String SELECT_ENCHERE = ""
			+ "SELECT no_utilisateur, no_article, date_enchere, montant_enchere "
			+ "FROM ENCHERES "
			+ "WHERE no_article=?";
	private static final String SELECT_DERNIERE_ENCHERE = ""
			+ "SELECT TOP 1 no_utilisateur, no_article, date_enchere, montant_enchere "
			+ "FROM ENCHERES "
			+ "WHERE no_article=? "
			+ "ORDER BY date_enchere DESC ";
	private static final String UPDATE_ENCHERE= ""
			+ "UPDATE ENCHERES SET no_utilisateur=?, date_enchere=GETDATE(), montant_enchere=? WHERE no_article=?";
	private static final String MAX_ENCHERE = ""
			+ "SELECT COUNT(*) "
			+ "FROM ENCHERES "
			+ "WHERE no_utilisateur=? AND no_article=?";
	private static final String SELECT_DERNIERE_ENCHERE_UTILISATEUR_CONNECTE = ""
			+ "SELECT no_utilisateur, no_article, date_enchere, montant_enchere "
			+ "FROM ENCHERES "
			+ "WHERE no_utilisateur=? AND no_article=?";
	
	@Override
	public void insertArticle(ArticleVendu art) throws BusinessException {
		if (art == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE,
						PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, art.getNom());
				pstmt.setString(2, art.getDescription());
				pstmt.setDate(3, java.sql.Date.valueOf(art.getDateDebut()));
				pstmt.setDate(4, java.sql.Date.valueOf(art.getDateFin()));
				pstmt.setInt(5, art.getPrixInitial());
				pstmt.setInt(6, art.getPrixInitial());
				pstmt.setInt(7, art.getUtilisateur().getId());
				pstmt.setInt(8, art.getCategorie().getId());
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					art.setId(rs.getInt(1));
				}
				rs.close();
				pstmt.close();
				cnx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}

	@Override
	public List<ArticleVendu> selectAll() throws BusinessException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			
			ArticleVendu unArticle;
			Utilisateur unUtilisateur;
			Categorie uneCategorie;
			
			while(rs.next())
			{
				unArticle = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"), 
						rs.getString("description"), 
						rs.getDate("date_debut_encheres").toLocalDate(), 
						rs.getDate("date_fin_encheres").toLocalDate(), 
						rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"));
				
				unUtilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"));

				uneCategorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				unArticle.setUtilisateur(unUtilisateur);
				unArticle.setCategorie(uneCategorie);

				listeArticles.add(unArticle);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
			throw businessException;
		}
		return listeArticles;
	}

	@Override
	public List<ArticleVendu> selectByNomAndCategorie(String nom, int id) throws BusinessException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			StringBuilder where = new StringBuilder();
			where.append(" WHERE 1=1 ");
			if(nom != "") {
				where.append("AND nom_article = ? ");
			} 
			if(id != 0) {
				where.append("AND c.no_categorie = ? ");
			}
			System.out.println(where);
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL+where);
			if(nom != "" && id != 0) {
				pstmt.setString(1, nom);
				pstmt.setInt(2, id);
			} else if(nom != "" && id == 0) {
				pstmt.setString(1, nom);
			} else if(nom == "" && id != 0) {
				pstmt.setInt(1, id);
			}
			ResultSet rs = pstmt.executeQuery();
			
			ArticleVendu unArticle;
			Utilisateur unUtilisateur;
			Categorie uneCategorie;
			
			while(rs.next())
			{
				unArticle = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"), 
						rs.getString("description"), 
						rs.getDate("date_debut_encheres").toLocalDate(), 
						rs.getDate("date_fin_encheres").toLocalDate(), 
						rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"));
				
				unUtilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"));

				uneCategorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				unArticle.setUtilisateur(unUtilisateur);
				unArticle.setCategorie(uneCategorie);

				listeArticles.add(unArticle);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
			throw businessException;
		}
		return listeArticles;
	}

	@Override
	public List<ArticleVendu> selectListeAchat(String nom, int idCat, String encheresOuvertes, String encheresEnCours,
			String encheresRemportees, int idUser) throws BusinessException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			StringBuilder where = new StringBuilder();
			where.append(" WHERE 1=1");
			if(nom != "") {
				where.append(" AND nom_article = ?");
			} 
			if(idCat != 0) {
				where.append(" AND c.no_categorie = ?");
			}
			if(encheresOuvertes != null) {
				where.append(" AND date_fin_encheres >= GETDATE() AND date_debut_encheres <= GETDATE()");
			}
			if(encheresEnCours != null) {
				where.append(" AND date_fin_encheres >= GETDATE() AND date_debut_encheres <= GETDATE() AND u.no_utilisateur = "+idUser);
			}
			if(encheresRemportees != null) {
				where.append(" AND date_fin_encheres <= GETDATE() AND u.no_utilisateur = "+idUser);
			}
			System.out.println(where);
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL+where);
			if(nom != "" && idCat != 0) {
				pstmt.setString(1, nom);
				pstmt.setInt(2, idCat);
			} else if(nom != "" && idCat == 0) {
				pstmt.setString(1, nom);
			} else if(nom == "" && idCat != 0) {
				pstmt.setInt(1, idCat);
			}
			ResultSet rs = pstmt.executeQuery();
			
			ArticleVendu unArticle;
			Utilisateur unUtilisateur;
			Categorie uneCategorie;
			
			while(rs.next())
			{
				unArticle = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"), 
						rs.getString("description"), 
						rs.getDate("date_debut_encheres").toLocalDate(), 
						rs.getDate("date_fin_encheres").toLocalDate(), 
						rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"));
				
				unUtilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"));

				uneCategorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				unArticle.setUtilisateur(unUtilisateur);
				unArticle.setCategorie(uneCategorie);

				listeArticles.add(unArticle);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
			throw businessException;
		}
		return listeArticles;
	}

	@Override
	public List<ArticleVendu> selectListeVente(String nom, int idCat, String ventesEnCours, String ventesNonDebutees,
			String ventesTerminees, int idUser) throws BusinessException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			StringBuilder where = new StringBuilder();
			where.append(" WHERE 1=1");
			if(nom != "") {
				where.append(" AND nom_article = ?");
			} 
			if(idCat != 0) {
				where.append(" AND c.no_categorie = ?");
			}
			if(ventesEnCours != null) {
				where.append(" AND date_fin_encheres >= GETDATE() AND date_debut_encheres <= GETDATE() AND av.no_utilisateur="+idUser);
			}
			if(ventesNonDebutees != null) {
				where.append(" AND date_debut_encheres >= GETDATE() AND av.no_utilisateur="+idUser);
			}
			if(ventesTerminees != null) {
				where.append(" AND date_fin_encheres <= GETDATE() AND av.no_utilisateur="+idUser);
			}
			System.out.println(where);
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL+where);
			if(nom != "" && idCat != 0) {
				pstmt.setString(1, nom);
				pstmt.setInt(2, idCat);
			} else if(nom != "" && idCat == 0) {
				pstmt.setString(1, nom);
			} else if(nom == "" && idCat != 0) {
				pstmt.setInt(1, idCat);
			}
			ResultSet rs = pstmt.executeQuery();
			
			ArticleVendu unArticle;
			Utilisateur unUtilisateur;
			Categorie uneCategorie;
			
			while(rs.next())
			{
				unArticle = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"), 
						rs.getString("description"), 
						rs.getDate("date_debut_encheres").toLocalDate(), 
						rs.getDate("date_fin_encheres").toLocalDate(), 
						rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"));
				
				unUtilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"));

				uneCategorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				unArticle.setUtilisateur(unUtilisateur);
				unArticle.setCategorie(uneCategorie);

				listeArticles.add(unArticle);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
			throw businessException;
		}
		return listeArticles;
	}

	@Override
	public ArticleVendu selectById(int id) throws BusinessException {
		ArticleVendu unArticle;
		Utilisateur unUtilisateur;
		Categorie uneCategorie;
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				unArticle = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"), 
						rs.getString("description"), 
						rs.getDate("date_debut_encheres").toLocalDate(), 
						rs.getDate("date_fin_encheres").toLocalDate(), 
						rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"));
				
				unUtilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"));

				uneCategorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				unArticle.setUtilisateur(unUtilisateur);
				unArticle.setCategorie(uneCategorie);
			}else {
				throw new Exception("KO");
			}
		} catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
			throw businessException;
		}
		return unArticle;
	}

	@Override
	public void insertEnchere(int no_utilisateur, int no_article, int montant_enchere) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE);
				pstmt.setInt(1, no_utilisateur);
				pstmt.setInt(2, no_article);
				pstmt.setInt(3, montant_enchere);
				pstmt.executeUpdate();
				pstmt.close();
				pstmt = cnx.prepareStatement(UPDATE_ARTICLE);
				pstmt.setInt(1, montant_enchere);
				pstmt.setInt(2, no_article);
				pstmt.executeUpdate();
				pstmt.close();
				cnx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}

	@Override
	public Enchere selectEnchere(int idArt) throws BusinessException {
		Enchere enchere;
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERE);
			pstmt.setInt(1, idArt);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				enchere = new Enchere(
						rs.getDate("date_enchere").toLocalDate(), 
						rs.getInt("montant_enchere"), 
						rs.getInt("no_utilisateur"),
						rs.getInt("no_article"));
			}else {
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ENCHERE_ECHEC);
			throw businessException;
		}
		return enchere;
	}

	@Override
	public Enchere selectDerniereEnchere(int idArt) throws BusinessException {
		Enchere enchere;
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_DERNIERE_ENCHERE);
			pstmt.setInt(1, idArt);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				enchere = new Enchere(
						rs.getDate("date_enchere").toLocalDate(), 
						rs.getInt("montant_enchere"), 
						rs.getInt("no_utilisateur"),
						rs.getInt("no_article"));
			}else {
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ENCHERE_ECHEC);
			throw businessException;
		}
		return enchere;
	}

	@Override
	public void modifyEnchere(int no_utilisateur, int no_article, int montant_enchere) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ENCHERE);
			pstmt.setInt(1, no_utilisateur);
			pstmt.setInt(2, montant_enchere);
			pstmt.setInt(3, no_article);
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = cnx.prepareStatement(UPDATE_ARTICLE);
			pstmt.setInt(1, montant_enchere);
			pstmt.setInt(2, no_article);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_ENCHERE_ERR);
			throw businessException;
		}
		
	}

	@Override
	public int maxEnchere(int no_utilisateur, int no_article) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(MAX_ENCHERE);
			pstmt.setInt(1, no_utilisateur);
			pstmt.setInt(2, no_article);
		} catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ENCHERE_ECHEC);
			throw businessException;
		}
		return -1;
	}

	@Override
	public Enchere selectDerniereEnchereUtilisateurConnecte(int idArt, int idUser) throws BusinessException {
		Enchere enchere;
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_DERNIERE_ENCHERE_UTILISATEUR_CONNECTE);
			pstmt.setInt(1, idUser);
			pstmt.setInt(2, idArt);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				enchere = new Enchere(
						rs.getDate("date_enchere").toLocalDate(), 
						rs.getInt("montant_enchere"), 
						rs.getInt("no_utilisateur"),
						rs.getInt("no_article"));
			}else {
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ENCHERE_ECHEC);
			throw businessException;
		}
		return enchere;
	}

	@Override
	public List<ArticleVendu> selectEnCours() throws BusinessException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_EN_COURS);
			ResultSet rs = pstmt.executeQuery();
			
			ArticleVendu unArticle;
			Utilisateur unUtilisateur;
			Categorie uneCategorie;
			
			while(rs.next())
			{
				unArticle = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"), 
						rs.getString("description"), 
						rs.getDate("date_debut_encheres").toLocalDate(), 
						rs.getDate("date_fin_encheres").toLocalDate(), 
						rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"));
				
				unUtilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"));

				uneCategorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				unArticle.setUtilisateur(unUtilisateur);
				unArticle.setCategorie(uneCategorie);

				listeArticles.add(unArticle);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
			throw businessException;
		}
		return listeArticles;
	}

}
