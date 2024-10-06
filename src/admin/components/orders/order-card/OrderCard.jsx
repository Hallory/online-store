import React from 'react';

const OrderCard = ({ orders }) => {
    console.log('Orders in OrderCard:', orders);

    return (
        <div className="order-card lg:w-[20rem] lg:h-[15rem] flex flex-col gap-4 p-4 border-2 border-gray-400 bg-gray-100 rounded-lg">
            <div>User: {orders.userId}</div>
            <div className="flex flex-row items-center justify-between gap-4">
            <div>Total:{orders.total}</div>
            <span className='h-full w-[0.1rem] bg-gray-300'/>
            <div>Discounded total: {orders.discountedTotal}</div>
            </div>
            <div></div>
            
        </div>
    );
};

export default OrderCard;