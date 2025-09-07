package com.nyilvt.nyilvt.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyilvt.nyilvt.dto.SzemelyDto;
import com.nyilvt.nyilvt.entity.Cim;
import com.nyilvt.nyilvt.entity.Elerhetoseg;
import com.nyilvt.nyilvt.entity.Szemely;
import com.nyilvt.nyilvt.exception.InvalidAddressCountException;
import com.nyilvt.nyilvt.service.SzemelyService;

@WebMvcTest(SzemelyController.class)
public class SzemelyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SzemelyService szemelyService;

	@Autowired
	private ObjectMapper objectMapper;

	private Szemely szemely;
	private SzemelyDto szemelyDto;

	@BeforeEach
	void setUp() {
		szemely = new Szemely();
		szemely.setId(1L);
		szemely.setNev("Teszt Elek");
		Cim cim = new Cim();
		cim.setUtca("Példa utca");
		cim.setVaros("Példaváros");
		cim.setTipus(Cim.CimTipus.ALLANDO);
		szemely.getCimek().add(cim);
		Elerhetoseg elerhetoseg = new Elerhetoseg();
		elerhetoseg.setErtek("teszt.elek@email.com");
		szemely.getElerhetosegek().add(elerhetoseg);

		szemelyDto = new SzemelyDto();
		szemelyDto.setNev("Teszt Elek");
		szemelyDto.setCim(Collections.emptyList());
		szemelyDto.setElerhetoseg(Collections.emptyList());
	}

	@Test
	void getAllSzemelyek_ReturnsListOfSzemelyek() throws Exception {
		when(szemelyService.getAllSzemely()).thenReturn(List.of(szemely));

		mockMvc.perform(get("/api/szemelyek")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].nev").value("Teszt Elek"));
	}

	@Test
	void getSzemelyById_Found() throws Exception {
		when(szemelyService.getSzemelyById(1L)).thenReturn(Optional.of(szemely));

		mockMvc.perform(get("/api/szemelyek/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.nev").value("Teszt Elek"));
	}

	@Test
	void createSzemely_Success() throws Exception {
		when(szemelyService.createSzemely(any(SzemelyDto.class))).thenReturn(szemely);

		mockMvc.perform(post("/api/szemelyek").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(szemelyDto))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.nev").value("Teszt Elek"));
	}

	@Test
	void createSzemely_InvalidAddressCount() throws Exception {
		doThrow(new InvalidAddressCountException("Túl sok cím")).when(szemelyService)
				.createSzemely(any(SzemelyDto.class));

		mockMvc.perform(post("/api/szemelyek").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(szemelyDto))).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.hiba").value("Túl sok cím"));
	}

	@Test
	void updateSzemely_Success() throws Exception {
		when(szemelyService.updateSzemely(anyLong(), any(SzemelyDto.class))).thenReturn(szemely);

		mockMvc.perform(put("/api/szemelyek/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(szemelyDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.nev").value("Teszt Elek"));
	}

	@Test
	void deleteSzemely_Success() throws Exception {
		doNothing().when(szemelyService).deleteSzemely(1L);

		mockMvc.perform(delete("/api/szemelyek/1")).andExpect(status().isNoContent());
	}
}
