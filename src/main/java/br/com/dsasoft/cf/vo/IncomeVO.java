package br.com.dsasoft.cf.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import javax.validation.constraints.Min;

import br.com.dsasoft.cf.db.document.Account;
import br.com.dsasoft.cf.db.document.CostCenter;

public class IncomeVO {

	private final Account account;

	private final BigDecimal amount;

	private final CostCenter costCenter;

	private final Date datetime;

	public IncomeVO(Account account, @Min(value = 0, message = "${validation.positive}") BigDecimal amount,
			CostCenter costCenter) {
		super();
		this.account = account;
		this.amount = amount;
		this.costCenter = costCenter;
		this.datetime = Date.from(LocalDateTime.now().toInstant((ZoneOffset) ZoneOffset.systemDefault()));
	}

	public Account getAccount() {
		return account;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public CostCenter getCostCenter() {
		return costCenter;
	}

	public Date getDatetime() {
		return datetime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((costCenter == null) ? 0 : costCenter.hashCode());
		result = prime * result + ((datetime == null) ? 0 : datetime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IncomeVO other = (IncomeVO) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (costCenter == null) {
			if (other.costCenter != null)
				return false;
		} else if (!costCenter.equals(other.costCenter))
			return false;
		if (datetime == null) {
			if (other.datetime != null)
				return false;
		} else if (!datetime.equals(other.datetime))
			return false;
		return true;
	}

}
