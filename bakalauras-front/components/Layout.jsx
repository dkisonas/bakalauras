import React from "react";
import Header from "./Header";

const Layout = () => {
  const { children } = this.props;
  return (
    <div className="layout">
      <Header />
      {children}
    </div>
  );
};

export default Layout;
