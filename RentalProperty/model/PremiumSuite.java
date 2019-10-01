package model;
import controller.RentExceptions;
import utilites.DateTime;

/**
 * Created by ZhiweiXu on 2018/8/23.
 */


public class PremiumSuite extends Property {

	public void setPropertyId(String propertyId) {
		propertyType = "Premium Suite";
		    this.propertyId = propertyId;
			this.numOfBedRoom = 3;
			this.rentalFeeOfDay = 554;
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
		DateTime nextMaintenanceDate = new DateTime(lastMaintenanceDate, 10);
			int MaintenanceDate = DateTime.diffDays(nextMaintenanceDate,
					rentDate);
			if (MaintenanceDate >= 0 && MaintenanceDate <= numOfRentDay)
			{
				throw new RentExceptions("first must be maintenance");
			}
			return 1;
	
	}

	public float getLateFee(int lateDays){
		return rentalFeeOfDay = 662*lateDays;
	}

	

	/**
	* This method was called on a rental property object (apartment or premium suite) to perform the operations required to maintain the property. </p>
	* This method should check the prerequisites. For example, when the property is currently rented, it makes no sense to call the completemainmaintain method on this property object,</p>
	* So this method should return false. You should check for any other possible conditions that will cause this method to return false. </p>
	* If maintenance can be completed, this method performs all necessary actions to update the information stored in this property object after maintenance is complete. Finally, this method will return true,</p>
	* to indicate that the maintenance of this property has been completed
	*/
	public boolean completeMaintenance(DateTime completionDate) {
		
		if (DateTime.diffDays(completionDate, new DateTime(
						lastMaintenanceDate, 10)) > 0)
			return false;
		return super.completeMaintenance(completionDate);
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
				+ ":"
				+ lastMaintenanceDate;
	}

}
