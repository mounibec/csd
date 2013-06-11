package com.zenika.resanet.dao;

import java.sql.Connection;

import com.zenika.resanet.model.Operator;

public interface OperatorDao {

	Operator findByLogin(Connection cx, String login);

	void save(Connection cx, Operator operator);

}
