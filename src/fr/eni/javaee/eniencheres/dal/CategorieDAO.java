package fr.eni.javaee.eniencheres.dal;

import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Categorie;

public interface CategorieDAO {
	public List<Categorie> selectAll() throws BusinessException;
	public Categorie selectById(String nomCat) throws BusinessException;
}
