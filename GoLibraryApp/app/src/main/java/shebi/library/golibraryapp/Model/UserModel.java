package shebi.library.golibraryapp.Model;

public class UserModel {
    String phone,adress,fname,lname;
    String type;

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
    public UserModel() {
    }

    public UserModel(String phone, String adress,String fname,String lname,String type) {
        this.phone = phone;
        this.adress = adress;
        this.fname=fname;
        this.lname=lname;
        this.type=type;
    }
}

