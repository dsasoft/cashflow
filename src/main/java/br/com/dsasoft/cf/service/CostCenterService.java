package br.com.dsasoft.cf.service;

import java.util.List;

import br.com.dsasoft.cf.db.document.CostCenter;

public interface CostCenterService extends DefaultBasicService<CostCenter> {

	public CostCenter save(final CostCenter costCenter);

	public void delete(final CostCenter costCenter);

	public CostCenter update(final CostCenter costCenter);

	public CostCenter findById(final String costCenterId);

	public List<CostCenter> findAll();
}
