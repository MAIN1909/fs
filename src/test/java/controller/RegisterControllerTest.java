package controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterControllerTest {
    private UserService userService = mock(UserService.class);
    @Mock
    private HttpServletResponse resp;

    @Before
    public void before() {
        when(userService.isUserExists(anyString())).thenReturn(true);
    }

    @Test
    public void registerViewTest() {
        RegisterController controller = new RegisterController(userService);
        ModelAndView result = controller.registerView();
        assertNotNull(result);
        assertEquals("register", result.getViewName());
    }

    @Test
    public void loginTestExist() throws IOException {

        RegisterController controller = new RegisterController(userService);
        controller.register("any", "any", "any", resp);
        verify(userService, times(1)).isUserExists(anyString());
        verify(resp).sendRedirect(anyString());

        when(userService.isUserExists(anyString())).thenReturn(false);
        controller.register("any", "any", "any", resp);
        verify(userService, times(2)).isUserExists(anyString());
        verify(resp, times(2)).sendRedirect(anyString());
    }

    @Test
    public void loginTestConfirmPass() throws IOException {
        RegisterController controller = new RegisterController(userService);
        controller.register("any", "any", "any", resp);
        verify(userService, times(1)).isPassConfirm(anyString(), anyString());
        verify(resp).sendRedirect(anyString());

        when(userService.isPassConfirm(anyString(), anyString())).thenReturn(false);
        controller.register("any", "any", "any", resp);
        verify(userService, times(2)).isPassConfirm(anyString(), anyString());
        verify(resp, times(2)).sendRedirect(anyString());

    }

    @Test
    public void savesUser() {

    }
}
