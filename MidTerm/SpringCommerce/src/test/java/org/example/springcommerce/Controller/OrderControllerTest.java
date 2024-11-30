package org.example.springcommerce.Controller;

import org.example.springcommerce.Model.AccountPrincipal;
import org.example.springcommerce.Model.Cart;
import org.example.springcommerce.Model.MyOrder;
import org.example.springcommerce.Model.Product;
import org.example.springcommerce.Service.CartService;
import org.example.springcommerce.Service.OrderService;
import org.example.springcommerce.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private CartService cartService;

    @Mock
    private ProductService productService;

    @Mock
    private AccountPrincipal accountPrincipal;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void testOrder_ShouldReturnOrderPage() throws Exception {
        Page<MyOrder> orderPage = new PageImpl<>(Collections.singletonList(new MyOrder()));
        when(orderService.getOrderByUserId(anyLong(), anyInt(), anyInt())).thenReturn(orderPage);
        when(accountPrincipal.getId()).thenReturn(1L);
        when(accountPrincipal.address()).thenReturn("Some Address");

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("username");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(get("/order")
                        .param("page", "0")
                        .param("size", "20")
                        .principal(() -> accountPrincipal.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("order"))
                .andExpect(model().attributeExists("orders", "currentPage", "totalPages", "role", "address", "username"));
    }

    @Test
    void testAddToOrder_ShouldReturnSuccess() throws Exception {
        Cart cart = new Cart();
        when(cartService.findById(anyLong())).thenReturn(cart);

        mockMvc.perform(post("/order/add")
                        .param("cartId", "1")
                        .param("address", "Some Address"))
                .andExpect(status().isOk())
                .andExpect(content().string("Add to order successfully"));

        verify(cartService, times(1)).deleteCart(anyLong());
        verify(orderService, times(1)).addToOrder(any(MyOrder.class));
    }

    @Test
    void testAddToOrderFromDetail_ShouldReturnSuccess() throws Exception {
        Product product = new Product();
        when(productService.findById(anyLong())).thenReturn(product);

        mockMvc.perform(post("/detail/order/add/{id}", 1L)
                        .param("quantity", "2")
                        .param("address", "Some Address")
                        .principal(() -> accountPrincipal.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("Add to order successfully"));

        verify(orderService, times(1)).addToOrder(any(MyOrder.class));
    }

    @Test
    void testUpdateOrder_ShouldReturnSuccess() throws Exception {
        mockMvc.perform(put("/ordermanagement/update/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Update order successfully"));

        verify(orderService, times(1)).updateOrder(eq(1L), eq("Delivered"));
    }

    @Test
    void testDeleteOrder_ShouldReturnSuccess() throws Exception {
        mockMvc.perform(delete("/order/delete/{id}", 1L)
                        .param("productId", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Delete order successfully"));

        verify(orderService, times(1)).deleteOrder(eq(1L), eq(2L));
    }
}