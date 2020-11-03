<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>



<div class="table-responsive-sm">
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Email</th>
        <th scope="col">Date</th>
        <th scope="col">Total</th>
        <th scope="col">Payment</th>
        <th scope="col">Ship</th>
        <th scope="col">Status</th>
        <th scope="col">View</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${requestScope.allOrders}">
        <tr>
            <td>${order.order_id}</td>
            <td>${order.user.userEmail}</td>
            <td>${order.order_time}</td>

            <c:set var = "price_format" value = "${order.order_total}" />
            <td><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${price_format}" />$</td>

            <td>${order.order_pay}</td>
            <td>${order.order_ship}</td>
            <td>${order.order_status}</td>
            <td><a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/ViewOrder?role=admin&order_id=${order.order_id}">View</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>



