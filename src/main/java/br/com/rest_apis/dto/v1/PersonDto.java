package br.com.rest_apis.dto.v1;

import java.io.Serializable;
import java.util.Objects;


public class PersonDto implements Serializable {

    private static final long serialVersion = 1L;
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    public PersonDto() {
        super();
    }

    public PersonDto(Long id, String firstName, String lastName, String address, String gender) {
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
        if (!(o instanceof PersonDto)) return false;
        PersonDto person = (PersonDto) o;
        return getId().equals(person.getId()) && getFirstName().equals(person.getFirstName())
                && getLastName().equals(person.getLastName()) && getAddress().equals(person.getAddress())
                && getGender().equals(person.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getAddress(), getGender());
    }
}
