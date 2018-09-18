package addresbook.model;

import java.io.File;
import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String firstname;
    private String middlename;
    private String lastname;
    private String company;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String email;
    private String address;
    private String allPhones;
    private File photo;

    /*public ContactData(int id, String firstname, String middlename, String lastname, String company, String homePhone, String mobilePhone, String workPhone, String email) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.company = company;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;

        this.workPhone = workPhone;
        this.email = email;
    }

    public ContactData(String firstname, String middlename, String lastname, String company, String homePhone, String mobilePhone, String workPhone, String email) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.company = company;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.email = email;
    }*/

    public ContactData setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData setMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData setCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData setHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstname);
    }

    public ContactData setId(int id) {
        this.id = id;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCompany() {
        return company;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public File getPhoto() {
        return photo;
    }

    public ContactData setPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ContactData setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData setAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
