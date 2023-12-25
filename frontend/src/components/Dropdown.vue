<template>
  <div class="mt-5">
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
</template>

<script lang="ts">
import axios from "axios";

export default {
  data() {
    return {
      estores: [],
      countries: [],
      selectedEstore: "", // Para almacenar la opción seleccionada en el primer combobox
      selectedCountry: "", // Para almacenar la opción seleccionada en el segundo combobox
      inputValue: "",
    };
  },
  methods: {
    handleButtonClick() {
      // Lógica para manejar el clic en el botón
      console.log("Selected estore:", this.selectedEstore);
      console.log("Selected country:", this.selectedCountry);
      console.log("Button clicked. Input value:", this.inputValue);

      // Realizar la solicitud POST
      this.sendPostRequest();
    },

    async fetchData(url, targetArray) {
      // Realiza la solicitud HTTP al endpoint y actualiza el array objetivo
    try {
      const response = await axios.get(url);
      this[targetArray] = response.data.data; // Asigna los datos al array objetivo
    } catch (error) {
      console.error("Error al obtener los datos:", error);
    }
  }, 

    sendPostRequest() {
      // Objeto para el cuerpo de la solicitud
      const requestBody = {
        source: this.selectedEstore.toLowerCase(), // Convertir a minúsculas
        country: this.selectedCountry.toLowerCase(), // Convertir a minúsculas
        values: this.inputValue,
      };

      // Realizar la solicitud POST
      axios
        .post("http://localhost:8080/api/v1/jobs/call-external-api", requestBody)
        .then((response) => {
          console.log("POST request successful:", response.data);
        })
        .catch((error) => {
          console.error("Error in POST request:", error);
        });
    },

    // Método para manejar el cambio en el segundo combobox
    handleCountryChange() {
      // Lógica adicional si es necesario

      // Ejemplo: Mostrar un mensaje en la consola al cambiar el país
      console.log("Selected country changed:", this.selectedCountry);
    },

    handleEstoreChange() {
      // Al seleccionar un estore, desbloquea el segundo combobox y carga los países correspondientes
      this.fetchData("http://localhost:8080/api/v1/countries/", "countries");
    },
  },
  async mounted() {
    // Llama a la función fetchData al montar el componente para obtener los estores
    await this.fetchData("http://localhost:8080/api/v1/estores/", "estores");
  }, 
};
</script>

<style scoped>
/* Estilos para el contenedor principal */
.combobox-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
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
  margin-top: 20px;
}

/* Estilos para el textfield */
.textfield {
  margin-top: 20px;
}

/* Estilos para el botón */
button {
  margin-top: 20px;
}

/* Estilos para el espaciador */
.spacer {
  margin-top: 20px;
}
</style>






