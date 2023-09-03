<template>
    <div>
        <h1>{{ room.roomName }}</h1>
        <div class="chat-box">
            <li v-for="(message, index) in messages" :key="index" class="message">
                {{ message.message }}
            </li>
        </div>
        <div class="input-box">
            <input v-model="newMessage"  placeholder="Type your message">
            <button @click="sendMessage"> 보내기 </button>

        </div>
    </div>
</template>


<script>
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import {mapGetters} from "vuex";
import axios from "axios";
export default {
    computed: {
        ...mapGetters(['sharedData']), // Vuex의 sharedData 게터를 컴포넌트에 매핑
    },
    data() {
        return {
            room:'',
            stompClient: null,
            messages: [],
            newMessage: ''
        };
    },
    created() {
        this.findRoom();
        const socket = new SockJS('http://localhost:8080/ws-stomp');
        // STOMP 클라이언트 인스턴스 생성
        this.stompClient = new Client({
            webSocketFactory: () => socket,

            reconnectDelay: 5000, // 재연결 딜레이
            heartbeatIncoming: 4000, // 수신 신호 주기
            heartbeatOutgoing: 4000 // 송신 신호 주기
        });
        this.stompClient.activate();


        this.stompClient.onConnect = () => {
            console.log(this.sharedData)
            console.log("STOMP connection established");
                // 구독 설정
                this.stompClient.subscribe(`/sub/chat/room/${this.sharedData}`, message => {
                    const jsonMessage = JSON.parse(message.body);
                    this.messages.push(jsonMessage);
                    console.log(jsonMessage)

                    // 메시지 처리 로직 추가
                });
                this.stompClient.publish({destination: "/pub/chat/message", body : JSON.stringify({type:'ENTER',roomId: this.sharedData, sender:this.sender})})
        };
    },
    methods: {

        sendMessage() {
            if (this.newMessage.trim() === '') return;
            this.stompClient.publish({destination: "/pub/chat/message", body : JSON.stringify({type:'TALK',roomId: this.sharedData, sender:this.sender, message: this.newMessage})})
            this.newMessage = '';
        },
        findRoom() {
            axios.get(`/api/chat/room/${this.sharedData}`).then(response => {
                    this.room = response.data
                })
        }
    },

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