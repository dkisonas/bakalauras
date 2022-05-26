import React from "react";
import ErrorMessage from "../ErrorMessage";
function InputField({ formControl, registerName, index, attrName }) {
  const {
    register,
    getValues,
    formState: { errors },
  } = formControl;

  const getRegisterName = () => {
    if (index > -1) {
      const name = `${registerName}.${index}.${attrName}`;
      return name;
    } else {
      return registerName;
    }
  };

  const getErrorMessage = () => {
    let message;
    if (index > -1) {
      message = errors?.[registerName]?.[index]?.[attrName]?.message;
    } else {
      message = errors?.[registerName]?.message;
    }
    return message;
  };

  return (
    <div>
      <div className="mt-1 border-b border-gray-300 focus-within:border-indigo-600">
        <input
          type="text"
          name="textfield"
          id="textfield"
          defaultValue={getValues(getRegisterName())}
          className="block w-full border-0 border-b border-transparent bg-gray-50 focus:border-indigo-600 focus:ring-0 sm:text-sm"
          {...register(getRegisterName(), { required: "Missing input." })}
        />
      </div>
      <ErrorMessage message={getErrorMessage()} />
    </div>
  );
}

export default InputField;
