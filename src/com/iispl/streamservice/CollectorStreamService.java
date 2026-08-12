package com.iispl.streamservice;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.iispl.dao.ChequeDao;
import com.iispl.enums.ValidationStatus;
import com.iispl.model.Cheque;

public class CollectorStreamService {
	private ChequeDao chequeDao;

    public CollectorStreamService(ChequeDao chequeDAO) {
        this.chequeDao = chequeDAO;
    }
    
 // gets chequenumber->cheque 
    public Map<String, Cheque> getChequeLookup(){
    	List<Cheque> list = chequeDao.getAllCheques();
    	
    	Map<String,Cheque> cheques = list.stream()
    			.collect(Collectors.toMap(Cheque::getChequeNumber,
    					cheque->cheque,
    					(oldCheque,newCheque)->oldCheque));
    	
		return cheques;
    	
    }

    // gets only the approved cheques
    public String getApprovedChequeReferences() {
    	List<Cheque> list = chequeDao.getAllCheques();
    	
    	String result = list.stream()
    			.filter(cheque->cheque.getValidationStatus()==ValidationStatus.APPROVED)
    			.map(Cheque::getChequeNumber)
    			.collect(Collectors.joining(","));
		return result;
    	
    }

    // BranchCode->>>count
    public Map<String, Long> getChequeCountByBranch(){
    	List<Cheque> list = chequeDao.getAllCheques();
    	Map<String, Long> result = list.stream()
    			.collect(Collectors.groupingBy(Cheque::getBranchCode,
    					Collectors.counting()));   	
		return result;
    	
    }

    // 10. Branch Amount Summary
    public Map<String, Double> getBranchTotalAmounts(){
    	List<Cheque> list = chequeDao.getAllCheques();
    	Map<String, Double> result= list.stream()
    			.collect(Collectors.groupingBy(Cheque::getBranchCode,
    					Collectors.summingDouble(Cheque::getAmount))); 
		return result;
    	
    }

    public Map<String, Double> getBranchAverageAmounts(){
    	List<Cheque> list = chequeDao.getAllCheques();
    	Map<String, Double> result= list.stream()
    			.collect(Collectors.groupingBy(Cheque::getBranchCode,
    					Collectors.averagingDouble(Cheque::getAmount))); 
		return result;    	
    }

    // 11. Branch Statistical Summary
    public Map<String, DoubleSummaryStatistics> getBranchStatistics(){
    	List<Cheque> list = chequeDao.getAllCheques();
    	Map<String, DoubleSummaryStatistics> result= list.stream()
    			.collect(Collectors.groupingBy(Cheque::getBranchCode,
    					Collectors.summarizingDouble(Cheque::getAmount))); 
		return result;    	    	
    }
}
