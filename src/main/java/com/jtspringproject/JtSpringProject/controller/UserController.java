package com.jtspringproject.JtSpringProject.controller;

import com.jtspringproject.JtSpringProject.models.Cart;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.jtspringproject.JtSpringProject.services.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.jtspringproject.JtSpringProject.services.userService;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.services.cartService;



@Controller
public class UserController{

	@Autowired
	private userService userService;

	@Autowired
	private productService productService;

	@GetMapping("/register")
	public String registerUser()
	{
		return "register";
	}
	@PostMapping("/register")
	public String processRegistration(@ModelAttribute User user, Model model) {

		if (user.getAddress() == null || user.getAddress().isEmpty()) {
			model.addAttribute("msg", "Please enter your address");
			return "register";
		}
		user.setRole("ROLE_NORMAL");
		this.userService.addUser(user);

		return "redirect:/";
	}

	@GetMapping("/buy")
	public String buy()
	{
		return "buy";
	}


	@GetMapping("/user/profile")
	public ModelAndView userProfile(@CookieValue(value = "username", defaultValue = "") String username) {
		ModelAndView mView = new ModelAndView("profile");

		User user = userService.getUserByUsername(username);

		if (user != null) {
			mView.addObject("user", user);
		} else {
			mView.addObject("msg", "User not found");
		}
		return mView;
	}

	@GetMapping("/")
	public String userlogin(Model model) {

		return "userLogin";
	}
	@RequestMapping(value = "userloginvalidate", method = RequestMethod.POST)
	public ModelAndView userlogin(
			@RequestParam("username") String username,
			@RequestParam("password") String pass,
			Model model,
			HttpServletResponse res)
	{
		User u = this.userService.checkLogin(username, pass);

		if (u != null && isPasswordCorrect(u.getPassword(), pass)) {
			res.addCookie(new Cookie("username", u.getUsername()));
			ModelAndView mView = new ModelAndView("index");
			mView.addObject("user", u);
			mView.addObject("profileLink", "/user/profile");

			List<Product> products = this.productService.getProducts();

			if (products.isEmpty()) {
				mView.addObject("msg", "No products are available");
			} else {
				mView.addObject("products", products);
			}
			return mView;
		} else {
			ModelAndView mView = new ModelAndView("userLogin");
			mView.addObject("error", "Incorrect username or password");
			return mView;
		}
	}

	private boolean isPasswordCorrect(String storedHashedPassword, String enteredPassword) {
		return passwordEncoder.matches(enteredPassword, storedHashedPassword);
	}
}


	@GetMapping("/user/products")
	public ModelAndView getproduct() {

		ModelAndView mView = new ModelAndView("uproduct");

		List<Product> products = this.productService.getProducts();

		if(products.isEmpty()) {
			mView.addObject("msg","No products are available");
		}else {
			mView.addObject("products",products);
		}

		return mView;
	}
	@RequestMapping(value = "newuserregister", method = RequestMethod.POST)
	public String newUseRegister(@ModelAttribute User user)
	{

		System.out.println(user.getEmail());
		user.setRole("ROLE_NORMAL");
		this.userService.addUser(user);

		return "redirect:/";
	}



	   //for Learning purpose of model
		@GetMapping("/test")
		public String Test(Model model)
		{
			System.out.println("test page");
			model.addAttribute("author","jay gajera");
			model.addAttribute("id",40);

			List<String> friends = new ArrayList<String>();
			model.addAttribute("f",friends);
			friends.add("xyz");
			friends.add("abc");

			return "test";
		}

		// for learning purpose of model and view ( how data is pass to view)

		@GetMapping("/test2")
		public ModelAndView Test2()
		{
			System.out.println("test page");
			//create modelandview object
			ModelAndView mv=new ModelAndView();
			mv.addObject("name","jay gajera 17");
			mv.addObject("id",40);
			mv.setViewName("test2");

			List<Integer> list=new ArrayList<Integer>();
			list.add(10);
			list.add(25);
			mv.addObject("marks",list);
			return mv;


		}


//	@GetMapping("carts")
//	public ModelAndView  getCartDetail()
//	{
//		ModelAndView mv= new ModelAndView();
//		List<Cart>carts = cartService.getCarts();
//	}

}
