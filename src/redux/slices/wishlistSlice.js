import { createSlice } from "@reduxjs/toolkit";

const wishlistSlice = createSlice({
    name: "wishlist",
    initialState: [],
        reducers: {
        addToWishlist: (state, action) => {
            const newProduct = action.payload;
            const isProductInWishlist = state.some(product => product.id === newProduct.id);

            if (!isProductInWishlist) {
                state.push(newProduct);
            }
        }
    }

})

export const { addToWishlist } = wishlistSlice.actions;
export default wishlistSlice.reducer;