import React from 'react';
import { useParams} from 'react-router-dom';
import ImageCarousel from '../../components/image-carousel/ImageCarousel';
import StarRating from '../../components/elements/star-rating/StarRating';
import PathName from '../../components/elements/pathname-segment/PathName';
const ProductDetails = ({ products }) => {
 
  const { productId } = useParams();
  
  const productIdNumber = parseInt(productId, 10);
  const product = products.find((product) => product.id === productIdNumber);

  if (!product) {
    return <div>No data available for the given product ID</div>;
  }

  return (
    <div className="max-w-[1400px] mx-auto">
        <PathName/>
      <div className="product-header flex lg:flex-row flex-col justify-around lg:gap-10 gap-2 pt-[50px]">
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
      <div className="other-details pt-10">
        asdad
      </div>
    </div>
  );
};

export default ProductDetails;
