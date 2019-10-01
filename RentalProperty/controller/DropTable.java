package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTable {
		public static void main(String[] args) {
			final String dbName="ApartmentDB";
			Connection con;
			try {
				con = ConnectTest.getConnection(dbName);
				Statement state=con.createStatement();
				String query="drop table Rental_Records";
				boolean result=state.execute(query);
				System.out.println(result);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
