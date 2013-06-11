package com.zenika.resanet.service.impl;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.zenika.resanet.dao.ConnectionManager;
import com.zenika.resanet.dao.impl.OperatorDaoImpl;
import com.zenika.resanet.exception.OperatorNotFoundException;
import com.zenika.resanet.model.Operator;
import com.zenika.resanet.service.AuthenticationService;

public class AuthenticationServiceTest{

	private AuthenticationService authenticationService;
	private Connection cx;

	@Before
	public void setUp() throws Exception {
		authenticationService = new AuthenticationServiceImpl(new OperatorDaoImpl());
		cx = ConnectionManager.getInstance().getConnection();
	}

	@Test
	public void testFindByLoginOK() {
		try{
			authenticationService.findByLogin(cx, "admin");
		}catch(OperatorNotFoundException exception){
			fail("Operator \"admin\" not found");
		}
		
		try{
			authenticationService.findByLogin(cx, "Pierre");
		}catch(OperatorNotFoundException exception){
			fail("Operator \"Pierre\" not found");
		}
		
	}

	@Test
	public void testFindByLoginKO() {
		try{
			authenticationService.findByLogin(cx, "DoesNotExist");
			fail("No exception catched");
		}catch(OperatorNotFoundException exception){
			
		}
	}

	@Test
	public void testAuthenticateOK() {
		try{
			authenticationService.authenticate(cx, "admin", "adminadmin");
		}catch(OperatorNotFoundException exception){
			fail("Operator \"admin\" not found");
		}
		try{
			authenticationService.authenticate(cx, "Pierre", "P@ssw0rd");
		}catch(OperatorNotFoundException exception){
			fail("Operator \"Pierre\" not found");
		}
	}

	@Test
	public void testAuthenticateKO() {
		try{
			authenticationService.authenticate(cx, "admin", "admin");
			fail("user admin/admin should not authenticate");
		}catch(OperatorNotFoundException exception){
		}
	}

	@Test
	public void testSaveOK() {
		Operator operator = new Operator();
		operator.setEmail("newUser@mail.com");
		operator.setLogin("newuser");
		operator.setPassword("newuserpassword");
		operator.setPhone("1234");
		operator.setRole("USER");
		
		authenticationService.save(cx,operator);
		
		Operator operatorSaved = authenticationService.findByLogin(cx,"newuser");
		assertEquals(operator, operatorSaved);
		try {
			cx.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
