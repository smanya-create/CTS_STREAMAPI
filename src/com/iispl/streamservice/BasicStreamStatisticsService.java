package com.iispl.streamservice;

import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalDouble;

import com.iispl.dao.ChequeDao;
import com.iispl.model.Cheque;

public class BasicStreamStatisticsService {
	private ChequeDao chequeDao;

    public BasicStreamStatisticsService(ChequeDao chequeDAO) {
        this.chequeDao = chequeDAO;
    }
    
    // 4. CTS Record Count
    public long getChequeCount() {
		return chequeDao.getAllCheques().stream()
				.count();
    	
    }

    // 5. Amount Extremes
    public Optional<Cheque> getHighestCheque(){
		return chequeDao.getAllCheques().stream()
				.max(Comparator.comparingDouble(Cheque::getAmount));
    	
    }

    public Optional<Cheque> getLowestCheque(){
		return chequeDao.getAllCheques().stream()
				.min(Comparator.comparingDouble(Cheque::getAmount));
    	
    }

    // 6. Average Cheque Amount
    public OptionalDouble getAverageAmount() {
		return chequeDao.getAllCheques().stream()
				.mapToDouble(Cheque::getAmount).average();
    	
    }

}
