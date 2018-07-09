<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@include file="/include.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="google-signin-client_id" content="1087289067680-tt2uveimjqg0t9cu46pmalum1kpsiblu.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script src="/Unistart/js/glogin.js"></script>
        <link rel="stylesheet" href="/Unistart/css/glogin.css"/>
        <link rel="stylesheet" href="/Unistart/css/common.css"/>
        <style>

        </style>
    </head>
    <body>
        <div id="glogin-button" class="g-signin2" data-onsuccess="onSignIn"></div>
        <a href="/Unistart/logout" id="glogout-button" onclick="signOut();" >Sign out</a><br/>
        <a href="/Unistart/">HOME PAGE</a>
        <br/><br/>
        <img src="${school.avatar}" width="200" height="200"/>
        <br/>
        <h1 id="school-name" style="color: darkorange;">${school.schoolName}</h1>
        <div class="field">
            Mã trường: ${school.schoolCode}
        </div>
        <div class="field">
            Hệ: ${school.type}
        </div>
        <div class="field">
            Website: <a href="${school.website}">${school.website}</a>
        </div>
        <div class="field">
            Điện thoại: ${school.phone}
        </div>
        <div class="field">
            Email: ${school.email}
        </div>
        <a href="/Unistart/secured/review?schoolId=${school.schoolId}">ĐÁNH GIÁ TRƯỜNG NÀY</a><br/>

        <h2>Các chi nhánh</h2>
        <c:forEach items="${school.branchs}" var="b">
            <div class="field">
                Tên chi nhánh: ${b.branchName}
            </div>
            <div class="field">
                Website: <a href="${b.website}">${b.website}</a>
            </div>
            <div class="field">
                Điện thoại: ${b.phone}
            </div>
            <div class="field">
                Địa chỉ: ${b.address}
            </div>
            <div>-----------------------------------------</div>
        </c:forEach>
        <h1>THÔNG TIN TUYỂN SINH NĂM 2017</h1>

        <table width="100%" border="1">
            <tr>
                <th width="30%">Ngành</th>
                <th width="20%">Tổ hợp môn</th>
                <th width="10%">Chỉ tiêu tuyển sinh</th>
                <th width="10%">Điểm sàn</th>
                <th width="30%">Chú thích</th>
            </tr>
            <c:forEach items="${school.entranceInfos}" var="ef">
                <tr>
                    <td>${ef.subName}</td>
                    <td>
                        <c:forEach items="${ef.subjectCombinations}" var="sjC">
                            <span>${sjC.sjCombiCode}, </span>
                        </c:forEach>
                    </td>
                    <td>${ef.normalEntranceAmount}</td>
                    <td>${ef.minPoint}</td>
                    <td>${ef.note}</td>
                </tr>
            </c:forEach>
        </table>

        <h1>CÁC BÀI ĐÁNH GIÁ</h1>
        <c:forEach items="${school.rates}" var="r">
            <c:choose>
                <c:when test="${r.anonymous}">
                    <h3 style="color:blueviolet;">Thành viên ẩn danh</h3>
                </c:when>
                <c:otherwise>
                    <h3 style="color:blueviolet;">${r.user.name}</h3>
                </c:otherwise>
            </c:choose>

            <div class="field">
                <b>${r.title}</b>
            </div>
            <div class="field">
                <b>Ưu điểm</b><br/>
                ${r.pros}
            </div>
            <div class="field">
                <b>Nhược điểm</b><br/>
                ${r.cons}
            </div>
            <div class="field">
                <b>Trải nghiệm</b><br/>
                ${r.experience}
            </div>
            <c:choose>
                <c:when test="${r.encourage}">
                    <div class="field" style="color:green;">Khuyến khích học</div>
                </c:when>
                <c:otherwise>
                    <div class="field" style="color:red;">Không khuyến khích học</div>
                </c:otherwise>
            </c:choose>
            <div>-----------------------------------------</div>
        </c:forEach>

    </body>
</html>
