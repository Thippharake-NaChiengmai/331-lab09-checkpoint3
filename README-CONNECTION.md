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

- ✅ CORS configuration for cross-origin requests
- ✅ Axios HTTP client for API calls
- ✅ Responsive auction items display
- ✅ Search and filter functionality
- ✅ Pagination support
- ✅ Real-time bid display
- ✅ Winning bid highlighting

## 🔧 Configuration

### Backend CORS Settings
- Allows requests from localhost:5173, localhost:3000
- Supports GET, POST, PUT, DELETE, OPTIONS methods
- Credentials enabled for authentication

### Frontend API Configuration
- Base URL: http://localhost:8080
- Automatic error handling
- TypeScript interfaces for type safety
