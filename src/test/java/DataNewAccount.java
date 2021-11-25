import java.util.Random;

public class DataNewAccount {
    private String taxID;
    private String firstName;
    private String address1;
    private String postcode;
    private String company;
    private String lastName;
    private String address2;
    private String city ;
    private String phone;
    private String desiredPassword;


    Random r = new Random();
    private int randomNamber = r.nextInt(Integer.MAX_VALUE);

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email = randomNamber + "@mail.ru";

    public DataNewAccount(String taxID, String firstName, String address1,
                          String postcode, String company, String lastName,
                          String address2, String city, String phone,
                          String desiredPassword) {
        this.taxID = taxID;
        this.firstName = firstName;
        this.address1 = address1;
        this.postcode = postcode;
        this.company = company;
        this.lastName = lastName;
        this.address2 = address2;
        this.city = city;
        this.phone = phone;
        this.desiredPassword = desiredPassword;

    }
    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDesiredPassword() {
        return desiredPassword;
    }

    public void setDesiredPassword(String desiredPassword) {
        this.desiredPassword = desiredPassword;
    }

}
