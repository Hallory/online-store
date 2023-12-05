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
    <div className="product-header flex lg:flex-row flex-col">
      <div>
        {pathnameSegments.map((segment, index) => (
          <React.Fragment key={segment}>
            <Link to={`/${pathnameSegments.slice(0, index + 1).join('/')}`}>{segment}</Link>
            {index < pathnameSegments.length - 1 && <span> / </span>}
          </React.Fragment>
        ))}
      </div>
      <div>
        <div>
          <ImageCarousel product={product} />
        </div>
      </div>
      <div className="product-details max-w-[33%]">
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
    <div className="other-details">
        
    </div>
  </div>
  );
};

export default ProductDetails;