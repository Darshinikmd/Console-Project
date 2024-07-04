package Resources;

import java.util.Date;

public class rescue_systemDTO {
    private int R_id;
    private String R_name;
    private int R_age;
    private String R_gender;
    private String Health_status;
    private Date Joining_Date;
    private int User_id;
    private int Register_num;
    private int location_id;

    public rescue_systemDTO(int R_id, String R_name, int R_age, String R_gender, String Health_status,
            Date Joining_Date, int User_id, int Register_num, int location_id) {
        this.R_id = R_id;
        this.R_name = R_name;
        this.R_age = R_age;
        this.R_gender = R_gender;
        this.Health_status = Health_status;
        this.Joining_Date = Joining_Date;
        this.User_id = User_id;
        this.Register_num = Register_num;
        this.location_id = location_id;
    }

    public rescue_systemDTO() {
        // TODO Auto-generated constructor stub
    }

    public int getR_id() {
        return this.R_id;
    }

    public void setR_id(int R_id) {
        this.R_id = R_id;
    }

    public String getR_name() {
        return this.R_name;
    }

    public void setR_name(String R_name) {
        this.R_name = R_name;
    }

    public int getR_age() {
        return this.R_age;
    }

    public void setR_age(int R_age) {
        this.R_age = R_age;
    }

    public String getR_gender() {
        return this.R_gender;
    }

    public void setR_gender(String R_gender) {
        this.R_gender = R_gender;
    }

    public String getHealth_status() {
        return this.Health_status;
    }

    public void setHealth_status(String Health_status) {
        this.Health_status = Health_status;
    }

    public Date getJoining_Date() {
        return this.Joining_Date;
    }

    public void setJoining_Date(Date Joining_Date) {
        this.Joining_Date = Joining_Date;
    }

    public int getUser_id() {
        return this.User_id;
    }

    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }

    public int getRegister_num() {
        return this.Register_num;
    }

    public void setRegister_num(int Register_num) {
        this.Register_num = Register_num;
    }

    public int getLocation_id() {
        return this.location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

}
