Vue.createApp({
    data(){
        return{
            emailUsuario:"",
            passwordUsuario:"",

            nombreNuevoCliente:"",
            apellidoNuevoCliente:"",
            emailNuevoCliente:"",
            passwordNuevoCliente:"",
        }
    },
    created(){
    },
    methods:{
        IniciarSesion(){
            console.log(this.emailUsuario)
            console.log(this.passwordUsuario)
            axios.post('/api/login',`email=${this.emailUsuario}&password=${this.passwordUsuario}`,{
                headers:{'content-type':'application/x-www-form-urlencoded'}})
                .then(response => window.location.href="/web/accounts.html")
                // .catch(error => console.log(error.response.data)
                // .swal("Oops!", "Algo salio mal")
                // ) 
    },
    
    RegistrarCliente(){
        axios.post('/api/clients',`firstName=${this.nombreNuevoCliente}&lastName=${this.apellidoNuevoCliente}&email=${this.emailNuevoCliente}&password=${this.passwordNuevoCliente}`,{
            headers:{'content-type':'application/x-www-form-urlencoded'}})
        .then(response => {
            this.emailUsuario = this.emailNuevoCliente
            this.passwordUsuario = this.passwordNuevoCliente
            this.IniciarSesion()
        })
        //  .catch(error => console.log(error.response.data)
        //  .swal("Oops!", "Algo salio mal")
        //  ) 
    }
},
}).mount('#app')