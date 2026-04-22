package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SaleSummaryDTO> getSummary(String minDate,String maxDate){
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate max = (maxDate == null) ? today : LocalDate.parse(maxDate);
		LocalDate min = (minDate == null) ? today.minusYears(1L) : LocalDate.parse(minDate);
		return repository.getSummary(min, max);
	}

	/*LocalDate max;
if (maxDate == null) {
    max = today;
} else {
    max = LocalDate.parse(maxDate);
} */
	
	public Page<SaleMinDTO> getReport(Pageable pageable, String minDate,String maxDate, String name){

		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate max = (maxDate == null) ? today : LocalDate.parse(maxDate);
		LocalDate min = (minDate == null) ? today.minusYears(1L) : LocalDate.parse(minDate);

		Page<SaleMinDTO> result = repository.getReport(pageable, min, max, name);
		return result;

	}
	
}
