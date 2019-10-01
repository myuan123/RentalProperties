package model;
import utilites.DateTime;

/**
 * Created by ZhiweiXu on 2018/8/23.
 */
public class RentalRecord {
	
	String recordId;
	DateTime rentDate;
	DateTime estimeatedReturnDate;
	String actualReturnDate = "none";
	String rentalFee = "none";
	String lateFee = "none";
int numOfRentDay;
    String getDetails() {
		return "Record ID:\t"
				+ recordId
				+ "\nRent Date:\t"
				+ rentDate.toString()
				+ "\nEstimeated Return Date:\t"
				+ estimeatedReturnDate.toString()
				+ (actualReturnDate.equals("none") ? "\nActual Return Date:\t"
						+ actualReturnDate + "\nRental Fee:\t" + rentalFee
						+ "\nLate Fee:\t" + lateFee : "");
	}

	/** recordId:rentDate:estimatedReturnDate:actualReturnDate:rentalFee:lateFee */
	public String toString() {
		return recordId + ":" + rentDate.toString() + ":"
				+ estimeatedReturnDate.toString() + ":" + actualReturnDate
				+ ":" + rentalFee + ":" + lateFee;
	}

}
