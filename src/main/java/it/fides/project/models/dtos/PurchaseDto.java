package it.fides.project.models.dtos;

import java.util.List;

public class PurchaseDto {
	
	private List<Long> idsBookList;
	
	public PurchaseDto() {}

	public List<Long> getIdsBookList() {
		return idsBookList;
	}

	public void setIdsBookList(List<Long> idsBookList) {
		this.idsBookList = idsBookList;
	}
}
