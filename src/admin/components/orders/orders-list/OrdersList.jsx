import React from 'react';
import OrderCard from '../order-card/OrderCard';

const OrdersList = ({ordersState}) => {
   
    if (ordersState.status === 'loading') {
        return <p>Loading...</p>;
    }

    if (ordersState.status === 'failed') {
        return <p>Error: {ordersState.error}</p>;
    }
    return (
        <div className='overflow-y-auto flex flex-col max-h-full gap-5 m-0 p-0'>
            {Array.isArray(ordersState) ? (
                ordersState.map((order) => (
                    <OrderCard key={order.id} orders={order} />
                ))
            ) : (
                <p>No orders available.</p>
            )}
            
        </div>
    );
};

export default OrdersList;