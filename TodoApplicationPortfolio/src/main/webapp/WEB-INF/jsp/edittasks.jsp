<%@ page import="model.AllTasks" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.UserAccount" %>
<%@ page import="model.AllTasks" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.Integer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<% AllTasks purposeTask = (AllTasks)request.getAttribute("purposeTask");%>
<% Optional<LocalDate> todos = purposeTask.getDeadlinedate(); %>
<% LocalDate ld = todos.orElse(LocalDate.MAX); %>
<% String localDateString = ""; %>
<% if (ld.equals(LocalDate.MAX)) { %>
<% localDateString = ""; %>
<% } else { %>
<% localDateString = ld.toString(); %>
<% } %>

<% int todoListnum = (int)request.getAttribute("todoListnumber"); %>
<% Integer ite = Integer.valueOf(todoListnum); %>
<% String todoListnumber = ite.toString(); %>

<% int pieceNumber = (int)request.getAttribute("pieceNumber"); %>
<% Integer ite2 = Integer.valueOf(pieceNumber); %>
<% String pieceNumberString = ite2.toString(); %>

<% Object errorMsgobj = request.getAttribute("errorMsg"); %>
<% String errorMsg = null; %>
<% if (errorMsgobj instanceof String) { %>
<% errorMsg = (String)errorMsgobj; %>
<% } %>

<% Object deleteErrorobj = request.getAttribute("deleteError"); %>
<% String deleteError = null; %>
<% if (deleteErrorobj instanceof String) { %>
<% deleteError = (String)deleteErrorobj; %>
<% } %>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>TDA｜簡単なTodoアプリケーション</title>
<meta name="description" content="これは練習用のポートフォリオです。実際にはサービスを提供しておりません。ですが、使いたい方はご自由にお使いください。">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css">
<link rel="stylesheet" href="css/edittasks.css">
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
            <h1>タスクの編集画面</h1>
            <form action="?" method="post">
                <div class="input-container-set">
                    <div class="input-container">
                        <label for="title">タイトル<br>
                        <span>※１５文字以内</span></label><br>
                        <input type="text" id="title" name="title" maxlength="15" value="<%= purposeTask.getTitle() %>">
                    </div>
                    <div class="input-container">
                        <label for="memo">内容<br>
                        <span>※１５０文字以内</span></label><br>
                        <textarea name="memo" id="memo" cols="42" rows="8"><%= purposeTask.getMemo() %></textarea>
                    </div>
                    <div class="input-container">
                        <label for="deadlinedate">期限日</label><br>
                        <input type="date" id="deadlinedate" name="deadlinedate" maxlength="12" pattern="^[!-~]{1,12}$" value="<%= localDateString %>">
                    </div>
                </div>
                <input type="hidden" name="todoListnumber" value="<%= todoListnumber %>">
                <input type="hidden" name="pieceNumberString" value="<%= pieceNumberString %>">
                <div class="button-set">
                	<% if (errorMsg != null) { %>
                	<p>※<%= errorMsg %></p>
                	<% } else if (deleteError != null) { %>
                	<p>※<%= deleteError %></p>
                	<% } %>
                    <a href="RegisterServlet4">編集せずにタスク一覧に戻る</a><br>
                    <!-- <button type="submit" formaction="RegisterServlet">新規登録する</button><br> -->
                    <button type="submit" formaction="EditRegServlet">編集完了</button>
                    <button type="submit" formaction="DeleteTask" formnovalidate>このタスクを削除</button>
                </div>
            </form>
        </section>
    </body>
</html>