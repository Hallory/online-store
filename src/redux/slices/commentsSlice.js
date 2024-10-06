import axios from "axios";
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import baseUrlSlice, { selectBaseUrl } from "./baseUrlSlice";

const fetchComments = createAsyncThunk('products/:productId/comments', async (_, thunkAPI) => {
    const response = await axios
})