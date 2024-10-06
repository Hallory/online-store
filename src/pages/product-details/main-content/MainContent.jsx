import React from 'react';
import ImageCarousel from '../../../components/image-carousel/ImageCarousel';
import { LuScale } from 'react-icons/lu';
import { CiHeart } from 'react-icons/ci';
import MainProductsDescriptions from './main-product-descriptions/MainProductsDescriptions';
import DeliveryVariants from './delivery-variants/DeliveryVariants';
import Price from './main-product-descriptions/price/Price';

const MainContent = ({ product }) => {
    return (
        <div className="w-full flex lg:flex-row flex-col justify-between">
            <div className="product-image w-1/2 flex justify-center">
                <ImageCarousel product={product} />
            </div>
            <div className="main-content w-1/2 px-10 flex flex-col justify-center">
                <div className="w-[38.8rem] flex items-center max-h-[11rem] gap-6 border-2 border-black p-10 mt-6 rounded-md">
                    <Price product={product} />
                    <div>
                        <div>
                            <span>Payment by installment</span>
                            <span></span>
                        </div>
                        <button className="text-2xl p-3 px-7 rounded-xl border-primary-500 border-2 text-primary-500">
                            Buy on credit
                        </button>
                    </div>
                    <span className="flex items-center gap-4 text-3xl text-black-800">
                        <button>
                            <LuScale />
                        </button>
                        <button>
                            <CiHeart />
                        </button>
                    </span>
                </div>

                <div className="w-[38.8rem] mt-10">
                    <MainProductsDescriptions product={product} />
                </div>
                <div>
                    <DeliveryVariants />
                </div>
            </div>
        </div>
    );
};

export default MainContent;
