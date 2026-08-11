package com.iispl.streamservice;

import java.util.DoubleSummaryStatistics;
import java.util.Map;

import com.iispl.dao.ChequeDao;
import com.iispl.model.Cheque;


//Collectors.toMap()
//Collectors.joining()
//Collectors.counting()
//Collectors.summingDouble()
//Collectors.averagingDouble()
//Collectors.summarizingDouble()
//groupingBy()


public class CollectorStreamService {
	private ChequeDao chequeDao;

    public CollectorStreamService(ChequeDao chequeDAO) {
        this.chequeDao = chequeDAO;
    }
    
 // 7. Cheque Lookup Structure
    public Map<String, Cheque> getChequeLookup(){
		return null;
    	
    }

    // 8. CTS Reference String
    public String getApprovedChequeReferences() {
		return null;
    	
    }

    // 9. Branch Record Count
    public Map<String, Long> getChequeCountByBranch(){
		return null;
    	
    }

    // 10. Branch Amount Summary
    public Map<String, Double> getBranchTotalAmounts(){
		return null;
    	
    }

    public Map<String, Double> getBranchAverageAmounts(){
		return null;
    	
    }

    // 11. Branch Statistical Summary
    public Map<String, DoubleSummaryStatistics> getBranchStatistics(){
		return null;
    	
    }
}
