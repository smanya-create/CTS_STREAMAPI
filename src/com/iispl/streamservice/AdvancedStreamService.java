package com.iispl.streamservice;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.iispl.dao.ChequeDao;
import com.iispl.model.Cheque;

public class AdvancedStreamService {
	private ChequeDao chequeDao;

	public AdvancedStreamService(ChequeDao chequeDAO) {
		this.chequeDao = chequeDAO;
	}

	//  Branch -> Cheque Numbers
	public Map<String, List<String>> getChequeNumbersByBranch() {
		List<Cheque> cheques = chequeDao.getAllCheques();

		return cheques.stream().collect(Collectors.groupingBy(Cheque::getBranchCode,
				Collectors.mapping(Cheque::getChequeNumber, Collectors.toList())));

	}

	// makes the list unmodifiable
	public List<Cheque> getFinalizedChequeCollection() {

		List<Cheque> cheques = chequeDao.getAllCheques();

		return cheques.stream()
				.collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
	}

	// peek used for debugging
	public List<Cheque> getChequeTrace() {

		List<Cheque> cheques = chequeDao.getAllCheques();

		return cheques.stream()
				.peek(c -> System.out.print(" TRACE " + c.getChequeNumber() + " Entered into the pipeline\n"))
				.collect(Collectors.toList());

	}

	// comparingthen to check multiple conditions
	public List<Cheque> getMultiLevelOrderedCheques() {
		Comparator<Cheque> comparator = Comparator.comparing(Cheque::getBranchCode).thenComparing(Cheque::getAmount)
				.reversed().thenComparing(Cheque::getChequeNumber);

		return chequeDao.getAllCheques().stream().sorted(comparator).collect(Collectors.toList());

	}

}
