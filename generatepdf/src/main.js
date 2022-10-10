require('./bootstrap');
window.Vue  = require('vue');
import Vue from 'vue';
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import GeneratePDF from './components/GeneratePDF.vue'

Vue.component("Datatable", DataTable);
Vue.component("Column", Column);

const app = new Vue({
    el: '#app',
    components:{GeneratePDF}
})
