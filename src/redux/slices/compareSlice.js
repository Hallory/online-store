import { createSlice } from '@reduxjs/toolkit';

const updateLocalStorage = (compareList) => {
    localStorage.setItem('compareList', JSON.stringify(compareList));
};

const compareSlice = createSlice({
    name: 'compare',
    initialState: [],
    reducers: {
        addToCompare: (state, action) => {
            const newProduct = action.payload;

            const isProductInCompare = state.some(product => product.id === newProduct.id);

            if (!isProductInCompare) {
                state.push(newProduct);

                const storedCompareList = JSON.parse(localStorage.getItem('compareList')) || {};

                if (!storedCompareList[newProduct.category]) {
                    storedCompareList[newProduct.category] = [];
                }

                storedCompareList[newProduct.category].push(newProduct);

                updateLocalStorage(storedCompareList);
            } else {
                console.log(`Product with ID ${newProduct.id} is already in the comparison list.`);
            }
        },
        removeItemFromCompare: (state, action) => {
            
        }
    },
});

export const { addToCompare } = compareSlice.actions;
export default compareSlice.reducer;
