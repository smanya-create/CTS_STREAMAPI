package com.iispl.streamservice;

import java.util.List;

import com.iispl.dao.ChequeDao;
import com.iispl.model.Cheque;

public class BasicStreamService {
	private ChequeDao chequeDao;

    public BasicStreamService(ChequeDao chequeDAO) {
        this.chequeDao = chequeDAO;
    }
    // 1. Unique CTS Values
    public List<String> getUniqueBranches(){
		return null;
    	
    }

    public List<String> getUniqueMicrCodes(){
		return null;
    	
    }

    // 2. Top Five Processing Records
    public List<Cheque> getTopFive(){
		return null;
    	
    }

    // 3. Cheque Pagination
    public List<Cheque> getPage(int pageNumber, int pageSize){
		return null;
    	
    }

}
