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
String useraccountname = useraccount.getUsername();

AllTasks firsttodo = new AllTasks();
Optional<LocalDate> deadlinedate = Optional.ofNullable(null);
LocalDate localDate;
String localDateString = "";

StringBuilder stringbuilder = new StringBuilder("");
String memo = stringbuilder.toString();

int piece = 0;
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
            </div>
            <div class="jumparea">
                <p onclick="toggleDropdown()"><span>tatakeru1</span>さんがログイン中</p>
                <div class="dropdown" id="myDropdown">
                    <a href="#">プロフィール</a>
                    <a href="#">ログアウト</a>
                </div>
            </div>
        </header>
        <section class="main-visual">
            <h1>タスク一覧</h1>
            <form class="task-todo" action="?" method="get">
                <% if (todoList != null) { %>
                <% if (todoList.size() > 0) { %>
                <% for (AllTasks todo : todoList) { %>
                <% stringbuilder = todo.getMemo(); %>
                <% memo = stringbuilder.toString(); %>
                <% piece = todo.getPiece(); %>
                <% deadlinedate = todo.getDeadlinedate(); %>
                <% try { %>
                <% localDate = deadlinedate.get(); %>
                <% localDateString = localDate.toString(); %>
                <% } catch (Exception e) { %>
                <% localDateString = ""; %>
                <% } %>
                <div class="input-container">
                    <div class="input-container-padding">
                        <p class="username">ユーザー名：<span><%= useraccountname %></span></p>
                        <div class="content-area flex">
                            <p class="heading">内容：</p>
                            <p class="content"><%= memo %></p>
                        </div>
                        <div class="content-area flex">
                            <p class="heading">期限日：</p>
                        	<p class="dead-line-date"><%= localDateString %></p>
                        </div>
                        <button class="input-container-button button" type="submit" formaction="EditServlet" name="edit" value="<%= piece %>">編集する</button>
                        <button class="button">完了</button>
                    </div>
                </div>   
                <% } %>
            	<% } %>
            	<% } %>               
                <div class="task-add">
                    <div class="square">
                        <a href="JumpNewTask" class="plus">+</a>
                    </div>
                </div>
            </form>
        </section>
        <script>
        function toggleDropdown() {
            document.getElementById("myDropdown").classList.toggle("show");
        }
        </script>
    </body>
</html>