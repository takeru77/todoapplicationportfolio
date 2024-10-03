<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.UserAccount" %>
<%@ page import="model.AllTasks" %>
<%@ page import="java.util.List" %>
<%@ page import="model.AllTasks" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
Object allTasksObj = session.getAttribute("todoList");
List<AllTasks> todoList;
if (allTasksObj instanceof List<?>) {
	todoList = (List<AllTasks>)allTasksObj;
} else {
	todoList = null;
}

UserAccount useraccount = (UserAccount)session.getAttribute("useraccount");
%>
<%-- else { --%>
<%-- todoList = new ArrayList<>(); --%>
<%-- } --%>
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
            <form class="task-todo" action="?" method="get">
            	<% if (todoList != null) { %>
            	<% for (AllTasks todo : todoList) { %>
            	<% Optional<LocalDate> todos = todo.getDeadlinedate(); %>
            	<% LocalDate ld = todos.orElse(LocalDate.MAX); %>
            	<% String localDateString = ""; %>
            	<% if (ld.equals(LocalDate.MAX)) { %>
            	<% 	 localDateString = ""; %>
            	<% } else { %>
            	<%   localDateString = ld.toString(); %>
            	<% } %>
                <div class="input-container">
                    <div class="input-container-padding">
                        <p class="username">ユーザー名：<span><%= useraccount.getUsername() %></span></p>
                        <div class="content-area flex">
                            <p class="heading">内容：</p>
                            <p class="content"><%= todo.getMemo() %></p>
                        </div>
                        <div class="content-area flex">
                            <p class="heading">期限日：</p>
                            <%-- 3項演算子 条件式 ? 真の値 : 偽の値 の場合、真の値と偽の値は同じ型でなくてはならない --%>
                        	<p class="dead-line-date"><%= localDateString %></p>
                        </div>
                        <button class="input-container-button button" type="submit" formaction="EditServlet" name="edit" value=<%= todo.getPiece() %>>編集する</button>
                        <button class="button">完了</button>
                    </div>
                </div>
                <% } %>
                <% } %>
                <div class="task-add">
                    <div class="square">
                        <a href="JumpNewTask" class="plus">+</a>
                    </div>
                </div>
            </form>
        </section>
    </body>
</html>