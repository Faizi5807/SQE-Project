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
@Transactional
@Rollback(true)

class ProductDaoTest 
{
    @Autowired
    private productDao productDao;
    @Autowired
    private SessionFactory sessionFactory;
    @Test
void testGetProductsByCategory() {
    // Arrange
    Category category = new Category();
    // Set necessary fields for the category
    Product testProduct1 = new Product();
    testProduct1.setCategory(category);
    productDao.addProduct(testProduct1);
    Product testProduct2 = new Product();
    testProduct2.setCategory(category);
    productDao.addProduct(testProduct2);
    List<Product> productsByCategory = productDao.getProductsByCategory(category);
    assertEquals(2, productsByCategory.size());
    assertTrue(productsByCategory.contains(testProduct1));
    assertTrue(productsByCategory.contains(testProduct2));
}


@Test
void testGetProductsWithoutCategory() 
{
    Product testProduct1 = new Product();
    productDao.addProduct(testProduct1);
    Product testProduct2 = new Product();
    productDao.addProduct(testProduct2);
    List<Product> productsWithoutCategory = productDao.getProductsWithoutCategory();
    assertEquals(2, productsWithoutCategory.size());
    assertTrue(productsWithoutCategory.contains(testProduct1));
    assertTrue(productsWithoutCategory.contains(testProduct2));
}

    @Test
    void testGetProducts() 
    {
        List<Product> initialProducts = productDao.getProducts();
        Product testProduct = new Product();
        // Set necessary fields for the product
        productDao.addProduct(testProduct);
        List<Product> productsAfterAddition = productDao.getProducts();

        assertEquals(initialProducts.size() + 1, productsAfterAddition.size());
        assertTrue(productsAfterAddition.contains(testProduct));
    }

    @Test
    void testAddProduct() {
        Product product = new Product();
        // Set necessary fields for the product
        productDao.addProduct(product);
        assertNotNull(product.getId());
    }

    @Test
    void testUpdateProduct() {
        Product testProduct = new Product();
        // Set necessary fields for the product
        productDao.addProduct(testProduct);
        Product updatedProduct = productDao.updateProduct(testProduct);
        
    }

    @Test
    void testDeleteProduct() {
        Product testProduct = new Product();
        // Set necessary fields for the product
        productDao.addProduct(testProduct);

        assertTrue(productDao.deletProduct(testProduct.getId()));
        assertNull(sessionFactory.getCurrentSession().get(Product.class, testProduct.getId()));
    }
}
