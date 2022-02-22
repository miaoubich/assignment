package com.esystems.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esystems.assignment.entity.MetarData;

@Repository
public interface MetarDataRepository extends JpaRepository<MetarData, Integer> {

	MetarData findByIcaoCode(String icaoCode);
}
