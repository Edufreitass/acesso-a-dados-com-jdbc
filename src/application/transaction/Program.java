package application.transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();
			
			// Não confirma as operações automaticamente, essas operações ficaram pendentes de uma confirmação
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			// Simulando uma falha no meio da transação, gerando uma exception
			/*int x = 1;
			if (x < 2) {
				throw new SQLException("Fake error");
			}*/
			
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			// Confirma que a transação terminou
			conn.commit();
			
			System.out.println("rows1 " + rows1);
			System.out.println("rows2 " + rows2);
		} catch (SQLException e) {
			// Volta ao estado inicial do banco
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
