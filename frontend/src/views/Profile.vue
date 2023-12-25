<template>
  <div class="container mt-5">
    <div class="row align-items-center profile-header">
      <div class="col-md-2 mb-3">
        <img
          :src="user?.picture"
          alt="User's profile picture"
          class="rounded-circle img-fluid profile-picture"
        />
      </div>
      <div class="col-md text-center text-md-left">
        <h2>{{ user?.name }}</h2>
        <p class="lead text-muted">{{ user?.email }}</p>
      </div>
    </div>

    <h1>Productos Guardados</h1>

    <div class="row">
      <div class="scrollable-items">
        <!-- Add more items for testing scrollbar -->
        <div class="item">Item 1</div>
        <div class="item">Item 2</div>
        <div class="item">Item 3</div>
        <div class="item">Item 4</div>
        <div class="item">Item 5</div>
        <div class="item">Item 6</div>
        <div class="item">Item 7</div>
        <div class="item">Item 8</div>
        <div class="item">Item 9</div>
        <div class="item">Item 10</div>
        <div class="item">Item 11</div>
        <div class="item">Item 12</div>
        <!-- Add more items as needed -->
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { useAuth0 } from '@auth0/auth0-vue';

export default {
  name: "profile-view",
  setup() {
    const auth0 = useAuth0();
    
    return {
      user: auth0.user,
    };
  },
};
</script>

<style scoped>
.scrollable-items {
  display: flex;
  overflow-x: auto;
}

.item {
  /* Add styling for your items */
  min-width: 100px; /* Adjust as needed */
  border: 3px solid #ccc;
  margin-right: 20px; /* Adjust spacing between items */
  padding: 175px;
}
</style>




