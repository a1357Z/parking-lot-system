public class Ticket {
    Vehicle vehicle;
    ParkingLot parkingLot;
    Floor floor;
    Slot slot;
    String ticket;
    public Ticket(ParkingLot parkingLot, Floor floor, Slot slot, Vehicle vehicle) {
        this.parkingLot = parkingLot;
        this.floor = floor;
        this.slot = slot;
        this.vehicle = vehicle;
        ticket = parkingLot.getId() + "_" + floor.getFloorNumber() + "_" + slot.getSlotNumber();
    }

    public String getId() {
        return ticket;
    }

    public void unpark() {
        slot.setVehicle(null);
    }

    public Vehicle getVehicle(){
        return vehicle;
    }
}
