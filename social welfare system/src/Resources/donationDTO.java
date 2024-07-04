package Resources;
import java.util.Date;
public class donationDTO {
    private int Item_id;
    private String Item_name;
    private int Quantity;
    private Date Date;
    private int User_id;

    public donationDTO(int Item_id,String Item_name, int Quantity,Date Date,int User_id) {
        this.Item_id = Item_id;
        this.Item_name = Item_name;
        this.Quantity = Quantity;
        this.Date = Date;
        this.User_id = User_id;
    }

    public int getItem_id() {
        return this.Item_id;
    }

    public void setItem_id(int Item_id) {
        this.Item_id = Item_id;
    }

    public String getItem_name() {
        return this.Item_name;
    }

    public void setItem_name(String Item_name) {
        this.Item_name = Item_name;
    }

    public int getQuantity() {
        return this.Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public Date getDate() {
        return this.Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public int getUser_id() {
        return this.User_id;
    }

    public void setUser_id(int User_id) 
    {
        this.User_id = User_id;
    }
    
}
