import React from 'react';
import { useParams, useLocation, Link } from 'react-router-dom';
import ImageCarousel from '../../components/image-carousel/ImageCarousel';

const ProductDetails = ({ products }) => {
  const location = useLocation();
  const { productId } = useParams();
  const productIdNumber = parseInt(productId, 10);

  const pathnameSegments = location.pathname.split('/').filter(Boolean);

  const product = products.find((product) => product.id === productIdNumber);

  if (!product) {
    return <div>No data available for the given product ID</div>;
  }

  return (
    <div className="max-w-[1400px] mx-auto">
      <div className="product-header flex lg:flex-row flex-col justify-around lg:gap-10 gap-2 pt-[50px]">
        <div className="w-full">
          {pathnameSegments.map((segment, index) => (
            <React.Fragment key={segment}>
              <Link to={`/${pathnameSegments.slice(0, index + 1).join('/')}`}>{segment}</Link>
              {index < pathnameSegments.length - 1 && <span> / </span>}
            </React.Fragment>
          ))}
        </div>
        <div className="product-image max-w-[60%]">
          <ImageCarousel product={product} />
        </div>
        <div className="product-details max-w-[40%] pl-10">
          <h1 className="text-3xl font-bold mb-2">{product.title}</h1>
          <p>Rating: {product.rating}</p>
          <p className="text-gray-600 mb-2">{product.category}</p>
          <p className="text-xl mb-4">${product.price}</p>
          <p>{product.description}</p>
          <p className="mt-4">Brand: {product.brand}</p>
          <p>Reviews: {product.numReviews}</p>
          <p>In Stock: {product.countInStock}</p>
        </div>
      </div>
      <div className="other-details"></div>
    </div>
  );
};

export default ProductDetails;
