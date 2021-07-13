package com.cts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.bean.ExchangeValue;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeValue,Long> {
	ExchangeValue findByFromAndTo(String from, String to);
}
