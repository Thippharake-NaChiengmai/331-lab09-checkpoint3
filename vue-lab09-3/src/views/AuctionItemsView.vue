<template>
  <div class="container-fluid py-4">
    <!-- Hero Section -->
    <div class="search-section mb-4">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-lg-6">
            <h1 class="display-4 fw-bold mb-3">
              <i class="bi bi-collection me-3"></i>
              Auction Items
            </h1>
            <p class="lead mb-0">Discover amazing items up for auction</p>
          </div>
        </div>
      </div>
    </div>

    <div class="container">
      <!-- Search and Filter Controls -->
      <div class="row mb-4">
        <div class="col-12">
          <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
              <h5 class="mb-0">
                <i class="bi bi-funnel me-2"></i>
                Search & Filter
              </h5>
            </div>
            <div class="card-body">
              <div class="row g-3">
                <!-- Search by Description -->
                <div class="col-md-4">
                  <label class="form-label fw-semibold">
                    <i class="bi bi-search me-1"></i>
                    Search by description
                  </label>
                  <input 
                    v-model="searchDescription" 
                    @input="searchItems"
                    placeholder="Enter description..."
                    class="form-control"
                  />
                </div>
                
                <!-- Search by Type -->
                <div class="col-md-4">
                  <label class="form-label fw-semibold">
                    <i class="bi bi-tags me-1"></i>
                    Search by type
                  </label>
                  <select 
                    v-model="searchType" 
                    @change="searchItems"
                    class="form-select"
                  >
                    <option value="">All Types</option>
                    <option value="Collectibles">🏺 Collectibles</option>
                    <option value="Electronics">📱 Electronics</option>
                    <option value="Art">🎨 Art</option>
                    <option value="Books">📚 Books</option>
                    <option value="Jewelry">💎 Jewelry</option>
                  </select>
                </div>
                
                <!-- Filter by Bid Amount -->
                <div class="col-md-4">
                  <label class="form-label fw-semibold">
                    <i class="bi bi-currency-dollar me-1"></i>
                    Max bid amount
                  </label>
                  <div class="input-group">
                    <span class="input-group-text">$</span>
                    <input 
                      v-model.number="maxBidAmount" 
                      @input="filterByBidAmount"
                      type="number"
                      placeholder="Enter max amount"
                      class="form-control"
                    />
                  </div>
                </div>
              </div>
              
              <div class="row mt-3">
                <div class="col-12 text-center">
                  <button @click="clearFilters" class="btn btn-outline-secondary">
                    <i class="bi bi-arrow-clockwise me-1"></i>
                    Clear All Filters
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Loading State -->
      <div v-if="loading" class="text-center py-5">
        <div class="spinner-border text-primary" role="status">
          <span class="visually-hidden">Loading...</span>
        </div>
        <p class="mt-3 text-muted">Loading auction items...</p>
      </div>

      <!-- Error State -->
      <div v-if="error" class="alert alert-danger d-flex align-items-center" role="alert">
        <i class="bi bi-exclamation-triangle-fill me-2"></i>
        <div>
          <strong>Error:</strong> {{ error }}
        </div>
      </div>

      <!-- Auction Items Grid -->
      <div v-if="!loading && !error" class="row g-4">
        <div v-for="item in auctionItems" :key="item.id" class="col-lg-4 col-md-6">
          <div class="card auction-card h-100 shadow-sm">
            <div class="card-header d-flex justify-content-between align-items-center">
              <h5 class="card-title mb-0 text-truncate" :title="item.description">
                {{ item.description }}
              </h5>
              <span class="badge bg-primary rounded-pill">
                {{ getTypeIcon(item.type) }} {{ item.type }}
              </span>
            </div>
            
            <div class="card-body">
              <div class="mb-3">
                <small class="text-muted">
                  <i class="bi bi-hash me-1"></i>
                  Item ID: {{ item.id }}
                </small>
              </div>
              
              <!-- Bids Section -->
              <div class="mb-3">
                <h6 class="d-flex align-items-center mb-2">
                  <i class="bi bi-currency-dollar me-2"></i>
                  Bids ({{ item.bids?.length || 0 }})
                </h6>
                
                <div v-if="item.bids && item.bids.length > 0" class="bids-container">
                  <div class="row g-2">
                    <!-- Always show first 3 bids -->
                    <div v-for="bid in item.bids.slice(0, 3)" :key="bid.id" class="col-12">
                      <div class="d-flex justify-content-between align-items-center p-2 bg-light rounded">
                        <span class="bid-amount fw-bold text-success">
                          ${{ bid.amount.toLocaleString() }}
                        </span>
                        <small class="text-muted">
                          <i class="bi bi-clock me-1"></i>
                          {{ formatDate(bid.datetime) }}
                        </small>
                      </div>
                    </div>
                    
                    <!-- Show additional bids when expanded -->
                    <template v-if="expandedBids.has(item.id)">
                      <div v-for="bid in item.bids.slice(3)" :key="bid.id" class="col-12">
                        <div class="d-flex justify-content-between align-items-center p-2 bg-light rounded">
                          <span class="bid-amount fw-bold text-success">
                            ${{ bid.amount.toLocaleString() }}
                          </span>
                          <small class="text-muted">
                            <i class="bi bi-clock me-1"></i>
                            {{ formatDate(bid.datetime) }}
                          </small>
                        </div>
                      </div>
                    </template>
                  </div>
                  
                  <!-- Show More/Less button always at the bottom -->
                  <div v-if="item.bids.length > 3" class="text-center mt-2">
                    <button class="btn btn-sm btn-outline-primary" @click="toggleBids(item.id)">
                      <i class="bi bi-three-dots"></i>
                      {{ expandedBids.has(item.id) ? 'Show Less' : `+${item.bids.length - 3} more` }}
                    </button>
                  </div>
                </div>
                
                <div v-else class="text-center py-3">
                  <i class="bi bi-inbox text-muted fs-1"></i>
                  <p class="text-muted mb-0">No bids yet</p>
                </div>
              </div>
            </div>

            <!-- Successful Bid -->
            <div v-if="item.successfulBid" class="card-footer">
              <div class="winning-bid text-center">
                <div class="d-flex align-items-center justify-content-center mb-2">
                  <i class="bi bi-trophy-fill me-2"></i>
                  <strong>Winning Bid</strong>
                </div>
                <div class="fs-4 fw-bold">
                  ${{ item.successfulBid.amount.toLocaleString() }}
                </div>
                <small class="opacity-75">
                  <i class="bi bi-calendar-check me-1"></i>
                  {{ formatDate(item.successfulBid.datetime) }}
                </small>
              </div>
            </div>
            
            <div v-else class="card-footer">
              <div class="auction-active text-center">
                <div class="d-flex align-items-center justify-content-center mb-2">
                  <i class="bi bi-hourglass-split me-2"></i>
                  <strong>Auction Active</strong>
                </div>
                <div class="fs-5 fw-bold text-warning">
                  Bidding Open
                </div>
                <small class="text-muted">
                  <i class="bi bi-clock me-1"></i>
                  Place your bid now!
                </small>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Pagination -->
      <div v-if="totalCount > pageSize" class="d-flex justify-content-center mt-5">
        <nav aria-label="Auction items pagination">
          <ul class="pagination pagination-lg">
            <li class="page-item" :class="{ disabled: currentPage === 0 }">
              <button 
                @click="previousPage" 
                :disabled="currentPage === 0"
                class="page-link"
              >
                <i class="bi bi-chevron-left"></i>
                Previous
              </button>
            </li>
            
            <li class="page-item active">
              <span class="page-link">
                Page {{ currentPage + 1 }} of {{ totalPages }}
                <br>
                <small>({{ totalCount }} total items)</small>
              </span>
            </li>
            
            <li class="page-item" :class="{ disabled: currentPage >= totalPages - 1 }">
              <button 
                @click="nextPage" 
                :disabled="currentPage >= totalPages - 1"
                class="page-link"
              >
                Next
                <i class="bi bi-chevron-right"></i>
              </button>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import { auctionService, type AuctionItem } from '@/services/api'

