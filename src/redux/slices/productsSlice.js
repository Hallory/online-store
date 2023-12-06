import { createSlice,createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";
import baseUrlSlice, { selectBaseUrl } from "./baseUrlSlice";

export const getProducts = createAsyncThunk('products/fetchProducts', async (_, thunkAPI) => {
    const response = await axios.get(selectBaseUrl(thunkAPI.getState()));
    return response.data.products;
});
