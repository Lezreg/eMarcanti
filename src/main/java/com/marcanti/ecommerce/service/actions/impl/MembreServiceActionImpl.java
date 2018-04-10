package com.marcanti.ecommerce.service.actions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.dao.FilleulDAO;
import com.marcanti.ecommerce.dao.MembreDAO;
import com.marcanti.ecommerce.model.Filleul;
import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.Profil;
import com.marcanti.ecommerce.service.actions.MembreServiceAction;
import com.marcanti.ecommerce.view.bean.ReferentielBean;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

@Service("membreService")
@Transactional
public class MembreServiceActionImpl implements MembreServiceAction {

	@Autowired
	private MembreDAO membreDAO;

	@Autowired
	private FilleulDAO filleulDAO;

	public MembreDAO getMembreDAO() {
		return membreDAO;
	}

	public void setMembreDAO(MembreDAO membreDAO) {
		this.membreDAO = membreDAO;
	}

	@Override
	public Membre getMembre(Membre idMembre) {
		return membreDAO.getMembre(idMembre);
	}

	@Override
	public void updateFilleulMembre(Membre filleul) {
		membreDAO.updateFilleulMembre(filleul);

	}

	@Override
	public void insertFilleulMembre(Membre filleul, UserSessionBean parrain) {
		membreDAO.insertFilleulMembre(filleul, parrain);

	}

	@Override
	public List<Membre> getMembreList() {
		Membre membre = null;
		List<Membre> membres = membreDAO.findAll();
		for (int i = 0; i < membres.size(); i++) {
			membre = membres.get(i);
			if (membre.getIdProfil().getIdProfil() == ReferentielBean.PROFIL_FILLEUL) {
				Filleul filleul = filleulDAO.getFilleul(membre.getIdMembre());
				if (filleul != null) {
					membre.setNomParrain(filleul.getParrainNom());
				}
			}
		}
		return membres;
	}

	@Override
	public List<Membre> getMembreByOrgaList(Organisation idOrga) {
		Membre membre = null;
		List<Membre> membres = membreDAO.getMembreByOrgaList(idOrga);
		for (int i = 0; i < membres.size(); i++) {
			membre = membres.get(i);
			if (membre.getIdProfil().getIdProfil() == ReferentielBean.PROFIL_FILLEUL) {
				Filleul filleul = filleulDAO.getFilleul(membre.getIdMembre());
				if (filleul != null) {
					membre.setNomParrain(filleul.getParrainNom());
				}
			}
		}
		return membres;
	}

	@Override
	public void updateMembre(Membre membre) {
		membreDAO.updateMembre(membre);
	}

	@Override
	public void insertMembre(Membre membre) {
		membreDAO.insertMembre(membre);
	}

	@Override
	public List<Membre> getParrainByOrgaList(Organisation idOrga, List<Profil> idProfilList) {
		return membreDAO.getParrainByOrgaList(idOrga, idProfilList);
	}

	@Override
	public void insertMembreFilleul(Membre filleul, Membre membreParrain) {
		membreDAO.insertMembreFilleul(filleul, membreParrain);

	}

	@Override
	public void updateMembreFilleul(Membre filleul, Membre membreParrain) {
		membreDAO.updateMembreFilleul(filleul, membreParrain);
	}

}
