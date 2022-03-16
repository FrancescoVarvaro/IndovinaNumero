package it.polito.tdp.IndovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	//Variabili del modello
	private int segreto;
	private final int TMAX = 8;
	private final int NMAX = 100;
	private int tentativiFatti;
	private Set <Integer> tentativi; //non devo permettere di inserire piu volte lo stesso numero
	
	private boolean inGioco = false; // dobbiamo verificare se i tentativi sono esauriti
	
/*	public Model() {
		tentativi = new HashSet<Integer>(); // se lo creiamo qui, dobbiamo ricordarci di pulire sempre il Set dalla partita precedente
											// lo creo quindi in tentativo.
*/	
	
	public void nuovaPartita() {
		//gestione di una nuova partita
    	this.segreto = (int)((Math.random() * NMAX) +1);
    	this.tentativiFatti = 0;
    	this.inGioco = true;
	}
						
	public int tentativo(int tentativo) {
		// ritorno 0 se indovinato, 1 se troppo alto, -1 se troppo basso
		tentativi = new HashSet<Integer>();  
		
		if(!inGioco)
			throw new IllegalStateException("HAI PERSO: La partita è terminata");
		
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero tra 1 e "+NMAX);
		}
		
		this.tentativiFatti++;
		this.tentativi.add(tentativo);
		
		if(this.tentativiFatti == TMAX) {
			this.inGioco = false;
		}
			
		if(tentativo == segreto) {
			this.inGioco = false;
			return 0;
		} else if (tentativo < segreto) {
			return -1;
		} else 
			return 1;
		
	}
	
	private boolean tentativoValido(int tentativo) {
		// TODO Auto-generated method stub
		if(tentativo < 1 || tentativo > NMAX || tentativi.contains(tentativo)) {
			return false;
		}
		return true;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTMAX() {
		return TMAX;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}
	
}
