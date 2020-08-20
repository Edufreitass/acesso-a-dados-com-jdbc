package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			/*
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, "Carl Purple"); // Name
			st.setString(2, "carl@gmail.com"); // Email
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime())); // BirthDate
			st.setDouble(4, 3000.0); // BaseSalary
			st.setInt(5, 4); // DepartmentId
			*/
			
			st = conn.prepareStatement(
					"INSERT INTO department (Name) "
					+ "VALUES ('D1'),('D2')",
					Statement.RETURN_GENERATED_KEYS);
			// Exibe quantas linhas foram alteradas no banco de dados
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					// getInt(1) indica que eu quero pegar o valor da primeira coluna
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			} else {
				System.out.println("No row affected");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
