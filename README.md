# TODO管理Webアプリ（Servlet / JSP）
開始日：9月16日
終了日：11月10日

## 概要
Servlet / JSP を用いて作成したTODO管理Webアプリです。
さくらのVPS上にデプロイし、Apache + Tomcatで運用しています。

【デモ用ログイン情報】  
以下の情報ですぐに動作確認いただけます。  
・メールアドレス  
test.email@example.com  
・パスワード  
password123

## URL
https://takeehomepagerensyu.com/TaskManagementTool/

## 使用技術
- Java 21
- Servlet / JSP
- Apache Tomcat
- PostgreSQL
- Apache
- さくらのVPS（Linux）

## システム構成
- VPS上にApacheとTomcatを構築
- ApacheからTomcatへリバースプロキシ
- PostgreSQLを同一サーバ上に配置

## 機能
- ユーザー登録 / ログイン
- TODOの登録 / 編集 / 削除
- 入力チェック（バリデーション）

## 工夫した点
- MVCを意識してServletとJSPの役割を分離
- SQLインジェクション対策としてPreparedStatementを使用
- VPSへの手動デプロイを経験

## 苦労した点
- テンプレート内変数にnullを表示させないコーディング
- タスク期限日未設定での登録時のコーディング

## 学んだこと
- Webアプリはコードだけでなく
  サーバ設定も含めて動くことを実感した

## 今後の改善点
- Spring Boot化
- Docker化
- CI/CDの導入
