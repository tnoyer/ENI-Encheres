package fr.eni.javaee.eniencheres.dal;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Retrait;

public interface RetraitDAO {
	public void insertRetrait(Retrait retrait) throws BusinessException;
}
