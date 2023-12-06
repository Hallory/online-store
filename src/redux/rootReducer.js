import { combineReducers } from "redux";
import ordersReducer from "./slices/ordersSlice";
import adminReducer from "./slices/adminSlice";
import baseUrlReducer from "./slices/baseUrlSlice";
import productsReducer from "./slices/productsSlice";

const rootReducer = combineReducers({
    orders:ordersReducer,
    admin:adminReducer,
    baseUrl:baseUrlReducer,
    products:productsReducer
})

export default rootReducer