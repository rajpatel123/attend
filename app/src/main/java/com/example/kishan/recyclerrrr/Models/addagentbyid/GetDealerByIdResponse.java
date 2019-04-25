package com.example.kishan.recyclerrrr.Models.addagentbyid;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDealerByIdResponse {

@SerializedName("agentId")
@Expose
private Integer agentId;
@SerializedName("dealerId")
@Expose
private Integer dealerId;
@SerializedName("firstName")
@Expose
private String firstName;
@SerializedName("lastName")
@Expose
private String lastName;
@SerializedName("email")
@Expose
private String email;
@SerializedName("phone")
@Expose
private String phone;
@SerializedName("message")
@Expose
private String message;
@SerializedName("registerDate")
@Expose
private Integer registerDate;
@SerializedName("latitude")
@Expose
private String latitude;
@SerializedName("longitude")
@Expose
private String longitude;
@SerializedName("agentFirstName")
@Expose
private String agentFirstName;
@SerializedName("agentLastName")
@Expose
private String agentLastName;
@SerializedName("distance")
@Expose
private String distance;

public Integer getAgentId() {
return agentId;
}

public void setAgentId(Integer agentId) {
this.agentId = agentId;
}

public Integer getDealerId() {
return dealerId;
}

public void setDealerId(Integer dealerId) {
this.dealerId = dealerId;
}

public String getFirstName() {
return firstName;
}

public void setFirstName(String firstName) {
this.firstName = firstName;
}

public String getLastName() {
return lastName;
}

public void setLastName(String lastName) {
this.lastName = lastName;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getPhone() {
return phone;
}

public void setPhone(String phone) {
this.phone = phone;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public Integer getRegisterDate() {
return registerDate;
}

public void setRegisterDate(Integer registerDate) {
this.registerDate = registerDate;
}

public String getLatitude() {
return latitude;
}

public void setLatitude(String latitude) {
this.latitude = latitude;
}

public String getLongitude() {
return longitude;
}

public void setLongitude(String longitude) {
this.longitude = longitude;
}

public String getAgentFirstName() {
return agentFirstName;
}

public void setAgentFirstName(String agentFirstName) {
this.agentFirstName = agentFirstName;
}

public String getAgentLastName() {
return agentLastName;
}

public void setAgentLastName(String agentLastName) {
this.agentLastName = agentLastName;
}

public String getDistance() {
return distance;
}

public void setDistance(String distance) {
this.distance = distance;
}

}