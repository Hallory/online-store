import React, { useState } from 'react';
import { MdNavigateNext, MdNavigateBefore } from 'react-icons/md';

const ImageCarousel = ({ product }) => {
    const [currentIndex, setCurrentIndex] = useState(0);
    const images = product.images;

    return (
        <div className="flex flex-col max-h-[624px] max-w-[664px] select-none w-full">
            <div className="main-image w-full max-w-xl max-h-[500px] relative text-3xl">
                <MdNavigateBefore
                    className="absolute top-1/2 left-0"
                    onClick={() =>
                        setCurrentIndex(
                            (currentIndex - 1 + images.length) % images.length
                        )
                    }
                />
                <img
                    className="w-full h-full object-cover px-10"
                    src={images[currentIndex]}
                    alt=""
                />
                <MdNavigateNext
                    className="absolute top-1/2 right-0"
                    onClick={() =>
                        setCurrentIndex((currentIndex + 1) % images.length)
                    }
                />
            </div>
            <div className="all-images md:flex flex-row w-full hidden gap-8 max-h-[8rem] overflow-y-auto overflow-x-hidden ">
                {images.map((image, index) => (
                    <div
                        key={index}
                        className={`image flex items-center justify-center ${
                            index === currentIndex
                                ? 'border-2 border-gray-400 rounded-xl'
                                : 'border-2 border-black-100'
                        } `}
                        onClick={() => setCurrentIndex(index)}
                    >
                        <img
                            style={{ maxWidth: '112px', maxHeight: '100%' }}
                            src={image}
                            alt=""
                        />
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ImageCarousel;
