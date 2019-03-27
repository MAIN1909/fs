package controller;

import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    private UserService userService = mock(UserService.class);
    @Mock
    private HttpSession session;
    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse resp;
    @Mock
    private User user;

    @Before
    public void before() {
        when(req.getSession()).thenReturn(session);
        when(userService.getByLogin(anyString())).thenReturn(user);
        when(user.getPass()).thenReturn("pass");

    }

    @Test
    public void loginViewTest() {
        LoginController controller = new LoginController(userService);
        ModelAndView result = controller.loginView();
        assertNotNull(result);
        assertEquals("login", result.getViewName());
    }

    @Test
    public void loginTest() throws IOException {
        LoginController controller = new LoginController(userService);
        controller.login("login", "pass", req, resp);
        verify(userService, times(1)).getByLogin("login");
        verify(user, times(1)).getPass();
        verify(userService, times(1)).setUserAttributeToSession(session, user);
        verify(req, times(1)).getSession();
        verify(userService, times(1)).addUsersOnline(user);
        verify(resp, times(1)).sendRedirect(anyString());
    }

    @Test
    public void loginTestIncorrectLogin() throws IOException {
        when(userService.getByLogin(anyString())).thenReturn(null);

        LoginController controller = new LoginController(userService);
        controller.login("login", "pass", req, resp);
        verify(userService, times(1)).getByLogin("login");
        verify(resp, times(1)).sendRedirect(anyString());
        verify(user, times(0)).getPass();
        verify(req, times(0)).getSession();
    }

    @Test
    public void loginTestIncorrectPass() throws IOException {
        when(user.getPass()).thenReturn("p");

        LoginController controller = new LoginController(userService);
        controller.login("login", "pass", req, resp);
        verify(userService, times(1)).getByLogin("login");
        verify(resp, times(1)).sendRedirect(anyString());
        verify(user, times(1)).getPass();
        verify(req, times(0)).getSession();
    }
}
