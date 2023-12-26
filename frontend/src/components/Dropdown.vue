<template>
    <div class="container">
      <div class="left-container">
      <div>
        <h1>Search for a product</h1>
        <p></p>
      </div>
        <div class="combobox-wrapper">
          <!-- Primer Combobox -->
          <select v-model="selectedEstore" class="form-select combobox" @change="handleEstoreChange">
            <option disabled value="">Select a E-store</option>
            <option v-for="estore in estores" :key="estore.storeId" :value="estore.name">
              {{ estore.name }}
            </option>
          </select>

          <!-- Márgen entre los combobox -->
          <div class="spacer"></div>

          <!-- Segundo Combobox -->
          <select v-model="selectedCountry" class="form-select combobox" :disabled="!selectedEstore" @change="handleCountryChange">
            <option disabled value="">Select a Country</option>
            <option v-for="country in countries" :key="country.countryId" :value="country.code">
              {{ country.name }}
            </option>
          </select>

          <!-- Márgen entre el segundo combobox y el textfield -->
          <div class="spacer"></div>

          <!-- Textfield -->
          <input type="text" v-model="inputValue" class="form-control textfield" placeholder="Search a product..." />

          <!-- Márgen entre el textfield y el botón -->
          <div class="spacer"></div>

          <!-- Botón -->
          <button @click="handleButtonClick" class="btn btn-primary">Search</button>
        </div>
      </div>
      <!-- Contenedor derecho con resultados -->
      <!-- Contenedor derecho con resultados -->
    <div class="right-container">
      <!-- Mostrar el spinner de carga mientras se obtiene el jobId -->
      <div v-if="loading" class="text-center mt-5">
        <div class="spinner-border text-primary" role="status">
          <span class="sr-only">Loading...</span>
        </div>
      </div>

      <!-- Mostrar resultados cuando se obtenga el jobId -->
      <!-- Mostrar resultados cuando se obtenga el jobId -->
      <div v-else>
        <div v-if="pricesData && pricesData.data && pricesData.data.length > 0">
          <div class="row">
            <div
              v-for="(price, index) in pricesData.data"
              :key="index"
              class="col-md-6 price-item"
            >
              <div class="price-item-border">
                <!-- Contenido actual del price-item -->
                <div class="d-flex flex-column align-items-center">
                  <img
                    :src="price.image"
                    alt="Product Image"
                    class="product-image"
                  />
                  <div class="price-details text-center">
                    <p class="price">Price: ${{ price.price }}</p>
                    <p class="name">{{ price.name }}</p>
                    <a
                      :href="price.urlPrice"
                      target="_blank"
                      class="url-link"
                      >Link to the Product</a
                    >
                  </div>
                </div>
                <!-- Fin del contenido del price-item -->
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import axios from "axios";

export default {
  data() {
    return {
      estores: [],
      countries: [],
      selectedEstore: "",
      selectedCountry: "",
      inputValue: "",
      jobId: null,
      pricesData: null,
      loading: false, // Nueva propiedad para almacenar la información de precios
    };
  },
  methods: {
    handleButtonClick() {
      this.sendPostRequest();
    },

    async fetchData(url, targetArray) {
      try {
        const response = await axios.get(url);
        this[targetArray] = response.data.data;
      } catch (error) {
        console.error("Error al obtener los datos:", error);
      }
    },

    sendPostRequest() {
      this.loading = true;

      const requestBody = {
        source: this.selectedEstore.toLowerCase(),
        country: this.selectedCountry.toLowerCase(),
        values: this.inputValue,
      };

      axios
        .post("http://localhost:8080/api/v1/jobs/call-external-api", requestBody)
        .then((response) => {
          this.jobId = response.data.data.job_id;
          this.fetchPricesByJobId();
        })
        .catch((error) => {
          console.error("Error in POST request:", error);
          this.loading = false;
        });
    },

    async fetchPricesByJobId() {
      try {
        const response = await axios.get(`http://localhost:8080/api/v1/prices/by-job-id/${this.jobId}`);
        this.pricesData = response.data;
      } catch (error) {
        console.error("Error fetching prices data:", error);
      } finally {
        this.loading = false;
      }
    },

    handleCountryChange() {
      console.log("Selected country changed:", this.selectedCountry);
    },

    handleEstoreChange() {
      (async () => {
        await this.fetchData("http://localhost:8080/api/v1/countries/", "countries");
      })();
    },
  },
  async mounted() {
    await this.fetchData("http://localhost:8080/api/v1/estores/", "estores");
  },
};
</script>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Digital:wght@400&display=swap');

