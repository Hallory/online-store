import React, { useState } from 'react';
import ProductDetails from '../../pages/product-details/ProductDetails';
import MainContent from '../../pages/product-details/main-content/MainContent';
import ReviewsContent from '../../pages/product-details/reviews-content/ReviewsContent';

const ProductInfoCard = ({ product, products }) => {
  const [activeTab, setActiveTab] = useState('main');

  const renderTabContent = () => {
    switch (activeTab) {
      case 'main':
        return <MainContent product={product} />;
      case 'description':
        return <p>{product.description}</p>;
      case 'reviews': 
        return <ReviewsContent products={products} />;
      default:
        return null;
    }
  };

  return (
    <div className="product-tabs">
      <div className="tab-buttons flex w-full gap-4 pb-6">
        <button onClick={() => setActiveTab('main')}>Main</button>
        <button onClick={() => setActiveTab('description')}>Description</button>
        <button onClick={() => setActiveTab('reviews')}>Reviews</button>
        {/* Добавьте дополнительные кнопки для других вкладок */}
      </div>
      <div className="tab-content">
        {renderTabContent()}
      </div>
    </div>
  );
};

export default ProductInfoCard;
