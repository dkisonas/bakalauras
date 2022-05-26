import React from "react";

function TableDelButton({remove, index}) {
  return (
    <button
      onClick={() => remove(index)}
      className="text-indigo-600 hover:text-indigo-900"
    >
      Delete
    </button>
  );
}

export default TableDelButton;
