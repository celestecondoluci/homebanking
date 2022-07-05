Vue.createApp({
    data() {
      return {
        clients: [],
        accounts: [],
        account1: [],
        account2: [],
        loans:[],
        cards:[],
        transactions:[],
        accountsOrdenadas:[],
        tipoCuenta:[],
      }
    },
    created() {
      axios.get('/api/clients/current')
        .then(datos => {
          this.clients = datos.data
          this.accounts = datos.data.accounts
          this.account1 = datos.data.accounts[0]
          // console.log(this.account1)
          this.account2 = datos.data.accounts[1]
          // console.log(this.account2)
          this.loans = datos.data.loans
          console.log(this.loans)
          this.cards = datos.data.cards
          console.log(datos.data.accounts)
          this.traerTransactions()
          this.ordenarTransactions()
          console.log(this.transactions)
          this.accountsOrdenadas = this.ordenarAccounts()
          this.accountsOrdenadas = this.accountsOrdenadas.filter(account => account.disable == false)
        })
        axios.get('/api/clients/current',
        {headers:{'accept':'application/xml'}})
        .then(response =>
        console.log(response.data))
    },
    methods:{
      CerrarSesion() {
        axios.post('/api/logout')
          .then(response => window.location.href = "/web/index.html")
      },
      ordenarAccounts(){
        let auxiliar = this.accounts
        auxiliar.sort((firstId, secondId) => firstId.id - secondId.id);
        return auxiliar
        },
      traerTransactions(){
        this.accounts.forEach(account => {
          account.transactions.forEach(transaction => {
            this.transactions.push(transaction)
          })
        });
      },
      ordenarTransactions(){
        this.transactions.sort((firstId, secondId) => secondId.id - firstId.id);
      },
      crearCuenta(){
        axios.post('/api/clients/current/accounts',
        `type=${this.tipoCuenta}`,{
          headers:{'content-type':'application/x-www-form-urlencoded'}
        }
        )
        .then(response => window.location.reload())
      },
      deshabilitarCuentas(id){
        axios.patch('/api/clients/current/accounts',
            `id=${id}`,{
            headers:{'content-type':'application/x-www-form-urlencoded'}})
             .then(response => axios.patch('/api/clients/current/transactions',
              `id=${id}`,{
              headers:{'content-type':'application/x-www-form-urlencoded'}}),
              window.location.reload(),
              console.log('account deleted')
              )
        }
        }
      }
      
  
  ).mount('#app')  