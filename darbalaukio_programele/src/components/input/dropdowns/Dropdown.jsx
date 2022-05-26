import React from "react";
import ErrorMessage from "../ErrorMessage";

function Dropdown({
  label,
  options,
  formControl,
  registerName,
  index,
  attrName,
}) {
  const {
    register,
    getValues,
    formState: { errors },
  } = formControl;

  const getOptionObject = (dropdownValue) => {
    const option = options.find((option) => option.code === dropdownValue);
    return option;
  };

  options.sort((a, b) => {
    let fa = a.name.toLowerCase(),
      fb = b.name.toLowerCase();
    if (fa < fb) {
      return -1;
    }
    if (fa > fb) {
      return 1;
    }
    return 0;
  });

  const getDefaultValue = () => {
    const value = getValues(getRegisterName());
    if (value && value !== "") {
      return getOptionObject(value);
    } else {
      return "";
    }
  };

  const getRegisterName = () => {
    if (index > -1) {
      return `${registerName}.${index}.${attrName}`;
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

  const getUniqueName = () => {
    let name;
    if (index > -1) {
      name = `${registerName}[${index}]${attrName}`;
    } else {
      name = registerName;
    }
    return name;
  };

  return (
    <div>
      <label className="block text-sm font-medium text-gray-700">{label}</label>
      <select
        name={getUniqueName()}
        className="mt-1 block w-full pl-3 pr-10 py-2 text-base border-gray-300 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm rounded-md"
        {...register(getRegisterName(), {
          validate: {
            one: (value) => value !== "" || "Please select a value.",
          },
          required: "Please select a value.",
        })}
      >
        <option value="" />

        {options.map((option) => {
          return (
            <option
              value={option.code}
              key={option.code}
            >{`${option.name} (${option.code})`}</option>
          );
        })}
      </select>
      <ErrorMessage message={getErrorMessage()} />
    </div>
  );
}

export default Dropdown;
