<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="google-signin-client_id" content="1087289067680-tt2uveimjqg0t9cu46pmalum1kpsiblu.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script src="/Unistart/js/glogin.js"></script>
        <link rel="stylesheet" href="/Unistart/css/glogin.css"/>
    </head>
    <a class="g-signin2" data-onsuccess="onSignIn"></a>
    <div>TODO write content</div>
    <a href="logout" onclick="signOut();">Sign out</a>
    <h1>login to continue</h1>
</html>
