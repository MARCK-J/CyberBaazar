<template>
    <div class="container">
      <div class="left-container">
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
        <p class="mt-2">Loading...</p>
      </div>

      <!-- Mostrar resultados cuando se obtenga el jobId -->
      <div v-else>
        <div v-if="pricesData && pricesData.data && pricesData.data.length > 0">
          <div v-for="(price, index) in pricesData.data" :key="index" class="price-item">
            <img :src="price.image" alt="Product Image" class="product-image" />
            <div class="price-details">
              <p class="price">Price: ${{ price.price }}</p>
              <p class="name">{{ price.name }}</p>
              <a :href="price.urlPrice" target="_blank" class="url-link">View on Amazon</a>
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
  border: 3px solid #ccc;
  padding: 20px;
}

/* Estilos para el contenedor derecho */
.right-container {
  max-width: 70%;
  max-height: 90%; /* Ajusta el ancho según tus necesidades */
  background-color: rgb(94, 94, 94);
  justify-content: left;
}
/* Estilos comunes para los componentes */
.combobox,
.textfield,
button {
  width: 250px; /* O el ancho que desees */
  padding: 1px; /* Ajusta el relleno según tus necesidades */
}
/* Estilos para el combobox */
.combobox {
  margin-top: 40px;
}

/* Estilos para el textfield */
.textfield {
  margin-top: 40px;
}

/* Estilos para el botón */
button {
  margin-top: 40px;
}

/* Estilos para el contenedor de precios */
.price-item {
  display: flex;
  margin-bottom: 20px;
}

/* Estilos para la imagen del producto */
.product-image {
  width: 100px; /* Ajusta el tamaño de la imagen según tus necesidades */
  height: auto;
  margin-right: 10px;
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
}

/* Estilos para el nombre del producto */
.name {
  font-size: 16px; /* Ajusta el tamaño de la fuente según tus necesidades */
  margin-bottom: 5px;
}

/* Estilos para el enlace URL */
.url-link {
  font-size: 14px; /* Ajusta el tamaño de la fuente según tus necesidades */
  color: blue; /* Puedes cambiar el color según tus necesidades */
  text-decoration: underline;
}

.spinner-border {
  width: 13rem;
  height: 13rem;
}

</style>