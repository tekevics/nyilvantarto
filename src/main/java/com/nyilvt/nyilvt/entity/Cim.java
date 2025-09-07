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
@Table(name = "cim")
public class Cim {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String iranyitoszam;
	private String varos;
	private String utca;

	@Enumerated(EnumType.STRING)
	private CimTipus tipus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "szemely_id")
	@JsonBackReference
	private Szemely szemely;

	public Cim() {
	}

	public enum CimTipus {
		ALLANDO, IDEIGLENES
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Szemely getSzemely() {
		return szemely;
	}

	public void setSzemely(Szemely szemely) {
		this.szemely = szemely;
	}

	public String getUtca() {
		return utca;
	}

	public void setUtca(String utca) {
		this.utca = utca;
	}

	public String getVaros() {
		return varos;
	}

	public void setVaros(String varos) {
		this.varos = varos;
	}

	public String getIranyitoszam() {
		return iranyitoszam;
	}

	public void setIranyitoszam(String iranyitoszam) {
		this.iranyitoszam = iranyitoszam;
	}

	public CimTipus getTipus() {
		return tipus;
	}

	public void setTipus(CimTipus tipus) {
		this.tipus = tipus;
	}
}
