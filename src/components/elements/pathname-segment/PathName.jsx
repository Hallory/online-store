import React from 'react';
import { useLocation } from 'react-router-dom';
import { Link } from 'react-router-dom';
const PathName = () => {
    const location = useLocation();
  
    const pathnameSegments = location.pathname.split('/').filter(Boolean);
    return (
        <div className="w-full">
          {pathnameSegments.map((segment, index) => (
            <React.Fragment key={segment}>
              <Link to={`/${pathnameSegments.slice(0, index + 1).join('/')}`}>{segment}</Link>
              {index < pathnameSegments.length - 1 && <span> / </span>}
            </React.Fragment>
          ))}
        </div>
    );
};

export default PathName;