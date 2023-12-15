import React from 'react';
import { Link } from 'react-router-dom';
import ProductCard from '../../components/product/ProductCard';
import { useSelector } from 'react-redux';

const Products = ({ products }) => {
    const filteredProducts = useSelector(state => state.products.filteredProducts);
    const sortProducts = useSelector(state => state.products.sortProducts);
    const allProducts = useSelector(state => state.products.list);

    // Функція для об'єднання сортування та фільтрації
    const applySortingAndFiltering = (items) => {
        // Спробуйте використати сортування, якщо воно присутнє
        const sortedItems = sortProducts.length > 0 ? sortProducts : items;

        // Повертаємо відфільтровані та відсортовані товари
        return filteredProducts.length > 0
            ? sortedItems.filter(product =>
                filteredProducts.some(filteredProduct => filteredProduct.brand === product.brand)
            )
            : sortedItems;
    };

    // Отримуємо відсортовані та відфільтровані товари
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
