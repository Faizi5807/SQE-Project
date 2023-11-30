import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.servlet.ModelAndView;

import com.jtspringproject.JtSpringProject.controller.AdminController;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.userService;

@SpringBootTest
class AdminControllerTests {

    @Autowired
    private AdminController adminController;

    @MockBean
    private userService userService;

    @Test
    void testAdminLoginSuccess() {
        String username = "admin";
        String password = "admin123";
        User mockUser = new User();
        mockUser.setRole("ROLE_ADMIN");
        when(userService.checkLogin(username, password)).thenReturn(mockUser);
        ModelAndView modelAndView = adminController.adminlogin(username, password);
        assertEquals("adminHome", modelAndView.getViewName());
        assertEquals(1, modelAndView.getModel().size());
    }

    @Test
    void testAdminLoginFailure() {
        String username = "user";
        String password = "wrongpass";
        when(userService.checkLogin(username, password)).thenReturn(new User());
        ModelAndView modelAndView = adminController.adminlogin(username, password);
        assertEquals("adminlogin", modelAndView.getViewName());
        assertEquals("Please enter correct username and password", modelAndView.getModel().get("msg"));
    }
    void testAdminHome() {
        adminController.setAdminlogcheck(1);
        adminController.setUsernameforclass("admin");
        Model model = mock(Model.class);
        String result = adminController.adminHome(model);
        assertEquals("adminHome", result);
        assertEquals("admin", model.getAttribute("username"));
    }
}
}
