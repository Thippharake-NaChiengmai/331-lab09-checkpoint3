<script setup lang="ts">
import { RouterLink } from 'vue-router'
</script>

<template>
  <main>
    <div class="welcome">
      <h1>Welcome to Auction House</h1>
      <p>Browse and bid on amazing auction items!</p>
      <RouterLink to="/auctions" class="cta-button">
        View Auctions
      </RouterLink>
    </div>
  </main>
</template>

<style scoped>
.welcome {
  text-align: center;
  padding: 60px 20px;
}

.welcome h1 {
  font-size: 3rem;
  color: #2c3e50;
  margin-bottom: 20px;
}

.welcome p {
  font-size: 1.2rem;
  color: #7f8c8d;
  margin-bottom: 30px;
}

.cta-button {
  display: inline-block;
  padding: 15px 30px;
  background-color: #3498db;
  color: white;
  text-decoration: none;
  border-radius: 5px;
  font-size: 1.1rem;
  transition: background-color 0.3s;
}

.cta-button:hover {
  background-color: #2980b9;
}
</style>
