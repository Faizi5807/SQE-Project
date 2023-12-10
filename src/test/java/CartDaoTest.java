import com.jtspringproject.JtSpringProject.dao.cartDao;
import com.jtspringproject.JtSpringProject.models.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartDaoTest {

    @InjectMocks
    private com.jtspringproject.JtSpringProject.dao.cartDao cartDao;

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(cartDao, "sessionFactory", sessionFactory);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    void testAddCart() {
        Cart cart = new Cart();
        when(session.save(cart)).thenReturn((Serializable) cart);

        Cart result = cartDao.addCart(cart);

        assertEquals(cart, result);
        verify(session).save(cart);
    }

    @Test
    void testGetCarts() {
        Query<Cart> query = mock(Query.class);
        List<Cart> expectedCarts = new ArrayList<>();
        when(session.createQuery("from CART", Cart.class)).thenReturn(query);
        when(query.list()).thenReturn(expectedCarts);

        List<Cart> result = cartDao.getCarts();

        assertEquals(expectedCarts, result);
        verify(session).createQuery("from CART", Cart.class);
        verify(query).list();
    }

    @Test
    void testUpdateCart() {
        Cart cart = new Cart();

        cartDao.updateCart(cart);

        verify(session).update(cart);
    }

    @Test
    void testDeleteCart() {
        Cart cart = new Cart();

        cartDao.deleteCart(cart);

        verify(session).delete(cart);
}

}