import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParkingLot {
    String id;
    List<Floor> floors;

    public ParkingLot(String parkingLotId, int floorCount, int slotCount) {
        id = parkingLotId;
        floors = new ArrayList<>(floorCount);

//        System.out.println(parkingLotId);
//        System.out.println(floorCount);
//        System.out.println(slotCount);

        //create floors
        int floorNumber = 1;
        while(floorNumber <= floorCount){
            Floor floor = new Floor(floorNumber, slotCount, this);
            floors.add(floor);
            floorNumber++;
        }


    }

    public Ticket parkVehicle(Vehicle vehicle) {
        // find the first vacant slot for vehicle
        List<Slot> slots;
        for(Floor floor: floors){
             slots = floor.getVacantSlots(vehicle.getVehicleType());
             if(!slots.isEmpty()){
                 // park it and return ticket
                return floor.parkVehicle(vehicle, slots.get(0));
             }
        }
        // no slots found
        return null;
    }

    public String getId() {
        return id;
    }

    public void displayFreeCount(VehicleType vehicleType) {
        for(Floor floor: floors){
            List<Slot> slots = floor.getVacantSlots(vehicleType);
            System.out.println("No. of free slots for " + vehicleType + " on Floor " + floor.getFloorNumber() + " : " + slots.size());
        }
    }

    public void displayFreeSlots(VehicleType vehicleType) {
        for(Floor floor: floors){
            List<Slot> slots = floor.getVacantSlots(vehicleType);
            StringBuilder s = new StringBuilder();
            for(Slot slot : slots){
                s.append(slot.getSlotNumber());
                s.append(", ");
            }
            System.out.println("Free slots for " + vehicleType + " on Floor " + floor.getFloorNumber() + " : " + s);
        }
    }

    public void displayOccupiedSlots(VehicleType vehicleType) {
        for(Floor floor: floors){
            List<Slot> slots = floor.getOccupiedSlots(vehicleType);
            StringBuilder s = new StringBuilder();
            for(Slot slot : slots){
                s.append(slot.getSlotNumber());
                s.append(", ");
            }
            System.out.println("Occupied slots for " + vehicleType + " on Floor " + floor.getFloorNumber() + " : " + s);
        }
    }
}
