sessionStorage.setItem('reload', 'false');

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
  // var profile = googleUser.getBasicProfile();
  sessionStorage.setItem('gId', googleUser.getId());
  // console.log(gId);
  var tempToken = googleUser.getAuthResponse().id_token;
  if (sessionStorage.getItem('gToken') != tempToken) {
    sessionStorage.setItem('gToken', tempToken);
    reload = true;
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://localhost:8084/Unistart/login');
    xhr.send(tempToken);
    if (sessionStorage.getItem('reload') == 'true')
      window.location.reload();
  }
  // console.log('Token: ' + token);

}

function signOut() {
  var auth2 = gapi.auth2.getAuthInstance();
  auth2.signOut().then(function () {
    sessionStorage.removeItem('gId');
    sessionStorage.removeItem('gToken');
    if (sessionStorage.getItem('reload') == 'true')
      window.location.reload();
  });

}
