package Resources;

public class locationDTO {
    private int location_id;
    private String location;
    private String location_code;

locationDTO loc=new locationDTO(location_id, location, location);
    public locationDTO(int location_id, String location, String location_code) {
        this.location_id = location_id;
        this.location = location;
        this.location_code = location_code;
    }

    public locationDTO() {
    
    }

    public int getLocation_id() {
        return this.location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation_code() {
        return this.location_code;
    }

    public void setLocation_code(String location_code) {
        this.location_code = location_code;
    }

    
}
