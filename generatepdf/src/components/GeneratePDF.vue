<template>
    <div>
        <h1>Hola</h1>
    </div>
</template>

<script>
import UserService from '../service/UserService'
export default {
    name : 'GeneratePDF',
    data(){
        return{
            users : null
        }
    },
    usersService : null,
    created(){
        this.usersService = new UserService();
    },
    mounted(){
        this.usersService.getAll().then(data =>{
            console.log(data);
        })
    }
}
</script>

<style scoped>

</style>