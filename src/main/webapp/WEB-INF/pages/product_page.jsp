<%--
  Created by IntelliJ IDEA.
  User: Tweny
  Date: 21/10/2020
  Time: 16.39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>

<div class="container">
    <%@include file="../includes/navigation.jsp"%>

        <h2 class="mt-4 mb-5 text-center">CupCake: ${requestScope.item.item_name}</h2>

        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-4">
                <!--            <br>-->
                <br>
                <img class="d-block w-100 img-fluid" src="images/${requestScope.item.item_image}.jpg" alt="${requestScope.item.item_name}">
            </div>
            <div class="col-md-4">

                <form class="form-product" action="AddToCart" method="post" >

                    <input type="hidden" name="item_id" value="${requestScope.item.item_id}">

                    <h5>Description</h5>
                    <p>${requestScope.item.item_des}</p>
                    <c:set var = "price_format" value = "${requestScope.item.item_price}" />
                    <h5 class="mb-3">Price: <fmt:formatNumber type = "number" minFractionDigits = "2" value = "${price_format}" />$</h5>

                    <div class="form-group">
                        <label for="toppingSelect">Choose Topping</label>
                        <select class="form-control" id="toppingSelect" name="topping_id">
                            <c:forEach var="topping" items="${requestScope.listOfToppings}">
                                <c:set var = "price_format" value = "${topping.topping_price}" />
                                <option value="${topping.topping_id}">${topping.topping_name} + <fmt:formatNumber type = "number" minFractionDigits = "2" value = "${price_format}" />$</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="bottomSelect">Choose Bottom</label>
                        <select class="form-control" id="bottomSelect" name="bottom_id">
                            <c:forEach var="bottom" items="${requestScope.listOfBottoms}">
                                <c:set var = "price_format" value = "${bottom.bottom_price}" />
                                <option value="${bottom.bottom_id}">${bottom.bottom_name} + <fmt:formatNumber type = "number" minFractionDigits = "2" value = "${price_format}" />$</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="qty">Choose Quantity</label>
                        <select class="form-control" id="qty" name="qty">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                        </select>
                    </div>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">Add to Cart</button>
                </form>


                <div>
                    <c:if test ="${requestScope.errorMessage != null}">
                        <div class="alert alert-danger" style="padding-bottom: inherit;">
                            <p>${requestScope.errorMessage}</p>
                        </div>
                    </c:if>
                </div>

            </div>
            <div class="col-md-2"></div>
        </div>

        <br>
        <br>

<%@include file="../includes/footer.jsp"%>
