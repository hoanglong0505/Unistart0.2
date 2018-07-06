function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function onSignIn(googleUser) {

    var id_token = googleUser.getAuthResponse().id_token;
    console.log('Token: ' + id_token);

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://localhost:8084/Unistart/login');
    xhr.setRequestHeader('Content-Type', 'text/plain');
    xhr.onload = function () {
//        console.log('Signed in as: ' + xhr.responseText);
    };
    xhr.send(id_token);

}

function onSignInThenRedirect(googleUser) {

    var id_token = googleUser.getAuthResponse().id_token;
    console.log('Token: ' + id_token);

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://localhost:8084/Unistart/login');
    xhr.setRequestHeader('Content-Type', 'text/plain');
    xhr.onload = function () {
        window.location.assign(xhr.responseText);
    };
    xhr.send(id_token);

}

function signOut() {
//    var xhr = new XMLHttpRequest();
//
//    xhr.open('POST', 'http://localhost:8084/Unistart/logout');
//    xhr.send("");
//
//    xhr.onload = function () {
//        var auth2 = gapi.auth2.getAuthInstance();
//        auth2.signOut().then(function () {
//            console.log('User signed out.');
//        });
//        location.reload(true);
//    }

    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });

}