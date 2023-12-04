import React from 'react';
import Product from '../../components/Products/Product';

const products = [
    {
        _id: "100001",
        img: "https://images.pexels.com/photos/47261/pexels-photo-47261.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        productName: "Samsung Galaxy S20",
        price: "999",
        color: "White",
    },
    {
        _id: "100002",
        img: "https://images.pexels.com/photos/1294886/pexels-photo-1294886.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        productName: "iPhone 15",
        price: "1500",
        color: "Black",
    },
    {
        _id: "100003",
        img: "https://images.pexels.com/photos/10902946/pexels-photo-10902946.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        productName: "XIAOMi",
        price: "500",
        color: "Black",
    },
    {
        _id: "100004",
        img: "https://images.pexels.com/photos/3585090/pexels-photo-3585090.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        productName: "Google Pixel 7 Pro",
        price: "1200",
        color: "Silver",
    },
    {
        _id: "100005",
        img: "https://images.pexels.com/photos/17807423/pexels-photo-17807423.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        productName: "huawei",
        price: "99",
        color: "Black",
    },
];

const Home = () => {
    return (
        <div className='container'>
            <div className='grid sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6'>
                {products.map((product, index) => (
                    <Product
                        key={index}
                        _id={product._id}
                        img={product.img}
                        productName={product.productName}
                        price={product.price}
                        color={product.color}
                    />
                ))}
            </div>
        </div>
    );
};

export default Home;