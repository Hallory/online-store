import React from 'react';

const Performance = ({ product }) => {

    const [colors, setColors] = React.useState([
        'green',
        'black',
    ]);

    const [storage, setStorage] = React.useState([
        '128 GB',
        '256 GB',
        '512 GB',
    ])

    return (
        <div className="w-full flex bg-white rounded-md flex-col">
            <div className="">
                {(product.category === 'smartphones' ||
                    product.category === 'laptops') && (
                    <div className='flex gap-8 p-6 flex-col pb-10 '>
                        <div className="flex gap-3">
                            Colors: {colors.map((color) => (
                            <button key={color} className={`w-6 h-6 rounded-full ${color === 'green' ? 'bg-success-600' : 'bg-gray-900'}`}></button>
                        ))}
                        </div>
                       <div className="flex gap-3">
                         Internal Storage:   <select className='border-2 border-gray-300 rounded-md'>
                           {storage.map((storage) => (
                              <option>{storage}</option>
                            ))}
                          </select>
                       </div>
                       <div className="flex gap-3">Resolution: 2400x1080 FHD+</div>
                       <div className="flex gap-3">Memory (RAM): 8GB, LPDDR5</div>
                       <div className="flex gap-3">Battery: 5000mAh</div>
                    </div>
                    
                )}
            </div>
        </div>
    );
};

export default Performance;
