<template>
  <div class="hero-container">
    <div class="text-center hero">
      <h1 class="mb-4">CyberBaazar</h1>
      
      <p>This is the home page of CyberBaazar.</p>

      <pre>{{ responseData }}</pre>
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
      const job_id = 'el_id_de_tu_trabajo'; // Reemplaza esto con el ID real de tu trabajo
      const url = `https://price-analytics.p.rapidapi.com/poll-job/6564ef348de040452f26e0a9`;

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
  background-color: black;
  color: white;
  height: 100vh;
  display: flex;
  flex-direction: ;
  justify-content: center;
  align-items: center;
}

.hero p {
  font-size: 15px;
}
</style>
