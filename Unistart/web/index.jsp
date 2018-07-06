<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="google-signin-client_id" content="1087289067680-tt2uveimjqg0t9cu46pmalum1kpsiblu.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script src="/Unistart/js/glogin.js"></script>
    </head>
    <body>
        <a class="g-signin2" data-onsuccess="onSignIn"></a>
        <div>TODO write content</div>
        <a href="/Unistart/logout" onclick="signOut();" >Sign out</a>

        <br/><br/><br/>
        <br/>
        <form action="filter-school" method="get">
            <h2>TỈNH/ THÀNH</h2>
            <select name="location">
                <option value="all">Tất cả</option>
                <c:forEach items="${locations}" var="loc">
                    <option value="${loc.locationId}">${loc.locationName}</option>
                </c:forEach>
            </select>
            <h2>TỔ HỢP MÔN</h2>
            <select name="sjCode">
                <option value="all">Tất cả</option>
                <c:forEach items="${sjCodes}" var="sjC">
                    <option value="${sjC.sjCombiCode}">${sjC.sjCombiCode.concat(" - ").concat(sjC.sjCombiName)}</option>
                </c:forEach>
            </select>
            <h2>NGÀNH/ NGHỀ ĐÀO TẠO</h2>
            <select name="fieldCode">
                <option value="all">Tất cả</option>
                <c:forEach items="${fields}" var="f">
                    <option value="${f.fieldCode}">${f.fieldName}</option>
                </c:forEach>
            </select>
            <h2>HỆ TRƯỜNG</h2>
            <select name="typeId">
                <option value="all">Tất cả</option>
                <c:forEach items="${types}" var="t">
                    <option value="${t.typeId}">${t.typeName}</option>
                </c:forEach>
            </select>
            <h2>MỨC ĐIỂM SÀN TỐI ĐA <input name="minPoint" type="number" min="1" max="30" value="30"/></h2>
            <input type="submit"/>
        </form>
        <br/>
        <c:forEach items="${schools}" var="sch">
            <a href="pages/review?schoolId=${sch.schoolId}">${sch.schoolName}</a>
            <br/>
        </c:forEach>
    </body>
</html>
