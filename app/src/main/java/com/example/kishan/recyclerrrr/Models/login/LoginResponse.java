package com.example.kishan.recyclerrrr.Models.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("companyId")
    @Expose
    private String companyId;
    @SerializedName("companyLoginName")
    @Expose
    private String companyLoginName;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("userFirstName")
    @Expose
    private String userFirstName;
    @SerializedName("userLastName")
    @Expose
    private String userLastName;
    @SerializedName("userRole")
    @Expose
    private String userRole;
    @SerializedName("userType")
    @Expose
    private String userType;
    @SerializedName("profilePic")
    @Expose
    private String profilePic;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("isActive")
    @Expose
    private String isActive;
    @SerializedName("deactivationDate")
    @Expose
    private String deactivationDate;
    @SerializedName("loginFlag")
    @Expose
    private String loginFlag;
    @SerializedName("agentId")
    @Expose
    private String agentId;
    @SerializedName("registrationType")
    @Expose
    private Object registrationType;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("userName")
    @Expose
    private Object userName;
    @SerializedName("companyType")
    @Expose
    private Object companyType;
    @SerializedName("punchStatus")
    @Expose
    private String punchStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyLoginName() {
        return companyLoginName;
    }

    public void setCompanyLoginName(String companyLoginName) {
        this.companyLoginName = companyLoginName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getDeactivationDate() {
        return deactivationDate;
    }

    public void setDeactivationDate(String deactivationDate) {
        this.deactivationDate = deactivationDate;
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public Object getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(Object registrationType) {
        this.registrationType = registrationType;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public Object getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Object companyType) {
        this.companyType = companyType;
    }

    public String getPunchStatus() {
        return punchStatus;
    }

    public void setPunchStatus(String punchStatus) {
        this.punchStatus = punchStatus;
    }

}