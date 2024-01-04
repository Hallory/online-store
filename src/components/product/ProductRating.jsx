import React, { useState, useEffect } from 'react';
import { FaStar } from 'react-icons/fa';

const ProductRating = (props) => {
    const { rating } = props.product;
    const [filledStars, setFilledStars] = useState(0);

    useEffect(() => {
        setFilledStars(rating >= 4.5 ? 5 : Math.floor(rating));
    }, [rating]);

    return (
        <div className='flex items-end'>
            {[...Array(5)].map((_, index) => {
                const isActive = filledStars > index;
                return (
                    <FaStar
                        key={index}
                        className={isActive ? 'text-warning-500' : 'text-gray-400'}
                        size={24}
                    />
                );
            })}
            <span className='pl-2 text-[0.625rem] text-info-700'>(103 reviews)</span>
        </div>
    );
};

export default ProductRating;
