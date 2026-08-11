package com.iispl.main;

import java.util.List;

import com.iispl.dao.ChequeDao;
import com.iispl.dao.ChequeDaoImpl;
import com.iispl.model.Cheque;

public class Testmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChequeDao dao = new ChequeDaoImpl();

		List<Cheque> cheques = dao.getAllCheques();

		System.out.println("Total cheques: " + cheques.size());

	}

}
