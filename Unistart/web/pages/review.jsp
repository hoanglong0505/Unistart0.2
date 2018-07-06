<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="google-signin-client_id" content="1087289067680-tt2uveimjqg0t9cu46pmalum1kpsiblu.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script src="/Unistart/js/glogin.js"></script>
        <style>
            textarea{
                resize: none;
                width:500px;
                height: 100px;
                font-family: arial;
            }
        </style>
    </head>
    <body>
        <a class="g-signin2" style="display:none;" data-onsuccess="onSignIn"></a>
        <div>TODO write content</div>
        <a href="/Unistart/logout" onclick="signOut();">Sign out</a>
        <a href="/Unistart/">HOME</a>
        <h1>REVIEW OF ${school.schoolName}</h1>

        <h1>Hello user: ${user.userId}</h1>
        <div>
            REVIEW PAGE
        </div>

        <script>
            function sendData(form) {
                var rate = new Object();
                rate.schoolId =${school.schoolId};
//                rate.userId =${user.userId};
                rate.title = form.title.value;
                rate.pros = form.pros.value;
                rate.cons = form.cons.value;
                rate.experience = form.experience.value;
                rate.encourage = form.encourage.checked;
                rate.anonymous = form.anonymous.checked;

                var critContainer = document.getElementById("crit-container");
                var critDetails = critContainer.getElementsByTagName("input");

                var rds = new Array();

                var i;
                for (i = 0; i < critDetails.length; i++) {
                    var id = Number(critDetails[i].name.substr(1));
                    var rd = new Object();
                    rd.criteriaId = id;
                    rd.value = critDetails[i].value;
                    rds.push(rd);
                }

                rate.rateDetails = rds;

                var xhr = new XMLHttpRequest();
                xhr.open('POST', 'http://localhost:8084/Unistart/pages/addreview');
                xhr.setRequestHeader('Content-Type', 'application/json;charset="utf-8"');
                xhr.onload = function () {
                    window.location.assign(xhr.responseText);
                };
                xhr.send(JSON.stringify(rate));
            }
        </script>

        <form id="reviewform" method="POST">
            <h2>Mức độ hài lòng:</h2>
            <div id="crit-container">
                <c:forEach items="${criterias}" var="cr">
                    ${cr.criteriaName}: <input type="number" name="c${cr.criteriaId}" min="1" max="10"/><br/>
                </c:forEach>
            </div>
            
            Nhập tiêu đề <input type="text" name="title"/><br/>
            Uu điểm<br/> <textarea name="pros" form="reviewform">Enter text here...</textarea><br/>
            Nhược điểm<br/> <textarea name="cons" form="reviewform">Enter text here...</textarea><br/>
            Trải nghiệm của bạn<br/> <textarea name="experience" form="reviewform">Enter text here...</textarea><br/>
            Bạn có khuyến khích học trường này <input type="checkbox" name="encourage"/><br/>
            Ẩn danh <input type="checkbox" name="anonymous"/><br/>
            <br/>
            <input type="button" onclick="sendData(this.parentElement)" value="XÁC NHẬN"/>
        </form>

    </body>
</html>
