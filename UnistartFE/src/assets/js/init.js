//------------------------ COOKIE, SESSION ---------------

function setCookie(cookie, exdays) {
  if (exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    cookie += (";" + expires + ";path=/");
    document.cookie = cookie;
    return;
  }
  document.cookie = cookie + ";path=/";
}

function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
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

function getSession(create) {
  var uniSession = getCookie('UNISESSION');
  if (uniSession) {
    uniSession = JSON.parse(uniSession);
    return uniSession;
  }
  if (create) {
    uniSession = createSession();
    return uniSession;
  }
  return null;
}

function createSession() {
  var uniSession = new Object();
  var UNISESSION = new Object();
  UNISESSION['uniSession'] = uniSession;
  setCookie('UNISESSION=' + JSON.stringify(UNISESSION));
  return UNISESSION;
}

function getSessionItem(key) {
  return getSession(true)['uniSession'][key];
}

function setSessionItem(key, value) {
  var uniSession = getSession(true)['uniSession'];
  uniSession[key] = value;
  pushToCookie(uniSession);
}

function removeSessionItem(key) {
  var uniSession = getSession(true)['uniSession'];
  delete uniSession[key];
  pushToCookie(uniSession);
}

function pushToCookie(uniSession) {
  var UNISESSION = new Object();
  UNISESSION['uniSession'] = uniSession;
  setCookie('UNISESSION=' + JSON.stringify(UNISESSION));
}
//------------------- LOGIN ---------------------

function onSignIn(googleUser) {
  var profile = googleUser.getBasicProfile();
  var tempToken = googleUser.getAuthResponse().id_token;
  var tempId = profile.getId();
  if (!getSessionItem('gId') || getSessionItem('gId') != tempId) {
    setSessionItem('gToken', tempToken);
    setSessionItem('gId', tempId);
    console.log(getSession(true));
    console.log(tempId);

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://localhost:8084/Unistart/login');
    xhr.send(tempToken);
    if (getSessionItem('reload'))
      window.location.reload();
  }

  userLogin(profile);

  // console.log('Token: ' + token);

}


function signOut() {
  var confirm = window.confirm('Xác nhận đăng xuất?');
  if (confirm) {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      removeSessionItem('gId');
      removeSessionItem('gToken');
      if (getSessionItem('reload'))
        window.location.reload();
    });
    userLogout();
  }
}

function userLogin(profile) {
  ele('glogin-button').style.display = 'none';
  ele('account').style.display = 'inline-block';
  ele('user-img').src = profile.getImageUrl();
  ele('user-menu').innerHTML = profile.getName();
}

function userLogout() {
  ele('glogin-button').style.display = 'inline-block';
  ele('account').style.display = 'none';
}

function ele(id) {
  return document.getElementById(id);
}
