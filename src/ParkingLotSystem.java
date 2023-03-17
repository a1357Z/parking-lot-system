

import java.util.*;

public class ParkingLotSystem {
    ParkingLot parkingLot;
    Map<String, Integer> commands2length;
    List<Ticket> tickets;

    Set<String> displayTypes, vehicleTypes;

    public ParkingLotSystem(){
        commands2length = new HashMap<>();
        tickets = new ArrayList<>();
        commands2length.put("create_parking_lot", 4);
        commands2length.put("park_vehicle",4);
        commands2length.put("unpark_vehicle", 2);
        commands2length.put("display", 3);
        commands2length.put("exit", 1);

        displayTypes = new HashSet<>();
        displayTypes.add("free_count");
        displayTypes.add("free_slots");
        displayTypes.add("occupied_slots");

        vehicleTypes = new HashSet<>();
        vehicleTypes.add("CAR");
        vehicleTypes.add("BIKE");
        vehicleTypes.add("TRUCK");
    }
    public boolean processInput(String input){
        String [] commands = input.split(" ");
        // return if empty input
        if(commands.length == 0)return false;

        //if exit command return true
        if(Objects.equals(commands[0], "exit"))return true;

        // return if invalid first command or if commands length don't match
        if(!commands2length.containsKey(commands[0]) || commands2length.get(commands[0]) != commands.length)return false;

        // create parking lot command
        if(Objects.equals(commands[0], "create_parking_lot")){
            String parkingLotId = commands[1];
            int floorCount = Integer.parseInt(commands[2]);
            int slotCount = Integer.parseInt(commands[3]);

            if ( floorCount <= 0 || slotCount < 3){
                System.out.println("Invalid parameters for creating parking lot");
                return false;
            }


            parkingLot = new ParkingLot(parkingLotId, floorCount, slotCount);
            System.out.println("Created parking lot with " + floorCount + " floors and " + slotCount + " slots per floor");
            return false;
        }

        // park a vehicle
        if(Objects.equals(commands[0], "park_vehicle")){
            String vehicleType = commands[1];
            String regNo = commands[2];
            String colour = commands[3];

            Vehicle vehicle = new Vehicle(vehicleType, regNo, colour);

            Ticket ticket = parkingLot.parkVehicle(vehicle);

            if(ticket == null){
                System.out.println("Parking Lot Full");
                return false;
            }else{
                tickets.add(ticket);
                System.out.println("Parked vehicle. Ticket ID: " + ticket.getId());
            }
        }

        if(Objects.equals(commands[0], "unpark_vehicle")){
            String ticketId = commands[1];
            Ticket ticket = null;
            for(Ticket t: tickets){
                if(Objects.equals(t.getId(), ticketId)){
                    // unpark
                    t.unpark();
                    ticket = t;
                    break;
                }
            }

            //remove ticket
            if(ticket != null){
                tickets.remove(ticket);
                Vehicle vehicle = ticket.getVehicle();
                String regNo = vehicle.getRegNo();
                String colour = vehicle.getColour();
                System.out.println("Unparked vehicle with Registration Number: " + regNo + " and Color: " + colour);
            }else{
                System.out.println("Invalid Ticket");
            }
        }

        if(Objects.equals(commands[0], "display")){
            String displayType = commands[1];
            String vehicleType = commands[2];

            if(!displayTypes.contains(displayType) || !vehicleTypes.contains(vehicleType)){
                System.out.println("Invalid Command");
                return false;
            }

            VehicleType v;
            if(Objects.equals(vehicleType, "BIKE")){
                v = VehicleType.BIKE;
            }else if(Objects.equals(vehicleType, "CAR")){
                v = VehicleType.CAR;
            }else{
                v = VehicleType.TRUCK;
            }

            if(Objects.equals(displayType, "free_count")){
                parkingLot.displayFreeCount(v);
            }else if(Objects.equals(displayType, "free_slots")){
                parkingLot.displayFreeSlots(v);
            }else{
                parkingLot.displayOccupiedSlots(v);
            }
        }

        return false;



    }
}
