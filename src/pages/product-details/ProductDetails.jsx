import React from 'react';
import { useParams} from 'react-router-dom';
import ProductInfoCard from '../../components/product/ProductsInfoCards';
import MainContent from './main-content/MainContent';
const ProductDetails = ({ products }) => {
 
  const { productId } = useParams();
  
  const productIdNumber = parseInt(productId, 10);
  const product = products.find((product) => product.id === productIdNumber);

  if (!product) {
    return <div>No data available for the given product ID</div>;
  }

  return (
    <div className="max-w-[1400px] mx-auto">
        <ProductInfoCard product={product} products={products}/>
      <div className="product-header flex lg:flex-row  justify-around lg:gap-10 gap-2 pt-[50px]">
      </div>
      <div className="other-details pt-10">
        asdad
      </div>
    </div>
  );
};

export default ProductDetails;
