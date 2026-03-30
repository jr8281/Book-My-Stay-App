import java.util.*;

/**
 * MAIN CLASS - UseCase6RoomAllocationService
 * @version 6.0
 */
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("Room Allocation Processing");

        RoomInventory inventory = new RoomInventory();

        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Single"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));

        RoomAllocationService allocationService = new RoomAllocationService();

        while (queue.hasPendingRequests()) {
            Reservation r = queue.getNextRequest();
            allocationService.allocateRoom(r, inventory);
        }
    }
}

/* ================= RoomAllocationService ================= */
class RoomAllocationService {

    private Set<String> allocatedRoomIds = new HashSet<>();
    private Map<String, Set<String>> assignedRoomsByType = new HashMap<>();

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String type = reservation.getRoomType();
        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get(type) == null || availability.get(type) <= 0) {
            System.out.println("No rooms available for type: " + type);
            return;
        }

        String roomId = generateRoomId(type);

        allocatedRoomIds.add(roomId);
        assignedRoomsByType
                .computeIfAbsent(type, k -> new HashSet<>())
                .add(roomId);

        inventory.updateAvailability(type, availability.get(type) - 1);

        System.out.println("Booking confirmed for Guest: "
                + reservation.getGuestName()
                + ", Room ID: "
                + roomId);
    }

    private String generateRoomId(String type) {

        assignedRoomsByType.putIfAbsent(type, new HashSet<>());
        int count = assignedRoomsByType.get(type).size() + 1;

        String roomId = type + "-" + count;

        while (allocatedRoomIds.contains(roomId)) {
            count++;
            roomId = type + "-" + count;
        }

        return roomId;
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

    public void updateAvailability(String type, int count) {
        roomAvailability.put(type, count);
    }
}

/* ================= BookingRequestQueue ================= */
class BookingRequestQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) { queue.offer(r); }
    public Reservation getNextRequest() { return queue.poll(); }
    public boolean hasPendingRequests() { return !queue.isEmpty(); }
}

/* ================= Reservation ================= */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}