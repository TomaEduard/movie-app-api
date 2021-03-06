package org.fasttrackit.movieappapi.transfer.customer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateCustomerRequest {

    @NotNull
    @Size(min = 1, max = 20)
    private String firstName;
    @NotNull
    @Size (min = 1, max = 20)
    private String lastName;
    private String address;

    private String email;
    private String phone;

    private String username;
    private String password;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //    WARNING - do not serialize password!
    @Override
    public String toString() {
        return "CreateCustomerRequest{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='********'" +
                '}';
    }
}

