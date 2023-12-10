
import com.jtspringproject.JtSpringProject.dao.cartDao;
import com.jtspringproject.JtSpringProject.dao.categoryDao;
import com.jtspringproject.JtSpringProject.models.Cart;
import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.services.cartService;
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
    private com.jtspringproject.JtSpringProject.dao.cartDao cartDao;

    @InjectMocks
    private com.jtspringproject.JtSpringProject.services.cartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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