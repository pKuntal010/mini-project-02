package com.prac.dto;

public class RegistrationDTO {

	private String userName;
	private String userEmail;
	private String pwd;
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;
	private String pwdUpdated;
	
	public String getPwdUpdated() {
		return pwdUpdated;
	}
	public void setPwdUpdated(String pwdUpdated) {
		this.pwdUpdated = pwdUpdated;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	@Override
	public String toString() {
		return "RegistrationDTO [userName=" + userName + ", userEmail=" + userEmail + ", pwd=" + pwd + ", countryId="
				+ countryId + ", stateId=" + stateId + ", cityId=" + cityId + ", pwdUpdated=" + pwdUpdated + "]";
	}
	
	
}
