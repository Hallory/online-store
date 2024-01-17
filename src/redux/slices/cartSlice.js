import { createSlice } from '@reduxjs/toolkit';

const loadCartFromLocalStorage = () => {
    const savedCart = localStorage.getItem('cart');
    return savedCart ? JSON.parse(savedCart) : [];
};

const cartSlice = createSlice({
    name: 'cart',
    initialState: {
        cart: loadCartFromLocalStorage(),
        isVisible: false,
    },
    reducers: {
        addToCart: (state, action) => {
            const itemPresent = state.cart.find(item => item.id === action.payload.id);
            if (itemPresent) {
                itemPresent.quantity++;
            } else {
                state.cart.push({ ...action.payload, quantity: 1 });
            }
            localStorage.setItem('cart', JSON.stringify(state.cart));
        },
        removeFromCart: (state, action) => {
            const removedItem = state.cart.filter(item => item.id !== action.payload);
            state.cart = removedItem;
            localStorage.setItem('cart', JSON.stringify(state.cart));
        },
        incrementQuantity: (state, action) => {
            const itemPresent = state.cart.find(item => item.id === action.payload);
            itemPresent.quantity++;
            localStorage.setItem('cart', JSON.stringify(state.cart));
        },
        decrementQuantity: (state, action) => {
            const itemPresent = state.cart.find(item => item.id === action.payload);
            if (itemPresent.quantity === 1) {
                const removeFromCart = state.cart.filter(item => item.id !== action.payload.id);
                state.cart = removeFromCart;
            } else {
                itemPresent.quantity--;
            }
            localStorage.setItem('cart', JSON.stringify(state.cart));
        },
        sideCartVisible: (state, action) => {
            state.isVisible = action.payload;
        },
    },
});

export const { addToCart, removeFromCart, incrementQuantity, decrementQuantity, sideCartVisible } = cartSlice.actions;
export default cartSlice.reducer;
