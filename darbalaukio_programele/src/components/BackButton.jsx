import React from "react";
import { ArrowNarrowLeftIcon } from "@heroicons/react/outline";
import { useNavigate } from 'react-router-dom';

function BackButton() {
    const navigate = useNavigate();
  return (
    <div>
      <button
        onClick={() => navigate('/dashboard')}
        className="border-b-2 border-transparent pr-1 inline-flex items-center text-sm font-medium text-gray-500 hover:text-gray-700 hover:border-gray-300"
      >
        <ArrowNarrowLeftIcon
          className="mr-3 h-5 w-5 text-gray-400"
          aria-hidden="true"
        />
        Go back
      </button>
    </div>
  );
}

export default BackButton;
