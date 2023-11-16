import React, { ReactNode } from 'react';
interface AppBodyProps {
    children: ReactNode;
  }
  const AppBody: React.FC<AppBodyProps> = ({ children }) => {
    return (
      <div className="container mx-auto mt-8">
        <div className="mx-auto max-w-lg">{children}</div>
      </div>
    );
  };

export default AppBody;