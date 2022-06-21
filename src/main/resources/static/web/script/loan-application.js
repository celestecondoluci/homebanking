Vue.createApp({
    data() {
      return {
        accounts:[],
        loans:[],
        loanHipotecario:[],
        loanPersonal:[],
        loanAutomotriz:[],
        idLoanSolicitado:0,
        amountLoanSolicitado:0,
        paymentsLoanSolictado:0,
        accountDestinationLoanSolicitado:"",
        montoInteres:0,
        montoPorMes:0,
      }

    },
    created(){
      axios.get('http://localhost:8080/api/loans')
      .then(datos => {
          this.loans = datos.data
          console.log(this.loans)
          this.loanHipotecario = this.loans.filter(loan => loan.id == 1)
          this.loanAutomotriz = this.loans.filter(loan => loan.id == 3)
          console.log(this.loanHipotecario)
          this.loanPersonal = this.loans.filter(loan => loan.id == 2)
        })
        axios.get('http://localhost:8080/api/clients/current')
        .then(datos => {
          this.accounts = datos.data.accounts
            console.log(this.accounts)
        })
},
methods:{
  solicitarPrestamo(){
    console.log(this.idLoanSolicitado)
    console.log(this.amountLoanSolicitado)
    console.log(this.accountDestinationLoanSolicitado)
    console.log(this.paymentsLoanSolictado)
    axios.post('/api/loans',
    {"idLoan":this.idLoanSolicitado, 
    "amount":this.amountLoanSolicitado,
    "payments":this.paymentsLoanSolictado,
    "accountNumberDestination":this.accountDestinationLoanSolicitado})
    .then(() => console.log('created'))
    .catch(error => 
      swal("No se pudo realizar el prestamo"))
  },
  CerrarSesion() {
    axios.post('/api/logout')
      .then(response => window.location.href = "http://localhost:8080/web/index.html")
  },

},
computed:{
  montoConIntereses(){
    if(this.idLoanSolicitado == 1){
    let veintePorCiento = Math.floor(this.amountLoanSolicitado*20)/100
    this.montoInteres = this.amountLoanSolicitado + veintePorCiento   
    this.montoPorMes = Math.ceil(this.montoInteres / this.paymentsLoanSolictado)}
    if(this.idLoanSolicitado == 2){
    let diezPorCiento = Math.floor(this.amountLoanSolicitado*10)/100
    this.montoInteres = this.amountLoanSolicitado + diezPorCiento   
    this.montoPorMes = Math.ceil(this.montoInteres / this.paymentsLoanSolictado)
    }
    if(this.idLoanSolicitado == 3){
      let treintaPorCiento = Math.floor(this.amountLoanSolicitado*30)/100
      this.montoInteres = this.amountLoanSolicitado + treintaPorCiento   
      this.montoPorMes = Math.ceil(this.montoInteres / this.paymentsLoanSolictado)
    }
  },
}
}).mount('#app') 

