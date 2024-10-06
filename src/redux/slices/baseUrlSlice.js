import { createSlice } from "@reduxjs/toolkit";

const baseURLSlice = createSlice({
    name:'baseUrl',
    initialState:{
        value:'https://dummyjson.com/products'
    },
    reducers:{
        setBaseUrl:(state,action)=>{
            state.value = action.payload;
        }
    }
})

export const {setBaseUrl} = baseURLSlice.actions;
export const selectBaseUrl = (state)=>state.baseUrl.value;

export default baseURLSlice.reducer;


