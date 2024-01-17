import { combineReducers } from "redux";
import ordersReducer from "./slices/ordersSlice";
import adminReducer from "./slices/adminSlice";
import baseUrlReducer from "./slices/baseUrlSlice";
import productsReducer from "./slices/productsSlice";
import wishlistReducer from "./slices/wishlistSlice";
import compareReducer from "./slices/compareSlice";
import cartReducer from "./slices/cartSlice";


const rootReducer = combineReducers({
    orders:ordersReducer,
    admin:adminReducer,
    baseUrl:baseUrlReducer,
    products: productsReducer,
    wishlist: wishlistReducer,
    compare: compareReducer,
    cart: cartReducer
    
})

export default rootReducer