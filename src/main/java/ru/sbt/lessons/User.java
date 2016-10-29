package ru.sbt.lessons;

/**
 * Created by axelk on 22.10.2016.
 */
public class User {
    private Long id;
    private String login;
    private String passwordMD5;

    public User() {
    }

    public User(Long id, String login, String passwordMD5) {
        this.id = id;
        this.login = login;
        this.passwordMD5 = passwordMD5;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordMD5() {
        return passwordMD5;
    }

    public void setPasswordMD5(String passwordMD5) {
        this.passwordMD5 = passwordMD5;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", passwordMd5='" + passwordMD5 + '\'' +
                '}';
    }
}
