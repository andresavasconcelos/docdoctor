package br.com.docdoctor.dto;

import br.com.docdoctor.enums.UserTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class UserRequestDTO
{

    @JsonProperty("name")
    private String fullName;

    private String email;

    private String phone;

    private Date birthDate;

    private UserTypeEnum userType;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }
}
