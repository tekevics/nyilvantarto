package com.nyilvt.nyilvt.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "szemely")
public class Szemely {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nev;
	private String szuletesi_hely;

	@OneToMany(mappedBy = "szemely", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	@JsonManagedReference
	private Set<Cim> cimek = new HashSet<Cim>();
	
	@OneToMany(mappedBy = "szemely", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	@JsonManagedReference
	private Set<Elerhetoseg> elerhetosegek = new HashSet<Elerhetoseg>();
	
	public Szemely() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public Set<Cim> getCimek() {
		return cimek;
	}

	public void setCimek(Set<Cim> cimek) {
		this.cimek = cimek;
	}

	public Set<Elerhetoseg> getElerhetosegek() {
		return elerhetosegek;
	}

	public void setElerhetosegek(Set<Elerhetoseg> elerhetosegek) {
		this.elerhetosegek = elerhetosegek;
	}

	public String getSzuletesi_hely() {
		return szuletesi_hely;
	}

	public void setSzuletesi_hely(String szuletesi_hely) {
		this.szuletesi_hely = szuletesi_hely;
	}
}
