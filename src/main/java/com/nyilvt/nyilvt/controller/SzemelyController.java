package com.nyilvt.nyilvt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nyilvt.nyilvt.dto.SzemelyDto;
import com.nyilvt.nyilvt.entity.Szemely;
import com.nyilvt.nyilvt.service.SzemelyService;

@RestController
@RequestMapping("api/szemelyek")
public class SzemelyController {
	@Autowired
	private SzemelyService szemelyService;
	
	@GetMapping
	public List<Szemely> getAllSzemelyek(){
		return szemelyService.getAllSzemely();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Szemely> getSzemelyById(@PathVariable Long id){
		Optional<Szemely> szemely = szemelyService.getSzemelyById(id);
		
		return szemely.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Szemely> createSzemely(@RequestBody SzemelyDto szemelyDto){
		Szemely createdSzemely = szemelyService.createSzemely(szemelyDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createdSzemely);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Szemely> updateSzemely(@PathVariable Long id, @RequestBody SzemelyDto szemelyDto){
		Szemely updateSzemely = szemelyService.updateSzemely(id, szemelyDto);
		
		return ResponseEntity.ok(updateSzemely);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSzemely(@PathVariable Long id){
		szemelyService.deleteSzemely(id);
		
		return ResponseEntity.noContent().build();
	}
}
