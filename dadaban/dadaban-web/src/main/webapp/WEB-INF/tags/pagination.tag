<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="com.dadaban.repository.util.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="margin-top-20 text-center">
    <ul class="pagination">
        <%if (page.hasPreviousPage()) {%>
            <li><a href="?page=1&sortType=${sortType}&${searchParams}">&lt;&lt;</a></li>
            <li><a href="?page=${page.pageNo-1}&sortType=${sortType}&${searchParams}">&lt;</a></li>
        <%} else {%>
            <li class="disabled"><a href="javascript:;">&lt;&lt;</a></li>
            <li class="disabled"><a href="javascript:;">&lt;</a></li>
        <%}%>

        <c:forEach var="i" begin="${page.showStart}" end="${page.showEnd}">
            <c:choose>
                <c:when test="${i eq page.pageNo}">
                    <li class="active"><a href="javascript:;">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="?page=${i}&sortType=${sortType}&${searchParams}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <% if (page.hasNextPage()){%>
            <li><a href="?page=${page.pageNo+1}&sortType=${sortType}&${searchParams}">&gt;</a></li>
            <li><a href="?page=${page.pageCount}&sortType=${sortType}&${searchParams}">&gt;&gt;</a></li>
        <%}else{%>
            <li class="disabled"><a href="javascript:;">&gt;</a></li>
            <li class="disabled"><a href="javascript:;">&gt;&gt;</a></li>
        <%} %>
    </ul>
</div>

