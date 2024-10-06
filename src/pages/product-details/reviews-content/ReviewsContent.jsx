import React from 'react';

const ReviewsContent = ({products}) => {
    return (
        <div>
            {products.map((product) => (
                <p key={product.id}>{product.description}</p>
            ))}
        </div>
    );
};

export default ReviewsContent;