package controller;

import collections.WaitUsers;
import entity.Battle;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import service.BattleService;
import service.UserService;
import service.WaitService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WaitControllerTest {

    private UserService userService = mock(UserService.class);
    private WaitService waitService = mock(WaitService.class);
    private BattleService battleService = mock(BattleService.class);
    private WaitUsers waitUsers = mock(WaitUsers.class);

    @Mock
    User user;
    @Mock
    Map<String, User> users;
    @Mock
    Battle battle;
    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse resp;
    @Mock
    private HttpSession session;


    @Before
    public void before() {
        when(req.getSession()).thenReturn(session);
        when(userService.getUserAttributeFromSession(req.getSession())).thenReturn(user);
        when(waitUsers.getWaitList()).thenReturn(users);
        when(battleService.getBattleById(battleService.isUserInBattle2(user.getLogin()))).thenReturn(battle);
    }

    @Test
    public void waitPageUserExistTest() throws IOException {
        when(users.size()).thenReturn(1);
        WaitController controller = new WaitController(userService, waitUsers, waitService, battleService);
        ModelAndView result = controller.waitPage(req, resp);
        assertNotNull(result);
        assertEquals("wait", result.getViewName());
        assertTrue(result.getModel().containsKey("u"));
        assertTrue(result.getModel().containsValue(user));
    }

    @Test
    public void waitPageWaitListEmptyTest() throws IOException {
        WaitController controller = new WaitController(userService, waitUsers, waitService, battleService);
        ModelAndView result = controller.waitPage(req, resp);
        verify(req.getSession()).getId();
        verify(resp).sendRedirect(anyString());
        assertNull(result);
    }

    @Test
    public void waitPageUserNullTest() throws IOException {
        when(userService.getUserAttributeFromSession(req.getSession())).thenReturn(null);
        WaitController controller = new WaitController(userService, waitUsers, waitService, battleService);
        ModelAndView result = controller.waitPage(req, resp);
        verify(req.getSession()).getId();
        verify(resp).sendRedirect(anyString());
        assertNull(result);
    }

    @Test
    public void waitLogicWaitUserExistTest() throws IOException {
        when(waitService.isWaitListEmpty()).thenReturn(false);
        when(waitService.waitUsersListContainsUserByLogin(user.getLogin())).thenReturn(false);
        when(waitService.getUserForBattle()).thenReturn(user);
        WaitController controller = new WaitController(userService, waitUsers, waitService, battleService);
        controller.waitLogic(req, resp, "in");
        verify(waitService).getUserForBattle();
        verify(battleService).createBattle(user, user);
        verify(req.getSession()).setAttribute(anyString(), anyInt());
        verify(battleService).addActivePlayer(any(), any(), anyInt());
        verify(resp).sendRedirect("/fs/battle/");
    }

    @Test
    public void waitLogicUserFromBattleTest() throws IOException {
        WaitController controller = new WaitController(userService, waitUsers, waitService, battleService);
        controller.waitLogic(req, resp, "in");
        verify(req.getSession()).setAttribute(anyString(), anyInt());
        verify(resp).sendRedirect("/fs/battle/");
    }

    @Test
    public void waitLogicUserInWaitListTest() throws IOException {
        when(battleService.getBattleById(battleService.isUserInBattle2(user.getLogin()))).thenReturn(null);
        when(waitService.waitUsersListContainsUserByLogin(user.getLogin())).thenReturn(true);
        WaitController controller = new WaitController(userService, waitUsers, waitService, battleService);
        controller.waitLogic(req, resp, "in");
        verify(resp).sendRedirect("/fs/wait/");
    }

    @Test
    public void waitLogicLeaveTest() throws IOException {
        when(users.containsKey(user.getLogin())).thenReturn(true);
        WaitController controller = new WaitController(userService, waitUsers, waitService, battleService);
        controller.waitLogic(req, resp, "out");
        verify(waitUsers.getWaitList()).remove(user.getLogin());
        verify(resp).sendRedirect("/fs/main/");
    }

    @Test
    public void waitLogicUserNullTest() throws IOException {
        when(userService.getUserAttributeFromSession(req.getSession())).thenReturn(null);
        WaitController controller = new WaitController(userService, waitUsers, waitService, battleService);
        controller.waitLogic(req, resp, "any");
        verify(resp).sendRedirect("/fs/");
    }
}
