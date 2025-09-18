# Frontend-Backend Connection Guide

## 🚀 Quick Start

### Option 1: Using Batch Files (Windows)
1. **Start Backend**: Double-click `start-backend.bat`
2. **Start Frontend**: Double-click `start-frontend.bat`

### Option 2: Manual Start

#### Backend (Spring Boot)
```bash
cd d:\Code\component-based\331-lab09-3
mvnw.cmd spring-boot:run
```
Backend will run on: http://localhost:8080

#### Frontend (Vue.js)
```bash
cd d:\Code\component-based\331-lab09-3\vue-lab09-3
npm run dev
```
Frontend will run on: http://localhost:5173

## 🔗 API Endpoints

- `GET /items` - Get all auction items
- `GET /items?description=search` - Search auction items
- `GET /items/byBidAmount?lessThan=500` - Filter by bid amount

## 🎯 Testing the Connection

1. Start both backend and frontend
2. Open browser to http://localhost:5173
3. Click on "Auctions" in the navigation
4. You should see auction items loaded from the backend

## 📁 Project Structure

```
331-lab09-3/
├── src/main/java/...           # Spring Boot Backend
├── vue-lab09-3/                # Vue.js Frontend
│   ├── src/
│   │   ├── services/api.ts     # API service layer
│   │   ├── views/AuctionItemsView.vue
│   │   └── router/index.ts
│   └── package.json
├── start-backend.bat           # Backend start script
└── start-frontend.bat          # Frontend start script
```

## 🛠️ Features Implemented

### ✅ Task 3.4: Frontend to show list of AuctionItems
- Responsive auction items grid display
- Shows all auction item details (ID, description, type, bids)
- Pagination support for large datasets
- Real-time bid display with amounts and timestamps
- Winning bid highlighting with special styling

### ✅ Task 3.5: Frontend to search AuctionItems by description and type
- **Search by Description**: Text input for searching item descriptions
- **Search by Type**: Dropdown selector for filtering by item types
  - Collectibles
  - Electronics  
  - Art
  - Books
  - Jewelry
- **Combined Search**: Search by both description AND type simultaneously
- **Case-insensitive search** for better user experience

### 🔧 Additional Features
- ✅ CORS configuration for cross-origin requests
- ✅ Axios HTTP client for API calls
- ✅ Advanced filtering by bid amount
- ✅ Clear all filters functionality
- ✅ Loading states and error handling

## 🔧 Configuration

### Backend CORS Settings
- Allows requests from localhost:5173, localhost:3000
- Supports GET, POST, PUT, DELETE, OPTIONS methods
- Credentials enabled for authentication

### Frontend API Configuration
- Base URL: http://localhost:8080
- Automatic error handling
- TypeScript interfaces for type safety
