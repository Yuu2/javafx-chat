## Introduction
: JavaFx를 이용한 간단한 채팅앱. NIO채널을 이용해서 TCP/IP 블로킹 방식으로 구현하고 있습니다. <br>

## Installation
: 작업중이므로 실행프로그램화 되어있지 않습니다. 그러므로 실행하기 위해서는 아래의 `플러그인`이 필요합니다.<br>
* JavaFX　<br>
`javafx.jar`<br>
* MySQL <br>
`mysql-connector-java-5.1.47`
https://dev.mysql.com/downloads/connector/j/ <br><br>

다음은 chatserver.sql을 mysql 데이터베이스에 불러와주세요.<br>


## Running
![1](https://user-images.githubusercontent.com/40384777/53419396-26260800-3a1d-11e9-9ed5-eae6318a2ece.png)　<br>
: 서버 초기설정이 필요합니다.<br>

`chatserver`  채팅 서버 명칭<br>
`ByteBuffer`  채널 버퍼 사이즈<br><br>
![2](https://user-images.githubusercontent.com/40384777/53467617-552e8f00-3a9a-11e9-9408-84ece8273573.png) <br>

![3](https://user-images.githubusercontent.com/40384777/53716861-a45d3100-3e99-11e9-99e6-ddc62d80615e.png) <br>
chat-room

## Contributing

## FAQ

## Updated
##### 2018/02/27
: 로그버튼 (텍스트파일화)<br>
##### 2018/02/28
: 귓속말 <br>
: 닉네임 유효성검사 <br>
##### 2018/03/01
: 고정 귓속말<br>
: 채팅룸 화면<br>
##### 2018/03/04
![4](https://user-images.githubusercontent.com/40384777/53716852-a1fad700-3e99-11e9-8223-82f52ea0f1f1.png) <br>
: 아이피차단<br>
: 채팅룸 DB 설계
