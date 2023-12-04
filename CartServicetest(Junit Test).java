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


class CartServiceTest {

    @Mock
    private cartDao cartDao;

    @InjectMocks
    private cartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }@Test
    void testAddProductToCart() {
        Cart cart = new Cart();
        Product product = new Product();
        int quantity = 2;
        when(cartDao.addProductToCart(cart, product, quantity)).thenReturn(true);
        boolean result = cartService.addProductToCart(cart, product, quantity);
        assertTrue(result);
        verify(cartDao, times(1)).addProductToCart(cart, product, quantity);
    }
    @Test
    void testRemoveProductFromCart() {
        Cart cart = new Cart();
        Product product = new Product();
        when(cartDao.removeProductFromCart(cart, product)).thenReturn(true);
        boolean result = cartService.removeProductFromCart(cart, product);
        assertTrue(result);
        verify(cartDao, times(1)).removeProductFromCart(cart, product);
    }
    
    @Test
    void testClearCart() {
        Cart cart = new Cart();
        when(cartDao.clearCart(cart)).thenReturn(true);
        boolean result = cartService.clearCart(cart);
        assertTrue(result);
        verify(cartDao, times(1)).clearCart(cart);
    }
    
    @Test
    void testAddProductToCartFailure() {
        Cart cart = new Cart();
        Product product = new Product();
        int quantity = 2;
        when(cartDao.addProductToCart(cart, product, quantity)).thenReturn(false);
        boolean result = cartService.addProductToCart(cart, product, quantity);
        assertFalse(result);
        verify(cartDao, times(1)).addProductToCart(cart, product, quantity);
    }
    
    @Test
    void testRemoveProductFromCartFailure() {
        Cart cart = new Cart();
        Product product = new Product();
        when(cartDao.removeProductFromCart(cart, product)).thenReturn(false);
        boolean result = cartService.removeProductFromCart(cart, product);
        assertFalse(result);
        verify(cartDao, times(1)).removeProductFromCart(cart, product);
    }
    
    @Test
    void testAddCart() {
        Cart cart = new Cart();
        when(cartDao.addCart(cart)).thenReturn(cart);
        Cart result = cartService.addCart(cart);
        assertEquals(cart, result);
        verify(cartDao, times(1)).addCart(cart);
    }
    
    @Test
    void testUpdateCart() {
        Cart cart = new Cart();
        cartService.updateCart(cart);
        verify(cartDao, times(1)).updateCart(cart);
    }
    @Test
    void testDeleteCart() {
        Cart cart = new Cart();
        cartService.deleteCart(cart);
        verify(cartDao, times(1)).deleteCart(cart);
    }
 
}

