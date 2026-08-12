package com.iispl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iispl.enums.AccountStatus;
import com.iispl.enums.ChequeType;
import com.iispl.enums.MicrStatus;
import com.iispl.enums.ValidationStatus;
import com.iispl.model.Cheque;
import com.iispl.util.DBUtil;

public class ChequeDaoImpl implements ChequeDao {
//method to get all cheques from the database
	@Override
	public List<Cheque> getAllCheques() {

		List<Cheque> cheques = new ArrayList<>();
		String sql = "SELECT * FROM cts_cheque";
		try (Connection con = DBUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Cheque cheque = mapCheque(rs);
				cheques.add(cheque);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cheques;
	}

	@Override
	public List<Cheque> getChequesByBatch(int batchId) {

		List<Cheque> cheques = new ArrayList<>();

		String sql = "SELECT * FROM cts_cheque WHERE batch_id = ?";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, batchId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				cheques.add(mapCheque(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cheques;
	}

	@Override
	public Cheque getChequeByNumber(String chequeNumber) {

		String sql = "SELECT * FROM cts_cheque WHERE cheque_number = ?";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, chequeNumber);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return mapCheque(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void updateMicrStatus(String chequeNumber, MicrStatus status) {

		String sql = "UPDATE cts_cheque " + "SET micr_status = ? " + "WHERE cheque_number = ?";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, status.name());
			ps.setString(2, chequeNumber);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateValidationStatus(String chequeNumber, ValidationStatus status) {

		String sql = "UPDATE cts_cheque " + "SET validation_status = ? " + "WHERE cheque_number = ?";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, status.name());
			ps.setString(2, chequeNumber);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Cheque mapCheque(ResultSet rs) throws Exception {

		return new Cheque(rs.getInt("cheque_id"), rs.getString("cheque_number"), rs.getString("account_number"),
				rs.getString("customer_name"), rs.getString("branch_code"), rs.getString("micr_code"),
				rs.getDouble("amount"), rs.getDouble("available_balance"), rs.getDate("cheque_date").toLocalDate(),
				AccountStatus.valueOf(rs.getString("account_status")), ChequeType.valueOf(rs.getString("cheque_type")),
				MicrStatus.valueOf(rs.getString("micr_status")),
				ValidationStatus.valueOf(rs.getString("validation_status")), rs.getInt("batch_id"));
	}
}