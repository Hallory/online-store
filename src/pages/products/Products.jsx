import React from 'react';
import { Link } from 'react-router-dom';
import ProductCard from '../../components/product/ProductCard';
import { useSelector } from 'react-redux';

const Products = ({ products }) => {
    const filteredProducts = useSelector(state => state.products.filteredProducts);
    const sortProducts = useSelector(state => state.products.sortProducts);
    const allProducts = useSelector(state => state.products.list);

    const applySortingAndFiltering = (items) => {
        const sortedItems = sortProducts.length > 0 ? sortProducts : items;

        return filteredProducts.length > 0
            ? sortedItems.filter(product =>
                filteredProducts.some(filteredProduct => filteredProduct.brand === product.brand)
            )
            : sortedItems;
    };

    const displayedProducts = applySortingAndFiltering(allProducts);

    return (
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4 p-4">
            {displayedProducts.map((product) => (
                <Link to={`/products/${product.id}`} key={product.id}>
                    <ProductCard product={product} />
                </Link>
            ))}
        </div>
    );
};

export default Products;
