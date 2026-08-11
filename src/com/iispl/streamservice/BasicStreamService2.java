package com.iispl.streamservice;

import java.util.Optional;
import java.util.OptionalDouble;

import com.iispl.dao.ChequeDao;
import com.iispl.model.Cheque;

public class BasicStreamService2 {
	private ChequeDao chequeDao;

    public BasicStreamService2(ChequeDao chequeDAO) {
        this.chequeDao = chequeDAO;
    }
    
    // 4. CTS Record Count
    public long getChequeCount() {
		return 0;
    	
    }

    // 5. Amount Extremes
    public Optional<Cheque> getHighestCheque(){
		return null;
    	
    }

    public Optional<Cheque> getLowestCheque(){
		return null;
    	
    }

    // 6. Average Cheque Amount
    public OptionalDouble getAverageAmount() {
		return null;
    	
    }

}
