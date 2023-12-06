import axios from "axios";
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";

export const fetchOrders = createAsyncThunk('orders/fetchOrders', async () => {
    const { data } = await axios.get('/api/orders');
    return data;
});

const ordersSlice = createSlice({
    name:'orders',
    initialState:{
        list:[],
        status:'idle',
        error:null
    },
    reducers:{},
    extraReducers:(builder)=>{
        builder
        .addCase(fetchOrders.pending,(state)=>{
            state.status = 'loading';
        })
        .addCase(fetchOrders.fulfilled,(state,action)=>{
            state.status = 'succeeded';
            state.list = action.payload;
        })
        .addCase(fetchOrders.rejected,(state,action)=>{
            state.status = 'failed';
            state.error = action.error.message
        })
    }
});

export default ordersSlice.reducer;