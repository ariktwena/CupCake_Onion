<%--
  Created by IntelliJ IDEA.
  User: Tweny
  Date: 21/10/2020
  Time: 18.39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/includes/header.jsp"%>

<div class="container">
    <%@include file="/WEB-INF/includes/navigation.jsp"%>

        <br>
        <div class="row">
            <div class="col-sm-12 col-md-2 text-center" style=""></div>

            <div class="col-sm-12 col-md-4 text-center" style="">

                <form class="form-signin" action="${pageContext.request.contextPath}/Login" method="POST" >

<%--                    <input type="hidden" name="target" value="login">--%>

                    <input type="hidden" name="checkoutProcess" value="${requestScope.checkoutProcess}">

                    <img class="mb-2" src="../../images/logo.png" alt="" width="72" >

                    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>

                    <div class="form-group" style="margin-bottom: 62px">
                    <label for="inputEmail" class="sr-only">Email address</label>
                    <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="">

                    <label for="inputPassword" class="sr-only">Password</label>
                    <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required="">
                    </div>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        Sign In
                        <svg width="1em" height="1em" viewBox="0 0 16 16" style="margin-top: -3px" class="bi bi-box-arrow-in-right" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"/>
                            <path fill-rule="evenodd" d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
                        </svg>
                    </button>
                </form>

                <div>
                    <c:if test ="${requestScope.messageSignIn != null}">
                        <div class="alert alert-danger" style="padding-bottom: inherit;">
                            <p>${requestScope.messageSignIn}</p>
                        </div>
                    </c:if>
                </div>

            </div>

<%--            <div class="col-sm-12 col-md-2 text-center" style=""></div>--%>

            <div class="col-sm-12 col-md-4 text-center" style="">

                <form class="form-signin" action="${pageContext.request.contextPath}/Register" method="POST">

<%--                    <input type="hidden" name="target" value="register">--%>

                    <input type="hidden" name="checkoutProcess" value="${requestScope.checkoutProcess}">

                    <img class="mb-2" src="../../images/logo.png" alt="" width="72" >

                    <h1 class="h3 mb-3 font-weight-normal">Please sign up</h1>

                    <label for="email" class="sr-only">Email address</label>
                    <input type="email" name="email" id="email" class="form-control" placeholder="Email address" required="" autofocus="">

                    <label for="password1" class="sr-only">Enter Password</label>
                    <input type="password" name="password1" id="password1" class="form-control" placeholder="Password" required="">

                    <label for="password2" class="sr-only">Reenter Password</label>
                    <input type="password" name="password2" id="password2" class="form-control" placeholder="Password again" required="">

                    <br>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        Sign Up
                        <svg width="1em" height="1em" viewBox="0 0 16 16" style="margin-top: -3px" class="bi bi-box-arrow-in-right" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"/>
                            <path fill-rule="evenodd" d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
                        </svg>
                    </button>

                </form>

                <div>
                    <c:if test ="${requestScope.messageSignUp != null}">
                        <div class="alert alert-danger" style="padding-bottom: inherit;">
                            <p>${requestScope.messageSignUp}</p>
                        </div>
                    </c:if>
                </div>

            </div>

            <div class="col-sm-12 col-md-2 text-center" style=""></div>
        </div>
        <br>
        <br>
        <br>
    <%@include file="/WEB-INF/includes/footer.jsp"%>
