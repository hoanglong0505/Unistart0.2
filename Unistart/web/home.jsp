<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@include file="include.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="google-signin-client_id" content="1087289067680-tt2uveimjqg0t9cu46pmalum1kpsiblu.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script src="/Unistart/js/glogin.js"></script>
        <link rel="stylesheet" href="/Unistart/css/glogin.css"/>
        <link rel="stylesheet" href="/Unistart/css/common.css"/>
        <link rel="stylesheet" href="/Unistart/css/header.css"/>
        <link rel="stylesheet" href="/Unistart/css/section.css"/>
    </head>
    <body>
        <div id="glogin-button" class="g-signin2" data-onsuccess="onSignIn"></div>
        <a href="logout" id="glogout-button" onclick="signOut();" >Sign out</a>
        <header>
            <h1 id="home">HOME PAGE</h1>
        </header>
        <section>
            <h2>TÌM KIẾM</h2>
            <form id="filter-form" action="filter-school" method="GET">
                <div class="field-container">
                    <div class="field-name">TÊN TRƯỜNG</div><input class="text" type="text" name="schoolName"/><br/>
                </div>
                <div class="field-container">
                    <div class="field-name">TỈNH/ THÀNH</div>
                    <select class="combobox" name="location">
                        <option value="all">Tất cả</option>
                        <c:forEach items="${locations}" var="loc">
                            <option value="${loc.locationId}">${loc.locationName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="field-container">
                    <div class="field-name">TỔ HỢP MÔN</div>
                    <select class="combobox" name="sjCode">
                        <option value="all">Tất cả</option>
                        <c:forEach items="${sjCodes}" var="sjC">
                            <option value="${sjC.sjCombiCode}">${sjC.sjCombiCode.concat(" - ").concat(sjC.sjCombiName)}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="field-container">
                    <div class="field-name">NGÀNH/ NGHỀ ĐÀO TẠO</div>
                    <select class="combobox" name="fieldCode">
                        <option value="all">Tất cả</option>
                        <c:forEach items="${fields}" var="f">
                            <option value="${f.fieldCode}">${f.fieldName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="field-container">
                    <div class="field-name">HỆ TRƯỜNG</div>
                    <select class="combobox" name="typeId">
                        <option value="all">Tất cả</option>
                        <c:forEach items="${types}" var="t">
                            <option value="${t.typeId}">${t.typeName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="field-container">
                    <div class="field-name">MỨC ĐIỂM SÀN TỐI ĐA</div> <input class="text" name="minPoint" type="number" min="1" max="30" value="30"/>

                </div>
                <br/>
                <input type="submit" value="TÌM KIẾM"/>
            </form>

        </section>
        <section>
            <h2>DANH SÁCH TRƯỜNG</h2>
            <c:forEach items="${schools}" var="sch">
                <a href="public/schoolinfo?schoolId=${sch.schoolId}">${sch.schoolName}</a>
                <br/>
            </c:forEach>
        </section>
    </body>
</html>

