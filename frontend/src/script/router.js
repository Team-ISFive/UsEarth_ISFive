import Chat from "@/Chat.vue";
import ChatRoom from "@/ChatRoom.vue";
import {createRouter, createWebHistory} from "vue-router";
import CreateChatRoom from "@/CreateChatRoom.vue";

const routes = [
    {path:'/chat', component: Chat},
    {path:'/chat-room', component :ChatRoom},
    {path: '/create-chat-room', component: CreateChatRoom}
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;