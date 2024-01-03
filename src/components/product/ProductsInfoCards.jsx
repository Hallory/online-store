import React, { useState } from 'react';
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
            <div className="tab-buttons flex p-6 w-full bg-black-200 gap-10 ">
                <button
                    className={
                        activeTab === 'main' ? 'border-b-2 border-primary-400' : ''
                    }
                    onClick={() => setActiveTab('main')}
                >
                    Main
                </button>
                <button
                    className={
                        activeTab === 'description'
                            ? 'border-b-2 border-primary-400'
                            : ''
                    }
                    onClick={() => setActiveTab('description')}
                >
                    Description
                </button>
                <button
                    className={
                        activeTab === 'reviews' ? 'border-b-2 border-primary-400' : ''
                    }
                    onClick={() => setActiveTab('reviews')}
                >
                    Reviews
                </button>
            </div>
            <div className="tab-content pt-10">{renderTabContent()}</div>
        </div>
    );
};

export default ProductInfoCard;
