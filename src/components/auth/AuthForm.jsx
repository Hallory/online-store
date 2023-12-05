import React from 'react';
import { useForm } from 'react-hook-form';
import { Link } from 'react-router-dom';

const AuthForm = ({ onSubmit, isRegistration }) => {
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm();

    return (
        <div className="h-[70vh] md:h-[80vh] w-full flex  justify-between max-w-[100%] md:max-w-[80%] p-8 bg-white rounded-md shadow">
            <div className="w-full lg:w-1/2 h-full flex-col  flex p-6 gap-10 dark:bg-gray-900">
                <h2 className='text-3xl'>
                    {isRegistration ? 'Create an account' : 'Login'}
                    
                </h2>
                <p className="text-gray-500 text-right w-full"><Link to={isRegistration ? '/login' : '/register'}>
                        {isRegistration ? 'Already have an account?' : 'Don\'t have an account?'}
                    </Link>{' '}</p>

                <form
                    className="flex flex-col w-full  gap-8 max-w-md md:gap-7 text-xs md:text-base"
                    onSubmit={handleSubmit(onSubmit)}
                >
                    {isRegistration && (
                        <label htmlFor="fullName" className="flex flex-col">
                            Full Name
                            <input
                                type="text"
                                placeholder="Enter your name"
                                {...register('fullName', { required: true })}
                                className="p-2 border border-gray-300 rounded mt-1"
                            />
                        </label>
                    )}

                    <label htmlFor="email" className="flex flex-col">
                        Email
                        <input
                            type="email"
                            placeholder="Enter your email"
                            {...register('email', {
                                required: true,
                                pattern: /^\S+@\S+$/i,
                            })}
                            className="p-2 border border-gray-300 rounded mt-1"
                        />
                        {errors.email && (
                            <p className="text-red-500">
                                Please enter a valid email
                            </p>
                        )}
                    </label>

                    <label htmlFor="password" className="flex flex-col">
                        <div className="flex justify-between">
                            <p>Password</p>
                            {!isRegistration && (
                                <Link to="#">Forgot password?</Link>
                            )}
                        </div>
                        <input
                            type="password"
                            id="password"
                            {...register('password', {
                                required: true,
                                minLength: 6,
                            })}
                            className="p-2 border border-gray-300 rounded mt-1"
                        />
                        {errors.password && (
                            <p className="text-red-500">
                                Please enter a valid password
                            </p>
                        )}
                    </label>

                    <div className="flex items-center">
                        <input
                            type="checkbox"
                            {...register('keepLoggedIn')}
                            className="mr-2 border max-w-[20px] border-gray-300 rounded p-2"
                        />
                        <label htmlFor="keepLoggedIn">Keep me logged in</label>
                    </div>

                    <button
                        style={{ backgroundColor: 'gray', color: 'white' }}
                        className="bg-slate-300 hover:bg-slate-400 text-black py-2 rounded"
                        type="submit"
                        onClick={handleSubmit(onSubmit)}
                    >
                        {isRegistration ? 'Sign up' : 'Sign in'}
                    </button>

                    <p className="text-center">or</p>

                    <button className="bg-blue-500 hover:bg-blue-700 text-white py-2 rounded">
                        {isRegistration
                            ? 'Sign in with Google'
                            : 'Sign up with Google'}
                    </button>
                </form>
            </div>
            <div className="w-1/2 lg:flex hidden items-center bg-gray-200 dark:bg-gray-900"></div>
        </div>
    );
};

export default AuthForm;
