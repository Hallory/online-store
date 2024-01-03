import React from 'react';
import ImageCarousel from '../../../components/image-carousel/ImageCarousel';
import { LuScale } from 'react-icons/lu';
import { CiHeart, CiCircleCheck } from 'react-icons/ci';
import MainProductsDescriptions from './main-product-descriptions/MainProductsDescriptions';

const MainContent = ({ product }) => {
    return (
        <div className="w-full flex lg:flex-row flex-col">
            <div className="product-image w-[50%]">
                <ImageCarousel product={product} />
            </div>
            <div className="product-details w-[50%] pl-10">
                <div className="w-full flex items-center gap-3 border-2 border-black p-10 mt-6 rounded-md">
                    <span className=" flex flex-col ">
                        <div className="gap-2 flex items-center text-xs">
                            <span className="text-gray-500 line-through">
                                {product.price + 150}
                            </span>
                            <span className="bg-danger-600 px-3 rounded-md">
                                -150
                            </span>
                        </div>
                        <span className="text-4xl text-danger-600 font-bold">
                            {product.price}$
                        </span>
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
                    <button className="text-2xl text-gray-100 py-3 px-7 rounded-xl bg-primary-500">
                        Buy
                    </button>
                    <button className="text-2xl p-3 px-7 rounded-xl border-primary-500 border-2 text-primary-500">
                        Buy on credit
                    </button>
                    <span className="flex items-center gap-4 text-2xl">
                        <button>
                            <LuScale />
                        </button>
                        <button>
                            <CiHeart />
                        </button>
                    </span>
                </div>

                <div className="w-full mt-10">
                    <MainProductsDescriptions product={product} />
                </div>
            </div>
        </div>
    );
};

export default MainContent;
