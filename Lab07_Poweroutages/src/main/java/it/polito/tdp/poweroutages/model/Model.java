package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.time.temporal.ChronoUnit;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	private PowerOutageDAO podao;
	private List<EventoBlackOut> eventi;
	private List<EventoBlackOut> soluzione;
	private int numMax=0;
	private int Ore=0;
	
	public Model() {
		podao = new PowerOutageDAO();
		
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public List<EventoBlackOut> trovaWorstCase(Nerc nerc, int maxYears, int maxHours) {
		this.eventi= podao.getEventiBlackOut(nerc);
		if(eventi.size()==0) {
			soluzione=null;
		}
		List<EventoBlackOut> parziale= new ArrayList<EventoBlackOut>();
		//int livello=0;
		ricorsione(parziale, maxYears, maxHours);
		return soluzione;
	}

	private void ricorsione(List<EventoBlackOut> parziale,int maxYears, int maxHours) {
		/*if(sommaOreDisservizio(parziale)>maxHours)
			return;*/
		
		/*if(troppaDifferenzaAnni(parziale,maxYears))
			return;*/
		
		//if(sommaOreDisservizio(parziale)<=maxHours) {
			if(numMax<sommaNumeroPersoneCoinvolte(parziale)) {
				numMax=sommaNumeroPersoneCoinvolte(parziale);
				this.soluzione= new ArrayList<EventoBlackOut>(parziale);
				this.Ore=sommaOreDisservizio(parziale);
			}
		//}
		
		/*if(livello==this.eventi.size())
			return;*/
		
		for(EventoBlackOut e: this.eventi) {
			if(!parziale.contains(e)) {
				parziale.add(e);
				if(sommaOreDisservizio(parziale)<=maxYears && !troppaDifferenzaAnni(parziale, maxYears)) {
					ricorsione(parziale,maxYears,maxHours);
				}
				parziale.remove(e);
			}
		}
		/*parziale.add(this.eventi.get(livello));
		ricorsione(parziale, livello+1, maxYears, maxHours);
		parziale.remove(this.eventi.get(livello));
		
		ricorsione(parziale, livello+1, maxYears, maxHours);*/
		
	}

	public int getOre() {
		return Ore;
	}

	public void setOre(int ore) {
		Ore = ore;
	}

	public boolean troppaDifferenzaAnni(List<EventoBlackOut> parziale, int maxYears) {
		if(parziale.size()>0) {
		EventoBlackOut piuRecente= parziale.get(0);
		for(EventoBlackOut e:parziale) {
			if(piuRecente.getDataEventfinished().isBefore(e.getDataEventfinished()))
				piuRecente= new EventoBlackOut(e.getId(), e.getCostumersAffected(), e.getDataEventbegan(), e.getDataEventfinished());
			}
	
		EventoBlackOut piuLontano= parziale.get(0);
		for(EventoBlackOut e:parziale) {
			if(piuLontano.getDataEventfinished().isAfter(e.getDataEventfinished()))
				piuLontano= new EventoBlackOut(e.getId(), e.getCostumersAffected(), e.getDataEventbegan(), e.getDataEventfinished());
			}
		
		int differenzaAnni= piuRecente.getDataEventfinished().getYear()-piuLontano.getDataEventfinished().getYear();
		
		if(differenzaAnni+1>maxYears) {
			
			return true;
		}
		else
			return false;
		}
		return false;
	}

	public int getNumMax() {
		return numMax;
	}

	private int sommaNumeroPersoneCoinvolte(List<EventoBlackOut> parziale) {
		int sum=0;
		for(EventoBlackOut e: parziale)
			sum+=e.getCostumersAffected();
		return sum;
	}

	private int sommaOreDisservizio(List<EventoBlackOut> parziale) {
		int sum=0;
		for(EventoBlackOut e: parziale)
			sum+= e.getDataEventbegan().until(e.getDataEventfinished(), ChronoUnit.HOURS);
		return sum;
	}


	
}
