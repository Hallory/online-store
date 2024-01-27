import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchUsers } from '../../../../redux/slices/usersSlice';

const ManagersList = () => {
    const usersState = useSelector(state => state.users);
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(fetchUsers());
    }, [dispatch]);

    return (
        <div>
            {usersState.status === 'loading' && <p>Loading...</p>}
            {Array.isArray(usersState.list) && usersState.list.length > 0 ? (
                usersState.list.map((user) => (
                    <div key={user.id}>
                        <p>First Name: {user.firstName}</p>
                        <p>Last Name: {user.lastName}</p>
                        <p>Age: {user.age}</p>
                        
                        <hr />
                    </div>
                ))
            ) : (
                <p>No users available.</p>
            )}
        </div>
    );
};

export default ManagersList;
