Vue.createApp({
    data() {
      return {
        card1:[],
        card2:[],
        cards:[],
        cardDebit:[],
        cardCredit:[],
        fecha:[],
      }
    },
    created() {
      axios.get('http://localhost:8080/api/clients/current')
        .then(datos => {
          this.card1 = datos.data.cards[0]
          this.card2 = datos.data.cards[1]
          this.cards = datos.data.cards
          this.cardDebit = this.cards.filter(card => card.type == 'DEBIT' && card.disable == false)
          this.cardCredit = this.cards.filter(card => card.type == 'CREDIT' && card.disable == false)
          // console.log(this.cardDebit)
        })

    },
    methods:{
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
            .then(response => window.location.href = "http://localhost:8080/web/index.html")
        },
        deshabilitarTarjetas(id){
          axios.patch('/api/clients/current/cards',
          `id=${id}`,{
            headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => window.location.href = "http://localhost:8080/web/card.html",
            console.log('card deleted')
            )
        },
        fechaAComparar(fechaDeCard){
          fechaDeCard = new Date(fechaDeCard);
          this.fecha = new Date();
            console.log(this.fecha);
            console.log(fechaDeCard)
           let fechaCard = this.fecha > fechaDeCard
           console.log(fechaCard)
           return fechaCard
        },
      }     
  
  
  }).mount('#app')
