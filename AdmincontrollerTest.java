package com.jtspringproject.JtSpringProject.servicestests;

import com.jtspringproject.JtSpringProject.dao.categoryDao;
import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.services.categoryService;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest 
{ 
    //Testing admin controller
    @InjectMocks
    private AdminController adminController;
    private List<Category> mockCategories;
    private List<Product> mockProducts;

    // using service clases
    @BeforeEach
    @Mock
    private userService userService;
    @Mock
    private categoryService categoryService;
    @Mock
    private productService productService;
   //Tests
   //setup test
    public void setup() 
    {
        mockCategories = new ArrayList<>();
        mockCategories.add(new Category(1, "Cate1"));
        mockCategories.add(new Category(2, "Cate2"));

        mockProducts = new ArrayList<>();
        mockProducts.add(new Product(1, "Prdct1", mockCategories.get(0), 10, 100, 5, "Description1", "Image1"));
        mockProducts.add(new Product(2, "Prdct2", mockCategories.get(1), 20, 150, 8, "Description2", "Image2"));
    }@Test
public void testAdminLoginWithInvalidUser() {
    when(userService.checkLogin("invalidAdmin", "invalidPassword")).thenReturn(null);
    ModelAndView modelAndView = adminController.adminlogin("invalidAdmin", "invalidPassword");
    assertEquals("loginError", modelAndView.getViewName());
    assertEquals(0, modelAndView.getModel().size());
}
@Test//testing when Admin logins with valid user name 
    public void testAdminLoginWithValidUser() 
    {
        when(userService.checkLogin("admin", "password")).thenReturn(new User("admin", "ROLE_ADMIN"));
        ModelAndView modelAndView = adminController.adminlogin("admin", "password");
        assertEquals("adminHome", modelAndView.getViewName());
        assertEquals(1, modelAndView.getModel().size());
        assertTrue(modelAndView.getModel().containsKey("admin"));
        assertEquals("admin", ((User) modelAndView.getModel().get("admin")).getUsername());
    }
@Test
public void testAdminLoginWithNullUser() {
    when(userService.checkLogin(null, null)).thenReturn(null);
    ModelAndView modelAndView = adminController.adminlogin(null, null);
    assertEquals("loginError", modelAndView.getViewName());
    assertEquals(0, modelAndView.getModel().size());
}

@Test
public void testGetProducts() {
    when(productService.getAllProducts()).thenReturn(mockProducts);
    ModelAndView modelAndView = adminController.getProducts();
    assertEquals("products", modelAndView.getViewName());
    assertEquals(1, modelAndView.getModel().size());
    assertTrue(modelAndView.getModel().containsKey("products"));
    assertEquals(mockProducts, modelAndView.getModel().get("products"));
}

    @Test// Testing case when username is not empty
    public void testIndexWhenUsernameIsNotEmpty() {
        Model model = mock(Model.class);
        adminController.setUsernameforclass("admin"); 
        when(model.addAttribute("username", "admin")).thenReturn(model);
        String result = adminController.index(model);
        assertEquals("index", result);
        verify(model, times(1)).addAttribute("username", "admin");
    }@Test//Testing when username is empty 
    public void testIndexWhenUsernameIsEmpty() 
    {
        Model model = mock(Model.class);
        when(model.addAttribute("username", "")).thenReturn(model);
        String result = adminController.index(model);
        assertEquals("userLogin", result);
        verify(model, times(1)).addAttribute("username", "");
    }
}
