package com.example.kishan.recyclerrrr.Models.registration;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

@SerializedName("result")
@Expose
private String result;

public String getResult() {
return result;
}

public void setResult(String result) {
this.result = result;
}

}