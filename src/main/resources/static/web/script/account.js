Vue.createApp({
    data() {
      return {  
          transactions:[],
          numberAccounts:[],
          transactionsOrdenadas:[],
          accounts:[],

      }
    },
    created(){
        const params = new Proxy(new URLSearchParams(window.location.search), {
            get: (searchParams, id) => searchParams.get("id"),
          });
          // Get the value of "some_key" in eg "https://example.com/?some_key=some_value"
          let value = params.some_key; 
          console.log(value)   
          axios.get('http://localhost:8080/api/accounts/' + value)
         .then(datos => {
             this.transactions = datos.data.transactions
             console.log(this.transactions)
             this.numberAccounts = datos.data.number
             console.log(this.numberAccounts)
            this.transactionsOrdenadas = this.ordenarTransactions()
            this.transactionsOrdenadas = this.transactionsOrdenadas.filter(transaction => transaction.disable == false)
            console.log(this.transactionsOrdenadas)
            this.accounts = datos.data.accounts

             
         })
        },
    methods:{
        ordenarTransactions(){
        let auxiliar = this.transactions
        auxiliar.sort((firstId, secondId) => secondId.id - firstId.id);
        return auxiliar
        },
        CerrarSesion() {
            axios.post('/api/logout')
              .then(response => window.location.href = "http://localhost:8080/web/index.html")
          }
    },
 
}).mount('#app')