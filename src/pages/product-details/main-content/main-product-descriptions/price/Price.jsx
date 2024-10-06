import React from 'react';
import { CiCircleCheck } from 'react-icons/ci';
const NowBuying = ({ product }) => {
    return (
        <div className="h-[9.5rem]">
            <span className="price flex flex-col ">
                <div className="gap-2 flex items-center text-xs">
                    <span className="text-gray-500 line-through">
                        {product.price + 150}
                    </span>
                </div>
                <span className="text-4xl text-danger-600 font-bold border-b-[0.025rem] border-black-300">
                    {product.price}$
                </span>
                <span className="text-gray-500 rounded-md">sparren: -150</span>
                <span
                    className={`${
                        !!product.countInStock === 0
                            ? 'text-danger-500'
                            : 'text-success-500'
                    } flex items-center gap-2 px-3 bg-success-100 border-2 border-success-500 rounded-md`}
                >
                    {!!product.countInStock === 0
                        ? ' - OUT OF STOCK'
                        : 'available'}{' '}
                    {<CiCircleCheck />}
                </span>
            </span>
            <button className="text-xl w-full text-gray-100  rounded-md bg-primary-500">
                Buy
            </button>
        </div>
    );
};
const CreditBuying = ({ product }) => {
    return <div></div>;
};
const Price = ({ product }) => {
    return (
        <div>
            <NowBuying product={product} />
            <CreditBuying product={product} />
        </div>
    );
};

export default Price;
