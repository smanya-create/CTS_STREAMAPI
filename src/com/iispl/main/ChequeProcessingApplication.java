package com.iispl.main;

import com.iispl.dao.ChequeDao;
import com.iispl.dao.ChequeDaoImpl;
import com.iispl.streamservice.AdvancedStreamService;
import com.iispl.streamservice.BasicStreamService;
import com.iispl.streamservice.BasicStreamService2;
import com.iispl.streamservice.CollectorStreamService;

public class ChequeProcessingApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChequeDao chequeDao=new ChequeDaoImpl();
		BasicStreamService basicService=new BasicStreamService(chequeDao);
		BasicStreamService2 basicService2=new BasicStreamService2(chequeDao);
		CollectorStreamService collectorService=new CollectorStreamService(chequeDao);
		AdvancedStreamService advancedService=new AdvancedStreamService(chequeDao);

	}

}
