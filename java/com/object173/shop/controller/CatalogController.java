package com.object173.shop.controller;

import com.object173.shop.dao.CategoryDaoImpl;
import com.object173.shop.model.*;
import com.object173.shop.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@SessionAttributes(value = "currentCategory")
public class CatalogController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);
    private int currentCategory=1;

    private UserService userService;
    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService)
    {
        this.userService=userService;
    }

    private OrderService orderService;
    @Autowired(required = true)
    @Qualifier(value = "orderService")
    public void setOrderService(OrderService orderService)
    {
        this.orderService=orderService;
    }

    private CartService cartService;
    @Autowired(required = true)
    @Qualifier(value = "cartService")
    public void setCartService(CartService cartService)
    {
        this.cartService=cartService;
    }

    private ProductService productService;

    @Autowired(required = true)
    @Qualifier(value = "productService")
    public void setProductService(ProductService productService)
    {
        this.productService=productService;
    }

    private CategoryService categoryService;

    @Autowired(required = true)
    @Qualifier(value = "categoryService")
    public void setCategoryService(CategoryService categoryService)
    {
        this.categoryService=categoryService;
    }

    private void loadData(int id, Model model)
    {
        Category category = this.categoryService.getCategory(currentCategory);

        if(currentCategory == 1 && category == null) {
            category = new Category(1, Category.FIRST);
            categoryService.addCategory(category);
        }

        model.addAttribute("currentCategory", category);

        category = new Category(); category.setParent_id(currentCategory);
        model.addAttribute("category", category);
        model.addAttribute("listCategory", this.categoryService.listCategory());
        Product product = new Product(); product.setCategory_id(currentCategory);
        model.addAttribute("product", product);
        model.addAttribute("listProduct", this.productService.listProduct(id));

        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"))
            model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @RequestMapping(value = "catalog", method = RequestMethod.GET)
    public String listProduct(Model model)
    {
        loadData(currentCategory, model);

        return "catalog";
    }

    @RequestMapping(value = "catalog/{id}")
    public String listProduct(@PathVariable("id") int id, Model model)
    {
        currentCategory=id;

        loadData(id, model);

        return "catalog";
    }

    public int getUserId()
    {
        User user = this.userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if(user==null) return -1;
        return user.getId();
    }

    @RequestMapping(value = "/addItemToBasket", method = RequestMethod.POST)
    public String showMessage(@RequestParam int productId,@RequestParam float productCost, @RequestParam int productCount, Model model) {
        int id=getUserId();
        if(id>0) {
            Cart cart = new Cart(id, productId, productCost, productCount);
            this.cartService.addCart(cart);
        }
        return "redirect:/catalog/"+currentCategory;
    }

    private void loadCartData(Model model)
    {
        int id=getUserId();
        if(id<1) return;

        List<Cart> carts = this.cartService.listCart(id);
        ArrayList<CartItem> itemList = new ArrayList<CartItem>();
        float cost=0;
        for(Cart cart:carts) {
            cost+=cart.getCost();
            itemList.add(new CartItem(cart, this.productService.getProduct(cart.getProduct_id())));
        }
        model.addAttribute("itemList",itemList);
        model.addAttribute("orderList",this.orderService.listOrder(id));
        model.addAttribute("sumCost",cost);
    }

    @RequestMapping(value = "cartPage", method = RequestMethod.GET)
    public String cartPage(Model model)
    {
        loadCartData(model);
        return "cartPage";
    }

    @RequestMapping("/removeCart/{id}")
    public String removeCart(@PathVariable("id") int id)
    {
        this.cartService.removeCart(id);
        return "redirect:/cartPage";
    }

    @RequestMapping("/createOrder")
    public String createOrder(Model model)
    {
        int id=getUserId();
        if(id>0) {
            List<Cart> carts = this.cartService.listCart(id);
            if(carts.size()>0) {
                this.orderService.addOrder(id, carts);
                this.cartService.removeAll(id);
            }
        }
        loadCartData(model);
        return "cartPage";
    }

    @RequestMapping("/orderCancel/{id}")
    public String cancelOrder(@PathVariable("id") int id)
    {
        Order order = this.orderService.getOrder(id);
        order.setStatus("canceled");
        this.orderService.updateOrder(order);
        return "redirect:/cartPage";
    }

    @RequestMapping("/orderInfo/{id}")
    public String orderInfo(@PathVariable("id") int id, Model model)
    {
        Order order = this.orderService.getOrder(id);
        if(order==null) return "redirect:/cartPage";
        model.addAttribute("order",order);
        List<OrderItem> items = this.orderService.listItem(id);
        List<CartItem> itemList = new ArrayList<CartItem>();
        for(OrderItem item:items)
            itemList.add(new CartItem(item, this.productService.getProduct(item.getProduct_id())));

        model.addAttribute("items",itemList);
        return "orderInfo";
    }

}
