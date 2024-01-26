import React, { useEffect, useState } from 'react';
import OrdersList from '../components/orders/orders-list/OrdersList';
import { useDispatch, useSelector } from 'react-redux';
import { fetchOrders } from '../../redux/slices/ordersSlice';


const Orders = () => {
    const ordersState = useSelector((state) => state.orders);
    const dispatch = useDispatch();
    const [lowPriceOrders, setLowPriceOrders] = useState([]);
    const [highPriceOrders, setHighPriceOrders] = useState([]);

    useEffect(() => {
        dispatch(fetchOrders());
    }, [dispatch]);
    
    useEffect(() => {
        if (ordersState.list.carts) {
            filterByPrice(ordersState.list.carts);
        }
    }, [ordersState.list.carts]);
    
    const filterByPrice = (orders) => {
        const lowPrice = orders.filter((order) => order.total < 1000);
        const highPrice = orders.filter((order) => order.total > 1000);
        setLowPriceOrders(lowPrice);
        setHighPriceOrders(highPrice);
    };
    

    return (
        <div className='flex h-full w-full gap-4'>
            <div className='active-orders h-full bg-gray-200 items-center w-1/4 border-2 border-gray-300 rounded flex flex-col'>
            <p className='text-xl p-4 text-left'>Low price orders</p>
            <OrdersList ordersState={lowPriceOrders}/>
            </div>
            <div className='active-orders h-full bg-gray-200 items-center w-1/4 border-2 border-gray-300 rounded flex flex-col'>
            <p className='text-xl p-4 text-left'>High price orders</p>
            <OrdersList ordersState={highPriceOrders}/>
            </div>
        
        </div>
    );
};

export default Orders;
