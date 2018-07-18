function onSignIn(googleUser) {
  var profile = googleUser.getBasicProfile();
  var tempToken = googleUser.getAuthResponse().id_token;
  var tempId = profile.getId();
  if (!sessionStorage.getItem('gId') || sessionStorage.getItem('gId') != tempId) {
    sessionStorage.setItem('gToken', tempToken);
    sessionStorage.setItem('gId', tempId);
    console.log(tempId);

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://localhost:8084/Unistart/login');
    xhr.send(tempToken);
    if (sessionStorage.getItem('reload') == 'true')
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
      sessionStorage.removeItem('gId');
      sessionStorage.removeItem('gToken');
      if (sessionStorage.getItem('reload') == 'true')
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
