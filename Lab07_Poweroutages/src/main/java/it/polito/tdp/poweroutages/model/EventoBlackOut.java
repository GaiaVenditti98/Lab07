package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventoBlackOut {
	@Override
	public String toString() {
		return dataEventfinished.getYear()+" "+ dataEventbegan+" "+dataEventfinished +" "+id+" "+costumersAffected;
	}

	private int id;
	private int costumersAffected;
	private LocalDateTime dataEventbegan;
	private LocalDateTime dataEventfinished;
	
	public EventoBlackOut(int id, int costumersAffected, LocalDateTime dataEventbegan,
			LocalDateTime dataEventfinished) {
		
		this.id = id;
		this.costumersAffected = costumersAffected;
		this.dataEventbegan = dataEventbegan;
		this.dataEventfinished = dataEventfinished;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCostumersAffected() {
		return costumersAffected;
	}

	public void setCostumersAffected(int costumersAffected) {
		this.costumersAffected = costumersAffected;
	}

	public LocalDateTime getDataEventbegan() {
		return dataEventbegan;
	}

	public void setDataEventbegan(LocalDateTime dataEventbegan) {
		this.dataEventbegan = dataEventbegan;
	}

	public LocalDateTime getDataEventfinished() {
		return dataEventfinished;
	}

	public void setDataEventfinished(LocalDateTime dataEventfinished) {
		this.dataEventfinished = dataEventfinished;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventoBlackOut other = (EventoBlackOut) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
	
	

}
