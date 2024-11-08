<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix ="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix ="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="utf-8"/>

<c:set var="_ctx" value="${pageContext.request.contextPath}" />

<c:set var="_css" value="${_ctx}/css" />
<c:set var="_img" value="${_ctx}/img" />
<c:set var="_view" value="${_ctx}/WEB-INF/views" />
<c:set var="_js" value="${_ctx}/js" />
<c:set var="_script" value="${_ctx}/script" />

<!-- 클라이언트 화면 -->
<c:set var="_header" value="${_ctx}/WEB-INF/views/layout/header.jsp" />
<c:set var="_footer" value="${_ctx}/WEB-INF/views/layout/footer.jsp" />
<c:set var="_resource" value="${_ctx}/WEB-INF/views/layout/resource.jsp" />
