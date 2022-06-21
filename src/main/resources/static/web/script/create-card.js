Vue.createApp({
    data(){
        return{
            tipoTarjeta:"",
            colorTarjeta:"",
            cards:[],
            cardDebit:[],
            cardCredit:[],
        }
    },
    created(){
    axios.get('/api/clients/current')
    .then(datos => {
        this.cards = datos.data.cards
        this.cardDebit = this.cards.filter(card => card.type == 'DEBIT' && card.disable == false)
        this.cardCredit = this.cards.filter(card => card.type == 'CREDIT' && card.disable == false)
    })
    },
    methods:{
        crearTarjeta(){
            axios.post('/api/clients/current/cards',`color=${this.colorTarjeta}&type=${this.tipoTarjeta}`,{
            headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => window.location.href = "/web/card.html",
            console.log('card created')
            )
        .catch(error => 
            swal("No puede solicitar otra tarjeta de este tipo"))
        },
        fechaFormateada(fecha){
            let date = new Date (fecha)

            let year = date.getFullYear()
            console.log(year)
             let newYear = Array.from(year.toString()).slice(-2).join() 
            let month = date.getMonth() + 1
            if (month < 10){
                month = "0" + month
            }
            let yearAndMonth = year + "/"+ month
            return yearAndMonth
        },
        CerrarSesion() {
          axios.post('/api/logout')
            .then(response => window.location.href = "/web/index.html")
        },
        
    },
}).mount('#app')