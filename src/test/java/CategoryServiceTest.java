
import com.jtspringproject.JtSpringProject.dao.categoryDao;
import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.services.categoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class CategoryServiceTest {

    @Mock
    private categoryDao categoryDao;

    @InjectMocks
    private categoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }@Test
    void testAddCategoryWithEmptyName() {
        String categoryName = "";
        Category category = new Category();
        category.setName(categoryName);
        when(categoryDao.addCategory(categoryName)).thenReturn(null);
        Category result = categoryService.addCategory(categoryName);
        assertNull(result);
        verify(categoryDao, times(0)).addCategory(categoryName);
    }@Test
    void testGetCategoryWithInvalidId(){
        int invalidCategoryId = -1;
        when(categoryDao.getCategory(invalidCategoryId)).thenReturn(null);
        Category result = categoryService.getCategory(invalidCategoryId);
        assertNull(result);
        verify(categoryDao, times(1)).getCategory(invalidCategoryId);
    }@Test
    void testDeleteCategoryFailure(){
        int categoryId = 1;
        when(categoryDao.deletCategory(categoryId)).thenReturn(false);
        boolean result = categoryService.deleteCategory(categoryId);
        assertFalse(result);
        verify(categoryDao, times(1)).deletCategory(categoryId);
    }

    @Test
    void testUpdateCategoryWithEmptyName() {
        int categoryId = 1;
        String newName = "";
        Category updatedCategory = new Category();
        updatedCategory.setId(categoryId);
        updatedCategory.setName(newName);
        when(categoryDao.updateCategory(categoryId, newName)).thenReturn(null);
        Category result = categoryService.updateCategory(categoryId, newName);
        assertNull(result);
        verify(categoryDao, times(1)).updateCategory(categoryId, newName);
    }
    @Test
    void testAddCategory() {
        String categoryName = "TestCategory";
        Category category = new Category();
        category.setName(categoryName);
        when(categoryDao.addCategory(categoryName)).thenReturn(category);
        Category result = categoryService.addCategory(categoryName);
        assertEquals(category, result);
        verify(categoryDao, times(1)).addCategory(categoryName);
    }
    @Test
    void testGetCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        when(categoryDao.getCategories()).thenReturn(categories);
        List<Category> result = categoryService.getCategories();
        assertEquals(categories, result);
        verify(categoryDao, times(1)).getCategories();
    }
    @Test
    void testUpdateCategory() {
        int categoryId = 1;
        String newName = "UpdatedCategoryName";
        Category updatedCategory = new Category();
        updatedCategory.setId(categoryId);
        updatedCategory.setName(newName);
        when(categoryDao.updateCategory(categoryId, newName)).thenReturn(updatedCategory);
        Category result = categoryService.updateCategory(categoryId, newName);
        assertEquals(updatedCategory, result);
        verify(categoryDao, times(1)).updateCategory(categoryId, newName);
    }
}
