<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
    <div class="wrapper">
      <h1>Auction House</h1>
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/auctions">Auctions</RouterLink>
      </nav>
    </div>
  </header>

  <RouterView />
</template>

<style scoped>
header {
  background-color: #2c3e50;
  color: white;
  padding: 1rem 0;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.wrapper {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper h1 {
  margin: 0;
  font-size: 1.8rem;
  color: #ecf0f1;
}

nav {
  display: flex;
  gap: 2rem;
}

nav a {
  color: #bdc3c7;
  text-decoration: none;
  font-weight: 500;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  transition: all 0.3s ease;
}

nav a:hover {
  color: #ecf0f1;
  background-color: rgba(255, 255, 255, 0.1);
}

nav a.router-link-exact-active {
  color: #3498db;
  background-color: rgba(52, 152, 219, 0.1);
}

@media (max-width: 768px) {
  .wrapper {
    flex-direction: column;
    gap: 1rem;
  }
  
  .wrapper h1 {
    font-size: 1.5rem;
  }
  
  nav {
    gap: 1rem;
  }
}
</style>
