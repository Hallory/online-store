import React from 'react';
import { useSelector } from 'react-redux';


const Wishlist = () => {
    const wishlist = useSelector((state) => state.wishlist);
    console.log(wishlist);

    const getTotal = () => {
        let total = 0;
        wishlist.forEach((product) => {
            total += product.price;
        });
        return total;
    };

    return (
        <div>
            <h2>Wishlist</h2>
            <ul>
                {wishlist.map((product) => (
                    <li key={product.id}>
                        <h3>{product.title}</h3>
                            {product.thumbnail && (
                                <img className='h-60 w-full object-cover object-center' src={product.thumbnail} alt='product img' />
                            )}
                    </li>
                ))}
            </ul>
            <p>{getTotal()}</p>
        </div>
    );
}

export default Wishlist;
