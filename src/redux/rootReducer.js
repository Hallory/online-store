import { combineReducers } from "redux";
import ordersReducer from "./slices/ordersSlice";
import adminReducer from "./slices/adminSlice";
import baseUrlReducer from "./slices/baseUrlSlice";
import productsReducer from "./slices/productsSlice";
import wishlistReducer from "./slices/wishlistSlice";
import usersSlice from "./slices/usersSlice";


const rootReducer = combineReducers({
    orders:ordersReducer,
    admin:adminReducer,
    baseUrl:baseUrlReducer,
    products: productsReducer,
    wishlist: wishlistReducer,
    users:usersSlice,
})

export default rootReducer