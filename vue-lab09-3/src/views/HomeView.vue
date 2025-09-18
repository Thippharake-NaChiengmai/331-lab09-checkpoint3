<script setup lang="ts">
import { RouterLink } from 'vue-router'
</script>

<template>
  <div class="home-page">
    <!-- Hero Section -->
    <section class="hero-section">
      <div class="container">
        <div class="row align-items-center min-vh-75">
          <div class="col-lg-6">
            <div class="hero-content">
              <h1 class="display-2 fw-bold mb-4">
                Welcome to 
                <span class="text-primary">Auction House</span>
              </h1>
              <p class="lead mb-4 text-muted">
                Discover rare treasures, unique collectibles, and amazing deals. 
                Join thousands of bidders in our exciting online auctions!
              </p>
              <div class="d-flex gap-3 flex-wrap">
                <RouterLink to="/auctions" class="btn btn-primary btn-lg px-4 py-3">
                  <i class="bi bi-collection me-2"></i>
                  Browse Auctions
                </RouterLink>
                <button class="btn btn-outline-primary btn-lg px-4 py-3">
                  <i class="bi bi-info-circle me-2"></i>
                  Learn More
                </button>
              </div>
            </div>
          </div>
          <div class="col-lg-6">
            <div class="hero-image text-center">
              <div class="auction-preview">
                <i class="bi bi-hammer display-1 text-primary mb-3"></i>
                <div class="floating-cards">
                  <div class="preview-card card-1">
                    <i class="bi bi-gem"></i>
                    <span>Jewelry</span>
                  </div>
                  <div class="preview-card card-2">
                    <i class="bi bi-palette"></i>
                    <span>Art</span>
                  </div>
                  <div class="preview-card card-3">
                    <i class="bi bi-laptop"></i>
                    <span>Electronics</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Features Section -->
    <section class="features-section py-5 bg-light">
      <div class="container">
        <div class="row text-center mb-5">
          <div class="col-12">
            <h2 class="display-5 fw-bold mb-3">Why Choose Auction House?</h2>
            <p class="lead text-muted">Experience the thrill of online bidding with confidence</p>
          </div>
        </div>
        
        <div class="row g-4">
          <div class="col-md-4">
            <div class="feature-card text-center p-4">
              <div class="feature-icon mb-3">
                <i class="bi bi-shield-check display-4 text-success"></i>
              </div>
              <h4 class="fw-bold mb-3">Secure Bidding</h4>
              <p class="text-muted">
                Your bids are protected with enterprise-level security. 
                Bid with confidence knowing your information is safe.
              </p>
            </div>
          </div>
          
          <div class="col-md-4">
            <div class="feature-card text-center p-4">
              <div class="feature-icon mb-3">
                <i class="bi bi-clock-history display-4 text-warning"></i>
              </div>
              <h4 class="fw-bold mb-3">Real-time Updates</h4>
              <p class="text-muted">
                Get instant notifications when you're outbid. 
                Never miss an opportunity with live auction updates.
              </p>
            </div>
          </div>
          
          <div class="col-md-4">
            <div class="feature-card text-center p-4">
              <div class="feature-icon mb-3">
                <i class="bi bi-people display-4 text-info"></i>
              </div>
              <h4 class="fw-bold mb-3">Trusted Community</h4>
              <p class="text-muted">
                Join a community of verified buyers and sellers. 
                Every transaction is backed by our guarantee.
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Stats Section -->
    <section class="stats-section py-5">
      <div class="container">
        <div class="row text-center">
          <div class="col-md-3 col-6 mb-4">
            <div class="stat-item">
              <h3 class="display-4 fw-bold text-primary">1000+</h3>
              <p class="text-muted">Active Auctions</p>
            </div>
          </div>
          <div class="col-md-3 col-6 mb-4">
            <div class="stat-item">
              <h3 class="display-4 fw-bold text-success">50K+</h3>
              <p class="text-muted">Happy Bidders</p>
            </div>
          </div>
          <div class="col-md-3 col-6 mb-4">
            <div class="stat-item">
              <h3 class="display-4 fw-bold text-warning">$2M+</h3>
              <p class="text-muted">Items Sold</p>
            </div>
          </div>
          <div class="col-md-3 col-6 mb-4">
            <div class="stat-item">
              <h3 class="display-4 fw-bold text-info">24/7</h3>
              <p class="text-muted">Support</p>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<style scoped>
.home-page {
  overflow-x: hidden;
}

.hero-section {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  min-height: 80vh;
  display: flex;
  align-items: center;
}

.min-vh-75 {
  min-height: 75vh;
}

.hero-content h1 {
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
}

.auction-preview {
  position: relative;
  padding: 2rem;
}

.floating-cards {
  position: relative;
  height: 300px;
}

.preview-card {
  position: absolute;
  background: white;
  border-radius: 12px;
  padding: 1rem;
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.5rem;
  color: #333;
  font-weight: 600;
  animation: float 3s ease-in-out infinite;
}

.card-1 {
  top: 20px;
  left: 20px;
  animation-delay: 0s;
}

.card-2 {
  top: 80px;
  right: 30px;
  animation-delay: 1s;
}

.card-3 {
  bottom: 40px;
  left: 50%;
  transform: translateX(-50%);
  animation-delay: 2s;
}

@keyframes float {
  0%, 100% {
    transform: translateY(0px);
  }
  50% {
    transform: translateY(-10px);
  }
}

.feature-card {
  background: white;
  border-radius: 12px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  height: 100%;
}

.feature-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
}

.stats-section {
  background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
  color: white;
}

.stat-item h3 {
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
}

.btn {
  border-radius: 8px;
  font-weight: 600;
  transition: all 0.3s ease;
}

.btn:hover {
  transform: translateY(-2px);
}

@media (max-width: 768px) {
  .hero-section {
    text-align: center;
    padding: 2rem 0;
  }
  
  .floating-cards {
    height: 200px;
  }
  
  .preview-card {
    font-size: 0.8rem;
    padding: 0.5rem;
  }
  
  .display-2 {
    font-size: 2.5rem;
  }
}
</style>
