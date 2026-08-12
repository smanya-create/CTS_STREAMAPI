package com.iispl.main;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Scanner;

import com.iispl.dao.ChequeDao;
import com.iispl.dao.ChequeDaoImpl;
import com.iispl.model.Cheque;
import com.iispl.streamservice.AdvancedStreamService;
import com.iispl.streamservice.BasicStreamService;
import com.iispl.streamservice.BasicStreamStatisticsService;
import com.iispl.streamservice.CollectorStreamService;

public class ChequeProcessingApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChequeDao chequeDao = new ChequeDaoImpl();
		BasicStreamService basicService = new BasicStreamService(chequeDao);
		BasicStreamStatisticsService basicStatisticsService = new BasicStreamStatisticsService(chequeDao);
		CollectorStreamService collectorService = new CollectorStreamService(chequeDao);
		AdvancedStreamService advancedService = new AdvancedStreamService(chequeDao);

		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			System.out.println();
			System.out.println("========== ADVANCED CTS STREAM REPORTS ==========");
			System.out.println("1. Unique Branch/MICR Values");
			System.out.println("2. Top 5 Processing Queue");
			System.out.println("3. Paginated Cheques");
			System.out.println("4. Record Count");
			System.out.println("5. Highest/Lowest Cheque");
			System.out.println("6. Average Cheque Amount");
			System.out.println("7. Cheque Lookup Map");
			System.out.println("8. CTS Reference String");
			System.out.println("9. Count Per Branch");
			System.out.println("10. Total/Average Per Branch");
			System.out.println("11. Branch Statistics");
			System.out.println("12. Branch -> Cheque Numbers");
			System.out.println("13. Finalized Collection");
			System.out.println("14. Pipeline Diagnostics");
			System.out.println("15. Multi-Level Comparator");
			System.out.println("16.Total Amount By Branch");
			System.out.println("0. Exit");

			System.out.println("Enter choice: ");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.println("==UNIQUE CTS VALUES==");
				List<String> branches = basicService.getUniqueBranches();
				List<String> micrCode = basicService.getUniqueMicrCodes();
				System.out.println("Branches=" + branches);
				System.out.println("Micr Count=" + micrCode.size());
				System.out.println("MICR Codes=" + micrCode);
				break;
			case 2:
				System.out.println("==TOP FIVE PROCESSING QUEUES==");
				List<Cheque> topFive = basicService.getTopFive();
				int rank = 1;
				for (Cheque cheque : topFive) {

					System.out.printf("%d. %s | %s | %.2f%n", rank++, cheque.getChequeNumber(), cheque.getBranchCode(),
							cheque.getAmount());
				}
				break;
			case 3:
				System.out.println("Enter page number=");
				int pageNumber = scanner.nextInt();
				System.out.println("Enter page size=");
				int pageSize = scanner.nextInt();

				List<Cheque> page = basicService.getPage(pageNumber, pageSize);

				System.out.println("page number=" + pageNumber);
				System.out.println("page size=" + pageSize);
				System.out.println("===== CHEQUE PAGE " + pageNumber + " =====");

				for (Cheque cheque : page) {

					System.out.println(cheque.getChequeNumber());
				}

				break;
			case 4:
				System.out.println("==CTS RECORD COUNT==");
				long count = basicStatisticsService.getChequeCount();
				System.out.println("total cheques=" + count);
				break;

			case 5:
				System.out.println("==HIGHEST/LOWEST==");
				Optional<Cheque> highest = basicStatisticsService.getHighestCheque();

				Optional<Cheque> lowest = basicStatisticsService.getLowestCheque();

				if (highest.isPresent()) {
					Cheque cheque = highest.get();
					System.out.printf("Highest : %s | %.2f%n", cheque.getChequeNumber(), cheque.getAmount());
				}

				if (lowest.isPresent()) {
					Cheque cheque = lowest.get();
					System.out.printf("Lowest : %s | %.2f%n", cheque.getChequeNumber(), cheque.getAmount());
				}

				break;
			case 6:

				System.out.println("===== AVERAGE CHEQUE AMOUNT =====");

				OptionalDouble average = basicStatisticsService.getAverageAmount();

				if (average.isPresent()) {

					System.out.printf("Average Amount : %.2f%n", average.getAsDouble());

				} else {

					System.out.println("No cheque records available.");
				}

				break;
			case 7:

				System.out.println("===== CHEQUE LOOKUP =====");
				Map<String, Cheque> chequeMap = collectorService.getChequeLookup();
				chequeMap.forEach((chequeNumber, cheque) -> {
				if (cheque != null) {
					System.out.printf(
					        "Key : %s | Customer : %s | Amount : %.2f | Branch : %s%n",
					        chequeNumber,
					        cheque.getCustomerName(),
					        cheque.getAmount(),
					        cheque.getBranchCode()
					    );
				}
				}
			);

				break;
			case 8:

				System.out.println("===== APPROVED CTS REFERENCES =====");

				String references = collectorService.getApprovedChequeReferences();

				System.out.println(references);

				break;
			case 9:

				System.out.println("===== CHEQUE COUNT BY BRANCH =====");

				Map<String, Long> countByBranch = collectorService.getChequeCountByBranch();

				countByBranch.forEach((branch, branchCount) -> {

					System.out.println(branch + " -> " + branchCount);
				});

				break;
			case 10:

				System.out.println("===== BRANCH AMOUNT SUMMARY =====");

				Map<String, Double> totalByBranch = collectorService.getBranchTotalAmounts();
				Map<String, Double> averageByBranch = collectorService.getBranchAverageAmounts();
				totalByBranch.forEach((branch, total) -> {
					double avg = averageByBranch.get(branch);
					System.out.printf("%s | Total: %.2f | Average: %.2f%n", branch, total, avg);
				});

				break;
			case 11:

				System.out.println("===== BRANCH STATISTICS =====");

				Map<String, DoubleSummaryStatistics> statistics = collectorService.getBranchStatistics();

				statistics.forEach((branch, stats) -> {

					System.out.printf("%s -> Count=%d, " + "Sum=%.2f, " + "Avg=%.2f, " + "Min=%.2f, " + "Max=%.2f%n",

							branch, stats.getCount(), stats.getSum(), stats.getAverage(), stats.getMin(),
							stats.getMax());
				});

				break;
			case 12:

				System.out.println("===== BRANCH -> CHEQUE NUMBERS =====");

				Map<String, List<String>> chequeNumbers = advancedService.getChequeNumbersByBranch();

				chequeNumbers.forEach((branch, numbers) -> {

					System.out.println(branch + " -> " + numbers);
				});

				break;
			case 13:
				System.out.println("===== FINALIZED CTS RESULT =====");
				List<Cheque> finalized = advancedService.getFinalizedChequeCollection();
				System.out.println("Records Collected : " + finalized.size());
				try {
					finalized.add(null);
				} catch (UnsupportedOperationException e) {
					System.out.println("Modification Test : " + "UnsupportedOperationException");
					System.out.println("Result : Collection remains unchanged");
				}
				break;
			case 14:

				System.out.println("===== STREAM TRACE =====");
				advancedService.getChequeTrace();
				System.out.println("Final result produced successfully.");

				break;
			case 15:
				System.out.println("===== MULTI-LEVEL ORDER =====");
				List<Cheque> orderedCheques = advancedService.getMultiLevelOrderedCheques();
				for (Cheque c : orderedCheques) {
					System.out.printf("%s | %s | %.2f%n", c.getBranchCode(), c.getChequeNumber(), c.getAmount());
				}
				break;
			case 16:
				System.out.println("====TOTAL AMOUNT BY BRANCH====");
				Map<String,Double> amountByBranch = advancedService.getTotalAmountByBranch();
				amountByBranch.forEach((branch, total) -> {

					System.out.println(branch + " -> " + total);
				});
				break;
			case 0:
				System.out.println("Exiting application...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}

		} while (choice != 0);

		scanner.close();

	}

}
