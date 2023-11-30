import React from 'react';
import {useForm} from 'react-hook-form'
const AuthForm = ({onSubmit, isRegistration}) => {

    const {register,handleSubmit,formState:{errors}, watch} = useForm()
    
    return (
        <form
        className='flex flex-col'
        onSubmit={handleSubmit(onSubmit)}>
            {isRegistration && (
                <label htmlFor="FullName">
                    Fullname
                </label>
            )}
            <label htmlFor="email">
                Email
                <input
                    type="email"
                    
                    ref={register('email',{required: true, pattern: /^\S+@\S+$/i})}
                />
                {errors.email && <p>Please enter a valid email</p>}
            </label>
            <label htmlFor="password">
                Password
                <input 
                type="password" 
                id="password" 
                ref={register('password',{required: true, minLength: 6})}
                />
                {errors.password && <p>Please enter a valid password</p>}
            </label>

                <label htmlFor="keep me logged in">
                    <input type="checkbox" name="keep me logged in" id="keep me logged in" /> Keep me logged in
                </label>

            <button type="submit" onClick={handleSubmit(onSubmit)}>
                {isRegistration ? 'Sign up' : 'Sign in'}
            </button>

            <p>
                or
            </p>

            <button>
                {isRegistration ? 'Sign in with Google' : 'Sign up with Google'}
            </button>

        </form>
    );
};

export default AuthForm;