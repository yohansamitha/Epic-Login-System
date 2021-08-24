package model;

public class User {
    private int id;
    private String name;
    private String address;
    private String contact;
    private String emailAddress;
    private String password;

    public User() {
    }

    public User(int id, String name, String address, String contact, String emailAddress, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public User(String name, String address, String contact, String emailAddress, String password) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
