package com.iispl.streamservice;

import java.util.List;
import java.util.Map;

import com.iispl.dao.ChequeDao;
import com.iispl.model.Cheque;

public class AdvancedStreamService {
	private ChequeDao chequeDao;

    public AdvancedStreamService(ChequeDao chequeDAO) {
        this.chequeDao = chequeDAO;
    }
    // 12. Branch -> Cheque Numbers
    public Map<String, List<String>> getChequeNumbersByBranch(){
		return null;
    	
    }

    // 13. Finalized Collection
    public List<Cheque> getFinalizedChequeCollection(){
		return null;
    	
    }

    // 14. Pipeline Diagnostics
    public List<Cheque> getChequeTrace(){
		return null;
    	
    }

    // 15. Multi-Level Comparator
    public List<Cheque> getMultiLevelOrderedCheques(){
		return null;
    	
    }

}
