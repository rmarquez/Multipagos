cocoon.load("resource://org/apache/cocoon/webapps/authentication/flow/javascript/auth.js");

function isLoggedIn() {
  var handler = cocoon.parameters["handler"];

  if (auth_isAuthenticated(handler))
    auth_success();
  else
    auth_failure();
}

function protect() {
  var handler = cocoon.parameters["handler"];

  if (auth_checkAuthentication(handler,""))
    auth_success();
  else
    /* already redirected by auth_checkAuthentication */;
}

function login() {
  var handler = cocoon.parameters["handler"];
  if (auth_isAuthenticated(handler))
      auth_success();
  else if (auth_login(handler, null, cocoon.parameters)) {
    cocoon.session.setAttribute("registrosPorPagina", java.lang.Integer.valueOf(20));
    auth_success();
  } else
    auth_failure();
}

function logout() {
  var handler = cocoon.parameters["handler"];
  auth_logout(handler);
  auth_failure();
}

function auth_success() {
  var internal = cocoon.parameters["protected-internal"];
  var redirect = cocoon.parameters["protected-redirect"];

  if (internal != null)
    cocoon.sendPage(internal);
  else if (redirect != null)
    cocoon.redirectTo(redirect);
  else
    throw new Error("No protected redirection parameter given");
}

function auth_failure() {

  var internal = cocoon.parameters["failure-internal"];
  var redirect = cocoon.parameters["failure-redirect"];

  if (internal != null)
    cocoon.sendPage(internal);
  else if (redirect != null)
    cocoon.redirectTo(redirect);
  else
    // Why does this throw cause an error?
    throw new Error("No failure redirection parameter given");
}

