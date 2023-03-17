import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Floor {
    Map<VehicleType, List<Slot>> vehicleType2slot;
    int totalSlotCount;
    int floorNumber;
    int truckSlotCount = 1;
    int bikeSlotCount = 2;
    int carSlotCount;
    ParkingLot parkingLot;

    public Floor(int floorNumber, int slotCount, ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.floorNumber = floorNumber;

        assert slotCount >= 3;

        carSlotCount = slotCount - truckSlotCount - bikeSlotCount;

        totalSlotCount = slotCount;

        vehicleType2slot = new HashMap<>();

        vehicleType2slot.put(VehicleType.TRUCK, new ArrayList<>());
        vehicleType2slot.put(VehicleType.BIKE, new ArrayList<>());
        vehicleType2slot.put(VehicleType.CAR, new ArrayList<>());

        int slotNumber = 1;
        int count = 1;

        // add truck slots
        while(count <= truckSlotCount){
            List<Slot> slots = vehicleType2slot.get(VehicleType.TRUCK);
            slots.add(new Slot(slotNumber, VehicleType.TRUCK));
            vehicleType2slot.put(VehicleType.TRUCK, slots);

            count++;
            slotNumber++;
        }

        count = 1;
        // add bike slots
        while(count <= bikeSlotCount){
            List<Slot> slots = vehicleType2slot.get(VehicleType.BIKE);
            slots.add(new Slot(slotNumber, VehicleType.BIKE));
            vehicleType2slot.put(VehicleType.BIKE, slots);

            count++;
            slotNumber++;
        }

        count = 1;
        // add car slots
        while(count <= carSlotCount){
            List<Slot> slots = vehicleType2slot.get(VehicleType.CAR);
            slots.add(new Slot(slotNumber, VehicleType.CAR));
            vehicleType2slot.put(VehicleType.CAR, slots);

            count++;
            slotNumber++;
        }

        // print slots
//        System.out.println("Printing slots for: " + this.floorNumber);
//        for(List<Slot> s: vehicleType2slot.values()){
//            System.out.println(s);
//        }
    }

    public List<Slot> getVacantSlots(VehicleType type){
        List<Slot> slots = vehicleType2slot.get(type);
        List<Slot> answer = new ArrayList<>();
        for(Slot s: slots){
            if(s.isEmpty())answer.add(s);
        }
        return answer;
    }


    public Ticket parkVehicle(Vehicle vehicle, Slot slot) {
        if(slot.isEmpty() && vehicle.getVehicleType() == slot.getSlotType()){
            slot.setVehicle(vehicle);
            return new Ticket(parkingLot, this, slot, vehicle);
        }
        return null;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<Slot> getOccupiedSlots(VehicleType type) {
        List<Slot> slots = vehicleType2slot.get(type);
        List<Slot> answer = new ArrayList<>();
        for(Slot s: slots){
            if(!s.isEmpty())answer.add(s);
        }
        return answer;
    }
}
