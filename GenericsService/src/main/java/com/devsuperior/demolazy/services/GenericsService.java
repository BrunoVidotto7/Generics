package com.devsuperior.demolazy.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.demolazy.util.Convertible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface GenericsService<T extends Convertible<DTO>, DTO, ID> {

	JpaRepository<T,ID> getRepository();
	
	default DTO findById(ID id) {
		Optional<T> result = getRepository().findById(id);
		return result.get().convert();
	}

	default List<DTO> findAll() {
		List<T> result = getRepository().findAll();
		return result.stream().map(x -> x.convert()).collect(Collectors.toList());
	}
}
