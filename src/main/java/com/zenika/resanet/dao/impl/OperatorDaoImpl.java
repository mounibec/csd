package com.zenika.resanet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zenika.resanet.dao.DaoException;
import com.zenika.resanet.dao.OperatorDao;
import com.zenika.resanet.model.Operator;

public class OperatorDaoImpl implements OperatorDao {

	@Override
	public Operator findByLogin(Connection cx, String login) {
		try {
			PreparedStatement ps = cx
					.prepareStatement("SELECT * FROM OPERATORS WHERE login = ?");
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			Operator result = null;
			if (rs.next()) {
				result = makeObject(rs);
			}
			ps.close();
			return result;

		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void save(Connection cx, Operator operator) {
		try {
			StringBuffer sb = new StringBuffer("INSERT INTO OPERATORS (");
			sb.append("email, login, password, phone, role) ");
			sb.append("VALUES (?, ?, ?, ?, ?)");
			PreparedStatement ps = cx.prepareStatement(sb.toString(),PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, operator.getEmail());
			ps.setString(2, operator.getLogin());
			ps.setString(3, operator.getPassword());
			ps.setString(4, operator.getPhone());
			ps.setString(5, operator.getRole());
			
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			operator.setId(rs.getLong(1));
			ps.close();

		} catch (SQLException e) {
			throw new DaoException(e);
		}
		
	}
	
	private Operator makeObject(ResultSet rs) throws SQLException {
		Operator result = new Operator();
		result.setId(rs.getLong("ID"));
		result.setEmail(rs.getString("EMAIL"));
		result.setLogin(rs.getString("LOGIN"));
		result.setPassword(rs.getString("PASSWORD"));
		result.setPhone(rs.getString("PHONE"));
		result.setRole(rs.getString("ROLE"));
		return result;
	}

}
