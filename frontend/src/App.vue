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
.container-fluid {
  width: 100%;
  padding-right: 15px;
  padding-left: 15px;
  margin-right: auto;
  margin-left: auto;
  background-color: rgb(94, 94, 94);
}

.flex-grow-1 {
  flex-grow: 1;
}

</style>