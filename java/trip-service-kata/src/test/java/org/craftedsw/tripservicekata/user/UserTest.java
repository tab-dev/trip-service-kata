package org.craftedsw.tripservicekata.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    @Test
    public void friendsWithTest(){
        User user = new User();
        User friend = new User();
        User notFriend = new User();

        user.addFriend(friend);

        assertTrue(user.isFriendsWith(friend));
        assertFalse(user.isFriendsWith(notFriend));
    }
}
