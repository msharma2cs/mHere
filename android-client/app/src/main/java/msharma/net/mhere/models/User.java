package msharma.net.mhere.models;

import java.util.Objects;

public class User {

    private Integer id;

    private String firstName;

    private String lastName;

    private String fullName;

    private String login;

    private String email;

    private String token;

    public User() {}

    public User(Integer id, String firstName, String lastName, String fullName, String login, String email, String token) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.login = login;
        this.email = email;
        this.token = token;
    }

    public User(Integer id, String firstName, String lastName, String login, String email, String token) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.login = login;
        this.email = email;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName() {
        this.fullName = this.firstName + " " + this.lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(fullName, user.fullName) &&
                Objects.equals(login, user.login) &&
                Objects.equals(email, user.email) &&
                Objects.equals(token, user.token);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, fullName, login, email, token);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

}