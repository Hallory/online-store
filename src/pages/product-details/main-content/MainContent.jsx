import React from 'react';
import ImageCarousel from '../../../components/image-carousel/ImageCarousel';
import StarRating from '../../../components/elements/star-rating/StarRating';

const MainContent = ({product}) => {
    return (
        <div className='w-full flex lg:flex-row flex-col'>
        <div className="product-image max-w-[60%]">
          <ImageCarousel product={product} /> 
        </div>
        <div className="product-details max-w-[40%] pl-10">
          <h1 className="text-3xl font-bold mb-2">{product.title}</h1>
          <StarRating totalStars={5} onRate={() => {}} usersRating={product.rating} />
          <p className="text-gray-600 mb-2">{product.category}</p>
          <p className="text-xl mb-4">${product.price}</p>
          <p>{product.description}</p>
          <p className="mt-4">Brand: {product.brand}</p>
          <p>Reviews: {product.numReviews}</p>
          <p>In Stock: {product.countInStock}</p>
        </div>
        </div>
    );
};

export default MainContent;