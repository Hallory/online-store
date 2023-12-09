import React from 'react';
import { Link } from 'react-router-dom';
import ProductCard from '../../components/product/ProductCard';
import { useSelector } from 'react-redux';

const Products = ({ products }) => {

    const brandFilter = useSelector((state) => state.selectedBrands);

    console.log(brandFilter);
    
    return (
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4 p-4">
                {products &&
                    products.map((product) => (
                        <Link to={`/products/${product.id}`} key={product.id}>
                            <ProductCard product={product} />
                        </Link>
                ))}
        </div>
    )
};
        
export default Products;