package com.utsa.AddItemsService.dto;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;


@MappedSuperclass
public abstract class User  implements Serializable {

    private String name;

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private String location;
    private String password;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getName(), user.getName()) && Objects.equals(getLocation(), user.getLocation()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLocation(), getPassword());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

