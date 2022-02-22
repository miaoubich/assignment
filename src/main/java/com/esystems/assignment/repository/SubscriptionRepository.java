package com.esystems.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.esystems.assignment.entity.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

	Subscription findByIcaoCode(String icaoCod);
	
	@Query("FROM Subscription WHERE active = ?1")
	List<Subscription> findByActive(String active);
}
