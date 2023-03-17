public class Slot {
    int slotNumber;
    VehicleType vehicleType;

    Vehicle vehicle = null;
    public Slot(int slotNumber, VehicleType vehicleType) {
        this.slotNumber = slotNumber;
        this.vehicleType = vehicleType;
    }

    public boolean isEmpty() {
        return vehicle == null;
    }

    public VehicleType getSlotType() {
        return vehicleType;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getSlotNumber() {
        return slotNumber;
    }
}
