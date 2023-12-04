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


@SpringBootTest
@ContextConfiguration(classes = {cartDao.class})


public class CartDaoTest {

    @Autowired
    private cartDao cartDao;

    @MockBean
    private SessionFactory sessionFactory;

    @MockBean
    private Session session;

    @BeforeEach
    public void setUp() {
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }
    @Test
    public void testGetCartByNonExistingUserId() {
        int nonExistingUserId = 99;
        Query<Cart> query = mock(Query.class);
        when(session.createQuery("from CART where customer_id = :userId", Cart.class)).thenReturn(query);
        when(query.setParameter("userId", nonExistingUserId)).thenReturn(query);
        when(query.uniqueResult()).thenReturn(null);
        Cart result = cartDao.getCartByUserId(nonExistingUserId);
        assertNull(result);
        verify(query).setParameter("userId", nonExistingUserId);
        verify(query).uniqueResult();
    }@Test
    public void testAddProductToCart() {
        Cart cart = new Cart();
        Product product = new Product();
        int quantity = 2;
        cartDao.addProductToCart(cart, product, quantity);
        verify(cart).addProduct(eq(product), eq(quantity));
        verify(session).update(cart);
    }@Test
    public void testRemoveProductFromCart() {
        Cart cart = new Cart();
        Product product = new Product();
        cartDao.removeProductFromCart(cart, product);
        verify(cart).removeProduct(product);
        verify(session).update(cart);
    }
    @Test
    public void testClearCart() {
        Cart cart = new Cart();
        cartDao.clearCart(cart);
        verify(cart).clear();
        verify(session).update(cart);
    }
    
    @Test
    public void testGetCarts() {
        Query<Cart> query = mock(Query.class);
        when(session.createQuery("from CART", Cart.class)).thenReturn(query);
        List<Cart> expectedCarts = new ArrayList<>();
        when(query.list()).thenReturn(expectedCarts);
        List<Cart> result = cartDao.getCarts();
        assertNotNull(result);
        assertEquals(expectedCarts, result);
        verify(session).createQuery("from CART", Cart.class);
    }
    @Test
    public void testUpdateCart() {
        Cart cart = new Cart();
        cartDao.updateCart(cart);
        verify(session).update(cart);
    }

    @Test
    public void testDeleteCart() {
        Cart cart = new Cart();
        cartDao.deleteCart(cart);
        verify(session).delete(cart);
    }
    @Test
    public void testGetCartByUserId() {
        int userId = 1;
        Query<Cart> query = mock(Query.class);
        when(session.createQuery("from CART where customer_id = :userId", Cart.class)).thenReturn(query);
        when(query.setParameter("userId", userId)).thenReturn(query);
        Cart expectedCart = new Cart();
        when(query.uniqueResult()).thenReturn(expectedCart);
        Cart result = cartDao.getCartByUserId(userId);
        assertNotNull(result);
        assertEquals(expectedCart, result);
        verify(query).setParameter("userId", userId);
        verify(query).uniqueResult();
    }
}
