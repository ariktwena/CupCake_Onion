<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>



<div class="row">
    <div class="col-sm-12 col-md-6" style="">
        <form action="AddCustomer" method="post">

            <div class="form-group">
                <label for="selectEmail">Email address</label>
                <input type="email" class="form-control" id="selectEmail" placeholder="Write email" value="${requestScope.user_email}" name="user_email" required>
            </div>
            <div class="form-group">
                <label for="selectPassword">Password</label>
                <input type="password" class="form-control" id="selectPassword" placeholder="Write password" value="${requestScope.password}" name="password" required>
            </div>
            <div class="form-group">
                <label for="selectRole">User Role</label>
                <select class="form-control" id="selectRole" name="user_role" required>
                    <c:choose>
                        <c:when test="${requestScope.user_role == 'customer'}">
<%--                            <option>Choose One</option>--%>
                            <option value="customer" selected>Customer</option>
                            <option value="admin">Admin</option>
                        </c:when>
                        <c:when test="${requestScope.user_role == 'admin'}">
<%--                            <option>Choose One</option>--%>
                            <option value="customer">Customer</option>
                            <option value="admin" selected>Admin</option>
                        </c:when>
                        <c:otherwise>
<%--                            <option selected>Choose One</option>--%>
                            <option value="customer">Customer</option>
                            <option value="admin">Admin</option>
                        </c:otherwise>
                    </c:choose>
                </select>
            </div>
            <div class="form-group">
                <label for="user_credit">Credit</label>
                <input type="number" step="any" class="form-control" id="user_credit" value="${requestScope.user_credit}" name="user_credit" required>
            </div>
            <button type="submit" class="btn btn-primary">Add Customer</button>
        </form>

        <!--Error handling -->
        <div>
            <c:if test ="${requestScope.errorMessage != null}">
                <div class="alert alert-danger" style="padding-bottom: inherit;">
                    <p>${requestScope.errorMessage}</p>
                </div>
            </c:if>
        </div>

        <!--Success handling-->
        <div>
            <c:if test ="${requestScope.successMessage != null}">
                <div class="alert alert-success" style="padding-bottom: inherit;">
                    <p>${requestScope.successMessage}</p>
                </div>
            </c:if>
        </div>
    </div>

    <div class="col-sm-12 col-md-6" style=""></div>
</div>