/* Estilos para el contenedor principal */
.container {
  display: flex;
  max-width: inherit;
  max-height: inherit;
  align-items: center;
}

/* Estilos para el contenedor izquierdo */
.left-container {
  width: 20%; /* Ajusta el ancho según tus necesidades */
  margin-right: auto;
  border: 2px solid #4e8cff;
  border-radius: 15px;
  padding: 20px;
  position: sticky;
  top: 0;
}

/* Estilos para el contenedor derecho */
.right-container {
  max-width: 70%;
  max-height: 90%; /* Ajusta el ancho según tus necesidades */
  justify-content: left;
  overflow-y: auto;
  padding: 20px; 
}
/* Estilos comunes para los componentes */
.combobox,
.textfield,
button {
  width: 250px; /* O el ancho que desees */
  padding: 1px;
  background-color: #cde3ff; /* Color celeste suave de fondo */
  border: 1px solid #0052eb; /* Borde con tono de celeste */
  border-radius: 5px; /* Bordes redondeados */
  color: #2b5a8a; /* Ajusta el relleno según tus necesidades */
}
/* Estilos para el combobox */
.combobox {
  margin-top: 45px;
}

/* Estilos para el textfield */
.textfield {
  margin-top: 45px;
}

/* Estilos para el botón */
button {
  margin-top: 45px;
  cursor: pointer;
}

/* Estilos adicionales para resaltar el botón al pasar el mouse */
button:hover {
  background-color: #4e8cff; /* Cambia el color al pasar el mouse */
  color: #fff; /* Cambia el color del texto al pasar el mouse */
}

/* Estilos actualizados para el contenedor de precios */
.price-item {
  display: flex;
  margin-bottom: 20px;
  justify-content: space-around;
}

.price-item-border {
  border: 2px solid #4e8cff; /* Color celeste del borde */
  padding: 10px; /* Espaciado interior */
  margin-bottom: 20px; /* Espaciado inferior entre elementos */
}

/* Estilos actualizados para la imagen del producto */
.product-image {
  width: 150px; /* Ajusta el tamaño de la imagen según tus necesidades */
  height: auto;
  margin-bottom: 10px;
}

/* Estilos para los detalles del precio */
.price-details {
  display: flex;
  flex-direction: column;
}

/* Estilos para el precio */
.price {
  font-size: 18px; /* Ajusta el tamaño de la fuente según tus necesidades */
  margin-bottom: 5px;
  font-family: 'Digital', sans-serif;
}

/* Estilos para el nombre del producto */
.name {
  font-size: 16px; /* Ajusta el tamaño de la fuente según tus necesidades */
  margin-bottom: 5px;
  font-family: 'Digital', sans-serif;
}

/* Estilos para el enlace URL */
.url-link {
  font-size: 18px; /* Ajusta el tamaño de la fuente según tus necesidades */
  color: rgb(144, 155, 255); /* Puedes cambiar el color según tus necesidades */
  text-decoration: underline;
  font-family: 'Arial', sans-serif;
}

.spinner-border {
  width: 13rem;
  height: 13rem;
  margin-right: 550px;
}

/* Nuevos estilos para la disposición de los resultados */
.row {
  display: flex;
  flex-wrap: wrap;
  margin-right: -15px;
  margin-left: -15px;
}

.col-md-6 {
  flex: 0 0 50%;
  max-width: 50%;
  padding-right: 15px;
  padding-left: 15px;
}

</style>