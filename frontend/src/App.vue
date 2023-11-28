<template>
  <div id="app" class="d-flex flex-column h-100">
    <nav-bar />
    <div class="container flex-grow-1">
      <error />
      <div class="mt-5">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import NavBar from "./components/NavBar.vue";
import Error from "./components/Error.vue";
import ApiCall from './components/ApiCall.vue';

export default {
  components: {
    NavBar,
    Error,
    ApiCall
  }
};
</script>
