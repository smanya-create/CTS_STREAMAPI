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
    
    // counts the total number of cheques
    public long getChequeCount() {
		return chequeDao.getAllCheques().stream()
				.count();
    	
    }

    // gets the highesht and lowest cheque amounts
    public Optional<Cheque> getHighestCheque(){
		return chequeDao.getAllCheques().stream()
				.max(Comparator.comparingDouble(Cheque::getAmount));
    	
    }

    public Optional<Cheque> getLowestCheque(){
		return chequeDao.getAllCheques().stream()
				.min(Comparator.comparingDouble(Cheque::getAmount));
    	
    }

    // gets average checkamount
    public OptionalDouble getAverageAmount() {
		return chequeDao.getAllCheques().stream()
				.mapToDouble(Cheque::getAmount).average();
    	
    }

}
