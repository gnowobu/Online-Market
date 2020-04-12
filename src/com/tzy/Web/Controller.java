package com.tzy.Web;

import com.tzy.DAO.imp.GoodsDAOimp;
import com.tzy.Entity.Customer;
import com.tzy.Entity.Goods;
import com.tzy.Service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;

@WebServlet(name = "Controller",urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        GoodsService goodservice = new GoodsServiceimp();
        OrderService orderService = new OrderServiceimp();

        if (action.equals("reg")) {
            // -----------register------------
            String userid = request.getParameter("userid");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");

            // server authenticate
            List<String> errors = new ArrayList<>();
            if (userid == null || userid.equals("")) {
                errors.add("user id can't be empty！");
            }
            if (name == null || name.equals("")) {
                errors.add("user name can't be empty! ");
            }
            if (password == null || password.equals("")
                    || password2 == null
                    || !password.equals(password2)) {
                errors.add("check and retype password again!");
            }


            if (errors.size() > 0) { // authentication fails
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("customer_reg.jsp").forward(request, response);
            } else { // success
                Customer customer = new Customer();
                customer.setId(userid);
                customer.setName(name);
                customer.setPassword(password);
                customer.setAddress(address);
                if(phone == null || address.equals("")){
                    customer.setPhone(000);
                }
                else {
                    customer.setPhone(Integer.parseInt(phone));
                }
                // register
                try {
                    // success
                    CustomerService service = new CustomerServiceimp();
                    service.register(customer);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } catch (ServiceException e) {
                    // id registered already
                    errors.add("this id has been registered！");
                    request.setAttribute("errors", errors);
                    request.getRequestDispatcher("customer_reg.jsp").forward(request, response);
                }

            }

        }

        else if(action.equals("login")){
            CustomerService service = new CustomerServiceimp();
            List<String> errors = new ArrayList();

            String userid = request.getParameter("userid");
            String password = request.getParameter("password");

            Customer customer = new Customer();
            customer.setPassword(password);
            customer.setId(userid);

            if (service.login(customer)) { // login succeeded
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);
            request.getRequestDispatcher("main.jsp").forward(request, response);
            }
            else {
                errors.add("login failed");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

            }

        else if(action.equals("list")){

            List<Goods> goodslist = goodservice.queryAll();
            request.setAttribute("goodsList", goodslist);
            request.getRequestDispatcher("goods_list.jsp").forward(request, response);

        }

        else if(action.equals("detail")){
            String id = request.getParameter("id");
            Goods goods = goodservice.goodsDetail(Integer.parseInt(id));

            request.setAttribute("goods", goods);
            request.getRequestDispatcher("goods_detail.jsp").forward(request, response);

        }

        else if ("add".equals(action)) {
            //----------add cart------------
            int goodsid = Integer.parseInt(request.getParameter("id"));
            String goodsname = request.getParameter("name");
            Float price = new Float(request.getParameter("price"));

            // Cart is a List of Map. Each Map object represents a product
            //
            List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");

            if (cart == null) { // cart supposed to be null every first time
                cart = new ArrayList<Map<String, Object>>();
                request.getSession().setAttribute("cart", cart);
            }

            // cart has something already
            int flag = 0;
            for (Map<String, Object> item : cart) {
                Integer goodsid2 = (Integer) (item.get("goodsid"));
                if (goodsid == goodsid2) {

                    Integer quantity = (Integer) item.get("quantity");
                    quantity++;
                    item.put("quantity", quantity);

                    flag++;
                }
            }

            // nothing in cart
            if (flag == 0) {
                Map<String, Object> item = new HashMap<>();
                // item is a Map[id, name, quantity, price]
                item.put("goodsid", goodsid);
                item.put("goodsname", goodsname);
                item.put("quantity", 1);
                item.put("price", price);

                cart.add(item);
            }

            System.out.println(cart);

            String pagename = request.getParameter("pagename");

            if (pagename.equals("list")) {

                List<Goods> goodsList = goodservice.queryAll();
                request.setAttribute("goodsList", goodsList);
                request.getRequestDispatcher("goods_list.jsp").forward(request, response);

            } else if (pagename.equals("detail")) {

                Goods goods = goodservice.goodsDetail(goodsid);
                request.setAttribute("goods", goods);
                request.getRequestDispatcher("goods_detail.jsp").forward(request, response);
            }


        }

        else if ("cart".equals(action)) {
            //---------check cart---------
            // get the cart from session
            List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");

            double total = 0.0;

            if (cart != null) {
                for (Map<String, Object> item : cart) {

                    Integer quantity = (Integer) item.get("quantity");
                    Float price = (Float) item.get("price");
                    double subtotal = price * quantity;
                    total += subtotal;
                }
            }
            request.setAttribute("total", total);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }

        else if ("sub_ord".equals(action)) {
            //------------submit order-----------
            // cart from the session
            List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");
            for (Map<String, Object> item : cart) {
                Integer goodsid = (Integer) item.get("goodsid");
                String strquantity = request.getParameter("quantity_" + goodsid);
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(strquantity);
                } catch (Exception e) {
                }

                item.put("quantity", quantity);
            }

            // submit order
            int orderid = orderService.submitOrders(cart);
            request.setAttribute("orderid", orderid);
            request.getRequestDispatcher("order_finish.jsp").forward(request, response);
            // clear cart
            request.getSession().removeAttribute("cart");
         }


        else if(action.equals("main")){
            request.getRequestDispatcher("main.jsp").forward(request, response);

        }

        else if(action.equals("logout")){
            request.getSession().removeAttribute("customer");
            request.getSession().removeAttribute("cart");

            request.getRequestDispatcher("login.jsp").forward(request, response);

        }
        else if(action.equals("reg_init")){//it will be blocked by filter to register at the beginning.
            request.getRequestDispatcher("customer_reg.jsp").forward(request, response);

        }
    }
}