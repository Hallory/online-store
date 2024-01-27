import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

export const fetchUsers = createAsyncThunk('users/fetchUsers', async () => {
    const response = await axios.get('https://dummyjson.com/users');
    return response.data.users;
});
const userSlice = createSlice({
    name: 'users',
    initialState: {
        list: [],
        status: 'idle',
        error: null
    },
    reducers: {},
    extraReducers: (builder) => {
        builder.addCase(fetchUsers.pending, (state) => {
            state.status = 'loading';
            state.error = null;
        })
        .addCase(fetchUsers.fulfilled, (state, action) => {
            state.status = 'succeeded';
            state.list = action.payload;
        })
        .addCase(fetchUsers.rejected, (state, action) => {
            state.status = 'failed';
            state.error = action.error.message;
        });
    }
});

export default userSlice.reducer;
