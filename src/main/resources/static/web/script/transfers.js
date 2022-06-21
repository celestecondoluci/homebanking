Vue.createApp({
    data() {
      return {
          amountTransfer:[],
          descriptionTransfer:[],
          numberAccountOrigin:[],
          numberAccountDestination:[],
          accounts:[],




          valueSelectOptions:[],
          cuentaPropia:[],
          cuentaTerceros:[],
          saldoActualCuenta:[],

      }},
      created(){
        axios.get('/api/clients/current')
        .then(datos => {
            this.accounts = datos.data.accounts
        })

        },
      methods:{
        realizarTransferencia(){
          axios.post('/api/clients/current/transactions',`amount=${this.amountTransfer}&description=${this.descriptionTransfer}&accountNumberOrigin=${this.numberAccountOrigin}&accountNumberDestination=${this.numberAccountDestination}`,{
            headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => window.location.reload(),
              console.log('transaction created'))
            .catch(error => 
              swal("No se pudo realizar la transferencia"))
        },
        CerrarSesion() {
          axios.post('/api/logout')
            .then(response => window.location.href = "/web/index.html")
        },
      },
      computed:{

      }
    }
).mount('#app')  
