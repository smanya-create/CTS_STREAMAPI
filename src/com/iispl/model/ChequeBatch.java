package com.iispl.model;

import java.time.LocalDate;
import java.util.List;

public class ChequeBatch {
	int batchId;
	String batchNumber;
	String branchCode;
	LocalDate batchDate;
	String batchStatus;
	List<Cheque> cheques;

}
