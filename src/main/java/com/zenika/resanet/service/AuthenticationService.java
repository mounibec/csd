package com.zenika.resanet.service;

import java.sql.Connection;

import com.zenika.resanet.exception.OperatorNotFoundException;
import com.zenika.resanet.model.Operator;

public interface AuthenticationService {

    /**
     * Authenticates with a login and a password
     *
     * @param login
     * @param password
     *
     * @return On success the operator corresponding to the login
     * @throws OperatorNotFoundException if user doesn't exist
     */
    public Operator authenticate(Connection cx, String login, String password) throws
            OperatorNotFoundException;


    /**
     * Finds an operator based on its login
     *
     * @param login Login of the operator to find
     * @return Operator with the right login
     * @throws OperatorNotFoundException if no operator was found with this login
     */
    public Operator findByLogin(Connection cx,String login) throws OperatorNotFoundException;

    /**
     * Saves a modified operator
     * @param operator
     */
    public void save(Connection cx, Operator operator);
}
