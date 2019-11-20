package org.springframework.samples.petclinic.hospedagem;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;


public interface HospedagemRepository extends Repository<Hospedagem, Integer>{
	void save(Hospedagem hosp) throws DataAccessException;

    List<Hospedagem> findByPetId(Integer petId);
}
