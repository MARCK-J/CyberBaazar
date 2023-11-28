<template>
    <div>
      <h1>Respuesta de la API</h1>
      <pre>{{ responseData }}</pre>
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
      this.callApi();
    },
    methods: {
      async callApi() {
        const encodedParams = new URLSearchParams();
        encodedParams.set('source', 'amazon');
        encodedParams.set('country', 'de');
        encodedParams.set('values', 'iphone 11');
  
        const options = {
          method: 'POST',
          url: 'https://price-analytics.p.rapidapi.com/search-by-term',
          headers: {
            'content-type': 'application/x-www-form-urlencoded',
            'X-RapidAPI-Key': '7f26c57ed7msh1b75ef0bb587240p1e019djsn66f587f31b02',
            'X-RapidAPI-Host': 'price-analytics.p.rapidapi.com',
          },
          data: encodedParams,
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
  