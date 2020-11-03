<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>



<div>
    <c:if test ="${requestScope.errorMessage != null}">
        <div class="alert alert-danger" style="padding-bottom: inherit;">
            <p>${requestScope.errorMessage}</p>
        </div>
    </c:if>
</div>
<div>
    <c:if test ="${requestScope.successMessage != null}">
        <div class="alert alert-success" style="padding-bottom: inherit;">
            <p>${requestScope.successMessage}</p>
        </div>
    </c:if>
</div>

<form action="BulkHandlerOrders" method="post">


    <div class="row">
        <!--    Padding-left skal ændres i admin CSS files så den flugter med den anden tabel "sb-admin.css" -->
        <div id="bulkOptionContainer" class="col-sx-6 col-sm-6 col-md-3">
            <select class="form-control" name="selector_option" id="">
                <option value="">Select option</option>
                <option value="pending">Change To Pending</option>
                <option value="delivered">Change To Delivered</option>
                <option value="delete">Delete Selected</option>
            </select>
            <p></p>
        </div>
        <!--    Padding-left skal ændres i admin CSS files så den flugter med den anden tabel "sb-admin.css" -->
        <div id="applyChangesPost" class="col-sx-6 col-sm-6 col-md-2">
            <input type="submit" name="submit_form" class="btn btn-success" value="Apply">
        </div>
    </div>

    <div class="table-responsive-sm">
    <table class="table table-striped">
        <thead>
        <tr>
            <th><input id="selectAllBoxes" type="checkbox"></th>
            <th scope="col">Id</th>
            <th scope="col">Email</th>
            <th scope="col">Date</th>
            <th scope="col">Total</th>
            <th scope="col">Payment</th>
            <th scope="col">Ship</th>
            <th scope="col">Status</th>
            <th scope="col">View</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${requestScope.allOrders}">
            <tr>
                <td><input class='checkBoxes' type='checkbox' name='bulkArrayOrderId' value='${order.order_id}'></td>
                <td>${order.order_id}</td>
                <td>${order.user.userEmail}</td>
                <td>${order.order_time}</td>

                <c:set var = "price_format" value = "${order.order_total}" />
                <td><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${price_format}" />$</td>

                <td>${order.order_pay}</td>
                <td>${order.order_ship}</td>
                <td>${order.order_status}</td>
                <td><a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/ViewOrder?role=admin&order_id=${order.order_id}">View</a></td>
                <td>
                    <form action="DeleteOrder" method="post">
                        <input type="hidden" name="order_id" value="${order.order_id}">
                        <button class="btn btn-danger btn-sm" type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>

</form>

