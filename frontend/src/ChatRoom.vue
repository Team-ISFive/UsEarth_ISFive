<template>
    <div>
        <div class="chat-box">
            <div v-for="(message, index) in messages" :key="index" class="message">
                {{ message.message }}
            </div>
        </div>
        <div class="input-box">
            <input v-model="newMessage" @keyup.enter="sendMessage" placeholder="Type your message">
        </div>
    </div>
</template>

<script>
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import {mapGetters} from "vuex";
export default {
    computed: {
        ...mapGetters(['sharedData']), // Vuex의 sharedData 게터를 컴포넌트에 매핑
    },
    data() {
        return {
            stompClient: null,
            messages: [],
            newMessage: ''
        };
    },
    created() {
        const socket = new SockJS('http://localhost:8080/ws-stomp');
        // STOMP 클라이언트 인스턴스 생성
        this.stompClient = new Client({
            webSocketFactory: () => socket,
            connectHeaders: {
                // 연결 헤더 (옵션)
               // Authorization: 'Bearer ' + localStorage.getItem('token') // 토큰 등
            },
      //      reconnectDelay: 5000, // 재연결 딜레이
        //    heartbeatIncoming: 4000, // 수신 신호 주기
          //  heartbeatOutgoing: 4000 // 송신 신호 주기
        });

        this.stompClient.activate();
        this.stompClient.onConnect = () => {
            console.log(this.sharedData)
            console.log("STOMP connection established");
                // 구독 설정
                this.stompClient.subscribe(`/sub/chat/room/1`, message => {
                    console.log("메시지 도착")
                    this.messages = message;

                    // 메시지 처리 로직 추가
                });
                this.stompClient.publish({destination: "/pub/chat/message", body : JSON.stringify({type:'ENTER',roomId: this.sharedData, sender:this.sender})})
        };
    },
    methods: {

        sendMessage() {
            if (this.newMessage.trim() === '') return;
            this.stompClient.publish({destination: "/pub/chat/message", body : JSON.stringify({type:'TALK',roomId: this.sharedData, sender:this.sender, message: this.newMessage})})
        }
    }
};
</script>

<style scoped>
.chat-box {
    border: 1px solid #ccc;
    padding: 10px;
    max-height: 200px;
    overflow-y: auto;
}
.message {
    margin: 5px 0;
}
.input-box {
    margin-top: 10px;
}
</style>