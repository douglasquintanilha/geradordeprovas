package br.com.caelum.geradordeprovas.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class TesteGmail {

	@Id
	@GeneratedValue
	private int id;
	
	private int contador = 0;
	
	public void incrementaContador(){
		this.contador++;
	}
	
}
