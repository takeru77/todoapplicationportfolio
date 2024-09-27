<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Object spaceErrorMsgobj = request.getAttribute("spaceErrorMsg");
String spaceErrorMsg = null;
if (spaceErrorMsgobj instanceof String) {
	spaceErrorMsg = (String)spaceErrorMsgobj;
}

Object spaceErrorMsg2obj = request.getAttribute("spaceErrorMsg2");
String spaceErrorMsg2 = null;
if (spaceErrorMsg2obj instanceof String) {
	spaceErrorMsg2 = (String)spaceErrorMsg2obj;
}

Object spaceErrorMsg3obj = request.getAttribute("spaceErrorMsg3");
String spaceErrorMsg3 = null;
if (spaceErrorMsg3obj instanceof String) {
	spaceErrorMsg3 = (String)spaceErrorMsg3obj;
}

Object errorMsgobj = request.getAttribute("errorMsg");
String errorMsg = null;
if (errorMsgobj instanceof String) {
	errorMsg = (String)errorMsgobj;
}

Object errorMsg2obj = request.getAttribute("errorMsg2");
String errorMsg2 = null;
if (errorMsg2obj instanceof String) {
	errorMsg2 = (String)errorMsg2obj;
}
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>TDA｜簡単なTodoアプリケーション</title>
<meta name="description" content="これは練習用のポートフォリオです。実際にはサービスを提供しておりません。ですが、使いたい方はご自由にお使いください。">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css">
<link rel="stylesheet" href="css/register.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Shippori+Mincho:wght@400;500;600;700;800&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/bd00b6e71c.js" crossorigin="anonymous"></script>
</head>
    <body>
        <header class="header flex">
            <div class="header-logo flex">
                <img src="images/WebSiteLogo.jpg" alt="TDAとそのロゴ">
                <p>TDA</p>
            </div>
        </header>
        <section class="main-visual">
            <h1>新規登録</h1>
            <form action="?" method="post">
                <div class="input-container-set">
                    <div class="input-container">
                        <label for="username">ユーザー名<br>
                        <span>※１文字以上１０文字以下の英数字</span></label><br>
                        <input type="text" id="username" name="username" maxlength="10" minlength="1" pattern="^[a-zA-Z0-9]{1,10}$" required>
                        <% if (spaceErrorMsg != null) { %>
                        <p>※<%= spaceErrorMsg %><p>
                        <% } else if (errorMsg2 != null) { %>
                        <p>※<%= errorMsg2 %></p>
                        <% } %>
                    </div>
                    <div class="input-container">
                        <label for="email">メールアドレス<br>
                        <span>※４０文字以下</span></label><br>
                        <input type="text" id="email" name="email" maxlength="40" pattern="^([\w\-._]+@[\w\-._]+\.[A-Za-z]{2,})(\.[A-Za-z]{2}){0,1}?$" required>
                        <% if (spaceErrorMsg2 != null) { %>
                        <p>※<%= spaceErrorMsg2 %></p>
                        <% } %>
                    </div>
                    <div class="input-container">
                        <label for="password">パスワード</label><br>
                        <span>※１２文字以下の英数記号</span></label><br>
                        <input type="text" id="password" name="password" maxlength="12" pattern="^[!-~]{1,12}$">
                        <% if (spaceErrorMsg3 != null) { %>
                        <p>※<%= spaceErrorMsg3 %></p>
                        <% } else if (errorMsg != null) { %>
                        <p>※<%= errorMsg %></p>
                        <% } %>
                    </div>
                </div>
                <div class="button-set">
                    <button type="submit" formaction="RegisterServlet">新規登録する</button><br>
                    <button type="submit" formaction="index.html" formnovalidate>キャンセル</button>
                </div>
            </form>
        </section>
    </body>
</html>