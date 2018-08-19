package addresbook;

public class ContactData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String company;
    private final String homePhone;
    private final String mobilePhone;
    private final String workPhone;
    private final String email;

    public ContactData(String firstname, String middlename, String lastname, String company, String homePhone, String mobilePhone, String workPhone, String email) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.company = company;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.email = email;
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
}
