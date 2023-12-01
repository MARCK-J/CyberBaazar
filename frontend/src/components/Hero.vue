<template>
  <div class="hero-container">
    <div class="text-center hero">
      <h1 class="mb-4">CyberBaazar</h1>
      
      <p>This is the home page of CyberBaazar.</p>

      <div v-if="responseData && responseData.status === 'finished' && responseData.results.length > 0">
        <div v-for="(result, resultIndex) in responseData.results" :key="resultIndex" class="result-item">
          <!-- Iterar sobre todas las ofertas -->
          <div v-for="(offer, offerIndex) in result.content.offers" :key="offerIndex" class="offer-item">
            <div class="item-container">
              <img :src="offer.image" alt="Product Image" class="product-image" />

              <div class="item-details">
                <p class="price">Price: {{ offer.price }}</p>
                <p class="name">{{ offer.name }}</p>
                <a :href="offer.link.href" target="_blank" class="view-link">View on Amazon</a>
              </div>
            </div>
          </div>
          <!-- Fin del bucle de ofertas -->

        </div>
      </div>

    </div>
  </div>
</template>


<script lang="ts">
import axios from 'axios';
  
export default {
  data() {
    return {
      responseData: null,
    };
  },
  mounted() {
    this.pollApi();
  },
  methods: {
    async pollApi() {
      const url = `https://price-analytics.p.rapidapi.com/poll-job/6569f8ebb78a375ada7694c4`;

      const options = {
        method: 'GET',
        url,
        headers: {
          'X-RapidAPI-Key': '7f26c57ed7msh1b75ef0bb587240p1e019djsn66f587f31b02',
          'X-RapidAPI-Host': 'price-analytics.p.rapidapi.com',
        },
      };

      try {
        const response = await axios.request(options);
        this.responseData = response.data;
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>

<style scoped>
.hero-container {
  background-color: rgb(255, 255, 255);
  color: white;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.product-image {
  width: 100px; /* Ancho de la imagen */
  height: 100px; /* Altura de la imagen */
}

.result-item {
  width: 100%; /* Ocupar todo el ancho de la pantalla */
}

.offer-item {
  margin-bottom: 20px; /* Espacio entre cada elemento */
  border-bottom: 1px solid white; /* Línea separadora entre elementos */
  padding-bottom: 20px; /* Espacio en la parte inferior de cada elemento */
}

.item-container {
  display: flex;
  align-items: center;
}

.item-details {
  margin-left: 20px; /* Ajusta el margen según tus necesidades */
}

.price, .name {
  color: black; /* Color de la tipografía para el precio, nombre y enlace */
}

.view-link {
  color: rgb(53, 20, 238)4)55, 0, 0); /* Color de la tipografía para el enlace */
}
</style>