import axios from 'axios'

const API_BASE_URL = 'http://localhost:8080'

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
})

export interface AuctionItem {
  id: number
  description: string
  type: string
  bids: Bid[]
  successfulBid?: Bid
}

export interface Bid {
  id: number
  amount: number
  datetime: string
}

export interface PaginatedResponse<T> {
  content: T[]
  totalElements: number
  totalPages: number
  size: number
  number: number
}

export const auctionService = {
  // Get all auction items with pagination
  async getAuctionItems(page = 0, limit = 10, description?: string) {
    const params: any = { _page: page, _limit: limit }
    if (description) {
      params.description = description
    }
    
    const response = await api.get<AuctionItem[]>('/items', { params })
    return {
      data: response.data,
      totalCount: parseInt(response.headers['x-total-count'] || '0')
    }
  },

  // Get auction items by successful bid amount
  async getAuctionItemsByBidAmount(lessThan: number, page = 0, limit = 10) {
    const response = await api.get<AuctionItem[]>('/items/byBidAmount', {
      params: { lessThan, _page: page, _limit: limit }
    })
    return {
      data: response.data,
      totalCount: parseInt(response.headers['x-total-count'] || '0')
    }
  },

  // Get single auction item by ID (if you add this endpoint later)
  async getAuctionItem(id: number) {
    const response = await api.get<AuctionItem>(`/items/${id}`)
    return response.data
  }
}

export default api
