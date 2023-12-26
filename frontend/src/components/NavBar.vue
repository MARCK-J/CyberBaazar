<template>
  <div class="nav-container mb-3">
    <nav class="navbar navbar-expand-md navbar-light bg-light">
      <div class="container">
        <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item">
              <router-link to="/" class="nav-link">Home</router-link>
            </li>
          </ul>
          <div class="title-container">
            <div class="text-center title">
              <h1 class="mb-4">CYBERBAAZAR</h1>
              <h1>THE PLACE TO FIND THE BEST PRICE</h1>
            </div>
          </div>
          <ul class="navbar-nav d-none d-md-block">
            <li v-if="!isAuthenticated && !isLoading" class="nav-item">
              <button
                id="qsLoginBtn"
                class="btn btn-primary btn-margin"
                @click.prevent="login"
              >Login</button>
            </li>

            <li class="nav-item dropdown" v-if="isAuthenticated">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                id="profileDropDown"
                data-toggle="dropdown"
              >
                <img
                  :src="user.picture"
                  alt="User's profile picture"
                  class="nav-user-profile rounded-circle"
                  width="50"
                />
              </a>
              <div class="dropdown-menu dropdown-menu-right">
                <div class="dropdown-header">{{ user.name }}</div>
                <router-link to="/profile" class="dropdown-item dropdown-profile">
                  <font-awesome-icon class="mr-3" icon="user" />Profile
                </router-link>
                <a id="qsLogoutBtn" href="#" class="dropdown-item" @click.prevent="logout">
                  <font-awesome-icon class="mr-3" icon="power-off" />Log out
                </a>
              </div>
            </li>
          </ul>
          

          <ul class="navbar-nav d-md-none" v-if="!isAuthenticated && !isLoading">
            <button id="qsLoginBtn" class="btn btn-primary btn-block" @click="login">Log in</button>
          </ul>

          <ul
            id="mobileAuthNavBar"
            class="navbar-nav d-md-none d-flex"
            v-if="isAuthenticated"
          >
            <li class="nav-item">
              <span class="user-info">
                <img
                  :src="user.picture"
                  alt="User's profile picture"
                  class="nav-user-profile d-inline-block rounded-circle mr-3"
                  width="50"
                />
                <h6 class="d-inline-block">{{ user.name }}</h6>
              </span>
            </li>
            <li>
              <font-awesome-icon icon="user" class="mr-3" />
              <router-link to="/profile">Profile</router-link>
            </li>

            <li>
              <font-awesome-icon icon="power-off" class="mr-3" />
              <a id="qsLogoutBtn" href="#" class @click.prevent="logout">Log out</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>

<script lang="ts">
import { useAuth0 } from '@auth0/auth0-vue';

export default {
  name: "NavBar",
  setup() {
    const auth0 = useAuth0();
    
    return {
      isAuthenticated: auth0.isAuthenticated,
      isLoading: auth0.isLoading,
      user: auth0.user,
      login() {
        auth0.loginWithRedirect();
      },
      logout() {
        auth0.logout({
          logoutParams: {
            returnTo: window.location.origin
          }
        });
      }
    }
  }
};
</script>

<style>
#mobileAuthNavBar {
  min-height: 130px;
  justify-content: space-between;
}
.nav-container {
  height: 140px;
  background-color: black;
}

.navbar {
  background-color: black !important;
   /* Fondo negro, !important para anular otros estilos */
}

.navbar-light .navbar-toggler-icon {
  background-color: white; /* Icono del botón de navegación en blanco */
}

.navbar-nav .nav-link {
  color: white !important;
  border-bottom: 1px solid rgb(78, 78, 78) !important;
}

.navbar-toggler-icon {
  background-color: white; /* Color del icono del botón de navegación */
}

.dropdown-menu {
  background-color: black; /* Fondo negro para el menú desplegable */
}

.dropdown-item {
  color: white !important; /* Texto blanco para los elementos del menú desplegable, !important para anular otros estilos */
}

.nav-user-profile {
  border: 2px solid rgb(41, 41, 41); /* Borde blanco para la imagen del perfil */
}

#qsLoginBtn {
  background-color: gray !important; /* Fondo gris para el botón Login, !important para anular otros estilos */
  color: white !important; /* Texto blanco para el botón Login, !important para anular otros estilos */
  border-color: gray !important; /* Color del borde gris, !important para anular otros estilos */
}

.title-container {
  color: white;
  margin-right: 30%;
  margin-left: 25%;
  
}

.title h1.mb-4 {
  font-size: 2.5em;
  font-family: 'Courier New', monospace;

}

.title h1 {
  font-size: 1em;
  font-family: 'Courier New', monospace;
 /* Tamaño del segundo título, ajusta según sea necesario */
}
</style>
