# ☕ Java Multiplayer Game from First Year ☕
- Old Java multiplayer game from my first year of college during the pandemic.
- Game created with Cherish Magpayo. 

## ✏️ Project Description
- Final project for CSCI 22 class
- Dealt with creating a multiplayer game via Java networking and creating a game server that two local clients can connect to
- It is also possible to run the game server via an AWS EC2 instance online

## 📁 How to Run

### OPTION 1: Batch Files
1. You may opt to first run the server by opening the:
<br>`run_server.bat`
2. This will automatically compile and run the Java files. 
3. Now you can also create two players by opening:
<br>`run_player.bat`
4. You will run this twice, once for Player 1 and once for Player 2. 
5. The details when running each player are as follows: 
<br>IP Address: `127.0.0.1`
<br>Port Number: `55555`

### If you wish to compile the files yourself, follow these steps instead:
### OPTION 2: Run the Server
1. First, compile the Java files for the server with the following command: 
<br>`javac game/GameServer.java`
2. Run the server:
<br>`java game.GameServer`

### OPTION 2: Run the Players
1. In another command prompt, run the following command: 
<br>`javac game/*.java game/components/*.java`
2. When asked for the IP Address, type in the localhost/loopback address of your device: 
<br>`127.0.0.1`
3. For the port number, input: 
<br>`55555`
4. Run another instance of the cmd to create the second player

## 📷 Sample Screenshots

### Server and Player CMD Running
- <img width="1919" height="1200" alt="bewitch screenshot" src="https://github.com/user-attachments/assets/73b8c125-748f-4fab-9d00-379d95b72073" />

### Two Players Ran through the local server
- <img width="1288" height="509" alt="bewitch screenshot2" src="https://github.com/user-attachments/assets/27252a00-9db6-412e-bee6-10ca4b470935" />
