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

<form action="BulkHandlerUsers" method="post">


    <div class="row">
        <!--    Padding-left skal ændres i admin CSS files så den flugter med den anden tabel "sb-admin.css" -->
        <div id="bulkOptionContainer" class="col-sx-6 col-sm-6 col-md-3">
            <select class="form-control" name="selector_option" id="">
                <option value="">Select option</option>
                <option value="customer">Convert to Customer</option>
                <option value="admin">Convert to Admin</option>
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
            <th scope="col">Role</th>
            <th scope="col">Credit</th>
            <th scope="col">Orders</th>
            <th scope="col">Edit</th>
<%--            <th scope="col">Delete</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${requestScope.allUsersFromDB}">
            <tr>
                <td><input class='checkBoxes' type='checkbox' name='bulkArrayUserId' value='${user.id}'></td>
                <td>${user.id}</td>
                <td>${user.userEmail}</td>
                <td>${user.userRole}</td>
                <c:set var = "price_format" value = "${user.user_credit}" />
                <td><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${price_format}" />$</td>
                <td><a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/ShowCustomerOrderHistory?user_id=${user.id}">View</a></td>
                <td><a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/EditCustomer?user_id=${user.id}">Edit</a></td>
                <td>
                    <form action="DeleteCustomer" method="post">
                        <input type="hidden" name="user_id" value="${user.id}">
                        <button class="btn btn-danger btn-sm" type="submit">Delete</button>
                    </form>
<%--                    <a type="button" class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/DeleteCustomer?target=allCustomers&user_id=${user.id}">Delete</a>--%>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>

</form>

