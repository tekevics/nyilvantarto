package com.nyilvt.nyilvt.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nyilvt.nyilvt.dto.CimDto;
import com.nyilvt.nyilvt.dto.SzemelyDto;
import com.nyilvt.nyilvt.entity.Szemely;
import com.nyilvt.nyilvt.exception.InvalidAddressCountException;
import com.nyilvt.nyilvt.exception.NotFoundException;
import com.nyilvt.nyilvt.repository.SzemelyRepository;

@ExtendWith(MockitoExtension.class)
class SzemelyServiceTest {
	@Mock
	private SzemelyRepository szemelyRepository;

	@InjectMocks
	private SzemelyService szemelyService;

	private Szemely szemely;
	private SzemelyDto szemelyDto;

	@BeforeEach
	void setUp() {
		szemely = new Szemely();
		szemely.setId(1L);
		szemely.setNev("Teszt Elek");

		szemelyDto = new SzemelyDto();
		szemelyDto.setNev("Teszt Elek");
		szemelyDto.setCim(new ArrayList<>());
		szemelyDto.setElerhetoseg(new ArrayList<>());
	}

	@Test
	void testCreateSzemely_Success() {
		when(szemelyRepository.save(any(Szemely.class))).thenReturn(szemely);

		Szemely createdSzemely = szemelyService.createSzemely(szemelyDto);

		assertNotNull(createdSzemely);
		assertEquals("Teszt Elek", createdSzemely.getNev());
		verify(szemelyRepository, times(1)).save(any(Szemely.class));
	}

	@Test
	void testCreateSzemely_TooManyAddresses_ThrowsException() {
		CimDto cim1 = new CimDto();
		CimDto cim2 = new CimDto();
		CimDto cim3 = new CimDto();
		List<CimDto> cimek = List.of(cim1, cim2, cim3);
		szemelyDto.setCim(cimek);

		assertThrows(InvalidAddressCountException.class, () -> szemelyService.createSzemely(szemelyDto));
		verify(szemelyRepository, times(0)).save(any(Szemely.class));
	}

	@Test
	void testGetSzemelyById_Found() {
		when(szemelyRepository.findById(1L)).thenReturn(Optional.of(szemely));
		Optional<Szemely> foundSzemely = szemelyService.getSzemelyById(1L);
		assertTrue(foundSzemely.isPresent());
		assertEquals(szemely.getNev(), foundSzemely.get().getNev());
	}

	@Test
	void testGetSzemelyById_NotFound() {
		when(szemelyRepository.findById(anyLong())).thenReturn(Optional.empty());
		Optional<Szemely> foundSzemely = szemelyService.getSzemelyById(99L);
		assertFalse(foundSzemely.isPresent());
	}

	@Test
	void testDeleteSzemely_Success() {
		when(szemelyRepository.existsById(1L)).thenReturn(true);
		doNothing().when(szemelyRepository).deleteById(1L);

		assertDoesNotThrow(() -> szemelyService.deleteSzemely(1L));
		verify(szemelyRepository, times(1)).deleteById(1L);
	}

	@Test
	void testDeleteSzemely_NotFound_ThrowsException() {
		when(szemelyRepository.existsById(anyLong())).thenReturn(false);

		assertThrows(NotFoundException.class, () -> szemelyService.deleteSzemely(99L));
		verify(szemelyRepository, times(0)).deleteById(anyLong());
	}
}
