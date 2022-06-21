Vue.createApp({
    data() {
      return {
        JSonClientes: [],
        clients:[], ///array vacio
        nombreCliente:"", //variable de una propiedad
        apellidoCliente:"",
        mailCliente:"",
      }
    },
    created(){
      axios.get('/api/clients')              ///obtener la api
      .then(datos =>{this.clients = datos.data //.then se usa para las promesas control de lo que pasa si funciona
        console.log(datos.data)
        this.JSonClientes = datos.data ///darle el valor 
      })
    
    }, 
    methods:{
      crearCliente(){
        axios.post('/api/clients',{  //post para enviar datos
          firstName:this.nombreCliente, //: porque le asignas el valor a una propiedad de un objeto
          lastName:this.apellidoCliente,
          mail:this.mailCliente,
        })
        .then(function(response){   //response devuelve una promesa
          console.log(response)
        })
        .catch(function(error){ //"agarrar" errores
          console.log(error)
        });
      },
      eliminarCliente(id){
        axios.delete(id) ///eliminar mediante el parametro "id" que se encuentran en el link (cliente1,cliente2)
        .then(function(){
          location.reload(); //recargar
        })
      },      
     },
  }).mount('#app')