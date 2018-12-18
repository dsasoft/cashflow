package br.com.dsasoft.cf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.dsasoft.cf.document.CostCenter;

@Service
public interface CostCenterService {

	public String save(final CostCenter costCenter);

	public void delete(final CostCenter costCenter);

	public void update(final CostCenter costCenter);

	public void findById(final String costCenterId);

	public List<CostCenter> findAll();
}
