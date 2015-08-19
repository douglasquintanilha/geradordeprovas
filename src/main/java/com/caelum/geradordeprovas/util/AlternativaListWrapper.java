package com.caelum.geradordeprovas.util;

import java.util.ArrayList;
import java.util.List;

import com.caelum.geradordeprovas.models.Alternativa;

public class AlternativaListWrapper {
	private List<Alternativa> alternativaList;

	public AlternativaListWrapper() {
		this.alternativaList = new ArrayList<Alternativa>();
	}

	public List<Alternativa> getAlternativaList() {
		return alternativaList;
	}

	public void setAlternativaList(List<Alternativa> alternativaList) {
		this.alternativaList = alternativaList;
	}

	public void add (Alternativa alternativa){
		this.alternativaList.add(alternativa);
	}
	
}
