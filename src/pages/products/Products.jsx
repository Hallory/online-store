import React from 'react';
import { Link } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { addToWishlist } from '../../redux/slices/wishlistSlice';
import ProductCard from '../../components/product/ProductCard';
import Button from '../../components/elements/Button';
import { IoMdHeartEmpty } from "react-icons/io";

const Products = () => {
    const dispatch = useDispatch();

    const filteredProducts = useSelector(state => state.products.filteredProducts);
    const sortProducts = useSelector(state => state.products.sortProducts);
    const priceFilter = useSelector(state => state.products.priceFilter);
    const allProducts = useSelector(state => state.products.list);

    const applySortingAndFiltering = (items) => {
        let sortedItems = sortProducts.length > 0 ? sortProducts : items;

        if (priceFilter.length > 0) {
            sortedItems = sortedItems.filter(product =>
                product.price >= priceFilter[0] && product.price <= priceFilter[1]
            );
        }

        return filteredProducts.length > 0
            ? sortedItems.filter(product =>
                filteredProducts.some(filteredProduct => filteredProduct.brand === product.brand)
            )
            : sortedItems;
    };

    const displayedProducts = applySortingAndFiltering(allProducts);

    const handleAddToWishlist = (product) => {
        dispatch(addToWishlist(product));
    }

    return (
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-3 gap-4 px-4">
            {displayedProducts.map((product) => (
                <div
                    className='max-w-[21.563rem] w-full border border-gray-150 rounded-md'
                    key={product.id}>
                    <Link to={`/products/${product.id}`}>
                        <ProductCard product={product} />
                    </Link>
                    <div className='flex justify-center gap-1 pb-4 pt-2'>
                        <Button
                            label='Add to Cart'
                            onClick={() => { console.log('Click!'); }}
                            variant=''
                        />
                        <Button
                            icon={IoMdHeartEmpty}
                            onClick={() => handleAddToWishlist(product)}
                            variant='icon'
                        />
                    </div>
                </div>
            ))}
        </div>
    );
};

export default Products;
