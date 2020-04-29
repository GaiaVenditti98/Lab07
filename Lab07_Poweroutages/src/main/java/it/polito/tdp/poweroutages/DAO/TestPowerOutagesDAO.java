package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.time.*;
import java.time.temporal.ChronoUnit;

import it.polito.tdp.poweroutages.model.EventoBlackOut;
import it.polito.tdp.poweroutages.model.Nerc;

public class TestPowerOutagesDAO {

	public static void main(String[] args) {
		
		try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			PowerOutageDAO dao = new PowerOutageDAO() ;
			
			System.out.println(dao.getNercList()) ;

		} catch (Exception e) {
			System.err.println("Test FAILED");
		}
		
		try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			PowerOutageDAO dao = new PowerOutageDAO() ;
			
			System.out.println(dao.getEventiBlackOut(new Nerc(5, "HI"))) ;
			
			for(EventoBlackOut e:  dao.getEventiBlackOut(new Nerc(5,"HI")))
				System.out.println(e.getDataEventbegan().until(e.getDataEventfinished(),ChronoUnit.HOURS));

		} catch (Exception e) {
			System.err.println("Test FAILED");
		}
		
		
		

	}

}
