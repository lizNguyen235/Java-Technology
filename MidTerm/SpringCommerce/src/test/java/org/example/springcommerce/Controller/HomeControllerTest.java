package org.example.springcommerce.Controller;

import org.example.springcommerce.Model.Product;
import org.example.springcommerce.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class HomeControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private HomeController homeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    void testAccessHomePage() throws Exception {
        List<Product> products = Arrays.asList(new Product());
        Page<Product> productPage = new PageImpl<>(products);

        when(productService.getProducts(0, 20)).thenReturn(productPage);
        when(productService.getCategories()).thenReturn(Arrays.asList("Category1", "Category2"));
        when(productService.getBrands()).thenReturn(Arrays.asList("Brand1", "Brand2"));
        when(productService.getColors()).thenReturn(Arrays.asList("Color1", "Color2"));

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("username");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(get("/home")
                        .param("page", "0")
                        .param("size", "20"))
                .andExpect(status().isOk())
                .andExpect(view().name("homepage"))
                .andExpect(model().attributeExists("categories", "brands", "colors", "productPage", "currentPage", "totalPages", "role", "username"))
                .andExpect(model().attribute("username", "username"))
                .andExpect(model().attribute("categories", Arrays.asList("Category1", "Category2")))
                .andExpect(model().attribute("brands", Arrays.asList("Brand1", "Brand2")))
                .andExpect(model().attribute("colors", Arrays.asList("Color1", "Color2")));
    }

    @Test
    void testFilterProducts() throws Exception {
        List<Product> products = Arrays.asList(new Product());
        Page<Product> productPage = new PageImpl<>(products);

        when(productService.findAllByCondition("Brand1", "Color1", "Category1", "50", "200", 0, 20)).thenReturn(productPage);
        when(productService.getCategories()).thenReturn(Arrays.asList("Category1", "Category2"));
        when(productService.getBrands()).thenReturn(Arrays.asList("Brand1", "Brand2"));
        when(productService.getColors()).thenReturn(Arrays.asList("Color1", "Color2"));

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("username");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(get("/home/filter")
                        .param("page", "0")
                        .param("size", "20")
                        .param("category", "Category1")
                        .param("brand", "Brand1")
                        .param("color", "Color1")
                        .param("minPrice", "50")
                        .param("maxPrice", "200"))
                .andExpect(status().isOk())
                .andExpect(view().name("homepage"))
                .andExpect(model().attributeExists("categories", "brands", "colors", "productPage", "currentPage", "totalPages", "role", "username", "selectedCategory", "selectedBrand", "selectedColor", "selectedMinPrice", "selectedMaxPrice"))
                .andExpect(model().attribute("username", "username"))
                .andExpect(model().attribute("selectedCategory", "Category1"))
                .andExpect(model().attribute("selectedBrand", "Brand1"))
                .andExpect(model().attribute("selectedColor", "Color1"))
                .andExpect(model().attribute("selectedMinPrice", "50"))
                .andExpect(model().attribute("selectedMaxPrice", "200"));
    }
}