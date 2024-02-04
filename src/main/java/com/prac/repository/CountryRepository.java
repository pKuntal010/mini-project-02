package com.prac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prac.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{

}
