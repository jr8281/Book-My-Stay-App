import java.util.HashMap;
import java.util.Map;

/**
 * ===============================================================
 * MAIN CLASS - UseCase3InventorySetup
 * ===============================================================
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * @version 3.1
 */
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        // Initialize Inventory
        RoomInventory inventory = new RoomInventory();

        // Create Room objects
        Room single = new Room("Single", 1, 250, 1500);
        Room dbl = new Room("Double", 2, 400, 2500);
        Room suite = new Room("Suite", 3, 750, 5000);

        // Fetch availability
        Map<String, Integer> availability = inventory.getRoomAvailability();

        // Display inventory
        System.out.println("Hotel Room Inventory Status\n");

        printRoomDetails(single, availability.get("Single"));
        printRoomDetails(dbl, availability.get("Double"));
        printRoomDetails(suite, availability.get("Suite"));
    }

    private static void printRoomDetails(Room room, int available) {
        System.out.println(room.getType() + " Room:");
        System.out.println("Beds: " + room.getBeds());
        System.out.println("Size: " + room.getSize() + " sqft");
        System.out.println("Price per night: " + room.getPrice());
        System.out.println("Available Rooms: " + available);
        System.out.println();
    }
}

/**
 * ===============================================================
 * CLASS - RoomInventory
 * ===============================================================
 *
 * Acts as single source of truth for room availability.
 *
 * @version 3.1
 */
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

/**
 * ===============================================================
 * CLASS - Room
 * ===============================================================
 *
 * Represents room properties.
 */
class Room {

    private String type;
    private int beds;
    private int size;
    private double price;

    public Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public int getBeds() {
        return beds;
    }

    public int getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }
}
