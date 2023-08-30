<template>
    <div class="chat-room-list">
        <h1>Chat Room List</h1>
        <button @click="goToCreateChatRoom"> Create Chat Room</button>

        <ul>
            <li v-for="(room, index) in chatRooms" :key="index" @click="enterChatRoom(room)">
                {{ room.roomName }}
            </li>
        </ul>
    </div>
</template>

<script>
import router from "../../frontend/src/script/router";
import axios from "axios";


export default {

    data () {
        return {
            chatRooms: []
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
            axios.get("http://localhost:8080/api/chat/rooms").then(response => {
              this.chatRooms = response.data
            })
        },
        createRoom() {
          axios.post("http://localhost:8080/api/chat/room")
        },
        enterChatRoom() {
            router.push({path: "/chat-room-detail" })
        }
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