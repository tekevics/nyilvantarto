package com.nyilvt.nyilvt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nyilvt.nyilvt.entity.Szemely;

@Repository
public interface SzemelyRepository extends JpaRepository<Szemely, Long>{

}
