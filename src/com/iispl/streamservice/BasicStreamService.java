package com.iispl.streamservice;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.iispl.dao.ChequeDao;
import com.iispl.model.Cheque;

public class BasicStreamService {
	private ChequeDao chequeDao;

	public BasicStreamService(ChequeDao chequeDAO) {
		this.chequeDao = chequeDAO;
	}

	// gets unique branch code
	public List<String> getUniqueBranches() {
		return chequeDao.getAllCheques().stream().map(Cheque::getBranchCode).distinct().collect(Collectors.toList());

	}
//gets unique micr codes
	public List<String> getUniqueMicrCodes() {
		return chequeDao.getAllCheques().stream().map(Cheque::getMicrCode).distinct().collect(Collectors.toList());
	}

//gets top five records after sorting in descending order
	public List<Cheque> getTopFive() {
		return chequeDao.getAllCheques().stream().sorted(Comparator.comparing(Cheque::getAmount).reversed()).limit(5)
				.collect(Collectors.toList());
	}

//cheque pagination using skip and limit
	public List<Cheque> getPage(int pageNumber, int pageSize) {
		return chequeDao.getAllCheques().stream().skip((pageNumber - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
	}

}
