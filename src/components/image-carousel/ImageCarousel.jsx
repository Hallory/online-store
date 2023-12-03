import React, {useState} from 'react';
import { motion, AnimatePresence } from "framer-motion";
const ImageCarousel = ({product}) => {
    const [currentIndex, setCurrentIndex] = useState(0);
    const images = product.images;
  
        return (
            <div className="flex pt-[50px]">
                <div className='all-images md:flex flex-col hidden'>
                    {images.map((image, index) => (
                        <div
                            key={index}
                            className={`image ${index === currentIndex ? 'active' : ''}`}
                            onClick={() => setCurrentIndex(index)}
                        >
                            <img style={{maxWidth: '150px', maxHeight: '100%'}} src={image} alt=""/>
                        </div>
                    ))}
                </div>
                <div className='main-image w-full max-h-full h-[500px]'>
                    <img className='w-full h-full' src={images[currentIndex]} alt=""/>
                </div>
            </div>
          );
        
};

export default ImageCarousel;