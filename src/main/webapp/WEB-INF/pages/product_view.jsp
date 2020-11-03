<%--
  Created by IntelliJ IDEA.
  User: Tweny
  Date: 21/10/2020
  Time: 16.38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>

<div class="container">
    <%@include file="../includes/navigation.jsp"%>


        <h2 class="mt-4 mb-5 text-center">Our Delicious CupCakes</h2>

        <div class="row">
<%--            <div class="col-md-3 mb-4">--%>
<%--                <div class="item-box">--%>
<%--                    <div class="the-box">--%>
<%--                        <a href="#"><img class="d-block w-100 img-fluid the-image" src="${pageContext.request.contextPath}/images/CupCake_1.jpg" alt="Picture of cupcake"></a>--%>
<%--                    </div>--%>
<%--                    <div class="text-center">--%>
<%--                        <h5>CupCake Name</h5>--%>
<%--                        <h6>Price: 32$</h6>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
            <c:forEach var="item" items="${requestScope.getAllItemsFromDB}">
                <div class="col-md-3 mb-4">
                    <div class="item-box">
                        <div class="the-box">
                            <a href="ItemPage?item_id=${item.item_id}"><img class="d-block w-100 img-fluid the-image" src="${pageContext.request.contextPath}/images/${item.item_image}.jpg" alt="Picture of cupcake"></a>
                        </div>
                        <div class="text-center">
                            <h5>${item.item_name}</h5>
                            <c:set var = "price_format" value = "${item.item_price}" />
                            <h6>Price: <fmt:formatNumber type = "number" minFractionDigits = "2" value = "${price_format}" />$</h6>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <br>
        <br>
        <br>

<%@include file="../includes/footer.jsp"%>
