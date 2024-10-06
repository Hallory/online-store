import { createSlice } from "@reduxjs/toolkit";

const adminSlice = createSlice({
    name:'admin',
    initialState:{
        isAdmin:false
    },
    reducers:{
        loginAdmin:(state)=>{
            state.isAdmin = true;
        },
        logoutAdmin:(state)=>{
            state.isAdmin = false;
        }
    }
})

export const { loginAdmin, logoutAdmin } = adminSlice.actions;
export const selectAdminStatus = (state) => state.admin.isAdminLoggedIn;

export default adminSlice.reducer;