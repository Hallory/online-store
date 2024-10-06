import React from 'react';

const Description = ({product}) => {
    return (
        <div className="w-full flex rounded-md flex-col">
            {product.description}
        </div>
    );
};

export default Description;