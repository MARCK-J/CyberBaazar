<template>
  <div class="container">
    <div class="left-component">
      <hero />
    </div>
    <div class="right-component">
      <home-content />
    </div>
  </div>
</template>

<script lang="ts">
import HomeContent from "../components/HomeContent.vue";
import Hero from "../components/Hero.vue";

export default {
  name: "home-view",
  components: {
    HomeContent
  },
};
</script>

<style lang="css" scoped>
.container {
  display: flex;
  max-width: fit-content; /* O el ancho que desees */ /* Puedes establecer un ancho máximo si es necesario */ /* Centra el contenedor horizontalmente */
}
</style>

