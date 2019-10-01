package model;
import controller.RentExceptions;
import utilites.DateTime;

/**
 * Created by ZhiweiXu on 2018/8/23.
 */

public class Apartment extends Property {
	
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
		propertyType = "Apartment";
	}

	/**
	 * ֻjust allowed Apartment calls
	 * */
	public void setNumOfBedRoom(int numOfBedRoom) {
		this.numOfBedRoom = numOfBedRoom;
		switch (numOfBedRoom) {
		case 1:
			this.rentalFeeOfDay = 143;
			break;
		case 2:
			this.rentalFeeOfDay = 210;
			break;
		case 3:
			this.rentalFeeOfDay = 319;
			break;
			default:
				break;
		}
	}

	/**
	 * This method is called on a rental property object (apartment or premium suite) to perform the operations required to rent the property.</p>
	 * This method should check the prerequisites to determine if the property can be rented. For example, when the property is currently being rented or being maintained
	 *  , this method will return false. You should check for any other possible conditions,</p> these conditions will cause this method to return false.</p>
	 * If the property is available for rent, this method performs all necessary actions to update the information stored in the property object based on the input parameters. For example, update attribute status, create new lease records,</p>
	 * Update the array of lease records and any other actions you deem necessary. Finally, if the property can be successfully rented, the method will return true.</p>
	 * 
	 * @param customerId
	 *            customer id
	 * @param rentDate
	 *            rent date
	 * @param numOfRentDay
	 *            rent days
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
		addRentalRecord(rentalRecord);
		lastCustomerId = customerId;
		return 1;

	}
	
	public float getLateFee(int lateDays){
		return (float) (rentalFeeOfDay * 115*lateDays)/100;
	}
	

	/**
	 * This method should construct a string and return it to the calling method. The returned string should be formatted in a predefined format as follows:</p>
	 * propertyId:streetNumber:streetName:suburb:propertyType:numOfBedRoom:</p>
	 * status ((Note how the colon is used as a separator. If the property is a premium suite, append the property lastMaintenanceDate.
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
				+ status;
	}
}
