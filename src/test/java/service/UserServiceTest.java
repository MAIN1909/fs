package service;

import collections.UsersOnline;
import dao.UserDao;
import entity.Card;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserDao udao = mock(UserDao.class);
    private UsersOnline usersOnline = mock(UsersOnline.class);
    @Mock
    private User user;
    @Mock
    private HttpSession session;
    @Mock
    private List<Card> allCards;
    @Mock
    private List<Card> userCards;
    @Mock
    private Map<String, User> userMap;

    @Before
    public void before() {
        when(udao.getByLogin(anyString())).thenReturn(user);
        when(usersOnline.getUsersOnline()).thenReturn(userMap);
    }

    @Test
    public void isUserExistsTestExists() {
        UserService service = new UserService(udao, usersOnline);
        boolean result = service.isUserExists("login");
        assertTrue(result);
    }

    @Test
    public void isUserExistsTestNotExists() {
        when(udao.getByLogin(anyString())).thenReturn(null);
        UserService service = new UserService(udao, usersOnline);
        boolean result = service.isUserExists("login");
        assertFalse(result);
    }

    @Test
    public void getByLoginTestExists() {
        UserService service = new UserService(udao, usersOnline);
        User result = service.getByLogin("login");
        assertNotNull(result);
    }

    @Test
    public void getByLoginTestNotExists() {
        when(udao.getByLogin(anyString())).thenReturn(null);
        UserService service = new UserService(udao, usersOnline);
        User result = service.getByLogin("login");
        assertNull(result);
    }

    @Test
    public void saveTest() {
        UserService service = new UserService(udao, usersOnline);
        service.save("login", "pass");
        verify(udao, times(1)).add(any());
    }

    @Test
    public void isPassConfirmTest() {
        UserService service = new UserService(udao, usersOnline);
        boolean result = service.isPassConfirm("any", "any");
        assertTrue(result);
    }

    @Test
    public void isPassConfirmTestPassNull() {
        UserService service = new UserService(udao, usersOnline);
        boolean result = service.isPassConfirm(null, "any");
        assertFalse(result);
    }

    @Test
    public void isPassConfirmTestNotConfirm() {
        UserService service = new UserService(udao, usersOnline);
        boolean result = service.isPassConfirm("any", "srting");
        assertFalse(result);
    }

    @Test
    public void setUserAttributeToSessionTest() {
        UserService service = new UserService(udao, usersOnline);
        service.setUserAttributeToSession(session, user);
        verify(session, times(1)).setAttribute(anyString(), any());
    }

    @Test
    public void getUserAttributeFromSessionTestExists() {
        when(session.getAttribute(anyString())).thenReturn(user);
        UserService service = new UserService(udao, usersOnline);
        User result = service.getUserAttributeFromSession(session);
        assertNotNull(result);
    }

    @Test
    public void getUserAttributeFromSessionTestNotExists() {
        UserService service = new UserService(udao, usersOnline);
        User result = service.getUserAttributeFromSession(session);
        assertNull(result);
    }

    @Test
    public void updateTest() {
        UserService service = new UserService(udao, usersOnline);
        service.update(user);
        verify(udao, times(1)).update(any());
    }

    @Test
    public void removeExistsCardsTest() {
        UserService service = new UserService(udao, usersOnline);
        service.removeExistsCards(allCards, userCards);
        verify(allCards, times(1)).removeAll(userCards);
    }

    @Test
    public void addUsersOnlineTest() {
        UserService service = new UserService(udao, usersOnline);
        service.addUsersOnline(user);
        verify(usersOnline.getUsersOnline(), times(1)).put(user.getLogin(), user);
    }

    @Test
    public void removeUserFromSessionTest() {
        UserService service = new UserService(udao, usersOnline);
        service.removeUserFromSession("login");
        verify(usersOnline.getUsersOnline(), times(1)).remove("login");
    }
}
