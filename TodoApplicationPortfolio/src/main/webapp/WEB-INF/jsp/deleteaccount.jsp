<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% Object errorMsgobj = request.getAttribute("errorMsg"); %>
<% String errorMsg = null; %>
<% if (errorMsgobj instanceof String) { %>
<%   errorMsg = (String)errorMsgobj; %>
<% } %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>TDA｜簡単なTodoアプリケーション</title>
<meta name="description" content="これは練習用のポートフォリオです。実際にはサービスを提供しておりません。ですが、使いたい方はご自由にお使いください。">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css">
<link rel="stylesheet" href="css/deleteaccount.css">
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
            <h1>アカウント削除しますか？</h1>
            <% if (errorMsg != null) { %>
            <p>※<%= errorMsg %></p>
            <% } %>
            <div class="button-set">
                <a href="tasktodo.jsp">タスク一覧に戻る</a>
                <form action="DeleteAccount" method="post">
                    <input type="submit" value="アカウント削除">
                </form>
            </div>
        </section>
    </body>
</html>