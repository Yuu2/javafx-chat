# Introduction
: 채팅서버와 채팅클라이언트를 NIO채널을 이용해서 <br>
TCP/IP 블로킹 방식으로 구현하고 있습니다. <br>
// チャットサーバとクライアントをNIOチャンネルを利用して<br>
TCP/IPブロッキング方式で具現しています。ソースの環境は以下になります。<br>

* Java8 SE(JDK1.8.201)
* JavaFX 2.2
* MySQL 5.7.10


## Installation
: 작업중이므로 실행프로그램화 되어있지 않습니다. 그러므로 실행하기 위해서는 아래의 `플러그인`이 필요합니다.<br>
// 作業中なのでまだ実行プログラム化できておりません。ですので実行させるには以下の`プラグイン`が必要となります。<br>
* JavaFX　<br>
`javafx.jar`<br>
: 자바 8에는 포함되어있습니다.<br>
// Java８SEには含まれています<br>
* MySQL <br>
`mysql-connector-java-5.1.47`
https://dev.mysql.com/downloads/connector/j/ <br><br>
: Build Path를 지정해주세요. <br>
// Build Pathを指定してください。<br><br>

: 다음은 chatserver.sql을 mysql 데이터베이스에 불러와주세요.<br>
// 次はchatserver.sqlをmysqlデータベースにimportお願いします。

# Running
![1](https://user-images.githubusercontent.com/40384777/53419396-26260800-3a1d-11e9-9ed5-eae6318a2ece.png)　<br>
: 서버 초기설정이 필요합니다.<br>
// サーバの初期設定を行ってください <br>

`chatserver`  채팅 서버 명칭(データベース名称)<br>
`ByteBuffer`  채널 버퍼 사이즈(チャンネルバッファーのサイズ)<br><br>
![2](https://user-images.githubusercontent.com/40384777/53467617-552e8f00-3a9a-11e9-9408-84ece8273573.png) <br>

![3](https://user-images.githubusercontent.com/40384777/53716861-a45d3100-3e99-11e9-99e6-ddc62d80615e.png) <br>
chat-room

# Contributing

# FAQ

# Update
##### 2018/02/27
: 로그버튼 (텍스트파일화)<br>
// ローグボタン（テキストファイル化させる機能)<br>
##### 2018/02/28
: 귓속말 <br>
// 耳打ち("/w") <br>
: 닉네임 유효성검사 <br>
// ニックネーム空白検査 <br>
##### 2018/03/01
: 고정 귓속말<br>
// 固定耳打ち機能<br>
: 채팅룸 화면<br>
// チャットルーム画面<br>

##### 2018/03/04
![4](https://user-images.githubusercontent.com/40384777/53716852-a1fad700-3e99-11e9-8223-82f52ea0f1f1.png) <br>
: 아이피차단<br>
// IP遮断 <br>
: 채팅룸 DB 설정
// チャットルームDB設定
# TODO
* chat-room
* selectable language
