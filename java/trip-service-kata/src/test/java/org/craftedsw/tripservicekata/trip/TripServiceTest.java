package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    @InjectMocks
    private TripService tripService;

    @Mock
    private UserSession userSession;
    @Mock
    private TripDAO tripDAO;

    private static User friend;
    private static User loggedUser;

    public TripServiceTest() {
        friend = new User();
        friend.addTrip(new Trip());
        friend.addTrip(new Trip());

        loggedUser = createUser();
        friend.addFriend(loggedUser);
    }

    private List<Trip> createTrips() {
        return Arrays.asList(new Trip());
    }

    private static User createUser() {
        User user = new User();

        user.addTrip(new Trip());
        user.addFriend(friend);

        return user;
    }

    @Test
    public void getTrips() {
        doReturn(loggedUser).when(userSession).getLoggedUser();
        doReturn(createTrips()).when(tripDAO).findTripsByUser(any(User.class));

        List<Trip> trips = tripService.getTripsByUser(friend);

        assertNotNull(trips);
        assertFalse(trips.isEmpty());
    }

    @Test
    public void getTripsWithNoLoggedUser(){
        doReturn(null).when(userSession).getLoggedUser();
        assertThrows(UserNotLoggedInException.class, ()-> tripService.getTripsByUser(friend));
    }
}
