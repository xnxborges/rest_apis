package br.com.rest_apis.dto.v2;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class PersonDtoV2 implements Serializable {


    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private String address;
    private String gender;

    public PersonDtoV2() {
        super();
    }

    public PersonDtoV2(Long id, String firstName, String lastName, String address, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonDtoV2)) return false;
        PersonDtoV2 that = (PersonDtoV2) o;
        return getId().equals(that.getId()) && getFirstName().equals(that.getFirstName())
                && getLastName().equals(that.getLastName()) && getBirthDay().equals(that.getBirthDay())
                && getAddress().equals(that.getAddress()) && getGender().equals(that.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getBirthDay(), getAddress(), getGender());
    }
}
