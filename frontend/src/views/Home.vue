<template>
  <div class="container">
      <dropdown />
  </div>
</template>

<script lang="ts">
import Dropdown from "../components/Dropdown.vue";

export default {
  name: "home-view",
  components: {
    Dropdown,
  },
};
</script>

<style lang="css" scoped>
.container {
  background-color: rgb(94, 94, 94);
  display: flex;
  max-width: inherit;
  height: 100vh; /* Ocupa todo el alto de la ventana */ /* Centra el contenido verticalmente */
}
</style>



