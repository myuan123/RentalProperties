package controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;




public class RentalRecordOperation {
	public static void main(String[] args) {
		Map<String, String> map=new HashMap<String,String>();
		map.put("Record_id", "11111111111111111");
		map.put("Rent_data", "2222222222222");
		map.put("Estimated_return_date", "3333333333");
		map.put("Actual_return_date", "4444444444444");
		map.put("Rental_fee", "555555555555");
		map.put("Late_fee","66666666666");
		RentalRecordOperation rro=new RentalRecordOperation();
		rro.insertRentalRecords(map);
	}
	
	public Boolean insertRentalRecords(Map<String, String> map) { 
		Boolean b=true;
		final String dbName="ApartmentDB";
		final String table_name="Rental_recordss";
		String[][] table=new String[10][2];
		Object a;
		int i=0;
		try {
			Connection con=ConnectTest.getConnection(dbName);
			Statement stmt=con.createStatement();
			Set<String> set=map.keySet();
			Iterator<String> iter=set.iterator();
			while(iter.hasNext()) {
				a=iter.next();
				table[i][0]=(String)a;
				table[i][1]=(String)map.get(a);
				i++;
			}
			String aa="INSERT INTO " + table_name + 
					"("+table[0][0]+", "+table[1][0]+", "+table[2][0]+", "+table[3][0]+", "+table[4][0]+", "+table[5][0]+") VALUES ('"+table[0][1]+"', '"+table[1][1]+"', '"+table[2][1]+"','"+table[3][1]+"', '"+table[4][1]+"', '"+table[5][1]+"')";
			System.out.println(aa);
			if(checkTableExist(table_name)) {
				int result=stmt.executeUpdate(aa);
				if(result==0) {
					System.out.println("success");
				}else {
					System.out.println("fail");
				}
			}else {	
				CreateTable ct=new CreateTable();
				ct.createTable();
				int result=stmt.executeUpdate(aa);
				if(result==0) {
					System.out.println("success");
				}else {
					System.out.println("fail");
				}
			}		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CreateTable cr=new CreateTable();
		cr.createTable();
		
		return b;
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

		CreateTable cr=new CreateTable();
		cr.createTable();
		
		return ifexist;
	}
	
	public boolean insertApartment(Map<String,String> map) {
		boolean exsit=true;
		final String dbName="ApartmentDB";
		final String table_name="Rental_propertiess";
		String[][] table=new String[10][2];
		String a;
		int i=0;
		try {
			Connection con=ConnectTest.getConnection(dbName);
			Statement stmt=con.createStatement();
			Set<String> set=map.keySet();
			Iterator<String> iter=set.iterator();
			while(iter.hasNext()) {
				a=iter.next();
				table[i][0]=(String)a;
				table[i][1]=(String)map.get(a);
				i++;
			}
			String aa="INSERT INTO " + table_name + 
					"("+table[0][0]+", "+table[1][0]+", "+table[2][0]+", "+table[3][0]+", "+table[4][0]+", "+table[5][0]+", "+table[6][0]+
					") VALUES ('"+table[0][1]+"', '"+table[1][1]+"', '"+table[2][1]+"','"+table[3][1]+"', '"+table[4][1]+"', '"+table[5][1]+"', '"+table[6][1]+"')";
			System.out.println(aa);
			if(checkTableExist(table_name)) {
				int result=stmt.executeUpdate(aa);
				if(result==0) {
					System.out.println("success");
				}else {
					System.out.println("fail");
				}
			}else {	
				CreateTable ct=new CreateTable();
				ct.createTable();
				int result=stmt.executeUpdate(aa);
				if(result==0) {
					System.out.println("success");
				}else {
					System.out.println("fail");
				}
			}		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exsit;
	}
	
	
	public Map<String,String> selectAllRentalRecord() {
		final String dbName="ApartmentDB";
		final String table_name="Rental_records";
		String query="select * from Rental_records";
		Map<String, String> map=new HashMap<String,String>();;
		try {
			Connection con=ConnectTest.getConnection(dbName);
			Statement stmt=con.createStatement();
			ResultSet rset=stmt.executeQuery(query);
			if(rset.next()) {
				ResultSetMetaData rsmd=rset.getMetaData();
				int columnCount=rsmd.getColumnCount();
				for(int i=1;i<=columnCount;i++) {
					map.put(rsmd.getColumnLabel(columnCount), rset.getObject(columnCount).toString());
					System.out.println(rsmd.getColumnLabel(columnCount));
					
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	public Map<String,String> selectAllRentalProperties() {
		final String dbName="ApartmentDB";
		final String table_name="Rental_Propertiess";
		String query="select * from Rental_Propertiess";
		Map<String, String> map=new HashMap<String,String>();
		ResultSetMetaData rsMetaData=null;
		try {
			Connection con=ConnectTest.getConnection(dbName);
			Statement stmt=con.createStatement();
			ResultSet rset=stmt.executeQuery(query);
			rsMetaData=rset.getMetaData();
			int columns=rsMetaData.getColumnCount();
	
			int j=1;
			   while(rset.next())
			   {

				  for(int i=1;i<=columns;i++)
				  {	  
			    	String a= rsMetaData.getColumnName(i)+"%"+j;
			    	map.put(a, rset.getString(i));;
				  }
				  j++;

			   }
			  
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
