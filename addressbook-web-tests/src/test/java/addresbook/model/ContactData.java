package addresbook.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.security.acl.Group;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "middlename")
    private String middlename;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "company")
    private String company;

    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;

    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;


    @Column(name = "email")
    @Type(type = "text")
    private String email;


    @Column(name = "email2")
    @Type(type = "text")
    private String email2;


    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Transient
    private String allPhones;

    @Transient
    private String allEmails;

    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    public Groups getGroups() {
        return new Groups(groups);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();


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
        return new File(photo);
    }

    public ContactData setPhoto(File photo) {
        this.photo = photo.getPath();
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

    public String getEmail2() {
        return email2;
    }

    public ContactData setEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactData setEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData setAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData contactData = (ContactData) o;
        return id == contactData.id &&
                Objects.equals(firstname, contactData.firstname);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, firstname);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
