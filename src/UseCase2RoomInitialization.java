// Abstract Class
abstract class Room {
    private int beds;
    private int size;
    private double price;

    public Room(int beds, int size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
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

    // Abstract method
    public abstract String getRoomType();
}

// Single Room Class
class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250, 1500.0);
    }

    @Override
    public String getRoomType() {
        return "Single Room";
    }
}

// Double Room Class
class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }

    @Override
    public String getRoomType() {
        return "Double Room";
    }
}

// Suite Room Class
class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 750, 5000.0);
    }

    @Override
    public String getRoomType() {
        return "Suite Room";
    }
}

// Main Application Class
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        // Static availability variables
        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        // Room objects (Polymorphism)
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        System.out.println("Hotel Room Initialization\n");

        // Display Single Room
        displayRoom(singleRoom, singleAvailability);

        // Display Double Room
        displayRoom(doubleRoom, doubleAvailability);

        // Display Suite Room
        displayRoom(suiteRoom, suiteAvailability);
    }

    // Helper method to display room details
    public static void displayRoom(Room room, int availability) {
        System.out.println(room.getRoomType() + ":");
        System.out.println("Beds: " + room.getBeds());
        System.out.println("Size: " + room.getSize() + " sqft");
        System.out.println("Price per night: " + room.getPrice());
        System.out.println("Available: " + availability);
        System.out.println();
    }
}
