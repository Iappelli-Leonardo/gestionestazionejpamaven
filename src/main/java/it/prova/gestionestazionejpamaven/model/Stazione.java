package it.prova.gestionestazionejpamaven.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "stazione")
public class Stazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "denominazione")
	private String denominazione;

	@Column(name = "indirizzo")
	private String indirizzo;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "stazioni")
	private Set<Treno> treni = new HashSet<Treno>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "citta_id", nullable = false)
	private Citta citta;

	public Stazione() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stazione(String denominazione, String indirizzo, Set<Treno> treni, Citta citta) {
		super();
		this.denominazione = denominazione;
		this.indirizzo = indirizzo;
		this.treni = treni;
		this.citta = citta;
	}

	public Stazione(String denominazione, String indirizzo) {
		super();
		this.denominazione = denominazione;
		this.indirizzo = indirizzo;
	}

	public Stazione(String denominazione, String indirizzo, Citta citta) {
		super();
		this.denominazione = denominazione;
		this.indirizzo = indirizzo;
		this.citta = citta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Set<Treno> getTreni() {
		return treni;
	}

	public void setTreni(Set<Treno> treni) {
		this.treni = treni;
	}

	public Citta getCitta() {
		return citta;
	}

	public void setCitta(Citta citta) {
		this.citta = citta;
	}

	@Override
	public String toString() {
		return "Stazione [id=" + id + ", denominazione=" + denominazione + ", indirizzo=" + indirizzo + "]";
	}

}
