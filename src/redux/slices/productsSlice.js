import { createSlice,createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";
import { selectBaseUrl } from "./baseUrlSlice";



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
        const brand = action.payload;
        
        const isBrandAlreadySelected = state.filteredProducts.some(product => product.brand === brand);

        if (!isBrandAlreadySelected) {

            const brandProducts = state.list.filter(product => product.brand === brand);
            state.filteredProducts = [...state.filteredProducts, ...brandProducts];
        } else {

            state.filteredProducts = state.filteredProducts.filter(product => product.brand !== brand);
        }

        const saveState = JSON.stringify(state.filteredProducts);
        sessionStorage.setItem('filteredProducts', saveState);
    } catch (error) {
        state.error = error.message;
    }
},
sortProducts: (state, action) => {
    try {
        let sorted = [...state.list];
        let filteredAndSorted;

        switch (action.payload) {
            case 'expensive':
                filteredAndSorted = sorted.sort((a, b) => b.price - a.price);
                state.sortProducts = filteredAndSorted;
                console.log(state.sortProducts);
                break;
            case 'cheapest':
                filteredAndSorted = sorted.sort((a, b) => a.price - b.price);
                state.sortProducts = filteredAndSorted;
                console.log(state.sortProducts);
                break;
            case 'featured':
                filteredAndSorted = state.list; 
                state.sortProducts = filteredAndSorted;
                console.log(state.sortProducts);
                break;
            default:
                return state;
        }

        if (state.filteredProducts.length > 0) {
            filteredAndSorted = filteredAndSorted.filter(product =>
                state.filteredProducts.some(filteredProduct => filteredProduct.brand === product.brand)
            );
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