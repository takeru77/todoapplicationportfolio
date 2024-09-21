<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>TDA｜簡単なTodoアプリケーション</title>
<meta name="description" content="これは練習用のポートフォリオです。実際にはサービスを提供しておりません。ですが、使いたい方はご自由にお使いください。">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css">
<link rel="stylesheet" href="css/login.css">
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
            <h1>ログイン</h1>
            <form action="?" method="post">
                <div class="input-container-set">
                    <div class="input-container">
                        <label for="email">メールアドレス<br>
                        <span>※４０文字以下</span></label><br>
                        <input type="text" id="email" name="email" maxlength="40" pattern="^([\w\-._]+@[\w\-._]+\.[A-Za-z]{2,})(\.[A-Za-z]{2}){0,1}?$" required>
                    </div>
                    <div class="input-container">
                        <label for="password">パスワード</label><br>
                        <span>※１２文字以下の英数記号</span></label><br>
                        <input type="text" id="password" name="password" maxlength="12" pattern="^[!-~]{1,12}$" required>
                    </div>
                </div>
                <div class="button-set">
                    <button type="submit" formaction="LoginServlet">ログインする</button><br>
                    <button type="submit" formaction="index.html" formnovalidate>キャンセル</button>
                </div>
            </form>
        </section>
    </body>
</html>