import com.jtspringproject.JtSpringProject.dao.productDao;
import com.jtspringproject.JtSpringProject.models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ProductDaoTest {

    @InjectMocks
    private com.jtspringproject.JtSpringProject.dao.productDao productDao;

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(productDao, "sessionFactory", sessionFactory);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    void testGetProducts() {
        List<Product> expectedProducts = List.of(new Product(), new Product());
        Query<Product> mockQuery = mock(Query.class);
        when(session.createQuery("from Product", Product.class)).thenReturn(mockQuery);
        when(mockQuery.list()).thenReturn(expectedProducts);

        List<Product> result = productDao.getProducts();

        assertEquals(expectedProducts, result);
        verify(session).createQuery("from Product", Product.class);
        verify(mockQuery).list();
    }

    @Test
    void testAddProduct() {
        Product product = new Product();
        doNothing().when(session).save(product);

        Product result = productDao.addProduct(product);

        assertEquals(product, result);
        verify(session).save(product);
    }

    @Test
    void testGetProduct() {
        int productId = 1;
        Product expectedProduct = new Product();
        when(session.get(Product.class, productId)).thenReturn(expectedProduct);

        Product result = productDao.getProduct(productId);

        assertEquals(expectedProduct, result);
        verify(session).get(Product.class, productId);
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        doNothing().when(session).update(product);

        Product result = productDao.updateProduct(product);

        assertEquals(product, result);
        verify(session).update(product);
    }

    @Test
    void testDeleteProduct() {
        int productId = 1;
        Product product = new Product();
        when(session.load(Product.class, productId)).thenReturn(product);

        boolean result = productDao.deletProduct(productId);

        assertTrue(result);
        verify(session).load(Product.class, productId);
        verify(session).delete(product);
}
}