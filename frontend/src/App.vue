<template>
  <div id="app" class="d-flex flex-column h-100">
    <nav-bar />
    <div class="container-fluid flex-grow-1">
      <error />
      <router-view />
    </div>
  </div>
</template>

<script lang="ts">
import NavBar from "./components/NavBar.vue";
import Error from "./components/Error.vue";

export default {
  components: {
    NavBar,
    Error
  }
};
</script>

<style scoped>
#app {
  height: 200%;
}

.container-fluid {
  background-color: rgb(94, 94, 94);
  margin-top: -15px;
}
</style>