package com.dijkstra.pojo;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class City {

@Override
	public String toString() {
		return "City [code=" + code + ", cityName=" + cityName + ", countryName=" + countryName + "]";
	}

@SerializedName("id_data")
@Expose
private Integer idData;
@SerializedName("code")
@Expose
private String code;
@SerializedName("city_name")
@Expose
private String cityName;
@SerializedName("country_name")
@Expose
private String countryName;
@SerializedName("synonyms")
@Expose
private String synonyms;
@SerializedName("description")
@Expose
private String description;
@SerializedName("airport_data")
@Expose
private String airportData;
@SerializedName("city_type")
@Expose
private String cityType;
@SerializedName("mapping_type")
@Expose
private String mappingType;
@SerializedName("activeState")
@Expose
private Boolean activeState;
@SerializedName("timestamp")
@Expose
private String timestamp;

/**
* 
* @return
* The idData
*/
public Integer getIdData() {
return idData;
}

/**
* 
* @param idData
* The id_data
*/
public void setIdData(Integer idData) {
this.idData = idData;
}

/**
* 
* @return
* The code
*/
public String getCode() {
return code;
}

/**
* 
* @param code
* The code
*/
public void setCode(String code) {
this.code = code;
}

/**
* 
* @return
* The cityName
*/
public String getCityName() {
return cityName;
}

/**
* 
* @param cityName
* The city_name
*/
public void setCityName(String cityName) {
this.cityName = cityName;
}

/**
* 
* @return
* The countryName
*/
public String getCountryName() {
return countryName;
}

/**
* 
* @param countryName
* The country_name
*/
public void setCountryName(String countryName) {
this.countryName = countryName;
}

/**
* 
* @return
* The synonyms
*/
public String getSynonyms() {
return synonyms;
}

/**
* 
* @param synonyms
* The synonyms
*/
public void setSynonyms(String synonyms) {
this.synonyms = synonyms;
}

/**
* 
* @return
* The description
*/
public String getDescription() {
return description;
}

/**
* 
* @param description
* The description
*/
public void setDescription(String description) {
this.description = description;
}

/**
* 
* @return
* The airportData
*/
public String getAirportData() {
return airportData;
}

/**
* 
* @param airportData
* The airport_data
*/
public void setAirportData(String airportData) {
this.airportData = airportData;
}

/**
* 
* @return
* The cityType
*/
public String getCityType() {
return cityType;
}

/**
* 
* @param cityType
* The city_type
*/
public void setCityType(String cityType) {
this.cityType = cityType;
}

/**
* 
* @return
* The mappingType
*/
public String getMappingType() {
return mappingType;
}

/**
* 
* @param mappingType
* The mapping_type
*/
public void setMappingType(String mappingType) {
this.mappingType = mappingType;
}

/**
* 
* @return
* The activeState
*/
public Boolean getActiveState() {
return activeState;
}

/**
* 
* @param activeState
* The activeState
*/
public void setActiveState(Boolean activeState) {
this.activeState = activeState;
}

/**
* 
* @return
* The timestamp
*/
public String getTimestamp() {
return timestamp;
}

/**
* 
* @param timestamp
* The timestamp
*/
public void setTimestamp(String timestamp) {
this.timestamp = timestamp;
}

}