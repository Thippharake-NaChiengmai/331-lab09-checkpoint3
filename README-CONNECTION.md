# 🏛️ Auction House Application

A full-stack auction application built with Spring Boot backend and Vue.js frontend.

## 🚀 Quick Start

### Start Backend (Spring Boot)
```bash
cd d:\Code\component-based\331-lab09-3
mvnw.cmd spring-boot:run
```
Backend runs on: **http://localhost:8080**

### Start Frontend (Vue.js)
```bash
cd d:\Code\component-based\331-lab09-3\vue-lab09-3
npm run dev
```
Frontend runs on: **http://localhost:5173**

## 🔗 API Endpoints

- `GET /items` - Get all auction items
- `GET /items?description=search` - Search by description
- `GET /items?type=Electronics` - Search by type
- `GET /items?description=laptop&type=Electronics` - Combined search
- `GET /items/byBidAmount?lessThan=500` - Filter by bid amount

## 📁 Clean Project Structure

```
auction-house/
├── src/main/java/se331/se331lab093/
│   ├── controller/
│   │   ├── AuctionItemController.java    # REST API endpoints
│   │   └── BidController.java
│   ├── entity/
│   │   ├── AuctionItem.java              # Main entity
│   │   └── Bid.java                      # Bid entity
│   ├── dao/
│   │   ├── AuctionItemDao.java           # Data access interface
│   │   └── AuctionItemDaoImp.java        # In-memory implementation
│   ├── services/
│   │   ├── AuctionItemServices.java      # Business logic interface
│   │   └── AuctionItemServicesImp.java   # Business logic implementation
│   └── config/
│       └── CorsConfig.java               # CORS configuration
├── vue-lab09-3/                          # Vue.js Frontend
│   ├── src/
│   │   ├── services/api.ts               # API client
│   │   ├── views/
│   │   │   ├── HomeView.vue              # Landing page
│   │   │   └── AuctionItemsView.vue      # Main auction interface
│   │   ├── router/index.ts               # Vue router
│   │   └── App.vue                       # Main app component
│   └── package.json
├── start-backend.bat                     # Backend launcher
└── start-frontend.bat                    # Frontend launcher
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
