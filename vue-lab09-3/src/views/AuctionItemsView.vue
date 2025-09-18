<template>
  <div class="auction-items">
    <h1>Auction Items</h1>
    
    <!-- Search and Filter Controls -->
    <div class="controls">
      <div class="search-section">
        <h3>Search Auction Items</h3>
        <div class="search-inputs">
          <div class="search-box">
            <label>Search by description:</label>
            <input 
              v-model="searchDescription" 
              @input="searchItems"
              placeholder="Enter description..."
              class="search-input"
            />
          </div>
          
          <div class="search-box">
            <label>Search by type:</label>
            <select 
              v-model="searchType" 
              @change="searchItems"
              class="search-input"
            >
              <option value="">All Types</option>
              <option value="Collectibles">Collectibles</option>
              <option value="Electronics">Electronics</option>
              <option value="Art">Art</option>
              <option value="Books">Books</option>
              <option value="Jewelry">Jewelry</option>
            </select>
          </div>
        </div>
      </div>
      
      <div class="filter-section">
        <h3>Filter by Bid Amount</h3>
        <div class="filter-box">
          <label>Max bid amount:</label>
          <input 
            v-model.number="maxBidAmount" 
            @input="filterByBidAmount"
            type="number"
            placeholder="Enter max amount"
            class="filter-input"
          />
        </div>
      </div>
      
      <div class="action-buttons">
        <button @click="clearFilters" class="clear-btn">Clear All Filters</button>
      </div>
    </div>

    <!-- Loading State -->
    <div v-if="loading" class="loading">
      Loading auction items...
    </div>

    <!-- Error State -->
    <div v-if="error" class="error">
      Error: {{ error }}
    </div>

    <!-- Auction Items Grid -->
    <div v-if="!loading && !error" class="items-grid">
      <div v-for="item in auctionItems" :key="item.id" class="item-card">
        <div class="item-header">
          <h3>{{ item.description }}</h3>
          <span class="item-type">{{ item.type }}</span>
        </div>
        
        <div class="item-details">
          <p><strong>Item ID:</strong> {{ item.id }}</p>
          
          <!-- Bids Section -->
          <div class="bids-section">
            <h4>Bids ({{ item.bids?.length || 0 }})</h4>
            <div v-if="item.bids && item.bids.length > 0" class="bids-list">
              <div v-for="bid in item.bids" :key="bid.id" class="bid-item">
                <span class="bid-amount">${{ bid.amount }}</span>
                <span class="bid-date">{{ formatDate(bid.datetime) }}</span>
              </div>
            </div>
            <p v-else class="no-bids">No bids yet</p>
          </div>

          <!-- Successful Bid -->
          <div v-if="item.successfulBid" class="successful-bid">
            <h4>Winning Bid</h4>
            <div class="winning-bid">
              <span class="winning-amount">${{ item.successfulBid.amount }}</span>
              <span class="winning-date">{{ formatDate(item.successfulBid.datetime) }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Pagination -->
    <div v-if="totalCount > pageSize" class="pagination">
      <button 
        @click="previousPage" 
        :disabled="currentPage === 0"
        class="page-btn"
      >
        Previous
      </button>
      
      <span class="page-info">
        Page {{ currentPage + 1 }} of {{ totalPages }}
        ({{ totalCount }} total items)
      </span>
      
      <button 
        @click="nextPage" 
        :disabled="currentPage >= totalPages - 1"
        class="page-btn"
      >
        Next
      </button>
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

// Load data on component mount
onMounted(() => {
  loadAuctionItems()
})
</script>

<style scoped>
.auction-items {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.controls {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 30px;
  margin-bottom: 30px;
  padding: 20px;
  background-color: #f8f9fa;
  border-radius: 8px;
  border: 1px solid #dee2e6;
}

.search-section, .filter-section {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.search-section h3, .filter-section h3 {
  margin: 0;
  color: #495057;
  font-size: 16px;
  font-weight: 600;
}

.search-inputs {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.search-box, .filter-box {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.search-box label, .filter-box label {
  font-size: 14px;
  font-weight: 500;
  color: #6c757d;
}

.action-buttons {
  grid-column: 1 / -1;
  display: flex;
  justify-content: center;
  margin-top: 10px;
}

.search-input, .filter-input {
  padding: 8px 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
}

.clear-btn {
  padding: 8px 16px;
  background-color: #6c757d;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.clear-btn:hover {
  background-color: #5a6268;
}

.loading, .error {
  text-align: center;
  padding: 40px;
  font-size: 18px;
}

.error {
  color: #dc3545;
}

.items-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(400px, 1fr));
  gap: 20px;
  margin-bottom: 30px;
}

.item-card {
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  background-color: #f8f9fa;
}

.item-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.item-header h3 {
  margin: 0;
  color: #333;
}

.item-type {
  background-color: #007bff;
  color: white;
  padding: 4px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.bids-section {
  margin: 15px 0;
}

.bids-section h4 {
  margin: 0 0 10px 0;
  color: #666;
}

.bids-list {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.bid-item {
  display: flex;
  justify-content: space-between;
  padding: 5px 10px;
  background-color: white;
  border-radius: 4px;
  font-size: 14px;
}

.bid-amount {
  font-weight: bold;
  color: #28a745;
}

.bid-date {
  color: #666;
  font-size: 12px;
}

.no-bids {
  color: #999;
  font-style: italic;
}

.successful-bid {
  margin-top: 15px;
  padding: 10px;
  background-color: #d4edda;
  border-radius: 4px;
}

.successful-bid h4 {
  margin: 0 0 5px 0;
  color: #155724;
}

.winning-bid {
  display: flex;
  justify-content: space-between;
}

.winning-amount {
  font-weight: bold;
  font-size: 18px;
  color: #155724;
}

.winning-date {
  color: #666;
  font-size: 12px;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
  margin-top: 30px;
}

.page-btn {
  padding: 8px 16px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.page-btn:hover:not(:disabled) {
  background-color: #0056b3;
}

.page-btn:disabled {
  background-color: #6c757d;
  cursor: not-allowed;
}

.page-info {
  font-size: 14px;
  color: #666;
}
</style>
