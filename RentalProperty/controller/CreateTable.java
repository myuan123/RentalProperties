package controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
	public static void main(String[] args) {
		/*final String dbName="ApartmentDB";
		final String Table_Name_rental_property="Rental_property";
		final String Table_name_retal_record="Rental_record";
		Connection con;
		try {
			con = ConnectTest.getConnection(dbName);
			Statement stmt=con.createStatement();
			String query="drop table RENTAL_PROPERTIESS";
			int result=stmt.executeUpdate(query);
			CreateTable ct=new  CreateTable();
			ct.checkTableExist("Rental_propertiess");
			if(result==0) {
				System.out.println("success");
			}else {
				System.out.println("fail");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		final String dbName="ApartmentDB";
		final String Table_Name_rental_property="Rental_property";
		final String Table_name_retal_record="Rental_record";

		int result;
		
	try {
			Connection con=ConnectTest.getConnection(dbName); 
			Statement state=con.createStatement();
			result=state.executeUpdate("CREATE TABLE Rental_recordss ("+ 
					"Record_id VARCHAR(100) NOT NULL,"+ 
					"Rent_data VARCHAR(100) NOT NULL," + 
					"Estimated_return_date VARCHAR(100) NOT NULL,"+ 
					"Actual_return_date VARCHAR(100),"+
					"Rental_fee VARCHAR(100),"+"Late_fee VARCHAR(100),"+
					"PRIMARY KEY (Record_id))");
			
			if(result == 0) {
				System.out.println("Table " + Table_name_retal_record + " has been created successfully");
			} else {
				System.out.println("Table " + Table_name_retal_record+ " is not created");
			}
			
			result=state.executeUpdate("CREATE TABLE Rental_propertiess ("+ 
					"Property_id VARCHAR(30) NOT NULL,"+
					"Street_number VARCHAR(30) ," + 
					"Street_name VARCHAR(30) ,"+
					"Suburb VARCHAR(30) ,"+
					"Number_bedroom VARCHAR(30),"+
					"Property_type VARCHAR(20) ,"+
					"Property_status VARCHAR(20) ,"+
					"PRIMARY KEY (Property_id))");
			if(result == 0) {
				System.out.println("Table " + Table_Name_rental_property + " has been created successfully");
			
			} else {
				System.out.println("Table " + Table_Name_rental_property+ " is not created");
			
			}
			
		
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("fail");
		}
	}
	public boolean createTable() {
		final String dbName="ApartmentDB";
		final String Table_Name_rental_property="Rental_propertiess";
		final String Table_name_retal_record="Rental_records";
		
		int result;
		
		try {
			Connection con=ConnectTest.getConnection(dbName);
			Statement state=con.createStatement();
			result=state.executeUpdate("CREATE TABLE Rental_recordss ("+ 
					"Record_id VARCHAR(100) NOT NULL,"+ 
					"Rent_data VARCHAR(100) NOT NULL," + 
					"Estimated_return_date VARCHAR(100) NOT NULL,"+ 
					"Actual_return_date VARCHAR(100),"+
					"Rental_fee VARCHAR(100),"+"Late_fee VARCHAR(100),"+
					"PRIMARY KEY (Record_id))");
			
			if(result == 0) {
				System.out.println("Table " + Table_name_retal_record + " has been created successfully");
			} else {
				System.out.println("Table " + Table_name_retal_record+ " is not created");
			}
			
			result=state.executeUpdate("CREATE TABLE Rental_propertiess ("+ 
					"Property_id VARCHAR(100) NOT NULL,"+
					"Street_number VARCHAR(100) NOT NULL," + 
					"Street_name VARCHAR(100) NOT NULL,"+
					"Suburb VARCHAR(100) NOT NULL,"+
					"Number_bedroom VARCHAR(30),"+
					"Property_type VARCHAR(100) NOT NULL,"+
					"Property_status VARCHAR(100) NOT NULL,"+
					"PRIMARY KEY (Property_id))");
			if(result == 0) {
				System.out.println("Table " + Table_Name_rental_property + " has been created successfully");
				return true;
			} else {
				System.out.println("Table " + Table_Name_rental_property+ " is not created");
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("fail");
		}
		
		return false;
	}
	
	public boolean checkTableExist(String table_name) {
		final String DB_NAME = "ApartmentDB";
		boolean ifexist=false;

		// IMPORTANT: table name is uppercase
		final String TABLE_NAME = table_name;
		
		// use try-with-resources Statement
		try (Connection con = ConnectTest.getConnection(DB_NAME)) {

			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			
			if(tables != null) {
				if (tables.next()) {
					System.out.println("Table " + TABLE_NAME + " exists.");
					ifexist=true;
				}
				else {
					System.out.println("Table " + TABLE_NAME + " does not exist.");
					ifexist=false;
				}	
				tables.close();
			} else {
				System.out.println(("Problem with retrieving database metadata"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ifexist;
	}

}
