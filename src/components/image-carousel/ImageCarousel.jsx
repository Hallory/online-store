import React, {useState} from 'react';
import { MdNavigateNext, MdNavigateBefore } from "react-icons/md";

const ImageCarousel = ({product}) => {
    const [currentIndex, setCurrentIndex] = useState(0);
    const images = product.images;
  
        return (
            <div className="flex ">
                <div className='all-images md:flex flex-col hidden gap-6 max-h-[500px] overflow-y-auto overflow-x-hidden '>
           
                    {images.map((image, index) => (
                        <div
                            key={index}
                            className={`image ${index === currentIndex ? 'active' : ''} pr-10`}
                            onClick={() => setCurrentIndex(index)}
                        >
                            <img style={{maxWidth: '150px', maxHeight: '100%'}} src={image} alt=""/>
                        </div>
                    ))}
                </div>
                <div className='main-image w-full max-h-[500px]  relative text-3xl'>
                    <MdNavigateBefore className='absolute top-1/2 left-0' onClick={() => setCurrentIndex((currentIndex - 1 + images.length) % images.length)}/>
                    <img className='w-full h-full px-10' src={images[currentIndex]} alt=""/>
                    <MdNavigateNext className='absolute top-1/2 right-0' onClick={() => setCurrentIndex((currentIndex + 1) % images.length)}/>
                </div>
            </div>
          );
        
};

export default ImageCarousel;