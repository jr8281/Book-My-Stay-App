import java.util.*;

/**
 * ===============================================================
 * MAIN CLASS - UseCase8BookingHistoryReport
 * ===============================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * @version 8.0
 */
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("Booking History and Reporting\n");

        // Initialize booking history
        BookingHistory history = new BookingHistory();

        // Add confirmed reservations (simulating Use Case 6 output)
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        // Generate report
        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history);
    }
}

/* ================= BookingHistory ================= */
class BookingHistory {

    // Stores confirmed reservations in order
    private List<Reservation> confirmedReservations;

    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}

/* ================= BookingReportService ================= */
class BookingReportService {

    public void generateReport(BookingHistory history) {

        System.out.println("Booking History Report");

        List<Reservation> reservations = history.getConfirmedReservations();

        for (Reservation r : reservations) {
            System.out.println("Guest: " + r.getGuestName()
                    + ", Room Type: " + r.getRoomType());
        }
    }
}

/* ================= Reservation ================= */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}