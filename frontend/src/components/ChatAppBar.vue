<template>
    <v-app-bar

            color="white"
            :elevation="0"
    >
        <v-row
                class="app-bar"
                justify="space-between"
                no-gutters
                align="center"
        >
            <v-col cols="auto">
                <v-icon
                        size="24"
                        color="black"

                >
                    mdi-arrow-left
                </v-icon>
            </v-col>
            <v-col
                    cols="auto"
                    class="green-text"
                    text-value="appBarTitle"
            >

            </v-col>
            <v-col cols="auto">
                <div style="width: 20px;" />
            </v-col>
        </v-row>
    </v-app-bar>
</template>

<script>

export default {
    components: {

    },
    props: {
        appBarTitle: {
            type: String,
            default: null,
        },
    }
}
</script>

<style scoped>

</style>