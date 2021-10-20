package it.prova.gestionestazionejpamaven.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "treno")
public class Treno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "denominazione")
	private String descrizione;

	@Column(name = "codice")
	private String codice;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "stazione_treno", joinColumns = @JoinColumn(name = "treno_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "stazione_id", referencedColumnName = "ID"))
	private Set<Stazione> stazioni = new HashSet<Stazione>();

	public Treno() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Treno(String descrizione, String codice, Set<Stazione> stazioni) {
		super();
		this.descrizione = descrizione;
		this.codice = codice;
		this.stazioni = stazioni;
	}

	public Treno(String descrizione, String codice) {
		super();
		this.descrizione = descrizione;
		this.codice = codice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Set<Stazione> getStazioni() {
		return stazioni;
	}

	public void setStazioni(Set<Stazione> stazioni) {
		this.stazioni = stazioni;
	}

	@Override
	public String toString() {
		return "Treno [id=" + id + ", descrizione=" + descrizione + ", codice=" + codice + "]";
	}
	
	public void addToStazioni(Stazione stazioneInstance) {
		this.stazioni.add(stazioneInstance);
		stazioneInstance.getTreni().add(this);
	}
	
	public void removeFromStazioni(Stazione stazioneInstance) {
		this.stazioni.remove(stazioneInstance);
		stazioneInstance.getTreni().remove(this);
	}

}
