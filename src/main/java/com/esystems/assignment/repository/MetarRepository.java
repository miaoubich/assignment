package com.esystems.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esystems.assignment.entity.Metar;

@Repository
public interface MetarRepository extends JpaRepository<Metar, Integer> {
}
