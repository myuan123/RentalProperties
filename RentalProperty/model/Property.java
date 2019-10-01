package model;
import java.util.HashMap;
import java.util.Map;

import controller.RentExceptions;
import controller.RentalRecordOperation;
import utilites.DateTime;

/**
 * Created by ZhiweiXu on 2018/8/23.
 */

public abstract class Property {
	/**
	* Unique identifier for each rental property (string type), if the house is a apartment (Apartment) id to start with A_, if the house type is a superior suite (Premium
	* Suite) id starts with S_
	*/
	String propertyId;
	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public float getRentalFeeOfDay() {
		return rentalFeeOfDay;
	}

	public void setRentalFeeOfDay(float rentalFeeOfDay) {
		this.rentalFeeOfDay = rentalFeeOfDay;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastCustomerId() {
		return lastCustomerId;
	}

	public void setLastCustomerId(String lastCustomerId) {
		this.lastCustomerId = lastCustomerId;
	}

	

	public DateTime getLastMaintenanceDate() {
		return lastMaintenanceDate;
	}

	public void setLastMaintenanceDate(DateTime lastMaintenanceDate) {
		this.lastMaintenanceDate = lastMaintenanceDate;
	}

	public int getNumOfBedRoom() {
		return numOfBedRoom;
	}

	public String streetNumber;
	public String streetName;
	public String suburb;
	/**
	 * Apartment bedroomsCount<4  Premium Suite bedroomsCount=3
	 */
	public int numOfBedRoom;
	/**
	* (Apartment) </p>One bedroom (1-bedroom) 143 one day</p> Two bedrooms (2-bedrooms) 210 one day
	* </p>Three bedrooms (3-bedroom) 319 days </p> (Premium Suite)</p> 554 days
	*/

	public float rentalFeeOfDay;
	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String propertyType;
	public String status = "Available";
	public String lastCustomerId;
	/** order */
	public RentalRecord[] rentalRecords = new RentalRecord[10];
	/**
	* (Premium Suite) must have a 10-day maintenance interval
	* */
	public DateTime lastMaintenanceDate;
	
	public void setNumOfBedRoom(int numOfBedRoom) {
	}

	/**
	* This method is called on a rental property object (apartment or premium suite) to perform the operations required to rent the property. </p>
	* This method should check the prerequisites to determine if the property can be rented. For example, when the property is currently being rented or being maintained
	* , this method will return false. You should check for any other possible conditions,</p> these conditions will cause this method to return false. </p>
	* If the property is available for rent, this method performs all necessary actions to update the information stored in the property object based on the input parameters. For example, update attribute status, create new lease records,</p>
	* Update the array of lease records and any other actions you deem necessary. Finally, if the property can be successfully rented, the method will return true. </p>
	*
	* @param customerId
	* Tenant id
	* @param rentDate
	* Rental date
	* @param numOfRentDay
	* Renting days
	*/

	public int rent(String customerId, DateTime rentDate, int numOfRentDay) throws RentExceptions {

		
		System.out.println("enter2");
		RentalRecord rentalRecord = new RentalRecord();
		rentalRecord.recordId = propertyId + "_" + customerId + "_"
				+ rentDate.getEightDigitDate();
		rentalRecord.rentDate = rentDate;
		rentalRecord.numOfRentDay = numOfRentDay;
		rentalRecord.estimeatedReturnDate = new DateTime(rentDate, numOfRentDay);
		status = "Rented";
//		addRentalRecord(rentalRecord);
		lastCustomerId = customerId;
		if (!status.equals(null)) {
			throw new RentExceptions("this proeprty can not rent");
		}
		return 1;
	}

	/** get minimum rent day */
	public int getMinRentDays(DateTime rentDate) {
		if (propertyType.equals("Apartment")) {
			if (rentDate.getInWeek() == 5 || rentDate.getInWeek() == 6) {
				return 3;
			} else
				return 2;
		} else
			return 1;
	}

	/** add rent record */
	public void addRentalRecord(RentalRecord rentalRecord) {
		for(int i=8;i>=0;i--)
			rentalRecords[i+1] = rentalRecords[i];
		System.out.println("enter");
		RentalRecordOperation rro=new RentalRecordOperation();
		Map<String, String> map=new HashMap<>();
		map.put("Record_id", rentalRecord.recordId);
		map.put("Rent_data", rentalRecord.rentDate.toString());
		map.put("Estimated_return_date", rentalRecord.estimeatedReturnDate.toString());
		map.put("Actual_return_date", rentalRecord.actualReturnDate.toString());
		map.put("Rental_fee", rentalRecord.rentalFee);
		map.put("Late_fee", rentalRecord.lateFee);
		rro.insertRentalRecords(map);
		rentalRecords[0] = rentalRecord;
	}

	/**
	* This method is called on a rental property object (apartment or premium suite) to perform the operations required to return to this property. </p>
	* This method should check the prerequisites to determine if the property can be returned. For example, when a given returnDate precedes the rentDate stored in the lease record
	* , the method will return false</p> . You should check for any other possible conditions that will cause this method to return false. </p>
	* If the property can be returned, this method performs all necessary actions to update the information stored in this property object based on the input parameters. For example, using rent,</p>
	* Late fees and any other operations that you deem necessary to update the property status and update the corresponding rent record. Finally, this method will return true if the property can be successfully returned.
	*
	* @param returnDate
	* Return date actualReturnDate
	*/

	public boolean returnProperty(DateTime returnDate) {
		RentalRecord rentalRecord = rentalRecords[0];
		int rentDays = DateTime.diffDays(returnDate, rentalRecord.rentDate);
		if (rentDays < getMinRentDays(rentalRecord.rentDate))
			return false;
		rentalRecord.actualReturnDate = returnDate.toString();
		if (rentDays <= rentalRecord.numOfRentDay) {
			rentalRecord.rentalFee = String.format("%.2f", rentDays
					* rentalFeeOfDay);
			rentalRecord.lateFee = "0.00";
		} else {
			rentalRecord.rentalFee = String.format("%.2f",
					rentalRecord.numOfRentDay * rentalFeeOfDay);
			rentalRecord.lateFee = String
					.format("%.2f",
							getLateFee(rentDays - rentalRecord.numOfRentDay));
		}
		status = "Available";
		return true;
	}
	
	
	
	public abstract float getLateFee(int lateDays);

	/**
	* This method is called on a rental property object (apartment or premium suite) to perform the operations required to maintain the property</p>
	* This method should check the prerequisites to determine if maintenance operations can be performed in the property. E.g,
	* This method will return false when the property is currently being rented. You should check for any other possible conditions,</p> these conditions will cause this method to return false. </p>
	* If the property is ready for maintenance, this method performs all necessary actions to update the information stored in this property object when maintenance occurs. Finally, if the property is under maintenance,</p>
	* This method will return true.
	*/

	public boolean performMaintenance() {
		if (status.equals("Rented"))
			return false;
		status = "Maintenance";
		return true;
	}

	/**
	* This method is called on a rental property object (apartment or premium suite) to perform the operations required to maintain the property. </p>
	* This method should check the prerequisites. For example, when the property is currently rented, it makes no sense to call the completemainmaintain method on this property object,</p>
	* So this method should return false. You should check for any other possible conditions that will cause this method to return false. </p>
	* If maintenance can be completed, this method performs all necessary actions to update the information stored in this property object after maintenance is complete. Finally, this method will return true,</p>
	* to indicate that the maintenance of this property has been completed
	*/
	public boolean completeMaintenance(DateTime completionDate) {
		if (!status.equals("Maintenance"))
			return false;
		lastMaintenanceDate = completionDate;
		status = "Available";
		return true;
	}

	/**
	* This method should construct a string and return it to the calling method. The returned string should be formatted in a predefined format as follows:</p>
	* propertyId:streetNumber:streetName:suburb:propertyType:numOfBedRoom:</p>
	* status (Note how the colon is used as a separator. If the property is a premium suite, append the property lastMaintenanceDate.
	*/

	public String toString() {
		return propertyId
				+ ":"
				+ getStreetNumber()
				+ ":"
				+ streetName
				+ ":"
				+ suburb
				+ ":"
				+ propertyType
				+ ":"
				+ numOfBedRoom
				+ ":"
				+ status
				+ (propertyType.equals("Premium Suite") ? ":"
						+ lastMaintenanceDate : "");
	}

	/**
	* This method should construct a string and return it to the calling method. This method should not be actually printed. The returned string contains all the information about the leased property,</p>
	* Includes detailed information on the last 10 rental records for the property. 
	*/
	public String getDetails() {
		StringBuilder values = new StringBuilder();
		values.append("Property ID: ").append(propertyId).append("\nAddress: ")
				.append(getStreetNumber() + " " + streetName).append("\nType: ")
				.append(propertyType).append("\nBedroom: ")
				.append(numOfBedRoom).append("\nStatus: ").append(status)
				.append("\nRENTAL RECORD: ");
		if (rentalRecords[0] == null) {
			values.append("empty");
			values.append("\n--------------------------------------");
		} else
			for (RentalRecord rentalRecord : rentalRecords) {
				if (rentalRecord == null)
					break;
				values.append("\nRecord ID: ").append(rentalRecord.recordId)
						.append("\nRent Date: ").append(rentalRecord.rentDate)
						.append("\nEstimated Return Date: ")
						.append(rentalRecord.estimeatedReturnDate);
				if (!rentalRecord.actualReturnDate.equals("none")) {
					values.append("\nActual Return Date: ")
							.append(rentalRecord.actualReturnDate)
							.append("\nRental Fee: ")
							.append(rentalRecord.rentalFee)
							.append("\nLate Fee: ")
							.append(rentalRecord.lateFee);
				}
				values.append("\n--------------------------------------");
			}
		
		
		return values.toString();
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

}
