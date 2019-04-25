package com.example.kishan.recyclerrrr.modelClass.addDealer;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddDealerRequest {

@SerializedName("agentId")
@Expose
private Integer agentId;
@SerializedName("email")
@Expose
private String email;
@SerializedName("firstName")
@Expose
private String firstName;
@SerializedName("lastName")
@Expose
private String lastName;
@SerializedName("latitude")
@Expose
private Double latitude;
@SerializedName("longitude")
@Expose
private Double longitude;
@SerializedName("message")
@Expose
private String message;

public Integer getAgentId() {
return agentId;
}

public void setAgentId(Integer agentId) {
this.agentId = agentId;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
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

public Number getLatitude() {
return latitude;
}

public void setLatitude(Double latitude) {
this.latitude = latitude;
}

public Number getLongitude() {
return longitude;
}

public void setLongitude(Double longitude) {
this.longitude = longitude;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

}