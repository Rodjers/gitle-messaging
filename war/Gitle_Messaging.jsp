<!doctype html>
<!-- The DOCTYPE declaration above will set the     -->
<!-- browser's rendering engine into                -->
<!-- "Standards Mode". Replacing this declaration   -->
<!-- with a "Quirks Mode" doctype is not supported. -->

<%@ page 	import="no.gitlestadit.gitlemessaging.userdatabase.UserDatabaseHandle"
			import="no.gitlestadit.gitlemessaging.userdatabase.kinds.App"
			import="no.gitlestadit.gitlemessaging.userdatabase.PMF"
			import="no.gitlestadit.gitlemessaging.userdatabase.kinds.Target"
			import="javax.jdo.PersistenceManager"
			import="javax.jdo.Query"
			import="java.util.ArrayList"
			import="java.util.Iterator"
			import="java.util.List"%>

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="Gitle_Messaging.css">
    <link href="formCss.css" rel="stylesheet" type="text/css" />
    <link type="text/css" rel="stylesheet" href="nova.css" />
    <style type="text/css">
    .form-label{
        width:150px !important;
    }
    .form-label-left{
        width:150px !important;
    }
    .form-line{
        padding-top:12px;
        padding-bottom:12px;
    }
    .form-label-right{
        width:150px !important;
    }
    body, html{
        margin:0;
        padding:0;
        background:false;
    }

    .form-all{
        margin:0px auto;
        padding-top:0px;
        width:650px;
        color:#555 !important;
        font-family:'Lucida Grande';
        font-size:14px;
    }
</style>
  </head>

  <body>

    <noscript>
      <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
      </div>
    </noscript>

    <h1>Gitle Messaging</h1>
    
    <form action="app/register" method="post" name="sendApp" accept-charset="utf-8">
    <div class="form-all">
      <ul class="form-section">
        <li class="form-input-wide">
          <div class="form-header-group">
            <h2 class="form-header">
              Register App
            </h2>
          </div>
        </li>
        <li class="form-line">
          <label class="form-label-left" for="name">App Name</label>
          <div class="form-input">
          <input type="text" class="form-textbox" name="name" size="20" />
          </div>
        </li>
        <li class="form-line">
          <label class="form-label-left" for="platform">Platform</label>
          <div class="form-input">
            <select name="platform">
            <option>gcm</option>
            <option>ios</option>
            <option>windows8</option>
            </select>
          </div>
        </li>
        <li class="form-line">
          <div class="form-input-wide">
            <div style="margin-left:156px" class="form-buttons-wrapper">
              <button type="submit" class="form-submit-button">
                Register
              </button>
              &nbsp;
              <button type="reset" class="form-submit-button">
                Clear Form
              </button>
            </div>
          </div>
        </li>
      </ul>
    </div>
    </form>

    <form action="target/register" method="post" name="registerApp" accept-charset="utf-8">
    <div class="form-all">
      <ul class="form-section">
        <li class="form-input-wide">
          <div class="form-header-group">
            <h2 class="form-header">
              Register User
            </h2>
          </div>
        </li>
        <li class="form-line">
          <label class="form-label-left" for="userId"> Username </label>
          <div class="form-input">
            <input type="text" class="form-textbox" name="userId" size="20" />
          </div>
        </li>
        <li class="form-line">
          <label class="form-label-left" for="pushId"> PushId </label>
          <div class="form-input">
            <input type="text" class="form-textbox" name="pushId" size="20" />
          </div>
        </li>
        <li class="form-line">
          <label class="form-label-left" for="appName"> App Name </label>
          <div class="form-input">
          <select name="appName">
          <%
          PersistenceManager pm = PMF.get().getPersistenceManager();
     		
     		Query q = pm.newQuery(App.class);
     		
     		try {
     		  List<App> results = (List<App>) q.execute();
     		  for (App r : results){
     			 out.println("<option value=\"" + r.getKey() + "\">" + r.getName() + " (" + r.getPlatform() + ")</option>");
     		  }
     		} finally {
     		  q.closeAll();
     		}
          %>
          </select>
          </div>
        </li>
        <li class="form-line">
          <div class="form-input-wide">
            <div style="margin-left:156px" class="form-buttons-wrapper">
              <button type="submit" class="form-submit-button">
                Register
              </button>
              &nbsp;
              <button type="reset" class="form-submit-button">
                Clear Form
              </button>
            </div>
          </div>
        </li>
      </ul>
    </div>
  </form>
  
  <form action="target/send" method="post" name="sendApp" accept-charset="utf-8">
  <div class="form-all">
    <ul class="form-section">
      <li class="form-input-wide">
        <div class="form-header-group">
          <h2 class="form-header">
            Send Message
          </h2>
        </div>
      </li>
      <li class="form-line">
        <label class="form-label-left" for="userId"> Username </label>
        <div class="form-input">
          <select name="userId">
          <%
     		q = pm.newQuery(Target.class);
     		q.setResult("distinct username");
     		
     		try {
     		  List<String> results = (List<String>) q.execute();
     		  for (String r : results){
     			 out.println("<option>" + r + "</option>");
     		  }
     		} finally {
     		  q.closeAll();
     		}
          %>
          </select>
        </div>
      </li>
      <li class="form-line">
        <label class="form-label-left" for="message"> Message </label>
        <div class="form-input">
          <input type="text" class="form-textbox" name="message" size="20" />
        </div>
      </li>
      <li class="form-line">
        <div class="form-input-wide">
          <div style="margin-left:156px" class="form-buttons-wrapper">
            <button type="submit" class="form-submit-button">
              Send
            </button>
            &nbsp;
            <button type="reset" class="form-submit-button">
              Clear Form
            </button>
          </div>
        </div>
      </li>
    </ul>
  </div>
</form>


  </body>
</html>
