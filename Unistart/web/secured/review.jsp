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
            #review-form .field {
                margin: 10px 0;
            }
            #review-form textarea{
                resize: none;
                width:500px;
                height: 100px;
                font-family: arial;
                border-radius: 10px;
            }

            #review-form .text{
                height: 25px;
                border-radius: 10px;
            }

            #review-form .button {
                border-radius: 10px;
                height: 25px;
            }
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

        <h1 style="color:red;">ĐÁNH GIÁ</h1>
        <form action="send-review" id="review-form" method="POST">
            <input style="display:none;" name="schoolId" value="${school.schoolId}"/>
            <h3>Mức độ hài lòng</h3>
            <div id="crit-container">
                <c:forEach items="${criterias}" var="cr">
                    <div class="field">
                        ${cr.criteriaName}: 
                        <input class="text" type="number" name="c${cr.criteriaId}" 
                               min="1" max="10" value="10"/><br/>
                    </div>
                </c:forEach>
            </div>
            <h3>Thông tin đánh giá</h3>
            <div class="field">            
                Nhập tiêu đề <input class="text" type="text" name="title"/><br/>
            </div>
            <div class="field">
                Uu điểm<br/> <textarea name="pros" form="review-form" placeholder="Enter text here..."></textarea><br/>
            </div>
            <div class="field">
                Nhược điểm<br/> 
                <textarea name="cons" form="review-form" placeholder="Enter text here..."></textarea><br/>
            </div>
            <div class="field">
                Trải nghiệm của bạn<br/> 
                <textarea name="experience" form="review-form" placeholder="Enter text here..."></textarea><br/>
            </div>
            <div class="field">
                Bạn có khuyến khích học trường này 
                <input type="checkbox" name="encourage"/><br/>
            </div>
            <div class="field">
                Ẩn danh <input type="checkbox" name="anonymous"/><br/>
            </div>
            <br/>
            <input class="button" type="submit" value="XÁC NHẬN"/>
        </form>

    </body>
</html>
