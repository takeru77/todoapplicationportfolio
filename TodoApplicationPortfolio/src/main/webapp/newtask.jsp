<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>TDA｜簡単なTodoアプリケーション</title>
<meta name="description" content="これは練習用のポートフォリオです。実際にはサービスを提供しておりません。ですが、使いたい方はご自由にお使いください。">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css">
<link rel="stylesheet" href="css/newtask.css">
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
            <h1>タスクの新規追加画面</h1>
            <form action="?" method="post">
                <div class="input-container-set">
                    <div class="input-container">
                        <label for="title">タイトル<br>
                        <span>※１５文字以内</span></label><br>
                        <input type="text" id="title" name="title" maxlength="15">
                    </div>
                    <div class="input-container">
                        <label for="content">内容<br>
                        <span>※１５０文字以内</span></label><br>
                        <textarea name="content" id="content" cols="42" rows="8"></textarea>
                    </div>
                    <div class="input-container">
                        <label for="deadlinedate">期限日</label><br>
                        <input type="date" id="deadlinedate" name="deadlinedate" maxlength="12" pattern="^[!-~]{1,12}$">
                    </div>
                </div>
                <div class="button-set">
                <%-- 本来はログイン時のサーブレットファイルに遷移した方が良いが、 --%>
                <%-- 今は未作成の為とりあえずRegisterServlet4に遷移することとする。 --%>
                    <a href="RegisterServlet4">新規追加せずにタスク一覧に戻る</a><br>
                    <!-- <button type="submit" formaction="RegisterServlet">新規登録する</button><br> -->
                    <button type="submit" formaction="NewTaskAdd">新規追加</button>
                    <!-- <button type="submit" formaction="index.html" formnovalidate>新規追加</button> -->
                </div>
            </form>
        </section>
    </body>
</html>