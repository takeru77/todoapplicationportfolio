<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>TDA｜簡単なTodoアプリケーション</title>
<meta name="description" content="これは練習用のポートフォリオです。実際にはサービスを提供しておりません。ですが、使いたい方はご自由にお使いください。">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css">
<link rel="stylesheet" href="newtask.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Shippori+Mincho:wght@400;500;600;700;800&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/bd00b6e71c.js" crossorigin="anonymous"></script>
</head>
    <body>
        <header class="header flex">
            <div class="header-logo flex">
                <img src="Portfolio-use-image/WebSiteLogo.jpg" alt="TDAとそのロゴ">
                <p>TDA</p>
            </div>
        </header>
        <section class="main-visual">
            <h1>タスクの新規追加画面</h1>
            <form action="?" method="post">
                <div class="input-container-set">
                    <div class="input-container">
                        <label for="username">タイトル<br>
                        <span>※１５文字以内</span></label><br>
                        <input type="text" id="username" name="username" maxlength="15">
                    </div>
                    <div class="input-container">
                        <label for="email">内容<br>
                        <span>※１５０文字以内</span></label><br>
                        <textarea name="content" id="content" cols="42" rows="8"></textarea>
                    </div>
                    <div class="input-container">
                        <label for="password">期限日</label><br>
                        <!-- <span>※</span></label><br> -->
                        <input type="date" id="password" name="password" maxlength="12" pattern="^[!-~]{1,12}$">
                    </div>
                </div>
                <div class="button-set">
                    <a href="">新規追加せずにタスク一覧に戻る</a><br>
                    <!-- <button type="submit" formaction="RegisterServlet">新規登録する</button><br> -->
                    <button type="submit" formaction="index.html">新規追加</button>
                    <!-- <button type="submit" formaction="index.html" formnovalidate>新規追加</button> -->
                </div>
            </form>
        </section>
    </body>
</html>