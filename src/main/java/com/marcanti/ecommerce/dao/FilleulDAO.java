package com.marcanti.ecommerce.dao;

import java.util.List;

import com.marcanti.ecommerce.model.Filleul;
import com.marcanti.ecommerce.model.Membre;

public interface FilleulDAO extends GenericDAO<Filleul> {

	public List<Membre> getFilleulsList(Membre parrain);

	public Filleul getFilleul(Long idFilleul);

	public List<Long> getFilleulsIdList(Long parrainId);

}
