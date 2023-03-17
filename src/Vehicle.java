import java.util.Objects;

public class Vehicle {
    String regNo;
    String colour;
    VehicleType vehicleType;

    public Vehicle(String vehicleType, String regNo, String colour) {

        // assign vehicle type
        if(Objects.equals(vehicleType, "CAR")){
            this.vehicleType = VehicleType.CAR;
        }else if(Objects.equals(vehicleType, "BIKE")){
            this.vehicleType = VehicleType.BIKE;
        }else if(Objects.equals(vehicleType, "TRUCK")){
            this.vehicleType = VehicleType.TRUCK;
        }

        this.regNo = regNo;
        this.colour = colour;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getColour() {
        return colour;
    }
}
