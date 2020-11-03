<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="table-responsive-sm">
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Email</th>
        <th scope="col">Role</th>
        <th scope="col">Credit</th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td>${sessionScope.user.userEmail}</td>
            <td>${sessionScope.user.userRole}</td>
            <c:set var = "price_format" value = "${sessionScope.user.user_credit}" />
            <td><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${price_format}" />$</td>
        </tr>

    </tbody>
</table>
</div>