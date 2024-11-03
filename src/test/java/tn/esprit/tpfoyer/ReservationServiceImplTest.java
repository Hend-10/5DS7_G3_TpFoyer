package tn.esprit.tpfoyer;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;
import tn.esprit.tpfoyer.ser.ReservationServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
public class ReservationServiceImplTest {
    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllReservations() {
        // Arrange
        Reservation reservation1 = new Reservation();
        Reservation reservation2 = new Reservation();
        when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservation1, reservation2));

        // Act
        List<Reservation> reservations = reservationService.retrieveAllReservations();

        // Assert
        assertEquals(2, reservations.size());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveReservation() {
        // Arrange
        String reservationId = "123";
        Reservation reservation = new Reservation();
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));

        // Act
        Reservation result = reservationService.retrieveReservation(reservationId);

        // Assert
        assertNotNull(result);
        verify(reservationRepository, times(1)).findById(reservationId);
    }

    @Test
    void testAddReservation() {
        // Arrange
        Reservation reservation = new Reservation();
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Act
        Reservation result = reservationService.addReservation(reservation);

        // Assert
        assertNotNull(result);
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testModifyReservation() {
        // Arrange
        Reservation reservation = new Reservation();
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Act
        Reservation result = reservationService.modifyReservation(reservation);

        // Assert
        assertNotNull(result);
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testTrouverResSelonDateEtStatus() {
        // Arrange
        Date date = new Date();
        boolean status = true;
        Reservation reservation1 = new Reservation();
        Reservation reservation2 = new Reservation();
        when(reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(date, status))
                .thenReturn(Arrays.asList(reservation1, reservation2));

        // Act
        List<Reservation> reservations = reservationService.trouverResSelonDateEtStatus(date, status);

        // Assert
        assertEquals(2, reservations.size());
        verify(reservationRepository, times(1))
                .findAllByAnneeUniversitaireBeforeAndEstValide(date, status);
    }

    @Test
    void testRemoveReservation() {
        // Arrange
        String reservationId = "123";

        // Act
        reservationService.removeReservation(reservationId);

        // Assert
        verify(reservationRepository, times(1)).deleteById(reservationId);
    }
}
