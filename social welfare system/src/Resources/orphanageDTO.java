package Resources;

public class orphanageDTO {
    private static int Register_num;
    private static int Total_Strength;
    private static  int Head_id;
    private static int location_id;
    private static String Orphanage_name;
    public orphanageDTO(){}

    public  orphanageDTO(int Register_num, int Total_Strength, int location_id,String Orphanage_name) {
        this.Register_num = Register_num;
        this.Total_Strength = Total_Strength;
        
        this.location_id = location_id;
        this.Orphanage_name = Orphanage_name;

    }

    public orphanageDTO(String orphanageName, String location) {
        
    }

    public static int getRegister_num() {
        return Register_num;
    }

    public void setRegister_num(int Register_num) {
        this.Register_num = Register_num;
    }

    public static int getTotal_Strength() {
        return Total_Strength;
    }

    public void setTotal_Strength(int Total_Strength) {
        this.Total_Strength = Total_Strength;
    }

    

   

    public static int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }
    public static String getOrphanage_name()
    {
        return Orphanage_name;
    }
    public  void setOrphanage_name(String Orphanage_name)
    {
        this.Orphanage_name=Orphanage_name;

    }

    public int getUser_id() {
       
        throw new UnsupportedOperationException("Unimplemented method 'getUser_id'");
    }
    
}
