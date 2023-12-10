package com.jtspringproject.JtSpringProject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpSession;

import com.jtspringproject.JtSpringProject.controller.AdminController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.categoryService;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.services.userService;

class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private userService userService;

    @Mock
    private categoryService categoryService;

    @Mock
    private productService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testReturnIndex() {
        String result = adminController.returnIndex();
        assertEquals("redirect:/", result);
    }

    @Test
    void testIndexWhenUsernameIsEmpty() {
        Model model = mock(Model.class);
        String result = adminController.index(model);
        assertEquals("userLogin", result);
    }

    @Test
    void testAdminLogin() {
        User user = new User();
        user.setRole("ROLE_ADMIN");
        when(userService.checkLogin("admin", "password")).thenReturn(user);

        ModelAndView modelAndView = adminController.adminlogin("admin", "password");

        assertEquals("adminHome", modelAndView.getViewName());
        assertEquals(1, modelAndView.getModel().size());
        assertTrue(modelAndView.getModel().containsKey("admin"));
        assertEquals(user, modelAndView.getModel().get("admin"));
    }

    // More test methods for other controller methods can be added here
}