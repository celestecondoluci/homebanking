Vue.createApp({
    data() {
      return {
        tipoPrestamo:[],
        montoMaximo:[],
        cantidadCuotas:[],
        porcentajeInteres:[]

      }
    },
    created(){},
    methods:{
        crearPrestamo(){
            console.log(typeof(this.tipoPrestamo))
            console.log(typeof(this.montoMaximo))
            console.log(typeof(Array.from(this.cantidadCuotas)))
            console.log(typeof(this.porcentajeInteres))
            console.log(
            `name=${this.tipoPrestamo}&maxAmount=${this.montoMaximo}&payments=${this.cantidadCuotas}&percentage=${this.porcentajeInteres}`)
            axios.patch('/api/loans',`name=${this.tipoPrestamo}&maxAmount=${this.montoMaximo}&payments=${this.cantidadCuotas}&percentage=${this.porcentajeInteres}`)
            .then(() => alert('created'))
            }
    },

}).mount('#app')
