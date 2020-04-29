package it.polito.tdp.poweroutages.model;

import java.sql.Connection;
import java.time.temporal.ChronoUnit;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.ConnectDB;
import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		
		
		
		
			
			PowerOutageDAO dao = new PowerOutageDAO() ;
			
			
			

		
		Nerc nerc=new Nerc(8, "RFC");
	   System.out.println(model.trovaWorstCase(nerc, 2, 50));
		

	}

}
