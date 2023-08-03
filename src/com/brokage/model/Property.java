package com.brokage.model;

/**
 * <p>Model class</p>
 * @author Ajay Shrestha, Gaurab Pokharel, Nirajan Karki, Sakar Thapa
 * @since 7/28/2023
 **/
public class Property {
    private int propertyId;
    private String agentName;
    private String askingPrice;
    private String region;
    private String propertyType;
    private String closingDate;

    //getter setter
    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(String askingPrice) {
        this.askingPrice = askingPrice;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    //Creating the constructor without the fields
    public Property() {
        super();
    }

    //Creating the constructor using the feilds
    public Property(int propertyId, String agentName, String askingPrice, String region, String propertyType,
			String closingDate) {
		super();
		this.propertyId = propertyId;
		this.agentName = agentName;
		this.askingPrice = askingPrice;
		this.region = region;
		this.propertyType = propertyType;
		this.closingDate = closingDate;
	}
    
    

	public Property(String agentName, String askingPrice, String region, String propertyType, String closingDate) {
		super();
		this.agentName = agentName;
		this.askingPrice = askingPrice;
		this.region = region;
		this.propertyType = propertyType;
		this.closingDate = closingDate;
	}

	@Override
	public String toString() {
		return "Property [propertyId=" + propertyId + ", agentName=" + agentName + ", askingPrice=" + askingPrice
				+ ", region=" + region + ", propertyType=" + propertyType + ", closingDate=" + closingDate + "]";
	}

	//toString method
    
}
