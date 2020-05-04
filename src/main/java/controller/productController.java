/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ProductService;
import Model.Product;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author brend
 */


@Controller
@RequestMapping("/product")
@SessionAttributes("user")
public class productController  {
     
    
    @Autowired
     ProductService service;
    
    
      @GetMapping(value="/processlogin")
    public String processLogin(HttpSession sess, HttpServletRequest request)
    {
        System.out.println("HEREE");
    if(request.isUserInRole("Admin")){
      sess.setAttribute("uname", getLoggedInUserName());
       
            return "redirect:/product/viewall";
        }
    else if (request.isUserInRole("User"))
    {
         sess.setAttribute("uname", getLoggedInUserName());
            return "redirect:/product/viewall";
        
        
    }
      else if (request.isUserInRole("SuperAdmin"))
    {
         sess.setAttribute("uname", getLoggedInUserName());
             return "redirect:/product/viewall";
        
        
    }
       else
        {
           
            throw new ForbiddenException();
        }
    }
      private String getLoggedInUserName() {
       Object principal = SecurityContextHolder.getContext()
               .getAuthentication().getPrincipal();
       if(principal instanceof UserDetails){
           return ((UserDetails)principal).getUsername();
           
       }
      return principal.toString();
    }
      
      
      
      
      
      @RequestMapping(value = "/viewall")
    public ModelAndView viewall(HttpSession sess, HttpServletRequest request) {
        
          if(request.isUserInRole("Admin")){
      sess.setAttribute("uname", getLoggedInUserName());
       
           List <Product> b = service.getAllProducts();
        
        return new ModelAndView("/allProducts", "productList", b);
        }
    else if (request.isUserInRole("User"))
    {
         sess.setAttribute("uname", getLoggedInUserName());
          List <Product> b = service.getAllProducts();
        
        return new ModelAndView("/allProducts", "productList", b);
        
        
    }
      else if (request.isUserInRole("SuperAdmin"))
    {
         sess.setAttribute("uname", getLoggedInUserName());
            List <Product> b = service.getAllProducts();
        
        return new ModelAndView("/allProducts", "productList", b);
        
        
    }
       else
        {
           List <Product> b = service.getAllProducts();
        
        return new ModelAndView("/allProducts", "productList", b);
            
        }
        
        
      
    }
    
    
    
    
       @GetMapping(value = "/addProduct")
    public ModelAndView add( HttpServletRequest request) {
        if(request.isUserInRole("SuperAdmin"))
        {
        return new ModelAndView("/addProduct", "Product", new Product());
        }
        else{
            throw new ForbiddenException();
        }
    }
    @PostMapping(value = "/add")
    public ModelAndView addProduct( @ModelAttribute("Product") Product product, ModelMap model, BindingResult result) {
        boolean success=service.addAProduct(product);
        if(result.hasErrors())
        {
            System.out.println("Some Errors");
            return new ModelAndView("redirect:/product/addProduct","product", product);
        
        }  
        else{
            System.out.println(" Result"+ result.getAllErrors());
            return new ModelAndView("redirect:/product/viewall");
            
        }
//        if(success==true)
//        {
//        
//         return new ModelAndView("redirect:/product/viewall");
//        }
//      
      
    }
       @GetMapping(value = "/Delete{code}")
    public ModelAndView deleteProduct(@QueryParam("code") String code, HttpServletRequest request) {
        if(request.isUserInRole("SuperAdmin"))
        {
        service.deleteAProduct(code);
        
        return new ModelAndView("redirect:/product/viewall");
        }
        else if(request.isUserInRole("Admin"))
        {
          service.deleteAProduct(code);
        
        return new ModelAndView("redirect:/product/viewall");
        
        
        }
        else{
            
            throw new ForbiddenException();
        }
    }
}