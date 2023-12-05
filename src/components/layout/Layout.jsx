import { useEffect, useState } from 'react';
import Header from './Header/Header';
import Footer from './Footer/Footer';
import Sidebar from './Sidebar/Sidebar';
import axios from 'axios';

const Layout = ({ children }) => {

    const [products, setProducts] = useState([]);

    useEffect(() => {
        const url = 'https://dummyjson.com/products';
        axios
            .get(url)
            .then((res) => {
                const data = res.data;
                setProducts(data.products);
                console.log(data.products);
            })
            .catch((err) => {
                console.log(err);
            });
    }, []);


    return (
        <div className='flex flex-col min-h-screen px-4'>
            <Header />
            <div className='flex-1 flex'>
                <Sidebar products={products} />
                <main className='flex-1 overflow-x-hidden overflow-y-auto'>
                    <div className='container mx-auto px-4 py-6'>
                        {children}
                    </div>
                </main>
            </div>
            <Footer />
        </div>
    )
}

export default Layout;
