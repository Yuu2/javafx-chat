# Introduction
クリックしてくださって本当にありがとうございます。<br>
恥ずかしい実力ですがチャットサーバとクライアントをNIOチャンネルを利用して<br>
TCP/IPブロッキング方式で具現しています。ソースの環境は以下になります。<br>

* Java8 SE(JDK1.8.201)
* JavaFX 2.2
* MySQL 5.7.10


## Installation
作業中なのでまだ実行プログラム化できておりません。ですので実行させるには以下の`Plug-In`が必要となります。<br>
* JavaFX　<br>
`javafx.jar`<br>
(Java８SEには含まれています)<br>
* MySQL <br>
`mysql-connector-java-5.1.47`
https://dev.mysql.com/downloads/connector/j/ <br><br>
Build Pathを指定してください！ <br><br>
次はchatserver.sqlをデータベースにimportお願いします。

# Running
![1](https://user-images.githubusercontent.com/40384777/53419396-26260800-3a1d-11e9-9ed5-eae6318a2ece.png)　<br>
サーバの初期設定を行ってください <br>

`chatserver`  データベース名称<br>
`ByteBuffer`  チャンネルバッファーのサイズ<br><br>
![2](https://user-images.githubusercontent.com/40384777/53467617-552e8f00-3a9a-11e9-9408-84ece8273573.png) <br>

![3](https://user-images.githubusercontent.com/40384777/53716861-a45d3100-3e99-11e9-99e6-ddc62d80615e.png) <br>
chat-room

# Contributing

# FAQ

# Update
##### 2018/02/27
：ローグボタン（テキストファイル化させる機能)<br>
##### 2018/02/28
：耳打ち("/w") <br>
：ニックネーム空白検査 <br>
##### 2018/03/01
：固定的耳打ち機能<br>
：チャットルーム画面
##### 2018/03/04
![4](https://user-images.githubusercontent.com/40384777/53716852-a1fad700-3e99-11e9-8223-82f52ea0f1f1.png) <br>
:　IP遮断 <br>
:  チャットルームDB設定
# Plan
* chat-room
* 言語変更機能
