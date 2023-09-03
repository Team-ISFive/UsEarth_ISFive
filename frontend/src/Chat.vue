<template>
    <div class="chat-room-list">
        <h1>Chat Room List</h1>
        <button @click="goToCreateChatRoom"> Create Chat Room</button>

        <ul>
            <li v-for="(room, index) in chatRooms" :key="index" @click="openModal(room)">
                {{ room.roomName }}
            </li>
        </ul>
    </div>

    <!-- Chat Room Modal -->
    <div v-if="selectedRoom" class="chat-room-detail">
        <h2>{{ selectedRoom.roomName }}</h2>
        <p>{{ selectedRoom.description }}</p>
        <p>인원수: {{ selectedRoom.members }}</p>
        <button @click="enterChatRoom(selectedRoom)">채팅방 입장</button>
    </div>
</template>

<script>
import router from "../../frontend/src/script/router";
import axios from "axios";
import {mapActions} from "vuex";



export default {


    data () {
        return {
            chatRooms: [],
            selectedRoom: null
        }
    },
    created() {
        this.findAllRoom();
    },
    methods: {
        goToCreateChatRoom() {
            router.push('/create-chat-room'); // 적절한 경로로 변경
        },
        findAllRoom () {
            axios.get("/api/chat/rooms").then(response => {
              this.chatRooms = response.data
            })
        },
        openModal(room) {
            this.selectedRoom = room;
        },
        ...mapActions(['updateSharedData']),
        enterChatRoom(room) {
            this.updateSharedData(room.id); // 선택한 채팅방의 roomId를 스토어에 전달
            // 이후 채팅방으로 이동하는 로직 추가
            router.push({path: "/chat-room"})
        },
    }
};
</script>

<style>
.chat-room-list {
    text-align: center;
    margin: 50px auto;
}

ul {
    list-style-type: none;
    padding: 0;
}

li {
    margin: 10px;
    padding: 10px;
    border: 1px solid #ccc;
    cursor: pointer;
}
</style>