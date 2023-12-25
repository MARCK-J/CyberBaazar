<template>
  <div class="container">
    <div class="left-component">
      <dropdown />
    </div>
    <div class="right-component">
      <home-content />
    </div>
  </div>
</template>

<script lang="ts">
import HomeContent from "../components/HomeContent.vue";
import Dropdown from "../components/Dropdown.vue";

export default {
  name: "home-view",
  components: {
    HomeContent,
    Dropdown,
  },
};
</script>

<style lang="css" scoped>
.container {
  display: flex;
  max-width: inherit;
  align-items: center;
  
}

.left-component {
  flex: 20%;
}

.right-component {
  flex: 80%; /* Ocupa el 80% del ancho disponible */
}
</style>