// Reactive data
const auctionItems = ref<AuctionItem[]>([])
const loading = ref(false)
const error = ref<string | null>(null)
const currentPage = ref(0)
const pageSize = ref(10)
const totalCount = ref(0)
const searchDescription = ref('')
const searchType = ref('')
const maxBidAmount = ref<number | null>(null)
const expandedBids = ref(new Set<number>())

// Computed properties
const totalPages = computed(() => Math.ceil(totalCount.value / pageSize.value))

// Methods
const loadAuctionItems = async () => {
  try {
    loading.value = true
    error.value = null
    
    let result
    if (maxBidAmount.value) {
      result = await auctionService.getAuctionItemsByBidAmount(
        maxBidAmount.value, 
        currentPage.value, 
        pageSize.value
      )
    } else {
      result = await auctionService.getAuctionItems(
        currentPage.value, 
        pageSize.value, 
        searchDescription.value || undefined,
        searchType.value || undefined
      )
    }
    
    auctionItems.value = result.data
    totalCount.value = result.totalCount
  } catch (err) {
    error.value = 'Failed to load auction items'
    console.error('Error loading auction items:', err)
  } finally {
    loading.value = false
  }
}

const searchItems = () => {
  currentPage.value = 0
  maxBidAmount.value = null
  loadAuctionItems()
}

