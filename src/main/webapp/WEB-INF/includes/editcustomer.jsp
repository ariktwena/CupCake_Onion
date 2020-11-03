<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>



<div class="row">
    <div class="col-sm-12 col-md-6" style="">
        <form action="EditCustomer" method="post">

            <input type="hidden" name="user_id" value="${requestScope.userToEdit.id}">

            <div class="form-group">
                <label for="selectEmail">Email address</label>
                <input type="email" class="form-control" id="selectEmail" placeholder="Write email" value="${requestScope.userToEdit.userEmail}" name="user_email" required>
            </div>
            <div class="form-group">
                <label for="selectRole">User Role</label>
                <select class="form-control" id="selectRole" name="user_role" required>
                    <c:choose>
                        <c:when test="${requestScope.userToEdit.userRole == 'customer'}">
                            <option>Choose One</option>
                            <option value="customer" selected>Customer</option>
                            <option value="admin">Admin</option>
                        </c:when>
                        <c:when test="${requestScope.userToEdit.userRole == 'admin'}">
                            <option>Choose One</option>
                            <option value="customer">Customer</option>
                            <option value="admin" selected>Admin</option>
                        </c:when>
                        <c:otherwise>
                            <option selected>Choose One</option>
                            <option value="customer">Customer</option>
                            <option value="admin">Admin</option>
                        </c:otherwise>
                    </c:choose>
                </select>
            </div>
            <div class="form-group">
                <label for="user_credit">Credit</label>
                <input type="number" step="any" class="form-control" id="user_credit" value="${requestScope.userToEdit.user_credit}" name="user_credit" required>
            </div>
            <button type="submit" class="btn btn-primary">Update User</button>
        </form>

        <!--Error handling -->
        <div>
            <c:if test ="${requestScope.errorMessageEdit != null}">
                <div class="alert alert-danger" style="padding-bottom: inherit;">
                    <p>${requestScope.errorMessageEdit}</p>
                </div>
            </c:if>
        </div>

        <!--Success handling-->
        <div>
            <c:if test ="${requestScope.successMessageEdit != null}">
                <div class="alert alert-success" style="padding-bottom: inherit;">
                    <p>${requestScope.successMessageEdit}</p>
                </div>
            </c:if>
        </div>
    </div>

    <div class="col-sm-12 col-md-6" style=""></div>
</div>

