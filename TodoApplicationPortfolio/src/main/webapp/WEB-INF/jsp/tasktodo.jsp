<%@page import="java.util.ArrayList"%>
<%@page import="model.UserAccount"%>
<%@page import="model.AllTasks"%>
<%@page import="java.util.List"%>
<%@ page import="model.AllTasks" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
Object allTasksObj = session.getAttribute("alltasks");
List<AllTasks> todoList;
if (allTasksObj instanceof List<?>) {
	todoList = (List<AllTasks>)allTasksObj;
} else {
	todoList = new ArrayList<>();
}

UserAccount useraccount = (UserAccount)session.getAttribute("useraccount");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>TDA｜簡単なTodoアプリケーション</title>
<meta name="description" content="これは練習用のポートフォリオです。実際にはサービスを提供しておりません。ですが、使いたい方はご自由にお使いください。">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css">
<link rel="stylesheet" href="css/tasktodo.css">
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
            <h1>タスク一覧</h1>
            <div class="task-todo">
            	<%--<% if (todoList != null) { %>--%>
            	<%--<% for (AllTasks todo : todoList) { %>--%>
                <div class="input-container">
                    <div class="input-container-padding">
                        <p class="username">ユーザー名：<span>あ<%--<%= useraccount.getUsername() %>--%></span></p>
                        <div class="content-area flex">
                            <p class="heading">内容：</p>
                            <p class="content">あ<%--<%= todo.getMemo() %>--%></p>
                        </div>
                        <div class="content-area flex">
                            <p class="heading">期限日：</p>
                            <p class="dead-line-date">2025-05-23<%--<%= todo.getDeadlinedate() %>--%></p>
                        </div>
                        <button class="input-container-button button">編集する</button>
                        <button class="button">完了</button>
                    </div>
                </div>
                <%--<% } %>--%>
                <%--<% } %>--%>
                <div class="task-add">
                    <div class="square">
                        <a href="JumpNewTask" class="plus">+</a>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>