const filterByBidAmount = () => {
  currentPage.value = 0
  searchDescription.value = ''
  searchType.value = ''
  loadAuctionItems()
}

const clearFilters = () => {
  searchDescription.value = ''
  searchType.value = ''
  maxBidAmount.value = null
  currentPage.value = 0
  loadAuctionItems()
}

const nextPage = () => {
  if (currentPage.value < totalPages.value - 1) {
    currentPage.value++
    loadAuctionItems()
  }
}

const previousPage = () => {
  if (currentPage.value > 0) {
    currentPage.value--
    loadAuctionItems()
  }
}

const formatDate = (dateString: string) => {
  return new Date(dateString).toLocaleString()
}

const getTypeIcon = (type: string) => {
  const icons: Record<string, string> = {
    'Collectibles': '🏺',
    'Electronics': '📱',
    'Art': '🎨',
    'Books': '📚',
    'Jewelry': '💎'
  }
  return icons[type] || '📦'
}

const toggleBids = (itemId: number) => {
  if (expandedBids.value.has(itemId)) {
    expandedBids.value.delete(itemId)
  } else {
    expandedBids.value.add(itemId)
  }
}

// Load data on component mount
onMounted(() => {
  loadAuctionItems()
})
</script>

<style scoped>
/* Custom styles for Bootstrap components */
.search-section {
  background: linear-gradient(135deg, #3498db, #2980b9);
  color: white;
  border-radius: 12px;
  padding: 2rem;
}

.auction-card {
  transition: all 0.3s ease;
  border: none;
}

.auction-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
}

.bids-container {
  max-height: 200px;
  overflow-y: auto;
}

.winning-bid {
  background: linear-gradient(135deg, #27ae60, #2ecc71);
  border-radius: 8px;
}

.auction-active {
  background: linear-gradient(135deg, #f39c12, #e67e22);
  color: white;
  border-radius: 8px;
  padding: 1rem;
}

.page-link {
  border: none;
  color: var(--bs-primary);
  font-weight: 500;
}

.page-link:hover {
  background-color: var(--bs-primary);
  color: white;
}

.badge {
  font-size: 0.75em;
}

@media (max-width: 768px) {
  .search-section .row {
    text-align: center;
  }
  
  .search-section .col-lg-6:last-child {
    margin-top: 1rem;
  }
}
</style>
