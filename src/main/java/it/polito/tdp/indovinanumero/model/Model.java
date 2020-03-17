package it.polito.tdp.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.*;

public class Model {
	
	private final int NMAX = 100; // range numeri da 1 a NMAX
	private final int TMAX = 8; // numero di tentativi massimi
	private int segreto; // numero generato dal computer
	private int tentativiFatti;
	private boolean inGioco; // all'inizio non ho ancora cliccato nuova partita
	
	private Set<Integer> tentativi;
	
	public Model() {
		this.inGioco = false;
		this.tentativiFatti = 0;
	}
	
	public void nuovaPartita() {
    	// gestione dell'inizio di una nuova partita - logica del sistema;
    	this.segreto = (int)(Math.random() * NMAX) + 1; // random arriva fino a 0.99999
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	this.tentativi = new HashSet<Integer>();
	}
	
	public int tentativo(int tentativo) {
		//controllo se la partita e' in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita e' gia' terminata\n");
		}
		
		// controllo l'input
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero che non hai ancora utiizzato tra 1 e "+NMAX+"\n");
		}
		
		// il tentativo e' valido -> possiamo provarlo

    	this.tentativiFatti++;
    	this.tentativi.add(tentativo);
    	
    	if(this.tentativiFatti == TMAX)
    		this.inGioco = false;
    	
    	if(tentativo == this.segreto) {
    		this.inGioco = false;
    		return 0;
    	}
    	
    	if(tentativo < this.segreto)
    		return -1;
    	else
    		return 1;
	}
	
	private boolean tentativoValido(int tentativo) {
		if(tentativo < 1 || tentativo > NMAX) {
			return false;
		} else if (this.tentativi.contains(tentativo))
			return false;
		else
			return true;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
}
