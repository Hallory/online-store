import { createSlice,createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";
import { selectBaseUrl } from "./baseUrlSlice";
import { list } from "postcss";


export const getProducts = createAsyncThunk('products/fetchProducts', async (_, thunkAPI) => {
    const response = await axios.get(selectBaseUrl(thunkAPI.getState()));
    return response.data.products;
});

const productsSlice = createSlice({
    
    name:'products',
    initialState:{
        list: [],
        filteredProducts: [],
        sortProducts: [],
        status:'idle',
        error:null
    },
    reducers: {
        filteredProducts: (state, action) => {
            try {
                const filter = state.list.filter((product) => {
                    return product.brand === action.payload;
                });
                state.filteredProducts = filter;
                console.log('filter', filter);
                const saveState = JSON.stringify(filter);
                sessionStorage.setItem('filteredProducts', saveState);
            } catch (error) {
                state.error = error.message;
            }
        },
        sortProducts: (state, action) => {
        try {
        let sorted = [...state.list];

        switch (action.payload) {
            case 'expensive':
                state.sortProducts = sorted.sort((a, b) => b.price - a.price);
                console.log(state.sortProducts);
                break;
            case 'cheapest':
                state.sortProducts = sorted.sort((a, b) => a.price - b.price);
                break;
            case 'featured':
                state.sortProducts = state.list;
                break;
            // Add other sorting options as needed
            default:
                // If the action payload doesn't match any defined cases, return the current state
                return state;
        }

        return state;

    } catch (error) {
        state.error = error.message;
        return state;
    }
}
    },
    extraReducers:(builder)=>{
        builder
        .addCase(getProducts.pending,(state)=>{
            state.status = 'loading';
        })
        .addCase(getProducts.fulfilled,(state,action)=>{
            state.status = 'succeeded';
            state.list = action.payload;
        })
        .addCase(getProducts.rejected,(state,action)=>{
            state.status = 'failed';
            state.error = action.error.message
        })
    }
});

export const { filteredProducts, sortProducts } = productsSlice.actions;
export default productsSlice.reducer;