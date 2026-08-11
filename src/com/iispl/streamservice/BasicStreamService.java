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

	// 1. Unique CTS Values
	public List<String> getUniqueBranches() {
		return chequeDao.getAllCheques().stream().map(Cheque::getBranchCode).distinct().collect(Collectors.toList());

	}

	public List<String> getUniqueMicrCodes() {
		return chequeDao.getAllCheques().stream().map(Cheque::getMicrCode).distinct().collect(Collectors.toList());
	}

	// 2. Top Five Processing Records
	public List<Cheque> getTopFive() {
		return chequeDao.getAllCheques().stream().sorted(Comparator.comparing(Cheque::getAmount).reversed()).limit(5)
				.collect(Collectors.toList());
	}

	// 3. Cheque Pagination
	public List<Cheque> getPage(int pageNumber, int pageSize) {
		return chequeDao.getAllCheques().stream().skip((pageNumber - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
	}

}
