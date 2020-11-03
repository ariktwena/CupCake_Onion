<%--
  Created by IntelliJ IDEA.
  User: Tweny
  Date: 27/10/2020
  Time: 18.39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>

<div class="container">
    <%@include file="../includes/navigation.jsp"%>


        <br>
        <div>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">

                    <h2 class="text-center">Thank you for your order :)</h2>

                    <h4 class="text-center">Order number: ${sessionScope.order.order_id}</h4>
                    <br>

                    <h5>Delivery info</h5>
                    <div class="row" style="border-top: 1px solid #d9d9d9;">
                        <div class="col-md-4">
                            <p>Email: ${sessionScope.order.user.userEmail}</p>
                            <p>Address: ${sessionScope.order.shipping.shipping_address}</p>
                            <p>Extra info:</p>
                            <p style="margin-top: -15px">${sessionScope.order.order_info}</p>
                        </div>
                        <div class="col-md-4">
                            <p>Zip: ${sessionScope.order.shipping.shipping_zip}</p>
                            <p>City: ${sessionScope.order.shipping.shipping_city}</p>
                        </div>
                        <div class="col-md-4">
                            <p>Date: ${sessionScope.order.order_time}</p>
                            <p>Delivery: ${sessionScope.order.order_ship}</p>
                        </div>
                    </div>

                    <br>
                    <h5>Order Details</h5>

                    <div class="table-responsive-sm">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">Product</th>
                            <th scope="col">Topping</th>
                            <th scope="col">Bottom</th>
                            <th scope="col">Qty</th>
                            <th scope="col">Subtotal</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="cart_item" items="${sessionScope.order.cart.listOfCartItems}">
                            <tr>
                                <td class="align-middle"><img class="img-fluid" src="images/${cart_item.item.item_image}.jpg" alt="" width="40" ></td>
                                <td class="align-middle">${cart_item.item.item_name}</td>
                                <td class="align-middle">${cart_item.topping.topping_name}</td>
                                <td class="align-middle">${cart_item.bottom.bottom_name}</td>
                                <td class="align-middle">${cart_item.qty}</td>
                                <c:set var = "price_format" value = "${cart_item.subTotal}" />
                                <td class="align-middle"><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${price_format}" />$</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    </div>
                    <c:set var = "price_format" value = "${sessionScope.order.order_total}" />
                    <h5 class="text-center">Order total: <fmt:formatNumber type = "number" minFractionDigits = "2" value = "${price_format}" />$</h5>



                </div>
                <div class="col-md-2"></div>
            </div>
        </div>

<br>
<br>
<br>
<%@include file="../includes/footer.jsp"%>
