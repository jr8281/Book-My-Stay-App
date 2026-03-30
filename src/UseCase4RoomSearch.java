import java.util.HashMap;
import java.util.Map;

/**
 * MAIN CLASS - UseCase4RoomSearch
 * @version 4.0
 */
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room single = new Room("Single", 1, 250, 1500);
        Room dbl = new Room("Double", 2, 400, 2500);
        Room suite = new Room("Suite", 3, 750, 5000);

        RoomSearchService searchService = new RoomSearchService();
        searchService.searchAvailableRooms(inventory, single, dbl, suite);
    }
}

/* ================= RoomSearchService ================= */
class RoomSearchService {

    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        System.out.println("Room Search\n");

        if (availability.get("Single") > 0) {
            printRoom(singleRoom, availability.get("Single"));
        }

        if (availability.get("Double") > 0) {
            printRoom(doubleRoom, availability.get("Double"));
        }

        if (availability.get("Suite") > 0) {
            printRoom(suiteRoom, availability.get("Suite"));
        }
    }

    private void printRoom(Room room, int available) {
        System.out.println(room.getType() + " Room:");
        System.out.println("Beds: " + room.getBeds());
        System.out.println("Size: " + room.getSize() + " sqft");
        System.out.println("Price per night: " + room.getPrice());
        System.out.println("Available: " + available);
        System.out.println();
    }
}

/* ================= RoomInventory ================= */
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }
}

/* ================= Room ================= */
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

    public String getType() { return type; }
    public int getBeds() { return beds; }
    public int getSize() { return size; }
    public double getPrice() { return price; }
}
