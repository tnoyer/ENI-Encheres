package fr.eni.javaee.eniencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.ArticleVendu;
import fr.eni.javaee.eniencheres.bo.Categorie;
import fr.eni.javaee.eniencheres.bo.Utilisateur;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	private static final String INSERT_ARTICLE = ""
			+ "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) "
			+ "VALUES(?,?,?,?,?,?,?,?)";
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
			if(ventesNonDebutees != null) {
				where.append(" AND date_fin_encheres >= GETDATE() AND av.no_utilisateur="+idUser);
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

}
