import { createSlice } from '@reduxjs/toolkit';

const filterSlice = createSlice({
    name: 'filter',
    initialState: {
        selectedBrands: [],
    },
    reducers: {
        selectBrands: (state, action) => {
            state.selectedBrands = action.payload;
        },
    },
});

export const { selectBrands } = filterSlice.actions;
export default filterSlice.reducer;
