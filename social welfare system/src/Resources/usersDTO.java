package Resources;


public class usersDTO {
    
    private int User_id;
    private String User_name;
    private String Contact_no;
    private String Email;
    private String Location;
    private String Role;
    
    private int Register_num;
    public usersDTO(){

    }

    public usersDTO(int User_id, String User_name, String Contact_no, String Email, String Location, String Role) {
        this.User_id = User_id;
        this.User_name = User_name;
        this.Contact_no = Contact_no;
        this.Email = Email;
        this.Location = Location;
        this.Role = Role;
        this.Register_num = Register_num;
    }

    public int getUser_id() {
        return this.User_id;
    }

    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }

    public String getUser_name() {
        return this.User_name;
    }

    public void setUser_name(String User_name) {
        this.User_name = User_name;
    }

    public String getContact_no() {
        return this.Contact_no;
    }

    public void setContact_no(String Contact_no)
    {
       this.Contact_no=Contact_no;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getLocation() {
        return this.Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getRole() {
        return this.Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public int getRegister_num() {
        return this.Register_num;
    }

    public void setRegister_num(int Register_num) {
        this.Register_num = Register_num;
    }

    public String getPassword() {
       
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }

   
}
