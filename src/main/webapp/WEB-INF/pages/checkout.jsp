<%--
  Created by IntelliJ IDEA.
  User: Tweny
  Date: 27/10/2020
  Time: 09.44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>

<div class="container">
    <%@include file="../includes/navigation.jsp"%>

        <br>
        <div class="text-center">
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <form class="form-checkout" action="SubmitOrder" method="POST" >

                        <input type="hidden" name="user_id" value="${sessionScope.user.id}">

                        <div class="row">
                            <div class="col-md-6">
                                <h2 class="h3 mb-4 font-weight-normal">Delivery Info</h2>
                                <div class="form-group">
                                    <select class="form-control" id="shippingOption" name="shippingOption">
                                        <option value="store">Collect order at the store</option>
                                        <option value="private">Different shipping address</option>
                                    </select>
                                </div>
                                <label for="inputaddress">Different Shipping address</label>
                                <div class="mb-1">
                                    <label for="inputaddress" class="sr-only">Address</label>
                                    <input type="text" name="address" id="inputaddress" class="form-control" placeholder="Enter address" autofocus="">
                                </div>
                                <div class="mb-1">
                                    <label for="inputzip" class="sr-only">Zip Code</label>
                                    <input type="number" name="zip" id="inputzip" class="form-control" placeholder="Enter zip code" autofocus="">
                                </div>
                                <div class="mb-1">
                                    <label for="inputcity" class="sr-only">City</label>
                                    <input type="text" name="city" id="inputcity" class="form-control" placeholder="Enter city" autofocus="">
                                </div>
                            </div>

                            <div class="col-md-6">
                                <c:set var = "price_format" value = "${requestScope.cartTotal}" />
                                <h2 class="h3 mb-4 font-weight-normal">Payment Option: <fmt:formatNumber type = "number" minFractionDigits = "2" value = "${price_format}" />$</h2>
                                <div class="form-group">
                                    <!--                                <label for="exampleFormControlSelect1">Example select</label>-->
                                    <select class="form-control" id="paymentOption" name="paymentOption">
                                        <!--                                    <option disabled>Choose your payment type</option>-->
<%--                                        <option value="credit" disabled>Use store credit xxx$</option>--%>
<%--                                        <option value="cash">Payment on delivery</option>--%>
                                        <c:choose>
                                            <c:when test="${sessionScope.user.user_credit >= requestScope.cartTotal}">
                                                <c:set var = "price_format" value = "${sessionScope.user.user_credit}" />
                                                <option value="credit">Use store credit <fmt:formatNumber type = "number" minFractionDigits = "2" value = "${price_format}" />$</option>
                                                <option value="cash">Payment on delivery</option>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var = "price_format" value = "${sessionScope.user.user_credit}" />
                                                <option value="credit" disabled>Use store credit <fmt:formatNumber type = "number" minFractionDigits = "2" value = "${price_format}" />$</option>
                                                <option value="cash">Payment on delivery</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </div>
                                <div class="form-group" style="margin-top: -5px">
                                <h2 class="h3 mb-3 font-weight-normal">Extra Info</h2>
                                    <!--                                <label for="exampleFormControlTextarea1">Extra Info</label>-->
                                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="4" name="extra_info"></textarea>
                                </div>
                            </div>
                        </div>

                        <br>

                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <button class="btn btn-lg btn-primary btn-block" type="submit">Confirm Order</button>
                            </div>
                            <div class="col-md-4"></div>
                        </div>
                    </form>
                    <div>
                        <c:if test ="${requestScope.errorMessage != null}">
                            <div class="alert alert-danger" style="padding-bottom: inherit;">
                                <p>${requestScope.errorMessage}</p>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="col-md-1"></div>
            </div>
        </div>

<br>
<br>
<br>
<%@include file="../includes/footer.jsp"%>