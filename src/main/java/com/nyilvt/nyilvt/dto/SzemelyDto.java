package com.nyilvt.nyilvt.dto;

import java.util.List;

public class SzemelyDto {
	private String nev;
	private String szuletesi_hely;
	private List<CimDto> cim;
	private List<ElerhetosegDto> elerhetoseg;

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public String getSzuletesi_hely() {
		return szuletesi_hely;
	}

	public void setSzuletesi_hely(String szuletesi_hely) {
		this.szuletesi_hely = szuletesi_hely;
	}

	public List<CimDto> getCim() {
		return cim;
	}

	public void setCim(List<CimDto> cim) {
		this.cim = cim;
	}

	public List<ElerhetosegDto> getElerhetoseg() {
		return elerhetoseg;
	}

	public void setElerhetoseg(List<ElerhetosegDto> elerhetoseg) {
		this.elerhetoseg = elerhetoseg;
	}
}
