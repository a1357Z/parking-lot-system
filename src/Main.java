import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        Scanner s = new Scanner(System.in);

        while (true){
            String input = s.nextLine();
            boolean exit = parkingLotSystem.processInput(input);
            if(exit)break;
        }
    }
}