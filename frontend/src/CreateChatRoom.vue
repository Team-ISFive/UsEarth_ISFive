<template>
    <div>
        <h1>Create Chat Room</h1>
        <form @submit.prevent="createChatRoom">
            <label for="roomName">Room Name:</label>
            <input type="text" v-model="roomName" required>
            <br>
            <label for="roomDescription">Room Description:</label>
            <input type="text" v-model="description" required>
            <br>
            <button type="submit">Create Room</button>
        </form>
    </div>
</template>

<script>
import axios from 'axios';
import router from "@/script/router";

export default {
    data() {
        return {
            roomName: '',
            description: ''
        };
    },
    methods: {
        createChatRoom() {

            const dto = {
                name: this.roomName,
                description: this.description
            };

            axios.post('http://localhost:8080/api/chat/room', dto)
                .then(response => {
                    // 성공적으로 채팅방을 생성한 후에 할 작업
                    console.log('Chat room created successfully:', response.data);
                    router.push({path: "/chat"})
                })
                .catch(error => {
                    // 채팅방 생성 중 오류 발생 시에 할 작업
                    console.error('Error creating chat room:', error);
                });
        }
    }
};
</script>

<style>
/* 필요한 스타일링 정의 */
</style>