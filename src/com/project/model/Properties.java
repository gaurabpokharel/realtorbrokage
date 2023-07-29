package com.project.model;

/**
 * @author
 * @project RealtorBrokage
 * @since 7/28/2023
 **/
public class Properties {
    private int propertyId;
    private String agentName;
    private String askingPrice;
    private String region;
    private String propertiesType;
    private String closingDate;

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

    public String getPropertiesType() {
        return propertiesType;
    }

    public void setPropertiesType(String propertiesType) {
        this.propertiesType = propertiesType;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public Properties( String agentName, String askingPrice, String region, String propertiesType, String closingDate) {
        this.agentName = agentName;
        this.askingPrice = askingPrice;
        this.region = region;
        this.propertiesType = propertiesType;
        this.closingDate = closingDate;
    }

    public Properties() {
        super();
    }

    @Override
    public String toString() {
        return "Properties{" +
                "propertyId=" + propertyId +
                ", agentName='" + agentName + '\'' +
                ", askingPrice='" + askingPrice + '\'' +
                ", region='" + region + '\'' +
                ", propertiesType='" + propertiesType + '\'' +
                ", closingDate='" + closingDate + '\'' +
                '}';
    }
}
