package com.object173.shop.controller;

import com.object173.shop.model.*;
import com.object173.shop.service.CategoryService;
import com.object173.shop.service.OrderService;
import com.object173.shop.service.ProductService;
import com.object173.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    private int currentCategory=1;

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
    public void setUserService(CategoryService categoryService)
    {
        this.categoryService=categoryService;
    }

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

    private void loadCatalogData(int id, Model model)
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

    @RequestMapping(value = "adminCatalog", method = RequestMethod.GET)
    public String listProduct(Model model)
    {
        loadCatalogData(1, model);

        return "admin/adminCatalog";
    }

    @RequestMapping(value = "adminCatalog/{id}")
    public String listProduct(@PathVariable("id") int id, Model model)
    {
        currentCategory=id;

        loadCatalogData(id, model);

        return "admin/adminCatalog";
    }

    @RequestMapping(value = "/adminCatalog/addProduct", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("product") Product product)
    {
        try {
            if (product.getId() == 0)
                this.productService.addProduct(product);
            else
                this.productService.updateProduct(product);
        }
        catch(Exception ex) {}

        return "redirect:/adminCatalog/"+currentCategory;
    }

    @RequestMapping(value = "/adminCatalog/addCategory", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute("category") Category category)
    {
        try {
            if (category.getId() == 0)
                this.categoryService.addCategory(category);
            else
                this.categoryService.updateCategory(category);
        }
        catch(Exception ex) {}

        return "redirect:/adminCatalog/"+currentCategory;
    }

    @RequestMapping("/removeProduct/{id}")
    public String removeProduct(@PathVariable("id") int id)
    {
        this.productService.removeProduct(id);
        return "redirect:/adminCatalog/"+currentCategory;
    }

    @RequestMapping("/removeCategory/{id}")
    public String removeCategory(@PathVariable("id") int id)
    {
        this.categoryService.removeCategory(id);
        return "redirect:/adminCatalog/"+currentCategory;
    }

    @RequestMapping("/editProduct/{id}")
    public String editProduct(@PathVariable("id") int id, Model model)
    {
        loadCatalogData(currentCategory, model);
        model.addAttribute("product", this.productService.getProduct(id));

        return "admin/adminCatalog";
    }

    @RequestMapping("editCategory/{id}")
    public String editCatalog(@PathVariable("id") int id, Model model)
    {
        loadCatalogData(currentCategory, model);
        model.addAttribute("category", this.categoryService.getCategory(id));

        return "admin/adminCatalog";
    }

    private void loadUserData(Model model)
    {
        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"))
            model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());

        model.addAttribute("listUser",this.userService.listUser());
        model.addAttribute("user",new User());
    }

    @RequestMapping(value = "adminUsers", method = RequestMethod.GET)
    public String listUser(Model model)
    {
        loadUserData(model);
        return "admin/adminUsers";
    }

    @RequestMapping(value = "/adminUsers/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user)
    {
        try {
            if (user.getId() == 0)
                this.userService.addUser(user);
            else
                this.userService.updateUser(user);
        }
        catch(Exception ex) {}

        return "redirect:/adminUsers";
    }

    @RequestMapping("/removeUser/{id}")
    public String removeUser(@PathVariable("id") int id)
    {
        this.userService.removeUser(id);
        return "redirect:/adminUsers";
    }

    @RequestMapping("editUser/{id}")
    public String editUser(@PathVariable("id") int id, Model model)
    {
        loadUserData(model);
        model.addAttribute("user", this.userService.getUser(id));

        return "admin/adminUsers";
    }

    private void loadOrdersData(Model model)
    {
        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"))
            model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());

        List<Order> orders =  this.orderService.listOrder();
        List<OrderModel> orModel = new ArrayList<OrderModel>();
        for(Order order:orders)
            orModel.add(new OrderModel(this.userService.getUser(order.getUser_id()).getUsername(),order));

        model.addAttribute("orderList",orModel);
    }

    @RequestMapping(value = "adminOrders", method = RequestMethod.GET)
    public String adminOrders(Model model)
    {
        loadOrdersData(model);

        return "admin/adminOrders";
    }

    @RequestMapping("/adminOrderRemove/{id}")
    public String removeOrder(@PathVariable("id") int id)
    {
        this.orderService.removeOrder(id);
        return "redirect:/adminOrders";
    }

    @RequestMapping("/adminOrderEdit/{id}")
    public String orderEdit(@PathVariable("id") int id, Model model)
    {
        Order order = this.orderService.getOrder(id);
        if(order==null) return "redirect:/admin/adminOrders";
        model.addAttribute("order",new OrderModel(this.userService.getUser(order.getUser_id()).getUsername(),order));
        List<OrderItem> items = this.orderService.listItem(id);
        List<CartItem> itemList = new ArrayList<CartItem>();
        for(OrderItem item:items)
            itemList.add(new CartItem(item, this.productService.getProduct(item.getProduct_id())));
        model.addAttribute("items", itemList);
        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"))
            model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        return "admin/adminOrderEdit";
    }

    @RequestMapping("/adminOrderItemRemove/{id}")
    public String removeOrderItem(@PathVariable("id") int id)
    {
        OrderItem item = this.orderService.getOrderItem(id);
        Order order=this.orderService.getOrder(item.getOrder_id());
        order.setCost(order.getCost()-item.getCost());
        this.orderService.removeOrderItem(id);
        this.orderService.updateOrder(order);
        return "redirect:/adminOrderEdit/"+order.getId();
    }

    @RequestMapping(value = "/setOrderStatus", method = RequestMethod.POST)
    public String showMessage(@RequestParam int orderId,@RequestParam String status, Model model) {
        Order order= this.orderService.getOrder(orderId);
        order.setStatus(status);
        this.orderService.updateOrder(order);

        return "redirect:/adminOrderEdit/"+orderId;
    }
}
