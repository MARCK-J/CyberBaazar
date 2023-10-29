import { createApp } from 'vue';
import App from './App.vue';
import { createAuth0 } from '@auth0/auth0-vue';


const app = createApp(App);

app.use(
    createAuth0({
      domain: "dev-aq5qghsj8trj0aw5.us.auth0.com",
      clientId: "xKlknSdlb79TUM1ZZmKTBxiqe3Q9qV1k",
      authorizationParams: {
        redirect_uri: window.location.origin
      }
    })
  );

app.mount('#app');

