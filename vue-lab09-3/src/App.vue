<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div id="app">
    <!-- Bootstrap Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
      <div class="container">
        <RouterLink class="navbar-brand fw-bold" to="/">
          <i class="bi bi-hammer me-2"></i>
          Auction House
        </RouterLink>
        
        <button 
          class="navbar-toggler" 
          type="button" 
          data-bs-toggle="collapse" 
          data-bs-target="#navbarNav"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <RouterLink class="nav-link" to="/">
                <i class="bi bi-house me-1"></i>
                Home
              </RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink class="nav-link" to="/auctions">
                <i class="bi bi-collection me-1"></i>
                Auctions
              </RouterLink>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Main Content -->
    <main class="flex-grow-1">
      <RouterView />
    </main>

    <!-- Footer -->
    <footer class="bg-dark text-light py-4 mt-5">
      <div class="container">
        <div class="row">
          <div class="col-md-6">
            <h5><i class="bi bi-hammer me-2"></i>Auction House</h5>
            <p class="mb-0">Your premier destination for online auctions</p>
          </div>
          <div class="col-md-6 text-md-end">
            <p class="mb-0">
              <i class="bi bi-clock me-1"></i>
              © 2025 Auction House. All rights reserved.
            </p>
            <p class="mb-0">Thippharake Na Chiengmai Code:662115019</p>
          </div>
        </div>
      </div>
    </footer>
  </div>
</template>

<style scoped>
#app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.navbar-brand {
  font-size: 1.5rem;
}

.nav-link {
  font-weight: 500;
  transition: color 0.3s ease;
}

.nav-link:hover {
  color: var(--bs-primary) !important;
}

.router-link-active {
  color: var(--bs-primary) !important;
}

main {
  flex: 1;
}

footer {
  margin-top: auto;
}
</style>
