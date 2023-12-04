import React from "react";
import Button from '../elements/Button';
import { IoMdHeartEmpty } from "react-icons/io";

const Product = (props) => {
    return (
        <div class='rounded-md overflow-hidden border border-gray-200'>
            <img class='h-60 w-full object-cover object-center' src={props.img} alt='product img' />
            <div class='px-3 pt-3'>
                <h3 class=''>{props.productName}</h3>
                <div class=''>
                    <p class=''>${props.price}</p>
                </div>
            </div>
            <div className="">
                <Button
                    label='Add to Cart'
                    onClick={() => { console.log('Click!'); }}
                    variant=''
                />
                <Button
                    icon={IoMdHeartEmpty}
                    onClick={() => { console.log('Click!'); }}
                    variant=''
                />
            </div>
        </div>
    );
};

export default Product;