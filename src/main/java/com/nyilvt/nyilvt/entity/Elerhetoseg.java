package com.nyilvt.nyilvt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "elerhetoseg")
public class Elerhetoseg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ElerhetosegTipus tipus;

	private String ertek;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "szemely_id")
	@JsonBackReference
	private Szemely szemely;

	public enum ElerhetosegTipus{
		EMAIL, TELEFON
	}
	
	public Elerhetoseg() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getErtek() {
		return ertek;
	}

	public void setErtek(String ertek) {
		this.ertek = ertek;
	}

	public Szemely getSzemely() {
		return szemely;
	}

	public void setSzemely(Szemely szemely) {
		this.szemely = szemely;
	}

	public ElerhetosegTipus getTipus() {
		return tipus;
	}

	public void setTipus(ElerhetosegTipus tipus) {
		this.tipus = tipus;
	}
}
