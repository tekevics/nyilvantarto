package com.nyilvt.nyilvt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nyilvt.nyilvt.dto.SzemelyDto;
import com.nyilvt.nyilvt.entity.Cim;
import com.nyilvt.nyilvt.entity.Elerhetoseg;
import com.nyilvt.nyilvt.entity.Szemely;
import com.nyilvt.nyilvt.exception.InvalidAddressCountException;
import com.nyilvt.nyilvt.repository.SzemelyRepository;

@Service
public class SzemelyService {
	@Autowired
	private SzemelyRepository szemelyRepository;

	public List<Szemely> getAllSzemely() {
		return szemelyRepository.findAll();
	}

	public Optional<Szemely> getSzemelyById(Long id) {
		return szemelyRepository.findById(id);
	}

	@Transactional
	public Szemely createSzemely(SzemelyDto szemelyDto) {
		Szemely szemely = new Szemely();

		szemely.setNev(szemelyDto.getNev());
		szemely.setSzuletesi_hely(szemelyDto.getSzuletesi_hely());

		updateSzemelyRelationships(szemely, szemelyDto);

		return szemelyRepository.save(szemely);
	}

	@Transactional
	public Szemely updateSzemely(Long id, SzemelyDto szemelyDto) {
		Szemely existingSzemely = szemelyRepository.findById(id)
				.orElseThrow(() -> new com.nyilvt.nyilvt.exception.NotFoundException("A személy nem található: " + id));

		existingSzemely.setNev(szemelyDto.getNev());

		updateSzemelyRelationships(existingSzemely, szemelyDto);

		return szemelyRepository.save(existingSzemely);
	}

	public void deleteSzemely(Long id) {
		if (!szemelyRepository.existsById(id)) {
			throw new com.nyilvt.nyilvt.exception.NotFoundException("A személy nem található: " + id);
		}

		szemelyRepository.deleteById(id);
	}

	private void updateSzemelyRelationships(Szemely szemely, SzemelyDto szemelyDto) {
		if (szemelyDto.getCim() != null) {
			if (szemelyDto.getCim().size() > 2) {
				throw new InvalidAddressCountException("Egy személynek legfeljebb 2 címe lehet!");
			}

			szemely.getCimek().clear();
			szemely.getCimek().addAll(szemelyDto.getCim().stream().map(cimDto -> {
				Cim cim = new Cim();

				cim.setIranyitoszam(cimDto.getIranyitoszam());
				cim.setVaros(cimDto.getVaros());
				cim.setUtca(cimDto.getUtca());
				cim.setSzemely(szemely);
				
				try {
					cim.setTipus(Cim.CimTipus.valueOf(cimDto.getTipus().toUpperCase()));
				} catch (IllegalArgumentException e) {
					throw new IllegalArgumentException("Érvénytelen címtípus: " + cimDto.getTipus());
				}

				return cim;
			}).collect(Collectors.toSet()));
		}

		if (szemelyDto.getElerhetoseg() != null) {
			szemely.getElerhetosegek().clear();

			szemely.getElerhetosegek().addAll(szemelyDto.getElerhetoseg().stream().map(elerhetosegDto -> {
				Elerhetoseg elerhetoseg = new Elerhetoseg();

//				elerhetoseg.setTipus(elerhetosegDto.getTipus());
				elerhetoseg.setErtek(elerhetosegDto.getErtek());
				elerhetoseg.setSzemely(szemely);

				try {
					elerhetoseg.setTipus(Elerhetoseg.ElerhetosegTipus.valueOf(elerhetosegDto.getTipus().toUpperCase()));
				} catch (IllegalArgumentException e) {
					throw new IllegalArgumentException("Érvénytelen elérhetőség típus: " + elerhetosegDto.getTipus());
				}

				return elerhetoseg;
			}).collect(Collectors.toSet()));
		}
	}
}
